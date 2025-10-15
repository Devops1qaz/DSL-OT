// ---------- Folder Hierarchy ----------
folder('OTMS') { description('OTMS parent folder') }
folder('OTMS/Env') { description('All environments') }
folder('OTMS/Env/Dev') { description('Dev environment') }
folder('OTMS/Env/Dev/Applications') { description('Applications for Dev') }
folder('OTMS/Env/Dev/Applications/Network-Skeleton') { description('Network Skeleton app in Dev') }
folder('OTMS/Env/Dev/Applications/Network-Skeleton/Infra') { description('Network Skeleton infra deployment jobs for Dev') }

// ---------- Global Vars ----------
def repoUrl = 'https://github.com/Snaatak-Cloudops-Crew/IAC-Terraform-repo.git'
def gitCredsId = 'sonal-github'          
def slackCredsId = 'slack-notifications' 
def awsCredsId = 'AWS-Sneha'

// ---------- Components ----------
def components = [
  [id: 'network_skeleton', branch: 'SCRUM-505-divya-mishra', path: 'Wrappercode/Env/Dev/Network-Skeleton']
]

// ---------- Job Creation ----------
components.each { c ->
    pipelineJob("OTMS/Env/Dev/Applications/Network-Skeleton/Infra/${c.id}") {
        description("Deploy ${c.id} - branch ${c.branch} for Dev environment")
        parameters {
            stringParam('ENV', 'dev', 'Environment to deploy')
            stringParam('AWS_REGION', 'ap-southeast-1', 'AWS region')
        }
        definition {
            cps {
                script("""
pipeline {
  agent any
  tools { terraform 'terraform' }

  parameters {
    string(name: 'ENV', defaultValue: 'dev', description: 'Environment')
    string(name: 'AWS_REGION', defaultValue: 'ap-southeast-1', description: 'AWS region')
  }

  environment {
    REPO_URL = '${repoUrl}'
    COMPONENT_PATH = '${c.path}'
    COMPONENT_NAME = '${c.id}'
    AWS_DEFAULT_REGION = "\${AWS_REGION}"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: '${c.branch}', url: '${repoUrl}', credentialsId: '${gitCredsId}'
      }
    }

    stage('Terraform Apply') {
      steps {
        withCredentials([aws(credentialsId: '${awsCredsId}', accessKeyVariable: 'AWS_ACCESS_KEY_ID', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
          dir("\${COMPONENT_PATH}") {
            sh '''
              terraform init -reconfigure
              terraform plan -out=tfplan
              terraform apply -auto-approve tfplan
            '''
          }
        }
      }
    }
  }

  post {
    success {
      echo ":white_check_mark: ${c.id} deployed successfully for ENV=\${ENV}"
      slackSend(channel: '#jenkins-notify', message: ":white_check_mark: ${c.id} deployed successfully in Dev (\${ENV})", tokenCredentialId: '${slackCredsId}')
    }
    failure {
      echo ":x: ${c.id} deployment failed for ENV=\${ENV}"
      slackSend(channel: '#jenkins-notify', message: ":x: ${c.id} deployment FAILED in Dev (\${ENV})", tokenCredentialId: '${slackCredsId}')
    }
  }
}
                """.stripIndent())
                sandbox(true)
            }
        }
    }
}
