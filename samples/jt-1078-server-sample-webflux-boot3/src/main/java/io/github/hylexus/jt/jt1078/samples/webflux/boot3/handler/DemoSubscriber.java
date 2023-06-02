package io.github.hylexus.jt.jt1078.samples.webflux.boot3.handler;

import io.github.hylexus.jt.jt1078.spec.Jt1078Publisher;
import io.github.hylexus.jt.jt1078.spec.Jt1078Request;
import io.github.hylexus.jt.jt1078.spec.Jt1078Session;
import io.github.hylexus.jt.jt1078.spec.Jt1078SessionManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class DemoSubscriber {
    private final Jt1078Publisher publisher;
    private final Jt1078SessionManager sessionManager;
    private final String basePath;
    private final AtomicLong atomicLong = new AtomicLong();

    private final Map<String, OutputStream> outputStreamMap = new HashMap<>();

    public DemoSubscriber(Jt1078Publisher publisher, Jt1078SessionManager sessionManager, @Value("${jt1078.dump.collector-path}") String path) {
        this.publisher = publisher;
        this.sessionManager = sessionManager;
        this.basePath = path;
    }

    @PostConstruct
    public void init() {
        this.doSubscribe();
    }

    public void doSubscribe() {
        this.publisher.subscribe()
                .doOnNext(it -> {
                    final Jt1078Request request = it.getRequest();
                    request.rawByteBuf();
                    final Optional<Jt1078Session> bySim = sessionManager.findBySim(request.sim());
                    if (bySim.isEmpty()) {
                        return;
                    }
                    final String sim = bySim.get().sim();
                    final OutputStream stream = getOutputStream(sim);
                    log.info("----------------------------------------");
                    try {
                        stream.write(new byte[]{0x30, 0x31, 0x63, 0x64});
                        request.rawByteBuf().readBytes(stream, request.rawByteBuf().readableBytes());
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }).subscribe();
    }

    private synchronized OutputStream getOutputStream(String sim) {
        OutputStream stream = outputStreamMap.get(sim);
        if (stream == null) {
            try {
                String path = basePath + "/" + sim + "/" + atomicLong.incrementAndGet();
                final File file = new File(path);
                file.getParentFile().mkdirs();
                if (!file.exists()) {
                    file.createNewFile();
                }
                stream = new FileOutputStream(path);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
            outputStreamMap.put(sim, stream);
        }
        return stream;
    }


    @PreDestroy
    public void destroy() {
        for (final OutputStream outputStream : this.outputStreamMap.values()) {
            try {
                outputStream.close();
            } catch (IOException ignore) {
                // ignored
            }
        }
    }

}
