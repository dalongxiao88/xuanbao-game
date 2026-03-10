package org.come.nettyClient;

import java.io.IOException;

/**
 * 服务端到客户端的消息处理入口。
 */
public interface FromServerAction
{
    void controlMessFromServer(String message) throws IOException;
}
