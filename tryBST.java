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
                                node.left = removeEvensRec(node.left);
                node.right = removeEvensRec(node.right);

                // If current node's data is even, remove it
                if (node.data % 2 == 0) {
                    return deleteNode(node);
                }

                return node;
            }

            private nNode deleteNode(nNode node) {
                // Case 1: No children
                if (node.left == null && node.right == null) {
                    return null;
                }

                // Case 2: One child
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                nNode successor = findMin(node.right);
                node.data = successor.data;
                node.right = deleteNodeRec(node.right, successor.data);

                return node;

            }

            private nNode deleteNodeRec(nNode node, int value) {
                if (node == null ) {
                    return null;
                }

                if ( value < node.data) {
                    node.left = deleteNodeRec(node.left, value );
                } else if (value > node.data) {
                    node.right = deleteNodeRec(node.right, value);
                } else {
                    return deleteNode(node);
                }

                return node;
            }

            private nNode findMin(nNode node) {
                while (node.left != null) {
                    node = node.left;
                }
                return node;
            }

            public int getNodeCount() {
                return nodeCount;
            }

        
            public void clear() {
                root = null;
                nodeCount = 0;
            }

            // Build balanced BST by inserting middle element recursively
            public static void buildBalancedBST(BinarySearchTree bst, int start, int end) {
                if (start > end) {
                    return;
                }

                int middle = start + (end - start) / 2;
                bst.insert(middle);

                buildBalancedBST(bst, start, middle - 1);
                buildBalancedBST(bst, middle + 1, end);
            }
            public static List<Integer> generateNumbers(int n) {
                List<Integer> numbers = new ArrayList<>();
                int max = (int) Math.pow(2, n) - 1;
                for (int i = 1; i <= max; i++) {
                    numbers.add(i);
                }
                return numbers;
            }

            // Calculate standard deviation
            public static double calculateStdDev(List<Long> times, double mean) {
                double sum = 0.0;
                for (long time : times) {
                    sum += Math.pow(time - mean, 2);
                }
                return Math.sqrt(sum / times.size());
            }
             public static void runExperiments(int n, int repetitions) {
                BinarySearchTree bst = new BinarySearchTree();
                List<Long> populateTimes = new ArrayList<>();
                List<Long> removeTimes = new ArrayList<>();

                System.out.println("Running experiments with n = " + n + " (total nodes: " + ((int) Math.pow(2, n) - 1) + ")");
                System.out.println("Repetitions: " + repetitions);
                System.out.println();

                for (int i = 0; i < repetitions; i++) {
                    // Time population
                    long startPopulate = System.nanoTime();
                    buildBalancedBST(bst, 1, (int) Math.pow(2, n) - 1);
                    long endPopulate = System.nanoTime();
                    long populateTime = (endPopulate - startPopulate) / 1_000_000; // Convert to milliseconds
                    populateTimes.add(populateTime);

                    // Verify BST property
                    if (!bst.isBST()) {
                        System.out.println("Warning: Tree is not a valid BST at iteration " + (i + 1));
                    }

                    // Time removal of even numbers
                    long startRemove = System.nanoTime();
                    bst.removeEvens();
                    long endRemove = System.nanoTime();
                    long removeTime = (endRemove - startRemove) / 1_000_000; // Convert to milliseconds
                    removeTimes.add(removeTime);

                    // Clear tree for next iteration
                    bst.clear();

                    // Progress indicator
                    if ((i + 1) % 10 == 0) {
                        System.out.println("Completed " + (i + 1) + " iterations...");
                    }
                }
    
                
                
