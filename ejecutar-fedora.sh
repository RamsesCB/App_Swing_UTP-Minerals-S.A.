#!/usr/bin/env bash
set -euo pipefail
DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
export _JAVA_AWT_WM_NONREPARENTING=1
if [[ ! -f "$DIR/dist/UTPMinerals.jar" ]]; then
  echo "Falta dist/UTPMinerals.jar. Compile el proyecto con Clean and Build en NetBeans."
  exit 1
fi
exec java -Dsun.java2d.xrender=false -Dsun.java2d.opengl=false -Dsun.java2d.pmoffscreen=false -jar "$DIR/dist/UTPMinerals.jar"
