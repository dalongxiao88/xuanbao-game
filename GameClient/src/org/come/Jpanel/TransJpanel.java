package org.come.Jpanel;

import com.tool.time.Limit;
import com.tool.time.TimeLimit;
import org.come.model.Lingbao;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import org.come.bean.GoodTrans;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.model.Configure;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.entity.Goodstable;
import com.tool.PanelDisplay.PetPanelShow;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.GoodTrans2;
import com.tool.Document.NumberDocument;
import com.tool.role.RoleLingFa;
import org.come.until.Util;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import org.come.until.ScrollUI;
import org.come.until.SrcollPanelUI;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.TransBtn;
import javax.swing.JLabel;
import com.tool.btn.goodbtn;
import com.tool.Document.InputNum;
import javax.swing.JPanel;

public class TransJpanel extends JPanel implements InputNum
{
    private goodbtn[] btnrights;
    private transRole myTransRole;
    private transRole noTransRole;
    private JLabel[] GoodsListLabel;
    private TransBtn petBtn;
    private TransBtn lingBtn;
    private TransBtn queBtn;
    private int jpanelType;
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private JScrollPane jScrollPane;
    public JLabel[] lingsLabel;
    private TransBtn upBtn;
    private TransBtn downBtn;
    private int ys;
    public static boolean isJY;
    ImageIcon icon;
    ImageIcon icon2;
    
