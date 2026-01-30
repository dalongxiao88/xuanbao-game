package org.come.action.mount;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.entity.ShouHu;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountShouHuAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.startsWith("open")) {
            ShouHu shouHu = new ShouHu();
            if (loginResult.getZhongtian() == null) {
                shouHu.setZhongtianlvl(-1);
                shouHu.setZhongtianmount("-1*-1");
                shouHu.setZhongtianxiuweidian(-1);
            }
            else {
                shouHu.setZhongtianlvl(Integer.parseInt(loginResult.getZhongtian().split("\\|")[0]));
                shouHu.setZhongtianxiuweidian(Integer.parseInt(loginResult.getZhongtian().split("\\|")[1]));
                shouHu.setZhongtianmount(loginResult.getZhongtian().split("\\|")[2]);
            }
            if (loginResult.getQinglong() == null) {
                shouHu.setQinglonglvl(-1);
                shouHu.setQinglongmount(-1);
                shouHu.setQinglongxiuweidian(-1);
            }
            else {
                shouHu.setQinglonglvl(Integer.parseInt(loginResult.getQinglong().split("\\|")[0]));
                shouHu.setQinglongxiuweidian(Integer.parseInt(loginResult.getQinglong().split("\\|")[1]));
                shouHu.setQinglongmount(Integer.parseInt(loginResult.getQinglong().split("\\|")[2]));
            }
            if (loginResult.getBaihu() == null) {
                shouHu.setBaihulvl(-1);
                shouHu.setBaihumount(-1);
                shouHu.setBaihuxiuweidian(-1);
            }
            else {
                shouHu.setBaihulvl(Integer.parseInt(loginResult.getBaihu().split("\\|")[0]));
                shouHu.setBaihuxiuweidian(Integer.parseInt(loginResult.getBaihu().split("\\|")[1]));
                shouHu.setBaihumount(Integer.parseInt(loginResult.getBaihu().split("\\|")[2]));
            }
            if (loginResult.getXuanwu() == null) {
                shouHu.setXuanwulvl(-1);
                shouHu.setXuanwumount(-1);
                shouHu.setXuanwuxiuweidian(-1);
            }
            else {
                shouHu.setXuanwulvl(Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]));
                shouHu.setXuanwuxiuweidian(Integer.parseInt(loginResult.getXuanwu().split("\\|")[1]));
                shouHu.setXuanwumount(Integer.parseInt(loginResult.getXuanwu().split("\\|")[2]));
            }
            if (loginResult.getZhuque() == null) {
                shouHu.setZhuquelvl(-1);
                shouHu.setZhuquemount(-1);
                shouHu.setZhuquexiuweidian(-1);
            }
            else {
                shouHu.setZhuquelvl(Integer.parseInt(loginResult.getZhuque().split("\\|")[0]));
                shouHu.setZhuquexiuweidian(Integer.parseInt(loginResult.getZhuque().split("\\|")[1]));
                shouHu.setZhuquemount(Integer.parseInt(loginResult.getZhuque().split("\\|")[2]));
            }
            shouHu.setShouhu(loginResult.getShouhu());
            shouHu.is = true;
            String sendmes = Agreement.getAgreement().ShouHuAgreement(GsonUtil.getGsonUtil().getgson().toJson(shouHu));
            SendMessage.sendMessageToSlef(ctx, sendmes);
            return;
        }
        else {
            if (loginResult == null) {
                return;
            }
            ShouHu shouHu = (ShouHu)GsonUtil.getGsonUtil().getgson().fromJson(message, ShouHu.class);
            String[] zhongtian = (String[])((loginResult.getZhongtian() == null) ? null : loginResult.getZhongtian().split("\\|"));
            String[] qianglong = (String[])((loginResult.getZhongtian() == null) ? null : loginResult.getQinglong().split("\\|"));
            String[] baihu = (String[])((loginResult.getZhongtian() == null) ? null : loginResult.getBaihu().split("\\|"));
            String[] xuanwu = (String[])((loginResult.getZhongtian() == null) ? null : loginResult.getXuanwu().split("\\|"));
            String[] zhuque = (String[])((loginResult.getZhongtian() == null) ? null : loginResult.getZhuque().split("\\|"));
            if (zhongtian == null) {
                loginResult.setZhongtian(shouHu.getZhongtianlvl() + "|" + shouHu.getZhongtianxiuweidian() + "|" + shouHu.getZhongtianmount());
            }
            else {
                if (shouHu.getZhongtianlvl() != -1) {
                    zhongtian[0] = String.valueOf(shouHu.getZhongtianlvl());
                }
                if (shouHu.getZhongtianxiuweidian() != -1) {
                    zhongtian[1] = String.valueOf(shouHu.getZhongtianxiuweidian());
                }
                if (shouHu.getZhongtianmount() != null) {
                    zhongtian[2] = shouHu.getZhongtianmount();
                }
                loginResult.setZhongtian(zhongtian[0] + "|" + zhongtian[1] + "|" + zhongtian[2]);
            }
            if (qianglong == null) {
                loginResult.setQinglong(shouHu.getQinglonglvl() + "|" + shouHu.getQinglongxiuweidian() + "|" + shouHu.getQinglongmount());
            }
            else {
                if (shouHu.getQinglonglvl() != -1) {
                    qianglong[0] = String.valueOf(shouHu.getQinglonglvl());
                }
                if (shouHu.getQinglongxiuweidian() != -1) {
                    qianglong[1] = String.valueOf(shouHu.getQinglongxiuweidian());
                }
                if (shouHu.getQinglongmount() != -1) {
                    qianglong[2] = String.valueOf(shouHu.getQinglongmount());
                }
                loginResult.setQinglong(qianglong[0] + "|" + qianglong[1] + "|" + qianglong[2]);
            }
            if (baihu == null) {
                loginResult.setBaihu(shouHu.getBaihulvl() + "|" + shouHu.getBaihuxiuweidian() + "|" + shouHu.getBaihumount());
            }
            else {
                if (shouHu.getBaihulvl() != -1) {
                    baihu[0] = String.valueOf(shouHu.getBaihulvl());
                }
                if (shouHu.getBaihuxiuweidian() != -1) {
                    baihu[1] = String.valueOf(shouHu.getBaihuxiuweidian());
                }
                if (shouHu.getBaihumount() != -1) {
                    baihu[2] = String.valueOf(shouHu.getBaihumount());
                }
                loginResult.setBaihu(baihu[0] + "|" + baihu[1] + "|" + baihu[2]);
            }
            if (xuanwu == null) {
                loginResult.setXuanwu(shouHu.getXuanwulvl() + "|" + shouHu.getXuanwuxiuweidian() + "|" + shouHu.getXuanwumount());
            }
            else {
                if (shouHu.getXuanwulvl() != -1) {
                    xuanwu[0] = String.valueOf(shouHu.getXuanwulvl());
                }
                if (shouHu.getXuanwuxiuweidian() != -1) {
                    xuanwu[1] = String.valueOf(shouHu.getXuanwuxiuweidian());
                }
                if (shouHu.getXuanwumount() != -1) {
                    xuanwu[2] = String.valueOf(shouHu.getXuanwumount());
                }
                loginResult.setXuanwu(xuanwu[0] + "|" + xuanwu[1] + "|" + xuanwu[2]);
            }
            if (zhuque == null) {
                loginResult.setZhuque(shouHu.getZhuquelvl() + "|" + shouHu.getZhuquexiuweidian() + "|" + shouHu.getZhuquemount());
            }
            else {
                if (shouHu.getZhuquelvl() != -1) {
                    zhuque[0] = String.valueOf(shouHu.getZhuquelvl());
                }
                if (shouHu.getZhuquexiuweidian() != -1) {
                    zhuque[1] = String.valueOf(shouHu.getZhuquexiuweidian());
                }
                if (shouHu.getZhuquemount() != -1) {
                    zhuque[2] = String.valueOf(shouHu.getZhuquemount());
                }
                loginResult.setZhuque(zhuque[0] + "|" + zhuque[1] + "|" + zhuque[2]);
            }
            if (shouHu.getShouhu() != -1) {
                loginResult.setShouhu(shouHu.getShouhu());
            }
            shouHu.setZhongtianlvl(Integer.parseInt(loginResult.getZhongtian().split("\\|")[0]));
            shouHu.setZhongtianxiuweidian(Integer.parseInt(loginResult.getZhongtian().split("\\|")[1]));
            shouHu.setZhongtianmount(loginResult.getZhongtian().split("\\|")[2]);
            shouHu.setQinglonglvl(Integer.parseInt(loginResult.getQinglong().split("\\|")[0]));
            shouHu.setQinglongxiuweidian(Integer.parseInt(loginResult.getQinglong().split("\\|")[1]));
            shouHu.setQinglongmount(Integer.parseInt(loginResult.getQinglong().split("\\|")[2]));
            shouHu.setZhuquelvl(Integer.parseInt(loginResult.getZhuque().split("\\|")[0]));
            shouHu.setZhuquexiuweidian(Integer.parseInt(loginResult.getZhuque().split("\\|")[1]));
            shouHu.setZhuquemount(Integer.parseInt(loginResult.getZhuque().split("\\|")[2]));
            shouHu.setBaihulvl(Integer.parseInt(loginResult.getBaihu().split("\\|")[0]));
            shouHu.setBaihuxiuweidian(Integer.parseInt(loginResult.getBaihu().split("\\|")[1]));
            shouHu.setBaihumount(Integer.parseInt(loginResult.getBaihu().split("\\|")[2]));
            shouHu.setXuanwulvl(Integer.parseInt(loginResult.getXuanwu().split("\\|")[0]));
            shouHu.setXuanwuxiuweidian(Integer.parseInt(loginResult.getXuanwu().split("\\|")[1]));
            shouHu.setXuanwumount(Integer.parseInt(loginResult.getXuanwu().split("\\|")[2]));
            String sendmes2 = Agreement.getAgreement().ShouHuAgreement(GsonUtil.getGsonUtil().getgson().toJson(shouHu));
            SendMessage.sendMessageToSlef(ctx, sendmes2);
            return;
        }
    }
}
