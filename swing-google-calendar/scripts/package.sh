#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/.."

mvn clean package
cp target/swing-google-calendar-1.0.0.jar dist/
zip -j dist/swing-google-calendar-demo.zip dist/swing-google-calendar-1.0.0.jar README.md

echo "打包完成: dist/swing-google-calendar-demo.zip"
