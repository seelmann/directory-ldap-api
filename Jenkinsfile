/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
pipeline {
  agent none
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
    timeout(time: 1, unit: 'HOURS')
  }
  triggers {
    cron('@weekly')
    pollSCM('@daily')
  }
  stages {
    stage ('Directory LDAP API') {
      parallel {
        stage ('Build Linux Java 8') {
          agent {
            docker {
              label 'ubuntu'
              image 'maven:3-jdk-8'
              args "-v ${env.JENKINS_HOME}/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2"
            }
          }
          steps {
            sh 'mvn -V clean verify -Duser.home=/var/maven'
          }
          post {
            always {
              junit '**/target/surefire-reports/*.xml'
              deleteDir()
            }
          }
        }
        stage ('Build Linux Java 11') {
          agent {
            docker {
              label 'ubuntu'
              image 'maven:3-jdk-11'
              args "-v ${env.JENKINS_HOME}/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2"
            }
          }
          steps {
            sh 'mvn -V clean verify -Duser.home=/var/maven'
          }
          post {
            always {
              deleteDir()
            }
          }
        }
        stage ('Build Windows Java 8') {
          agent {
            label 'Windows'
          }
          when {
            beforeAgent true
            environment name: 'JENKINS_URL', value: 'https://builds.apache.org/'
          }
          steps {
            bat '''
            set JAVA_HOME=F:\\jenkins\\tools\\java\\latest1.8
            F:\\jenkins\\tools\\maven\\latest3\\bin\\mvn -V clean verify
            '''
          }
          post {
            always {
              deleteDir()
            }
          }
        }
        stage ('Deploy Linux Java 8') {
          agent {
            docker {
              label 'ubuntu'
              image 'maven:3-jdk-8'
              args "-v ${env.JENKINS_HOME}/.m2:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2"
            }
          }
          when {
            beforeAgent true
            branch 'master'
            environment name: 'JENKINS_URL', value: 'https://builds.apache.org/'
          }
          steps {
            sh 'mvn -V clean deploy -Duser.home=/var/maven'
          }
          post {
            always {
              deleteDir()
            }
          }
        }
      }
    }
  }
}

