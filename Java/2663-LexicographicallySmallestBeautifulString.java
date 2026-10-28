class Solution {
    public String smallestBeautifulString(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            for (char c = (char) (arr[i] + 1); c < 'a' + k; c++) {

                if (i > 0 && arr[i - 1] == c) {
                    continue;
                }

                if (i > 1 && arr[i - 2] == c) {
                    continue;
                }

                arr[i] = c;

                for (int j = i + 1; j < n; j++) {
                    for (char next = 'a'; next < 'a' + k; next++) {

                        if (arr[j - 1] == next) {
                            continue;
                        }

                        if (j > 1 && arr[j - 2] == next) {
                            continue;
                        }

                        arr[j] = next;
                        break;
                    }
                }

                return new String(arr);
            }
        }

        return "";
    }
}
