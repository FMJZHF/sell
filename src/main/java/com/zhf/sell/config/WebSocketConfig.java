package com.zhf.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket  配置
 */
@Component
public class WebSocketConfig {

    //TODO 测试websocket消息推送是，如果有连接失败，将注释释放
//    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
