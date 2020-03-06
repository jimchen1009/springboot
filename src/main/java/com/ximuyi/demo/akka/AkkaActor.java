package com.ximuyi.demo.akka;

import akka.actor.AbstractActor;
import akka.actor.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("akkaActor")
@Scope("prototype")
public class AkkaActor extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(AkkaActor.class);

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, message -> {
            logger.debug("收到消息：{}", message);
            sender().tell(new Status.Success("成功"), self());
        }).matchAny(other -> {
            logger.warn("未知消息：{}", other);
            sender().tell(new Status.Failure(new ClassNotFoundException()), self());
        }).build();
    }
}
