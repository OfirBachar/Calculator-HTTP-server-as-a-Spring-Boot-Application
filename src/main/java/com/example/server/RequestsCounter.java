package com.example.server;

import lombok.Getter;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Getter
public class RequestsCounter {
    private AtomicLong counter = new AtomicLong();

    public void encreas(){
        this.counter.addAndGet(1);
        ThreadContext.put("REQUEST_COUNTER", this.counter.toString());
    }
}
