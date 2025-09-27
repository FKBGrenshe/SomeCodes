package com.baseDataStructure.HuffmanTree;

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

}
