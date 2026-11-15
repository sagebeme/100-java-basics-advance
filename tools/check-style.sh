#!/usr/bin/env bash
# Runs Checkstyle with the short rule list in tools/checkstyle.xml over the course's Java files.
# The Checkstyle jar is downloaded once into .cache/ (ignored by git) and checked against its
# SHA-256 before use.
#
# Usage, from the course root:
#   tools/check-style.sh                 # every Java file Git tracks
#   tools/check-style.sh day29 day80     # just these folders or files
set -euo pipefail
cd "$(dirname "$0")/.."

VERSION=14.3.0
SHA256=754e218ab1fcabb1e1c5f8530e9d3aa37636806c22987bb279dd907a6ee749b2
jar=.cache/checkstyle/checkstyle-$VERSION-all.jar

if [ ! -s "$jar" ]; then
  mkdir -p "$(dirname "$jar")"
  curl -fsSL -o "$jar.part" \
    "https://github.com/checkstyle/checkstyle/releases/download/checkstyle-$VERSION/checkstyle-$VERSION-all.jar" \
    || { echo "could not download Checkstyle $VERSION" >&2; exit 2; }
  mv "$jar.part" "$jar"
fi
if ! echo "$SHA256  $jar" | sha256sum -c --quiet -; then
  echo "$jar does not match the pinned SHA-256; delete it and run again" >&2
  exit 2
fi

if [ $# -gt 0 ]; then
  mapfile -t files < <(git ls-files -- "$@" | grep '\.java$' || true)
else
  mapfile -t files < <(git ls-files -- '*.java')
fi
[ ${#files[@]} -gt 0 ] || { echo "no Java files to check" >&2; exit 2; }

java -jar "$jar" -c tools/checkstyle.xml "${files[@]}"
