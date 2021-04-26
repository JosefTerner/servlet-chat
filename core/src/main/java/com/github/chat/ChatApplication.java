package com.github.chat;

import com.github.chat.config.ServerConfig;
import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.payload.Envelope;
import com.github.chat.utils.WrapTomcat;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;



public class ChatApplication {

    private static final Logger log = LoggerFactory.getLogger(Envelope.class);

    public static void main(String[] args) {
        try {
            WrapTomcat t = ServerConfig.start();
                t.start();
                t.websocketRegistry(new WebsocketHandler());
                t.getServer().await();
        } catch (LifecycleException | ServletException e) {
            log.error(e.getMessage());
        }
    }
}
