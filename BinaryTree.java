class BinaryTree {
	BinaryNode root;
	public BinaryTree(){
		root = null;
	}
	public boolean isEmpty(){
		return root == null;
	}
//	public int countLeaves(BinaryNode root){
//		if(root == null){
//			return 0;
//		}
//		else if(root.left == null && root.right == null){
//			return 1;
//		}
//		else{
//			return countLeaves(root.left) + countLeaves(root.right);
//		}
//	}
	
//	public int sumOfLeafDepths(BinaryNode root, int depth){
//		if(root == null){
//			return 0;
//		}
//		else if(root.left == null && root.right == null){
//			return depth;
//		}
//		else{
//			return sumOfLeafDepths(root.left, depth + 1) + sumOfLeafDepths(root.right, depth + 1);
//		}
//	}
	
	public int findMaximumLeafDepth(BinaryNode root, int depth){
		if(root == null){
			return 0;
		}
		else if(root.left == null && root.right == null){
			return depth;
		}
		else{
			int leftMax = findMaximumLeafDepth(root.left, depth + 1);
			int rightMax = findMaximumLeafDepth(root.right, depth + 1);
			return Math.max(leftMax, rightMax);
		}
	}
	
	public static int findDepth(BinaryNode node, BinaryNode root){
		BinaryNode current = root;
		double number = node.element;
		int depth = 0;
		while(current != null){
			if(number > current.element){
				current = current.right;
				depth++;
			}
			else if(number < current.element){
				current = current.left;
				depth++;
			}
			else{
				break;
			}
		}
		return depth;
	}
	
//	public BinaryNode search(int number){
//		BinaryNode current = root;
//		while(current != null){
//			if(number > current.element){
//				current = current.right;
//			}
//			else if(number < current.element){
//				current = current.left;
//			}
//			else{
//				break; // found
//			}
//		}
//		return current;
//	}
	public void addNode(double d) {
		BinaryNode newNode = new BinaryNode(d);
		if (root == null) {
			setRoot(newNode);
		} else {
			BinaryNode parent = root;
			BinaryNode position = root;
			while(position != null){
				if(d < position.element){
					parent = position;
					position = position.left;
				}
				else if(d > position.element){
					parent = position;
					position = position.right;
				}
				else{
					break; //no duplicates
				}
			}
			if(d < parent.element){
				parent.left = newNode;
			}
			else if(d > parent.element){
				parent.right = newNode;
			}
			else{
				;
			}
		}
	}
	public void setRoot(BinaryNode newNode) {
		root = newNode;
	}
	int sum = 0;
	public int sumDepthOfAllNodes(BinaryNode root){
		BinaryNode current = root;
		if(current != null){
			sumDepthOfAllNodes(root.left);
			sum += findDepth(root, this.root);
			sumDepthOfAllNodes(root.right);
		}
		return sum;
	}
	public void printTree(){
		if(isEmpty()){
			System.out.println("Empty tree.");
		}
		else{
			printTree(root);
		}
	}
	public void printTree(BinaryNode root){
		if(root != null){
			printTree(root.left);
			System.out.println(root.element);
			printTree(root.right);
		}
	}
	public BinaryTree getSubTree(BinaryNode node) {
		BinaryTree t1 = new BinaryTree();
		if (node == null) {
			;
		} else {
			t1.addNode(node.element);
			if (node.left != null) {
				BinaryTree t2 = getSubTree(node.left);
				t1.addNode(t2.root.element);
			}
			if (node.right != null) {
				BinaryTree t3 = getSubTree(node.right);
				t1.addNode(t3.root.element);
			}
		}
		return t1;
	}
	public int countNodes(BinaryNode root){
		int counter = 1;
		if(root == null){
			return 0;
		}
		BinaryTree right = getSubTree(root.right);
		BinaryTree left = getSubTree(root.left);
		if(right != null){
			counter += countNodes(root.right);
		}
		if(left != null){
			counter += countNodes(root.left);
		}
		return counter;
	}
	
	public static void main(String[] args){
		BinaryTree t1 = new BinaryTree();
		for(int i = 0; i < 512; i++){
			t1.addNode(Math.random());
		}
		int nodeCount = t1.countNodes(t1.root);
		int maxDepth = t1.findMaximumLeafDepth(t1.root, 0);
		int sumDepth = t1.sumDepthOfAllNodes(t1.root);
		double averageDepthOfAllNodes = ((double) sumDepth / nodeCount);
		System.out.println("Sum of depths of all nodes is: " + sumDepth);
		System.out.println("Average depth of all nodes is: " + averageDepthOfAllNodes);
		System.out.println("Number of nodes is: " + nodeCount);
		System.out.println("MaxDepth of tree is: " + maxDepth);
	}
}
class BinaryNode{
	double element;
	BinaryNode left;
	BinaryNode right;
	public BinaryNode(double d){
		element = d;
	}
}