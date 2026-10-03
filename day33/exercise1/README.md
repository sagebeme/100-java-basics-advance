# Day 33 - Exercise 1: Your first GET

Write `fetchText(URI uri)` in `src/FirstGet.java`. It sends a GET request and returns the body, but only for status `200`. For any other status, throw an `IOException` whose message includes the status, like `"HTTP 404"`.

Then, in `main`, fetch `https://api.open-meteo.com/v1/forecast?latitude=-1.29&longitude=36.82&current=temperature_2m` and print it.

The tests start a fake server on your own machine, so they work offline.

## Files

- `src/FirstGet.java`: your code goes here.
- `src/FirstGetTest.java`: the tests.
- `solution/FirstGet.java`: one answer.

## 📌 Stuck?

`HttpClient.newHttpClient()`, `HttpRequest.newBuilder(uri).GET().build()`, then `client.send(request, HttpResponse.BodyHandlers.ofString())`. The response has `statusCode()` and `body()`.
