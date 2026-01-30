package come.tool.Fighting;

import org.come.until.CutButtonImage;
import org.come.bean.PathPoint;
import com.tool.image.ImageMixDeal;
import org.come.bean.LoginResult;
import org.come.good.Consumptions;
import com.tool.role.RoleData;
import org.apache.commons.lang.StringUtils;
import com.updateNew.MyIsif;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Font;
import com.tool.image.ManimgAttribute;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import com.tool.tcpimg.UIUtils;
import org.come.until.Util;
import com.tool.tcp.SpriteFactory;
import org.come.until.ScrenceUntil;
import java.awt.Graphics;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.AccessTeamInfoUntil;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import com.tool.tcp.Sprite;
import java.awt.Color;
import com.tool.tcpimg.FloatPanel;
import com.tool.tcp.NewPart;
import java.util.List;

public class Fightingimage
{
    public static List<Integer> moved;
    private int x;
    private int y;
    private int suo;
    private FightingManData fightingManData;
    private long time;
    private NewPart part;
    private int dir;
    private String[] names;
    private List<FloatPanel> chatPanels;
    private Color nameColor;
    private static int num;
    private static int num22;
    private boolean isHigh;
    public long bl1;
    public Sprite rolef;
    public static ImageIcon[] xuelans;
    int size;
    private boolean isOperation;
    private ImageIcon OperationTimeIcon;
    boolean tem;
    private List<DataDisplay> displays;
    private static int currentValue;
    
    public Color getNameColor() {
        return this.nameColor;
    }
    
    public void setNameColor(Color nameColor) {
        this.nameColor = nameColor;
    }
    
    public void setDefaultNameColor() {
        this.nameColor = null;
    }
    
    public void Dialogue(String text) {
        if (text == null || text.equals("")) {
            return;
        }
        this.chatPanels.add(new FloatPanel(text));
    }
    
    public Fightingimage(FightingManData fightingManData, int my_Camp) {
        this.suo = 0;
        this.chatPanels = new ArrayList<>();
        this.nameColor = null;
        this.isHigh = false;
        this.size = 0;
        this.OperationTimeIcon = new ImageIcon("inkImg/button/wait.png");
        this.tem = false;
        this.bl1 = 0L;
        fightingManData.initState();
        this.fightingManData = fightingManData;
        if (this.fightingManData.ztcz("隐身")) {
            this.fightingManData.setAlpha(0.3f);
        }
        this.part = fightingManData.getPart();
        if (fightingManData.getHp_Current() <= 0) {
            this.part.setAct(8);
        }
        else {
            this.part.setAct(7);
        }
        if (fightingManData.getCamp() == my_Camp || (fightingManData.getCamp() == 1 && my_Camp == -1)) {
            this.dir = 7;
        }
        else {
            this.dir = 3;
        }
        this.tiaoz(my_Camp);
        this.names = AccessTeamInfoUntil.getss(fightingManData.getManname());
        if (FightingMixDeal.CurrentRound != 1) {
            return;
        }
        if (fightingManData.getCamp() == 0 && fightingManData.getMan() == 2) {
            if (FightingMixDeal.BattleType == 15) {
                this.chatPanels.add(new FloatPanel("大胆狗贼!哪里逃"));
            }
            else if (FightingMixDeal.BattleType == 16) {
                this.chatPanels.add(new FloatPanel("现在年轻人真是没有眼力劲"));
            }
        }
        this.getmsg();
    }
    
    public boolean setisHigh(boolean t) {
        return this.isHigh = t;
    }
    
    public void getmsg() {
        if (this.fightingManData.getHp_Current() <= 0) {
            return;
        }
        String msg = this.fightingManData.getMsg();
        if (msg == null || msg.equals("")) {
            return;
        }
        this.chatPanels.add(new FloatPanel(msg));
        FrameMessageChangeJpanel.addtext(0, msg, null, this.fightingManData.getManname());
    }
    
