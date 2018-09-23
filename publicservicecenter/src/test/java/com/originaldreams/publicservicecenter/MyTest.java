package com.originaldreams.publicservicecenter;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyTest {
    @Test
    public void testMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("a",1);
        map.put("b","ddd");
        System.out.println(map.toString());
    }

}
