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
        stringParam('BRANCH_NAME', 'main', 'Git branch to use')
        choiceParam('ACTION', ['apply', 'destroy'], 'Choose apply to create infra or destroy to remove it.')
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('folder('terraform') {
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

folder('terraform/env/dev/applications/notification-infra') {
    displayName('notification-Infra')
    description('notification infrastructure resources')
}


pipelineJob("terraform/env/dev/applications/notification-infra/Deploy-security-group") {
    description("Deploys/Destroys notification Security Group using Terraform.")

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
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/Notification-API-Infra/Security-Group/Jenkinsfile")
        }
    }
}

println " Pipeline job created → terraform/env/dev/applications/notification-infra/Deploy-security-group"



pipelineJob("terraform/env/dev/applications/notification-infra/target-group") {
    description("Deploys/Destroys  target-group using Terraform.")

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
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev / Notification-API-Infra/Target-Group/Jenkinsfile")
        }
    }
}

println " Pipeline job created → terraform/env/dev/applications/notification-infra/Deploy-target-group"


pipelineJob("terraform/env/dev/applications/notification-infra/launch-template") {
    description("Deploys/Destroys  launch-template using Terraform.")

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
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Application/Notification-Worker/Launch-Template/Jenkinsfile")
        }
    }
}

println " Pipeline job created → terraform/env/dev/applications/notification-infra/Deploy-launch-template"

pipelineJob("terraform/env/dev/applications/notification-infra/auto-scaling-group") {
    description("Deploys/Destroys  auto-scaling-group using Terraform.")
    description("Deploys/Destroys Auto Scaling Group for Notification Worker Infra using Terraform.")

parameters {
stringParam('BRANCH_NAME', 'main', 'Git branch to use')
@@ -121,65 +14,14 @@ pipelineJob("terraform/env/dev/applications/notification-infra/auto-scaling-grou
url('https://github.com/Devops1qaz/Wrapper-Code.git')
credentials('git')
}
                    // Correctly expands the parameter at runtime
branches('*/${BRANCH_NAME}')
}
}
            // ✅ Correct Jenkinsfile path for your pipeline
scriptPath("Env/Dev/Application/Notification-Worker-Infra/Auto-Scaling-Group/Jenkinsfile")
}
}
}

println " Pipeline job created → terraform/env/dev/applications/notification-infra/autoscaling-group"


pipelineJob("terraform/env/dev/applications/notification-infra/auto-scaling-policies") {
    description("Deploys/Destroys  autoscaling policies using Terraform.")

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
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Terraform/Notification-Worker/Auto-Scaling-Policies/Jenkinsfile")
        }
    }
}

println " Pipeline job created → terraform/env/dev/applications/notification-infra/autoscaling-policies"

pipelineJob("terraform/env/dev/applications/notification-infra/listener-rules-ALB") {
    description("Deploys/Destroys  autoscaling policies using Terraform.")

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
                        credentials('git')
                    }
                    branches('*/${BRANCH_NAME}')
                }
            }
            scriptPath("Env/Dev/Applications/notification-API-Infra/Listener-rule/Jenkinsfile")
        }
    }
}

println " Pipeline job created → terraform/env/dev/applications/notification-infra/Listener-Rules"
println "✅ Pipeline job created → terraform/env/dev/applications/notification-infra/auto-scaling-group"')
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
