def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + env.GIT_BRANCH
}

//branchfrom = realease -> branchto = master
def merge(branchfrom, branchto) {
    figlet 'git merge'
    def merge_text = 'Merge '+branchfrom+' into '+branchto
    echo merge_text

    sh 'git pull --all origin'
    sh 'git checkout -b '+branchfrom+' remotes/origin/'+branchfrom
    sh 'git pull origin '+branchfrom
    sh 'git checkout '+branchto
    sh 'git pull origin '+branchto
    sh 'git merge '+branchfrom
    sh 'git commit -am \" Merge '+branchfrom+' a '+branchto+' \"'
    sh 'git push origin '+branchto
}

return this
