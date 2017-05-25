cd /Users/bbabu/DroidRepo
#release = $RELEASE_VERSION
sed -i '' "s/public static final String VERSION =.*/public static final String VERSION = ${1}/" AMPLibraryInfo.java

#cd $HOME/Documents/Projects/amp-android-sdk/scripts/bash-scripts/
pwd
input=${1}
data=(${input//./ })
echo " 0 : ${data[0]}"
echo " 1 : ${data[1]}"
echo " 2 : ${data[2]}"
echo "=====INPUT ${1}"
echo ${#data[@]}
if [ ${#data[@]} -gt 1 ]; then
sprintno=( "${data[1]}.${data[2]}" )
echo "Length is 2 : $sprintno ======"
sed -i '' "s/export SPRINT=.*/export SPRINT=$sprintno/" project.sh
else
sprintno=( "${data[1]}" )
echo $sprintno
sed -i '' "s/export SPRINT=.*/export SPRINT=$sprintno/" project.sh
fi

git checkout develop
git add .
git commit -m "Updating Release VERSION and SPRINT for release build"
git push origin develop
