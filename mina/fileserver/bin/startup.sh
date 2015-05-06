#!/bin/sh

#MINA.FileServer

ROOT_PATH=$(cd "$(dirname "$0")/.."; pwd)

LIB_PATH=${ROOT_PATH}/lib

CLASSPATH=${CLASSPATH}:${LIB_PATH}/mina-core-2.0.7.jar:${LIB_PATH}/slf4j-api-1.7.6.jar:${LIB_PATH}/slf4j-nop-1.7.6.jar:${LIB_PATH}/log4j-1.2.14.jar:${LIB_PATH}/mina.fs.jar

echo startup....

java com.seshenghuo.mina.fs.FileServer &
