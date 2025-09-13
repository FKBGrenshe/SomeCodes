package com.baseDataStructure.GraphStructure;

import com.baseDataStructure.GraphStrucuture.DisjoinSet;
import org.junit.jupiter.api.Test;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-13
 * @Description: 并查集测试
 */
public class DisjointSetTest {

    @Test
    void creatUnionJoinSet(){
        DisjoinSet disjoinSet = new DisjoinSet(7);
        System.out.println(disjoinSet);
    }



}
