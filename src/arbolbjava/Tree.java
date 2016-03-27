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

    public void insert(int key, TreeNode Node) {
        if (Node.getFather() == null && Node.isLeaf()) {
            if (!Node.isFull()) {
                if (Node.leftEmpty() && Node.rightEmpty()) {
                    Node.setLeftKey(key);
                } else if (!Node.leftEmpty() && Node.rightEmpty()) {
                    Node.setRightKey(key);
                }
                order(Node);
            } else {
                int key1 = Node.getLeftKey();
                int key2 = Node.getRightKey();
                int key3 = key;
                TreeNode leftTemp, rightTemp;

                if (key3 < key1) {

                    leftTemp = new TreeNode(key3, Node);
                    rightTemp = new TreeNode(key2, Node);
                    Node = new TreeNode(key1);
                    leftTemp.setFather(Node);
                    rightTemp.setFather(Node);
                    Node.setLeftBranch(leftTemp);
                    Node.setRightBranch(rightTemp);

                } else if (key3 > key2) {

                    leftTemp = new TreeNode(key1, Node);
                    rightTemp = new TreeNode(key, Node);
                    Node = new TreeNode(key2);
                    leftTemp.setFather(Node);
                    rightTemp.setFather(Node);
                    Node.setLeftBranch(leftTemp);
                    Node.setRightBranch(rightTemp);
                } else {

                    leftTemp = new TreeNode(key1, Node);
                    rightTemp = new TreeNode(key2, Node);
                    Node = new TreeNode(key);
                    leftTemp.setFather(Node);
                    rightTemp.setFather(Node);
                    Node.setLeftBranch(leftTemp);
                    Node.setRightBranch(rightTemp);
                }

            }
        } else if (Node.getFather() == null && !Node.isLeaf()) {
            int leftKey = Node.getLeftKey();
            int rightKey = Node.getRightKey();

            if (key < leftKey) {
                insert(key, Node.getLeftBranch());

            } else if (key > leftKey && key < rightKey) {
                insert(key, Node.getMiddleBranch());

            } else if (key > rightKey && Node.getRightBranch() != null) {
                insert(key, Node.getRightBranch());

            } else if (key > rightKey && Node.getRightBranch() == null) {
                insert(key, Node.getMiddleBranch());
            }

        } else {
            if (Node.isLeaf() && !Node.isFull()) {
                Node.setRightKey(key);
                order(Node);
            } else if (!Node.isLeaf()) {
                if (Node.rightEmpty()) {
                    if (key < Node.getLeftKey()) {
                        insert(key, Node.getLeftBranch());
                    } else {
                        insert(key, Node.getMiddleBranch());
                    }
                } else {
                    if (key < Node.getLeftKey()) {
                        insert(key, Node.getLeftBranch());
                    } else if (Node.getLeftKey() < key && key > Node.getRightKey()) {
                        insert(key, Node.getMiddleBranch());
                    } else {
                        insert(key, Node.getRightBranch());
                    }
                }
            } else if (Node.isLeaf() && Node.isFull() && !Node.getFather().isFull()) {
                setRelations(null, root);
                if (Node ==  Node.getFather().getLeftBranch()){
                    promote(Node, key,true,false);
                }else if (Node == Node.getFather().getMiddleBranch()){
                    promote(Node, key,false,true);
                }
                
            }else if (Node.isLeaf() && Node.isFull() && Node.getFather().isFull()){
                if (Node ==  Node.getFather().getLeftBranch()){
                    
                }else if (Node == Node.getFather().getMiddleBranch()){
                    
                }else if (Node == Node.getFather().getRightBranch()){
                    
                }
            }
        }

    }

    public void order(TreeNode node) {
        int left = node.getLeftKey();
        int right = node.getRightKey();
        if (left > right) {
            node.setLeftKey(right);
            node.setRightKey(left);
        }
    }

    public void setRelations(TreeNode father, TreeNode child) {
        if (father == null) {
            if (father.getLeftBranch() != null) {
                setRelations(child, child.getLeftBranch());
            }
            if (father.getMiddleBranch() != null) {
                setRelations(child, child.getMiddleBranch());
            }
            if (father.getRightBranch() != null) {
                setRelations(child, child.getRightBranch());
            }

        } else if (father != null && !father.isLeaf()) {
            if (father.getLeftBranch() != null) {
                setRelations(child, child.getLeftBranch());
            }
            if (father.getMiddleBranch() != null) {
                setRelations(child, child.getMiddleBranch());
            }
            if (father.getRightBranch() != null) {
                setRelations(child, child.getRightBranch());
            }
        }
        child.setFather(father);
    }

    public void promote(TreeNode node, int key3, boolean first, boolean second) {
        int key1 = node.getLeftKey();
        int key2 = node.getRightKey();
        int keyToLeft= -1;
        int keyToRight = -1;
        int toRise = -1;
        TreeNode leftNode = new TreeNode();
        TreeNode rightNode = new TreeNode();

        if (key3 < key1) {
            //key3 menor
            leftNode = new TreeNode(key3);
            rightNode = new TreeNode(key2);
            toRise = key1;
            keyToLeft = key3;
            keyToRight = key2;
            

        } else if ( key1 < key3 && key3 < key2 ) {
            //key3 en medio
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key2);
            toRise = key3;
            keyToLeft = key1;
            keyToRight = key2;
            int fatherLeft;

        } else if (key2 < key3 ) {
            //key3 mayor
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key3);
            toRise = key2;
            keyToLeft = key1;
            keyToRight = key3;
        }
        
        if (first){
              node.getFather().setRightKey(node.getFather().getLeftKey());
              node.getFather().setLeftKey(toRise);
              node.getFather().setLeftBranch(new TreeNode(keyToLeft));
              node.getFather().setRightBranch(node.getFather().getMiddleBranch());
              node.getFather().setMiddleBranch(new TreeNode(keyToRight));
        }
        if (second){
            node.getFather().setRightKey(toRise);
            node.getFather().setMiddleBranch(new TreeNode(keyToLeft));
            node.getFather().setRightBranch(new TreeNode(keyToRight));
        }
        setRelations(null,root);
    }

    public void promoteRR(TreeNode father, int key, boolean first, boolean second, boolean third) {
        int fatherLeft = father.getLeftKey();
        int fatherRight = father.getRightKey();
        int sonLeft = -1;
        int sonRight = -1;
        TreeNode tempFather;
        TreeNode tempFirstBorn;
        TreeNode tempSecondBorn;
        TreeNode tempGrandson1;
        TreeNode tempGrandson2;
        TreeNode tempGrandson3;
        TreeNode tempGrandson4;   
        
        tempFather = new TreeNode(fatherLeft);
        tempFirstBorn = new TreeNode(sonLeft);
        tempSecondBorn = new TreeNode(fatherRight);

        tempGrandson1 = new TreeNode();
        tempGrandson2 = new TreeNode();
        tempGrandson3 = father.getMiddleBranch();
        tempGrandson4 = father.getRightBranch();

        tempGrandson1.setFather(tempFirstBorn);
        tempGrandson2.setFather(tempFirstBorn);
        tempGrandson3.setFather(tempSecondBorn);
        tempGrandson4.setFather(tempSecondBorn);

        tempFirstBorn.setLeftBranch(tempGrandson1);
        tempFirstBorn.setMiddleBranch(tempGrandson2);
        tempFirstBorn.setFather(tempFather);

        tempSecondBorn.setLeftBranch(tempGrandson3);
        tempSecondBorn.setMiddleBranch(tempGrandson4);
        tempSecondBorn.setFather(tempFather);

        tempFather.setFather(father.getFather());
        father = tempFather;
             
    }
    
    public void promoteR(TreeNode father, int key, boolean first, boolean second, boolean third){
        int sonLeft = father.getLeftKey();
        int sonRight = father.getRightKey();
        int grandSonLeft;
        int grandSonRight;
        int bottomLeft;
        int bottomRight;
        int toRise;
        int toRiseAgain;
        TreeNode tempFather;
        TreeNode firstSon;
        TreeNode secondSon;
        TreeNode grandSon1;
        TreeNode grandSon2;
        TreeNode grandSon3;
        
        if (first){
            grandSonLeft = father.getLeftBranch().getLeftKey();
            grandSonRight = father.getLeftBranch().getRightKey();
            
            if (key < grandSonLeft){
                toRise = grandSonLeft;
                bottomLeft = key;
                bottomRight = grandSonRight;
                
            }else if (grandSonLeft < key && key < grandSonRight){
                toRise = key;
                bottomLeft = key;
                bottomRight = grandSonRight;
                
            }else{
                toRise = grandSonRight;
                bottomLeft = key;
                bottomRight = grandSonRight;
                
            }
        }
        if (second){
            grandSonLeft = father.getMiddleBranch().getLeftKey();
            grandSonRight = father.getMiddleBranch().getRightKey();
            
            if (key < grandSonLeft){
                toRise = grandSonLeft;
            }else if (grandSonLeft < key && key < grandSonRight){
                toRise = key;
            }else{
                toRise = grandSonRight;
            }
        }
        if (third){
            grandSonLeft = father.getRightBranch().getLeftKey();
            grandSonRight = father.getRightBranch().getRightKey();
            
            if (key < grandSonLeft){
                toRise = grandSonLeft;
            }else if (grandSonLeft < key && key < grandSonRight){
                toRise = key;
            }else{
                toRise = grandSonRight;
            } 
        }
        
        
        
        
        
    }
}