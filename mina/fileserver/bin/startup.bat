@title MINA.FileServer
@set BIN_PATH=%~dp0
@set FS_ROOT=%BIN_PATH%..
@set LIB_PATH=%FS_ROOT%\lib
@set CLASSPATH=%CLASSPATH%;%LIB_PATH%\mina-core-2.0.7.jar;%LIB_PATH%\slf4j-api-1.7.6.jar;%LIB_PATH%\slf4j-nop-1.7.6.jar;%LIB_PATH%\log4j-1.2.14.jar;%LIB_PATH%\mina.fs.jar

@echo startup....

@start "MINA.FileServer" /MIN java com.seshenghuo.mina.fs.FileServer