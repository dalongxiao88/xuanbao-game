package org.come.socket;

import java.util.Enumeration;
import java.net.NetworkInterface;
import org.come.until.AESUtil;
import org.come.until.CreateTextUtil;
import io.netty.util.concurrent.Future;
import org.come.until.Util;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.codec.string.StringDecoder;
import java.nio.charset.StandardCharsets;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import java.math.BigDecimal;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;

public class GameClient
{
    public static String VERSION;
    public static String BT;
    public static String atid;
    public static String userid;
    public static int lianhua;
    private NioEventLoopGroup workGroup;
    private Channel channel;
    private Bootstrap bootstrap;
    public static String[] potAndIpStrings;
    private static String privateStr;
    public static String username;
    public static String userpasw;
    public static String ip;
    public static int port;
    private String serverType;
    public static String payurl;
    public static String Mac;
    public static String DXCL;
    public static BigDecimal tempreId;
    int a;
    
    public GameClient() {
        this.workGroup = new NioEventLoopGroup();
        this.a = 0;
        GameClient.ip = "81.70.45.106";
        GameClient.port = 7105;
    }
    
    public GameClient(String ip, int port) {
        this.workGroup = new NioEventLoopGroup();
        this.a = 0;
        GameClient.ip = ip;
        GameClient.port = port;
    }
    
    public static String getPrivateStr() {
        return GameClient.privateStr;
    }
    
    public static void setPrivateStr(String privateStr) {
        GameClient.privateStr = privateStr;
    }
    
    public void start() {
        try {
            (this.bootstrap = new Bootstrap()).option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            ((Bootstrap)((Bootstrap)((Bootstrap)this.bootstrap.group(this.workGroup)).channel(NioSocketChannel.class)).option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true))).handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
                    p.addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()));
                    p.addLast("decoder", new StringDecoder(StandardCharsets.UTF_8));
                    p.addLast("encoder", new StringEncoder(StandardCharsets.UTF_8));
                    p.addLast(new ChannelHandler[] { new IdleStateHandler(0L, 30L, 0L, TimeUnit.SECONDS) });
                    p.addLast(new ChannelHandler[] { new GameClientHandler(GameClient.this) });
                }
            });
            this.bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
            this.bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
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
        SendMessageUntil.gameServerKey = GameClient.ip + "|" + GameClient.port;
        ChannelFuture future = this.bootstrap.connect(GameClient.ip, GameClient.port);
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    GameClient.this.a = 0;
                    GameClient.this.channel = futureListener.channel();
                    if (GameClient.DXCL != null) {
                        System.err.println("断线重新连接");
                        try {
                            Thread.sleep((long)(100 + Util.random.nextInt(40) * 50));
                        }
                        catch (Exception ex) {}
                        String serverMes = Agreement.getAgreement().enterGameAgreement(GameClient.DXCL);
                        GameClient.this.channel.writeAndFlush(serverMes + "\n");
                    }
                }
                else {
                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (GameClient.this.a <= 30) {
                                    GameClient this$0 = GameClient.this;
                                    ++GameClient.this.a;
                                    GameClient.this.doConnect();
                                }
                                else {
                                    System.exit(0);
                                }
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
    
    public static String[] getServerIpAndPort() throws Exception {
        GameClient.Mac = getLocalMac();
        String str = AESUtil.AESJDKDncode(CreateTextUtil.getContent("resource/other/opacity-inif.pat"), ">LA~h4FNKPMJW077jxO");
        GameClient.privateStr = AESUtil.AESJDKDncode(CreateTextUtil.getContent("resource/other/opacity-selfinif.pat"), str);
        String msg1 = AESUtil.AESJDKDncode(CreateTextUtil.getContent("resource/other/init.txt"), GameClient.privateStr);
        String[] mes1 = msg1.toString().split(";");
        int sum = Integer.parseInt(mes1[0].split("=")[1]);
        String[] portAndIp = new String[sum * 8];
        for (int i = 0; i < portAndIp.length; ++i) {
            if (i + 1 < mes1.length) {
                portAndIp[i] = mes1[i + 1].split("=")[1];
            }
        }
        return portAndIp;
    }
    
    public static String getLocalMac() throws Exception {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
                if (!netInterface.isLoopback() && !netInterface.isVirtual() && !netInterface.isPointToPoint()) {
                    if (!netInterface.isUp()) {
                        continue;
                    }
                    else {
                        mac = netInterface.getHardwareAddress();
                        if (mac != null) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < mac.length; ++i) {
                                sb.append(String.format("%02X%s", new Object[] { Byte.valueOf(mac[i]), (i < mac.length - 1) ? "-" : "" }));
                            }
                            if (sb.length() > 0) {
                                return sb.toString();
                            }
                            else {
                                continue;
                            }
                        }
                        else {
                            continue;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return "";
    }
    
    public String getIp() {
        return GameClient.ip;
    }
    
    public void setIp(String ip) {
        GameClient.ip = ip;
    }
    
    public int getPort() {
        return GameClient.port;
    }
    
    public void setPort(int port) {
        GameClient.port = port;
    }
    
    static {
        GameClient.VERSION = "version250110";
        GameClient.BT = "大话西游Ⅱ";
        GameClient.lianhua = 0;
        GameClient.DXCL = null;
        GameClient.tempreId = null;
    }
}
