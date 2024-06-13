import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SortingAndSearchingAlgorithmGUI extends JFrame implements ActionListener {
    private JComboBox<String> algorithmComboBox;
    private JComboBox<String> searchComboBox;
    private JTextField inputField;
    private JTextField searchField;
    private JTextArea outputArea;
    private JButton sortButton;
    private JButton searchButton;

    public SortingAndSearchingAlgorithmGUI() {
        setTitle("Sorting and Searching Algorithm GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top panel setup
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        algorithmComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Quick Sort", "Heap Sort", "Insertion Sort", "Selection Sort", "Merge Sort", "Shell Sort"});
        searchComboBox = new JComboBox<>(new String[]{"Linear Search", "Binary Search", "DFS"});
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
        topPanel.add(sortButton);

        topPanel.add(new JLabel("Search Algorithm:"));
        topPanel.add(searchComboBox);
        topPanel.add(new JLabel("Search Value:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // Output area setup
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to the frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            String inputString = inputField.getText().trim();
            if (inputString.isEmpty()) {
                outputArea.setText("Input cannot be empty. Please enter integers separated by commas.");
                return;
            }

            String[] inputArray = inputString.split(",");
            int[] arr = new int[inputArray.length];
            try {
                for (int i = 0; i < inputArray.length; i++) {
                    arr[i] = Integer.parseInt(inputArray[i].trim());
                }
                String algorithm = (String) algorithmComboBox.getSelectedItem();
                int[] sortedArr = sortArray(algorithm, arr);
                outputArea.setText("Sorted array: " + Arrays.toString(sortedArr));
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter integers separated by commas.");
            }
        } else if (e.getSource() == searchButton) {
            String inputString = inputField.getText().trim();
            String searchString = searchField.getText().trim();
            if (inputString.isEmpty() || searchString.isEmpty()) {
                outputArea.setText("Input and search value cannot be empty. Please enter integers.");
                return;
            }

            String[] inputArray = inputString.split(",");
            int[] arr = new int[inputArray.length];
            try {
                for (int i = 0; i < inputArray.length; i++) {
                    arr[i] = Integer.parseInt(inputArray[i].trim());
                }
                int target = Integer.parseInt(searchString.trim());
                String searchAlgorithm = (String) searchComboBox.getSelectedItem();
                if (searchAlgorithm.equals("DFS")) {
                    int vertices = arr.length;
                    DFS dfs = new DFS(vertices);
                    // For demonstration, adding some edges to the graph
                    // You can customize this part as per your requirements
                    for (int i = 0; i < vertices - 1; i++) {
                        dfs.addEdge(arr[i], arr[i + 1]);
                    }
                    outputArea.setText("DFS Traversal starting from vertex " + target + ": ");
                    dfs.DFS(target, outputArea);
                } else {
                    int index = searchArray(searchAlgorithm, arr, target);
                    if (index != -1) {
                        outputArea.setText("Element found at index: " + index);
                    } else {
                        outputArea.setText("Element not found in the array.");
                    }
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter integers.");
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
                Arrays.sort(arr);  // Ensure array is sorted for binary search
                return binarySearch(arr, target);
        }
        return -1;
    }

    public static void main(String[] args) {
        new SortingAndSearchingAlgorithmGUI();
    }

    // Bubble Sort implementation
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

    // Quick Sort implementation
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

    // Heap Sort implementation
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
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

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    // Insertion Sort implementation
    public static void insertionSort(int[] array, boolean ascending) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            if (ascending) {
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
            } else {
                while (j >= 0 && array[j] < key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
            }
            array[j + 1] = key;
        }
    }

    // Selection Sort implementation
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    // Merge Sort implementation
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
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

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

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

    // Shell Sort implementation
    public static void shellSort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }

    // Linear Search implementation
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search implementation
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

    // DFS Class
    public static class DFS {
        private int V;
        private LinkedList<Integer>[] adj;

        public DFS(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        void DFSUtil(int v, boolean[] visited, JTextArea outputArea) {
            visited[v] = true;
            outputArea.append(v + " ");

            for (int n : adj[v]) {
                if (!visited[n]) {
                    DFSUtil(n, visited, outputArea);
                }
            }
        }

        void DFS(int v, JTextArea outputArea) {
            boolean[] visited = new boolean[V];
            DFSUtil(v, visited, outputArea);
        }
    }
}
