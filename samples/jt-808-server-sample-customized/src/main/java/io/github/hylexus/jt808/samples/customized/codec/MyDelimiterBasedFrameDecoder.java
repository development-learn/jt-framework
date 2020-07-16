package io.github.hylexus.jt808.samples.customized.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

import static io.github.hylexus.jt.config.JtProtocolConstant.PACKAGE_DELIMITER;

public class MyDelimiterBasedFrameDecoder extends ByteToMessageDecoder {

    private final ByteBuf jt808Delimiter = Unpooled.copiedBuffer(new byte[]{PACKAGE_DELIMITER});
    private final ByteBuf suDelimiter = Unpooled.copiedBuffer(new byte[]{0x30, 0x31, 0x63, 0x64});
    private final int maxFrame;

    public MyDelimiterBasedFrameDecoder() {
        maxFrame = 2048;
    }

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    protected Object decode(@SuppressWarnings("UnusedParameters") ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if (in.readableBytes() <= 0) {
            return null;
        }

        final int indexOf7e = indexOf(in.readerIndex(), in, jt808Delimiter);
        final int indexOfSu = indexOf(in.readerIndex(), in, suDelimiter);
        final boolean process7e = indexOf7e >= 0
                && (indexOfSu < 0 || indexOf7e < indexOfSu);

        if (process7e) {
            return in.readRetainedSlice(indexOf7e + jt808Delimiter.capacity());
        } else {
            if (indexOfSu > 0) {
                return in.readRetainedSlice(indexOfSu);
            }
            if (indexOfSu == 0) {
                int next7e = indexOf(in.readerIndex(), in, jt808Delimiter);
                int nextSu = indexOf(in.readerIndex() + 1, in, suDelimiter);
                if (next7e > 0 && (nextSu < 0 || next7e < nextSu)) {
                    return in.readRetainedSlice(next7e);
                }
                if (nextSu > 0 && (next7e < 0 || nextSu < next7e)) {
                    return in.readRetainedSlice(nextSu);
                }
            }
        }

        if (in.readableBytes() > this.maxFrame) {
            throw new TooLongFrameException("frame length exceeds " + maxFrame + ": " + in.readableBytes());
        }
        return null;
    }

    protected int indexOf(int start, ByteBuf sources, ByteBuf target) {
        for (int i = start; i < sources.writerIndex(); i++) {
            int sourceIdx = i;
            int targetIdx;
            for (targetIdx = 0; targetIdx < target.capacity(); targetIdx++) {
                if (sources.getByte(sourceIdx) != target.getByte(targetIdx)) {
                    break;
                } else {
                    sourceIdx++;
                    if (sourceIdx == sources.writerIndex() && targetIdx != target.capacity() - 1) {
                        return -1;
                    }
                }
            }

            if (targetIdx == target.capacity()) {
                // Found the target from the sources!
                return i - sources.readerIndex();
            }
        }

        return -1;
    }
}
