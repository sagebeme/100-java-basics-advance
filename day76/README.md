# Day 76 - Build Your Own: Stack, Queue and Binary Search Tree

> You've used `ArrayList` and `HashMap` for weeks. Today you build three data structures yourself, to see what's inside the ones Java gives you, and why each is fast at some things and slow at others.

## 📖 The story

Three scenes from a Nairobi morning, and the data structure behind each:

- **The plates at a kibanda.** Mama Mboga's helper stacks clean plates, and takes from the top. The last one in is the first one out: a **stack**.
- **The matatu queue at the stage.** The first person in line gets the first seat. First in, first out: a **queue**.
- **The phone book.** To find "Otieno" you don't read from the start: you open the middle, see "Mwangi", and know to go further on. Each look halves what's left: a **binary search tree**.

Today you build all three, generic so they hold any type, and test them with 10,000 items each:

```
Stack pop order: 3, 2, 1
Queue dequeue order: 1, 2, 3
Tree in-order (should be sorted): [1, 3, 4, 5, 7, 8, 9]
Tree height: 3
```

## 🎯 Learning Objectives
- Write generic classes: `CustomStack<T>`, `CustomQueue<T>`, `BinarySearchTree<T extends Comparable<T>>`
- Implement LIFO and FIFO, and choose the list that makes each one fast
- Insert into and search a binary search tree, recursively and with a loop
- Walk a tree in order to get its values sorted
- Throw the right exception for an empty structure
- Explain why the shape of a tree decides its speed

## 🧰 What you'll use

**A stack on an `ArrayList`.** Push and pop at the end, which is fast. Popping from the front would shift every other element.

```java
public T pop() {
    if (isEmpty()) throw new EmptyStackException();
    return elements.remove(elements.size() - 1);
}
```

**A queue on a `LinkedList`.** Add at the back, remove from the front, both fast. With an `ArrayList`, `remove(0)` shifts everything along.

**A binary search tree.** Every node's left side holds smaller values, and its right side holds bigger ones:

```
        5
      /   \
     3     8
    / \   / \
   1   4 7   9
```

To find 7: bigger than 5, go right; smaller than 8, go left; found. Three looks instead of seven. That's why `T extends Comparable<T>`: the tree has to be able to compare values.

**In-order traversal** (left, node, right) visits the values in sorted order: `[1, 3, 4, 5, 7, 8, 9]`.

**Height decides speed.** Insert the values above in that order and the tree is 3 levels tall. Insert `1, 2, 3, ... 7` in order and every node goes right: the "tree" becomes a line 7 tall, and searching is as slow as reading a list. (Java's `TreeMap` rebalances itself to stop this; yours doesn't.)

## 🪜 Steps

**Project: Three structures from scratch** (the `day76/` folder is a finished version: build your own first, then compare).

1. `CustomStack<T>`: `push`, `pop`, `peek`, `isEmpty`, `size`. An empty pop throws `EmptyStackException`.
2. `CustomQueue<T>`: `enqueue`, `dequeue`, `peek`. An empty dequeue throws `NoSuchElementException`.
3. `BinarySearchTree<T>`: `insert` (ignoring duplicates), `contains`, `size`, `inOrder`, `height`.
4. Tests: the order things come out, empty cases, duplicates, and 10,000 items each.
5. Insert 1 to 1,000 in order, print the height, and explain the number.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day76
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **The queue is slow with 100,000 items**: it's built on an `ArrayList` and `remove(0)` moves every element. Use a `LinkedList` or `ArrayDeque`.
- **`StackOverflowError` inserting sorted numbers**: the tree has become one long line, and the recursive `insert` goes one call deep per node. Insert in a mixed order, or write `insert` with a loop.
- **`inOrder` isn't sorted**: check the order: left subtree, then the node, then the right subtree.
- **`size` counts duplicates**: only increase it when a new node is actually created.

## ✅ Checklist
- [ ] A generic stack with LIFO order
- [ ] A generic queue with FIFO order
- [ ] A binary search tree with insert, search, in-order and height
- [ ] Empty structures throw clear exceptions
- [ ] Tests with 10,000 items pass
- [ ] Committed code to Git

## ➡️ Next

Day 77 uses these ideas for searching and sorting, and walks a graph to find the shortest route.
