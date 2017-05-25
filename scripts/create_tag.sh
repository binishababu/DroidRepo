#!/bin/sh

# current Git branch
branch=$(git symbolic-ref HEAD | sed -e 's,.*/\(.*\),\1,')

# v1.0.0, v1.5.2, etc.
versionLabel=v6.10


# establish branch and tag name variables
devBranch=develop
masterBranch=master
releaseBranch=release-$versionLabel

# create the release branch from the -develop branch
git checkout -b $releaseBranch $devBranch


# merge release branch with the new version number into master
git checkout $masterBranch
git merge --no-ff $releaseBranch



# create tag for new version from -master
git tag $versionLabel


# merge release branch with the new version number back into develop
git checkout $devBranch
git merge --no-ff $releaseBranch


# remove release branch
git branch -d $releaseBranch
