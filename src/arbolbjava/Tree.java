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
    
    public void insert(int key, TreeNode Node){
        if (Node.getFather() == null && Node.isLeaf()){
            if (!Node.isFull()){
                if (Node.leftEmpty() && Node.rightEmpty()){
                    Node.setLeftKey(key);
                }else if (!Node.leftEmpty() && Node.rightEmpty()){
                    Node.setRightKey(key);
                }
                order(Node);
            }else{
                int key1 = Node.getLeftKey();
                int key2 = Node.getRightKey();
                int key3 = key;
                TreeNode leftTemp, rightTemp;
                
                if (key3 < key1){
                    
                    leftTemp = new TreeNode(key3,Node);
                    rightTemp = new TreeNode(key2,Node);
                    Node = new TreeNode(key1);
                    leftTemp.setFather(Node);
                    rightTemp.setFather(Node);
                    Node.setLeftBranch(leftTemp);
                    Node.setRightBranch(rightTemp);             
                    
                }else if (key3 > key2){
                    
                    leftTemp = new TreeNode(key1,Node);
                    rightTemp = new TreeNode(key,Node);
                    Node = new TreeNode(key2);
                    leftTemp.setFather(Node);
                    rightTemp.setFather(Node);
                    Node.setLeftBranch(leftTemp);
                    Node.setRightBranch(rightTemp);
                }else {
                    
                    leftTemp = new TreeNode(key1,Node);
                    rightTemp = new TreeNode(key2,Node);
                    Node = new TreeNode(key);
                    leftTemp.setFather(Node);
                    rightTemp.setFather(Node);
                    Node.setLeftBranch(leftTemp);
                    Node.setRightBranch(rightTemp);
                }
               
            }
        }else if (Node.getFather() == null && !Node.isLeaf()){
            int leftKey = Node.getLeftKey();
            int rightKey = Node.getRightKey();
            
            if(key < leftKey){
                insert(key,Node.getLeftBranch());
                
            }else if (key > leftKey && key< rightKey){
                insert(key,Node.getMiddleBranch());
                
            }else if (key  > rightKey && Node.getRightBranch() != null){
                insert(key,Node.getRightBranch());
                                                
            }else if (key > rightKey && Node.getRightBranch() == null){
                insert(key,Node.getMiddleBranch());
            }
            
            
        }else{
            if (Node.isLeaf()){
                
            }
        }
        
    }
    
    public void order(TreeNode node){
        int left = node.getLeftKey();
        int right = node.getRightKey();
        if (left > right){
            node.setLeftKey(right);
            node.setRightKey(left);
        }
    }
 
}