    public boolean Drawing(Graphics g, long dietime) {
        if (this.fightingManData.getStates().contains("乾坤破阵") || this.fightingManData.getStates().contains("9307S")) {
            return true;
        }
        long time = this.time;
        if (this.part != null) {
            if (this.part.getTCP() != null) {
                if (this.isHigh) {
                    this.part.getTCP().setisHigh(true);
                }
                else {
                    this.part.getTCP().setisHigh(false);
                }
            }
            this.fightingManData.draw2(g, time, this.x, this.y);
            if (this.part.getAct() == 8) {
                this.part.drawEnd(g, this.x, this.y, this.dir, this.fightingManData.getAlpha());
            }
            else {
                int tmp = 0;
                if (this.fightingManData.getType() == 3 && this.x > ScrenceUntil.Screen_x / 2) {
                    tmp += 10;
                }
                if (this.fightingManData.getType() == 3 && this.part.getAct() == 5) {
                    this.part.drawBattle(g, ScrenceUntil.Screen_x / 2, ScrenceUntil.Screen_y / 2, this.dir, time, this.fightingManData.getAlpha());
                }
                else {
                    this.part.drawBattle(g, this.x + tmp, this.y, this.dir, time, this.fightingManData.getAlpha());
                }
            }
            this.drawArticle(g);
            this.fightingManData.draw1(g, time, this.x, this.y);
        }
        if (this.isHigh) {
            this.rolef = SpriteFactory.Prepare("resource/mouse/jiantou.tcp");
            if (this.rolef != null) {
                this.rolef.updateToTime(this.bl1, 0);
                this.rolef.draw(g, this.x - 14, this.y - 175);
                if (this.rolef.getCurrFrame().equals(this.rolef.getFrames()[this.rolef.getFrames().length - 1])) {
                    this.bl1 = 0L;
                }
                this.bl1 += 12L;
            }
        }
        this.drawfont(g);
        return true;
    }
    
    public void Drawing2(Graphics g, long dietime) {
        this.getDisplays();
        for (int i = this.displays.size() - 1; i >= 0; --i) {
            if (this.displays.get(i).draw(g, this.x, this.y - 25 + i * 20, this.fightingManData.getCamp(), dietime)) {
                this.displays.remove(i);
            }
        }
    }
    
    private boolean shouldDisplay(FloatPanel chatPanel) {
        return Util.getTime() - chatPanel.getCreateTime() < Util.TIME_CHAT;
    }
    
    public void drawSay(Graphics g) {
        if (this.chatPanels.size() != 0) {
            int py = this.part.getPy();
            if (py != -1) {
                int chatY = this.y - py;
                for (int i = this.chatPanels.size() - 1; i >= 0; --i) {
                    FloatPanel chatPanel = (FloatPanel)this.chatPanels.get(i);
                    if (this.shouldDisplay(chatPanel)) {
                        int chatX = this.x - chatPanel.getWidth() / 2;
                        chatY -= chatPanel.getHeight() + 2;
                        g.translate(chatX, chatY);
                        chatPanel.paint(g);
                        g.translate(-chatX, -chatY);
                    }
                    else {
                        chatPanel.remove();
                        this.chatPanels.remove(i);
                    }
                }
            }
        }
    }
    
