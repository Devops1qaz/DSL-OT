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
    description('Application Infra')
}

folder('terraform/env/dev/applications/employee-infra') {
    displayName('Employee-Infra')
    description('Employee application infrastructure resources')
}

pipelineJob("terraform/env/dev/applications/employee-infra/Wrapper-Code") {
    description("Deploys/Destroys Employee application using Wrapper-Code.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-577-Sneha-Joshi', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Cloudops-Crew/IAC-Terraform-repo.git')
                        credentials('github-token')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Wrappercode/Env/Dev/Applications/Employee-App/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/employee-infra"
