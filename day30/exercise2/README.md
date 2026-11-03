# Day 30 - Exercise 2: Reading and writing JSON by hand

Today's project saves the password manager's data as JSON. Usually a library such as Gson or Jackson does this, and in real work you should use one. Writing a small reader yourself, once, shows what those libraries do, and why splitting the text on commas is not enough:

```json
{"site":"kplc.co.ke","note":"Shop 4, \"Eldoret\": upstairs"}
```

Split that on `,` and on `:` and the note falls apart. You have to walk the text one character at a time and know whether you're inside a string.

Write `src/SimpleJson.java`, for flat objects whose values are all strings. `src/JsonException.java` is done.

| Method | What it does |
|---|---|
| `write(map)` | `{"site":"kplc.co.ke","user":"cafe-eldoret"}`: no spaces, keys in the map's order, `{}` for an empty map. |
| `quote(text)` | Wraps text in quotes and escapes four characters: `"` → `\"`, `\` → `\\`, new line → `\n`, tab → `\t`. |
| `read(json)` | The other way, into a `LinkedHashMap` so the keys keep their order. Spaces and new lines between the parts are allowed. Inside strings, it understands `\"`, `\\`, `\/`, `\n` and `\t`. |

Anything else is broken JSON, and `read` throws `new JsonException(what, position)`, where `position` is the index in the text where it noticed. For `{"site" "kplc"}` the message is `Expected ':' at position 8`. Broken includes a missing `:` or `}`, a string with no closing quote, a value without quotes, a comma before `}`, an unknown escape like `\q`, and anything left over after the `}`.

## A plan for `read`

Keep the text and an index `pos` in a small helper object. Then write little methods that each read one thing and move `pos` past it:

- `skipSpaces()`: moves past any whitespace.
- `expect(c)`: the next character must be `c`, or throw.
- `string()`: `expect('"')`, then collect characters until the closing `"`, turning `\n` and friends into the real character.
- `object()`: `{`, then, unless the next thing is `}`, a string, `:`, a string, and either `,` (go round again) or `}` (stop).

Each method is a few lines. This is how most parsers work, including the one inside `javac`.

## Files

- `src/SimpleJson.java`: your code goes here.
- `src/JsonException.java`: done.
- `src/SimpleJsonTest.java`: the tests.
- `solution/SimpleJson.java`: one answer.

```bash
javac -cp "lib/*" -d day30/exercise2/src day30/exercise2/src/*.java
java -cp "day30/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Escapes stack up in Java source: `"\\\""` is the two characters `\"`, a backslash and a quote. When it gets confusing, print the string and look.
- `JsonException` is unchecked (`extends RuntimeException`), so `read` doesn't need `throws`. Broken input here is a bug in whoever made the file, not a normal event like Exercise 1's empty float.
- Before every `text.charAt(pos)`, check `pos < text.length()`, or broken input throws `StringIndexOutOfBoundsException` instead of your `JsonException`.
