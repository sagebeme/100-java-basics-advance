# Day 51 - Solution

The finished Kasi Check. `mvn test` runs `VerdictTest` (no browser) and `KasiCheckTest` (headless Chrome).

Things worth noticing:

- **The bot ignores the animation.** The dial's number changes all through the test; the final results are in data attributes that only appear when `data-state` is `done`.
- **The median, not the average.** One unusually fast or slow test can't swing the verdict.
- **The complaint carries its evidence**: every test's numbers, the median and the share of the plan.
- **Every run is logged** to `speeds.csv`, so over a week you'd have a real record to show the provider.
