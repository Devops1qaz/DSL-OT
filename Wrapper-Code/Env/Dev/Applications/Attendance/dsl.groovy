

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

folder('terraform/env/dev/applications/Attendance') {
    displayName('Attendance')
    description('Attendance infrastructure resources')
}

// ------------------ Security Group ------------------
pipelineJob("terraform/env/dev/applications/Attendance/security-group") {
    description("Deploys/Destroys Security Group for Attendance Infra using Terraform.")

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
                    branches("*/${BRANCH_NAME}")
                }
            }
            scriptPath("Env/Dev/Applications/Attendance/Security-Group/Jenkinsfile")
        }
    }
}

println "✅ Pipeline job created → terraform/env/dev/applications/Attendance/security-group"


// ------------------ Target Group ------------------
pipelineJob("terraform/env/dev/applications/Attendance/target-group") {
    description("Deploys/Destroys Target Group for Attendance Infra using Terraform.")

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
                    branches("*/${BRANCH_NAME}")
                }
            }
            scriptPath("Env/Dev/Applications/Attendance/Target-Group/Jenkinsfile")
        }
    }
}

println "✅ Pipeline job created → terraform/env/dev/applications/Attendance/target-group"


// ------------------ Launch Template ------------------
pipelineJob("terraform/env/dev/applications/Attendance/launch-template") {
    description("Deploys/Destroys Launch Template for Attendance Infra using Terraform.")

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
                    branches("*/${BRANCH_NAME}")
                }
            }
            scriptPath("Env/Dev/Applications/Attendance/Launch-Template/Jenkinsfile")
        }
    }
}

println "✅ Pipeline job created → terraform/env/dev/applications/Attendance/launch-template"


// ------------------ Auto Scaling Group ------------------
pipelineJob("terraform/env/dev/applications/Attendance/auto-scaling-group") {
    description("Deploys/Destroys Auto Scaling Group for Attendance Infra using Terraform.")

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
                    branches("*/${BRANCH_NAME}")
                }
            }
            scriptPath("Env/Dev/Applications/Attendance/Auto-Scaling-Group/Jenkinsfile")
        }
    }
}

println "✅ Pipeline job created → terraform/env/dev/applications/Attendance/auto-scaling-group"


// ------------------ Auto Scaling Policies ------------------
pipelineJob("terraform/env/dev/applications/Attendance/auto-scaling-policies") {
    description("Deploys/Destroys Auto Scaling Policies for Attendance Infra using Terraform.")

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
                    branches("*/${BRANCH_NAME}")
                }
            }
            scriptPath("Env/Dev/Applications/Attendance/Auto-Scaling-Policies/Jenkinsfile")
        }
    }
}

println "✅ Pipeline job created → terraform/env/dev/applications/Attendance/auto-scaling-policies"


// ------------------ Listener Rules ------------------
pipelineJob("terraform/env/dev/applications/Attendance/listener-rules-ALB") {
    description("Deploys/Destroys ALB Listener Rules for Attendance Infra using Terraform.")

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
                    branches("*/${BRANCH_NAME}")
                }
            }
            scriptPath("Env/Dev/Applications/Attendance/Listener-Rules/Jenkinsfile")
        }
    }
}

println "✅ Pipeline job created → terraform/env/dev/applications/Attendance/listener-rules-ALB"

