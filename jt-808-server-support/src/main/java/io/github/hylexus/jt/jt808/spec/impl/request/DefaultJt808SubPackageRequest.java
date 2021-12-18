package io.github.hylexus.jt.jt808.spec.impl.request;

import io.github.hylexus.jt.jt808.spec.Jt808RequestHeader;
import io.github.hylexus.jt.jt808.spec.Jt808SubPackageRequest;
import io.github.hylexus.jt.jt808.spec.MsgType;
import io.netty.buffer.ByteBuf;

/**
 * @author hylexus
 */
public class DefaultJt808SubPackageRequest
        extends DefaultJt808Request
        implements Jt808SubPackageRequest {

    private final Jt808SubPackage subPackage;

    public DefaultJt808SubPackageRequest(
            MsgType msgType, Jt808RequestHeader header, ByteBuf rawByteBuf,
            ByteBuf body, byte originalCheckSum, byte calculatedCheckSum, Jt808SubPackage subPackageSpec) {
        super(msgType, header, rawByteBuf, body, originalCheckSum, calculatedCheckSum);
        this.subPackage = subPackageSpec;
    }

    @Override
    public Jt808SubPackage subPackage() {
        return subPackage;
    }
}