    public TransJpanel() throws Exception {
        this.GoodsListLabel = null;
        this.jpanelType = 0;
        this.lingsLabel = null;
        this.ys = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(558, 470));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 14);
            offBtn.setBounds(521, 10, 25, 25);
            this.add(offBtn);
            (this.queBtn = new TransBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "确定", 2, this)).setBounds(43, 272, 59, 24);
            this.add(this.queBtn);
            (this.petBtn = new TransBtn("inkImg/button/B150.png", 1, 0, this)).setBounds(50, 5, 63, 26);
            this.add(this.petBtn);
            (this.lingBtn = new TransBtn("inkImg/button/B137.png", 1, 1, this)).setBounds(115, 5, 63, 26);
            this.add(this.lingBtn);
            this.init();
        }
        else {
            this.setPreferredSize(new Dimension(536, 497));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 14);
            offBtn.setBounds(516, 0, 23, 23);
            this.add(offBtn);
            (this.queBtn = new TransBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "确定", 2, this)).setBounds(20, 285, 60, 26);
            this.add(this.queBtn);
            (this.petBtn = new TransBtn("img/xy2uiimg/petBtnZ.png", 1, 0, this)).setBounds(22, 20, 63, 26);
            this.add(this.petBtn);
            (this.lingBtn = new TransBtn("img/xy2uiimg/lingBtnW.png", 1, 1, this)).setBounds(85, 20, 63, 26);
            this.add(this.lingBtn);
            this.init();
        }
    }
    
    public void init() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(523, 30 + i * 35, 39, 31);
                this.add(this.btnrights[i]);
            }
            this.GoodsListLabel = new JLabel[24];
            for (int i = 0; i < 24; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).setBounds(217 + i % 6 * 51, 34 + i / 6 * 51, 49, 49);
                this.GoodsListLabel[i].addMouseListener(new transMouse(i, -1));
                this.add(this.GoodsListLabel[i]);
            }
            this.lingsLabel = new JLabel[9];
            for (int i = 0; i < 9; ++i) {
                (this.lingsLabel[i] = new JLabel()).setBounds(51 + i % 3 * 51, 35 + i / 3 * 51, 49, 49);
                this.lingsLabel[i].addMouseListener(new transMouse(i, -2));
                this.lingsLabel[i].setVisible(false);
                this.add(this.lingsLabel[i]);
            }
            (this.upBtn = new TransBtn("inkImg/button/10.png", 1, 3, this)).setBounds(164, 187, 18, 18);
            this.upBtn.setVisible(false);
            this.add(this.upBtn);
            (this.downBtn = new TransBtn("inkImg/button/9.png", 1, 4, this)).setBounds(184, 187, 18, 18);
            this.downBtn.setVisible(false);
            this.add(this.downBtn);
            this.listModel = new DefaultListModel<>();
            (this.listpet = new JList<>()).setOpaque(false);
            this.listpet.setSelectionBackground(new Color(33, 42, 52));
            this.listpet.setSelectionForeground(Color.GREEN);
            this.listpet.setForeground(Color.GREEN);
            this.listpet.setFont(new Font("微软雅黑", 1, 14));
            this.listpet.setBackground(UIUtils.Color_BACK);
            this.listpet.setModel(this.listModel);
            this.listpet.setSelectionMode(0);
            this.listpet.addMouseListener(new transPetMouse());
            (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(49, 52, 159, 157);
            this.jScrollPane.setBorder(null);
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
        }
        else {
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(500, 45 + i * 35, 30, 35);
                this.add(this.btnrights[i]);
            }
            this.GoodsListLabel = new JLabel[24];
            for (int i = 0; i < 24; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).setBounds(195 + i % 6 * 51, 45 + i / 6 * 51, 49, 49);
                this.GoodsListLabel[i].addMouseListener(new transMouse(i, -1));
                this.add(this.GoodsListLabel[i]);
            }
            this.lingsLabel = new JLabel[9];
            for (int i = 0; i < 9; ++i) {
                (this.lingsLabel[i] = new JLabel()).setBounds(25 + i % 3 * 51, 45 + i / 3 * 51, 49, 49);
                this.lingsLabel[i].addMouseListener(new transMouse(i, -2));
                this.lingsLabel[i].setVisible(false);
                this.add(this.lingsLabel[i]);
            }
            (this.upBtn = new TransBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, 3, this)).setBounds(140, 200, 19, 20);
            this.upBtn.setVisible(false);
            this.add(this.upBtn);
            (this.downBtn = new TransBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 4, this)).setBounds(160, 200, 19, 20);
            this.downBtn.setVisible(false);
            this.add(this.downBtn);
            this.listModel = new DefaultListModel<>();
            (this.listpet = new JList<>()).setOpaque(false);
            this.listpet.setSelectionBackground(new Color(33, 42, 52));
            this.listpet.setSelectionForeground(Color.GREEN);
            this.listpet.setForeground(Color.GREEN);
            this.listpet.setFont(new Font("微软雅黑", 1, 14));
            this.listpet.setBackground(UIUtils.Color_BACK);
            this.listpet.setModel(this.listModel);
            this.listpet.setSelectionMode(0);
            this.listpet.addMouseListener(new transPetMouse());
            (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(25, 65, 155, 155);
            this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
        }
    }
    
    public void transReset(BigDecimal role_id, String roleName) {
        if (this.myTransRole == null) {
            this.myTransRole = new transRole(this);
        }
        if (this.noTransRole == null) {
            this.noTransRole = new transRole(role_id, roleName);
        }
        this.myTransRole.reset(null, null);
        this.noTransRole.reset(role_id, roleName);
        this.listModel.clear();
        if (UserMessUntil.getPetListTable() != null) {
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                this.listModel.addElement(((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
            }
        }
        this.cJpanelType(this.ys = 0);
        FormsManagement.upgradForm(14);
        FormsManagement.HideForm(6);
        FormsManagement.HideForm(18);
        TransJpanel.isJY = true;
    }
    
    public void cJpanelType(int p) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jpanelType == p) {
                return;
            }
            this.jpanelType = p;
            boolean is = this.jpanelType == 0;
            this.jScrollPane.setVisible(is);
            is = !is;
            for (int i = 0; i < this.lingsLabel.length; ++i) {
                this.lingsLabel[i].setVisible(is);
            }
            this.upBtn.setVisible(is);
            this.downBtn.setVisible(is);
            try {
                if (is) {
                    this.petBtn.setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                    this.lingBtn.setIcons(CutButtonImage.cuts("inkImg/button/B138.png"));
                }
                else {
                    this.petBtn.setIcons(CutButtonImage.cuts("inkImg/button/B150.png"));
                    this.lingBtn.setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                }
            }
            catch (Exception ex) {}
        }
        else {
            if (this.jpanelType == p) {
                return;
            }
            this.jpanelType = p;
            boolean is = this.jpanelType == 0;
            this.jScrollPane.setVisible(is);
            is = !is;
            for (int i = 0; i < this.lingsLabel.length; ++i) {
                this.lingsLabel[i].setVisible(is);
            }
            this.upBtn.setVisible(is);
            this.downBtn.setVisible(is);
            try {
                if (is) {
                    this.petBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/petBtnW.png"));
                    this.lingBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/lingBtnZ.png"));
                }
                else {
                    this.petBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/petBtnZ.png"));
                    this.lingBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/lingBtnW.png"));
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B314.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.jpanelType == 1) {
                if (this.icon2 == null) {
                    this.icon2 = new ImageIcon("inkImg/background1/B315.png");
                }
                g.drawImage(this.icon2.getImage(), 47, 32, this);
            }
            GoodsListFromServerUntil.draw(g, 217, 34);
            Util.drawMoney(g, 86, 234);
            g.setFont(UIUtils.TEXT_NAME_FONT);
            if (this.myTransRole != null) {
                this.myTransRole.paint(g);
                if (this.jpanelType == 1) {
                    RoleLingFa.getRoleLingFa().drawTrans(g, 51, 35, this.ys, this.myTransRole.goodTrans.getLingbaos());
                }
            }
            if (this.noTransRole != null) {
                this.noTransRole.paint(g);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/trans.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.jpanelType == 1) {
                if (this.icon2 == null) {
                    this.icon2 = new ImageIcon("img/xy2uiimg/transLing.png");
                }
                g.drawImage(this.icon2.getImage(), 23, 45, this);
            }
            GoodsListFromServerUntil.draw(g, 195, 46);
            Util.drawMoney(g, 67, 247);
            g.setFont(UIUtils.TEXT_NAME_FONT);
            if (this.myTransRole != null) {
                this.myTransRole.paint(g);
                if (this.jpanelType == 1) {
                    RoleLingFa.getRoleLingFa().drawTrans(g, 25, 45, this.ys, this.myTransRole.goodTrans.getLingbaos());
                }
            }
            if (this.noTransRole != null) {
                this.noTransRole.paint(g);
            }
        }
    }
    
    @Override
    public void upNum() {
        BigDecimal j1 = ((NumberDocument)this.myTransRole.field.getDocument()).getNum();
        BigDecimal j2 = this.myTransRole.goodTrans.getMoney();
        if (j2 == null) {
            j2 = new BigDecimal(0);
        }
        if (j1.compareTo(j2) != 0) {
            this.myTransRole.goodTrans.setMoney(j1);
            GoodTrans2 goodTrans2 = new GoodTrans2();
            goodTrans2.setI(true);
            goodTrans2.setMoney(j1);
            String send = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans2));
            SendMessageUntil.toServer(send);
        }
    }
    
    @Override
    public boolean isChange() {
        return this.myTransRole == null || this.myTransRole.type != 0;
    }
    
    public void changTransGood(GoodTrans2 goodTrans2) {
        this.noTransRole.goodTrans.updateGood(goodTrans2);
        if (goodTrans2.getMoney() != null) {
            ((NumberDocument)this.noTransRole.field.getDocument()).replace(goodTrans2.getMoney().toString());
        }
    }
    
    public void transClose(boolean is) {
        if (this.myTransRole == null || this.myTransRole.goodTrans == null) {
            return;
        }
        if (is) {
            if (this.myTransRole.goodTrans.getLingbaos() != null && this.myTransRole.goodTrans.getLingbaos().size() != 0) {
                RoleLingFa.getRoleLingFa().deleteling(this.myTransRole.goodTrans.getLingbaos());
            }
            if (FormsManagement.getframe(6).isVisible()) {
                UserMessUntil.setChosePetMes(null);
                PetPanelShow.Show();
            }
        }
        else {
            if (this.myTransRole.goodTrans.getGoods() != null && this.myTransRole.goodTrans.getGoods().size() != 0) {
                for (int i = 0; i < this.myTransRole.goodTrans.getGoods().size(); ++i) {
                    Goodstable goodstable = (Goodstable)this.myTransRole.goodTrans.getGoods().get(i);
                    GoodsListFromServerUntil.stall2(goodstable);
                }
            }
            if (this.myTransRole.goodTrans.getPets() != null && this.myTransRole.goodTrans.getPets().size() != 0) {
                for (int i = 0; i < this.myTransRole.goodTrans.getPets().size(); ++i) {
                    RoleSummoning pet = (RoleSummoning)this.myTransRole.goodTrans.getPets().get(i);
                    UserMessUntil.getPetListTable().add(pet);
                }
            }
        }
        this.myTransRole.goodTrans = null;
    }
    
    public void CState(int zhi, String roleName) {
        transRole role1 = null;
        transRole role2 = null;
        if (this.noTransRole.roleName.equals(roleName)) {
            role1 = this.noTransRole;
            role2 = this.myTransRole;
        }
        else if (RoleData.getRoleData().getLoginResult().getRolename().equals(roleName)) {
            role1 = this.myTransRole;
            role2 = this.noTransRole;
        }
        if (zhi == 3) {
            role1.setType(1);
        }
        else if (zhi == 4) {
            role1.setType(0);
            if (role2.type == 2) {
                role2.setType(1);
            }
        }
        else if (zhi == 5) {
            role1.setType(2);
        }
    }
    
    public void queDing() {

        if (this.myTransRole.type == 0 || this.noTransRole.type == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("双方都锁定才能点击确定");
            return;
        }
//        FormsManagement.HideForm(11);

        if (NewRefiningJpanel.isLH) {
            ZhuFrame.getZhuJpanel().addPrompt("炼化中无法打开其他操作");
            return;
        }
        if (this.noTransRole.goodTrans.getGoods() != null) {
            int size = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
            if (size < this.noTransRole.goodTrans.getGoods().size()) {
                ZhuFrame.getZhuJpanel().addPrompt2("背包不足");
                return;
            }
        }
        if (this.noTransRole.goodTrans.getPets() != null) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            int size2 = this.noTransRole.goodTrans.getPets().size() + UserMessUntil.getPetListTable().size();
            if (size2 > Integer.parseInt(configure.getXdzhssx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽格子不足");
                return;
            }
        }
        PetAddPointMouslisten.clearWindow();
        UserMessUntil.unSetChosePetMes();
        String send = Agreement.getAgreement().TransStateAgreement("5");
        SendMessageUntil.toServer(send);
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public GoodTrans getGoodTrans() {
        if (this.myTransRole != null) {
            return this.myTransRole.goodTrans;
        }
        return null;
    }
    
    static {
        TransJpanel.isJY = false;
    }
    
    class transRole
    {
        private int type;
        private BigDecimal role_id;
        private String roleName;
        private String MSG;
        private JTextField field;
        private JLabel[] jLabels;
        private GoodTrans goodTrans;
        
        public transRole(TransJpanel transJpanel) {
            this.MSG = "已确定";
            if (MyIsif.getStyle().equals("水墨")) {
                (this.field = new JTextField()).setBounds(86, 243, 121, 16);
                this.field.setBorder(null);
                this.field.setOpaque(false);
                this.field.setCaretColor(Color.white);
                this.field.setForeground(Color.white);
                this.field.setFont(UIUtils.TEXT_FONT1);
                this.field.setDocument(new NumberDocument(this.field, 0, transJpanel));
                TransJpanel.this.add(this.field);
                this.jLabels = new JLabel[13];
                for (int i = 0; i < 8; ++i) {
                    (this.jLabels[i] = new JLabel()).setBounds(43 + i % 4 * 51, 344 + i / 4 * 51, 49, 49);
                    this.jLabels[i].addMouseListener(new transMouse(i, 0));
                    TransJpanel.this.add(this.jLabels[i]);
                }
                for (int i = 0; i < 4; ++i) {
                    int p = i + 8;
                    (this.jLabels[p] = new JLabel()).setBounds(43 + i / 2 * 110, 297 + i % 2 * 20, 95, 18);
                    this.jLabels[p].addMouseListener(new transMouse(p, 0));
                    TransJpanel.this.add(this.jLabels[p]);
                }
                (this.jLabels[12] = new JLabel()).setBounds(140, 270, 15, 15);
                this.jLabels[12].addMouseListener(new transMouse(12, 0));
                TransJpanel.this.add(this.jLabels[12]);
            }
            else {
                (this.field = new JTextField()).setBounds(67, 254, 120, 18);
                this.field.setBorder(null);
                this.field.setOpaque(false);
                this.field.setCaretColor(Color.white);
                this.field.setForeground(Color.white);
                this.field.setFont(UIUtils.TEXT_FONT1);
                this.field.setDocument(new NumberDocument(this.field, 0, transJpanel));
                TransJpanel.this.add(this.field);
                this.jLabels = new JLabel[13];
                for (int i = 0; i < 8; ++i) {
                    (this.jLabels[i] = new JLabel()).setBounds(20 + i % 4 * 51, 357 + i / 4 * 51, 49, 49);
                    this.jLabels[i].addMouseListener(new transMouse(i, 0));
                    TransJpanel.this.add(this.jLabels[i]);
                }
                for (int i = 0; i < 4; ++i) {
                    int p = i + 8;
                    (this.jLabels[p] = new JLabel()).setBounds(20 + i / 2 * 110, 310 + i % 2 * 20, 95, 18);
                    this.jLabels[p].addMouseListener(new transMouse(p, 0));
                    TransJpanel.this.add(this.jLabels[p]);
                }
                (this.jLabels[12] = new JLabel()).setBounds(125, 285, 12, 12);
                this.jLabels[12].addMouseListener(new transMouse(12, 0));
                TransJpanel.this.add(this.jLabels[12]);
            }
        }
        
        public transRole(BigDecimal role_id, String roleName) {
            this.MSG = "已确定";
            if (MyIsif.getStyle().equals("水墨")) {
                this.role_id = role_id;
                this.roleName = roleName;
                (this.field = new JTextField()).setBounds(376, 247, 121, 16);
                this.field.setBorder(null);
                this.field.setOpaque(false);
                this.field.setEditable(false);
                this.field.setText("0");
                this.field.setForeground(Color.white);
                this.field.setFont(UIUtils.TEXT_FONT1);
                this.field.setDocument(new NumberDocument(this.field, 4));
                TransJpanel.this.add(this.field);
                this.jLabels = new JLabel[13];
                for (int i = 0; i < 8; ++i) {
                    (this.jLabels[i] = new JLabel()).setBounds(290 + i % 4 * 51, 357 + i / 4 * 51, 49, 49);
                    this.jLabels[i].addMouseListener(new transMouse(i, 1));
                    TransJpanel.this.add(this.jLabels[i]);
                }
                for (int i = 0; i < 4; ++i) {
                    int p = i + 8;
                    (this.jLabels[p] = new JLabel()).setBounds(290 + i / 2 * 110, 310 + i % 2 * 20, 95, 18);
                    this.jLabels[p].addMouseListener(new transMouse(p, 1));
                    TransJpanel.this.add(this.jLabels[p]);
                }
                (this.jLabels[12] = new JLabel()).setBounds(503, 248, 15, 15);
                TransJpanel.this.add(this.jLabels[12]);
            }
            else {
                this.role_id = role_id;
                this.roleName = roleName;
                (this.field = new JTextField()).setBounds(355, 258, 120, 18);
                this.field.setBorder(null);
                this.field.setOpaque(false);
                this.field.setEditable(false);
                this.field.setText("0");
                this.field.setForeground(Color.white);
                this.field.setFont(UIUtils.TEXT_FONT1);
                this.field.setDocument(new NumberDocument(this.field, 4));
                TransJpanel.this.add(this.field);
                this.jLabels = new JLabel[13];
                for (int i = 0; i < 8; ++i) {
                    (this.jLabels[i] = new JLabel()).setBounds(290 + i % 4 * 51, 357 + i / 4 * 51, 49, 49);
                    this.jLabels[i].addMouseListener(new transMouse(i, 1));
                    TransJpanel.this.add(this.jLabels[i]);
                }
                for (int i = 0; i < 4; ++i) {
                    int p = i + 8;
                    (this.jLabels[p] = new JLabel()).setBounds(290 + i / 2 * 110, 310 + i % 2 * 20, 95, 18);
                    this.jLabels[p].addMouseListener(new transMouse(p, 1));
                    TransJpanel.this.add(this.jLabels[p]);
                }
                (this.jLabels[12] = new JLabel()).setBounds(480, 260, 12, 12);
                TransJpanel.this.add(this.jLabels[12]);
            }
        }
        
        public void reset(BigDecimal role_id, String roleName) {
            this.role_id = role_id;
            this.roleName = roleName;
            this.goodTrans = new GoodTrans();
            this.setType(0);
            this.field.setText("0");
        }
        
        protected void paint(Graphics g) {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.roleName != null) {
                    g.setColor(Color.red);
                    g.drawString(this.roleName, 313, 290);
                    if (this.goodTrans != null) {
                        this.goodTrans.paint(g, 1);
                    }
                    if (this.MSG != null) {
                        g.setColor(Color.RED);
                        g.drawString(this.MSG, 473, 290);
                    }
                }
                else {
                    g.setColor(Color.white);
                    if (this.goodTrans != null) {
                        this.goodTrans.paint(g, 0);
                    }
                    if (this.MSG != null) {
                        g.setColor(Color.RED);
                        g.drawString(this.MSG, 160, 280);
                    }
                }
            }
            else if (this.roleName != null) {
                g.setColor(Color.red);
                g.drawString(this.roleName, 290, 303);
                if (this.goodTrans != null) {
                    this.goodTrans.paint(g, 1);
                }
                if (this.MSG != null) {
                    g.setColor(Color.RED);
                    g.drawString(this.MSG, 450, 303);
                }
            }
            else {
                g.setColor(Color.white);
                if (this.goodTrans != null) {
                    this.goodTrans.paint(g, 0);
                }
                if (this.MSG != null) {
                    g.setColor(Color.RED);
                    g.drawString(this.MSG, 140, 303);
                }
            }
        }
        
        public void setType(int type) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.type = type;
                if (this.type == 0) {
                    this.MSG = null;
                    this.jLabels[12].setIcon(null);
                }
                else if (this.type == 1) {
                    this.MSG = "未确定";
                    this.jLabels[12].setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                }
                else if (this.type == 2) {
                    this.MSG = "已确定";
                    this.jLabels[12].setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                }
            }
            else {
                this.type = type;
                if (this.type == 0) {
                    this.MSG = null;
                    this.jLabels[12].setIcon(null);
                }
                else if (this.type == 1) {
                    this.MSG = "未确定";
                    this.jLabels[12].setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", -1, -1));
                }
                else if (this.type == 2) {
                    this.MSG = "已确定";
                    this.jLabels[12].setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", -1, -1));
                }
            }
        }
    }
    
    class transMouse implements MouseListener
    {
        private int p;
        private int type;
        
        public transMouse(int p, int type) {
            this.p = p;
            this.type = type;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (this.type == 0 && this.p == 12) {
                if (TransJpanel.this.myTransRole.jLabels[12].getIcon() == null) {
                    String send = Agreement.getAgreement().TransStateAgreement("3");
                    SendMessageUntil.toServer(send);
                }
                else {
                    String send = Agreement.getAgreement().TransStateAgreement("4");
                    SendMessageUntil.toServer(send);
                }
                return;
            }
            else {
                if (TransJpanel.this.myTransRole.type != 0) {
                    return;
                }
                if (this.type == -2) {
                    if (TransJpanel.this.myTransRole.goodTrans.getLingbaos() != null && TransJpanel.this.myTransRole.goodTrans.getLingbaos().size() >= 2) {
                        ZhuFrame.getZhuJpanel().addPrompt2("格子已满");
                        return;
                    }
                    if (TransJpanel.this.myTransRole.type != 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你已锁定");
                        return;
                    }
                    Lingbao lingbao = RoleLingFa.getRoleLingFa().getTrans(TransJpanel.this.ys, TransJpanel.this.myTransRole.goodTrans.getLingbaos(), this.p);
                    if (lingbao != null) {
                        TransJpanel.this.myTransRole.goodTrans.setLingbao(lingbao, true);
                        GoodTrans2 goodTrans2 = new GoodTrans2();
                        goodTrans2.setI(true);
                        goodTrans2.setLingbao(lingbao);
                        String send2 = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans2));
                        SendMessageUntil.toServer(send2);
                    }
                }
                else if (this.type == -1) {
                    Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.p);
                    if (goodstable != null) {
                        if (GoodsListFromServerUntil.isJY(goodstable)) {
                            ZhuFrame.getZhuJpanel().addPrompt2("该物品禁止交易");
                            return;
                        }
                        if (TransJpanel.this.myTransRole.goodTrans.getGoods() != null && TransJpanel.this.myTransRole.goodTrans.getGoods().size() >= 8) {
                            ZhuFrame.getZhuJpanel().addPrompt2("格子已满");
                            return;
                        }
                        if (TransJpanel.this.myTransRole.type != 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("你已锁定");
                            return;
                        }
                        if (e.isMetaDown()) {
                            Goodstable good = TransJpanel.this.myTransRole.goodTrans.getGood(goodstable.getRgid());
                            int goodNum = GoodsListFromServerUntil.getGoodNum(goodstable.getGoodsid());
                            if (good == null) {
                                good = (Goodstable)goodstable.clone();
                                good.setUsetime(Integer.valueOf(goodNum));
                                TransJpanel.this.myTransRole.goodTrans.setGood(good);
                            }
                            else {
                                good.setUsetime(Integer.valueOf((int)good.getUsetime() + goodNum));
                            }
                            goodstable.goodxh(goodNum);
                            if ((int)goodstable.getUsetime() <= 0) {
                                GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                            }
                            GoodTrans2 goodTrans3 = new GoodTrans2();
                            goodTrans3.setI(true);
                            goodTrans3.setGoods(good);
                            String send3 = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans3));
                            SendMessageUntil.toServer(send3);
                        }
                        else {
                            Goodstable good = TransJpanel.this.myTransRole.goodTrans.getGood(goodstable.getRgid());
                            if (good == null) {
                                good = (Goodstable)goodstable.clone();
                                good.setUsetime(Integer.valueOf(1));
                                TransJpanel.this.myTransRole.goodTrans.setGood(good);
                            }
                            else {
                                good.setUsetime(Integer.valueOf((int)good.getUsetime() + 1));
                            }
                            goodstable.goodxh(1);
                            if ((int)goodstable.getUsetime() <= 0) {
                                GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
                            }
                            GoodTrans2 goodTrans4 = new GoodTrans2();
                            goodTrans4.setI(true);
                            goodTrans4.setGoods(good);
                            String send4 = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans4));
                            SendMessageUntil.toServer(send4);
                        }
                    }
                }
                else if (this.type == 0) {
                    if (TransJpanel.this.myTransRole.type != 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你已锁定");
                        return;
                    }
                    if (this.p < 8) {
                        Goodstable goodstable = TransJpanel.this.myTransRole.goodTrans.getGood(this.p);
                        if (goodstable != null) {
                            Goodstable good = (Goodstable)goodstable.clone();
                            goodstable.setUsetime(Integer.valueOf(0));
                            TransJpanel.this.myTransRole.goodTrans.getGoods().remove(goodstable);
                            GoodTrans2 goodTrans4 = new GoodTrans2();
                            goodTrans4.setI(false);
                            goodTrans4.setGoods(goodstable);
                            String send4 = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans4));
                            SendMessageUntil.toServer(send4);
                            GoodsListFromServerUntil.stall2(good);
                        }
                    }
                    else if (this.p < 10) {
                        RoleSummoning pet = TransJpanel.this.myTransRole.goodTrans.getPet(this.p - 8);
                        if (pet != null) {
                            UserMessUntil.getPetListTable().add(pet);
                            TransJpanel.this.listModel.addElement(pet.getSummoningname());
                            TransJpanel.this.myTransRole.goodTrans.setPet(pet, false);
                            GoodTrans2 goodTrans2 = new GoodTrans2();
                            goodTrans2.setI(false);
                            goodTrans2.setPet(pet);
                            String send2 = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans2));
                            SendMessageUntil.toServer(send2);
                        }
                    }
                    else if (this.p < 12) {
                        Lingbao lingbao = TransJpanel.this.myTransRole.goodTrans.getLingbao(this.p - 10);
                        if (lingbao != null) {
                            TransJpanel.this.myTransRole.goodTrans.getLingbaos().remove(lingbao);
                            GoodTrans2 goodTrans2 = new GoodTrans2();
                            goodTrans2.setI(false);
                            goodTrans2.setLingbao(lingbao);
                            String send2 = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans2));
                            SendMessageUntil.toServer(send2);
                        }
                    }
                }
                return;
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (this.type == -2) {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getTrans(TransJpanel.this.ys, TransJpanel.this.myTransRole.goodTrans.getLingbaos(), this.p);
                if (lingbao != null) {
                    ZhuFrame.getZhuJpanel().creatlingtext(lingbao);
                }
            }
            else if (this.type == -1) {
                Goodstable goodstable = GoodsListFromServerUntil.Getgood(this.p);
                if (goodstable != null) {
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
            }
            else {
                transRole role = null;
                if (this.type == 0) {
                    role = TransJpanel.this.myTransRole;
                }
                else {
                    role = TransJpanel.this.noTransRole;
                }
                if (role == null) {
                    return;
                }
                if (this.p < 8) {
                    Goodstable goodstable2 = role.goodTrans.getGood(this.p);
                    if (goodstable2 != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(goodstable2);
                    }
                }
                else if (this.p < 10) {
                    RoleSummoning pet = role.goodTrans.getPet(this.p - 8);
                    if (pet != null) {
                        ZhuFrame.getZhuJpanel().creatpettext(pet);
                    }
                }
                else if (this.p < 12) {
                    Lingbao lingbao2 = role.goodTrans.getLingbao(this.p - 10);
                    if (lingbao2 != null) {
                        ZhuFrame.getZhuJpanel().creatlingtext(lingbao2);
                    }
                }
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            if (this.type == -2) {
                ZhuFrame.getZhuJpanel().clearlingtext();
            }
            else if (this.type == -1) {
                ZhuFrame.getZhuJpanel().cleargoodtext();
            }
            else if (this.p < 8) {
                ZhuFrame.getZhuJpanel().cleargoodtext();
            }
            else if (this.p < 10) {
                ZhuFrame.getZhuJpanel().pettext();
            }
            else if (this.p < 12) {
                ZhuFrame.getZhuJpanel().clearlingtext();
            }
        }
    }
    
    class transPetMouse implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (TransJpanel.this.myTransRole.goodTrans.getPets() != null && TransJpanel.this.myTransRole.goodTrans.getPets().size() >= 2) {
                ZhuFrame.getZhuJpanel().addPrompt2("格子已满");
                return;
            }
            if (TransJpanel.this.myTransRole.type != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你已锁定");
                return;
            }
            if (UserMessUntil.getPetListTable().size() != 0) {
                RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(TransJpanel.this.listpet.getSelectedIndex());
                if (pet.getQuality() != null && !pet.getQuality().equals("2")) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R提示：#Y召唤兽#G【" + pet.getSummoningname() + "】#Y禁止交易！！！");
                    return;
                }
                if (pet.getPetlock() == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt("#R提示：#Y召唤兽#G" + pet.getSummoningname() + "】#Y已被加锁，禁止交易！！");
                    return;
                }
                if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("#R提示：#Y该召唤兽在参战中，禁止交易！");
                    return;
                }
                if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("#R提示：#Y这只召唤兽被管制中，禁止交易！！！");
                    return;
                }
                Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
                if (limit != null && limit.getValue().equals(pet.getSid().toString())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("召唤兽已使用枯荣丹，禁止交易！！！");
                    return;
                }
                UserMessUntil.getPetListTable().remove(pet);
                TransJpanel.this.listModel.remove(TransJpanel.this.listpet.getSelectedIndex());
                TransJpanel.this.myTransRole.goodTrans.setPet(pet, true);
                GoodTrans2 goodTrans2 = new GoodTrans2();
                goodTrans2.setI(true);
                goodTrans2.setPet(pet);
                String send = Agreement.getAgreement().TransGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(goodTrans2));
                SendMessageUntil.toServer(send);
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
