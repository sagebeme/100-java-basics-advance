package com.learning;

import java.util.*;

public class Graph {
    private final Map<Integer, List<Integer>> adjacency = new HashMap<>();

    public void addEdge(int from, int to) {
        adjacency.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        adjacency.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
    }

    private List<Integer> neighborsOf(int node) {
        return adjacency.getOrDefault(node, List.of());
    }

    public List<Integer> breadthFirstSearch(int start) {
        List<Integer> visited = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        seen.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited.add(node);
            for (int neighbor : neighborsOf(node)) {
                if (seen.add(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }

    public List<Integer> depthFirstSearch(int start) {
        List<Integer> visited = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        depthFirstSearch(start, seen, visited);
        return visited;
    }

    private void depthFirstSearch(int node, Set<Integer> seen, List<Integer> visited) {
        if (!seen.add(node)) return;
        visited.add(node);
        for (int neighbor : neighborsOf(node)) {
            depthFirstSearch(neighbor, seen, visited);
        }
    }

    /**
     * The shortest path between two nodes by number of edges (BFS is optimal for this on an
     * unweighted graph), or an empty list if there is no path.
     */
    public List<Integer> shortestPath(int start, int end) {
        Map<Integer, Integer> previous = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        seen.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == end) {
                return reconstructPath(previous, start, end);
            }
            for (int neighbor : neighborsOf(node)) {
                if (seen.add(neighbor)) {
                    previous.put(neighbor, node);
                    queue.add(neighbor);
                }
            }
        }
        return List.of();
    }

    private List<Integer> reconstructPath(Map<Integer, Integer> previous, int start, int end) {
        LinkedList<Integer> path = new LinkedList<>();
        Integer current = end;
        while (current != null) {
            path.addFirst(current);
            if (current == start) break;
            current = previous.get(current);
        }
        return path;
    }
}
