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

folder('terraform/env/dev/applications/frontend-infra') {
    displayName('Frontend-Infra')
    description('Frontend infrastructure resources')
}


pipelineJob("terraform/env/dev/applications/frontend-infra/Deploy-security-group") {
    description("Deploys/Destroys frontend Security Group using Terraform.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-439-ishaan', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Apt-Get-Swag/terraform.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Frontend-API-Infra/Security-Group/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/frontend-infra/Deploy-security-group"



pipelineJob("terraform/env/dev/applications/frontend-infra/target-group") {
    description("Deploys/Destroys  target-group using Terraform.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-441-ishaan', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Apt-Get-Swag/terraform.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Frontend-API-Infra/Target-group/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/frontend-infra/Deploy-target-group"


pipelineJob("terraform/env/dev/applications/frontend-infra/launch-template") {
    description("Deploys/Destroys  launch-template using Terraform.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-440-ishaan', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Apt-Get-Swag/terraform.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Frontend-API-Infra/Launch-Template/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/frontend-infra/Deploy-launch-template"

pipelineJob("terraform/env/dev/applications/frontend-infra/auto-scaling-group") {
    description("Deploys/Destroys  auto-scaling-group using Terraform.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-442-ishaan', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Apt-Get-Swag/terraform.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Frontend-API-Infra/autoscaling-group/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/frontend-infra/autoscaling-group"


pipelineJob("terraform/env/dev/applications/frontend-infra/auto-scaling-policies") {
    description("Deploys/Destroys  autoscaling policies using Terraform.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-443-ishaan', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Apt-Get-Swag/terraform.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Frontend-API-Infra/autoscaling-policies/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/frontend-infra/autoscaling-policies"

pipelineJob("terraform/env/dev/applications/frontend-infra/listener-rules-ALB") {
    description("Deploys/Destroys  autoscaling policies using Terraform.")

    parameters {
        stringParam('BRANCH_NAME', 'SCRUM-444-ishaan', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/Snaatak-Apt-Get-Swag/terraform.git')
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Frontend-API-Infra/Listener-rule/Jenkinsfile")
        }
    }
}

println "✔︎ Pipeline job created → terraform/env/dev/applications/frontend-infra/Listener-Rules"
