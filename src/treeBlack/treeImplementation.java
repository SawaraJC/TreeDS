package treeBlack;

import java.util.*;

class Node{
	int value;
	Node left;
	Node right;
	
	Node(int value){
		this.value = value;
		left = null;
		right = null;
	}
}

class BinaryTree{
	static Node root;
	
	BinaryTree(){
		root = null;
	}
	
	void insert(int value) {
		root = insertRec(root, value);
	}
	
	Node insertRec(Node root, int value) {
		if(root == null) {
			root = new Node(value);
			return root;
		}
		
		if(value < root.value) {
			root.left = insertRec(root.left, value);
		}
		
		else if(value > root.value) {
			root.right = insertRec(root.right, value);
		}
		
		return root;
	}
	
	boolean search(int value) {
		return searchRec(root, value);
	}
	
	boolean searchRec(Node root, int val) {
		if(root == null)
			return false;
		
		if(root.value == val)
			return true;
		
		if(root.value < val) {
			return searchRec(root.right, val);
		}
		
		return searchRec(root.left, val);
	}
	
	void inorder() {
		inorderRec(root);
	}
	
	void preorder() {
		preorderRec(root);
	}
	
	void postorder() {
		postorderRec(root);
	}
	
	void inorderRec(Node root) {
		if(root != null) {
			inorderRec(root.left);
			System.out.println(root.value + " ");
			inorderRec(root.right);
		}
	}
	
	void preorderRec(Node root) {
		if(root != null) {
			System.out.println(root.value + " ");
			preorderRec(root.left);
			preorderRec(root.right);
		}
	}
	
	void postorderRec(Node root) {
		if(root != null) {
			postorderRec(root.left);	
			postorderRec(root.right);
			System.out.println(root.value + " ");
		}
	}
	
	void bfs() {
		if(root == null)
			return;
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			System.out.print(current.value + " ");
			
			if(current.left != null)
				queue.add(current.left);
			
			if(current.right != null)
				queue.add(current.right);
		}
		
		System.out.println();
	}
	
	int height(Node root) {
		if (root == null)
			return 0;
		
		int left = height(root.left);
		int right = height(root.right);
		
		int max = Math.max(left, right);
		return max+1;
	}
	
	
	
	public static void main (String[] args) {
		BinaryTree tree = new BinaryTree();
        tree.insert(100);
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(200);
        tree.insert(150);
        tree.insert(300);
        tree.insert(170);
        
        System.out.println("Inorder: ");
        tree.inorder();  // Output: 2 5 10 15
        
        System.out.println("Preorder: ");
        tree.preorder();
        
        System.out.println("Postorder: ");
        tree.postorder();
//        System.out.println(tree.search(10));  // Output: true
//        System.out.println(tree.search(7));   // Output: false
        System.out.println("BFS: ");
        tree.bfs();
        
        System.out.println("Height: ");
        int x = tree.height(root);
        System.out.println(x);
		
	}
}