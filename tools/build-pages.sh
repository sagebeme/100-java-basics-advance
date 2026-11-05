#!/usr/bin/env bash
# Puts together the course website for GitHub Pages, in the folder given (default _site).
# The website is a Next.js static export: it reads the day READMEs while it builds, so the
# published folder is site/out/ and nothing else from the repository (no code, no day folders).
# Pages serves it under /<repo>/, which is passed to Next as its base path. Try it locally with
#   tools/build-pages.sh /tmp/pages/100-java-basics-advance
#   python3 -m http.server -d /tmp/pages 8000     # then open http://localhost:8000/100-java-basics-advance/
set -euo pipefail

cd "$(dirname "$0")/.."
out="$(realpath -m "${1:-_site}")"
# The repository's name: from GitHub Actions, or the name this repo has on GitHub today.
slug="${GITHUB_REPOSITORY:-sagebeme/100-java-basics-advance}"
owner="${slug%%/*}"
repo="${slug#*/}"

(
  cd site
  [ -d node_modules ] || npm ci
  rm -rf out
  # next/font downloads the Google fonts at build time, and that sometimes fails; one retry.
  PAGES_BASE_PATH="/$repo" NEXT_TELEMETRY_DISABLED=1 npm run build ||
    (echo "Retrying after a font download failure"; rm -rf .next; PAGES_BASE_PATH="/$repo" NEXT_TELEMETRY_DISABLED=1 npm run build)
)

rm -rf "$out"
mkdir -p "$(dirname "$out")"
cp -R site/out "$out"
echo "Built $out for https://$owner.github.io/$repo/: $(find "$out/day" -mindepth 1 -maxdepth 1 -type d | wc -l) lessons"
