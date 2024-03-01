package com.chris.demo.core;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lianlianpay.mip.pay.query.service.request.RefundApplyOrderRequest;
import com.lianlianpay.mip.pay.query.service.request.RefundOrderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.lianlianpay.mip.pay.query.service.IRefundPayOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {

	@Test
	public void contextLoads() {
	}

    @Reference(url = "dubbo://127.0.0.1:20889")
    IRefundPayOrderService refundPayOrderService;


    @Test
    public void testRefundApply() {
        RefundOrderRequest req = new RefundOrderRequest();
        req.setMchNo("402206220000027592");
        refundPayOrderService.getCount(req);
    }


}
