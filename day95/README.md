# Day 95 - Portfolio Project: API Integration Website

> Most real apps are glue between other people's services. Today's dashboard calls three free public APIs and shows the results on one page, and it stays up when one of them goes down.

## 📖 The story

Kip runs the screens in the waiting area of a co-working space in Westlands. Members asked for something more interesting than the Wi-Fi password, so he wants a dashboard: the weather in Nairobi, today's Bitcoin and Ethereum prices (half the members trade), and, because it's fun, where the International Space Station is right now. It should refresh by itself every 30 seconds.

| Section | API | Key needed? |
|---|---|---|
| Nairobi weather | Open-Meteo | no |
| Crypto prices (USD) | CoinGecko | no, but rate-limited |
| ISS location | Open Notify | no |

Kip's real worry: if CoinGecko stops answering, the whole screen mustn't go blank. Each section has to fail on its own.

## 🎯 Learning Objectives
- Call JSON APIs with Spring's `RestClient` and read the answers with Jackson's `JsonNode`
- Build URLs with template variables, so values are encoded properly
- Check every field of someone else's JSON before using it
- Turn every kind of failure (network, HTTP error, odd response) into one `ApiException`
- Keep one failing API from breaking the whole page
- Test API clients offline with `MockRestServiceServer`, and the page with mocked services

## 🧰 What you'll use

**Calling an API:**

```java
JsonNode root = restClient.get()
        .uri("https://api.coingecko.com/api/v3/simple/price?ids={ids}&vs_currencies=usd", "bitcoin,ethereum")
        .retrieve()                  // 4xx and 5xx answers throw a RestClientException
        .body(JsonNode.class);
```

`{ids}` is filled in and encoded for you (the comma becomes `%2C`), which is safer than gluing strings into a URL.

**Don't trust the response.** A field can be missing, `null`, or a string where you expected a number. `root.get("x").asDouble()` on a missing field throws `NullPointerException`, which isn't an `ApiException`, so the controller wouldn't catch it and the whole page would fail. So each service checks first:

```java
JsonNode current = root == null ? null : root.get("current_weather");
if (current == null || !current.path("temperature").isNumber()) {
    throw new ApiException("Weather API response missing the current temperature", null);
}
```

**Failing one section at a time.** The controller wraps each call in its own `try`/`catch` and puts either the data or an error message into the model, so the page always renders.

**Testing without the internet.** Each service has a second constructor that takes a `RestClient.Builder`. A test binds that builder to a `MockRestServiceServer`, which answers with whatever the test chooses: a normal response, a missing field, a 503 or a 429:

```java
RestClient.Builder builder = RestClient.builder();
MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
server.expect(requestTo(startsWith("https://api.open-meteo.com/")))
      .andRespond(withSuccess("{\"current_weather\":{\"temperature\":23.4,\"windspeed\":11.2}}", APPLICATION_JSON));
WeatherInfo weather = new WeatherService(builder).fetchCurrentWeather();
```

One test per service still calls the real API, and skips itself when the network isn't there.

## 🪜 Steps

**Your brief** (the `day95/` folder is one finished version: build your own first, then compare).

1. `WeatherInfo`, `CryptoPrice` and `IssLocation` records, and `ApiException`.
2. `WeatherService`, `CryptoService` and `IssService`, each checking its response.
3. `DashboardController` and `dashboard.html`: three cards, an error in a card if its API fails, and `<meta http-equiv="refresh" content="30">`.
4. Tests: each service with fixed responses (good, incomplete, an HTTP error) and once for real; the page with all services working, and with one failing.
5. Make it yours: cache each answer for a minute so the page doesn't hit the APIs on every refresh (CoinGecko will thank you), show KES as well as USD, or put the ISS on a Leaflet map (Day 87).

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day95
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080> and leave it open: it refreshes every 30 seconds.

## 🆘 Stuck?

- **The whole page is an error**: something other than `ApiException` escaped a service, usually a `NullPointerException` from a missing field. Check fields before reading them.
- **`429 Too Many Requests` from CoinGecko**: the free API limits how often you can call it. Cache results, or refresh less often.
- **The ISS section fails on a secure site**: Open Notify only serves plain `http`. Browsers block http requests from https pages, but this app calls it from the server, so it works.
- **`MockRestServiceServer` says the request wasn't expected**: compare the URLs exactly, including encoding (`%2C` for a comma).

## ✅ Checklist
- [ ] Three APIs on one page
- [ ] Every response checked, and every failure an `ApiException`
- [ ] One API down leaves the others showing
- [ ] Tests that run offline, plus one real call per API
- [ ] Added caching, KES prices or a map
- [ ] Committed code to Git

## ➡️ Next

Day 96 builds a small online shop: a catalogue, a cart, checkout, and a simulated card payment.
