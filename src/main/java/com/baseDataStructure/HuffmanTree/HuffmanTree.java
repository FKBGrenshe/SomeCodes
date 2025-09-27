package com.baseDataStructure.HuffmanTree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-27
 * @Description: 哈夫曼树
 */
public class HuffmanTree {

    static class NodeHuffmanTree{
        Character ch; // 字符
        int frequence; // 频次
        String code; // 编码 -- 实际上应该为bit位而不是string

        NodeHuffmanTree left, right;

        public NodeHuffmanTree(Character ch) {
            this.ch = ch;
        }

        public NodeHuffmanTree(int frequence, NodeHuffmanTree left, NodeHuffmanTree right) {
            this.frequence = frequence;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return left == null;  //  哈夫曼树是满二叉树，所以可以这么进行判断
        }

        public void setCh(Character ch) {
            this.ch = ch;
        }

        public int getFrequence() {
            return frequence;
        }

        public void setFrequence(int frequence) {
            this.frequence = frequence;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public NodeHuffmanTree getLeft() {
            return left;
        }

        public void setLeft(NodeHuffmanTree left) {
            this.left = left;
        }

        public NodeHuffmanTree getRight() {
            return right;
        }

        public void setRight(NodeHuffmanTree right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "NodeHuffmanTree{" +
                    "ch=" + ch +
                    ", frequence=" + frequence +
                    ", code='" + code + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    String str;
    Map<Character, NodeHuffmanTree> map = new HashMap<>();

    NodeHuffmanTree root = null;

    public HuffmanTree(String str) {
        this.str = str;

        /*1. 统计频率*/
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!map.containsKey(c)){
                map.put(c,new NodeHuffmanTree(c));
            }
            NodeHuffmanTree curNode = map.get(c);
            curNode.frequence++;
        }
//        for (NodeHuffmanTree node : map.values()) {
//            System.out.println(node);
//        }
        /*2. 构造huffman树*/
        PriorityQueue<NodeHuffmanTree> queue = new PriorityQueue<>(Comparator.comparingInt(NodeHuffmanTree::getFrequence));
        queue.addAll(map.values());
        while (queue.size() >= 2){
            //每次出队两个频次最低的元素，合并找爹
            NodeHuffmanTree x = queue.poll();
            NodeHuffmanTree y = queue.poll();
            int totalFrequence = x.frequence + y.frequence;
            NodeHuffmanTree fatherNode = new NodeHuffmanTree(totalFrequence, x, y);
            queue.offer(fatherNode);
        }
        root = queue.poll();
//        System.out.println(root);
        /*3. 深度优先遍历 - 计算每个字符的编码*/
        dfs(root, new StringBuilder());
        for (NodeHuffmanTree node : map.values()) {
            System.out.println( node );
        }
        soutTotalBits();
    }

    private void dfs(NodeHuffmanTree curNode, StringBuilder path) {
        if (curNode.isLeaf()){
            // 找到叶子节点，计算它的编码
            curNode.setCode(path.toString());
        }else {
            dfs(curNode.left, path.append(0));
            dfs(curNode.right, path.append(1));
        }
        if (path.length() > 0){
            path.deleteCharAt(path.length()-1);
        }



    }

    public String enCode(){
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append(map.get(aChar).code);
        }
        return stringBuilder.toString();
    }


    public String deCode(){

        char[] chars = this.enCode().toCharArray();
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        NodeHuffmanTree node = root;
        while (i < chars.length){
            if (!node.isLeaf()){ // 非叶子
                if (chars[i] == '0'){
                    // left
                    node = node.left;
                }else {
                    // right
                    node = node.right;
                }
                i++;
            }

            if (node.isLeaf()){
                // 叶子
                stringBuilder.append(node.ch);
                node = root;
            }

        }
        return stringBuilder.toString();

    }

    public void soutTotalBits(){
        int totalBits = 0;
        for (NodeHuffmanTree node : map.values()) {
            totalBits += node.frequence * node.code.length();
        }
        System.out.println("totalBits = " + totalBits);
    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree("abbccccccc");
//        String s = huffmanTree.enCode();
//        System.out.println(s);

        String s1 = huffmanTree.deCode();
        System.out.println(s1);
    }
}
