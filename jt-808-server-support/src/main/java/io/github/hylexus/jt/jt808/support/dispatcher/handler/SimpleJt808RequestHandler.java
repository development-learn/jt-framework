package io.github.hylexus.jt.jt808.support.dispatcher.handler;

import io.github.hylexus.jt.jt808.Jt808ProtocolVersion;
import io.github.hylexus.jt.jt808.spec.Jt808Response;
import io.github.hylexus.jt.jt808.spec.Jt808ServerExchange;
import io.github.hylexus.jt.jt808.spec.MsgType;
import io.github.hylexus.jt.jt808.support.dispatcher.MultipleVersionSupport;
import io.github.hylexus.jt.jt808.support.dispatcher.mapping.SimpleJt808RequestHandlerHandlerMapping;

import java.util.Set;

/**
 * @author hylexus
 * @see SimpleJt808RequestHandlerHandlerMapping
 */
public interface SimpleJt808RequestHandler<T> extends MultipleVersionSupport {

    /**
     * @return 该处理器可以处理什么类型的消息
     */
    Set<MsgType> getSupportedMsgTypes();

    /**
     * @return 该处理器可以处理的协议类型(默认为ALL)
     */
    @Override
    default Set<Jt808ProtocolVersion> getSupportedVersions() {
        return MultipleVersionSupport.super.getSupportedVersions();
    }

    /**
     * 处理消息
     *
     * @return {@link Jt808Response} 或 可以转换为 {@link Jt808Response} 的类型
     */
    T handleMsg(Jt808ServerExchange exchange);

}
