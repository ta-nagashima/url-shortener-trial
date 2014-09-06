
BIN_DIR=`dirname $0`
cd $BIN_DIR/../../configs/development/library

java -cp h2-1.4.177.jar:h2storedprocedure-2.0.jar:../../production/library/charset-2.0.jar org.h2.tools.Server