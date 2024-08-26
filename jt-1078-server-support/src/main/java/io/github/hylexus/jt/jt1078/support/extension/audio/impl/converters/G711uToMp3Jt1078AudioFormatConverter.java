package io.github.hylexus.jt.jt1078.support.extension.audio.impl.converters;

import io.github.hylexus.jt.jt1078.support.extension.audio.Jt1078AudioFormatConverter;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;

@Slf4j
public class G711uToMp3Jt1078AudioFormatConverter implements Jt1078AudioFormatConverter {

    private final G711uToPcmJt1078AudioFormatConverter g711uToPcmConverter = new G711uToPcmJt1078AudioFormatConverter();
    private final PcmToMp3Jt1078AudioFormatConverter pcmToMp3Converter = new PcmToMp3Jt1078AudioFormatConverter();

    @Nonnull
    @Override
    public Jt1078AudioData convert(ByteBuf stream, AudioFormatOptions sourceOptions) {
        if (isEmptyStream(stream)) {
            log.warn("Jt1078AudioFormatConverter receive empty stream !!!");
            return Jt1078AudioData.empty();
        }
        try (Jt1078AudioData pcmData = this.g711uToPcmConverter.convert(stream, sourceOptions)) {
            return this.pcmToMp3Converter.convert(pcmData.payload(), pcmData.payloadOptions());
        }
    }

    @Override
    public void close() {
        this.pcmToMp3Converter.close();
    }
}
