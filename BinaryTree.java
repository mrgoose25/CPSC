import java.util.LinkedList;
import java.util.Queue;
public class BinaryTree {
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
    public void traverse(BinaryNode node){
        if (node == null){
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(node);
        while(!queue.isEmpty()) {
            BinaryNode n = queue.remove();
            System.out.println(n.info);
            if (n.left != null) {
                queue.add(n.left);
            }
            BinaryNode pointer = n.right;
            while (pointer != null) {
                System.out.println(pointer.info);
                if (pointer.left != null) {
                    queue.add(pointer.left);
                }
                pointer = pointer.right;
            }
        }
        System.out.println("---------");
        preOrderTraversal(0,root);
    }
    public void preOrderTraversal(int i,BinaryNode node){
        if (node == null) {
            return;
        } else {
            for(int j=0;j<i;j++){
                System.out.print("    ");
            }
            System.out.println(node.info);
            preOrderTraversal(i+1,node.left);
            preOrderTraversal(i+1,node.right);
        }
        return;
    }
}
