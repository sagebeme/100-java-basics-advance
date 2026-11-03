# Day 77 - Searching, Sorting and the Shortest Route

> Sorting a list, finding something in it, and finding a route through a map are the three questions behind half of all interview problems. Today you write the classic answers and measure why some are faster than others.

## 📖 The story

Achieng is building an app for a matatu SACCO. It needs to do three things:

1. **Sort** the day's fares, so the treasurer can see the smallest and biggest at a glance.
2. **Find** one fare in a sorted list of thousands, without reading every one.
3. **Route** a passenger between two stages with the fewest changes.

For routing she numbers the stages and records which ones have a direct matatu between them: a **graph**. In this small, made-up network, stage 1 connects to 2 and 3, and there are two ways to get from 1 to 5:

```
1 ── 2 ── 4
│         │
3 ─────── 5
```

`1 → 3 → 5` is two rides; `1 → 2 → 4 → 5` is three. A **breadth-first search** finds the two-ride route, because it explores every stage one ride away before any stage two rides away.

```
$ mvn -q compile exec:java
Sorted: [1, 2, 3, 5, 8, 9]
Binary search for 8: index 4
```

## 🎯 Learning Objectives
- Write bubble sort and quick sort, and compare how their work grows
- Write linear search and binary search, and know when binary search is allowed
- Store a graph as an adjacency list: a map from each node to its neighbours
- Walk a graph breadth-first (with a queue) and depth-first (with recursion)
- Find the shortest path in an unweighted graph, and rebuild the path afterwards
- Test an algorithm by comparing it with a simpler one on random data

## 🧰 What you'll use

**How the work grows (Big-O):**

| Algorithm | Work for n items | 1,000 items |
|---|---|---|
| Linear search | n | up to 1,000 looks |
| Binary search (sorted data only) | log₂ n | about 10 looks |
| Bubble sort | n² | about 1,000,000 comparisons |
| Quick sort (usually) | n log n | about 10,000 comparisons |

**Binary search** looks at the middle, then throws away the half that can't contain the target:

```java
int mid = left + (right - left) / 2;   // not (left + right) / 2, which can overflow
```

**Quick sort** picks a pivot (here, the last element), moves everything smaller to its left, and then sorts each side the same way.

**A graph as a map of lists:**

```java
Map<Integer, List<Integer>> adjacency = new HashMap<>();
adjacency.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
adjacency.computeIfAbsent(to, k -> new ArrayList<>()).add(from);  // matatus go both ways
```

**Shortest path = BFS + "where did I come from?"** As the search reaches each stage, record the stage it came from in a `previous` map. When it reaches the destination, follow `previous` back to the start and reverse it.

## 🪜 Steps

**Project: The SACCO app's algorithms** (the `day77/` folder is a finished version: build your own first, then compare).

1. `Sorter`: `bubbleSort` (stopping early if nothing swapped) and `quickSort`.
2. `Searcher`: `linearSearch` and `binarySearch`, both returning -1 when the value isn't there.
3. `Graph`: `addEdge`, `breadthFirstSearch`, `depthFirstSearch` and `shortestPath`.
4. Tests: sorted, empty and duplicate arrays; both sorts agreeing on 500 random numbers; both searches agreeing on every value; the 2-ride route; no route; a stage to itself.
5. Time bubble sort and quick sort on 20,000 random numbers with `System.nanoTime()`.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day77
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **Binary search never finishes**: the loop must shrink the range every time: `left = mid + 1` or `right = mid - 1`, never `left = mid`.
- **Binary search gives wrong answers**: the array isn't sorted. Binary search only works on sorted data.
- **BFS visits a stage twice, or loops forever**: mark a stage as seen when you add it to the queue, not when you take it out.
- **The path comes out backwards**: you followed `previous` from the end. Add each stage to the front of the list, or reverse it.
- **`StackOverflowError` in quick sort on sorted data**: with the last element as pivot, sorted input makes one side empty every time. Pick the middle element as the pivot instead.

## ✅ Checklist
- [ ] Bubble sort and quick sort, tested against each other
- [ ] Linear and binary search, tested against each other
- [ ] A graph with breadth-first and depth-first walks
- [ ] The shortest route, including "no route"
- [ ] Timed the two sorts and explained the difference
- [ ] Committed code to Git

## ➡️ Next

Day 78 tackles problems where the obvious answer is wrong: greedy choices, and dynamic programming.
