#!/bin/sh

BIN_DIR=`dirname $0`
RESOURCE_DIR=$BIN_DIR/../../../src/test/resources/scenario

# Sleep
if [ $1 = "Sleep" ]; then
	sleep 30
else
	DATA_FILE=$RESOURCE_DIR/$1/$1
	cat $DATA_FILE
fi

