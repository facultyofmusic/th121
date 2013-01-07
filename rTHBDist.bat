@echo off
echo ===============================================================================
echo               Touhou Project Doujin 12.1 efficiency test package.           
echo ===============================================================================
echo System Memory Information:
MEM
echo .
echo .
java -version
echo .
echo .
echo .>> Executing efficiency test:
echo ===============================================================================
echo LOG:
echo ===============================================================================

java -jar -Djava.library.path=lib THBDist.jar

echo ===============================================================================
echo .
echo Process terminated.
pause