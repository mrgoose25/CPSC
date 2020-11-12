import java.util.LinkedList;
import java.util.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayHeap {
	
    private int [] array;
    int size;
	private static final int DEFAULT_CAPACITY = 10;

	public ArrayHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayHeap(int capacity) {
		size = 0;
		array = new int[capacity + 1];
	}
	
	public ArrayHeap(int [] items) {
		size = items.length;
		array = new int[(size + 2)*11/10];
		
		int i = 1;
		for(int item : items) {
			array[i++] = item;
		}
	}
		
    public void insert(int input){
		if(size == array.length - 1) {
			enlargeArray(array.length * 2 + 1);
		}
        size++;
		
		int hole = size;
		for(array[0] = input; input < array[hole/2]; hole /=2) {
			array[hole] = array[hole/2];
		}
		array[hole]=input;
    }
	
	private void enlargeArray(int newSize) {
		int[] old = array;
		array = new int[newSize];
		for(int i = 0; i < old.length; i++) {
			array[i] = old[i];
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int findMin() {
		if(isEmpty()) {
			return 0;
		}
		return array[1];
	}
	
	public int deleteMin() {
		if(isEmpty()) {
			return 0;
		}
		int minItem = findMin();
		array[1]=array[size--];
		percolateDown(1);
		return minItem;
	}

	public void buildHeap() {
		for(int i = size/2; i > 0; i--) {
			percolateDown(i);
		}
	}
	
	public void percolateDown(int hole) {
		int child;
		int temp = array[hole];
		
		for(; hole*2 <= size; hole = child) {
			child = hole*2;
			if(child != size && array[child+1] < array[child]) {
				child++;
			}
			if(array[child] < temp) {
				array[hole] = array[child];
			}
			else {
				break;
			}
		}
		array[hole] = temp;
	}
	
	public void traverse() {
		for(int i = 0; i <=size; i++) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
	
    public static void bt(){
        ArrayHeap bn = new ArrayHeap();
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
		
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
		bn.deleteMin();
		bn.traverse();
    }
    public static void main(String[] args) {
        //ls();
        //ar();
        bt();
    }
}