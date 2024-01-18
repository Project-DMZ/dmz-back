// pipeline {
//     agent any
//
//     stages {
//         stage('Prepare') {
//             steps {
//                 echo '=== Prepare ==='
//                 // git 'https://github.com/Project-DMZ/dmz-back/'
//                 git branch: 'dev', url: 'https://github.com/Project-DMZ/dmz-back/'
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 echo '=== Build ==='
//                 sh '''
//                     chmod +x gradlew
//                     ./gradlew clean build
//                 '''
//             }
//         }
//
//         stage('Deploy') {
//             steps {
//                 echo '=== Deploy ==='
//                 sshPublisher(publishers: [
//                     sshPublisherDesc(configName: 'DEV-DMZ-WAS-KEY', transfers: [
//                         sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sh init.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'deploy/', remoteDirectorySDF: false, removePrefix: 'build/libs', sourceFiles: 'build/libs/*.jar')
//                         ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
//                     ])
//             }
//         }
//     }
// }

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
                            git branch: 'dev',
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

                     withCredentials([file(credentialsId: 'application-test', variable: 'SECRET_FILE_PATH')]) {
                         def targetFilePath = "${env.WORKSPACE}/src/main/resources/application-test.yml"
                         sh "echo 'property: \${SECRET_FILE_PATH}' > ${targetFilePath}"
//                          sh "cat ${SECRET_FILE_PATH}"
//                          echo "File Path: ${targetFilePath}"
                     }

                     withCredentials([file(credentialsId: 'application-secret', variable: 'SECRET_FILE_PATH')]) {
                          def targetFilePath = "${env.WORKSPACE}/src/main/resources/application-secret.yml"
                          sh "echo 'property: \${SECRET_FILE_PATH}' > ${targetFilePath}"
//                           sh "cat ${SECRET_FILE_PATH}"
//                           echo "File Path: ${targetFilePath}"
                      }
                         echo "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"
                         def yamlFiles = sh(script: "ls ${env.WORKSPACE}/src/main/resources/*.yml", returnStdout: true).trim()
                         yamlFiles.split("\n").each { yamlFile ->
                             sh "echo 'Reading contents of ${yamlFile}:'"
                             sh "cat ${yamlFile}"
                         }
                         echo "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"
                }
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
                        sshPublisherDesc(configName: 'jenkins-pem', transfers: [
                            sshTransfer(cleanRemote: false, excludes: '', execCommand: 'sh init.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'deploy/', remoteDirectorySDF: false, removePrefix: 'build/libs', sourceFiles: 'build/libs/*.jar')
                        ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
                    ])
                }
            }
        }
    }
}
