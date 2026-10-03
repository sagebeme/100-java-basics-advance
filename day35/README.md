# Day 35 - SMS Alerts: Environment Variables and Configuration

> An API key in your code is a secret you've already given away. Learn where secrets and settings belong instead.

## 📖 The story

A small bakery in Ruaka wants to text customers when their cake is ready. SMS providers such as Africa's Talking give you a **username** and an **API key**. Anyone who has the key can send messages on your account, and you pay for them.

Every year, thousands of keys leak because someone typed them into the code and pushed it to GitHub, where bots scan new commits within minutes. Today you'll build the bakery's alert program so its secrets never touch the code:

```
$ export SMS_API_KEY=atsk_9f2c1e7a55d04b8e
$ java SmsAlerts 0712345678 "Your cake is ready for pickup. Asante!"
Config: sms.username=ruaka-bakery, sms.apiKey=••••••••••••4b8e, sms.sender=RUAKABAKE, sms.sandbox=true
Sending to +254712345678: Your cake is ready for pickup. Asante!
Sent (console gateway, nothing left the computer).
```

## 🎯 Learning Objectives
- Read **environment variables** with `System.getenv`
- Read settings from a **`.properties`** file
- Layer configuration: environment variables override the file, and the file overrides built-in defaults
- Fail early with a clear message when a required setting is missing
- **Mask** secrets before printing or logging them
- Keep secrets out of Git with `.gitignore`, and commit an example file instead
- Normalise Kenyan phone numbers to the international `+254` format

## 🧰 Java you'll use

**Environment variables** are settings that live in your terminal session, not in your code:

```bash
export SMS_API_KEY=atsk_9f2c...       # macOS / Linux
$env:SMS_API_KEY = "atsk_9f2c..."     # Windows PowerShell
```

```java
String key = System.getenv("SMS_API_KEY"); // null if it isn't set
```

**Properties files** hold ordinary settings as `key=value` lines:

```properties
sms.username=ruaka-bakery
sms.sender=RUAKABAKE
```

```java
Properties props = new Properties();
try (Reader in = Files.newBufferedReader(Path.of("config.properties"))) {
    props.load(in);
}
props.getProperty("sms.sender"); // "RUAKABAKE"
```

**Layers.** Each setting is looked up in order, and the first one found wins:

1. the environment variable (`sms.apiKey` → `SMS_APIKEY`, or the explicit name you choose, like `SMS_API_KEY`)
2. the properties file
3. the built-in default

So the file can be committed with safe values, and each computer (or server) supplies its own secrets through the environment.

**Testing without touching the real environment.** `System.getenv()` returns a `Map<String, String>`. If your code takes that map as a parameter, a test can pass `Map.of("SMS_API_KEY", "test-key")` instead of changing real environment variables, which Java doesn't let you do anyway.

## 🪜 Steps

1. **Exercise 1: Reading the environment** (`exercise1/`). Defaults, numbers, and a map you can test with.
2. **Exercise 2: Masking secrets** (`exercise2/`). Show enough of a key to recognise it, and no more.
3. **Project: SMS Alerts** (`_start/src/`).
   1. `AppConfig.load(env, fileProps)`: for each setting, take the environment variable, else the file, else the default.
   2. `require(...)`: throw a `ConfigException` that lists **every** missing required setting at once, not just the first.
   3. `describe()`: all settings for printing, with `sms.apiKey` masked.
   4. `Phone.normalise`: `0712345678`, `712345678`, `254712345678` and `+254 712 345 678` all become `+254712345678`. Anything else is an error.
   5. `SmsAlerts.main`: load the config, validate, normalise the number, and send through the `SmsGateway`. Today's gateway prints instead of sending.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`, and `$env:SMS_API_KEY="..."` to set the variable):

```bash
javac -cp "lib/*" -d day35/_end/src day35/_end/src/*.java
cd day35/_end/src
SMS_API_KEY=atsk_9f2c1e7a55d04b8e java -cp ".:../../../lib/*" SmsAlerts 0712345678 "Your cake is ready"
java -cp ".:../../../lib/*" SmsAlerts 0712345678 "Your cake is ready"    # no key: a clear error
cd ../../..
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day35/_end/src:lib/*" --scan-classpath
```

`config.properties` is committed and has no secrets in it. `config.example.properties` shows every setting, including the secret ones, with placeholder values. The repo's `.gitignore` ignores `config.local.properties` and `.env`, so real secrets in those files can't be committed by accident.

## 🆘 Stuck?

- **The variable is set but `getenv` returns `null`**: environment variables belong to one terminal session. Set it in the same terminal you run Java from, or in your IDE's run configuration.
- **`FileNotFoundException: config.properties`**: relative paths start from the folder you ran `java` in, not from the source file's folder.
- **The phone check is getting long**: remove spaces first, then deal with `+254`, `254` and `0` in that order. What's left must be 9 digits starting with `7` or `1`.
- **I already pushed a key to GitHub**: deleting the commit isn't enough, because it's already been copied. Revoke the key in the provider's dashboard and make a new one.

## ✅ Checklist
- [ ] Read an environment variable, with a default
- [ ] Loaded a `.properties` file
- [ ] Layered environment, file and defaults
- [ ] Reported every missing setting in one error
- [ ] Masked the API key in all output
- [ ] Normalised phone numbers to `+254`
- [ ] The tests pass
- [ ] Committed code to Git, with no secrets in it

## ➡️ Next

Day 36 combines two APIs: Nairobi Securities Exchange share prices and news headlines, and an alert when a price jumps.
