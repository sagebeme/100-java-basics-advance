# Day 56 - Exercise 1: A juice kiosk's page, from static files

Mama Ngina Drive in Mombasa is lined with kiosks selling madafu (fresh coconut) and juice. One of them wants a simple page that people can open from a QR code on the counter. No Java logic is needed for that: Spring Boot hands out anything in `src/main/resources/static/` exactly as it is.

The stylesheet, script and logo are already in `static/`. Your jobs:

**1. Write `static/index.html`,** the home page. Spring Boot serves a `static/index.html` at `/` without any controller. It needs:

- the viewport meta tag, so it reads well on a phone;
- the logo at `/images/logo.svg`, with alt text;
- the name **Madafu na Juice** and a line about the kiosk;
- a link to `/css/style.css`, the script `/js/app.js`, and a link to the menu board at `/menu/today.txt`.

Notice the URLs: a file at `static/css/style.css` is served at `/css/style.css`. `static` is never part of the URL.

**2. Serve the menu board in `WebConfig`.** The day's menu, `src/main/resources/menu-board/today.txt`, is outside `static/`, because the owner updates it separately. Add a resource handler so `/menu/**` is served from `classpath:/menu-board/`, with a `Cache-Control` header that lets phones keep it for one day.

## Files

- `src/main/resources/static/index.html`: yours to create.
- `src/main/java/com/learning/WebConfig.java`: your code goes here.
- `src/main/resources/static/css`, `js` and `images`, and `menu-board/today.txt`: done.
- `src/test/java/com/learning/StaticResourceTest.java`: the tests.
- `solution/src/...`: one answer for each, at the same paths.

```bash
cd day56/exercise1
mvn test
```

`mvn spring-boot:run` and open http://localhost:8080 to see the page.

## 📌 Stuck?

- `registry.addResourceHandler("/menu/**").addResourceLocations("classpath:/menu-board/")`. The trailing `/` on the location matters.
- Chain `.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))` to add the header. One day is 86400 seconds, which is what the header says.
- Use paths starting with `/` in the HTML, such as `/css/style.css`, so they work from any page.
