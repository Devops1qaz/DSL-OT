folder('terraform') {
    displayName('Terraform')
    description('Contains all Terraform infrastructure deployment jobs')
}

folder('terraform/env') {
    displayName('Env')
    description('Environment specific deployments')
}

folder('terraform/env/dev') {
    displayName('Dev')
    description('Development environment deployments')
}

folder('terraform/env/dev/applications') {
    displayName('Applications')
    description('Application-Infra')
}

folder('terraform/env/dev/applications/Attendance-Infra') {
    displayName('Attendance-Infra')
    description('Attendance infrastructure resources')
}


pipelineJob("terraform/env/dev/applications/Attendance-Infra/Wrapper-Code") {
    description("Deploys/Destroys frontend using Wrapper-Code.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-504-kawalpreetkour', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Cloudops-Crew/IAC-Terraform-repo.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Wrappercode/Env/Dev/Applications/Attendance-App/Jenkinsfile")
        }
    }
}

println "Pipeline job created → terraform/env/dev/applications/Attendance-Infra"
