import java.util.LinkedList;
import java.util.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinaryTree {
	public class BinaryNode {
		BinaryNode left;
		BinaryNode right;
		int info;

		BinaryNode(BinaryNode l,int in,BinaryNode r){
			left= l;
			info = in;
			right = r;
		}
	}
	
	public class Node {
		Node next;
		int info;
		
		Node(int in, Node n) {
			info = in;
			next = n;
		}
	}
	
    BinaryNode root;
    int size;

    public void insert(int input){
        size++;
		if (root == null){
			root = new BinaryNode(null,input,null);
		}
		else{
			BinaryNode parent = findNode(size/2);
			BinaryNode child = new BinaryNode(null,input,null);
			if (size%2==0){
				parent.left = child;
			}
			else{
				parent.right = child;
			}
			int index=size/2;
			while(parent!=null&&parent.info>child.info){
				swap(parent,child);
				child = parent;
				index =index/2;
				parent = findNode(index);
			}
		}
    }
	
    public BinaryNode findNode(int i){
        if(i==0){
            return null;
        }
        Node head = null;
        int j=i;
        while(j!=0){
            head = new Node(j%2,head);
            j=j/2;
        }
        BinaryNode returnNode = root;
        head = head.next;
        while(head!=null){
            if (head.info==0){
                returnNode = returnNode.left;
            }
            else{
                returnNode = returnNode.right;
            }
            head = head.next;
        }
        return returnNode;
    }
	
    public void swap(BinaryNode a,BinaryNode b){
        int cur;
        cur = a.info;
        a.info = b.info;
        b.info = cur;
    }
	
	public int deleteMin() {
		if(size == 0) {
			return -1;
		}
		
		int returnValue = root.info;
		root.info = findNode(size).info;
		percolateDown(root);
		
		BinaryNode parent = findNode(size/2);
		if(size%2==0){
			parent.left = null;
		}
		else {
			parent.right = null;
		}
		size--;

		return returnValue;
	}
	
	private void percolateDown(BinaryNode node) {
		BinaryNode parent = node;
		BinaryNode child;
		if(parent.right == null || parent.right.info > parent.left.info) {
			child = parent.left;
		}
		else
		{
			child = parent.right;
		}
		
		while(child != null && child.info < parent.info) {
			swap(parent, child);
			parent = child;
			if(parent.right == null || parent.right.info > parent.left.info) {
				child = parent.left;
			}
			else
			{
				child = parent.right;
			}			
		}
	}
	
    public void traverse(BinaryNode node){
        preOrderTraversal(0,root);
    }
	
    public void preOrderTraversal(int i,BinaryNode node){
        if (node == null) {
            return;
        } 
		else{
            for(int j=0;j<i;j++){
                System.out.print("    ");
            }
            System.out.println(node.info);
            preOrderTraversal(i+1,node.left);
            preOrderTraversal(i+1,node.right);
        }
        return;
    }
	
    public static void bt(){
        BinaryTree bn = new BinaryTree();
        File obj = new File("assn3in.txt");
        Scanner reader;
        int num,input;
        {
            try {
                reader = new Scanner(obj);
                num = reader.nextInt();
                reader.nextLine();
                while (reader.hasNextInt()){
                    input = reader.nextInt();
                    bn.insert(input);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
		
		bn.traverse(bn.root);
		bn.deleteMin();
		bn.traverse(bn.root);
		bn.deleteMin();
		bn.traverse(bn.root);
		bn.deleteMin();
		bn.traverse(bn.root);
		bn.deleteMin();
		bn.traverse(bn.root);
		bn.deleteMin();
    }
    public static void main(String[] args) {
        //ls();
        //ar();
        bt();
    }
}
