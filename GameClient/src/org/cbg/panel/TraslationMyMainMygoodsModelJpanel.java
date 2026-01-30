package org.cbg.panel;

import org.cbg.mouslisten.CBGmoveMouslisten;
import java.util.List;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.cbg.bean.GoodsBackBean;
import org.come.until.GoodsListFromServerUntil;
import org.cbg.entity.Roleorder;
import org.cbg.frame.TrslationMainJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationMyMainMygoodsModelJpanel extends JPanel
{
    private JLabel zhanshidikuang;
    private JLabel zhangshi;
    private JLabel mingzi;
    private JLabel dengji;
    private JLabel quanxuankuang;
    private JLabel gouxuan;
    private TrslationBtn kuaisuquhui;
    
    public TraslationMyMainMygoodsModelJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(304, 50));
            this.setOpaque(false);
            this.setLayout(null);
            this.zhanshidikuang = new JLabel();
            this.zhangshi = new JLabel();
            this.mingzi = new JLabel("名字");
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            this.dengji = new JLabel("120");
            this.kuaisuquhui = new TrslationBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "快速取回");
            this.zhanshidikuang.setBorder(null);
            this.zhangshi.setBorder(null);
            this.mingzi.setBorder(null);
            this.dengji.setBorder(null);
            this.kuaisuquhui.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhangshi.setBounds(40, 5, 39, 39);
            this.mingzi.setBounds(190, 5, 100, 39);
            this.gouxuan.setBounds(13, 12, 15, 15);
            this.quanxuankuang.setBounds(13, 12, 15, 15);
            this.dengji.setBounds(353, 5, 39, 39);
            this.kuaisuquhui.setBounds(470, 15, 68, 17);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhangshi.setIcon(CutButtonImage.getImage("img/xy2uiimg/217.png", 39, 39));
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.zhanshidikuang.setOpaque(false);
            this.zhangshi.setOpaque(false);
            this.mingzi.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.dengji.setOpaque(false);
            this.kuaisuquhui.setOpaque(false);
            this.zhanshidikuang.setName("1");
            this.gouxuan.setName("1");
            this.mingzi.setForeground(Color.white);
            this.dengji.setForeground(Color.white);
            this.add(this.zhanshidikuang);
            this.add(this.zhangshi);
            this.add(this.mingzi);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.add(this.dengji);
            this.add(this.kuaisuquhui);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
                    if (TraslationMyMainMygoodsModelJpanel.this.gouxuan.getName().equals("1")) {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == 10) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("0");
                        }
                    }
                    else {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != 10) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(null);
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("1");
                        }
                    }
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(304, 50));
            this.setOpaque(false);
            this.setLayout(null);
            this.zhanshidikuang = new JLabel();
            this.zhangshi = new JLabel();
            this.mingzi = new JLabel("名字");
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            this.dengji = new JLabel("120");
            this.kuaisuquhui = new TrslationBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "快速取回");
            this.zhanshidikuang.setBorder(null);
            this.zhangshi.setBorder(null);
            this.mingzi.setBorder(null);
            this.dengji.setBorder(null);
            this.kuaisuquhui.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhangshi.setBounds(40, 5, 39, 39);
            this.mingzi.setBounds(190, 5, 100, 39);
            this.gouxuan.setBounds(13, 12, 15, 15);
            this.quanxuankuang.setBounds(13, 12, 15, 15);
            this.dengji.setBounds(353, 5, 39, 39);
            this.kuaisuquhui.setBounds(470, 15, 68, 17);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhangshi.setIcon(CutButtonImage.getImage("img/xy2uiimg/217.png", 39, 39));
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.zhanshidikuang.setOpaque(false);
            this.zhangshi.setOpaque(false);
            this.mingzi.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.dengji.setOpaque(false);
            this.kuaisuquhui.setOpaque(false);
            this.zhanshidikuang.setName("1");
            this.gouxuan.setName("1");
            this.mingzi.setForeground(Color.white);
            this.dengji.setForeground(Color.white);
            this.add(this.zhanshidikuang);
            this.add(this.zhangshi);
            this.add(this.mingzi);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.add(this.dengji);
            this.add(this.kuaisuquhui);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
                    if (TraslationMyMainMygoodsModelJpanel.this.gouxuan.getName().equals("1")) {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == 10) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("0");
                        }
                    }
                    else {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != 10) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(null);
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("1");
                        }
                    }
                }
            });
        }
    }
    
    public TraslationMyMainMygoodsModelJpanel(Roleorder roleorder) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(304, 50));
            this.setOpaque(false);
            this.setLayout(null);
            this.zhanshidikuang = new JLabel();
            this.zhangshi = new JLabel();
            this.mingzi = new JLabel(roleorder.getSalename());
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            this.dengji = new JLabel();
            this.kuaisuquhui = new TrslationBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "快速取回");
            this.zhanshidikuang.setBorder(null);
            this.zhangshi.setBorder(null);
            this.mingzi.setBorder(null);
            this.dengji.setBorder(null);
            this.kuaisuquhui.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhangshi.setBounds(40, 5, 39, 39);
            this.mingzi.setBounds(190, 5, 100, 39);
            this.gouxuan.setBounds(13, 12, 15, 15);
            this.quanxuankuang.setBounds(13, 12, 15, 15);
            this.dengji.setBounds(353, 5, 39, 39);
            this.kuaisuquhui.setBounds(470, 15, 68, 17);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhangshi.setIcon(CutButtonImage.getCBG((int)roleorder.getSaletype(), roleorder.getSaleskin(), 39, 39));
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.zhanshidikuang.setOpaque(false);
            this.zhangshi.setOpaque(false);
            this.mingzi.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.dengji.setOpaque(false);
            this.kuaisuquhui.setOpaque(false);
            this.zhanshidikuang.setName(roleorder.getSaleid().toString());
            this.gouxuan.setName("1");
            this.mingzi.setForeground(Color.white);
            this.dengji.setForeground(Color.white);
            this.dengji.setText(setBack(roleorder.getStatus()));
            if ((int)roleorder.getStatus() != 3) {
                this.kuaisuquhui.setVisible(false);
                this.quanxuankuang.setVisible(false);
                this.gouxuan.setVisible(false);
            }
            else {
                this.kuaisuquhui.setVisible(true);
                this.quanxuankuang.setVisible(true);
                this.gouxuan.setVisible(true);
            }
            this.add(this.zhanshidikuang);
            this.add(this.zhangshi);
            this.add(this.mingzi);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.add(this.dengji);
            this.add(this.kuaisuquhui);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
                    if (TraslationMyMainMygoodsModelJpanel.this.gouxuan.getName().equals("1")) {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax()) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("0");
                        }
                    }
                    else {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax()) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(null);
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("1");
                        }
                    }
                }
            });
            this.kuaisuquhui.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
                    if (packNumber > 0) {
                        GoodsBackBean backBean = new GoodsBackBean();
                        List<BigDecimal> mesIdList = new ArrayList<>();
                        mesIdList.add(new BigDecimal(TraslationMyMainMygoodsModelJpanel.this.zhanshidikuang.getName()));
                        backBean.setIds(mesIdList);
                        String sendmes = Agreement.getAgreement().searchGoodsBackAgreement(GsonUtil.getGsonUtil().getgson().toJson(backBean));
                        SendMessageUntil.toServer(sendmes);
                        if ("0".equals(TraslationMyMainMygoodsModelJpanel.this.gouxuan.getName())) {
                            TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        }
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGeshuMax(TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax() - 1);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("1");
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setVisible(false);
                        TraslationMyMainMygoodsModelJpanel.this.kuaisuquhui.setVisible(false);
                        TraslationMyMainMygoodsModelJpanel.this.quanxuankuang.setVisible(false);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("背包格数不足");
                    }
                }
            });
            this.zhangshi.addMouseListener(new CBGmoveMouslisten(roleorder.getSaletype(), roleorder.getOtherid()));
        }
        else {
            this.setPreferredSize(new Dimension(304, 50));
            this.setOpaque(false);
            this.setLayout(null);
            this.zhanshidikuang = new JLabel();
            this.zhangshi = new JLabel();
            this.mingzi = new JLabel(roleorder.getSalename());
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            this.dengji = new JLabel();
            this.kuaisuquhui = new TrslationBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "快速取回");
            this.zhanshidikuang.setBorder(null);
            this.zhangshi.setBorder(null);
            this.mingzi.setBorder(null);
            this.dengji.setBorder(null);
            this.kuaisuquhui.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhangshi.setBounds(40, 5, 39, 39);
            this.mingzi.setBounds(190, 5, 100, 39);
            this.gouxuan.setBounds(13, 12, 15, 15);
            this.quanxuankuang.setBounds(13, 12, 15, 15);
            this.dengji.setBounds(353, 5, 39, 39);
            this.kuaisuquhui.setBounds(470, 15, 68, 17);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhangshi.setIcon(CutButtonImage.getCBG((int)roleorder.getSaletype(), roleorder.getSaleskin(), 39, 39));
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.zhanshidikuang.setOpaque(false);
            this.zhangshi.setOpaque(false);
            this.mingzi.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.dengji.setOpaque(false);
            this.kuaisuquhui.setOpaque(false);
            this.zhanshidikuang.setName(roleorder.getSaleid().toString());
            this.gouxuan.setName("1");
            this.mingzi.setForeground(Color.white);
            this.dengji.setForeground(Color.white);
            this.dengji.setText(setBack(roleorder.getStatus()));
            if ((int)roleorder.getStatus() != 3) {
                this.kuaisuquhui.setVisible(false);
                this.quanxuankuang.setVisible(false);
                this.gouxuan.setVisible(false);
            }
            else {
                this.kuaisuquhui.setVisible(true);
                this.quanxuankuang.setVisible(true);
                this.gouxuan.setVisible(true);
            }
            this.add(this.zhanshidikuang);
            this.add(this.zhangshi);
            this.add(this.mingzi);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.add(this.dengji);
            this.add(this.kuaisuquhui);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationMyMainCardJpanel traslationMyMainCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel();
                    if (TraslationMyMainMygoodsModelJpanel.this.gouxuan.getName().equals("1")) {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax()) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("0");
                        }
                    }
                    else {
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax()) {
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setIcon(null);
                            traslationMyMainCardJpanel.getTraslationMyMainMygoodsJpanel().getGouxuan().setName("1");
                        }
                    }
                }
            });
            this.kuaisuquhui.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
                    if (packNumber > 0) {
                        GoodsBackBean backBean = new GoodsBackBean();
                        List<BigDecimal> mesIdList = new ArrayList<>();
                        mesIdList.add(new BigDecimal(TraslationMyMainMygoodsModelJpanel.this.zhanshidikuang.getName()));
                        backBean.setIds(mesIdList);
                        String sendmes = Agreement.getAgreement().searchGoodsBackAgreement(GsonUtil.getGsonUtil().getgson().toJson(backBean));
                        SendMessageUntil.toServer(sendmes);
                        if ("0".equals(TraslationMyMainMygoodsModelJpanel.this.gouxuan.getName())) {
                            TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        }
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGeshuMax(TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax() - 1);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setName("1");
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsModelJpanel.this.gouxuan.setVisible(false);
                        TraslationMyMainMygoodsModelJpanel.this.kuaisuquhui.setVisible(false);
                        TraslationMyMainMygoodsModelJpanel.this.quanxuankuang.setVisible(false);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("背包格数不足");
                    }
                }
            });
        }
        this.zhangshi.addMouseListener(new CBGmoveMouslisten(roleorder.getSaletype(), roleorder.getOtherid()));
    }
    
    public static String setBack(Integer status) {
        String dengji = null;
        switch ((int)status) {
            case 1: {
                dengji = "未付钱";
                break;
            }
            case 2: {
                dengji = "超时";
                break;
            }
            case 3: {
                dengji = "已付钱";
                break;
            }
            case 4: {
                dengji = "已取货";
                break;
            }
            case 5: {
                dengji = "已取回";
                break;
            }
        }
        return dengji;
    }
}
