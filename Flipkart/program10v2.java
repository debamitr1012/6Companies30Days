class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] frequency = new int[10001];
        for (int barcode : barcodes) {
            ++frequency[barcode];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<> ((barcode1, barcode2) -> frequency[barcode2] - frequency[barcode1]);
        for (int barcode = 1; barcode <= 10000; barcode++) {
            if (frequency[barcode] != 0) {
                maxHeap.offer (barcode);
            }
        }
        int index = 0;
        while (!maxHeap.isEmpty ()) {
            int barcode = maxHeap.poll ();
            int freq = frequency[barcode];
            while (freq-- != 0) {
                barcodes[index] = barcode;
                index += 2;
                
                if (index >= barcodes.length) {
                    index = 1;
                }
            }
        }
        return barcodes;
    }
}