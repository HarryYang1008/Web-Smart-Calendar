#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/.."

rm -rf out
mkdir -p out/classes dist
javac -encoding UTF-8 -d out/classes $(find src/main/java -name '*.java')
jar cfe dist/swing-google-calendar-1.0.0.jar com.example.calendar.app.CalendarApplication -C out/classes .
zip -j dist/swing-google-calendar-demo.zip dist/swing-google-calendar-1.0.0.jar README.md scripts/run.sh scripts/run.bat

echo "本地打包完成: dist/swing-google-calendar-demo.zip"
