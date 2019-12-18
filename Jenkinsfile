#!groovy
pipeline {
    agent any

    post {
        success {
            mail(to: config.mailNotification + ",pacolopezbaena@gmail.com",
                    subject: "Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) ejecutado",
                    body: "El Job ha finalizado: ${env.BUILD_URL}. ")
        }
        failure {
            mail(to: config.mailNotification + ",pacolopezbaena@gmail.com",
                    subject: "Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) ejecutado con errores",
                    body: "El Job ha finalizado: ${env.BUILD_URL}. ")
        }
        unstable {
            mail(to: config.mailNotification + ",pacolopezbaena@gmail.com",
                    subject: "Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) inestable",
                    body: "El Job ha finalizado: ${env.BUILD_URL}. ")
        }
    }
    stages {

        stage('Build') {
            steps {

                withMaven(
                        jdk: "1.8",
                        maven: "M3",
                        options: [
                                artifactsPublisher(disabled: build.skipArtifacts),
                                findbugsPublisher(disabled: true),
                                openTasksPublisher(disabled: true)]) {

                    bat('mvn -f pom.xml -DskipTests -Dcobertura.skip -Dmaven.javadoc.skip=true clean install' )
                }


            }
        }
<<<<<<< HEAD
        stage('Testing') {
            steps {
                try {
                    withMaven(
                            jdk: "1.8",
                            maven: "M3",
                            options: [
                                    artifactsPublisher(disabled: true),
                                    findbugsPublisher(disabled: true),
                                    openTasksPublisher(disabled: true)]) {

                        bat('mvn -f pom.xml org.jacoco:jacoco-maven-plugin:prepare-agent  test -Dmaven.javadoc.skip=true -fae -Dmaven.test.failure.ignore=false')
                    }
                } catch (err) {
                    echo 'Testing failed!'
                    def sw = new StringWriter()
                    def pw = new PrintWriter(sw)
                    err.printStackTrace(pw)
                    echo sw.toString()
                    currentBuild.result = 'UNSTABLE'
                }
            }

        }
=======

>>>>>>> release/1.0.3
    }
}
