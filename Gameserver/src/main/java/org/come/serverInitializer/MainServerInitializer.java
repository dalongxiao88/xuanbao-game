package org.come.serverInitializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import org.come.handler.MainServerHandler;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.codec.string.StringDecoder;
import java.nio.charset.Charset;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelInitializer;

public class MainServerInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("logging", new LoggingHandler(LogLevel.INFO));
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()));
        pipeline.addLast(new ChannelHandler[] { new IdleStateHandler(180L, 0L, 0L, TimeUnit.SECONDS) });
        pipeline.addLast("decoder", new StringDecoder(Charset.forName("utf-8")));
        pipeline.addLast("encoder", new StringEncoder(Charset.forName("utf-8")));
        pipeline.addLast("handler", new MainServerHandler());
    }
}
