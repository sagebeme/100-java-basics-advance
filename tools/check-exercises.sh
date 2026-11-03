#!/usr/bin/env bash
# Checks that every exercise is honest:
#   - the solution (solution/ copied over the starter) passes its tests, and
#   - the untouched starter compiles but fails its tests.
#
# Usage, from the course root:
#   tools/check-exercises.sh                      # every exercise
#   tools/check-exercises.sh day29/exercise2 ...  # just these
#
# Plain exercises are compiled with javac against lib/ (JUnit). Exercises with a pom.xml run
# `mvn test`. A few Days 18 and 27 exercises use JavaFX: their jars are downloaded once from
# Maven Central into .cache/javafx (ignored by git), or set JAVAFX_CP to your own copies.
set -uo pipefail

root=$(cd "$(dirname "$0")/.." && pwd)
cd "$root"
LIB="$root/lib"
FX_VERSION=21.0.7
MVN_OFFLINE=${MVN_OFFLINE:-}   # set to -o to run Maven offline

javafx_cp() {
  if [ -n "${JAVAFX_CP:-}" ]; then echo "$JAVAFX_CP"; return; fi
  local dir="$root/.cache/javafx" cp="" m
  mkdir -p "$dir"
  for m in base controls graphics; do
    local jar="$dir/javafx-$m-$FX_VERSION-linux.jar"
    if [ ! -s "$jar" ]; then
      curl -fsSL -o "$jar" \
        "https://repo1.maven.org/maven2/org/openjfx/javafx-$m/$FX_VERSION/javafx-$m-$FX_VERSION-linux.jar" \
        || { echo "could not download javafx-$m" >&2; exit 2; }
    fi
    cp="$cp${cp:+:}$jar"
  done
  echo "$cp"
}

if [ $# -gt 0 ]; then
  exercises=("$@")
else
  mapfile -t exercises < <(find day* -maxdepth 1 -type d -name 'exercise*' | sort -V)
fi

FX=""
if grep -rlq "import javafx" --include='*.java' "${exercises[@]}" 2>/dev/null; then FX=$(javafx_cp); fi

work=$(mktemp -d)
trap 'rm -rf "$work"' EXIT
failures=0 checked=0

# Compiles and runs the tests in $1. Exit code 3 means it didn't compile.
run_javac() {
  (cd "$1" && javac -nowarn --release 17 -cp "$LIB/*${FX:+:$FX}" -d . ./*.java >"$work/compile.log" 2>&1) || return 3
  (cd "$1" && timeout 300 java -cp ".:$LIB/*${FX:+:$FX}" org.junit.platform.console.ConsoleLauncher execute \
     --scan-classpath --disable-banner --details=summary --fail-if-no-tests >"$work/test.log" 2>&1)
}

# Runs `mvn test` in $1. Exit code 3 means it didn't compile.
run_maven() {
  (cd "$1" && mvn $MVN_OFFLINE -B -q test >"$work/test.log" 2>&1)
  local rc=$?
  grep -q 'COMPILATION ERROR' "$work/test.log" && return 3
  return $rc
}

report() { # report <exercise> <what> <ok?> <message>
  if [ "$3" = ok ]; then echo "ok    $1 $2"; else echo "FAIL  $1 $2: $4"; failures=$((failures + 1)); fi
}

for ex in "${exercises[@]}"; do
  ex=${ex%/}
  [ -d "$ex/solution" ] || { report "$ex" "" fail "no solution/ folder"; continue; }
  checked=$((checked + 1))
  if [ -f "$ex/pom.xml" ]; then
    runner=run_maven
    copy() { (cd "$ex" && tar --exclude=target --exclude=solution -cf - .) | (cd "$1" && tar -xf -); }
    overlay() { cp -r "$ex/solution/." "$1/"; }
  else
    runner=run_javac
    copy() { cp -r "$ex/src/." "$1/"; }
    overlay() { cp -r "$ex/solution/." "$1/"; }
  fi

  rm -rf "$work/solution" "$work/starter"; mkdir -p "$work/solution" "$work/starter"
  copy "$work/solution"; overlay "$work/solution"; copy "$work/starter"

  $runner "$work/solution"; rc=$?
  if [ $rc -eq 0 ]; then report "$ex" "solution passes" ok
  elif [ $rc -eq 3 ]; then report "$ex" "solution" fail "does not compile"; head -20 "$work/compile.log" "$work/test.log" 2>/dev/null
  else report "$ex" "solution" fail "tests fail"; grep -E '✘|expected|ERROR\]' "$work/test.log" | head -15
  fi

  $runner "$work/starter"; rc=$?
  if [ $rc -eq 3 ]; then report "$ex" "starter" fail "does not compile"; head -20 "$work/compile.log" "$work/test.log" 2>/dev/null
  elif [ $rc -eq 0 ]; then report "$ex" "starter" fail "passes before any work is done"
  else report "$ex" "starter fails, as it should" ok
  fi
done

echo
echo "$checked exercises checked, $failures problems."
[ "$failures" -eq 0 ] && [ "$checked" -gt 0 ]
