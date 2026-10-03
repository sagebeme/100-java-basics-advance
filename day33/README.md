# Day 33 - Laundry Forecast: REST APIs and HttpClient

> Saturday morning, a full basin of washing, and a grey sky over Nairobi. Ask a real weather service whether it will dry.

## 📖 The story

Every house in Kenya has had this argument: hang the washing out and risk the afternoon rain, or dry it inside and wait three days. Today your program settles it with data. It calls a free weather **API** over the internet, reads the hourly chance of rain, and gives a verdict:

```
$ java LaundryForecast
Laundry forecast for -1.29, 36.82 (Africa/Nairobi), 8am to 5pm
Highest chance of rain: 25% at 13:00
Hang it out.
```

The service is [Open-Meteo](https://open-meteo.com/). It's free for non-commercial use and needs no account or API key, so you can call it straight away.

## 🎯 Learning Objectives
- Know what a REST API is: a URL you call over HTTP that answers with data, usually JSON
- Build a request URL with a query string, and encode its values safely
- Send a GET request with Java's built-in `HttpClient`, with a timeout
- Check the HTTP status code before trusting the body
- Read JSON with the `org.json` library
- Test HTTP code against a small fake server running on your own machine

## 🧰 Java you'll use

**A request** has a URL; the part after `?` is the **query string**, pairs of `name=value` joined by `&`:

```
https://api.open-meteo.com/v1/forecast?latitude=-1.29&longitude=36.82&hourly=precipitation_probability
```

**`HttpClient`** (built into Java since version 11) sends it:

```java
HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
HttpRequest request = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(10)).GET().build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
response.statusCode(); // 200 means OK
response.body();       // the JSON text
```

**Status codes** say how it went: `200` OK, `400` you sent something wrong, `404` no such thing, `429` too many requests, `500` the server broke. Never parse the body of a failed response as if it were good data.

**`org.json`** (in `lib/`) reads JSON:

```java
JSONObject root = new JSONObject(body);
JSONObject hourly = root.getJSONObject("hourly");
JSONArray times = hourly.getJSONArray("time");
times.getString(0);   // "2026-10-02T00:00"
```

**A fake server for tests.** Java ships a tiny web server, `com.sun.net.httpserver.HttpServer`. A test starts one on a random free port, serves a saved JSON file, and points your client at it. The test never touches the internet, so it's fast and gives the same answer every time.

## 🪜 Steps

1. **Exercise 1: Your first GET** (`exercise1/`). Fetch text, and handle a bad status code.
2. **Exercise 2: Query strings** (`exercise2/`). Build a URL safely from a map of parameters.
3. **Project: Laundry Forecast** (`_start/src/`).
   1. `forecastUri(lat, lon)`: the Open-Meteo URL for hourly `precipitation_probability`, in the `Africa/Nairobi` time zone, for one day.
   2. `WeatherClient.fetch(uri)`: send the request; throw an `ApiException` (with the status code) for anything other than 200.
   3. `parse(json)`: a list of `HourlyRain(LocalDateTime time, int chance)`.
   4. `verdict(hours, fromHour, toHour)`: look only at hours from `fromHour` up to, but not including, `toHour`. Below 30% at the worst hour: `Hang it out.` Below 60%: `Risky: hang it near the door.` Otherwise: `Keep it inside.`
   5. `main`: Nairobi by default, or a latitude and longitude from the arguments. Print the verdict, and print a friendly message instead of a stack trace if the network is down.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day33/_end/src day33/_end/src/*.java
java -cp "day33/_end/src:lib/*" LaundryForecast
java -cp "day33/_end/src:lib/*" LaundryForecast -0.42 36.95      # Nyeri
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day33/_end/src:lib/*" --scan-classpath
```

The tests read `forecast-dry.json` and `forecast-wet.json`, real responses saved from the API, so they pass even offline.

## 🆘 Stuck?

- **`ConnectException` or `HttpTimeoutException`**: no internet, or the service is slow. That's exactly why `main` catches `IOException` and prints a message.
- **`JSONException: JSONObject["hourly"] not found`**: print the body. You probably got an error response, such as `{"error":true,"reason":"..."}`, and parsed it without checking the status.
- **`URISyntaxException` or strange results with a value like `Africa/Nairobi`**: encode query values with `URLEncoder.encode(value, StandardCharsets.UTF_8)`. The `/` must become `%2F`.
- **The wrong hours are counted**: `LocalDateTime.parse("2026-10-02T13:00").getHour()` is `13`. Compare with `>= fromHour && < toHour`.

## ✅ Checklist
- [ ] Built a URL with an encoded query string
- [ ] Sent a GET request with a timeout
- [ ] Checked the status code before reading the body
- [ ] Read nested JSON with `org.json`
- [ ] Tested HTTP code against a local fake server
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 34 puts an API behind a window: a quiz app with buttons, scores and questions fetched from the internet.
