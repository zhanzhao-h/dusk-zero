package com.hy.dusk.controller;

import com.hy.dusk.common.ResponseContainer;
import com.hy.dusk.pojo.vo.AccountVO;
import com.hy.dusk.pojo.vo.ResAccountVO;
import com.hy.dusk.pojo.vo.TestGetVo;
import com.hy.dusk.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/dusk/account/")
@Api(value = "account用户业务", tags = {"account--接口"})
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation("新增用户")
    @PostMapping("/v1/insert")
    public ResponseContainer<Long> testGet(@RequestBody AccountVO accountVO) {
        Long accountId = accountService.insertAccount(accountVO);
        return ResponseContainer.success(accountId,"");
    }

    @ApiOperation("查询单个用户")
    @GetMapping("/v1/search")
    public ResponseContainer<ResAccountVO> getAccount(@RequestParam Long accountId) {

        ResAccountVO resAccountVO = accountService.getAccount(accountId);
        return ResponseContainer.success(resAccountVO, "");
    }

    @ApiOperation("查询用户list")
    @GetMapping("/v1/list")
    public ResponseContainer<List<ResAccountVO>> listAccounts(@RequestParam Long accountId) {
        List<ResAccountVO> resAccountVOList = accountService.listAccounts(accountId);
        return ResponseContainer.success(resAccountVOList, "");
    }
}
