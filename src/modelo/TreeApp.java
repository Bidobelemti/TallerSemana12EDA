package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TreeApp {

    public static void main(String[] args) throws IOException {
        int value;
        Tree theTree = new Tree();

        theTree.insert(6, 1.5);
        theTree.insert(4, 1.2);
        theTree.insert(8, 1.7);
        theTree.insert(2, 1.5);
        theTree.insert(5, 1.2);
        theTree.insert(7, 1.7);
        theTree.insert(9, 1.5);
        theTree.insert(10, 1.5);
        theTree.insert(11, 1.2);
        theTree.insert(1, 1.2);
        theTree.insert(3, 1.7);

        while (true) {
            System.out.print("Ingrese la primera letra de show, ");
            System.out.print("insert, find, delete, or traverse: ");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("\nIngrese el valor a insertar: ");
                    value = getInt();
                    theTree.insert(value, value + 0.9);
                    break;
                case 'f':
                    System.out.print("\nIngrese el valor a buscar: ");
                    value = getInt();
                    Node found = theTree.find(value);
                    if (found != null) {
                        System.out.print("\nEncontrado: ");
                        found.displayNode();
                        System.out.print("\n");
                    } else {
                        System.out.print("\nNo se pudo encontrar");
                    }
                    System.out.print(value + '\n');
                    break;
                case 'd':
                    System.out.print("Ingrese el valor a eliminar: ");
                    value = getInt();
                    boolean didDelete = theTree.delete(value);
                    if (didDelete) {
                        System.out.print("Eliminado " + value + '\n');
                    } else {
                        System.out.print("No se pudo eliminar ");
                    }
                    System.out.print(value + '\n');
                    break;
                case 't':
                    System.out.println("1->Preorder\n2->Inorder\n3->Postorder\n4->Level order");
                    System.out.print("Ingrese el tipo 1, 2, 3 or 4: ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Entrada invalida\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
