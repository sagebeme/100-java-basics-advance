# Day 77 - Algorithms and Data Structures

## 📚 Learning Objectives
- Implement common algorithms
- Understand algorithm complexity
- Solve algorithmic problems
- Optimize algorithms
- Apply data structures

## 🎯 Topics Covered
- Sorting algorithms
- Searching algorithms
- Graph algorithms
- Algorithm complexity
- Problem solving

## 📝 Step-by-Step Instructions

### Step 1: Sorting
Implement sorting:

```java
public class Sorter {
    public void bubbleSort(int[] arr) {
        // Bubble sort implementation
    }
    
    public void quickSort(int[] arr) {
        // Quick sort implementation
    }
}
```

### Step 2: Searching
Implement searching:

```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

## 🎮 Project: Algorithm Library

### Requirements
Implement:
1. Sorting algorithms
2. Searching algorithms
3. Graph algorithms
4. Performance testing

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can implement algorithms
- [ ] Understand complexity
- [ ] Can solve problems
- [ ] Completed algorithm library
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Graph.java` | Breadth-first and depth-first search, and the shortest path by number of edges |
| `Searcher.java` | Linear and binary search |
| `Sorter.java` | Bubble sort and quicksort |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day77
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day77
mvn test
```

Runs `GraphTest`, `SearcherTest` and `SorterTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 78?** You'll learn advanced algorithms!
