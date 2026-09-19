package com.learning;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    /**
     *   1 - 2 - 4
     *   |       |
     *   3 - - - 5
     */
    private Graph sampleGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);
        return graph;
    }

    @Test
    void breadthFirstSearchVisitsEveryReachableNode() {
        List<Integer> visited = sampleGraph().breadthFirstSearch(1);
        assertEquals(5, visited.size());
        assertTrue(visited.containsAll(List.of(1, 2, 3, 4, 5)));
        assertEquals(1, visited.get(0));
    }

    @Test
    void depthFirstSearchVisitsEveryReachableNode() {
        List<Integer> visited = sampleGraph().depthFirstSearch(1);
        assertEquals(5, visited.size());
        assertTrue(visited.containsAll(List.of(1, 2, 3, 4, 5)));
        assertEquals(1, visited.get(0));
    }

    @Test
    void shortestPathFindsTheFewestEdgesRoute() {
        // 1 -> 3 -> 5 is 2 edges; 1 -> 2 -> 4 -> 5 is 3 edges. BFS must find the 2-edge route.
        List<Integer> path = sampleGraph().shortestPath(1, 5);
        assertEquals(List.of(1, 3, 5), path);
    }

    @Test
    void shortestPathFromANodeToItselfIsJustThatNode() {
        assertEquals(List.of(1), sampleGraph().shortestPath(1, 1));
    }

    @Test
    void shortestPathReturnsEmptyWhenThereIsNoRoute() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(10, 20); // a separate, disconnected component
        assertTrue(graph.shortestPath(1, 10).isEmpty());
    }
}
