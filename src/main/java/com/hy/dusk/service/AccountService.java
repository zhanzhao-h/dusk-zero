package com.hy.dusk.service;

import com.hy.dusk.pojo.vo.AccountVO;
import com.hy.dusk.pojo.vo.ResAccountVO;
import com.hy.dusk.pojo.vo.TestGetVo;

import java.util.List;

public interface AccountService {

    Long insertAccount(AccountVO accountVO);

    ResAccountVO getAccount(Long accountId);

    List<ResAccountVO> listAccounts(Long accountId);


}
