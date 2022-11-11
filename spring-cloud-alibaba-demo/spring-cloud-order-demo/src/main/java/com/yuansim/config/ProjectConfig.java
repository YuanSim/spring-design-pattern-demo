package com.yuansim.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 *  spring cloud + @RefreshScope 动态刷新， 需要属性有get set方法
 */
@Data
@RefreshScope
@Component
public class ProjectConfig {

    @Value(value = "${spring.cloud.order.project.parent}")
    private String parent;

    @Value(value = "${spring.application.name}")
    private String serverName;

    @Value(value = "${server.port}")
    private String serverPort;

    @Value(value = "${project.founder}")
    private String founder;

    @Value(value = "${project.createOn}")
    private String createdOn;
}
