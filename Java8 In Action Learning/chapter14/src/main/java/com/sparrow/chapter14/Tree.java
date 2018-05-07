package com.sparrow.chapter14;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class Tree {

    private String key;
    private int val;
    private Tree left, right;

    public Tree(String key, int val, Tree left, Tree right){
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static class TreeProcessor{
        public static int lookup(String key, int defaultVal, Tree tree){
            if(tree == null){
                return defaultVal;
            }
            if(key.equals(tree.key)){
                return tree.val;
            }
            return lookup(key, defaultVal, key.compareTo(tree.key) < 0 ? tree.left : tree.right);
        }

        public static Tree fupdate(String key, int newVal, Tree tree){
            if(tree == null){
                return new Tree(key, newVal, null, null);
            }else if(key.equals(tree.key)){
                return new Tree(key, newVal, tree.left, tree.right);
            }else if(key.compareTo(tree.key) < 0){
                return new Tree(key, newVal, fupdate(key, newVal, tree.left), tree.right);
            }else{
                return new Tree(key, newVal, tree.left, fupdate(key, newVal, tree.right));
            }
        }
    }

}
