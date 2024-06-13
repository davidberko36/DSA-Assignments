import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingAndSearchingAlgorithmGUI extends JFrame implements ActionListener {
    private JComboBox<String> algorithmComboBox;
    private JTextField inputField;
    private JTextField searchField;
    private JTextArea outputArea;
    private JButton sortButton;
    private JButton searchButton;

    public SortingAndSearchingAlgorithmGUI() {
        setTitle("Sorting and Searching Algorithm GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        algorithmComboBox = new JComboBox<>(new String[]{
                "Bubble Sort", "Quick Sort", "Heap Sort", "Insertion Sort",
                "Selection Sort", "Merge Sort", "Shell Sort",
                "Linear Search", "Binary Search", "Exhaustive Search", "Brute Force Search"
        });
        inputField = new JTextField(20);
        searchField = new JTextField(10);
        sortButton = new JButton("Sort");
        searchButton = new JButton("Search");

        sortButton.addActionListener(this);
        searchButton.addActionListener(this);

        topPanel.add(new JLabel("Algorithm:"));
        topPanel.add(algorithmComboBox);
        topPanel.add(new JLabel("Input:"));
        topPanel.add(inputField);
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(sortButton);
        topPanel.add(searchButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String algorithm = (String) algorithmComboBox.getSelectedItem();
        String inputString = inputField.getText();

        if (e.getSource() == sortButton) {
            try {
                String[] inputArray = inputString.split(",");
                int[] arr = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();
                int[] sortedArr = sortArray(algorithm, arr);
                outputArea.setText(Arrays.toString(sortedArr));
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter integers separated by commas.");
            }
        } else if (e.getSource() == searchButton) {
            try {
                String[] inputArray = inputString.split(",");
                int[] arr = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();
                String targetString = searchField.getText();

                if (algorithm.equals("Linear Search") || algorithm.equals("Binary Search") || algorithm.equals("Exhaustive Search")) {
                    int target = Integer.parseInt(targetString);
                    int index = searchArray(algorithm, arr, target);
                    outputArea.setText("Index: " + index);
                } else if (algorithm.equals("Brute Force Search")) {
                    int index = bruteForceSearch(inputString, targetString);
                    outputArea.setText("Index: " + index);
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input or target. Please enter integers.");
            }
        }
    }

    private int[] sortArray(String algorithm, int[] arr) {
        switch (algorithm) {
            case "Bubble Sort":
                bubbleSort(arr);
                break;
            case "Quick Sort":
                quickSort(arr, 0, arr.length - 1);
                break;
            case "Heap Sort":
                heapSort(arr);
                break;
            case "Insertion Sort":
                insertionSort(arr, true);
                break;
            case "Selection Sort":
                selectionSort(arr);
                break;
            case "Merge Sort":
                mergeSort(arr, 0, arr.length - 1);
                break;
            case "Shell Sort":
                shellSort(arr);
                break;
        }
        return arr;
    }

    private int searchArray(String algorithm, int[] arr, int target) {
        switch (algorithm) {
            case "Linear Search":
                return linearSearch(arr, target);
            case "Binary Search":
                Arrays.sort(arr); // Binary search requires sorted array
                return binarySearch(arr, target);
            case "Exhaustive Search":
                return exhaustiveSearch(arr, target) ? target : -1;
        }
        return -1;
    }

    private boolean exhaustiveSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    private int bruteForceSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new SortingAndSearchingAlgorithmGUI();
    }

    // Sorting and searching algorithms implementations
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public static void insertionSort(int[] arr, boolean ascending) {
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

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

