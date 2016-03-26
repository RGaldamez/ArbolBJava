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
            if (Node.isLeaf() && !Node.isFull()){
                Node.setRightKey(key);
                order(Node);
            }else if(!Node.isLeaf()){
                if (Node.rightEmpty()){
                    if (key < Node.getLeftKey()){
                        insert(key, Node.getLeftBranch());
                    }else{
                        insert(key, Node.getMiddleBranch());
                    }
                }else{
                    if (key < Node.getLeftKey()){
                        insert(key, Node.getLeftBranch());
                    }else if (Node.getLeftKey() < key && key > Node.getRightKey()){
                        insert(key , Node.getMiddleBranch());
                    }else{
                        insert (key, Node.getRightBranch());
                    }
                }
            }else if (Node.isLeaf() && Node.isFull()){
                //split promote
                setRelations(null,root);
                
                
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
    
    public void setRelations(TreeNode father, TreeNode child){
        if (father == null){
            if (father.getLeftBranch() != null){
                setRelations(child,child.getLeftBranch());
            }
            if (father.getMiddleBranch() != null){
                setRelations(child,child.getMiddleBranch());
            }
            if (father.getRightBranch() != null){
                setRelations(child,child.getRightBranch());
            }
            
        }else if (father != null && !father.isLeaf()){
            if (father.getLeftBranch() != null){
                setRelations(child,child.getLeftBranch());
            }
            if (father.getMiddleBranch() != null){
                setRelations(child,child.getMiddleBranch());
            }
            if (father.getRightBranch() != null){
                setRelations(child,child.getRightBranch());
            }
        }else{
            child.setFather(father);
        }
    }
    
    public void promote (TreeNode node, int key3){
        int key1 = node.getLeftKey();
        int key2 = node.getRightKey();
        int toRise;
        TreeNode leftNode = new TreeNode();
        TreeNode rightNode = new TreeNode();
        
        if (key3 < key1){
            //key3 menor
            leftNode = new TreeNode(key3);
            rightNode = new TreeNode(key2);
            toRise = key1;
            
        }else if (key1 < key3 && key3 < key2){
            //key3 en medio
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key2);
            toRise = key3;
            
        }else if (key2 < key3){
            //key3 mayor
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key3);
            toRise = key2;  
        }
        
        if (node.getFather() == null){
            //uy papa
        }else{
            if (node.getFather().rightEmpty()){
                if (node.getFather().getMiddleBranch().rightEmpty()){
                    node.getFather().getMiddleBranch().setRightKey(leftNode.getLeftKey());
                    order(node.getFather().getMiddleBranch());
                }else{
                    int newkey1;
                    int newkey2;
                    int newkey3;
                }
                if (node.getFather().getRightBranch() == null){
                    node.getFather().setRightBranch(new TreeNode(rightNode.getLeftKey(), node.getFather()));
                    
                }else if (node.getFather().getRightBranch() != null && node.getFather().getRightBranch().rightEmpty()){
                    node.getFather().getRightBranch().setRightKey(rightNode.getLeftKey());
                    order(node.getFather().getRightBranch());
                    
                }else{
                    
                    int newkey1;
                    int newkey2;
                    int newkey3;
                }
            }else{
                
            }
        }
 
    }
}

