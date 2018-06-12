import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Assignment2 {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the elements (int) of binary tree by separating space, press enter when done.");
        String s1 = scan.nextLine();
        String[] input = s1.split(" ");
        int[] elements = new int[input.length];
        for(int i=0; i<input.length;i++)
            elements[i]=Integer.parseInt(input[i]);
        // for(int i=0; i<input.length;i++)
        //   System.out.println(elements[i]);
        BinaryNode<Integer> root = new BinaryNode<Integer>(Integer.parseInt(input[0]));
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(root);
        //insert rest of the nodes
        for(int i=1; i<input.length;i++){
            binaryTree.insertNode(root, i,Integer.parseInt(input[i]) );
        }
        System.out.println("Your inserted binary tree printed in-order (left-root-right):");
        binaryTree.printTree(root);

        // Create option: 1- Find a key's ancestors, 2- Find common ancestors of 2 keys
        System.out.println("\nChoose option: 1- Find a key's ancestors, 2- Find common ancestors of 2 keys");
        int option = scan.nextInt();
        scan.nextLine();
        if(option==1){
            // Print Ancestor
            System.out.println("\nEnter a key to find its ancestors");
            int key= scan.nextInt();
            scan.nextLine();
            ArrayList<Integer> ancestorRes = binaryTree.getAncestor(root,key);
            binaryTree.printAncestor(ancestorRes);
        }else if(option==2){
            //Lowest Common Ancestor
            System.out.println("Enter two keys to find its ancestors");
            System.out.println("Enter key1:");
            int key1= scan.nextInt();
            scan.nextLine();
            System.out.println("Enter key2:");
            int key2= scan.nextInt();
            scan.nextLine();
            Optional lowCommon = binaryTree.lowerCommonAncestor(key1,key2);
            System.out.println("Lowest common ancestor is: "+lowCommon);
        }
      }
}
