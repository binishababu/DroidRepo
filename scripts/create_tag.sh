#!/bin/sh

# current Git branch
branch=$(git symbolic-ref HEAD | sed -e 's,.*/\(.*\),\1,')

# v1.0.0, v1.5.2, etc.
versionLabel=v6.10

# establish branch and tag name variables
devBranch=develop
masterBranch=master
releaseBranch=release-$versionLabel

#pull from both master and develop
git checkout master
git reset --hard
git clean -df
git fetch
git pull
#Switch to develop
git checkout develop
git fetch
git pull
# create the release branch from the -develop branch
# git checkout -b $releaseBranch $devBranch
git flow release start $versionLabel $devBranch
#Publish the release branch
git flow release publish $versionLabel


# Finish Release and create tag for new version
git tag $versionLabel

git flow release finish -m "Tagging" $versionLabel
# merge release branch with the new version number back into develop
git checkout $masterBranch

git checkout $devBranch
git merge --no-ff $releaseBranch


# remove release branch
git branch -d $releaseBranch
