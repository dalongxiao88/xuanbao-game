package org.come.XuanBao;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoPackJ_xuanyunpanel;
import org.come.XuanBao.XuanBaoPackJpanel;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.FormsManagement;
import org.come.mouslisten.GoodsMouslisten;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class XuanBaoPackGoodsMouslisten implements MouseListener {
    int id = -1;
    XuanBaoPackJpanel xuanBaoPackJpanel;
    XuanBaoPackJ_xuanyunpanel xuanBaoPackJ_xuanyunpanel;


    public XuanBaoPackGoodsMouslisten(int id, XuanBaoPackJpanel xuanBaoPackJpanel) {
        this.id = id;
        this.xuanBaoPackJpanel = xuanBaoPackJpanel;

    }


    public XuanBaoPackGoodsMouslisten(int id, XuanBaoPackJ_xuanyunpanel xuanBaoPackJ_xuanyunpanel) {
        this.id = id;
        this.xuanBaoPackJ_xuanyunpanel = xuanBaoPackJ_xuanyunpanel;

    }


    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            if (this.xuanBaoPackJpanel != null) {
                this.xuanBaoPackJpanel.xuanzhong(this.id);
            } else if (this.xuanBaoPackJ_xuanyunpanel != null) {
                this.xuanBaoPackJ_xuanyunpanel.xuanzhong(this.id);
            }
        } else if (e.getButton() == 3) {
            if (this.xuanBaoPackJpanel != null) {
                int k = this.id + this.xuanBaoPackJpanel.page * 12;
                if (k >= this.xuanBaoPackJpanel.getGoodstableList().size())
                    return;
            } else if (this.xuanBaoPackJ_xuanyunpanel != null) {
                int k = this.id + this.xuanBaoPackJ_xuanyunpanel.page * 12;
                if (k >= this.xuanBaoPackJ_xuanyunpanel.getGoodstableList().size())
                    return;
                Goodstable goodstable = this.xuanBaoPackJ_xuanyunpanel.getGoodstableList().get(k);
                // 检查物品是否为可以直接使用的类型(如1115或1116类型)
                if (goodstable != null && (goodstable.getType() == 1115 || goodstable.getType() == 1116)) {
                    // 调用物品使用方法
                    try {
                        GoodsMouslisten goodsMouslisten = new GoodsMouslisten(k);
                        goodsMouslisten.ManNoCombat(goodstable, goodstable.getType());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // 原有逻辑：镶嵌玄印到玄宝
                    XuanBao xuanBao = (RoleXuanBao.getRoleXuanBao()).choseBao;
                    if (goodstable != null && xuanBao != null) {
                        SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("US=" + xuanBao.getBid() + "=" + goodstable.getRgid()));
                    } else {
                        ZhuFrame.getZhuJpanel().addPrompt2("检查物品");
                    }
                }

            }

        }

    }


    public void mousePressed(MouseEvent e) {
    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
        if (this.xuanBaoPackJpanel != null) {
            this.xuanBaoPackJpanel.effect(this.id);
            int k = this.id + this.xuanBaoPackJpanel.page * 12;
            if (k >= this.xuanBaoPackJpanel.getGoodstableList().size()) {
                return;
            }
            Goodstable goodstable = this.xuanBaoPackJpanel.getGoodstableList().get(k);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);

            }
        } else if (this.xuanBaoPackJ_xuanyunpanel != null) {
            this.xuanBaoPackJ_xuanyunpanel.effect(this.id);
            int k = this.id + this.xuanBaoPackJ_xuanyunpanel.page * 12;
            if (k >= this.xuanBaoPackJ_xuanyunpanel.getGoodstableList().size())
                return;
            Goodstable goodstable = this.xuanBaoPackJ_xuanyunpanel.getGoodstableList().get(k);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }


    public void mouseExited(MouseEvent e) {
        if (this.xuanBaoPackJpanel != null)
            this.xuanBaoPackJpanel.effect(-1);
        if (this.xuanBaoPackJ_xuanyunpanel != null)
            this.xuanBaoPackJ_xuanyunpanel.effect(-1);
        FormsManagement.HideForm(24);
    }

}

