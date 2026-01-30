package org.come.xingpan;

import org.come.entity.Goodstable;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Rectangle;
import org.come.until.FormsManagement;
import org.come.bean.Skill;
import org.come.Frame.MsgJframe;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.tool.tcp.SpriteFactory;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.NpcMenuUntil;
import org.come.action.MapAction;
import org.come.action.NpcMenuAction;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.role.RoleData;
import org.come.npc.SkillLearn3;
import org.come.until.CutButtonImage;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import java.util.Vector;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import com.tool.role.BaseXingpans;
import com.tool.role.RoleProperty;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import com.tool.tcp.Sprite;
import javax.swing.JPanel;

public class JpanelXingCardMain extends JPanel
{
    private static Sprite tcp_xp_km;
    private static String[] skillNames;
    private static Map<Integer, List<Integer>> starMap;
    private static Map<Integer, Point> starPoints;
    private BtnStarCard starCardBtn;
    private BtnStarCard[] starCardBtns;
    private StarLabel[] starLabels;
    private JLabel cardBackImg;
    private JLabel cardImg;
    private JLabel skillBackImg;
    private JLabel bigSkillLab;
    private int radioType;
    private AstrolabeBorderAnimation[] astrolabeBorderAnimationArr;
    private long time;
    private boolean isPlayXPKM;
    private ImageIcon iconBack;
    private boolean isChoose;
    private static Sprite tcp_choose;
    private static Sprite tcp_cistern;
    private static long currentTimeMillis;
    private static Sprite tcp_star;
    private static ImageIcon starLabelBack;
    
    public static String getStarNameById(int id) {
        return JpanelXingBackMain.starSouls[id - 1];
    }
    
    public static boolean isAllStarOpen(int id) {
        return isAllStarOpen(id, (List<Integer>)JpanelXingCardMain.starMap.get(Integer.valueOf(id)));
    }
    
