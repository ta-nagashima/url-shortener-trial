#!/bin/sh
RELEASE_DIR=~/release
JENKINS_BUILD_DIR=~/jobs/mobile/workspace
SVN_REPO_NAME=AP_00004193_3_dev_trunk
SVN_REPO_PATH=https://api.souko.bcos.biglobe.ne.jp/svn/AP_00004193/branches/$SVN_REPO_NAME/
OFFLINE_BUILD_JARS_PATH=$RELEASE_DIR/workspace/build/distributions/workspace-sources.zip
SVN_USER_PASS_OPTION="--username kmb74974 --password bv92kwkc"
BUILD_NUMBER=test

# svn checkout
rm -rf $RELEASE_DIR/
mkdir -p $RELEASE_DIR/
cd $RELEASE_DIR/
svn checkout $SVN_USER_PASS_OPTION $SVN_REPO_PATH

# jenkinsがbuildしたgitブランチをコピー
cp -rf $JENKINS_BUILD_DIR $RELEASE_DIR

# offlinebuild用に必要なモジュールを作成
cd $RELEASE_DIR/workspace/
./gradlew releaseSource

# svnのディレクトリにコピー
cp -f $OFFLINE_BUILD_JARS_PATH $RELEASE_DIR/$SVN_REPO_NAME/

# svnにcommit
cd $RELEASE_DIR/$SVN_REPO_NAME/
svn add * --force
svn commit -m $BUILD_NUMBER $SVN_USER_PASS_OPTION

#rm -rf $RELEASE_DIR/

