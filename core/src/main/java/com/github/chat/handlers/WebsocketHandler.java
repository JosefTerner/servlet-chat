package com.github.chat.handlers;

import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.Token;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;

public class WebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private final Broker broker;

    private final WebsocketConnectionPool websocketConnectionPool;

    public WebsocketHandler(WebsocketConnectionPool websocketConnectionPool, Broker broker) {
        this.websocketConnectionPool = websocketConnectionPool;
        this.broker = broker;
    }

    @OnMessage
    public void messages(Session session, String payload){
        try {
            Envelope env = JsonHelper.fromJson(payload, Envelope.class).get();
            switch(env.getTopic()) {
                case auth:
                    Token result = TokenProvider.decode(env.getPayload());
                    System.out.println(result);
                    long id = result.getId();
                    System.out.println(id);
                    this.websocketConnectionPool.addSession(id, session);
                    break;
                case message:
                    this.broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    break;
                default:
            }
        }catch (Throwable e){
            //todo: single send about error to user
            log.warn("Enter: {}", e.getMessage());
        }
    }

}
