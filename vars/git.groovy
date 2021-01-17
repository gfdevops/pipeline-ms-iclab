def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + env.GIT_BRANCH
}

def merge(branchfrom, branchto) {
    figlet 'git merge'
    def merge_text = 'Merge '+branchfrom+' into '+branchto
    echo merge_text
    sh '''
        git pull origin main
        git checkout '''+branchto+'''; git pull origin  '''+branchto+'''
        git merge '''+branchfrom+'''
        git commit -am '''Merged '''+ branchfrom +''' branch to '''+ branchto +''''
        git push origin ''' + branchto + '''
    '''
}

return this


/*

codigo profe

    git fetch -p
	git checkout '''+ Origin + ''';git pull
	
	git merge '''+ Origin +''' +''' ''' +'''+ Dest +'''
	
	git commit -am '''Merged '''+ Origin +''' branch to '''+ Dest +''''
	
	git push origin ''' + Dest + '''
	
	'''



*/