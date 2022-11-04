package com.hy.dusk.service;

import com.hy.dusk.pojo.vo.TestGetVo;
import com.hy.dusk.pojo.vo.TestPostVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Service
public class TestService {

    public TestGetVo testGet(int id,String name){
        TestGetVo.TestGetVo2 build = TestGetVo.TestGetVo2.builder().id(1111111).age(23).build();
        return TestGetVo.builder().id(1111).name("小小name").testVo(build).build();
    }

    @PostMapping("/api/dusk/test/post")
    public TestPostVo testPost(int id,String name){
        HashMap<String, Object> testMap = new HashMap<>();
        testMap.put("aaaaa",12);
        testMap.put("bbbbb",12);
        testMap.put("aMp",12);
        return TestPostVo.builder().id(1111).name("小小name").testMap(testMap).build();
    }


}
