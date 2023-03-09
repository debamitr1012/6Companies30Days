class Solution {
    private static final String DELIMITER = ",";
    private static final Integer MAX_AMOUNT = 1000;
    private static final Integer MAX_DIFF = 60;
    private static class TransactionEntry {
        int id;
        String name;
        int time;
        int amount;
        String city;
        public TransactionEntry(int id, String name, int time, int amount, String city) {
            this.id = id;
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }
    }
    public List<String> invalidTransactions(String[] transactions) {
        if (transactions == null) {
            return Collections.emptyList();
        }
        Map<String, List<TransactionEntry>> nameToTransactions = new HashMap<>();
        for (int i = 0; i < transactions.length; ++i) {
            String[] splittedTransaction = transactions[i].split(DELIMITER);
            TransactionEntry entry = new TransactionEntry(i, splittedTransaction[0], Integer.parseInt(splittedTransaction[1]), Integer.parseInt(splittedTransaction[2]), splittedTransaction[3]);
            List<TransactionEntry> listTransactions = nameToTransactions.getOrDefault(entry.name, new ArrayList<>()); 
            listTransactions.add(entry);
            nameToTransactions.put(entry.name, listTransactions);
        }
        List<String> invalidTransactions = new ArrayList<>();
        for(Map.Entry<String, List<TransactionEntry>> entry : nameToTransactions.entrySet()) {
            checkList(entry.getValue(), invalidTransactions, transactions);
        }
        return invalidTransactions;
    }
    private void checkList(List<TransactionEntry> entries, List<String> invalidTransactions, String[] transactions) {
        for (TransactionEntry entry1 : entries) {
            if (entry1.amount > MAX_AMOUNT) {
                invalidTransactions.add(transactions[entry1.id]);
                continue;
            }
            for (TransactionEntry entry2 : entries) {
                if (entry1.id != entry2.id && Math.abs(entry1.time - entry2.time) <= MAX_DIFF && !entry1.city.equals(entry2.city)) {
                    invalidTransactions.add(transactions[entry1.id]);
                    break;
                }}}}}