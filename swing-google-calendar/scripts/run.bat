@echo off
cd /d %~dp0..
if not exist dist\swing-google-calendar-1.0.0.jar (
  echo jar not found, please run scripts\package-local.bat first.
  exit /b 1
)
java -jar dist\swing-google-calendar-1.0.0.jar
