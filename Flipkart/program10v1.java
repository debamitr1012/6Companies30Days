public class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes == null || barcodes.length <= 2) return barcodes;
        rearrangeBarcodes(barcodes, true);
        return barcodes;
    }
    private void rearrangeBarcodes(int[] barcodes, boolean isFirstRearrangement) {
        for (int i = 1; i < barcodes.length; i++) {
            if (barcodes[i] == barcodes[i - 1]) {
                int swapIndex = findIndexToSwapWith(barcodes, i);
                int temp = barcodes[i];
                barcodes[i] = barcodes[swapIndex];
                barcodes[swapIndex] = temp;
            }
        }
        if (isFirstRearrangement) rearrangeBarcodes(barcodes, false);
    }
    private int findIndexToSwapWith(int[] barcodes, int i) {
        for (int j = i + 1; j < barcodes.length; j++) {
            if (barcodes[i] != barcodes[j]) return j;
        }
        for (int j = 0; j < i; j++) {
            if (barcodes[i] != barcodes[j]) return j;
        }
        throw new IllegalArgumentException("Could not find any barcode aside from " + barcodes[i]);
    }
}