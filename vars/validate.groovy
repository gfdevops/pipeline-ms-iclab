
//Validar el tipo de rama a ejecutar (feature, develop o release)
//USO : validate.getBranchName()


def getBranchName() {
    //OPCIONAL 
    // def scmVars = checkout scm
    // String branch = scmVars.GIT_BRANCH
    return env.GIT_BRANCH
}

//USO : if (validate.isBranchName('develop')) {}
def isBranchName(String branchName) {

    return branchName==env.GIT_BRANCH


}

//Validar formato de nombre de rama release según patrón 
//release-v{major}-{minor}-{patch}
//USO :  if (validate.validateReleaseNameFormat('origin/release-v1-0-0')) {  echo 'OK' }
//env entrega :
//GIT_BRANCH=origin/release-v1-1-3
//GIT_LOCAL_BRANCH=release-v1-1-3
def validateReleaseNameFormat(String releaseName) {
    return releaseName =~ /^(origin\/release-v[0-9]+)\-([0-9]+)\-([0-9]+)?$/
}