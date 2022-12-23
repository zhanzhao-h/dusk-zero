package com.hy.dusk.controller;

import com.hy.dusk.common.log.GenLog;
import com.hy.dusk.common.log.GenLogEnum;
import com.hy.dusk.pojo.vo.TestGetVo;
import com.hy.dusk.pojo.vo.TestPostVo;
import com.hy.dusk.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Api(value = "test 接口", tags = {"test--接口"})
@RequestMapping("/api")
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/dusk/test")
    @GenLog(value = GenLogEnum.WEB)
    public Object test(){
        TestGetVo testGetVo = testService.testGet(1, "");
        return "test ok";
    }

    @ApiOperation("test get 接口")
    @GetMapping("/dusk/test/get")
    @GenLog(value = GenLogEnum.WEB)
    public TestGetVo testGet(@RequestParam Integer id,
                             @RequestParam String name){
        return testService.testGet(id,name);
    }

    @ApiOperation("test Post 接口")
    @PostMapping("/dusk/test/post")
    public TestPostVo testPost(@RequestParam Integer id,
                               @RequestParam String name,
                               @RequestBody HashMap<String,Object> testRes){

        return testService.testPost(id,name);
    }

}
