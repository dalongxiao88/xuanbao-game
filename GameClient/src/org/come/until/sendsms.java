package org.come.until;

/**
 * 客户端短信发送占位实现。
 *
 * 安全修复：
 * 原实现会在客户端直接携带并使用第三方短信平台凭据，
 * 这会把短信网关账号与密码暴露到分发包中。
 * 当前客户端侧统一禁用直连短信网关，验证码发送应由服务端处理。
 */
public class sendsms
{
    public static String sendUNtil(String phone) {
        return "error";
    }
}
