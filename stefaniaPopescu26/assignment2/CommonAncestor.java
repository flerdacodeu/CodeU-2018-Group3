/*
    My code will return null for LCA of nodes that don't exist.
 */

import java.util.Scanner;

public class CommonAncestor<T> extends Tree<T> {

    public boolean isCommonAncestor(Node<T> node, T data1, T data2) {
        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();

        if (left != null) {
            if (node.getData() == data1)
                if (findNode(left, data2) != null)
                    return true;
            if (node.getData() == data2)
                if (findNode(left, data1) != null)
                    return true;
        }

        if (right != null) {
            if (node.getData() == data1)
                if (findNode(right, data2) != null)
                    return true;
            if (node.getData() == data2)
                if (findNode(right, data1) != null)
                    return true;
        }

        if (left != null && right != null) {
            if (findNode(left, data1) != null && findNode(right, data2) != null)
                return true;

            if (findNode(left, data2) != null && findNode(right, data1) != null)
                return true;
        }

        return false;
    }

    private T LCA(Node<T> start, T data1, T data2) {
        T LCAleft = null, LCAright = null;
        Node<T> left = start.getLeft();
        Node<T> right = start.getRight();

        if (isCommonAncestor(start, data1, data2))
            return start.getData();

        if (left != null)
            LCAleft = LCA(left, data1, data2);

        if (LCAleft != null)
            return LCAleft;

        if (right != null)
            LCAright = LCA(right, data1, data2);

        return LCAright;
    }

    public T getCommonAncestor(T data1, T data2) {
        if (findNode(getRoot(), data1) == null || findNode(getRoot(), data2) == null)
            return null;

        return LCA(getRoot(), data1, data2);
    }

    public static void main(String[] args) {
        CommonAncestor<Integer> tree = new CommonAncestor<>();

        tree.add(null, 0, 7);
        tree.add(7, 0, 3);
        tree.add(7, 1, 4);
        tree.add(3, 0, 2);
        tree.add(4, 1, 8);
        tree.add(3, 1, 5);
        tree.add(2, 0, 1);
        tree.add(2, 1, 6);

        Scanner in = new Scanner(System.in);
        Integer data1, data2;
        data1 = in.nextInt();
        data2 = in.nextInt();

        System.out.println(tree.getCommonAncestor(data1, data2));
    }
}
