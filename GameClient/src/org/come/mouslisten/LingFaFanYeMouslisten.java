package org.come.mouslisten;

import javax.swing.JTextField;
import javax.swing.JLabel;
import org.come.model.Lingbao;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.until.MessagrFlagUntil;
import javax.swing.text.BadLocationException;
import com.tool.Document.RichDocument;
import org.come.Frame.ZhuFrame;
import java.awt.Color;
import javax.swing.BorderFactory;
import org.come.Frame.LingbaoJframe;
import com.tool.role.RoleProperty;
import org.come.Jpanel.ZhuJpanel;
import com.tool.role.RoleLingFa;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LingFaFanYeMouslisten implements MouseListener
{
    public static boolean shijian;
    private boolean type;
    private int path;
    
    public LingFaFanYeMouslisten(boolean type, int path) {
        this.type = type;
        this.path = path;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Lingbao lingbao = this.getLingbao();
        if (lingbao != null) {
            if (e.getButton() == 3) {
                if (LingFaFanYeMouslisten.shijian) {
                    if (this.path >= 0) {
                        RoleLingFa.getRoleLingFa().choseuse(lingbao, true);
                        if (!lingbao.getBaotype().equals("法宝")) {
                            ZhuJpanel.setLabLingImg(lingbao.getSkin());
                        }
                    }
                    else {
                        RoleLingFa.getRoleLingFa().choseuse(lingbao, false);
                        if (!lingbao.getBaotype().equals("法宝")) {
                            ZhuJpanel.setLabLingImg(null);
                        }
                    }
                    RoleProperty.ResetEw();
                }
                else {
                    RoleLingFa.getRoleLingFa().choseBao = lingbao;
                    LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
                    for (int i = 0; i < LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().getLingbaoListLabel().length; ++i) {
                        JLabel jLabel = LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().getLingbaoListLabel()[i];
                        if (jLabel != null) {
                            jLabel.setBorder(BorderFactory.createEmptyBorder());
                        }
                        if (i == this.path) {
                            jLabel.setBorder(BorderFactory.createLineBorder(Color.red));
                        }
                    }
                }
            }
            else if (e.isShiftDown() && e.getButton() == 1) {
                try {
                    JTextField SendMes = ZhuFrame.getZhuJpanel().getSendMes();
                    ((RichDocument)SendMes.getDocument()).insertRich(SendMes.getCaretPosition(), 4, lingbao.getBaoid(), "[" + lingbao.getBaoname() + "]", "G", null);
                }
                catch (BadLocationException e2) {
                    e2.printStackTrace();
                }
            }
            else if (e.getButton() == 1) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE10)) {
                    if (lingbao.getGoodlock() == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt("该物品已加锁");
                    }
                    else {
                        lingbao.setGoodlock(1);
                        String sendMes = Agreement.UpdateLing(GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                        SendMessageUntil.toServer(sendMes);
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        ZhuFrame.getZhuJpanel().addPrompt("加锁成功");
                    }
                }
                else if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE11)) {
                    if (lingbao.getGoodlock() == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt("该物品未加锁");
                    }
                    else {
                        lingbao.setGoodlock(0);
                        String sendMes = Agreement.UpdateLing(GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                        SendMessageUntil.toServer(sendMes);
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                        ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Lingbao lingbao = this.getLingbao();
        if (lingbao != null) {
            ZhuFrame.getZhuJpanel().creatlingtext(lingbao);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().clearlingtext();
    }
    
    public Lingbao getLingbao() {
        Lingbao lingbao = null;
        if (this.path < 0) {
            if (this.type) {
                lingbao = RoleLingFa.getRoleLingFa().equipBao[0];
            }
            else if (this.path == -1) {
                lingbao = RoleLingFa.getRoleLingFa().equipBao[1];
            }
            else {
                lingbao = RoleLingFa.getRoleLingFa().equipBao[2];
            }
        }
        else if (this.type) {
            lingbao = RoleLingFa.getRoleLingFa().getlingbao(this.path);
        }
        else {
            lingbao = RoleLingFa.getRoleLingFa().getfabao(this.path);
        }
        return lingbao;
    }
    
    static {
        LingFaFanYeMouslisten.shijian = true;
    }
}
