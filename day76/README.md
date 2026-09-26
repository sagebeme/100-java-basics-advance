# Day 76 - Collections and Data Structures

## 📚 Learning Objectives
- Master Java collections
- Understand data structures
- Choose appropriate structures
- Implement custom structures
- Optimize data access

## 🎯 Topics Covered
- Collection framework
- Lists, Sets, Maps
- Custom data structures
- Performance optimization
- Algorithm efficiency

## 📝 Step-by-Step Instructions

### Step 1: Use Collections
Work with collections:

```java
List<String> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();
```

### Step 2: Custom Structures
Implement custom structures:

```java
public class CustomStack<T> {
    private List<T> elements = new ArrayList<>();
    
    public void push(T element) {
        elements.add(element);
    }
    
    public T pop() {
        return elements.remove(elements.size() - 1);
    }
}
```

## 🎮 Project: Custom Data Structures

### Requirements
Implement:
1. Custom stack
2. Custom queue
3. Custom tree
4. Performance testing

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Understand collections
- [ ] Can use data structures
- [ ] Can implement custom structures
- [ ] Completed custom structures
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `BinarySearchTree.java` | Insert, search, in-order traversal and height |
| `CustomQueue.java` | A queue built from scratch |
| `CustomStack.java` | A stack built from scratch |
| `Demo.java` | Runs all three |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day76
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day76
mvn test
```

Runs `BinarySearchTreeTest`, `CustomQueueTest` and `CustomStackTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 77?** You'll learn algorithms and data structures!
