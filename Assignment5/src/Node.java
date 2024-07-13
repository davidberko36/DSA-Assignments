class Node {
    int frequency;
    char character;
    Node left;
    Node right;

    Node(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
        this.left = this.right = null;
    }

    Node(int frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
