package com.ximuyi.demo.akka;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

@Component
public class AkkaRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AkkaRunner.class);

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    @Override
    public void run(String... args) throws Exception {
        Props props = springExtension.props("akkaActor");
        ActorRef actorRef = actorSystem.actorOf(props, "Name");
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Future<Object> awaitable = Patterns.ask(actorRef, "测试信息，这里是 AkkaRunner.", Timeout.durationToTimeout(duration));
        logger.info("Akka 执行结果: " + Await.result(awaitable, duration));
    }
}
