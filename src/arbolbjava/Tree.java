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
                if (Node == Node.getFather().getLeftBranch()) {
                    promote(Node, key, true, false);
                } else if (Node == Node.getFather().getMiddleBranch()) {
                    promote(Node, key, false, true);
                }

            } else if (Node.isLeaf() && Node.isFull() && Node.getFather().isFull()) {
                if (Node == Node.getFather().getLeftBranch()) {
                    promote(Node,key,true,false,false);

                } else if (Node == Node.getFather().getMiddleBranch()) {
                    promote(Node,key,false,true,false);

                } else if (Node == Node.getFather().getRightBranch()) {
                    promote(Node,key,false,false,true);

                }
            }
        }
        if (Node == null){
            Node = new TreeNode(key);
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
        int keyToLeft = -1;
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

        } else if (key1 < key3 && key3 < key2) {
            //key3 en medio
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key2);
            toRise = key3;
            keyToLeft = key1;
            keyToRight = key2;
            int fatherLeft;

        } else if (key2 < key3) {
            //key3 mayor
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key3);
            toRise = key2;
            keyToLeft = key1;
            keyToRight = key3;
        }

        if (first) {
            node.getFather().setRightKey(node.getFather().getLeftKey());
            node.getFather().setLeftKey(toRise);
            node.getFather().setLeftBranch(new TreeNode(keyToLeft));
            node.getFather().setRightBranch(node.getFather().getMiddleBranch());
            node.getFather().setMiddleBranch(new TreeNode(keyToRight));
        }
        if (second) {
            node.getFather().setRightKey(toRise);
            node.getFather().setMiddleBranch(new TreeNode(keyToLeft));
            node.getFather().setRightBranch(new TreeNode(keyToRight));
        }
        setRelations(null, root);
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

    public void promote(TreeNode node, int key3, boolean first, boolean second, boolean third) {
        int key1 = node.getLeftKey();
        int key2 = node.getRightKey();
        int keyToLeft = -1;
        int keyToRight = -1;
        int toRise = -1;
        int toRiseAgain = -1;
        int bigKeyToLeft = -1;
        int bigKeyToRight = -1;
        TreeNode leftNode = new TreeNode();
        TreeNode rightNode = new TreeNode();

        if (key3 < key1) {
            //key3 menor
            leftNode = new TreeNode(key3);
            rightNode = new TreeNode(key2);
            toRise = key1;
            keyToLeft = key3;
            keyToRight = key2;

        } else if (key1 < key3 && key3 < key2) {
            //key3 en medio
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key2);
            toRise = key3;
            keyToLeft = key1;
            keyToRight = key2;
            int fatherLeft;

        } else if (key2 < key3) {
            //key3 mayor
            leftNode = new TreeNode(key1);
            rightNode = new TreeNode(key3);
            toRise = key2;
            keyToLeft = key1;
            keyToRight = key3;
        }

        if (first) {
            toRiseAgain = node.getFather().getLeftKey();
            TreeNode firstSon = new TreeNode(toRise);
            TreeNode secondSon = new TreeNode(node.getFather().getRightKey());
            TreeNode grandSonL1 = new TreeNode(keyToLeft);
            TreeNode grandSonL2 = new TreeNode(keyToRight);
            TreeNode grandSonR1 = node.getFather().getMiddleBranch();
            TreeNode grandSonR2 = node.getFather().getRightBranch();
            TreeNode newFather = new TreeNode();
            if (node.getFather().getFather() == null) {
                newFather = new TreeNode(toRiseAgain);
                newFather.setLeftBranch(firstSon);
                newFather.setMiddleBranch(secondSon);
                newFather.getLeftBranch().setLeftBranch(grandSonL1);
                newFather.getLeftBranch().setLeftBranch(grandSonL2);
                newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                newFather.getMiddleBranch().setLeftBranch(grandSonR2);
                root = newFather;
            } else if (node.getFather().getFather() != null && !node.getFather().getFather().isFull()) {

                if (node.getFather().getFather() == node.getFather().getFather().getFather().getLeftBranch()) {
                    node.getFather().getFather().getFather().setRightBranch(node.getFather().getFather().getFather().getMiddleBranch());
                    node.getFather().getFather().getFather().setMiddleBranch(node.getFather().getFather().getFather().getLeftBranch());

                    newFather = new TreeNode(toRiseAgain);
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR2);

                    node = newFather;
                } else if (node.getFather().getFather() == node.getFather().getFather().getMiddleBranch()) {

                    newFather = new TreeNode(toRiseAgain);
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR2);

                    node.getFather().getFather().getFather().setMiddleBranch(firstSon);
                    node.getFather().getFather().getFather().setRightBranch(secondSon);
                    node = newFather;

                }
            } else {
                if (node.getFather().getFather() == node.getFather().getFather().getFather().getLeftBranch()) {
                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;

                    promote(node.getFather(), toRiseAgain, true, false, false);
                } else if (node.getFather().getFather() == node.getFather().getFather().getFather().getMiddleBranch()) {
                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;
                    promote(node.getFather(), toRiseAgain, false, true, false);
                } else if (node.getFather().getFather() == node.getFather().getFather().getFather().getRightBranch()) {

                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;
                    promote(node.getFather(), toRiseAgain, false, false, true);
                }

            }

        }
        if (second) {

            toRiseAgain = node.getFather().getLeftKey();
            TreeNode firstSon = new TreeNode(toRise);
            TreeNode secondSon = new TreeNode(node.getFather().getRightKey());
            TreeNode grandSonL1 = new TreeNode(keyToLeft);
            TreeNode grandSonL2 = new TreeNode(keyToRight);
            TreeNode grandSonR1 = node.getFather().getMiddleBranch();
            TreeNode grandSonR2 = node.getFather().getRightBranch();
            TreeNode newFather = new TreeNode();
            if (node.getFather().getFather() == null) {
                newFather = new TreeNode(toRiseAgain);
                newFather.setLeftBranch(firstSon);
                newFather.setMiddleBranch(secondSon);
                newFather.getLeftBranch().setLeftBranch(grandSonL1);
                newFather.getLeftBranch().setLeftBranch(grandSonL2);
                newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                newFather.getMiddleBranch().setLeftBranch(grandSonR2);
                root = newFather;
            } else if (node.getFather().getFather() != null && !node.getFather().getFather().isFull()) {

                if (node.getFather().getFather() == node.getFather().getFather().getFather().getLeftBranch()) {
                    node.getFather().getFather().getFather().setRightBranch(node.getFather().getFather().getFather().getMiddleBranch());
                    node.getFather().getFather().getFather().setMiddleBranch(node.getFather().getFather().getFather().getLeftBranch());

                    newFather = new TreeNode(toRiseAgain);
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR2);

                    node = newFather;
                } else if (node.getFather().getFather() == node.getFather().getFather().getMiddleBranch()) {

                    newFather = new TreeNode(toRiseAgain);
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR2);

                    node.getFather().getFather().getFather().setMiddleBranch(firstSon);
                    node.getFather().getFather().getFather().setRightBranch(secondSon);
                    node = newFather;

                }
            } else {
                if (node.getFather().getFather() == node.getFather().getFather().getFather().getLeftBranch()) {
                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;

                    promote(node.getFather(), toRiseAgain, true, false, false);
                } else if (node.getFather().getFather() == node.getFather().getFather().getFather().getMiddleBranch()) {
                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;
                    promote(node.getFather(), toRiseAgain, false, true, false);
                } else if (node.getFather().getFather() == node.getFather().getFather().getFather().getRightBranch()) {

                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;
                    promote(node.getFather(), toRiseAgain, false, false, true);
                }

            }

        }
        if (third) {

            toRiseAgain = node.getFather().getLeftKey();
            TreeNode firstSon = new TreeNode(toRise);
            TreeNode secondSon = new TreeNode(node.getFather().getRightKey());
            TreeNode grandSonL1 = new TreeNode(keyToLeft);
            TreeNode grandSonL2 = new TreeNode(keyToRight);
            TreeNode grandSonR1 = node.getFather().getMiddleBranch();
            TreeNode grandSonR2 = node.getFather().getRightBranch();
            TreeNode newFather = new TreeNode();
            if (node.getFather().getFather() == null) {
                newFather = new TreeNode(toRiseAgain);
                newFather.setLeftBranch(firstSon);
                newFather.setMiddleBranch(secondSon);
                newFather.getLeftBranch().setLeftBranch(grandSonL1);
                newFather.getLeftBranch().setLeftBranch(grandSonL2);
                newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                newFather.getMiddleBranch().setLeftBranch(grandSonR2);
                root = newFather;
            } else if (node.getFather().getFather() != null && !node.getFather().getFather().isFull()) {

                if (node.getFather().getFather() == node.getFather().getFather().getFather().getLeftBranch()) {
                    node.getFather().getFather().getFather().setRightBranch(node.getFather().getFather().getFather().getMiddleBranch());
                    node.getFather().getFather().getFather().setMiddleBranch(node.getFather().getFather().getFather().getLeftBranch());

                    newFather = new TreeNode(toRiseAgain);
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR2);

                    node = newFather;
                } else if (node.getFather().getFather() == node.getFather().getFather().getMiddleBranch()) {

                    newFather = new TreeNode(toRiseAgain);
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR1);
                    newFather.getMiddleBranch().setLeftBranch(grandSonR2);

                    node.getFather().getFather().getFather().setMiddleBranch(firstSon);
                    node.getFather().getFather().getFather().setRightBranch(secondSon);
                    node = newFather;

                }
            } else {
                if (node.getFather().getFather() == node.getFather().getFather().getFather().getLeftBranch()) {
                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;

                    promote(node.getFather(), toRiseAgain, true, false, false);
                } else if (node.getFather().getFather() == node.getFather().getFather().getFather().getMiddleBranch()) {
                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;
                    promote(node.getFather(), toRiseAgain, false, true, false);
                } else if (node.getFather().getFather() == node.getFather().getFather().getFather().getRightBranch()) {

                    toRiseAgain = node.getFather().getLeftKey();
                    firstSon = new TreeNode(toRise);
                    secondSon = new TreeNode(node.getFather().getRightKey());
                    grandSonL1 = new TreeNode(keyToLeft);
                    grandSonL2 = new TreeNode(keyToRight);
                    grandSonR1 = node.getFather().getMiddleBranch();
                    grandSonR2 = node.getFather().getRightBranch();
                    newFather.setFather(node.getFather());
                    newFather.setLeftBranch(firstSon);
                    newFather.setMiddleBranch(secondSon);
                    newFather.getLeftBranch().setLeftBranch(grandSonL1);
                    newFather.getLeftBranch().setLeftBranch(grandSonL2);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR1);
                    newFather.getLeftBranch().setMiddleBranch(grandSonR2);
                    node = newFather;
                    promote(node.getFather(), toRiseAgain, false, false, true);
                }

            }
        }
        setRelations(null, root);
    }
    
     public void print(TreeNode father, int contador) {
        if (father != null && !father.isLeaf()) {
            System.out.println("Nivel: "+ contador);
            System.out.println("father: " +father.toString());
            if (father.getLeftBranch() != null) {
                System.out.println("LeftChild: " + father.getLeftBranch().toString());
                print(father.getLeftBranch(),contador+1);
            }
            if (father.getMiddleBranch() != null) {
                System.out.println("MiddleChild: " + father.getMiddleBranch().toString());
                print(father.getMiddleBranch(),contador+1);
            }
            if (father.getRightBranch() != null) {
                System.out.println("MiddleChild: " + father.getRightBranch().toString());
                print(father.getRightBranch(),contador+1);
            }
        }
       
        
    }
}
