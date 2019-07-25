package sort;

import java.util.LinkedList;
import java.util.List;

/**
 * @author hejianglong
 * @date 2019/7/25
 */
public class TopologicalSort {

    public static void main(String[] args) {
    }

    static class Graph {
        private int v;

        private List<Integer>[] adj;

        public Graph(int v) {
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int start, int end) {
            adj[start].add(end);
        }

        public void topoSortByKahn() {
            int[] inDegree = new int[v];
            // 统计每个定点的入度
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int v = adj[i].get(j);
                    inDegree[v]++;
                }
            }
            LinkedList<Integer> queue = new LinkedList<>();
            // 入度为 0 的输出
            for (int i = 0; i < v; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    System.out.print("->" + inDegree[i]);
                }
            }
            while (!queue.isEmpty()) {
                int k = queue.remove();
                System.out.print("->" + k);
                for (int i = 0; i < adj[k].size(); i++) {
                    int v = adj[k].get(i);
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.add(v);
                    }
                }
            }
        }

        public void topoSortByDFS() {
            LinkedList<Integer>[] inverseAdj = new LinkedList[v];
            // 构建逆邻链表用于表示某个定点依赖的其它定点
            for (int i = 0 ; i < v; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int v = adj[i].get(j);
                    inverseAdj[v].add(i);
                }
            }
            boolean[] visited = new boolean[v];
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < inverseAdj[i].size(); j++) {
                    if (!visited[i]) {
                        visited[j] = true;
                        dfs(i, inverseAdj, visited);
                    }
                }
            }
        }

        private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
            for (int i = 0; i < inverseAdj[vertex].size(); i++) {
                int k = inverseAdj[vertex].get(i);
                if (visited[k]) {
                    continue;
                }
                visited[k] = true;
                dfs(k, inverseAdj, visited);
            }
            System.out.print("->" + vertex);
        }
    }
}
