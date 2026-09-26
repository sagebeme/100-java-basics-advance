# Day 78 - Advanced Algorithms

## 📚 Learning Objectives
- Implement advanced algorithms
- Work with complex data structures
- Solve challenging problems
- Optimize performance
- Apply advanced techniques

## 🎯 Topics Covered
- Advanced sorting
- Graph algorithms
- Dynamic programming
- Greedy algorithms
- Optimization techniques

## 📝 Step-by-Step Instructions

### Step 1: Graph Algorithms
Implement graph algorithms:

```java
public class GraphAlgorithms {
    public List<Integer> bfs(Graph graph, int start) {
        // Breadth-first search
    }
    
    public List<Integer> dfs(Graph graph, int start) {
        // Depth-first search
    }
}
```

### Step 2: Dynamic Programming
Use dynamic programming:

```java
public int fibonacci(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

## 🎮 Project: Advanced Algorithm Solutions

### Requirements
Solve advanced problems:
1. Graph problems
2. Dynamic programming
3. Greedy algorithms
4. Optimization

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can implement advanced algorithms
- [ ] Can solve complex problems
- [ ] Can optimize solutions
- [ ] Completed advanced algorithms
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `DynamicProgramming.java` | Fibonacci built bottom-up, the longest common subsequence, and the 0/1 knapsack |
| `GreedyAlgorithms.java` | Activity selection and greedy coin change |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day78
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day78
mvn test
```

Runs `DynamicProgrammingTest` and `GreedyAlgorithmsTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 79?** You'll learn testing and quality assurance!
