package com.hy.dusk.controller;

import afu.org.checkerframework.checker.oigj.qual.O;
import com.hy.dusk.pojo.vo.TestGetVo;
import com.hy.dusk.pojo.vo.TestPostVo;
import com.hy.dusk.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Api(value = "test 接口", tags = {"test--接口"})
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/api/dusk/test")
    public Object test(){
        return "test ok";
    }

    @ApiOperation("test get 接口")
    @GetMapping("/api/dusk/test/get")
    public TestGetVo testGet(@RequestParam Integer id,
                             @RequestParam String name){
        return testService.testGet(id,name);
    }

    @ApiOperation("test Post 接口")
    @PostMapping("/api/dusk/test/post")
    public TestPostVo testPost(@RequestParam Integer id,
                               @RequestParam String name,
                               @RequestBody HashMap<String,Object> testRes){

        return testService.testPost(id,name);
    }

}
