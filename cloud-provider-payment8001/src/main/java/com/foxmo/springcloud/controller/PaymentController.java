package com.foxmo.springcloud.controller;

import com.foxmo.springcloud.entities.CommonResult;
import com.foxmo.springcloud.entities.Payment;
import com.foxmo.springcloud.service.PaymentService;
import jdk.nashorn.internal.ir.CallNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> createPayment(@RequestBody Payment payment){
        int result = paymentService.createPayment(payment);

        log.info("************** 插入结果：{} **************",result);

        if (result > 0){
            return new CommonResult<>(200,"插入成功,serverPort: " + serverPort,result);
        }else{
            return new CommonResult<>(444,"插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);

        log.info("************** 查询结果：{} **************",payment);

        if (payment != null){
            return new CommonResult<>(200,"查询成功,serverPort: " + serverPort,payment);
        }else{
            return new CommonResult<>(444,"没有查询到对应的数据，查询ID：" + id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //获取已注册带eureka的服务名称列表
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("**************** element:{} ****************",service);
        }
        //获取指定服务的所有实例的详细信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("******* serviceId:{}\t host:{} \t port:{} \t uri:{} *******",instance.getServiceId(),instance.getHost(),instance.getPort(),instance.getUri());
        }

        return this.discoveryClient;
    }
}