    public void drawfont(Graphics g) {
        String mes = this.fightingManData.getModel();
        String[] mes2 = mes.split("_");
        String aaa = mes2[0];
        if (aaa.equals("6001")) {
            if (getNum() == 0 && this.part.getAct() != 4 && this.part.getAct() != 8) {
                this.part.setAct(7);
            }
            else if (getNum() >= 1 && getNum() <= 185 && this.part.getAct() == 3) {
                this.part.setAct(12);
            }
            else if (getNum() >= 188 && this.part.getAct() != 4 && this.part.getAct() != 8) {
                this.part.setAct(12);
            }
        }
        if (aaa.equals("500150")) {
            if (getNum() == 0 && this.part.getAct() != 4 && this.part.getAct() != 8) {
                this.part.setAct(7);
            }
            else if (getNum() >= 1 && getNum() <= 185 && this.part.getAct() == 5) {
                this.part.setAct(3);
            }
            else if (getNum() >= 188 && this.part.getAct() != 4 && this.part.getAct() != 8) {
                this.part.setAct(11);
            }
        }
        if (this.names == null) {
            return;
        }
        g.setFont(UIUtils.TEXT_FONT2);
        int textY = this.y + 30;
        if (this.size == 0) {
            this.size = g.getFontMetrics().stringWidth(this.fightingManData.getManname()) / 2;
        }
        int textX = this.x - this.size;
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (this.fightingManData.getW() != null) {
            Image hz = ManimgAttribute.getHzImg((int)this.fightingManData.getW());
            g2d.drawImage(hz, textX - 28, textY - 18, null);
        }
        g2d.setFont(new Font("宋体", 1, 16));
        if (this.fightingManData.getType() == 0 && this.fightingManData.getStates().size() != 0) {
            for (int i = this.fightingManData.getStates().size() - 1; i >= 0; --i) {
                if (((String)this.fightingManData.getStates().get(i)).equals("乾坤遮天") || ((String)this.fightingManData.getStates().get(i)).equals("乾坤破阵")) {
                    return;
                }
            }
        }
        if (this.fightingManData.getType() == 0) {
            FontMetrics fm = g2d.getFontMetrics();
            for (int j = 0; j < this.names.length; ++j) {
                g2d.setColor(UIUtils.COLOR_NAME8);
                g2d.drawString(this.names[j], textX + 1, textY + 1);
                g2d.setColor((this.nameColor == null) ? UIUtils.getcolor(this.fightingManData.getZs()) : this.nameColor);
                g2d.drawString(this.names[j], textX, textY);
                textX += fm.stringWidth(this.names[j]) - 1;
            }
        }
        else if (this.fightingManData.getType() == 1) {
            if (this.fightingManData.getStates().size() != 0) {
                for (int i = this.fightingManData.getStates().size() - 1; i >= 0; --i) {
                    if (((String)this.fightingManData.getStates().get(i)).equals("乾坤遮天") || ((String)this.fightingManData.getStates().get(i)).equals("乾坤破阵")) {
                        return;
                    }
                }
            }
            FontMetrics fm = g2d.getFontMetrics();
            for (int j = 0; j < this.names.length; ++j) {
                g2d.setColor(UIUtils.COLOR_NAME8);
                g2d.drawString(this.names[j], textX + 6, textY + 3);
                g2d.setColor((this.nameColor == null) ? UIUtils.getPetcolor(this.fightingManData.getZs()) : this.nameColor);
                g2d.drawString(this.names[j], textX + 5, textY + 2);
                textX += fm.stringWidth(this.names[j]) - 1;
            }
        }
        else if (this.fightingManData.getType() == 2) {
            FontMetrics fm = g2d.getFontMetrics();
            if (this.fightingManData.getType() == 3) {
                textY += 15;
            }
            else if (this.fightingManData.getType() == 4) {
                textY -= 5;
            }
            for (int j = 0; j < this.names.length; ++j) {
                g2d.setColor(UIUtils.COLOR_NAME8);
                g2d.drawString(this.names[j], textX + 6, textY + 1);
                g2d.setColor((this.nameColor == null) ? UIUtils.COLOR_Y2 : this.nameColor);
                g2d.drawString(this.names[j], textX + 5, textY);
                textX += fm.stringWidth(this.names[j]) - 1;
            }
        }
        else {
            //灵宝小孩名称坐标
            FontMetrics fm = g2d.getFontMetrics();
            if (this.fightingManData.getType() == 3) {
                textY += 8;//灵宝名称坐标
            }
            else if (this.fightingManData.getType() == 4) {
                textY -= -45;//孩子名称坐标
            }
            for (int j = 0; j < this.names.length; ++j) {
                //黑色字体
                g2d.setColor(UIUtils.COLOR_NAME8);
                g2d.drawString(this.names[j], textX + 6, textY - 50);
                //浅色字体
                g2d.setColor((this.nameColor == null) ? UIUtils.COLOR_NAME10 : this.nameColor);
                g2d.drawString(this.names[j], textX + 5, textY- 50);
                textX += fm.stringWidth(this.names[j]) - 1;
            }
        }
        g2d.dispose();
    }
    
    public void drawArticle(Graphics g) {
        if (this.fightingManData.getStates().size() != 0) {
            for (int i = this.fightingManData.getStates().size() - 1; i >= 0; --i) {
                if (((String)this.fightingManData.getStates().get(i)).equals("乾坤遮天") || ((String)this.fightingManData.getStates().get(i)).equals("乾坤破阵")) {
                    return;
                }
            }
        }
        if (this.fightingManData.getType() == 3 || this.fightingManData.getType() == 4) {
            return;
        }
        if (this.fightingManData.getCamp() == FightingMixDeal.camp || FightingMixDeal.buffUtil.isMcqh) {
            if (MyIsif.getStyle().equals("水墨")) {
                int hp_bai = (int)((double)this.fightingManData.getHp_Current() / (double)this.fightingManData.getHp_Total() * 66.0);
                if (hp_bai > 66) {
                    hp_bai = 66;
                }
                else if (hp_bai < 0) {
                    hp_bai = 0;
                }
                g.drawImage(Fightingimage.xuelans[0].getImage(), this.x - 35, this.y - 125, 66, 5, null);
                g.drawImage(Fightingimage.xuelans[1].getImage(), this.x - 35, this.y - 125, hp_bai, 5, null);
                int mp_bai = (int)((double)this.fightingManData.getMp_Current() / (double)this.fightingManData.getMp_Total() * 66.0);
                if (mp_bai > 66) {
                    mp_bai = 66;
                }
                else if (mp_bai < 0) {
                    mp_bai = 0;
                }
                g.drawImage(Fightingimage.xuelans[0].getImage(), this.x - 35, this.y - 119, 66, 5, null);
                g.drawImage(Fightingimage.xuelans[2].getImage(), this.x - 35, this.y - 119, mp_bai, 5, null);
            }
            else {
                int hp_bai = (int)((double)this.fightingManData.getHp_Current() / (double)this.fightingManData.getHp_Total() * 66.0);
                if (hp_bai > 66) {
                    hp_bai = 66;
                }
                else if (hp_bai < 0) {
                    hp_bai = 0;
                }
                g.drawImage(Fightingimage.xuelans[0].getImage(), this.x - 35, this.y - 125, 66, 5, null);
                g.drawImage(Fightingimage.xuelans[1].getImage(), this.x - 35, this.y - 125, hp_bai, 5, null);
                int mp_bai = (int)((double)this.fightingManData.getMp_Current() / (double)this.fightingManData.getMp_Total() * 66.0);
                if (mp_bai > 66) {
                    mp_bai = 66;
                }
                else if (mp_bai < 0) {
                    mp_bai = 0;
                }
                g.drawImage(Fightingimage.xuelans[0].getImage(), this.x - 35, this.y - 119, 66, 5, null);
                g.drawImage(Fightingimage.xuelans[2].getImage(), this.x - 35, this.y - 119, mp_bai, 5, null);
            }
        }
    }
    
