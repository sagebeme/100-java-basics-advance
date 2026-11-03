#!/usr/bin/env bash
# Runs `mvn test` in every day that is a Maven project (Day 54 and Days 60-100), and reports
# which ones fail. The exercises inside earlier days are checked by tools/check-exercises.sh.
#
# Usage, from the course root:
#   tools/test-projects.sh                # every day with a pom.xml
#   tools/test-projects.sh day80 day90    # just these
set -uo pipefail

root=$(cd "$(dirname "$0")/.." && pwd)
cd "$root"
MVN_OFFLINE=${MVN_OFFLINE:-}   # set to -o to run Maven offline

if [ $# -gt 0 ]; then
  days=("$@")
else
  mapfile -t days < <(for pom in day*/pom.xml; do dirname "$pom"; done | sort -V)
fi

failed=()
tested=0
for day in "${days[@]}"; do
  if [ ! -f "$day/pom.xml" ]; then
    echo "SKIP  $day (no pom.xml)"
    continue
  fi
  tested=$((tested + 1))
  log=$(mktemp)
  if (cd "$day" && mvn $MVN_OFFLINE -B -q test >"$log" 2>&1); then
    echo "ok    $day"
  else
    echo "FAIL  $day"
    grep -E "Tests run:.*Fail|ERROR\]|FAIL" "$log" | head -15 | sed 's/^/      /'
    failed+=("$day")
  fi
  rm -f "$log"
done

echo
echo "$tested projects tested, ${#failed[@]} failed${failed[*]:+: ${failed[*]}}."
[ ${#failed[@]} -eq 0 ]
