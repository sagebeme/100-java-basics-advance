# Day 33 - Solution

The finished Laundry Forecast, checked by `LaundryForecastTest.java`.

Things worth noticing:

- **`WeatherClient` only fetches and `LaundryForecast` only decides.** The decision code works on plain `HourlyRain` records, so most tests don't need a network at all.
- **The status check sits in one place.** Any non-200 answer becomes an `ApiException` before anyone tries to read it as weather.
- **`Locale.ROOT` in `String.format`.** On a computer set to, say, German, `%.4f` would print `-1,2864` with a comma, and the API would reject it.
- **`chances.isNull(i)`**: real APIs send `null` for missing values. Assume they will.
- **The fake server** in the test is ten lines of JDK code, and it lets you test the error path, which is hard to trigger against the real service.
