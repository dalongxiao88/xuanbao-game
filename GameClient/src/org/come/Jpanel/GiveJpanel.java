package org.come.Jpanel;

import com.tool.role.RoleData;
import com.tool.ModerateTask.Task;
import com.tool.ModerateTask.Hero;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.GiveGoodsBean;
import org.come.Frame.ZhuFrame;
import org.come.bean.Bbuy;
import org.come.until.UserMessUntil;
import org.come.until.FormsManagement;
import com.tool.image.ImageMixDeal;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import com.tool.Document.NumberDocument;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.mouslisten.GiveGoodsMouslisten;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.image.ManimgAttribute;
import org.come.entity.Goodstable;
import com.tool.btn.RoleOperationPanelBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.tool.btn.goodbtn;
import com.tool.Document.InputNum;
import javax.swing.JPanel;

public class GiveJpanel extends JPanel implements InputNum
{
    private goodbtn[] btnrights;
    private static JTextField textNumber;
    private static JTextField textMoney;
    private JLabel labplayer;
    private JLabel labplyername;
    private RoleOperationPanelBtn btnGive;
    private JLabel[] GoodsListLabel;
    private Goodstable giveGood;
    private ManimgAttribute attribute;
    private ImageIcon icon;
    private int xz;
    
    public GiveJpanel() throws Exception {
        this.xz = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(400, 379));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 12);
            offBtn.setBounds(363, 10, 25, 25);
            this.add(offBtn);
            this.GoodsListLabel = new JLabel[24];
            for (int i = 0; i < 24; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).setBounds(46 + i % 6 * 51, 48 + i / 6 * 51, 50, 50);
                this.GoodsListLabel[i].addMouseListener(new GiveGoodsMouslisten(i, this));
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(352, 46 + i * 35, 39, 31);
                this.add(this.btnrights[i]);
            }
            (GiveJpanel.textNumber = new JTextField()).setBounds(102, 267, 151, 16);
            GiveJpanel.textNumber.setText("0");
            GiveJpanel.textNumber.setForeground(Color.white);
            GiveJpanel.textNumber.setBackground(UIUtils.Color_BACK);
            GiveJpanel.textNumber.setBorder(BorderFactory.createEmptyBorder());
            GiveJpanel.textNumber.setFont(UIUtils.TEXT_FONT1);
            GiveJpanel.textNumber.setDocument(new NumberDocument(GiveJpanel.textNumber, 4, this));
            this.add(GiveJpanel.textNumber);
            (GiveJpanel.textMoney = new JTextField()).setBounds(102, 295, 151, 16);
            GiveJpanel.textMoney.setText("0");
            GiveJpanel.textMoney.setForeground(Color.white);
            GiveJpanel.textMoney.setBackground(UIUtils.Color_BACK);
            GiveJpanel.textMoney.setBorder(BorderFactory.createEmptyBorder());
            GiveJpanel.textMoney.setDocument(new NumberDocument(GiveJpanel.textMoney, 4));
            GiveJpanel.textMoney.setFont(UIUtils.TEXT_FONT1);
            this.add(GiveJpanel.textMoney);
            (this.labplayer = new JLabel()).setText("玩家:");
            this.labplayer.setBounds(36, 339, 40, 20);
            this.labplayer.setForeground(Color.black);
            this.labplayer.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labplayer);
            (this.labplyername = new JLabel()).setBounds(76, 339, 130, 20);
            this.labplyername.setForeground(Color.red);
            this.labplyername.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labplyername);
            (this.btnGive = new RoleOperationPanelBtn("inkImg/button1/B20.png", 1, "给予", null, null)).setBounds(240, 335, 59, 24);
            this.add(this.btnGive);
        }
        else {
            this.setPreferredSize(new Dimension(363, 385));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 12);
            offBtn.setBounds(343, 0, 23, 23);
            this.add(offBtn);
            this.GoodsListLabel = new JLabel[24];
            for (int i = 0; i < 24; ++i) {
                (this.GoodsListLabel[i] = new JLabel()).setBounds(19 + i % 6 * 51, 45 + i / 6 * 51, 50, 50);
                this.GoodsListLabel[i].addMouseListener(new GiveGoodsMouslisten(i, this));
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(326, 43 + i * 33, 24, 33);
                this.add(this.btnrights[i]);
            }
            (GiveJpanel.textNumber = new JTextField()).setBounds(142, 271, 141, 17);
            GiveJpanel.textNumber.setText("0");
            GiveJpanel.textNumber.setForeground(Color.white);
            GiveJpanel.textNumber.setBackground(UIUtils.Color_BACK);
            GiveJpanel.textNumber.setBorder(BorderFactory.createEmptyBorder());
            GiveJpanel.textNumber.setFont(UIUtils.TEXT_FONT1);
            GiveJpanel.textNumber.setDocument(new NumberDocument(GiveJpanel.textNumber, 4, this));
            this.add(GiveJpanel.textNumber);
            (GiveJpanel.textMoney = new JTextField()).setBounds(142, 305, 141, 17);
            GiveJpanel.textMoney.setText("0");
            GiveJpanel.textMoney.setForeground(Color.white);
            GiveJpanel.textMoney.setBackground(UIUtils.Color_BACK);
            GiveJpanel.textMoney.setBorder(BorderFactory.createEmptyBorder());
            GiveJpanel.textMoney.setDocument(new NumberDocument(GiveJpanel.textMoney, 4));
            GiveJpanel.textMoney.setFont(UIUtils.TEXT_FONT1);
            this.add(GiveJpanel.textMoney);
            (this.labplayer = new JLabel()).setText("玩家:");
            this.labplayer.setBounds(21, 339, 40, 20);
            this.labplayer.setForeground(Color.black);
            this.labplayer.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labplayer);
            (this.labplyername = new JLabel()).setBounds(61, 339, 130, 20);
            this.labplyername.setForeground(Color.red);
            this.labplyername.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labplyername);
            (this.btnGive = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "给 予", null)).setBounds(240, 335, 80, 26);
            this.add(this.btnGive);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B313.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 400, 379, this);
            GoodsListFromServerUntil.draw(g, 46, 48);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/38_png.xy2uiimg.give_bg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 363, 385, this);
            GoodsListFromServerUntil.draw(g, 19, 45);
        }
    }
    
    public void qingchu() {
        this.xuanzhong(null, 0);
        GiveJpanel.textMoney.setText("0");
    }
    
    public void PaintingText(int shopPlace) {
        this.GoodsListLabel[shopPlace].setBorder(BorderFactory.createLineBorder(Color.red));
    }
    
    public void ClearText(int shopPlace) {
        if (this.xz != shopPlace) {
            this.GoodsListLabel[shopPlace].setBorder(null);
        }
    }
    
    public void giveShow(ManimgAttribute attribute) {
        this.btnGive.setText("给予");
        this.attribute = null;
        this.giveGood = null;
        ((NumberDocument)GiveJpanel.textNumber.getDocument()).replace("0");
        ((NumberDocument)GiveJpanel.textMoney.getDocument()).replace("0");
        GiveJpanel.textNumber.setEditable(true);
        GiveJpanel.textMoney.setEditable(true);
        if (attribute.getRoleShow() != null) {
            this.labplayer.setText("玩家:");
            this.labplyername.setText(attribute.getRoleShow().getRolename());
        }
        else if (attribute.getNpc() != null) {
            String type = attribute.getNpc().getNpctable().getNpctype();
            if (!type.equals("71") && !type.equals("89") && !TaskMixDeal.isgive(attribute.getNpc().getNpctable().getNpcname())) {
                ImageMixDeal.userimg.Dialogue("他好像不想理你");
                return;
            }
            GiveJpanel.textMoney.setEditable(false);
            this.labplayer.setText("NPC:");
            this.labplyername.setText(attribute.getNpc().getNpctable().getNpcname());
        }
        else if (attribute.getMapMonsterBean() != null) {
            GiveJpanel.textMoney.setEditable(false);
            this.labplayer.setText("限时收购:");
            this.labplyername.setText(attribute.getMapMonsterBean().getRobotname());
        }
        else {
            return;
        }
        this.attribute = attribute;
        FormsManagement.showForm(12);
    }
    
    public void xuanzhong(Goodstable goodstable, int shopPlace) {
        if (goodstable == null) {
            if (this.xz != -1) {
                this.GoodsListLabel[this.xz].setBorder(null);
            }
            this.xz = -1;
            ((NumberDocument)GiveJpanel.textNumber.getDocument()).replace("0");
            this.giveGood = null;
            if (this.attribute == null || this.attribute.getRoleShow() == null) {
                ((NumberDocument)GiveJpanel.textMoney.getDocument()).replace("0");
            }
        }
        else if (this.giveGood != null && this.giveGood.getRgid().compareTo(goodstable.getRgid()) == 0) {
            long sum = GiveJpanel.textNumber.getText().equals("") ? 0L : ((NumberDocument)GiveJpanel.textNumber.getDocument()).getNum().longValue();
            sum = (((long)(int)goodstable.getUsetime() > sum) ? (sum + 1L) : ((long)(int)goodstable.getUsetime()));
            ((NumberDocument)GiveJpanel.textNumber.getDocument()).replace(sum + "");
            this.upMoney(sum);
        }
        else {
            if (this.xz != -1) {
                this.GoodsListLabel[this.xz].setBorder(null);
            }
            this.xz = shopPlace;
            this.GoodsListLabel[this.xz].setBorder(BorderFactory.createLineBorder(Color.red));
            this.giveGood = goodstable;
            ((NumberDocument)GiveJpanel.textNumber.getDocument()).replace("1");
            this.upMoney(1L);
        }
    }
    
    public void upMoney(long sum) {
        if (this.attribute == null || this.attribute.getNpc() == null || this.giveGood == null) {
            return;
        }
        String text = null;
        if (this.attribute.getNpc().getNpctable().getNpctype().equals("71")) {
            Bbuy bbuy = UserMessUntil.getBbuy(this.giveGood.getGoodsid());
            if (bbuy != null && bbuy.getPrice1() != 0L) {
                text = bbuy.getPrice1() * sum + "";
            }
            else {
                text = "0";
            }
        }
        else if (this.attribute.getNpc().getNpctable().getNpctype().equals("89")) {
            Bbuy bbuy = UserMessUntil.getBbuy(this.giveGood.getGoodsid());
            if (bbuy != null && bbuy.getPrice2() != 0L) {
                text = bbuy.getPrice2() * sum + "";
            }
            else {
                text = "0";
            }
        }
        if (text != null) {
            ((NumberDocument)GiveJpanel.textMoney.getDocument()).replace(text);
        }
    }
    
    public void giveMethod() {
//        FormsManagement.HideForm(11);

        if (NewRefiningJpanel.isLH) {
            ZhuFrame.getZhuJpanel().addPrompt("炼化中无法打开其他操作");
            return;
        }
        if (this.attribute == null) {
            return;
        }
        int count = 0;
        if (this.giveGood != null) {
            if (GoodsListFromServerUntil.isExist(this.giveGood)) {
                return;
            }
            if (this.attribute.getRoleShow() != null && GoodsListFromServerUntil.isJY(this.giveGood)) {
                ZhuFrame.getZhuJpanel().addPrompt("物品无法给予");
                return;
            }
            count = ((NumberDocument)GiveJpanel.textNumber.getDocument()).getNum().intValue();
            if (count <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("给与的数量不能为0");
                return;
            }
            if (count > (int)this.giveGood.getUsetime()) {
                count = (int)this.giveGood.getUsetime();
                ZhuFrame.getZhuJpanel().addPrompt2("您给与的物品个数超出你拥有的物品数,将给与的数量调整为你拥有的数量");
            }
        }
        if (this.attribute.getRoleShow() != null) {
            this.givePlayer(count);
            return;
        }
        if (count == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中的物品");
            return;
        }
        if (count > (int)this.giveGood.getUsetime()) {
            count = (int)this.giveGood.getUsetime();
        }
        if (this.attribute.getNpc() != null) {
            if (TaskMixDeal.isgive(this.attribute.getNpc().getNpctable().getNpcname())) {
                this.giveTask(count);
            }
            else if (this.attribute.getNpc().getNpctable().getNpctype().equals("71")) {
                this.giveshipolan(1, count);
            }
            else if (this.attribute.getNpc().getNpctable().getNpctype().equals("89")) {
                this.giveshipolan(2, count);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("对你的物品丝毫不感兴趣");
                return;
            }
        }
        else if (this.attribute.getMapMonsterBean() != null) {
            this.giveshipolan(count);
        }
    }
    
    public void givePlayer(int count) {
        BigDecimal money = getqian();
        if (money == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("金钱不足");
            return;
        }
        GiveGoodsBean givebean = new GiveGoodsBean();
        givebean.setType(0);
        givebean.setOtherID(this.attribute.getRoleShow().getRole_id());
        givebean.setOtherName(this.attribute.getRoleShow().getRolename());
        givebean.setRgid((this.giveGood != null) ? this.giveGood.getRgid() : null);
        givebean.setSum(count);
        givebean.setGold(money);
        String senmes = Agreement.getAgreement().giveAgreement(GsonUtil.getGsonUtil().getgson().toJson(givebean));
        SendMessageUntil.toServer(senmes);
        this.qingchu();
    }
    
    public void giveshipolan(int type, int count) {
        Bbuy bbuy = UserMessUntil.getBbuy(this.giveGood.getGoodsid());
        if (bbuy == null || (bbuy.getPrice1() == 0L && type == 1) || (bbuy.getPrice2() == 0L && type == 2)) {
            ZhuFrame.getZhuJpanel().addPrompt2("不属于回收范围");
            return;
        }
        GiveGoodsBean givebean = new GiveGoodsBean();
        givebean.setType(type);
        givebean.setRgid(this.giveGood.getRgid());
        givebean.setSum(count);
        String senmes = Agreement.getAgreement().giveAgreement(GsonUtil.getGsonUtil().getgson().toJson(givebean));
        SendMessageUntil.toServer(senmes);
        this.qingchu();
    }
    
    public void giveTask(int count) {
        Task task = Hero.getHero().PartFinish("给予物品", this.giveGood.getGoodsname());
        if (task == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("对你的物品丝毫不感兴趣");
            return;
        }
        String mes = Agreement.getAgreement().TaskNAgreement("G" + task.getTaskId() + "|" + this.giveGood.getRgid() + "|" + count);
        SendMessageUntil.toServer(mes);
        this.qingchu();
    }
    
    public void giveshipolan(int count) {
        GiveGoodsBean givebean = new GiveGoodsBean();
        givebean.setOtherID(new BigDecimal((int)this.attribute.getMapMonsterBean().getI()));
        givebean.setType(3);
        givebean.setRgid(this.giveGood.getRgid());
        givebean.setSum(count);
        String senmes = Agreement.getAgreement().giveAgreement(GsonUtil.getGsonUtil().getgson().toJson(givebean));
        SendMessageUntil.toServer(senmes);
        this.qingchu();
    }
    
    public static BigDecimal getqian() {
        try {
            long x = ((NumberDocument)GiveJpanel.textMoney.getDocument()).getNum().longValue();
            if (x <= 0L) {
                return new BigDecimal(0);
            }
            if (RoleData.getRoleData().getLoginResult().getGold().longValue() < x) {
                return null;
            }
            return new BigDecimal(x);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public void upNum() {
        BigDecimal j1 = ((NumberDocument)GiveJpanel.textNumber.getDocument()).getNum();
        this.upMoney((long)j1.intValue());
    }
    
    @Override
    public boolean isChange() {
        return false;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
}
