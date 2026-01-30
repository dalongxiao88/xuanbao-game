package org.come.nettyClient;

public class VersionControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes) {
        System.out.println("与账号服务器连接成功!!--信息: " + mes);
    }
}
