package graph;

import java.util.*;

/**
 * @author hejianglong
 * @date 2019/7/16
 */
public class Graph {
    private int v;

    private LinkedList<Integer>[] adj;

    private boolean found = false;

    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(3, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 7);
        g.bfs(0,6);
        System.out.println();
        List<Integer> friends = g.relation(0, 5);
        friends.stream().forEach(s -> System.out.println(s));
        System.out.println("深度遍历");
        g.dfs(0,6);
    }

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     *
     * @param s 起始顶点
     * @param t 终止点
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        visited[s] = true;
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    queue.add(q);
                    visited[q] = true;
                }
            }
        }
    }

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) {
            return;
        }
        if (w == t) {
            found = true;
            return;
        }
        visited[w] = true;
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    public List relation(int s, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean[] visited = new boolean[v];
        List<Integer> friends = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(s, 0);
        visited[s] = true;
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                Integer q = adj[w].get(i);
                Integer num = map.get(w) + 1;
                map.put(q,num);
                if (num > n) {
                    continue;
                }
                if (!visited[q]) {
                    friends.add(q);
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
        return friends;
    }

    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && s != t) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }
}
