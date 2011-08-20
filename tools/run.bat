@ECHO ON
rem set JAVA_HOME=C:\SDKs\jdk1.6.0

rem %~dp0 is the expanded pathname of the current script under NT
set LOCAL_TOOLS_HOME=
if "%OS%"=="Windows_NT" set LOCAL_TOOLS_HOME=%~dp0

set DEFAULT_PATH=%PATH%
set DEFAULT_CLASSPATH=%CALCLASSPATH%

set BINDIR=%LOCAL_TOOLS_HOME%\
set LIBDIR=%LOCAL_TOOLS_HOME%lib
set LOCALCLASSPATH=%LIBDIR%\scribe-1.2.0.jar
set LOCALCLASSPATH=%LOCALCLASSPATH%;%LIBDIR%\commons-codec-1.4.jar
set LOCALCLASSPATH=%LOCALCLASSPATH%;%LIBDIR%\commons-logging-1.1.jar
set PATH=%JAVA_HOME%\bin;%Path%;%BINDIR%
set CLASSPATH=.;%LOCALCLASSPATH%
@java -cp %CLASSPATH% %1 %2 %3 %4 %5 %6 %7 %8

set PATH=%DEFAULT_PATH%
set CLASSPATH=%DEFAULT_CALCLASSPATH%
@pause
