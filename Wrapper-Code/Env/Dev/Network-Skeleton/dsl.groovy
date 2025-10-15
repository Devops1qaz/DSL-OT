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

folder('terraform/env/dev/Network-Skeleton') {
    displayName('Network-Skeleton')
    description('Terraform network skeleton deployment')
}

pipelineJob("terraform/env/dev/Network-Skeleton/deploy") {
    parameters {
        stringParam('BRANCH_NAME', 'main', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Devops1qaz/Wrapper-Code.git')
                        
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Network-Skeleton/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/Network-Skeleton/deploy"
