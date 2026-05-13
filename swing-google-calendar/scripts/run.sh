#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/.."

if [ ! -f dist/swing-google-calendar-1.0.0.jar ]; then
  echo "未发现 dist/swing-google-calendar-1.0.0.jar，先执行: bash scripts/package-local.sh"
  exit 1
fi

java -jar dist/swing-google-calendar-1.0.0.jar
