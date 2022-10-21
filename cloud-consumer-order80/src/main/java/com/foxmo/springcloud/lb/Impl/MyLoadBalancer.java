package com.foxmo.springcloud.lb.Impl;

import com.foxmo.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer implements LoadBalancer {
    //原子整型
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获取当前访问服务的次数
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        //自旋锁
        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));

        System.out.println("*********当前为第" + next + "次访问**********");
        return next;
    }

    /**
     * 获取实际调用的服务实例（自定意负载均衡算法）
     * @param serviceInstances  服务器集群列表
     * @return  实际调用的服务实例
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
