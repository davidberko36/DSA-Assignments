public class InsertionSort {
    public static void sort(int[] arr, boolean ascending) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            if (ascending) {
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
            } else {
                while (j >= 0 && arr[j] < key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
            }
            arr[j + 1] = key;
        }
    }
}

