def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + env.GIT_BRANCH
}

def merge(branchfrom, branchto) {
    figlet 'git merge'
    def merge_text = 'Merge '+branchfrom+' into '+branchto

    sh '''
        git fetch -p
        git checkout '''+ branchfrom + ''';git pull
        
        git merge '''+ branchfrom +''' +''' ''' +'''+ branchto +'''
        
        git commit -am '''Merged '''+ branchfrom +''' branch to '''+ branchto +''''
        
        git push origin ''' + branchto + '''
        
        '''
}
