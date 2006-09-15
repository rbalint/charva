@echo off
rem "example-charva.bat runs a Charva example program that is almost
rem "exactly identical to the Swing program run by example-swing.bat."
rem "It is intended to be run from the '%CHARVA_HOME%'
rem "directory in a DOS command shell."

rem "Last Modified: 2006/10/14 by Rob Pitman <rob@pitman.co.za>"

rem "It expects to find Terminal.dll in the directory
rem '%CHARVA_HOME%\c\lib', and the charva.jar file in the directory
rem '%CHARVA_HOME%\java\lib'.
rem "Last Modified: 2006/8/14 by Rob Pitman <rob@pitman.co.za>"

rem Check that we are in the right directory to run this script.
if not exist "c\lib\Terminal.dll" goto noDLL
if not exist "java\dist\lib\charva.jar" goto noJAR
if not exist "test\classes\example\charva\AppFrame.class" goto noClass

rem JAVA_HOME must be set to the JDK or JRE installation directory 
rem (for example, C:\jdk1.4 or C:\jre1.4)
rem set JAVA_HOME=C:\j2sdk1.4.2
if not exist %JAVA_HOME% goto noJAVA_HOME

set TEST_OPTS=%TEST_OPTS% -Dcharva.color=1

%JAVA_HOME%\bin\java %TEST_OPTS% -cp .;test/classes;java/lib/commons-logging.jar;java/lib/log4j-1.2.8.jar;java/dist/lib/charva.jar -Djava.library.path=c\lib example.charva.AppFrame
goto end


:noJAVA_HOME
echo The JAVA_HOME environment variable is not set!
goto end

:noDLL
echo The Terminal.dll library is not available! Have you run "ant makeDLL" yet? Read the installation instructions.
goto end

:noClass
echo Cannot find "test\classes\example\charva\AppFrame.class" - have you run "ant compile-test" yet?

:noJAR
echo The charva.jar file is not available! Have you run "ant dist" yet?
goto end

:end
