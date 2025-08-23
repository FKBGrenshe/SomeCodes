package com.baseDataStructure.HeapStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-11
 * @Description: 大顶堆
 * -xxx 核心方法：up上浮、down下潜、heapify建堆
 */
public class MaxHeap {

    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MaxHeap(int[] array){
        // 将普通数组转成大顶堆
        this.array = array;
        this.size = array.length;
        // 建堆
        heapify();
    }

    /**
     * -xxx 建堆
     *  1. 找到最后一个非叶子节点
     *  2. 从后向前，对每个节点执行下潜
     */
    private void heapify(){
        // 1.找到最后一个非叶子节点：(size/2)-1
        int lastNonLeafNodeIdx = (size >> 1) -1;
        for (int i = lastNonLeafNodeIdx; i >= 0; i--) {
            // 2. 从后往前，对每个非叶子节点执行下潜
            down(i);
        }
    }

    /**
     * 删除堆顶元素
     * @return 堆顶元素
     */
    public int poll(){
        int top = array[0];
        // 交换
        swap(0,size-1);
        // 删除原堆顶元素
        size--;
        // 对于堆顶元素进行下潜
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index){
        int deleted = array[index];
        // 交换
        swap(index,size-1);
        // 删除原堆顶元素
        size--;
        // 对于堆顶元素进行下潜
        down(index);
        return deleted;
    }

    /**
     * 堆的尾部添加元素
     * @param offered 被添加的元素值
     * @return 是否添加成功
     */
    public boolean offer(int offered){
        if (size == array.length){
            // 已满
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    /**
     * 将 inserted 元素上浮：直至offered小于父元素或到堆顶
     * @param offered
     */
    private void up(int offered){
        int child = size;
        while (child > 0){
            int parent = (child - 1)/2;
            if (offered > array[parent]){
                array[child] = array[parent];
            }else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    /**
     * 将 parent索引处的元素下潜，与两个孩子较大者交换，直至没有孩子或没有孩子比它大
     * @param parent
     */
    private void down(int parent){
        int left = parent * 2+ 1;
        int right = left+1;
        int max = parent;

        if (left < size){
            max = array[left] > array[max] ? left : max;
        }
        if (right < size){
            max = array[right] > array[max] ? right : max;
        }
        if (max != parent){
            // 确实找到了child比parent更大
            // 交换
            // 继续下潜
            swap(max,parent);
            down(max);
        }

    }

    /**
     * 交换两个索引处的元素
     * @param i
     * @param j
     */
    private void swap(int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }


    /**
     * 获取堆顶元素
     * @return
     */
    public int peek(){
        return array[0];
    }

    /**
     * 替换堆顶元素
     * @param replaced 新元素
     */
    public void replace(int replaced){
        array[0] = replaced;
        down(0);
    }

}
