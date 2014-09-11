
BIN_DIR=`dirname $0`
cd $BIN_DIR/../../../etc/library/local

java -cp h2-1.4.181.jar:h2storedprocedure-2.0.jar:../production/charset-2.0.jar org.h2.tools.Server