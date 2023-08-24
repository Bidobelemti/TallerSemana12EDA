package modelo;
//1/08/2023

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Tree {

    private Node root; // first node of tree
    // -------------------------------------------------------------

    public Tree() // constructor
    {
        root = null;
    } // no nodes in tree yet
    //-------------------------------------------------------------

    public Node find(int key) {
        Node current = root;
        while (current.iData != key) {
            if (key < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.iData) {
                    current = current.leftChild;
                    if (current == null) { // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                } else {

                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }
    // -------------------------------------------------------------

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.leftChild == null
                && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        } else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else {

            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }

            successor.leftChild = current.leftChild;
        }

        return true;
    }
    // -------------------------------------------------------------
    // returns node with next-highest value after delNode
    // goes to right child, then right child�s left descendents

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild; // go to right child
        while (current != null) // until no more
        { // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild; // go to left child
        }
        // if successor not
        if (successor != delNode.rightChild) // right child,
        { // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    // -------------------------------------------------------------

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                long tiempoInicialUno = System.nanoTime();
                preOrder(root);
                long tiempoFinalUno = System.nanoTime();
                long duracion = tiempoFinalUno - tiempoInicialUno;
                System.out.println("\nDuracion de: " + duracion + " ns");
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                long tiempoInicialDos = System.nanoTime();
                inOrder(root);
                long tiempoFinalDos = System.nanoTime();
                long duracionDos = tiempoFinalDos - tiempoInicialDos;
                System.out.println("\nDuracion de: " + duracionDos + " ns");
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                long tiempoInicialTres = System.nanoTime();
                postOrder(root);
                long tiempoFinalTres = System.nanoTime();
                long duracionTres = tiempoFinalTres - tiempoInicialTres;
                System.out.println("\nDuracion de: " + duracionTres + " ns");
                break;
            case 4:
                long tiempoInicialCuatro = System.nanoTime();
                levelOrder();
                long tiempoFinalCuatro = System.nanoTime();
                long duracionCuatro = tiempoFinalCuatro - tiempoInicialCuatro;
                System.out.println("\nDuracion de: " + duracionCuatro + " ns");
                break;
        }
        System.out.println();
    }
    //-------------------------------------------------------------

    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
    //-------------------------------------------------------------

    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + "  ");
            inOrder(localRoot.rightChild);
        }
    }
    //-------------------------------------------------------------

    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + "  ");
        }
    }
    //-------------------------------------------------------------

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (isRowEmpty == false) {

            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if (temp.leftChild != null
                            || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(' ');
                }
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        } // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    } // end displayTree()
    //-------------------------------------------------------------

    public void levelOrder() {
        if (root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        System.out.print("Recorrido por niveles: ");
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.iData + " ");

            if (current.leftChild != null) {
                queue.add(current.leftChild);
            }

            if (current.rightChild != null) {
                queue.add(current.rightChild);
            }
        }
        System.out.println();
    }

} // end class Tree
////////////////////////////////////////////////////////////////

