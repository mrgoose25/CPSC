import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VirusTree {
	public TreeNode virusTree;
		
	public class TreeNode {
		public TreeNode firstChild;
		public TreeNode nextSibling;
		public String nodeName;
		
		public TreeNode(String s) {
			nodeName = s;
		}
	}
	
	public void insert(String s[]) {
		TreeNode node = null;
		if(virusTree == null) {
			virusTree = new TreeNode(s[0]);
			node = virusTree;
		}
		else {
			node = find(virusTree, s[0]);
		}
		if(node != null) {
			int c = 1;
			if(node.firstChild == null) {
				node.firstChild = new TreeNode(s[c++]);
			}
			TreeNode siblingPtr = node.firstChild;
			TreeNode ptr = siblingPtr.nextSibling;
			while(ptr != null) {
				siblingPtr = ptr;
				ptr = ptr.nextSibling;
			}
			while(c < s.length) {
				siblingPtr.nextSibling = new TreeNode(s[c++]);
				siblingPtr = siblingPtr.nextSibling;
			}
			printNode(node);
		}
	}
	
	public TreeNode find(TreeNode n, String s) {
		if(s.equals(n.nodeName)) {
			return n;
		}
		if(n.firstChild != null) {
			TreeNode c = find(n.firstChild, s);
			if(c != null) {
				return c;
			}
		}
		if(n.nextSibling != null) {
			TreeNode d = find(n.nextSibling, s);
			if(d != null) {
				return d;
			}
		}
		return null;
	}
	
	public void printTree() {
		printTree(virusTree, 0);
	}
	
	public void printTree(TreeNode n, int level) {
		for(int t = 0; t < level; t++) {
			System.out.print("          ");
		}
		System.out.println(n.nodeName);
		if(n.firstChild != null) {
			printTree(n.firstChild, level + 1);
		}
		if(n.nextSibling != null) {
			printTree(n.nextSibling, level);
		}
	}
	
	public void printNode(TreeNode n) {
		System.out.print(n.nodeName);System.out.print(": ");
		if(n.firstChild != null) {
			System.out.print(n.firstChild.nodeName);
			TreeNode ptr = n.firstChild.nextSibling;
			while(ptr != null) {
				System.out.print(" -> ");
				System.out.print(ptr.nodeName);
				ptr = ptr.nextSibling;
			}
		}
		System.out.println();
	}

	public void createTree () {
		try {
			Scanner scanner = new Scanner(new File("tree_of_virus_input.txt"));
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				insert(s.split(","));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int height(TreeNode n) {
		int h;
		int g;
		h = 0;
		g = 0;
		if(n.firstChild != null) {
			h = height(n.firstChild) + 1;
		}
		if(n.nextSibling != null) {
			g = height(n.nextSibling);
		}
		if(h > g) {
			return h;
		}
		return g;
	}
	
	public int height() {
		return height(virusTree);
	}
	
	public void breadthFirstTraversal(TreeNode node) {
		if(node == null) {
			return;
		}	
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(node);
		while(!queue.isEmpty()) {
			TreeNode n = queue.remove();
			System.out.println(n.nodeName);
			if(n.firstChild != null) {
				queue.add(n.firstChild);				
			}
			TreeNode ptr = n.nextSibling;
			while(ptr != null) {
				System.out.println(ptr.nodeName);
				if(ptr.firstChild != null) {
					queue.add(ptr.firstChild);				
				}
				ptr = ptr.nextSibling;
			}
		}
	}
	
	public void preOrderTraversal(TreeNode node) {
		if(node == null) {
			return;
		}
		System.out.println(node.nodeName);
		preOrderTraversal(node.firstChild);
		preOrderTraversal(node.nextSibling);
	}
	
	public void postOrderTraversal(TreeNode node) {
		if(node == null) {
			return;
		}
		postOrderTraversal(node.firstChild);
		System.out.println(node.nodeName);
		TreeNode ptr = node.nextSibling;
		while(ptr != null) {
			postOrderTraversal(ptr.firstChild);
			System.out.println(ptr.nodeName);
			ptr = ptr.nextSibling;
		}
	}
	
	public TreeNode findPath(TreeNode n, String s, LinkedList<String> path) {
		if(s.equals(n.nodeName)) {
			path.add(s);
			return n;
		}
		if(n.firstChild != null) {
			TreeNode c = findPath(n.firstChild, s, path);
			if(c != null) {
				path.add(n.nodeName);
				return c;
			}
		}
		if(n.nextSibling != null) {
			TreeNode d = findPath(n.nextSibling, s, path);
			if(d != null) {
				return d;
			}
		}
		return null;
	}
	
	public void distance(String s1, String s2) {
		LinkedList<String> l1 = new LinkedList<String>();
		LinkedList<String> l2 = new LinkedList<String>();
		findPath(virusTree, s1, l1);
		findPath(virusTree, s2, l2);
		int d = l1.size() - 1;
		while(l1.get(d).equals(l2.get(d))) {
			d--;
		}
		System.out.print("The distance between ");
		System.out.print(s1);
		System.out.print(" and ");
		System.out.print(s2);
		System.out.print(" is ");
		System.out.print(d + 1);
		System.out.print(". They have common ancestor ");
		System.out.print(l1.get(d + 1));
		System.out.println(".");
	}

	public static void main(String[] args) {
		VirusTree vt = new VirusTree();
		vt.createTree();
		vt.printTree();
		System.out.print("Height: ");System.out.println(vt.height());
		System.out.println("--------PreOrder--------");
		vt.preOrderTraversal(vt.virusTree);
		System.out.println("--------PostOrder--------");
		vt.postOrderTraversal(vt.virusTree);
		System.out.println("--------BreadthFirst--------");
		vt.breadthFirstTraversal(vt.virusTree);
		System.out.println("--------Distance--------");
		vt.distance("Ebola virus", "Bombali virus");
		vt.distance("Ebola virus", "Marburg virus");
		vt.distance("HCoV-OC43", "Hcov-229E");
		vt.distance("SARS-CoV", "Zika virus");
	}
}
