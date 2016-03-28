/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbjava;

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
        
        Tree arbol = new Tree(new TreeNode(10));
        arbol.insert(20, arbol.getRoot());
        arbol.insert(30, arbol.getRoot());
        arbol.insert(40, arbol.getRoot());
        arbol.insert(50, arbol.getRoot());
        
        arbol.print(arbol.getRoot(), 1);
    }
    
}
