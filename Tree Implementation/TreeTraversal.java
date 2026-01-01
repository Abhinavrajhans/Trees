
import java.util.*;
public class TreeTraversal {

    public class Pair{
        Node node;
        int state;
        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }

    void preorder(Node node) {
        if (node == null)return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    void iterativePreorder(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    void iterativeInorder(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
    }

    void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    void iterativePostorder(Node root) {
        if (root == null) return;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }


    void iterativePostorderSingleStack(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Node lastVisited = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                Node peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    System.out.print(peekNode.data + " ");
                    lastVisited = stack.pop();
                }
            }
        }
    }

    void preInPostTraversal(Node node) {
        if (node == null) return;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(node, 1));
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            if (p.state == 1) {
                pre.add(p.node.data);
                p.state++;
                stack.push(p);
                if (p.node.left != null) stack.push(new Pair(p.node.left, 1));
            } else if (p.state == 2) {
                in.add(p.node.data);
                p.state++;
                stack.push(p);
                if (p.node.right != null) stack.push(new Pair(p.node.right, 1));
            } else {
                post.add(p.node.data);
            }
        }
        System.out.println("Preorder: " + pre);
        System.out.println("Inorder: " + in);
        System.out.println("Postorder: " + post);
    }


    void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
            if (tempNode.left != null) queue.add(tempNode.left);
            if (tempNode.right != null) queue.add(tempNode.right);
        }
    }

    public static void main(String[] args) {
        TreeTraversal tree = new TreeTraversal();
        Node root=null;
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Inorder traversal of the binary tree is:");
        tree.inorder(root);
    }
}