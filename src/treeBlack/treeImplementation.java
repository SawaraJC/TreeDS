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
	
	Node deleteNode(Node root, int key) {
		if(root == null)
			return null;
		
        // If the key to be deleted is smaller than the root's key, then it lies in the left subtree
		if(root.value>key) {
				root.left = deleteNode(root.left, key);
		}
		
        // If the key to be deleted is greater than the root's key, then it lies in the right subtree
		else if(root.value < key) {
			root.right = deleteNode(root.right, key);
		}
		
        // If key is same as root's key, then this is the node to be deleted
		else {
			if(root.left == null) {
				return root.right;
			}
			
			else if (root.right == null) {
				return root.left;
			}
			
            // Node with two children: Get the inorder successor (smallest in the right subtree)
			root.value = minValue(root.right);
			
			root.right = deleteNode(root.right, root.value);
		}
		
		return root;
	}
	
	int minValue(Node root) {
		int min = root.value;
        while (root.left != null) {
            min = root.left.value;
            root = root.left;
        }
        return min;
	}
	
	int findCeil(Node root, int key) {
        if (root == null) return -1;
        // Code here
        
        int ceil = -1;
        
        while(root != null){
            if(root.value == key){
                ceil = root.value;
                return ceil;
            }
            
            if(key > root.value){
                root = root.right;
            }else{
                ceil = root.value;
                root = root.left;
            }
        }
        
        return ceil;
    }
	
	int findFloor(Node root, int key) {
		int floor = -1;

        // Traverse the BST until reaching
        // the end or finding the key
        while (root != null) {
            // If the key is found, assign it
            // as the floor value and return
            if (root.value == key) {
                floor = root.value;
                return floor;
            }

            // If the key is greater than the current
            // node's value, move to the right subtree
            if (key > root.value) {
                // Update the floor with the current node's
                // value and move to the right subtree
                floor = root.value;
                root = root.right;
            } else {
                // If the key is smaller than the current
                // node's value, move to the left subtree
                root = root.left;
            }
        }
        // Return the computed floor value
        return floor;
	}
	
	public List<List<Integer>> levelOrder(Node root){
		List<List<Integer>> solution = new ArrayList<>();
		Queue<Node> que = new LinkedList<>();
		int max = Integer.MIN_VALUE;
		
		if(root == null)
			return solution;
		
		que.offer(root);
		
		while(!que.isEmpty()){
			int levelNum = que.size();
			List<Integer> subList = new ArrayList<>();
			for(int i=0; i<levelNum; i++) {
				if(que.peek().left != null) que.offer(que.peek().left);
				if(que.peek().right != null) que.offer(que.peek().right);
				
				int vals = que.poll().value;
				
				if(vals > max)
					max = vals;
				
				subList.add(vals);
			}
			
			solution.add(subList);
			System.out.println("Level wise max: "+max);
		}
		
		return solution;
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
        
        List<List<Integer>> abc = new ArrayList<>();
        
        System.out.println("Inorder: ");
        tree.inorder();
        
        System.out.println("Preorder: ");
        tree.preorder();
        
        System.out.println("Postorder: ");
        tree.postorder();
//        System.out.println(tree.search(10));  // Output: true
//        System.out.println(tree.search(7));   // Output: false
        System.out.println("BFS: ");
        tree.bfs();
        
        System.out.println("Deleting: ");
        Node del = tree.deleteNode(root, 170);
        System.out.println(del.value);
        
        System.out.println("Height: ");
        int x = tree.height(root);
        System.out.println(x);
        
        System.out.println("Inorder: ");
        tree.inorder();
        
        System.out.println("Ceil: ");
        int z = tree.findCeil(root, 110);
        System.out.println(z);
        
        System.out.println("Floor: ");
        int w = tree.findFloor(root, 110);
        System.out.println(w);
        
        System.out.println("BFS in arrayList: ");
        abc = tree.levelOrder(root);
        System.out.println(abc);
		
	}
}