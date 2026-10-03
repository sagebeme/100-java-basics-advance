# Day 11 - Solution

`src/SeasonSimulator.java` is one finished version, checked by `src/SeasonSimulatorTest.java`.

Things worth noticing:

- **The only random thing is `randomRainfall`.** Every other method takes the rainfall as a plain number, so the tests can check a dry year, a good year and a wet year without any luck involved.
- **One method per rule.** When the rules change (the cooperative raises the levy, or a new grade appears), you change one method, and its test tells you whether you got it right.
- **`seasonReport` only assembles.** It calls the other methods and formats their answers; it doesn't work anything out itself.
- **Earlier days, reused.** The drying loop is Day 5's, the 6-to-1 ratio is Day 2's and the levy is Day 8's. A capstone is mostly old ideas put together.
