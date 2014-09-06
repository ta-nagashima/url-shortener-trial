#!/bin/sh
SVN_USER_PASS_OPTION="--username kmb74974 --password bv92kwkc"

SERVICE_NAME=mobile

SVN_REPO_NAME_SRC=AP_00004193_3_dev_trunk
SVN_REPO_PATH_SRC=https://api.souko.bcos.biglobe.ne.jp/svn/AP_00004193/branches/$SVN_REP...

SVN_REPO_NAME_WAR=AP_00004159_3_dev_trunk
SVN_REPO_PATH_WAR=https://api.souko.bcos.biglobe.ne.jp/svn/AP_00004159/branches/$SVN_REP...
WAR_FILE_NAME=mobile-1.0.war

cd /aspctl/blcusr/build/$SERVICE_NAME/src
svn checkout $SVN_REPO_PATH_SRC $SVN_USER_PASS_OPTION
cd $SVN_REPO_NAME_SRC
unzip workspace-sources.zip
/aspctl/blcusr/tool/gradle-1.12/bin/gradle clean war


cd /aspctl/blcusr/build/$SERVICE_NAME/war/$SVN_REPO_NAME_WAR/
svn update
mv -f /aspctl/blcusr/build/$SERVICE_NAME/src/$SVN_REPO_NAME_SRC/build/libs/$WAR_FILE_NAME /aspctl/blcusr/build/$SERVICE_NAME/war/$SVN_REPO_NAME_WAR/webapps/$WAR_FILE_NAME
svn commit -m "bo" $SVN_USER_PASS_OPTION

rm -rf /aspctl/blcusr/build/$SERVICE_NAME/src/$SVN_REPO_NAME_SRC