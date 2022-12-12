echo off
@set JAVA_PATH=c:\j2sdk1.6.0_14
@set path=%path%;%JAVA_PATH%\bin
echo Building the srm.jar file ...
if not exist classes (
   md classes
) else (
   del /q classes\*
)

javac -d classes public\*.java internal\*.java

if not exist lib (
   md lib
)

copy internal\*.dat classes\SRM

jar cf lib\srm.jar -C classes SRM

echo Compiling the sample application ...
if not exist bin (
   md bin
)
javac -classpath "lib\srm.jar" apps\CdToCcConv.java -d bin
