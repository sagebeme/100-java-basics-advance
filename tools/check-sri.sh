#!/usr/bin/env bash
# Fails if any <script> or <link> in a day's HTML loads from a CDN without an integrity hash.
# A tag may span several lines, so each file is read whole.
set -euo pipefail
cd "$(dirname "$0")/.."
missing=0
tmp=$(mktemp)
while IFS= read -r -d '' f; do
  perl -0777 -ne '
    while (/<(script|link)\b[^>]*?(?:src|href)="(https:\/\/(?:cdn\.jsdelivr\.net|unpkg\.com|cdnjs\.cloudflare\.com)[^"]*)"[^>]*>/gs) {
      my ($tag, $url) = ($&, $2);
      print "$ARGV: $url\n" unless $tag =~ /\bintegrity="sha(256|384|512)-/;
    }' "$f"
done < <(find . -path ./site -prune -o -path '*/node_modules' -prune -o -path '*/target' -prune -o -name '*.html' -print0) > "$tmp" || true
if [ -s "$tmp" ]; then
  echo "CDN files loaded without an integrity hash:"; cat "$tmp"; missing=1
else
  echo "Every CDN script and stylesheet has an integrity hash."
fi
rm -f "$tmp"
exit $missing
