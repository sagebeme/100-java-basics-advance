package com.learning;

public class Demo {
    public static void main(String[] args) {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack pop order: " + stack.pop() + ", " + stack.pop() + ", " + stack.pop());

        CustomQueue<Integer> queue = new CustomQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Queue dequeue order: " + queue.dequeue() + ", " + queue.dequeue() + ", " + queue.dequeue());

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int value : new int[]{5, 3, 8, 1, 4, 7, 9}) {
            tree.insert(value);
        }
        System.out.println("Tree in-order (should be sorted): " + tree.inOrder());
        System.out.println("Tree height: " + tree.height());
    }
}
