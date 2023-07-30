package io.github.hylexus.jt.jt1078.support.codec;

import io.github.hylexus.jt.jt1078.spec.Jt1078Request;
import io.github.hylexus.jt.jt1078.spec.Jt1078Subscriber;
import io.github.hylexus.jt.jt1078.spec.Jt1078SubscriberDescriptor;
import io.github.hylexus.jt.jt1078.spec.Jt1078Subscription;
import io.github.hylexus.jt.jt1078.spec.exception.Jt1078SubscriberCloseException;
import io.github.hylexus.jt.jt1078.support.codec.impl.collector.H264ToFlvJt1078ChannelCollector;
import io.github.hylexus.jt.jt1078.support.codec.impl.collector.PassThroughJt1078ChannelCollector;
import io.github.hylexus.jt.jt1078.support.codec.impl.collector.RawDataJt1078ChannelCollector;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.stream.Stream;

public interface Jt1078ChannelCollector<S extends Jt1078Subscription> {

    Class<H264ToFlvJt1078ChannelCollector> H264_TO_FLV_COLLECTOR = H264ToFlvJt1078ChannelCollector.class;
    // Class<PassThroughJt1078ChannelCollector> PASS_THROUGH_COLLECTOR = PassThroughJt1078ChannelCollector.class;
    Class<RawDataJt1078ChannelCollector> RAW_DATA_COLLECTOR = RawDataJt1078ChannelCollector.class;

    void collect(Jt1078Request request);

    Jt1078Subscriber<S> doSubscribe(String sim, short channelNumber, Duration timeout);

    default void unsubscribe(String id) {
        this.unsubscribe(id, null);
    }

    void unsubscribe(String id, @Nullable Jt1078SubscriberCloseException reason);

    void unsubscribe(@Nullable Jt1078SubscriberCloseException reason);

    Stream<Jt1078SubscriberDescriptor> list();

    default void close() {
    }
}
