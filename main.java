
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class main {
    public static void ls(){
        File obj = new File("assn3in.txt");
        Scanner reader;
        int num,input;
        linkList  L = new linkList();
        {
            try {
                reader = new Scanner(obj);
                num = reader.nextInt();
                reader.nextLine();
                while (reader.hasNextInt()){
                    input = reader.nextInt();
                    L.insert(input);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ar(){


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
                bn.traverse(bn.root);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        //ls();
        //ar();
        bt();
    }
}
