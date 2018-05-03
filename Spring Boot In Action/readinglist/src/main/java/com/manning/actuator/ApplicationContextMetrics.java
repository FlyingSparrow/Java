package com.manning.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/5/3
 */
@Component
public class ApplicationContextMetrics implements PublicMetrics {

    private ApplicationContext context;

    @Autowired
    public ApplicationContextMetrics(ApplicationContext context){
        this.context = context;
    }

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metricsList = new ArrayList();
        //记录启动时间
        metricsList.add(new Metric<Long>("spring.context.startup-date",
                context.getStartupDate()));
        //记录Bean定义数量
        metricsList.add(new Metric<Integer>("spring.beans.definitions",
                context.getBeanDefinitionCount()));
        //记录Bean数量
        metricsList.add(new Metric<Integer>("spring.beans",
                context.getBeanNamesForType(Object.class).length));
        //记录控制器类型的Bean数量
        metricsList.add(new Metric<Integer>("spring.controllers",
                context.getBeanNamesForAnnotation(Controller.class).length));


        return metricsList;
    }
}
