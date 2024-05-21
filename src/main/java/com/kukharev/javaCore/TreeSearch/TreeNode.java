/*
A class representing a tree node containing a value and references to the left and right subtrees.
Класс, представляющий узел дерева, содержащий значение и ссылки на левое и правое поддеревья.
 */

package com.kukharev.javaCore.TreeSearch;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}