    public void drawOperationTime(Graphics g) {
        if ((this.fightingManData.getType() == 0 || this.fightingManData.getType() == 1) && FightingMixDeal.camp == this.fightingManData.getCamp() && !this.isOperation) {
            g.drawImage(this.OperationTimeIcon.getImage(), this.x - 50, this.y - 125, 13, 13, null);
        }
    }
    
    public void setOperation(boolean operation) {
        if ((this.fightingManData.getType() == 0 || this.fightingManData.getType() == 1) && FightingMixDeal.camp == this.fightingManData.getCamp()) {
            this.isOperation = operation;
        }
    }
    
    public boolean zhixnig(StateProgress zhixing) {
        if (this.fightingManData.getStates().size() != 0) {
            for (int i = this.fightingManData.getStates().size() - 1; i >= 0; --i) {
                if (((String)this.fightingManData.getStates().get(i)).equals("天蓬转世")) {
                    this.fightingManData.setB(true);
                    this.bianshen(this.fightingManData, false);
                    this.tem = true;
                }
                else {
                    this.fightingManData.setB(false);
                }
            }
        }
        else {
            this.fightingManData.setB(false);
        }
        if (this.tem) {
            int k = 0;
            for (int q = FightingMixDeal.CurrentData.size() - 1; q >= 0; --q) {
                if (!((Fightingimage)FightingMixDeal.CurrentData.get(q)).getFightingManData().getB()) {
                    ++k;
                }
            }
            if (k == FightingMixDeal.CurrentData.size()) {
                this.bianshen(null, false);
                this.tem = false;
            }
        }
        if (zhixing.getZxzt() == 2) {
            return false;
        }
        if (zhixing.getZxzt() == 0) {
            this.addDataDisplay(zhixing);
            FightingMixDeal.LoadMusic(zhixing.getMusic());
            FightingMixDeal.LoadMusic(zhixing.getExtSound());
            this.addSkill(zhixing.getSpell());
            this.Dialogue(zhixing.getText());
            if (zhixing.getMusic().equals("5471116")) {
                FightingMixDeal.LoadMusic("暗影离魂");
                this.setNum(getNum() + 2);
            }
            if (zhixing.getMusic().equals("547111")) {
                this.setNum(getNum() + 186);
            }
            if (zhixing.getMusic().equals("亢龙有悔")) {
                this.setNum(getNum() + 1);
            }
            if (this.suo == 0) {
                zhixing.setZxzt(this.suo = 1);
                this.Change(zhixing.getType(), zhixing.getDir());
            }
            else {
                zhixing.setZxzt(2);
                this.fightingManData.chang(zhixing.getHp_Change(), zhixing.getMp_Change(), zhixing.getYq_Change(), zhixing.getNq_Change(), zhixing.getXyz());
                this.fightingManData.addstate(zhixing.getAddchixu());
                this.fightingManData.deletestate(zhixing.getDeletechixu());
                this.fightingManData.addstate(zhixing.getAddchixu1());
                FightingMixDeal.buffUtil.CS(zhixing.getBuff(), FightingMixDeal.camp);
                return false;
            }
        }
        else if (zhixing.getZxzt() == 1) {
            if (zhixing.getPath() != null) {
                if (!this.pathmove(zhixing.getPath(), zhixing.getData2())) {
                    this.setNum(0);
                    this.setNum22(0);
                    zhixing.setZxzt(2);
                    this.suo = 3;
                    this.endtype(zhixing);
                    this.fightingManData.addstate(zhixing.getAddchixu());
                    this.fightingManData.deletestate(zhixing.getDeletechixu());
                    this.fightingManData.addstate(zhixing.getAddchixu1());
                    FightingMixDeal.buffUtil.CS(zhixing.getBuff(), FightingMixDeal.camp);
                }
            }
            else if (this.suo == 1 && this.tcpend(zhixing)) {
                this.suo = 2;
                this.endtype(zhixing);
            }
            else if (this.suo == 2) {
                this.suo = 3;
                zhixing.setZxzt(2);
                this.fightingManData.addstate(zhixing.getAddchixu());
                this.fightingManData.deletestate(zhixing.getDeletechixu());
                this.fightingManData.addstate(zhixing.getAddchixu1());
                FightingMixDeal.buffUtil.CS(zhixing.getBuff(), FightingMixDeal.camp);
            }
        }
        if (this.suo == 3) {
            this.suo = 0;
            return false;
        }
        return true;
    }
    
