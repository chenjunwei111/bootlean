package com.cjw.boot.service.web3j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

/**
* Description 区块链类
* @Author junwei
* @Date 9:42 2019/12/5
**/
@Service
public class BlockchainService {

     Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Autowired
    Web3j web3j;

}
