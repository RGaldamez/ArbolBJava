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
public class TreeNode {
    private int key;
    private TreeNode leftNode = null;
    private TreeNode middleNode = null;
    private TreeNode rightNode = null;

    public TreeNode(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getMiddleNode() {
        return middleNode;
    }

    public void setMiddleNode(TreeNode middleNode) {
        this.middleNode = middleNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
    
    boolean isEmpty(){
        if (leftNode== null && middleNode == null && rightNode == null){
            return true;
        }
        return false;
    }
    
    
    
    
    
    
}
