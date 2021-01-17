def diff(){
    figlet 'git diff'
    sh 'git diff origin/main..' + getValidBranchName()
}

//branchfrom = realease -> branchto = master
def merge(branchfrom, branchto) {
    figlet 'git merge'
    def merge_text = 'Merge '+branchfrom+' into '+branchto
    echo merge_text

    sh 'git checkout '+branchfrom 
    sh 'git pull origin '+branchfrom
    sh 'git checkout '+branchto
    sh 'git pull origin '+branchto
    sh 'git merge '+branchfrom
    sh 'git commit -am \" Merge '+branchfrom+' a '+branchto+' \"' + '|| true'
    sh 'git push origin '+branchto 
}

return this
