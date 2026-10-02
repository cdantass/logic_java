import java.util.*;

/**
 * Exemplos em Java dos principais padroes algoritmicos usados no LeetCode.
 * Cada metodo estatico eh independente e pode ser copiado/testado separadamente.
 */
public class PadroesLeetCode {

    // ============================================================
    // 1. TWO POINTERS (ponteiros opostos) - Two Sum II (array ordenado)
    // ============================================================
    public static int[] twoSumSorted(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) return new int[]{left, right};
            else if (sum < target) left++;   // soma pequena -> aumenta
            else right--;                    // soma grande -> diminui
        }
        return new int[]{-1, -1};
    }

    // Two Pointers - Fast/Slow (mesma direcao) - detectar ciclo em lista ligada
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // ============================================================
    // 2. SLIDING WINDOW (tamanho fixo) - soma maxima de subarray de tamanho k
    // ============================================================
    public static int maxSumSubarrayFixed(int[] arr, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += arr[i];
        int maxSum = windowSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k]; // entra um, sai outro
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    // SLIDING WINDOW (tamanho variavel) - maior substring sem repeticao
    public static int longestSubstringWithoutRepeat(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {              // condicao violada -> encolhe
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // ============================================================
    // 3. PREFIX SUM - soma de intervalo em O(1) apos pre-processamento
    // ============================================================
    static class PrefixSum {
        int[] prefix;

        PrefixSum(int[] nums) {
            prefix = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        // soma do intervalo [i, j] inclusive
        int rangeSum(int i, int j) {
            return prefix[j + 1] - prefix[i];
        }
    }

    // ============================================================
    // 4. BINARY SEARCH - busca classica em array ordenado
    // ============================================================
    public static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    // BINARY SEARCH na resposta (espaco de respostas monotonico)
    // Exemplo: menor capacidade de barco para transportar pacotes em D dias
    public static int minCapacity(int[] weights, int days) {
        int lo = Arrays.stream(weights).max().getAsInt();
        int hi = Arrays.stream(weights).sum();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShip(weights, mid, days)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private static boolean canShip(int[] weights, int capacity, int days) {
        int daysNeeded = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysNeeded++;
                currentLoad = 0;
            }
            currentLoad += w;
        }
        return daysNeeded <= days;
    }

    // ============================================================
    // 5. BFS - menor caminho em grafo/grade (nivel por nivel)
    // ============================================================
    public static int shortestPathBFS(int[][] grid, int[] start, int[] end) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0}); // linha, coluna, distancia
        visited[start[0]][start[1]] = true;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == end[0] && curr[1] == end[1]) return curr[2];
            for (int[] d : dirs) {
                int nr = curr[0] + d[0], nc = curr[1] + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && !visited[nr][nc] && grid[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, curr[2] + 1});
                }
            }
        }
        return -1;
    }

    // ============================================================
    // 6. DFS - travessia de arvore binaria (recursivo)
    // ============================================================
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // ============================================================
    // 7. HEAP / PRIORITY QUEUE - Top K elementos mais frequentes
    // ============================================================
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);

        // min-heap ordenado pela frequencia
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            heap.offer(new int[]{entry.getKey(), entry.getValue()});
            if (heap.size() > k) heap.poll(); // remove o menos frequente
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) result[i] = heap.poll()[0];
        return result;
    }

    // ============================================================
    // 8. LINKED LIST - reverter lista ligada
    // ============================================================
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // ============================================================
    // 9. BACKTRACKING - gerar todas as subconjuntos (subsets)
    // ============================================================
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackSubsets(int[] nums, int start,
                                          List<Integer> current,
                                          List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // adiciona o estado atual
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);                     // escolhe
            backtrackSubsets(nums, i + 1, current, result); // explora
            current.remove(current.size() - 1);        // desfaz (backtrack)
        }
    }

    // ============================================================
    // 10. DYNAMIC PROGRAMMING - Fibonacci com memoizacao (top-down)
    // ============================================================
    public static int fibMemo(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // DP - Coin Change (tabulacao / bottom-up)
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // valor "infinito"
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // ============================================================
    // 11. INTERVALS - mesclar intervalos sobrepostos
    // ============================================================
    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // ordena pelo inicio
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] =
                        Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    // ============================================================
    // 12. UNION FIND (DISJOINT SET) - detectar componentes conectados
    // ============================================================
    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]); // compressao de caminho
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) parent[rootX] = rootY;
        }
    }

    // ============================================================
    // 13. MONOTONIC STACK - proximo elemento maior (next greater element)
    // ============================================================
    public static int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // guarda indices

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i]; // achou o "proximo maior"
            }
            stack.push(i);
        }
        return result;
    }

    // ============================================================
    // 14. TOPOLOGICAL SORT - ordenacao de cursos com pre-requisitos
    // ============================================================
    public static int[] topologicalSort(int numNodes, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numNodes];
        for (int i = 0; i < numNodes; i++) graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numNodes];
        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[idx++] = node;
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) queue.offer(neighbor);
            }
        }
        return idx == numNodes ? order : new int[0]; // vazio = ha ciclo
    }

    // ============================================================
    // 15. BIT MANIPULATION - encontrar o unico numero (XOR)
    // ============================================================
    public static int findSingleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) result ^= n; // pares se cancelam, sobra o unico
        return result;
    }

    // ============================================================
    // 16. HASH MAP - Two Sum classico (array nao ordenado)
    // ============================================================
    public static int[] twoSumUnsorted(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // valor -> indice
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
