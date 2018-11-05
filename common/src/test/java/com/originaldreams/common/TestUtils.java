package com.originaldreams.common;

import com.originaldreams.common.util.StringUtils;
import org.junit.Test;

public class TestUtils {
    @Test
    public void testIsEmpty(){
        System.out.println(StringUtils.isEmpty("a","  ","v"));
        System.out.println(StringUtils.isEmpty(" "));
    }


}