    public static boolean isAllStarOpen(int id, List<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (!isStarOpen(id, (int)list.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isStarOpen(int id, int starSoulId) {
        Vector<BaseXingpans> xingpansVector = RoleProperty.getRoleProperty().xingpanVector;
        for (int i = 0; i < xingpansVector.size(); ++i) {
            BaseXingpans baseXingpans = (BaseXingpans)xingpansVector.get(i);
            if (baseXingpans.getBh() == id && StringUtils.isNotBlank(baseXingpans.getExp()) && Arrays.asList(baseXingpans.getExp().split("=")).contains(String.valueOf(starSoulId))) {
                return true;
            }
        }
        return false;
    }
    
    public void setTime(Long time) {
        this.time = (long)time;
    }
    
    public void setIsPlayXPKM(Boolean b) {
        this.isPlayXPKM = (boolean)b;
        this.time = System.currentTimeMillis();
        if ((boolean)b) {
            this.hideStrengthen();
        }
        else {
            this.showStrengthen();
        }
    }
    
    public void hideStrengthen() {
        this.skillBackImg.setVisible(false);
        this.cardBackImg.setVisible(false);
        this.cardImg.setVisible(false);
        for (int i = 0; i < this.starLabels.length; ++i) {
            if (this.starLabels[i].isVisible()) {
                this.starLabels[i].setShow(false);
            }
        }
    }
    
    public void showStrengthen() {
        this.skillBackImg.setVisible(true);
        this.cardBackImg.setVisible(true);
        this.cardImg.setVisible(true);
        for (int i = 0; i < this.starLabels.length; ++i) {
            if (this.starLabels[i].isVisible()) {
                this.starLabels[i].setShow(true);
            }
        }
    }
    
    public JpanelXingCardMain() {
        this.radioType = 1;
        this.astrolabeBorderAnimationArr = new AstrolabeBorderAnimation[8];
        this.isPlayXPKM = false;
        this.isChoose = false;
        this.setPreferredSize(new Dimension(646, 464));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("img/jiegua/gb.png", 1, 122);
        offBtn.setBounds(546, 234, 17, 56);
        this.add(offBtn);
        this.getCardImg();
        this.getStarLabels();
        this.getCardBackImg();
        this.getSkillBackImg();
        this.starCardBtn();
        this.starCardBtns();
        this.getBigSkillLab();
        this.update(true);
    }
    
    public void update(int id) {
        this.radioType = id;
        this.update(true);
    }
    
    public void update(boolean is) {
        for (int i = 0; i < this.starCardBtns.length; ++i) {
            if (isAllStarOpen(i + 1)) {
                this.starCardBtns[i].setOpen(true);
            }
            else {
                this.starCardBtns[i].setOpen(false);
            }
        }
        this.getCardBackImg().setIcon(CutButtonImage.getImage("inkImg/background/xpbj" + this.radioType + ".png", 274, 274));
        List<Integer> list = (List<Integer>)JpanelXingCardMain.starMap.get(Integer.valueOf(this.radioType));
        for (int j = 0; j < this.starLabels.length; ++j) {
            if (j < list.size()) {
                Integer id = (Integer)list.get(j);
                this.starLabels[j].setStarName(getStarNameById((int)id));
                this.starLabels[j].setId((int)id);
                this.starLabels[j].setBounds(((Point)JpanelXingCardMain.starPoints.get(id)).x, ((Point)JpanelXingCardMain.starPoints.get(id)).y, 40, 40);
                this.starLabels[j].setBright(isStarOpen(this.radioType, (int)id));
                this.starLabels[j].setVisible(true);
            }
            else {
                this.starLabels[j].setVisible(false);
            }
        }
        this.isChoose = false;
        String path = "resource/mouse/xp/XPSkill";
        if (!isAllStarOpen(this.radioType, list)) {
            path += "-1";
        }
        else if (SkillLearn3.isExistSkill(JpanelXingCardMain.skillNames[this.radioType - 1])) {
            this.isChoose = true;
        }
        else {
            this.isChoose = false;
        }
        this.getCardImg().setIcon(CutButtonImage.getImage(path + ".png", 50, 50));
        switch (this.radioType) {
            case 1: {
                this.getSkillBackImg().setBounds(300, 80, 56, 56);
                break;
            }
            case 2: {
                this.getSkillBackImg().setBounds(360, 115, 56, 56);
                break;
            }
            case 3: {
                this.getSkillBackImg().setBounds(390, 190, 56, 56);
                break;
            }
            case 4: {
                this.getSkillBackImg().setBounds(350, 260, 56, 56);
                break;
            }
            case 5: {
                this.getSkillBackImg().setBounds(300, 280, 56, 56);
                break;
            }
            case 6: {
                this.getSkillBackImg().setBounds(234, 275, 56, 56);
                break;
            }
            case 7: {
                this.getSkillBackImg().setBounds(190, 198, 56, 56);
                break;
            }
            case 8: {
                this.getSkillBackImg().setBounds(226, 120, 56, 56);
                break;
            }
        }
        this.getCardImg().setBounds(this.getSkillBackImg().getX() + 3, this.getSkillBackImg().getY() + 3, 50, 50);
        this.getCardImg().setText(JpanelXingCardMain.skillNames[this.radioType - 1]);
        this.openSuperSkill();
        if (is) {
            this.setIsPlayXPKM(Boolean.valueOf(true));
        }
    }
    
    public void openSkill(BaseXingpans baseXingpans) {
        if (isAllStarOpen(baseXingpans.getBh())) {
            RoleData.getRoleData().getPrivateData().setSkills("X", null);
            SendRoleAndRolesummingUntil.sendRole(RoleData.getRoleData().getPrivateData());
            String skillName = JpanelXingCardMain.skillNames[baseXingpans.getBh() - 1];
            ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.XPSKILL)).menuControl(skillName);
            FrameMessageChangeJpanel.addtext(5, "你学会了" + skillName, null, null);
            this.openSuperSkill();
            this.isChoose = true;
        }
    }
    
    public void openSuperSkill() {
        int sum = 0;
        for (int i = 0; i < this.starCardBtns.length; ++i) {
            if (this.starCardBtns[i].isOpen()) {
                ++sum;
            }
        }
        this.bigSkillLab.setVisible(false);
        if (sum > 0) {
            JpanelXingCardMain.tcp_cistern = SpriteFactory.VloadSprite("resource/mouse/xp/XPJD" + sum + ".tcp", null);
            if (sum >= 8) {
                this.bigSkillLab.setVisible(true);
                ((NpcMenuAction)MapAction.npcmenuAction.get(NpcMenuUntil.XPSKILL)).menuControl("天罡八卦");
            }
        }
        else {
            JpanelXingCardMain.tcp_cistern = null;
        }
    }
    
    public StarLabel[] getStarLabels() {
        if (this.starLabels == null) {
            this.starLabels = new StarLabel[6];
            for (int i = 0; i < this.starLabels.length; ++i) {
                (this.starLabels[i] = new StarLabel()).setOpaque(false);
                this.starLabels[i].setForeground(Color.white);
                this.starLabels[i].setFont(UIUtils.TEXT_FONT);
                this.starLabels[i].setVisible(true);
                this.add(this.starLabels[i]);
            }
        }
        return this.starLabels;
    }
    
    public JLabel getCardBackImg() {
        if (this.cardBackImg == null) {
            (this.cardBackImg = new JLabel()).setBounds(186, 80, 274, 274);
            this.cardBackImg.setIcon(CutButtonImage.getImage("inkImg/background/xpbj8.png", 274, 274));
            this.add(this.cardBackImg);
        }
        return this.cardBackImg;
    }
    
    public JLabel getCardImg() {
        if (this.cardImg == null) {
            (this.cardImg = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (JpanelXingCardMain.this.isChoose) {
                        JpanelXingCardMain.tcp_choose.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                        JpanelXingCardMain.tcp_choose.draw(g, 0, 0);
                    }
                }
            }).setBounds(325, 245, 50, 50);
            this.cardImg.setIcon(CutButtonImage.getImage("resource/mouse/xp/XPSkill.png", 50, 50));
            this.cardImg.setOpaque(false);
            this.cardImg.setText("源源不绝");
            this.cardImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        Vector<BaseXingpans> xingpansVector = RoleProperty.getRoleProperty().xingpanVector;
                        for (int z = 0; z < xingpansVector.size(); ++z) {
                            BaseXingpans baseXingpans = (BaseXingpans)xingpansVector.get(z);
                            if (baseXingpans.getBh() == JpanelXingCardMain.this.radioType) {
                                JpanelXingCardMain.this.openSkill(baseXingpans);
                                return;
                            }
                        }
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (JpanelXingCardMain.this.cardImg.isVisible()) {
                        Skill skill = UserMessUntil.getskill1(JpanelXingCardMain.this.cardImg.getText());
                        if (skill == null) {
                            return;
                        }
                        MsgJframe.getJframe().getJapnel().SM2(skill);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    FormsManagement.HideForm(46);
                }
            });
            this.add(this.cardImg);
        }
        return this.cardImg;
    }
    
    public JLabel getSkillBackImg() {
        if (this.skillBackImg == null) {
            (this.skillBackImg = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/tupiankuang/Z1007.png", 56, 56));
            this.skillBackImg.setOpaque(false);
            this.add(this.skillBackImg);
        }
        return this.skillBackImg;
    }
    
    public JLabel getBigSkillLab() {
        if (this.bigSkillLab == null) {
            (this.bigSkillLab = new JLabel()).setBounds(476, 374, 62, 62);
            this.bigSkillLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Skill skill = UserMessUntil.getskill1("天罡八卦");
                    if (skill == null) {
                        return;
                    }
                    MsgJframe.getJframe().getJapnel().SM2(skill);
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    FormsManagement.HideForm(46);
                }
            });
            this.bigSkillLab.setOpaque(false);
            this.add(this.bigSkillLab);
        }
        return this.bigSkillLab;
    }
    
    public void setCardImg(JLabel cardImg) {
        this.cardImg = cardImg;
    }
    
    public BtnStarCard[] starCardBtns() {
        if (this.starCardBtns == null) {
            this.starCardBtns = new BtnStarCard[8];
            Rectangle[] points = { new Rectangle(248, 10, 135, 61), new Rectangle(389, 32, 115, 113), new Rectangle(470, 146, 65, 137), new Rectangle(398, 284, 114, 115), new Rectangle(263, 364, 138, 65), new Rectangle(148, 294, 114, 116), new Rectangle(114, 160, 65, 140), new Rectangle(139, 44, 114, 113) };
            for (int i = 0; i < this.starCardBtns.length; ++i) {
                Sprite sprite = SpriteFactory.VloadSprite("resource/mouse/特效/xp-" + (i + 1) + "-d.tcp", null);
                AstrolabeBorderAnimation astrolabeBorderAnimation = new AstrolabeBorderAnimation();
                astrolabeBorderAnimation.setSprite(sprite);
                astrolabeBorderAnimation.setX(Integer.valueOf(points[i].x));
                astrolabeBorderAnimation.setY(Integer.valueOf(points[i].y));
                this.astrolabeBorderAnimationArr[i] = astrolabeBorderAnimation;
                (this.starCardBtns[i] = new BtnStarCard("inkImg/button/xp" + (i + 1) + ".png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "", i + 1, this)).setBounds(points[i]);
                this.add(this.starCardBtns[i]);
            }
        }
        return this.starCardBtns;
    }
    
    public BtnStarCard starCardBtn() {
        if (this.starCardBtn == null) {
            (this.starCardBtn = new BtnStarCard("inkImg/button/XPD.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "", 0, this)).setBounds(0, 0, 64, 59);
            this.add(this.starCardBtn);
        }
        return this.starCardBtn;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon();
            }
            BufferedImage read = ImageIO.read(new File("resource/mouse/xp/XPBack0000.png"));
            Graphics graphics = read.getGraphics();
            int index = this.radioType - 1;
            Sprite sprite = this.astrolabeBorderAnimationArr[index].getSprite();
            sprite.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            sprite.draw(graphics, (int)this.astrolabeBorderAnimationArr[index].getX(), (int)this.astrolabeBorderAnimationArr[index].getY());
            this.drawBG(graphics);
            this.iconBack.setImage(read);
            g.drawImage(this.iconBack.getImage(), 0, 0, 646, 464, this);
            if (JpanelXingCardMain.tcp_cistern != null) {
                JpanelXingCardMain.tcp_cistern.updateToTime((System.currentTimeMillis() - JpanelXingCardMain.currentTimeMillis) / 1L, 1);
                JpanelXingCardMain.tcp_cistern.draw(g, 508, 405);
            }
            g.setColor(Color.white);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.starCardBtn.setBounds(121, 376, 64, 59);
    }
    
    private void drawBG(Graphics g) {
        long l = System.currentTimeMillis();
        l -= this.time;
        if (this.time != 0L) {
            if (this.isPlayXPKM && l <= 1900L) {
                JpanelXingCardMain.tcp_xp_km.updateToTime(l, 1);
                JpanelXingCardMain.tcp_xp_km.draw(g, 322, 216);
            }
            else {
                this.setIsPlayXPKM(Boolean.valueOf(false));
                this.time = 0L;
            }
        }
    }
    
    static {
        JpanelXingCardMain.tcp_xp_km = SpriteFactory.VloadSprite("resource/mouse/特效/xp-km.tcp", null);
        JpanelXingCardMain.skillNames = new String[] { "甘霖瑞雪", "雾掩青山", "知耻后勇", "神清气正", "荆棘束身", "力击八方", "幻影谜踪", "源源不绝" };
        JpanelXingCardMain.starMap = new HashMap<>();
        JpanelXingCardMain.starPoints = new HashMap<>();
        for (int i = 1; i <= 8; ++i) {
            List<Integer> list = new ArrayList<>();
            switch (i) {
                case 1: {
                    list.add(Integer.valueOf(36));
                    list.add(Integer.valueOf(35));
                    list.add(Integer.valueOf(34));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(36), new Point(240, 140));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(35), new Point(280, 230));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(34), new Point(374, 182));
                    break;
                }
                case 2: {
                    list.add(Integer.valueOf(20));
                    list.add(Integer.valueOf(19));
                    list.add(Integer.valueOf(18));
                    list.add(Integer.valueOf(17));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(20), new Point(248, 100));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(19), new Point(228, 195));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(18), new Point(270, 250));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(17), new Point(338, 218));
                    break;
                }
                case 3: {
                    list.add(Integer.valueOf(16));
                    list.add(Integer.valueOf(15));
                    list.add(Integer.valueOf(14));
                    list.add(Integer.valueOf(13));
                    list.add(Integer.valueOf(12));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(16), new Point(340, 92));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(15), new Point(240, 178));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(14), new Point(222, 244));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(13), new Point(292, 224));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(12), new Point(390, 120));
                    break;
                }
                case 4: {
                    list.add(Integer.valueOf(11));
                    list.add(Integer.valueOf(10));
                    list.add(Integer.valueOf(9));
                    list.add(Integer.valueOf(8));
                    list.add(Integer.valueOf(7));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(11), new Point(354, 86));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(10), new Point(378, 142));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(9), new Point(284, 176));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(8), new Point(314, 225));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(7), new Point(248, 230));
                    break;
                }
                case 5: {
                    list.add(Integer.valueOf(6));
                    list.add(Integer.valueOf(5));
                    list.add(Integer.valueOf(4));
                    list.add(Integer.valueOf(3));
                    list.add(Integer.valueOf(2));
                    list.add(Integer.valueOf(1));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(6), new Point(288, 112));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(5), new Point(234, 154));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(4), new Point(300, 196));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(3), new Point(220, 215));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(2), new Point(392, 168));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(1), new Point(382, 116));
                    break;
                }
                case 6: {
                    list.add(Integer.valueOf(25));
                    list.add(Integer.valueOf(24));
                    list.add(Integer.valueOf(23));
                    list.add(Integer.valueOf(22));
                    list.add(Integer.valueOf(21));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(25), new Point(286, 100));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(24), new Point(352, 106));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(23), new Point(346, 160));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(22), new Point(420, 176));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(21), new Point(320, 248));
                    break;
                }
                case 7: {
                    list.add(Integer.valueOf(29));
                    list.add(Integer.valueOf(28));
                    list.add(Integer.valueOf(27));
                    list.add(Integer.valueOf(26));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(29), new Point(288, 88));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(28), new Point(284, 142));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(27), new Point(352, 188));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(26), new Point(384, 268));
                    break;
                }
                case 8: {
                    list.add(Integer.valueOf(33));
                    list.add(Integer.valueOf(32));
                    list.add(Integer.valueOf(31));
                    list.add(Integer.valueOf(30));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(33), new Point(228, 190));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(32), new Point(240, 250));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(31), new Point(332, 186));
                    JpanelXingCardMain.starPoints.put(Integer.valueOf(30), new Point(390, 110));
                    break;
                }
            }
            JpanelXingCardMain.starMap.put(Integer.valueOf(i), list);
        }
        JpanelXingCardMain.tcp_choose = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
        JpanelXingCardMain.currentTimeMillis = System.currentTimeMillis();
        JpanelXingCardMain.tcp_star = SpriteFactory.VloadSprite("resource/mouse/xp/XPBackDL.tcp", null);
        JpanelXingCardMain.starLabelBack = new ImageIcon("inkImg/button/B213.png");
    }
    
    public class StarLabel extends JLabel
    {
        private int id;
        private String starName;
        private boolean isBright;
        private boolean isShow;
        private long time;
        
        public StarLabel() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!StarLabel.this.isBright) {
                        Goodstable goodstable = UserMessUntil.getgoodstable(BigDecimal.valueOf((long)(51000 + StarLabel.this.id - 1)));
                        int goodNum = (goodstable != null) ? GoodsListFromServerUntil.getStarSoulNum(goodstable.getGoodsid()) : 0;
                        if (goodNum <= 0) {
                            ZhuFrame.getZhuJpanel().addPrompt2("数量不足");
                            return;
                        }
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.openStarCard, "Z1=" + JpanelXingCardMain.this.radioType + "=" + StarLabel.this.id, "#Y你确定要激活 #G" + JpanelXingCardMain.getStarNameById(StarLabel.this.id) + "#Y这个星魂吗?");
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            if (this.isShow) {
                int x = 0;
                int y = 20;
                g.drawImage(JpanelXingCardMain.starLabelBack.getImage(), x + 2, y, null);
                if (this.isBright) {
                    this.time += 20L;
                    JpanelXingCardMain.tcp_star.updateToTime(this.time, 1);
                    JpanelXingCardMain.tcp_star.draw(g, x + 11, y + 8);
                }
                if (this.time >= 2147483647L) {
                    this.time = 0L;
                }
                g.setFont(this.getFont());
                g.setColor(this.getForeground());
                g.drawString(this.starName, x, y - 5);
            }
        }
        
        public void setShow(boolean show) {
            this.isShow = show;
        }
        
        public String getStarName() {
            return this.starName;
        }
        
        public void setStarName(String starName) {
            this.starName = starName;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public int getId() {
            return this.id;
        }
        
        public boolean isBright() {
            return this.isBright;
        }
        
        public void setBright(boolean bright) {
            this.isBright = bright;
        }
    }
}
