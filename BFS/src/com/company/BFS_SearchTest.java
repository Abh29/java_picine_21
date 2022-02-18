package com.company;

import org.junit.Assert;

class BFS_SearchTest {

    @org.junit.jupiter.api.Test
    void shortestPath() {

        Assert.assertEquals(-1,BFS_Search.ShortestPath(43,57,Rules::add_3,Rules::multiply_4));
        Assert.assertEquals(0,BFS_Search.ShortestPath(10,10,Rules::add_3,Rules::multiply_4));
        Assert.assertEquals(5,BFS_Search.ShortestPath(3,57,Rules::add_3,Rules::multiply_4));
        Assert.assertEquals(6,BFS_Search.ShortestPath(43,57,Rules::add_3,Rules::multiply_4,Rules::sub_1));
        Assert.assertEquals(4,BFS_Search.ShortestPath(3,1000,Rules::add_3,Rules::multiply_4,Rules::sub_1,Rules::power_2,Rules::power_3,Rules::multiply_16,Rules::divide_2));
        Assert.assertEquals(13,BFS_Search.ShortestPath(8997,-6240,Rules::add_3,Rules::multiply_4,Rules::sub_1,Rules::divide_2,Rules::add_12,Rules::multiply_16,Rules::divide_8,Rules::multiply_minus_1));
        Assert.assertEquals(20,BFS_Search.ShortestPath(8997,-6240,Rules::add_3,Rules::multiply_4,Rules::sub_1,Rules::divide_2,Rules::add_12,Rules::multiply_16,Rules::divide_8));

    }

}