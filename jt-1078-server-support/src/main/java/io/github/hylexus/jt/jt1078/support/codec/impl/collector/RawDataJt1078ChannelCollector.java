package io.github.hylexus.jt.jt1078.support.codec.impl.collector;

import io.github.hylexus.jt.jt1078.spec.Jt1078Request;
import io.github.hylexus.jt.jt1078.spec.impl.subscription.RawDataJt1078Subscription;
import reactor.core.publisher.FluxSink;

import java.time.LocalDateTime;

public class RawDataJt1078ChannelCollector
        extends AbstractChannelCollector<RawDataJt1078Subscription, RawDataSubscriber> {
    @Override
    protected void doCollect(Jt1078Request request) {
        final RawDataJt1078Subscription subscription = new RawDataJt1078Subscription(request);
        forEachSubscriber(rawDataSubscriber -> {
            rawDataSubscriber.sink().next(subscription);
        });
    }

    @Override
    protected RawDataSubscriber createSubscribe(String uuid, String sim, short channelNumber, FluxSink<RawDataJt1078Subscription> fluxSink) {
        return new RawDataSubscriber(uuid, sim, channelNumber, "request.body()", LocalDateTime.now(), fluxSink);
    }
}
