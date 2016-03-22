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
    private int leftKey;
    private int rightKey;
    private TreeNode leftBranch;
    private TreeNode middleBranch;
    private TreeNode rightBranch;

    public TreeNode() {
        leftKey = -1;
        rightKey = -1;
        leftBranch = null;
        rightBranch = null;
        middleBranch = null;
    }
    
    public int getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    public TreeNode getLeftBranch() {
        return leftBranch;
    }

    public void setLeftBranch(TreeNode leftBranch) {
        this.leftBranch = leftBranch;
    }

    public TreeNode getMiddleBranch() {
        return middleBranch;
    }

    public void setMiddleBranch(TreeNode middleBranch) {
        this.middleBranch = middleBranch;
    }

    public TreeNode getRightBranch() {
        return rightBranch;
    }

    public void setRightBranch(TreeNode rightBranch) {
        this.rightBranch = rightBranch;
    }
    
    boolean isLeaf(){
//        if (leftBranch.getLeftBranch() == null && leftBranch.getMiddleBranch() == null && leftBranch.getRightBranch() == null &&
//                middleBranch.getLeftBranch() == null && middleBranch.getMiddleBranch() == null && middleBranch.getRightBranch() == null &&
//                rightBranch.getLeftBranch() == null && rightBranch.getMiddleBranch() == null && rightBranch.getRightBranch() == null){
//            return true;
//        }
        if (leftBranch == null && middleBranch == null && rightBranch == null){
            return true;
        }
        return false;
    }
    
    
    
    
    
    
}
