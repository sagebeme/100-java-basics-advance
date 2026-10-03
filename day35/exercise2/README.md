# Day 35 - Exercise 2: Masking secrets

Logs get copied into chat messages, screenshots and bug reports. A secret that's printed once is no longer secret. Write two methods in `src/Masking.java`:

**`mask(String secret)`**: show the last 4 characters behind 12 bullets, so you can tell *which* key is in use without revealing it. Secrets of 8 characters or fewer are fully hidden as 8 bullets. `null` or empty gives `"(not set)"`.

| Secret | Masked |
|---|---|
| `atsk_9f2c1e7a55d04b8e` | `••••••••••••4b8e` |
| `abc123` | `••••••••` |

**`redact(String line, List<String> secrets)`**: replace every appearance of every secret in a log line with its masked form.

```java
redact("POST /sms key=atsk_9f2c1e7a55d04b8e to=+2547...", List.of("atsk_9f2c1e7a55d04b8e"))
// "POST /sms key=••••••••••••4b8e to=+2547..."
```

## Files

- `src/Masking.java`: your code goes here.
- `src/MaskingTest.java`: the tests.
- `solution/Masking.java`: one answer.

## 📌 Stuck?

`String.replace(target, replacement)` replaces every occurrence, with no regular expressions involved. Skip `null` or empty secrets in `redact`, or you'll get strange results.
