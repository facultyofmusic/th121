@echo off
echo ===============================================================================
echo                            Touhou Project Doujin 12.1           
echo ===============================================================================
echo System Memory Information:
MEM
echo .
echo .
java -version
echo .
echo .
echo ===============================================================================
echo LOG:
echo ===============================================================================

java -jar -Djava.library.path=lib THDist.jar

echo ===============================================================================
echo .
echo Process terminated.
pause