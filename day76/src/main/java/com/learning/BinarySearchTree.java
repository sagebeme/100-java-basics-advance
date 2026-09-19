package com.learning;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size = 0;

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        }
        // Equal values are not duplicated - a set-like tree, not a multiset.
        return node;
    }

    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp == 0) return true;
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }

    public int size() {
        return size;
    }

    /**
     * In-order traversal, which visits a binary search tree's values in sorted order.
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, List<T> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
