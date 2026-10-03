# Day 4 - Exercise 2: The queue at the station

At 4pm there's a queue at the weighing station. Farmers join at the back, and the clerk serves from the front. An array can't grow, so this is a job for an `ArrayList`.

Write three methods in `src/StationQueue.java`:

| Method | What it does |
|---|---|
| `join(ArrayList<String> queue, String name)` | Adds `name` to the back, unless they're already in the queue. |
| `serveNext(ArrayList<String> queue)` | Removes and returns the person at the front. Returns `"nobody"` if the queue is empty. |
| `positionOf(ArrayList<String> queue, String name)` | Their place in the queue, counting from `1`. Returns `-1` if they're not in it. |

## Files

- `src/StationQueue.java`: your code goes here.
- `src/StationQueueTest.java`: the tests.
- `solution/StationQueue.java`: one answer.

## 📌 Stuck?

- `queue.contains(name)` tells you whether they're already in.
- `queue.isEmpty()` checks for an empty list, and `queue.remove(0)` takes the front person out and gives them back to you.
- `queue.indexOf(name)` counts from `0` and gives `-1` when the name isn't there. People count from `1`.
