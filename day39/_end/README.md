# Day 39 - Solution

The finished Part 1, checked by `DealFinderTest.java`.

Things worth noticing:

- **Each rule is a small, separately tested method**: long weekends, the next one, the cheapest fare within a limit, the sort. `main` only wires them together.
- **The test talks to the practice server over real HTTP**, so `FareClient`'s URL building and JSON parsing are covered too.
- **`FARE_API_URL`** chooses the server. With it unset, the program starts its own practice API and stops it when done.
- **The no-deal list explains itself**: "cheapest KES 4,800, your limit KES 4,500" tells you whether to raise your limit or wait.
