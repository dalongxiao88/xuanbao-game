package org.come.Jpanel;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import org.come.until.Util;
import java.text.ParseException;
import org.come.bean.OneArenaNotes;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.CutButtonImage;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import com.tool.tcpimg.UIUtils;
import org.come.until.AnalysisString;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.text.SimpleDateFormat;
import org.come.bean.OneArenaRole;
import com.tool.btn.PartnerArenaBtn;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class PartnerArenaModelPanel extends JPanel implements MouseListener
{
    private JLabel labImg;
    private JLabel labNum;
    private JLabel labName;
    private JLabel labLvl;
    private JLabel labUpDown;
    private JLabel labUpDownNum;
    private JLabel labTime;
    private PartnerArenaBtn btnChallenge;
    private OneArenaRole oneArenaRole;
    private int type;
    SimpleDateFormat formatter;
    private ImageIcon icon;
    private ImageIcon iconBack;
    
    public PartnerArenaModelPanel() {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setPreferredSize(new Dimension(220, 49));
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(Color.gray);
        this.type = 0;
        this.getLabImg();
        this.getLabLvl();
        this.getLabName();
        this.getLabNum();
        this.getBtnChallenge();
        this.addMouseListener(this);
    }
    
    public PartnerArenaModelPanel(int type) {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setPreferredSize(new Dimension(343, 41));
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(Color.gray);
        this.type = type;
        this.getLabImgType();
        this.getLabLvlType();
        this.getLabNameType();
        this.getLabNumType();
        this.getLabUpDown();
        this.getLabUpDownNum();
        this.getLabTime();
        this.addMouseListener(this);
    }
    
    public void showView(OneArenaRole oneArenaRole, int type) {
        this.oneArenaRole = oneArenaRole;
        int lvl = oneArenaRole.getLvl();
        this.labLvl.setText(AnalysisString.lvl(lvl) + "级");
        int lvltrue = AnalysisString.lvltrue(lvl);
        this.labLvl.setForeground(UIUtils.getcolor(lvltrue));
        this.labName.setText(oneArenaRole.getName());
        int place = oneArenaRole.getPlace();
        this.labNum.setText(String.valueOf(place));
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            this.labImg.setIcon(CutButtonImage.getImage("img/head/" + oneArenaRole.getSkin() + "-1.png", 39, 39));
        }
        else {
            this.labImg.setIcon(CutButtonImage.getImage("img/head/" + oneArenaRole.getSkin() + ".png", 39, 39));
        }
        if (type != 0) {
            if (type == -1 && place <= 5) {
                this.btnChallenge.setVisible(false);
            }
            else {
                this.btnChallenge.setVisible(true);
            }
        }
    }
    
    public void showWarView(OneArenaNotes arenaNotes, BigDecimal roleId) {
        if (arenaNotes.getRole1().compareTo(roleId) == 0) {
            this.refreshView(arenaNotes.getLvl2(), arenaNotes.getName2(), (arenaNotes.getIsV() == 0) ? 1 : 2, arenaNotes.getPlace(), arenaNotes.getTime(), arenaNotes.getSkin2());
        }
        else if (arenaNotes.getRole2().compareTo(roleId) == 0) {
            this.refreshView(arenaNotes.getLvl1(), arenaNotes.getName1(), (arenaNotes.getIsV() == 0) ? 2 : 1, arenaNotes.getPlace(), arenaNotes.getTime(), arenaNotes.getSkin1());
        }
    }
    
    public void refreshView(int lvl, String name, int nextInt, int place, String time, String skin) {
        this.labLvl.setText(AnalysisString.lvl(lvl) + "级");
        int lvltrue = AnalysisString.lvltrue(lvl);
        this.labLvl.setForeground(UIUtils.getcolor(lvltrue));
        this.labName.setText(name);
        if (nextInt == 1) {
            this.labNum.setForeground(Color.green);
            this.labNum.setText("胜");
            this.labUpDown.setIcon(CutButtonImage.getImage("inkImg/background/S183.png", -1, -1));
            this.labUpDownNum.setText("+" + place);
            this.labUpDownNum.setForeground(Color.green);
        }
        else {
            this.labNum.setForeground(Color.red);
            this.labNum.setText("负");
            this.labUpDown.setIcon(CutButtonImage.getImage("inkImg/background/S184.png", -1, -1));
            this.labUpDownNum.setText("-" + place);
            this.labUpDownNum.setForeground(Color.red);
        }
        String nao = "新";
        if (nao.equals("新")) {
            this.labTime.setText(this.getTimes(time));
            this.labImg.setIcon(CutButtonImage.getImage("img/head/" + skin + "-1.png", 39, 39));
        }
        else {
            this.labTime.setText(this.getTimes(time));
            this.labImg.setIcon(CutButtonImage.getImage("img/head/" + skin + ".png", 39, 39));
        }
    }
    
    public String getTimes(String str) {
        String resultTimes = "";
        long date = 0L;
        try {
            date = this.formatter.parse(str).getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        long timeNow = Util.getTime();
        long times = timeNow - date;
        long day = times / 86400000L;
        long hour = times / 3600000L - day * 24L;
        long min = times / 60000L - day * 24L * 60L - hour * 60L;
        StringBuffer sb = new StringBuffer();
        if (day > 0L) {
            sb.append(day);
            sb.append("天前");
        }
        else if (hour > 0L) {
            sb.append(hour);
            sb.append("小时前");
        }
        else if (min > 0L) {
            sb.append(min);
            sb.append("分钟前");
        }
        else {
            sb.append("刚刚");
        }
        resultTimes = sb.toString();
        return resultTimes;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("inkImg/background/10.png", -1, -1);
        }
        if (this.type == 0) {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background/S108.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 37, 5, 39, 39, null);
            g.drawImage(this.iconBack.getImage(), 15, 48, 190, 1, null);
        }
        else if (this.type == 1) {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background/S177.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 90, 1, 39, 39, null);
            g.drawImage(this.iconBack.getImage(), 0, 40, 345, 1, null);
        }
    }
    
    public JLabel getLabImg() {
        if (this.labImg == null) {
            (this.labImg = new JLabel()).setBounds(38, 6, 37, 37);
            this.labImg.setOpaque(false);
            this.add(this.labImg);
        }
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    public JLabel getLabNum() {
        if (this.labNum == null) {
            (this.labNum = new JLabel("", 0)).setForeground(Color.yellow);
            this.labNum.setFont(UIUtils.TEXT_FONT);
            this.labNum.setBounds(0, 0, 37, 49);
            this.labNum.setOpaque(false);
            this.add(this.labNum);
        }
        return this.labNum;
    }
    
    public void setLabNum(JLabel labNum) {
        this.labNum = labNum;
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            (this.labName = new JLabel()).setBounds(80, 4, 100, 20);
            this.labName.setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            this.labName.setOpaque(false);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel()).setBounds(80, 28, 70, 20);
            this.labLvl.setOpaque(false);
            this.labLvl.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public PartnerArenaBtn getBtnChallenge() {
        if (this.btnChallenge == null) {
            (this.btnChallenge = new PartnerArenaBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "挑战", UIUtils.TEXT_FONT, 3, this)).setBounds(175, 20, 34, 17);
            this.add(this.btnChallenge);
        }
        return this.btnChallenge;
    }
    
    public void setBtnChallenge(PartnerArenaBtn btnChallenge) {
        this.btnChallenge = btnChallenge;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setOpaque(true);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.setOpaque(false);
    }
    
    public OneArenaRole getOneArenaRole() {
        return this.oneArenaRole;
    }
    
    public void setOneArenaRole(OneArenaRole oneArenaRole) {
        this.oneArenaRole = oneArenaRole;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel getLabLvlType() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel()).setBounds(130, 21, 70, 20);
            this.labLvl.setOpaque(false);
            this.labLvl.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public JLabel getLabNameType() {
        if (this.labName == null) {
            (this.labName = new JLabel()).setBounds(130, 1, 120, 20);
            this.labName.setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            this.labName.setOpaque(false);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public JLabel getLabImgType() {
        if (this.labImg == null) {
            (this.labImg = new JLabel()).setBounds(90, 1, 37, 37);
            this.labImg.setOpaque(false);
            this.add(this.labImg);
        }
        return this.labImg;
    }
    
    public JLabel getLabNumType() {
        if (this.labNum == null) {
            (this.labNum = new JLabel("", 0)).setFont(UIUtils.TEXT_RESULT);
            this.labNum.setBounds(0, 0, 49, 49);
            this.labNum.setOpaque(false);
            this.add(this.labNum);
        }
        return this.labNum;
    }
    
    public JLabel getLabUpDown() {
        if (this.labUpDown == null) {
            (this.labUpDown = new JLabel()).setBounds(60, 1, 19, 24);
            this.add(this.labUpDown);
        }
        return this.labUpDown;
    }
    
    public void setLabUpDown(JLabel labUpDown) {
        this.labUpDown = labUpDown;
    }
    
    public JLabel getLabUpDownNum() {
        if (this.labUpDownNum == null) {
            (this.labUpDownNum = new JLabel("", 0)).setFont(UIUtils.TEXT_FONT);
            this.labUpDownNum.setBounds(50, 25, 40, 16);
            this.add(this.labUpDownNum);
        }
        return this.labUpDownNum;
    }
    
    public void setLabUpDownNum(JLabel labUpDownNum) {
        this.labUpDownNum = labUpDownNum;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public JLabel getLabTime() {
        if (this.labTime == null) {
            (this.labTime = new JLabel()).setBounds(250, 10, 90, 20);
            this.labTime.setForeground(Color.white);
            this.labTime.setFont(UIUtils.TEXT_FONT);
            this.add(this.labTime);
        }
        return this.labTime;
    }
    
    public void setLabTime(JLabel labTime) {
        this.labTime = labTime;
    }
}
