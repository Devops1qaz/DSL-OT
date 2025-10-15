folder('terraform') {
    displayName('Terraform')
    description('Contains all Terraform infrastructure deployment jobs')
}

folder('terraform/env') {
    displayName('Env')
    description('Different environment-level folders')
}

folder('terraform/env/dev') {
    displayName('Dev')
    description('Development environment jobs')
}

folder('terraform/env/dev/salary-api') {
    displayName('Salary-API')
    description('Salary-API Terraform deployments')
}

// ---------------- Helper Function ----------------
def createTerraformJob(String jobName, String branchName, String jenkinsfilePath, String jobDescription) {
    pipelineJob("terraform/env/dev/salary-api/${jobName}") {

        description(jobDescription)

        parameters {
            stringParam('BRANCH_NAME', branchName, 'Git branch to use')
            choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
        }

        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/Snaatak-Cloudops-Crew/IAC-Terraform-repo.git")
                            credentials("github-token")
                        }
                        branch("${branchName}")
                    }
                }
                scriptPath(jenkinsfilePath)
            }
        }

        properties([
            environmentVariables([
                AWS_CREDENTIALS_ID: "AWS-Sneha"
            ])
        ])
    }
}

// ---------------- Actual Salary-API Jobs ----------------
createTerraformJob("security-group",      "SCRUM-480-deepak", "Env/Dev/Applications/Salary-App-Infra/Security-Group/Jenkinsfile",      "Deploys/Destroys Security Group for Salary-API.")
createTerraformJob("launch-template",     "SCRUM-481-deepak", "Env/Dev/Applications/Salary-App-Infra/Launch-Template/Jenkinsfile",     "Deploys/Destroys Launch Template for Salary-API.")
createTerraformJob("target-group",        "SCRUM-482-deepak", "Env/Dev/Applications/Salary-App-Infra/Target-Group/Jenkinsfile",        "Deploys/Destroys Target Group for Salary-API.")
createTerraformJob("instance",            "SCRUM-479-deepak", "Env/Dev/Applications/Salary-App-Infra/Instance/Jenkinsfile",            "Deploys/Destroys EC2 Instance for Salary-API.")
createTerraformJob("autoscaling-group",   "SCRUM-483-deepak", "Env/Dev/Applications/Salary-App-Infra/Autoscaling-Group/Jenkinsfile",   "Deploys/Destroys Auto Scaling Group for Salary-API.")
createTerraformJob("autoscaling-policies","SCRUM-484-deepak", "Env/Dev/Applications/Salary-Dev-Infra/Autoscaling-Policies/Jenkinsfile","Deploys/Destroys Auto Scaling Policies for Salary-API.")
createTerraformJob("alb-listener-rule",   "SCRUM-485-deepak", "Env/Dev/Applications/Salary-App-Infra/ALB-Listener-Rule/Jenkinsfile",   "Deploys/Destroys ALB Listener Rules for Salary-API.")

// ---------------- Master Pipeline ----------------
pipelineJob("terraform/env/dev/salary-api/master-pipeline") {
    description("Master pipeline to run all Salary-API infra jobs in correct sequence")

    parameters {
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    stages {
                        stage('Security Group') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/security-group', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-480-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                        stage('Launch Template') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/launch-template', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-481-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                        stage('Target Group') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/target-group', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-482-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                        stage('Instance') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/instance', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-479-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                        stage('AutoScaling Group') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/autoscaling-group', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-482-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                        stage('AutoScaling Policies') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/autoscaling-policies', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-483-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                        stage('ALB Listener Rule') {
                            steps {
                                build job: 'terraform/env/dev/salary-api/alb-listener-rule', parameters: [string(name: 'BRANCH_NAME', value: 'SCRUM-484-deepak'), string(name: 'ACTION', value: params.ACTION)]
                            }
                        }
                    }
                }
            """.stripIndent())
        }
    }
}

println "✔︎ Salary-API DSL updated successfully with correct master pipeline sequence"
