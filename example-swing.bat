@echo off
rem "example-swing.bat runs a Swing example program that is almost
rem "exactly identical to the Charva program run by example-charva.bat."
rem "It is intended to be run from the '%CHARVA_HOME%'
rem "directory in a DOS command shell."

rem "Last Modified: 2006/10/14 by Rob Pitman <rob@pitman.co.za>"

rem JAVA_HOME must be set to the JDK or JRE installation directory 
rem (for example, C:\jdk1.4 or C:\jre1.4)
rem set JAVA_HOME=C:\j2sdk1.4.2
if not exist %JAVA_HOME% goto noJAVA_HOME

if not exist "test\classes\example\java\AppFrame.class" goto noClass

%JAVA_HOME%\bin\java %TEST_OPTS% -cp .;test/classes;java/lib/commons-logging.jar;java/lib/log4j-1.2.8.jar  example.java.AppFrame
goto end

:noJAVA_HOME
echo The JAVA_HOME environment variable is not set!
goto end

:noClass
echo Cannot find "test\classes\example\charva\AppFrame.class" - have you run "ant compile-test" yet?

:end
