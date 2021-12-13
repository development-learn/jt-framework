package io.github.hylexus.jt.jt808.samples.debug.handler;

import io.github.hylexus.jt.config.Jt808ProtocolVersion;
import io.github.hylexus.jt.data.msg.BuiltinJt808MsgType;
import io.github.hylexus.jt.data.msg.MsgType;
import io.github.hylexus.jt.jt808.spec.Jt808Request;
import io.github.hylexus.jt.jt808.spec.Jt808ServerExchange;
import io.github.hylexus.jt.jt808.spec.Jt808Response;
import io.github.hylexus.jt.jt808.support.dispatcher.handler.Jt808RequestMsgHandler;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author hylexus
 */
@Component
public class SimpleLocationInfoUploadMsgHandler implements Jt808RequestMsgHandler<Jt808Response> {
    @Override
    public Set<MsgType> getSupportedMsgTypes() {
        return Set.of(BuiltinJt808MsgType.CLIENT_LOCATION_INFO_UPLOAD);
    }

    @Override
    public Set<Jt808ProtocolVersion> getSupportedVersions() {
        return Set.of(Jt808ProtocolVersion.VERSION_2019);
    }

    @Override
    public Jt808Response handleMsg(Jt808ServerExchange exchange) {
        final Jt808Request request = exchange.request();

        return exchange.response()
                .writeWord(request.flowId())
                .writeWord(BuiltinJt808MsgType.CLIENT_LOCATION_INFO_UPLOAD.getMsgId())
                .writeByte(0);
    }
}
