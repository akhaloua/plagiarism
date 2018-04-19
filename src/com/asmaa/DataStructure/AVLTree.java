package com.asmaa.DataStructure;

/**
 * this class is the implementation of the avl tree with the insertion method
 * which allow to add new node to the tree
 */
public class AVLTree<TYPE extends Comparable<? super TYPE>> {

	private Node<TYPE> root;

	public Node<TYPE> getRoot() {
		return root;
	}

	public void setRoot(Node<TYPE> root) {
		this.root = root;
	}

	// this method return the height of the current node
	public int height(Node<TYPE> node) {
		if (node == null)
			return 0;

		return node.getHeight();
	}

	// this method return the max value of two numbers ( two heights)
	public int max(int n1, int n2) {
		return Math.max(n1, n2);
		// return (n1 > n2) ? n1 : n2;
	}

	// this method rotate to right the unbalanced node
	public Node<TYPE> rightRotate(Node<TYPE> current) {
		Node<TYPE> temp = current.getLeft();
		Node<TYPE> T2 = temp.getRight();

		temp.setRight(current);
		current.setLeft(T2);

		// update the height after rotation
		current.setHeight(max(height(current.getLeft()), height(current.getRight())) + 1);
		temp.setHeight(max(height(temp.getLeft()), height(temp.getRight())) + 1);

		return temp;
	}

	// this method rotate to left the unbalanced node
	public Node<TYPE> leftRotate(Node<TYPE> current) {
		Node<TYPE> temp = current.getRight();
		Node<TYPE> T2 = temp.getLeft();

		temp.setLeft(current);
		current.setRight(T2);

		// update the height after rotation
		current.setHeight(max(height(current.getLeft()), height(current.getRight())) + 1);
		temp.setHeight(max(height(temp.getLeft()), height(temp.getRight())) + 1);

		return temp;
	}

	public Node<TYPE> insert(Node<TYPE> current, TYPE value) {

		// insert the new value
		if (current == null)
			return (new Node<TYPE>(value));

		if (value.compareTo(current.getValue()) < 0)
			current.setLeft(insert(current.getLeft(), value));
		else if (value.compareTo(current.getValue()) > 0)
			current.setRight(insert(current.getRight(), value));
		else // leave if the node already exist
			return current;

		// update height
		current.setHeight(1 + max(height(current.getLeft()), height(current.getRight())));

		// check the balance after adding the new node to check if the tree become
		// unbalanced(less than -1 or greater than 1)
		int balance = getBalance(current);

		// if the balance more than 1 then process right rotation
		if (balance > 1 && value.compareTo(current.getLeft().getValue()) < 0)
			return rightRotate(current);

		// if the balance is less than -1 ,then process left rotation
		if (balance < -1 && value.compareTo(current.getRight().getValue()) > 0)
			return leftRotate(current);

		// LR rotation
		if (balance > 1 && value.compareTo(current.getLeft().getValue()) > 0) {
			current.setLeft(leftRotate(current.getLeft()));
			return rightRotate(current);
		}

		// RL rotation
		if (balance < -1 && value.compareTo(current.getRight().getValue()) < 0) {
			current.setRight(rightRotate(current.getRight()));
			return leftRotate(current);
		}

		return current;
	}

	// this method return the balance of the curent node
	public int getBalance(Node<TYPE> node) {
		if (node == null)
			return 0;

		return height(node.getLeft()) - height(node.getRight());
	}

	public Node<TYPE> find(AVLTree<TYPE> tree, TYPE v) {
		Node<TYPE> current = tree.root;
		while (current != null && (current.getValue().compareTo(v) > 0 || current.getValue().compareTo(v) < 0)) {

			if (v.compareTo(current.getValue()) > 0)
				current = current.getRight();
			else
				current = current.getLeft();
		}

		return current;
	}

}