/*
TreeNode: A class for a tree node, containing a value and references to the left and right subtrees.
TreeNode: Класс для узла дерева, содержащий значение и ссылки на левое и правое поддеревья.
*/
package com.kukharev.core.animation.TreeSearch;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}
