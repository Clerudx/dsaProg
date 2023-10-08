package dsaLinked;

public class Node {
	int seat = 0;
	boolean reservation = false;
	Node next;
	Node prev;

	public Node(int data) {
		seat = data;
		next = null;
		prev = null;
	}
}
