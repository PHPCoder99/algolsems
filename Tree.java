class Tree{
    private Node root;
    class Node{
        int value;
        Node left;
        Node right;

        Color color;
    }

    enum Color{
        BLACK,
        RED
    }

    public void insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
        }else{
            insert(root, value);
            root = balance(root);
        }
        root.color = Color.BLACK;
    }

    private void insert(Node node, int value){
        if(node.value != value){
            if(node.value < value){
                if(node.right == null){
                    node.right = new Node();
                    node.right.value = value;
                   node.right.color = Color.RED;
                }else{
                    insert(node.right, value);
                }
            }else{
                if(node.left == null){
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                }else{
                    insert(node.left, value);
                    node.left.color = Color.BLACK;
                }
            }
        }
        return balance(node);
    }

    public Node find(int value){
        return find(root, value);
    }

    private Node find(Node node, int value) {
        if(node == null){
            return null;
        }
        if(node.value == value){
            return node;
        }
        if(node.value < value) {
            return find(node.right, value);
        }else{
            return find(node.left, value);
        }
    }

    private Node balance(Node node) {
    if (isRed(node.right) && !isRed(node.left)) {
        node = rotateLeft(node);
    }
    if (isRed(node.left) && isRed(node.left.left)) {
        node = rotateRight(node);
    }
    if (isRed(node.left) && isRed(node.right)) {
        flipColors(node);
    }
    return node;
}

private Node rotateLeft(Node node) {
    Node newRoot = node.right;
    node.right = newRoot.left;
    newRoot.left = node;
    newRoot.color = node.color;
    node.color = Color.RED;
    return newRoot;
}

private Node rotateRight(Node node) {
    Node newRoot = node.left;
    node.left = newRoot.right;
    newRoot.right = node;
    newRoot.color = node.color;
    node.color = Color.RED;
    return newRoot;
}

private void flipColors(Node node) {
    node.color = Color.RED;
    node.left.color = Color.BLACK;
    node.right.color = Color.BLACK;
}

private boolean isRed(Node node) {
    if (node == null) {
        return false;
    }
    return node.color == Color.RED;
}
}
