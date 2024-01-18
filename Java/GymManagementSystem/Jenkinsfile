pipeline {

	agent any



		stages {


		stage("Build Modules & Build Docker Images") {

		steps {

		script {

		// def modules = findFiles(glob: '**/pom.xml')

		def modules = ['GymMicroservice', 'GymReportMicroservice','NotificationMicroservice','GymAuthenticationService-1','EurekaServer','GatewayServer','GymCommons']

		for (def module in modules) {

		dir("${module}") {

		echo "Building ${module}..."

		bat "mvn clean install"
		}
		}
		}
		}
		}
		stage("SonarQube Scanner") {
        steps {
         bat "mvn sonar:sonar -Dsonar.projectKey=jenkins-docker -Dsonar.projectName='jenkins-docker' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_23e866143be49e0352f8aeaaa1a2651b418c78d8"
        }
        }
        }
        }


