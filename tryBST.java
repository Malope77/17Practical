//Start binary search tree project

import java.util.*;

public class binarySearch {

    static class BinarySearchTree {
        
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(); 
    }
}
        class nNode {
            int data;
            nNode left;
            nNode right;

            public nNode(int data) {
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }

        class BinarySearchTree {
            private nNode root;
            private int nodeCount;

            public BinarySearchTree() {
                root = null;
                nodeCount = 0;
            }
            
            public void insert(int value) {
                root = insertRec(root, value);
                nodeCount++;
            }

            private nNode insertRec(nNode node, int value) {
                if (node == null) {
                    return new nNode(value);
                }

                if (value < node.data) {
                    node.left = insertRec(node.left, value);
                } else if (value > node.data) {
                    node.right = insertRec(node.right, value);
                }
                
                return node;
            }

            
            public boolean isBST() {
                return isBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }

            private boolean isBSTRec(nNode node, int min, int max) {
                if (node == null) {
                    return true;
                }

                if (node.data < min || node.data > max) {
                    return false;
                }

                return isBSTRec(node.left, min, node.data - 1) &&
                        isBSTRec(node.right, node.data + 1, max);
            }
            
            public void removeEvens() {
                root = removeEvensRec(root);
            }

            private nNode removeEvensRec(nNode node) {
                if (node == null) {
                    return null;
                }
