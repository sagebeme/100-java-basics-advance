# Day 44 - Solution

The finished Mitumba Price Watch, checked by `PriceWatchTest.java`.

Things worth noticing:

- **The crawler stops for three reasons**: there's no `rel="next"` link, `robots.txt` disallows the next path, or it has fetched `maxPages` pages. A crawler with no limit is a crawler that can run forever.
- **`putIfAbsent` by SKU** removes listings that appear on two pages, which happens on real sites when new items push old ones along while you're reading.
- **The delay is a constructor parameter**: one second between requests in `main`, zero in the tests so they stay fast.
- **`PracticeShop` counts its requests**, so a test can prove the crawler never fetches the same page twice.
- **Cleaning is separate from scraping.** `Cleaning.priceKes` knows nothing about HTML, so it's easy to test with dozens of odd prices.
