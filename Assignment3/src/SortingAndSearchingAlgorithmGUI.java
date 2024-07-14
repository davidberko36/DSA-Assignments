import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
                "Selection Sort", "Merge Sort", "Shell Sort", "Radix Sort",
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
                    int index = BruteForceSearch.search(inputString, targetString);
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
                BubbleSort.sort(arr);
                break;
            case "Quick Sort":
                QuickSort.sort(arr, 0, arr.length - 1);
                break;
            case "Heap Sort":
                HeapSort.sort(arr);
                break;
            case "Insertion Sort":
                InsertionSort.sort(arr, true);
                break;
            case "Selection Sort":
                SelectionSort.sort(arr);
                break;
            case "Merge Sort":
                MergeSort.sort(arr, 0, arr.length - 1);
                break;
            case "Shell Sort":
                ShellSort.sort(arr);
                break;
            case "Radix Sort":
                RadixSort.sort(arr);
                break;
        }
        return arr;
    }

    private int searchArray(String algorithm, int[] arr, int target) {
        switch (algorithm) {
            case "Linear Search":
                return LinearSearch.search(arr, target);
            case "Binary Search":
                return BinarySearch.search(arr, target);
            case "Exhaustive Search":
                return ExhaustiveSearch.search(arr, target) ? target : -1;
        }
        return -1;
    }

    public static void main(String[] args) {
        new SortingAndSearchingAlgorithmGUI();
    }
}
