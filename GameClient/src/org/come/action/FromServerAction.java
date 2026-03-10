package org.come.action;

/**
 * 客户端收到服务端消息后的处理入口。
 */
public interface FromServerAction
{
    void controlMessFromServer(String message, String type);
}