    private void addSkill(SkillSpell skill) {
        if (skill == null) {
            return;
        }
        if (skill.getSkinpath() == 0 || skill.getSkinpath() == 4) {
            FightingMixDeal.skills.add(skill);
        }
        else {
            for (int i = FightingMixDeal.skills.size() - 1; i >= 0; --i) {
                this.setNum(0);
                SkillSpell skillSpell = (SkillSpell)FightingMixDeal.skills.get(i);
                if (skillSpell.getRound() == skill.getRound() && skillSpell.getSkillid().equals(skill.getSkillid())) {
                    return;
                }
            }
            FightingMixDeal.skills.add(skill);
            this.setNum(0);
        }
    }
    
    public void endtype(StateProgress zhixing) {
        if (zhixing.getMp_Change() < 0 && StringUtils.isNotBlank(zhixing.getSkillName())) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            if (this.fightingManData.getManname().equals(loginResult.getRolename())) {
                Consumptions.addSkillProficiency(zhixing.getSkillName(), 1);
            }
        }
        this.fightingManData.chang(zhixing.getHp_Change(), zhixing.getMp_Change(), zhixing.getYq_Change(), zhixing.getNq_Change(), zhixing.getXyz());
        if ("召唤".equals(zhixing.getData2())) {
            this.Change(0, zhixing.getDirend());
        }
        else {
            this.Change((this.fightingManData.getHp_Current() > 0) ? 7 : 8, zhixing.getDirend());
        }
    }
    
    public void bianshen(FightingManData manData, boolean k) {
        String skin = this.fightingManData.getModel();
        String skin2 = this.fightingManData.getModel();
        String[] strs1 = skin2.split("_");
        int m = strs1[0].indexOf("tx");
        if (skin.indexOf("&") != 0 && m != -1) {
            String skin3 = "";
            String skin4 = "";
            char[] skinfanzhuan = skin.toCharArray();
            for (int f = skinfanzhuan.length - 1; f >= 0; --f) {
                skin3 += skinfanzhuan[f];
            }
            String[] strs2 = skin3.split("&");
            skinfanzhuan = strs2[0].toCharArray();
            for (int s = skinfanzhuan.length - 1; s >= 0; --s) {
                skin4 += skinfanzhuan[s];
            }
            strs2 = skin4.split("_");
        }
        else {
            String[] strs2 = skin.split("_");
        }
        if (manData != null) {
            List<String> p = manData.getStates();
            if (p.size() != 0) {
                for (int i = p.size() - 1; i >= 0; --i) {
                    if (p.get(i).equals("乾坤遮天")) {
                        this.part = SpriteFactory.createPart("183", this.part.getAct(), 1, null);
                    }
                    ImageMixDeal.userimg.changeskin("183");
                    if (p.get(i).equals("天蓬转世")) {
                        this.part = SpriteFactory.createPart("400149", this.part.getAct(), 1, null);
                    }
                }
            }
        }
        for (int q = FightingMixDeal.CurrentData.size() - 1; q >= 0; --q) {
            if (FightingMixDeal.CurrentData.get(q).getFightingManData().getB()) {
                return;
            }
        }
        ImageMixDeal.userimg.changeskin(null);
    }
    
    public boolean pathmove(List<PathPoint> path, String type) {
        if (type.equals("遁地")) {
            long time = this.getTCPTime();
            if (path.size() > 1) {
                this.x = ((PathPoint)path.get(1)).getX();
                this.y = ((PathPoint)path.get(1)).getY();
                path.clear();
            }
            else if (path.size() == 1) {
                this.x = ((PathPoint)path.get(0)).getX();
                this.y = ((PathPoint)path.get(0)).getY();
                path.clear();
            }
            if (this.time > time) {
                return false;
            }
        }
        else if (FightingMove2.getmovetime(this, path)) {
            for (int i = 0; i <= FightingMixDeal.CurrentData.size() - 1; ++i) {
                Fightingimage fightingimage = (Fightingimage)FightingMixDeal.CurrentData.get(i);
                for (int k = 0; k <= fightingimage.fightingManData.getStates().size() - 1; ++k) {
                    if ("分身".equals(fightingimage.fightingManData.getStates().get(k))) {
                        fightingimage.fightingManData.deletestate("分身");
                    }
                }
            }
            path.clear();
            return false;
        }
        return true;
    }
    
    public List<DataDisplay> getDisplays() {
        if (this.displays == null) {
            this.displays = new ArrayList<>();
        }
        return this.displays;
    }
    
    public void addDataDisplay(StateProgress zhixing) {
        if (zhixing.getData() == null && zhixing.getHp_Change() == 0 && zhixing.getMp_Change() == 0) {
            return;
        }
        if (zhixing.getData() == null || (!zhixing.getData().equals("法力不足") && !zhixing.getData().equals("无法用药") && !zhixing.getData().equals("怨气不足"))) {
            this.getDisplays().add(new DataDisplay(zhixing.getData(), zhixing.getHp_Change(), zhixing.getMp_Change()));
        }
        else {
            int myman = FightingMixDeal.myman();
            int man = this.fightingManData.getMan();
            if (FightingMixDeal.camp == this.fightingManData.getCamp() && (myman == man || myman + 5 == man)) {
                this.getDisplays().add(new DataDisplay(zhixing.getData(), zhixing.getHp_Change(), zhixing.getMp_Change()));
            }
        }
    }
    
    public void tiaoz(int mycamp) {//修改战斗中孩子灵宝位置
        if (this.fightingManData.getType() == 3 || this.fightingManData.getType() == 4) {
            PathPoint pathPoint = this.Position(this.fightingManData.getCamp(), mycamp, this.fightingManData.getMan() - (this.fightingManData.getType() - 1) * 5);
            this.x = pathPoint.getX();
            this.y = pathPoint.getY() - 15;
            if (this.fightingManData.getCamp() == mycamp || (this.fightingManData.getCamp() == 1 && mycamp == -1)) {
                if (this.fightingManData.getType() == 4) {
                    this.x += 52;//我方孩子坐标
                    this.y = pathPoint.getY() - 5;
                }
                else {
                    this.x += 100;//我方灵宝位置
                    this.y += 60;
                }
            }
            else if (this.fightingManData.getType() == 4) {
                this.x -= 50;//敌方孩子坐标
            }
            else {
                this.x -= 90;//敌方灵宝位置
                this.y = pathPoint.getY() + 30;
            }
        }
        else {
            PathPoint pathPoint = this.Position(this.fightingManData.getCamp(), mycamp, this.fightingManData.getMan());
            this.x = pathPoint.getX();
            this.y = pathPoint.getY();
        }
    }
    
    public PathPoint Position(int Camp, int my_Camp, int man) {
        if (ScrenceUntil.Screen_x > 800) {
            if ((this.fightingManData.getCamp() == 1 && my_Camp == -1) || Camp == my_Camp) {
                if (man < 5) {
                    return new PathPoint(ScrenceUntil.Screen_x - 140 - 55 * man, ScrenceUntil.Screen_y - 505 + 95 * man);
                }
                man -= 5;
                return new PathPoint(ScrenceUntil.Screen_x - 265 - 55 * man, ScrenceUntil.Screen_y - 540 + 95 * man);
            }
            else {
                if (man < 5) {
                    return new PathPoint(300 - 55 * man, 150 + 95 * man);
                }
                man -= 5;
                return new PathPoint(425 - 55 * man, 170 + 95 * man);
            }
        }
        else if ((this.fightingManData.getCamp() == 1 && my_Camp == -1) || Camp == my_Camp) {
            if (man < 5) {
                return new PathPoint(ScrenceUntil.Screen_x - 70 - 65 * man, ScrenceUntil.Screen_y - 325 + 65 * man);
            }
            man -= 5;
            return new PathPoint(ScrenceUntil.Screen_x - 165 - 65 * man, ScrenceUntil.Screen_y - 390 + 65 * man);
        }
        else {
            if (man < 5) {
                return new PathPoint(330 - 65 * man, 150 + 65 * man);
            }
            man -= 5;
            return new PathPoint(425 - 65 * man, 215 + 65 * man);
        }
    }
    
    public boolean tcpend(StateProgress zhixing) {
        Double sp = (zhixing.getSp() != null) ? ((double) zhixing.getSp()) : 1.0;
        return (double)this.time >= (double)this.part.getTime() * (double)sp;
    }
    
    public void updateValue(int newValue) {
        if (Fightingimage.currentValue != newValue) {
            Fightingimage.currentValue = newValue;
            this.setNum22(0);
        }
        else {
            this.setNum22(getNum22() + 1);
        }
    }
    
    public void Change(int type, int dir) {
        if (type == 0 || type == 2) {
            return;
        }
        this.time = 0L;
        this.part.setAct(type);
        this.part.loadTcp();
        if (dir == 0) {
            dir = ((this.fightingManData.getCamp() == FightingMixDeal.camp || (this.fightingManData.getCamp() == 1 && FightingMixDeal.camp == -1)) ? 7 : 3);
        }
        this.dir = dir;
        String mes = this.fightingManData.getModel();
        String[] mes2 = mes.split("_");
        String aaa = mes2[0];
        if (aaa.equals("400315")) {
            this.setNum(9);
        }
        if (aaa.equals("900097")) {
            this.setNum(5);
        }
        if (aaa.equals("400518")) {
            aaa = "400127";
        }
        if (aaa.equals("400523")) {
            aaa = "400120";
        }
        if (aaa.equals("400521")) {
            aaa = "400121";
        }
        if (aaa.equals("400519")) {
            aaa = "400107";
        }
        if (aaa.equals("400520")) {
            aaa = "400108";
        }
        if (aaa.equals("400522")) {
            aaa = "400109";
        }
        if (aaa.equals("400524")) {
            aaa = "400110";
        }
        if (aaa.equals("400525")) {
            aaa = "400111";
        }
        if (aaa.equals("900058")) {
            aaa = "500150";
        }
        if (aaa.equals("900085")) {
            aaa = "500637";
        }
        if (aaa.equals("900086")) {
            aaa = "500159";
        }
        if (aaa.equals("900087")) {
            aaa = "9999";
        }
        if (aaa.equals("900088")) {
            aaa = "400009";
        }
        if (aaa.equals("900098")) {
            aaa = "500301";
        }
        if (aaa.equals("900099")) {
            aaa = "500300";
        }
        if (aaa.equals("900089")) {
            aaa = "400091";
        }
        if (aaa.equals("900090")) {
            aaa = "400088";
        }
        if (aaa.equals("900091")) {
            aaa = "400095";
        }
        if (aaa.equals("900088")) {
            aaa = "400103";
        }
        if (aaa.equals("900092")) {
            aaa = "400316";
        }
        if (aaa.equals("900093")) {
            aaa = "400138";
        }
        if (aaa.equals("900095")) {
            aaa = "400506";
        }
        if (aaa.equals("9514201")) {
            aaa = "52104";
        }
        if (aaa.equals("900097")) {
            aaa = "400315";
        }
        if (aaa.equals("300289")) {
            aaa = "400171";
        }
        String[] mes3 = mes.split("\\&");
        String[] vs = mes3[0].split("\\_");
        if (this.part.getAct() == 9) {
            this.setNum(getNum() + 1);
            this.setNum22(0);
        }
        if (this.part.getAct() == 5) {
            this.setNum(0);
            this.updateValue(this.fightingManData.getMan());
        }
        if (this.part.getAct() == 10) {
            this.setNum(0);
            this.setNum22(0);
        }
        if (this.part.getAct() == 8) {
            this.setNum(0);
            this.setNum22(0);
        }
    }
    
    public int getType() {
        return this.part.getAct();
    }
    
    public void setType(int type) {
        this.time = 0L;
        this.part.setAct(type);
    }
    
    public int getDir() {
        return this.dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
    
    public List<FloatPanel> getChatPanels() {
        if (FightingMixDeal.State == 6) {
            this.setNum(0);
            this.setNum22(0);
        }
        if (FightingMixDeal.State == 1) {
            this.setNum(0);
            this.setNum22(0);
        }
        return this.chatPanels;
    }
    
    public void setChatPanels(List<FloatPanel> chatPanels) {
        if (FightingMixDeal.State == 6) {
            this.setNum(0);
            this.setNum22(0);
        }
        if (FightingMixDeal.State == 1) {
            this.setNum(0);
            this.setNum22(0);
        }
        this.chatPanels = chatPanels;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public FightingManData getFightingManData() {
        if (FightingMixDeal.State == 6) {
            this.setNum(0);
            this.setNum22(0);
        }
        if (FightingMixDeal.State == 1) {
            this.setNum(0);
            this.setNum22(0);
        }
        return this.fightingManData;
    }
    
    public void setFightingManData(FightingManData fightingManData) {
        if (FightingMixDeal.State == 6) {
            this.setNum(0);
            this.setNum22(0);
        }
        if (FightingMixDeal.State == 1) {
            this.setNum(0);
            this.setNum22(0);
        }
        this.fightingManData = fightingManData;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void addTime(long time) {
        if (this.part.getAct() == 4 || this.part.getAct() == 9) {
            if (getNum() >= 2 && getNum() <= 4) {
                time = (long)((double)time * 2.3);
            }
            else if (getNum() > 4 && getNum() <= 20) {
                time = (long)((double)time * 2.6);
            }
            else {
                time = (long)((double)time * 2.1);
            }
        }
        else if (this.part.getAct() == 3) {
            if (getNum() >= 2 && getNum() <= 3) {
                time = (long)((double)time * 2.5);
            }
            else if (getNum() > 3 && getNum() <= 6) {
                time = (long)((double)time * 2.5);
            }
            else if (getNum() > 7 && getNum() <= 188) {
                time = (long)((double)time * 2.5);
            }
            else if (getNum() > 188 && getNum() <= 1880) {
                time = (long)((double)time * 2.5);
            }
            else if (getNum() > 1880 && getNum() <= 99999999) {
                time = (long)((double)time * 2.5);
            }
            else {
                time = (long)((double)time * 2.5);
            }
        }
//        else if (this.part.getAct() == 10) {
//            time *= 2L;
//        }
        else if (this.part.getAct() == 5) {
            if (getNum22() >= 1) {
                if (this.fightingManData.getType() == 1) {
                    time = (long)((double)time * 2.5);
                }
                else {
                    time = (long)((double)time * 2.5);
                }
            }
            else {
                time = (long)((double)time * 1.2);
            }
        }
        else if (this.part.getAct() == 12) {
            if (getNum() >= 2 && getNum() <= 3) {
                time = (long)((double)time * 2.3);
            }
            else if (getNum() > 3 && getNum() <= 6) {
                time = (long)((double)time * 2.6);
            }
            else if (getNum() > 7 && getNum() <= 188) {
                time = (long)((double)time * 2.6);
            }
            else if (getNum() > 188 && getNum() <= 1880) {
                time = (long)((double)time * 2.8);
            }
            else if (getNum() > 1880 && getNum() <= 99999999) {
                time = (long)((double)time * 20.8);
            }
            else {
                time = (long)((double)time * 1.8);
            }
        }
        else if (this.part.getAct() == 11) {
            if (getNum() >= 2 && getNum() <= 3) {
                time = (long)((double)time * 1.9);
            }
            else if (getNum() > 3 && getNum() <= 6) {
                time = (long)((double)time * 2.4);
            }
            else if (getNum() > 7 && getNum() <= 188) {
                time = (long)((double)time * 2.6);
            }
            else if (getNum() > 188 && getNum() <= 1880) {
                time = (long)((double)time * 2.8);
            }
            else if (getNum() > 1880 && getNum() <= 99999999) {
                time = (long)((double)time * 20.8);
            }
            else {
                time = (long)((double)time * 1.8);
            }
        }
        else {
            time = (long)((double)time * 1.2);
        }
        this.time += time;
    }
    
    public void setTime(long time) {
        this.time += time;
    }
    
    public long getTCPTime() {
        return this.part.getTime();
    }
    
    public boolean isContains(int x, int y) {
        return this.part.contains(x - this.x, y - this.y);
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
    }
    
    public static int getNum() {
        return Fightingimage.num;
    }
    
    public void setNum(int num) {
        Fightingimage.num = num;
    }
    
    public static int getNum22() {
        return Fightingimage.num22;
    }
    
    public void setNum22(int num22) {
        Fightingimage.num22 = num22;
    }
    
    static {
        Fightingimage.moved = new ArrayList<>();
        Fightingimage.num = 0;
        Fightingimage.num22 = 0;
        (Fightingimage.xuelans = new ImageIcon[3])[0] = CutButtonImage.getImage("img/xy2uiimg/75_png.xy2uiimg.sempty.png", -1, -1);
        Fightingimage.xuelans[1] = CutButtonImage.getImage("img/xy2uiimg/6_png.xy2uiimg.hps.png", -1, -1);
        Fightingimage.xuelans[2] = CutButtonImage.getImage("img/xy2uiimg/2_png.xy2uiimg.mps.png", -1, -1);
    }
}
