package com.abigtomato.example.service.impl;

import com.abigtomato.example.client.AccountClient;
import com.abigtomato.example.client.StorageClient;
import com.abigtomato.example.dao.OrderDao;
import com.abigtomato.example.domain.Order;
import com.abigtomato.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageClient storageClient;

    @Resource
    private AccountClient accountClient;

    @Override
    public void create(Order order) {
        log.info("创建订单");
        orderDao.create(order);

        log.info("调用store服务，扣减库存");
        storageClient.decrease(order.getProductId(), order.getCount());

        log.info("调用account服务，扣减余额");
        accountClient.decrease(order.getUserId(), order.getMoney());

        log.info("修改订单状态");
        orderDao.update(order.getUserId(), 0);
    }
}
