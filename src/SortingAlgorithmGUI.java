import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingAlgorithmGUI extends JFrame implements ActionListener {
    private JComboBox<String> algorithmComboBox;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton sortButton;

    public SortingAlgorithmGUI() {
        setTitle("Sorting Algorithm GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        algorithmComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Quick Sort", "Heap Sort", "Insertion Sort", "Selection Sort"});
        inputField = new JTextField(20);
        sortButton = new JButton("Sort");
        sortButton.addActionListener(this);
        topPanel.add(new JLabel("Algorithm:"));
        topPanel.add(algorithmComboBox);
        topPanel.add(new JLabel("Input:"));
        topPanel.add(inputField);
        topPanel.add(sortButton);

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
        if (e.getSource() == sortButton) {
            String inputString = inputField.getText();
            String[] inputArray = inputString.split(",");
            int[] arr = new int[inputArray.length];
            try {
                for (int i = 0; i < inputArray.length; i++) {
                    arr[i] = Integer.parseInt(inputArray[i].trim());
                }
                String algorithm = (String) algorithmComboBox.getSelectedItem();
                int[] sortedArr = sortArray(algorithm, arr);
                outputArea.setText(Arrays.toString(sortedArr));
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter integers separated by commas.");
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
        }
        return arr;
    }

    public static void main(String[] args) {
        new SortingAlgorithmGUI();
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
            int minIndex = i;

            // Find the index of the minimum element in the unsorted part of the array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first element of the unsorted part
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}