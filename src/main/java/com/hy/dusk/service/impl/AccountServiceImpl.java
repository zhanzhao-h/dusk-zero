package com.hy.dusk.service.impl;

import com.hy.dusk.common.util.UidGeneratorUtil;
import com.hy.dusk.mapper.AccountMapper;
import com.hy.dusk.pojo.po.Account;
import com.hy.dusk.pojo.vo.AccountVO;
import com.hy.dusk.pojo.vo.ResAccountVO;
import com.hy.dusk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }


    @Override
    public Long insertAccount(AccountVO accountVO) {
        Account account = accountVO2AccountPO(accountVO);
        Long accountId = UidGeneratorUtil.getUidGenerator();
        account.setId(accountId);
        accountMapper.insert(account);
        return accountId;
    }

    @Override
    public ResAccountVO getAccount(Long accountId) {
        Account account = accountMapper.selectByPrimaryKey(accountId);
        return accountPO2AccountVO(account);
    }

    @Override
    public List<ResAccountVO> listAccounts(Long accountId) {
        return null;
    }

    public Account accountVO2AccountPO(AccountVO accountVO) {
        Account account = new Account();
        account.setAge(accountVO.getAge());
        account.setGender(accountVO.getGender());
        account.setNickname(accountVO.getNickname());
        account.setRealName(accountVO.getRealName());
        account.setAvatarUrl(accountVO.getAvatarUrl());
        account.setDescription(accountVO.getDescription());

        account.setUserStatus(1);
        account.setOperatorId(10000001L);
        account.setUserStatus(1);
        account.setUpdateTime(System.currentTimeMillis());
        account.setCreateTime(System.currentTimeMillis());
        return account;
    }

    public ResAccountVO accountPO2AccountVO(Account account) {
        ResAccountVO resAccountVO = new ResAccountVO();
        resAccountVO.setId(account.getId());
        resAccountVO.setAge(account.getAge());
        resAccountVO.setGender(account.getGender());
        resAccountVO.setNickname(account.getNickname());
        resAccountVO.setRealName(account.getRealName());
        resAccountVO.setAvatarUrl(account.getAvatarUrl());
        resAccountVO.setDescription(account.getDescription());

        resAccountVO.setUserStatus(account.getUserStatus());
        resAccountVO.setOperatorId(account.getOperatorId());
        resAccountVO.setUpdateTime(account.getUpdateTime());
        resAccountVO.setCreateTime(account.getCreateTime());
        return resAccountVO;
    }

}
