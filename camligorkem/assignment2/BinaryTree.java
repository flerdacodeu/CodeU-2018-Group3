import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class BinaryTree<T> {
    private BinaryNode<T> root;

    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    public BinaryNode<T> getRoot() {
        return root;
    }
    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    /**
     * Insert new nodes to the tree, assumes root is NOT null
     *  root should be set before it is used.
     * @param root user should pass the root of the tree
     * @param index to find the place to be inserted
     * @param data new value to be added into tree
     */
    public void insertNode(BinaryNode<T> root, int index, T data){
        BinaryNode<T> newNode = new BinaryNode<T>(data);
        //check current node is empty
        if(root == null) {
            return;
        }
        int parentIndex = (index-1)/2;
        if(parentIndex == 0){
            if(root.getLeft()== null){
                root.setLeft(newNode);
            }else if(root.getRight() == null){
                root.setRight(newNode);
            }
        }else{
            BinaryNode<T> child;
            if(parentIndex%2 ==1){
                child= root.getLeft();
            }else{
                child = root.getRight();
            }
            insertNode(child, parentIndex, data);
        }
    }

    /**
     * Prints the binary tree in order (left- root- right)
     * @param node prints the nodes including itself and its subtrees.
     * If you pass root prints all the tree.
     */
    public void printTree(BinaryNode<T> node){
        if(node != null){
            printTree(node.getLeft());
            System.out.print(node.getData()+ "\t");
            printTree(node.getRight());
        }
    }

    /**
     * Insert new nodes to the tree assumes root is NOT null
     *  root should be set before it is used.
     *  Remark: When there is duplicate element returns the ancestor of the first found element
     * @param root user should pass the root of the tree
     * @param key the value of the node which its ancestors want to be found
     * @return an array list of T which are ancestor values of the node, returns empty list if ancestor
     * is the root, returns null if the tree is empty or node doesn't exist.
     */
    //Assumes unique value for each element
    public ArrayList<T> getAncestor (BinaryNode<T> root,  T key){
        if(root == null)
            return null;
        if(root.getData().equals(key)){    // key found - previous node is its ancestor
            ArrayList<T> ancestorList = new ArrayList<>();
            return ancestorList;
        }
        ArrayList<T> ancestorsL = getAncestor(root.getLeft(), key);
        if(ancestorsL!=null){  // key is in the left subtree
                ancestorsL.add(root.getData());
                return ancestorsL;
        }
        ArrayList<T> ancestorsR = getAncestor(root.getRight(), key);
        if(ancestorsR!=null){     // key is in the right subtree
                ancestorsR.add(root.getData());
                return ancestorsR;
        }
        return null;
    }

    /**
     * Prints the ancestor list passed as parameter.
     * If list is empty or null instead of list prints out a warning.
     * @param list takes a ArrayList of generic type T
     */
    public void printAncestor(ArrayList<T> list){
        if(list == null){
            System.out.println("No ancestor found! Key doesn't exist");
            return;
        }
        if(list.isEmpty()){
            System.out.println("No ancestor found! Key is a root.");
            return;
        }
        for(T element:list){
            System.out.print(element+"\t");
        }
        System.out.println();
    }

    /**
     * Prints the lowest common ancester of 2 keys
     * Remark: if one key is ancestor of another case (i.e. 2,6 in assignment):
     *     Ancestors of 2 = 3, 7 and ancestors of 6 = 2,3,7 and  common ancestors are 3,7;
     *     Lowest common ancestor than should be 3.
     * Remark 2: Since in getAncestor function when a duplicate exists, it returns the ancestor result of first one found,
     * lowestCommonAncestor function for same values doesn't make sense to use for duplicates because it won't be between the duplicate
     * nodes but the first found node with itself.
     * @param key1
     * @param key2
     * @return the lowest common ancestor
     */
    public Optional<T> lowestCommonAncestor(T key1, T key2){
        ArrayList<T> key1Ancestors = getAncestor(root,key1);
        if(key1Ancestors == null){
            return Optional.empty();
        }
        ArrayList<T> key2Ancestors = getAncestor(root,key2);
        //no common ancestors -> one of the keys doesn't exist
        if(key2Ancestors == null){
            return Optional.empty();
        }
        // returns empty optional one of the keys is the root - no common ancestor since one has no ancestor
        if(key1Ancestors.size() == 0 || key2Ancestors.size()==0)
            return Optional.empty();
        T lowestComAnc = null;
        //reverse the lists and compare ancestor from key1 and key2 at the same index
        // add them to commonAncestor list until you found one that doesn't match.
        Collections.reverse(key1Ancestors);
        Collections.reverse(key2Ancestors);
        int size = key1Ancestors.size() <= key2Ancestors.size() ? key1Ancestors.size() : key2Ancestors.size();
        
        int i;
        for (i=0; i<size;i++){
            if( !(key1Ancestors.get(i).equals(key2Ancestors.get(i)))){
                if(i >0) {
                    lowestComAnc = key1Ancestors.get(i - 1);
                    break;
                }
                // else i=0 there is no common ancestor so result is empty.
            }
        }
        //if we don't enter the break & loop finished one of the keys is ancestor of the other
        // take the last element looped as common ancestor
        if(i==size)
            lowestComAnc = key1Ancestors.get(size-1);
        if(lowestComAnc == null)
            return Optional.empty();
        return Optional.of(lowestComAnc);
    }
}
