package com.originaldreams.proxycenter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ProxycenterApplicationTests {

    @Test
    public void test() throws Exception{
        String a = "{\"success\":0,\"data\":null,\"message\":null}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map;
        try {
            map = mapper.readValue(a, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(map.get("data"));


    }


}
