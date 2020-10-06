import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VirusTree {
	public TreeNode virusTree;
		
	public class TreeNode {
		public LinkedList<TreeNode> child;
		public String nodeName;
		
		public TreeNode(String s) {
			nodeName = s;
			child = new LinkedList<TreeNode>();
		}
	}
	
	public void insert(String s[]) {
		if(virusTree == null) {
			virusTree = new TreeNode(s[0]);
			for(int i = 1; i < s.length; i++) {
				TreeNode n = new TreeNode(s[i]);
				virusTree.child.add(n);
			}
		}
		else {
			TreeNode n = find(virusTree, s[0]);
			if(n != null) {
				System.out.print(s[0]);
				System.out.print(": ");
				for(int i = 1; i < s.length; i++) {
					TreeNode subNode = new TreeNode(s[i]);
					n.child.add(subNode);
					if ( i > 1) {
						System.out.print(" -> ");
					}
					System.out.print(s[i]);
				}
				System.out.println();
			}
		}
		
	}
	
	public TreeNode find(TreeNode n, String s) {
		if(s.equals(n.nodeName)) {
			return n;
		}
		else if(n.child == null||n.child.size() == 0) {
			return null;
		}
		else {
			for(int i = 0; i < n.child.size(); i++) {
				TreeNode f = find(n.child.get(i), s);
				if( f != null ) {
					return f;
				}
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
		for(int i = 0; i < n.child.size(); i++) {
			printTree(n.child.get(i), level + 1);
		}
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
		h = 0;
		if ( n.child == null || n.child.size() == 0 ) {
			return 0;
		}
		else {
			for(int i = 0; i < n.child.size(); i++) {
				int g = height(n.child.get(i));
				if ( g > h ) {
					h = g;
				}
			}
			return h + 1;
		}
	}
	
	public LinkedList<string> findPath(TreeNode n, String s) {
		if(s.equals(n.nodeName)) {
			LinkedList<string> l = new LinkedList<string>();
			l.add(s);
			return l;
		}
		else if(n.child == null||n.child.size() == 0) {
			return null;
		}
		else {
			for(int i = 0; i < n.child.size(); i++) {
				LinkedList<string> f = findPath(n.child.get(i), s);
				if( f != null ) {
					f.add(s);
					return f;
				}
			}
		}
		return null;		
	}
	
	public int distance(String v1, String v2) {
		LinkedList<string> l1 = findPath(virusTree, v1);
		LinkedList<string> l2 = findPath(virusTree, v2);
		if( l1 != null && l2 != null) {
			while
		}
	}
	
	public int height() {
		return height(virusTree);
	}

	public static void main(String[] args) {
		VirusTree vt = new VirusTree();
		vt.createTree();
		vt.printTree();
		System.out.println(vt.height());
	}
}
