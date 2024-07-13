import java.util.*;

class HuffmanTree {
    private Node root;

    public HuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

        // Create a leaf node for each character and add it to the priority queue.
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            priorityQueue.add(new Node(entry.getValue(), entry.getKey()));
        }

        // Combine nodes until there is only one node left in the queue.
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node combined = new Node(left.frequency + right.frequency, left, right);
            priorityQueue.add(combined);
        }

        // The remaining node is the root of the Huffman tree.
        root = priorityQueue.poll();
    }

    public Map<Character, String> generateCodes() {
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodesRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private void generateCodesRecursive(Node node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        // If this is a leaf node, it contains a character.
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }

        // Traverse the left and right subtrees.
        generateCodesRecursive(node.left, code + "0", huffmanCodes);
        generateCodesRecursive(node.right, code + "1", huffmanCodes);
    }
}

