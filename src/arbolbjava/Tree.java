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
            } else if (Node.isLeaf() && Node.isFull()) {
                //split promote
                setRelations(null, root);
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
        } else {
            child.setFather(father);
        }
    }

    public void promote(TreeNode node, int key3) {
        int key1 = node.getLeftKey();
        int key2 = node.getRightKey();
        int toRise = -1;
        TreeNode leftNode = new TreeNode();
        TreeNode rightNode = new TreeNode();

        if (key3 < key1) {
            //key3 menor
            leftNode = new TreeNode(key3);
            rightNode = new TreeNode(key2);
            toRise = key1;

        } else if (key1 < key3 && key3 < key2) {
            //key3 en medio
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key2);
            toRise = key3;

        } else if (key2 < key3) {
            //key3 mayor
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key3);
            toRise = key2;
        }

        if (node.getFather() == null) {
            //uy papa
        } else {
            if (node.getFather().rightEmpty()) {
                if (node.getFather().getMiddleBranch().rightEmpty()) {
                    node.getFather().getMiddleBranch().setRightKey(leftNode.getLeftKey());
                    order(node.getFather().getMiddleBranch());
                } else {
                    /*int newkey1 = node.getFather().getLeftKey();
                    int newkey2 = node.getFather().getRightKey();
                    int toRiseAgain;

                    if (toRise < newkey1) {

                    } else if (toRise > newkey1 && toRise < newkey2) {

                    } else if (toRise > newkey2) {

                    }*/

                }
                if (node.getFather().getRightBranch() != null && node.getFather().getRightBranch().rightEmpty()) {
                    node.getFather().getRightBranch().setRightKey(rightNode.getLeftKey());
                    order(node.getFather().getRightBranch());
                } else {

                    /*int newkey1 = node.getFather().getLeftKey();
                    int newkey2 = node.getFather().getRightKey();
                    int toRiseAgain;

                    if (toRise < newkey1) {

                    } else if (toRise > newkey1 && toRise < newkey2) {

                    } else if (toRise > newkey2) {

                    }*/
                }
            } else {

            }
        }

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
            }else if (grandSonLeft < key && key < grandSonRight){
                toRise = key;
            }else{
                toRise = grandSonRight;
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