/*
BST: Class for binary search tree with methods for inserting nodes.
BST: Класс для бинарного дерева поиска с методами для вставки узлов.
 */

package com.kukharev.core.animation.TreeSearch;

public class BST {
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
