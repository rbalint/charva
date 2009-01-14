@echo off
rem "This batchfile runs the Swing version of the Charva test program."
rem "It is intended to be run from the '%CHARVA_HOME%'
rem "directory in a DOS command shell."

rem "Last Modified: 2006/10/10 by Rob Pitman <rob@pitman.co.za>"


rem JAVA_HOME must be set to the JDK or JRE installation directory 
rem (for example, C:\jdk1.4 or C:\jre1.4)
rem set JAVA_HOME=C:\j2sdk1.4.2
if not exist %JAVA_HOME% goto noJAVA_HOME

rem Uncomment the following option to test for memory leaks.
rem set TEST_OPTS=%TEST_OPTS% -Xrunhprof:heap=sites

rem Uncomment the following line if you want to debug the application
rem using an IDE such as IntelliJ IDEA (I believe that other IDEs such
rem as NetBeans and JBuilder have the same capability).
rem set TEST_OPTS=%TEST_OPTS% -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005


%JAVA_HOME%\bin\java %TEST_OPTS% -cp .;test/classes;java/lib/commons-logging.jar;java/lib/log4j-1.2.8.jar tutorial.java.Tutorial
goto end

:noJAVA_HOME
echo The JAVA_HOME environment variable is not set!
goto end

:end
