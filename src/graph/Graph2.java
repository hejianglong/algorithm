package graph;

import java.util.LinkedList;

/**
 * @author hejianglong
 * @date 2019/7/25
 */
public class Graph2 {

    private LinkedList<Edge>[] adj;
    private int v;

    public static void main(String[] args) {
        Graph2 g = new Graph2(6);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 4, 15);
        g.addEdge(1, 2, 15);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 5, 5);
        g.addEdge(3, 2, 1);
        g.addEdge(3, 5, 12);
        g.addEdge(4, 5, 10);
        g.dijkstra(0, 5);
    }

    public Graph2(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    /**
     * 从顶点 s 到顶点 t 的最短路径
     * @param s 顶点
     * @param t 顶点
     */
    public void dijkstra(int s, int t) {
        // 用来还原最短路径
        int[] predecessor = new int[this.v];
        Vertex[] vertices = new Vertex[this.v];
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue queue = new PriorityQueue(v);
        queue.add(vertices[s]);
        boolean[] inQueue = new boolean[v];
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex first = queue.poll();
            if (first.id == t) {
                break;
            }
            for (int i = 0; i < adj[first.id].size(); i++) {
                Edge nextEdge = adj[first.id].get(i);
                Vertex nextVertex = vertices[nextEdge.tid];
                if (first.dist + nextEdge.w < nextVertex.dist) {
                    nextVertex.dist = first.dist + nextEdge.w;
                    predecessor[nextVertex.id] = first.id;
                    if (inQueue[nextVertex.id]) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inQueue[nextVertex.id] = true;
                    }
                }
            }
        }
        System.out.println("->" + s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.println("->" + t);
    }

    // 构建小顶堆
    private class PriorityQueue {
        private Vertex[] nodes;
        private int count;
        private int size = 0;
        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.count = v;
        }

        public Vertex poll() {
            Vertex result = nodes[1];
            nodes[1] = nodes[size];
            nodes[size] = null;
            --size;
            if (size > 1) {
                heapify(1, size);
            }
            return result;
        }

        public void add(Vertex vertex) {
            if (size < count + 1) {
                ++size;
            }
            nodes[size] = vertex;
            int i = size;
            while (i/2 > 0 && nodes[i].dist < nodes[i/2].dist) {
                swap(i, i/2);
                i = i/2;
            }
        }

        public void update(Vertex vertex) {
            for (int i = 1; i < size; i++) {
                if (nodes[i].id == vertex.id) {
                    nodes[i].dist = vertex.dist;
                    break;
                }
            }
            for (int i = size/2; i >= 1; i--) {
                heapify(i, size);
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void swap(int i, int minPos) {
            Vertex tmp = nodes[i];
            nodes[i] = nodes[minPos];
            nodes[minPos] = tmp;
        }

        private void heapify(int i, int n) {
            for (;;) {
                int minPos = i;
                if (i * 2 <= n && nodes[i].dist > nodes[i * 2].dist) {
                    minPos = i * 2;
                }
                if (i * 2 + 1 <= n && nodes[minPos].dist > nodes[i * 2 + 1].dist) {
                    minPos = i * 2 + 1;
                }
                if (minPos == i) {
                    break;
                }
                swap(i, minPos);
                i = minPos;
            }
        }
    }

    private class Vertex {
        private int dist;
        private int id;

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重
        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }
}
