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
public class Tree {
    private TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    private void insert(int key, TreeNode Node){
        if (Node.isLeaf() && !Node.isFull()){
            if(Node.leftEmpty()){
                Node.setLeftKey(key);
            }else if(!Node.leftEmpty() && Node.rightEmpty()){
                Node.setRightKey(key);
            }
        }else{
            
        }
    }
    
    
}
