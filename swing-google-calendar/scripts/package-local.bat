@echo off
cd /d %~dp0..
if exist out rmdir /s /q out
if not exist out\classes mkdir out\classes
if not exist dist mkdir dist

for /r src\main\java %%f in (*.java) do (
  set FILES=!FILES! "%%f"
)

REM 使用 powershell 获取文件列表并调用 javac，避免 cmd 变量长度问题
powershell -NoProfile -Command "$files = Get-ChildItem -Recurse src/main/java -Filter *.java | %% { $_.FullName }; javac -encoding UTF-8 -d out/classes $files"
if errorlevel 1 exit /b 1

jar cfe dist/swing-google-calendar-1.0.0.jar com.example.calendar.app.CalendarApplication -C out/classes .
if errorlevel 1 exit /b 1

powershell -NoProfile -Command "Compress-Archive -Force -Path dist/swing-google-calendar-1.0.0.jar,README.md,scripts/run.sh,scripts/run.bat -DestinationPath dist/swing-google-calendar-demo.zip"

echo 本地打包完成: dist\swing-google-calendar-demo.zip
