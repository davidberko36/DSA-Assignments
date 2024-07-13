import java.util.*;

public class HuffmanCoding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of characters.
        System.out.print("Enter the number of characters: ");
        int numChars = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Read the characters and their frequencies.
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < numChars; i++) {
            System.out.print("Enter character and frequency (e.g., a 5): ");
            String input = scanner.nextLine();
            char character = input.charAt(0);
            int frequency = Integer.parseInt(input.substring(2));
            frequencies.put(character, frequency);
        }

        // Build the Huffman tree and generate codes.
        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        Map<Character, String> huffmanCodes = huffmanTree.generateCodes();

        // Print the generated Huffman codes.
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
