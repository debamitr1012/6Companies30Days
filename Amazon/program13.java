class Solution {
  public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap();
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
            (a,b)->{
                if(a.getValue() != b.getValue())
                    return a.getValue().compareTo(b.getValue());
                return -a.getKey().compareTo(b.getKey());
            }
        );
        for(String word: words){
            map.merge(word, 1, Integer::sum);
        }
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            heap.offer(entry);
            if(heap.size() > k)
                heap.poll();
        }
        List<String> ans = new ArrayList();
        while(heap.size() > 0)
            ans.add(heap.poll().getKey());
        Collections.reverse(ans);
        return ans;
    }
}