/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbjava;

import java.util.Scanner;

/**
 *
 * @author RickAg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        Tree tree = new Tree(new TreeNode());
        do {
            opcion = menu();

            if(opcion == 1){
                System.out.println("Que llave desea agregar?");
                int llave = sc.nextInt();
                tree.insert(llave, tree.getRoot());
                System.out.println("Llave agregada");

            }else if (opcion ==2 ){
                tree.print(tree.getRoot(), 1);

            }else if (opcion ==3 ){
                tree.saveTree();
                System.out.println("Arbol guardado");

            }else if (opcion ==4){
                tree.loadTree();
                System.out.println("Arbol cargado");
            }
        }while (opcion != 5);
    }
    
    public static int menu(){
        Scanner sc = new Scanner(System.in);
        int seleccion = -1;
        while (seleccion > 5 || seleccion < 1){
            System.out.println("");
            System.out.println("1) Agregar en el arbol");
            System.out.println("2) Imprimir Arbol");
            System.out.println("3) Salvar Arbol");
            System.out.println("4) Cargar arbol");
            System.out.println("5) Salir");
            seleccion = sc.nextInt();
        }
        return seleccion;
    }
    
}
