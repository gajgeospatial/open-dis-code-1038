echo Building the srm.jar file ...
if [ ! -d classes ]; then
   mkdir classes;
else
   rm -rf classes/*;
fi

javac -d classes public/*.java internal/*.java

if [ ! -d lib ]; then
   mkdir lib;
fi

cp internal/*.dat classes/SRM/.

jar cf lib/srm.jar -C classes SRM

echo Compiling the sample application CdToCcConv ...

if [ ! -d bin ]; then
   mkdir bin;
fi
javac -classpath "lib/srm.jar" apps/CdToCcConv.java -d bin
