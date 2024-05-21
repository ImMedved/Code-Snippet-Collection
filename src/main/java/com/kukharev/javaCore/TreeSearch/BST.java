/*
A class for a binary search tree, including methods for inserting nodes.
Класс для бинарного дерева поиска, включающий методы для вставки узлов.
 */

package com.kukharev.javaCore.TreeSearch;

class BST {
    TreeNode root;
    BST() {root = null;}

    void insert(int value) {root = insertRec(root, value);}

    TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.value) root.left = insertRec(root.left, value);
        else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }
}
