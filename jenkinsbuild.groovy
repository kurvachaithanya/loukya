//declarative pipeline
pipeline{
    agent any
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'type branch name here')
    }
    stages{
        stage("clone code"){
            steps{
                println "cloning the code from git hub"
            }
        }
        stage("build"){
            steps{
                println "build code"
                sh "mvn clean package"
                sh "ls -l"
            }
        }
        stage("save artifacts in s3"){
            steps{
                println " upload artifacts to s3"
                sh "echo $BUILD_NUM"
                sh "aws s3 cp target/hello-${BUILD_NUM}.war s3://chaituart/"
            }
        }

    }
}