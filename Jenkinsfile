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

    }
}
