package io.github.hylexus.jt808.samples.customized.config;

import io.github.hylexus.jt.data.msg.MsgType;
import io.github.hylexus.jt.utils.HexStringUtils;
import io.github.hylexus.jt808.codec.BytesEncoder;
import io.github.hylexus.jt808.codec.Decoder;
import io.github.hylexus.jt808.converter.MsgTypeParser;
import io.github.hylexus.jt808.dispatcher.RequestMsgDispatcher;
import io.github.hylexus.jt808.msg.RequestMsgHeader;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RequestMsgWrapper;
import io.github.hylexus.jt808.session.Jt808SessionManager;
import io.github.hylexus.jt808.support.netty.Jt808ChannelHandlerAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static io.netty.util.ReferenceCountUtil.release;

/**
 * Created At 2020-07-15 21:22
 *
 * @author hylexus
 */
@Slf4j
public class MyJt808ChannelHandlerAdapter extends Jt808ChannelHandlerAdapter {
    private final Decoder decoder;
    private final RequestMsgDispatcher msgDispatcher;
    private final MsgTypeParser msgTypeParser;
    private final BytesEncoder bytesEncoder;
    private final Jt808SessionManager sessionManager;

    public MyJt808ChannelHandlerAdapter(
            RequestMsgDispatcher msgDispatcher, MsgTypeParser msgTypeParser,
            BytesEncoder bytesEncoder, Jt808SessionManager sessionManager) {
        super(msgDispatcher, msgTypeParser, bytesEncoder, sessionManager);

        this.sessionManager = sessionManager;
        this.decoder = new Decoder();
        this.msgDispatcher = msgDispatcher;
        this.msgTypeParser = msgTypeParser;
        this.bytesEncoder = bytesEncoder;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            final ByteBuf buf = (ByteBuf) msg;
            if (buf.readableBytes() <= 0 || buf.readableBytes() == 1) {
                return;
            }

            final byte[] unescaped = new byte[buf.readableBytes()];
            buf.readBytes(unescaped);
            final String hexString = HexStringUtils.bytes2HexString(unescaped);
            log.debug("\nreceive msg:");
            log.debug(">>>>>>>>>>>>>>> : {}", hexString);

            if (!hexString.endsWith("7E")) {
                // TODO 这里处理附件信息, 可以发送到其他队列里去处理
                log.info("这里处理附件信息:{}", hexString);
                return;
            }

            buf.resetReaderIndex();
            final int newLength = buf.readableBytes() - 1;
            ByteBuf striped = buf.copy(0, newLength);
            byte[] array = new byte[newLength];
            striped.readBytes(array);
            final byte[] escaped = this.bytesEncoder.doEscapeForReceive(array, 0, newLength);
            log.debug("[escaped] : {}", HexStringUtils.bytes2HexString(escaped));

            final RequestMsgMetadata metadata = decoder.parseMsgMetadata(escaped);
            final RequestMsgHeader header = metadata.getHeader();
            final int msgId = header.getMsgId();
            final Optional<MsgType> msgType = this.msgTypeParser.parseMsgType(msgId);
            if (!msgType.isPresent()) {
                log.warn("received unknown msg, msgId = {}({}). ignore.", msgId, HexStringUtils.int2HexString(msgId, 4));
                return;
            }
            metadata.setMsgType(msgType.get());

            final String terminalId = header.getTerminalId();
            sessionManager.persistenceIfNecessary(terminalId, ctx.channel());
            log.debug("[decode] : {}, terminalId={}, msg = {}", msgType.get(), terminalId, metadata);

            RequestMsgWrapper requestMsgWrapper = new RequestMsgWrapper().setMetadata(metadata);
            this.msgDispatcher.doDispatch(requestMsgWrapper);
        } catch (InvocationTargetException e) {
            // TODO exception handler
            log.error("InvocationTargetException", e);
            throw e;
        } catch (Throwable throwable) {
            // TODO exception handler
            log.error(throwable.getMessage(), throwable);
            throw throwable;
        } finally {
            release(msg);
        }
    }
}
