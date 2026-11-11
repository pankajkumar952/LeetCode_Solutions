
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        int[] degree = new int[n + 1];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u][v] = true;
            graph[v][u] = true;

            degree[u]++;
            degree[v]++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (!graph[i][j]) {
                    continue;
                }

                for (int k = j + 1; k <= n; k++) {
                    if (graph[i][k] && graph[j][k]) {
                        int trioDegree = degree[i] + degree[j] + degree[k] - 6;
                        ans = Math.min(ans, trioDegree);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

