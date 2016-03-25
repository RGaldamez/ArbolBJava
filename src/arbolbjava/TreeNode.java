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
    private TreeNode father;

    public TreeNode() {
        leftKey = -1;
        rightKey = -1;
        leftBranch = null;
        rightBranch = null;
        middleBranch = null;
        father = null;
    }

    public TreeNode(TreeNode father) {
        leftKey = -1;
        rightKey = -1;
        leftBranch = null;
        rightBranch = null;
        middleBranch = null;
        father = father;
    }

    public TreeNode(int leftKey, int rightKey, TreeNode father) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.father = father;
        leftBranch = null;
        rightBranch = null;
        middleBranch = null;
    }

    public TreeNode(int leftKey, TreeNode father) {
        this.leftKey = leftKey;
        this.rightKey = -1;
        this.father = father;
        leftBranch = null;
        rightBranch = null;
        middleBranch = null;
    }

    public TreeNode(int leftKey) {
        this.leftKey = leftKey;
        rightKey = -1;
        leftBranch = null;
        rightBranch = null;
        middleBranch = null;
        father = null;
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

    public TreeNode getFather() {
        return father;
    }

    public void setFather(TreeNode father) {
        this.father = father;
    }

    boolean isLeaf() {
//        if (leftBranch.getLeftBranch() == null && leftBranch.getMiddleBranch() == null && leftBranch.getRightBranch() == null &&
//                middleBranch.getLeftBranch() == null && middleBranch.getMiddleBranch() == null && middleBranch.getRightBranch() == null &&
//                rightBranch.getLeftBranch() == null && rightBranch.getMiddleBranch() == null && rightBranch.getRightBranch() == null){
//            return true;
//        }
        if (leftBranch == null && middleBranch == null && rightBranch == null) {
            return true;
        }
        return false;
    }

    boolean isFull() {
        if (leftKey != -1 && rightKey != -1) {
            return true;
        }
        return false;
    }

    boolean leftEmpty() {
        if (leftKey != -1) {
            return false;
        }
        return true;
    }

    boolean rightEmpty() {
        if (rightKey != -1) {
            return false;
        }
        return true;
    }

}
