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

    public void promoteRR(TreeNode father, int key) {
        int fatherLeft = father.getLeftKey();
        int fatherRight = father.getRightKey();
        int sonLeft = -1;
        int sonRight = -1;
        
        if (key < fatherLeft){
            sonLeft = father.getLeftBranch().getLeftKey();
            sonRight = father.getLeftBranch().getRightKey();
            TreeNode tempFather = new TreeNode(fatherLeft);
            TreeNode tempFirstBorn = new TreeNode(key);
            TreeNode tempSecondBorn = new TreeNode(fatherRight);
            
            if (key < sonLeft ){
                
            }else if(sonLeft < key && key < sonRight){
                
            }else if (key > sonRight){
                
            }else{
                System.err.println("Error en PromoteRR");
            }
            
            
        }else if ( (key > fatherLeft && key> fatherRight) || (key > fatherRight && father.getRightBranch() == null) ){
                sonLeft = father.getMiddleBranch().getLeftKey();
                sonRight = father.getMiddleBranch().getRightKey();
                
        }else if (key > fatherRight && father.getRightBranch() != null){
                sonLeft = father.getRightBranch().getLeftKey();
                sonRight = father.getRightBranch().getRightKey();
        }else{
            System.err.println("error en promote RR");
        }
        
    }
}
