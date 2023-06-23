package io.github.hylexus.jt.jt1078.support.codec.impl.collector;

import io.github.hylexus.jt.jt1078.spec.Jt1078Subscription;
import reactor.core.publisher.FluxSink;

import java.time.LocalDateTime;

public class AbstractInternalSubscriber<S extends Jt1078Subscription>
        implements InternalSubscriber<S> {

    private final String id;

    private final String sim;

    private final short channel;

    private final String desc;
    private final LocalDateTime createdAt;

    private final FluxSink<S> sink;

    public AbstractInternalSubscriber(String id, String sim, short channel, String desc, LocalDateTime createdAt, FluxSink<S> sink) {
        this.id = id;
        this.sim = sim;
        this.channel = channel;
        this.desc = desc;
        this.createdAt = createdAt;
        this.sink = sink;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String sim() {
        return this.sim;
    }

    @Override
    public short channel() {
        return this.channel;
    }

    @Override
    public String desc() {
        return this.desc;
    }

    @Override
    public LocalDateTime createdAt() {
        return this.createdAt;
    }

    @Override
    public FluxSink<S> sink() {
        return this.sink;
    }
}
