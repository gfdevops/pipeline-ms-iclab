def execute() {
    def branchName = validate.getBranchName()
    println 'run maven cd'

    stage('gitDiff') {
        git.diff()
    }

    stage('nexusDownload') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            //se descarga repositorio mavenci-repo
            sh 'curl -X GET -u admin:DevOps2020 http://192.81.214.49:8081/repository/mavenci-repo/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar -O'
        }catch (Exception e){
            executeError(e)
        }
    }

    stage('run') {
        try{
            //Ejecutar artefacto descargado.
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            //falta el directorio local del job-nexus
            //sh 'java -jar DevOpsUsach2020-0.0.1.jar &'
        }catch (Exception e){
            executeError(e)
        }
    }
	
    stage('Test') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
        }catch (Exception e){
            executeError(e)
        }
    }
	
    stage('gitMergeMaster') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            git.merge(env.GIT_BRANCH,'main')
        }catch (Exception e){
            executeError(e)
        }
    }
	
	stage('gitMergeDevelop') {
        try {
            env.JENKINS_STAGE = env.STAGE_NAME
            echo env.JENKINS_STAGE
            git.merge(env.GIT_BRANCH,'develop')
        }catch (Exception e){
            executeError(e)
        }
    }

	stage('gitTagMaster') {
        env.JENKINS_STAGE = env.STAGE_NAME
        echo env.JENKINS_STAGE
	}	

}

def executeError(e) {
    //error para output del pipeline mas detallado
    echo "OUTPUT ERROR ${e.toString()}"
    //Error para slack desde post actions en ejecucion.groovy
    def message = env.ERROR_MESSAGE == '' || env.ERROR_MESSAGE == null ? "[Stage ${env.JENKINS_STAGE}] Pipeline aborted due stage failure" : env.ERROR_MESSAGE 
    error message

}

return this
