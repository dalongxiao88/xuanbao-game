package org.come.nettyClient;

import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import io.netty.util.concurrent.Future;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.codec.string.StringDecoder;
import java.nio.charset.Charset;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;

public class GameClient
{
    public static int lianhua;
    private NioEventLoopGroup workGroup;
    private Channel channel;
    private Bootstrap bootstrap;
    private String ip;
    private int port;
    private static String privateStr;
    int a;
    
    public GameClient(String ip, int port) {
        this.workGroup = new NioEventLoopGroup();
        this.a = 0;
        this.ip = ip;
        this.port = port;
    }
    
    public static String getPrivateStr() {
        return GameClient.privateStr;
    }
    
    public static void setPrivateStr(String privateStr) {
        GameClient.privateStr = privateStr;
    }
    
    public void start() {
        try {
            this.bootstrap = new Bootstrap();
            ((Bootstrap)((Bootstrap)((Bootstrap)this.bootstrap.group(this.workGroup)).channel(NioSocketChannel.class)).option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true))).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
                    p.addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()));
                    p.addLast("decoder", new StringDecoder(Charset.forName("UTF-8")));
                    p.addLast("encoder", new StringEncoder(Charset.forName("UTF-8")));
                    p.addLast(new ChannelHandler[] { new IdleStateHandler(0L, 30L, 0L, TimeUnit.SECONDS) });
                    p.addLast(new ChannelHandler[] { new GameClientHandler(GameClient.this) });
                }
            });
            this.doConnect();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void doConnect() throws Exception {
        if (this.channel != null && this.channel.isActive()) {
            return;
        }
        ChannelFuture future = this.bootstrap.connect(this.ip, this.port);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    GameClient.this.a = 0;
                    GameClient.this.channel = futureListener.channel();
                    System.out.println("****** ip: " + GameClient.this.ip + " 端口: " + GameClient.this.port + " 连接中.......");
                }
                else {
                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                GameClient.this.doConnect();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 10L, TimeUnit.SECONDS);
                }
            }
        });
    }
    
    public void sendData() throws Exception {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; ++i) {
            if (this.channel != null && this.channel.isActive()) {
                String content = "client msg " + i;
                this.channel.writeAndFlush(content + "\n");
            }
            Thread.sleep((long)random.nextInt(20000));
        }
    }
    
    public void connectClose(ChannelHandlerContext ctx) {
        ctx.close();
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public int getPort() {
        return this.port;
    }
    
    static {
        GameClient.lianhua = 0;
    }
}
