pipeline {
    agent any

    stages {
        stage('Prepare') {
            steps {
                script {
                    // env.BRANCH_NAME = env.GIT_BRANCH.split('/').last()
                    env.BRANCH_NAME = env.GIT_BRANCH.split('/').last()
                    try {
                        sh "echo ${BRANCH_NAME}"
                        if (env.BRANCH_NAME == 'chhong' || env.BRANCH_NAME == 'dev') {
                            sh 'java --version'
                            git branch: 'chhong',
                                url: 'https://github.com/Project-DMZ/dmz-back'
                        } else if (BRANCH_NAME == 'master') {
                            git branch: 'main',
                                url: 'https://github.com/Project-DMZ/dmz-back/'
                        }
                    } catch (Exception e) {
                        sh "echo 'catch'"
                    } finally {
                        sh "echo 'finally'"
                    }
                }
            }
        }


        stage('add credentials') {
            steps {
                script {
                 def targetFilePath = "${env.WORKSPACE}/src/main/resources/application-test.yml"
                 withCredentials([file(credentialsId: 'application-test', variable: 'SECRET_FILE_PATH')]) {
                     sh "rm -f ${targetFilePath} && cat ${SECRET_FILE_PATH} > ${targetFilePath}"
                 }

                 targetFilePath = "${env.WORKSPACE}/src/main/resources/application-secret.yml"
                 withCredentials([file(credentialsId: 'application-secret', variable: 'SECRET_FILE_PATH')]) {
                     sh "rm -f ${targetFilePath} && cat ${SECRET_FILE_PATH} > ${targetFilePath}"
                 }
                }
            }
        }

        stage('Test') {
            steps {
                echo '=== Test ==='

                }
            }

        stage('Build') {
            steps {
                echo '=== Build ==='
                sh '''
                    chmod +x gradlew
                    ./gradlew clean build
                '''
            }
        }

        stage('Deploy') {
            steps {
                echo '=== Deploy ==='
                script {
                    sshPublisher(publishers: [
                        sshPublisherDesc(configName: 'dev-dmz', transfers: [
                            sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sh init.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'deploy/', remoteDirectorySDF: false, removePrefix: 'build/libs', sourceFiles: 'build/libs/*.jar')
                        ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
                    ])
                }
            }
        }
    }
}
