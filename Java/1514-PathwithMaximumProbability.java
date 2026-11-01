
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<double[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double prob = succProb[i];

            graph.get(a).add(new double[]{b, prob});
            graph.get(b).add(new double[]{a, prob});
        }

        double[] maxProb = new double[n];
        maxProb[start] = 1.0;

        PriorityQueue<double[]> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b[1], a[1])
        );

        pq.offer(new double[]{start, 1.0});

        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            int node = (int) curr[0];
            double prob = curr[1];

            if (prob < maxProb[node]) {
                continue;
            }

            if (node == end) {
                return prob;
            }

            for (double[] neighbor : graph.get(node)) {
                int next = (int) neighbor[0];
                double newProb = prob * neighbor[1];

                if (newProb > maxProb[next]) {
                    maxProb[next] = newProb;
                    pq.offer(new double[]{next, newProb});
                }
            }
        }

        return 0.0;
    }
}

