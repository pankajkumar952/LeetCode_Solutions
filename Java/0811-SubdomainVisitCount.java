
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String cp : cpdomains) {
            String[] parts = cp.split(" ");
            int count = Integer.parseInt(parts[0]);
            String domain = parts[1];

            map.put(domain, map.getOrDefault(domain, 0) + count);

            while (domain.contains(".")) {
                domain = domain.substring(domain.indexOf('.') + 1);
                map.put(domain, map.getOrDefault(domain, 0) + count);
            }
        }

        List<String> result = new ArrayList<>();

        for (String domain : map.keySet()) {
            result.add(map.get(domain) + " " + domain);
        }

        return result;
    }
}

