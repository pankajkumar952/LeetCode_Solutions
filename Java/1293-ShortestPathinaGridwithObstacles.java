class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        if (k >= m + n - 2) {
            return m + n - 2;
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][][] visited = new boolean[m][n][k + 1];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, k});
        visited[0][0][k] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] curr = queue.poll();

                int r = curr[0];
                int c = curr[1];
                int remain = curr[2];

                if (r == m - 1 && c == n - 1) {
                    return steps;
                }

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    int nextRemain = remain - grid[nr][nc];

                    if (nextRemain >= 0 && !visited[nr][nc][nextRemain]) {
                        visited[nr][nc][nextRemain] = true;
                        queue.offer(new int[]{nr, nc, nextRemain});
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}

