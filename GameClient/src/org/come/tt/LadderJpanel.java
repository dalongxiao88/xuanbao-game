package org.come.tt;

import java.util.Iterator;
import com.tool.tcp.NewPart;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import com.tool.tcp.PartTwo;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.SpriteFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.btn.FormsOnOffBtn;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.bean.LoginResult;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LadderJpanel extends JPanel
{
    private ImageIcon beijing;
    private JLabel headImg;
    private LadderPanelBtn startTheGame;
    private LadderPanelBtn ladderPoints;
    private LadderPanelBtn ladderLottery;
    private LadderPanelBtn award1;
    private LadderPanelBtn award2;
    private LadderPanelBtn award3;
    private String seasonInfo;
    private String currSeason;
    private List<LoginResult> loginResultList;
    
    public String getSeasonInfo() {
        return this.seasonInfo;
    }
    
    public void setSeasonInfo(String seasonInfo) {
        this.seasonInfo = seasonInfo;
    }
    
    public String getCurrSeason() {
        return this.currSeason;
    }
    
    public void setCurrSeason(String currSeason) {
        this.currSeason = currSeason;
    }
    
    public List<LoginResult> getLoginResultList() {
        return this.loginResultList;
    }
    
    public void setLoginResultList(List<LoginResult> loginResultList) {
        this.loginResultList = loginResultList;
    }
    
    public LadderJpanel() {
        this.seasonInfo = "";
        this.currSeason = "";
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        this.setPreferredSize(new Dimension(682, 475));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.beijing = new ImageIcon("inkImg/background/S284.png");
        (this.headImg = new JLabel()).setBounds(30, 50, 75, 100);
        if (nao.equals("新")) {
            ImageIcon Img = CutButtonImage.getImage("img/head/" + RoleData.getRoleData().getLoginResult().getSpecies_id() + "-1.png", 75, 100);
            this.headImg.setIcon(Img);
            this.add(this.headImg);
        }
        else {
            ImageIcon Img = CutButtonImage.getImage("img/head/" + RoleData.getRoleData().getLoginResult().getSpecies_id() + ".png", 75, 100);
            this.headImg.setIcon(Img);
            this.add(this.headImg);
        }
        (this.startTheGame = new LadderPanelBtn("inkImg/button/ttgo.png", 1, 1)).setBounds(550, 61, 100, 19);
        this.add(this.startTheGame);
        (this.ladderPoints = new LadderPanelBtn("inkImg/button/ttjf.png", 1, 2)).setBounds(550, 91, 100, 19);
        this.add(this.ladderPoints);
        (this.ladderLottery = new LadderPanelBtn("inkImg/button/ttjc.png", 1, 3)).setBounds(550, 121, 100, 19);
        this.add(this.ladderLottery);
        (this.award1 = new LadderPanelBtn("inkImg/button/ttcc1.png", 1, 4)).setBounds(450, 60, 100, 25);
        this.add(this.award1);
        (this.award2 = new LadderPanelBtn("inkImg/button/ttcc10.png", 1, 5)).setBounds(450, 90, 100, 25);
        this.add(this.award2);
        (this.award3 = new LadderPanelBtn("inkImg/button/ttcc20.png", 1, 6)).setBounds(450, 120, 100, 25);
        this.add(this.award3);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 604);
        offBtn.setBounds(647, 10, 25, 25);
        this.add(offBtn);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        super.paintComponent(g);
        g.drawImage(this.beijing.getImage(), 0, 0, 674, 449, null);
        LoginResult login = RoleData.getRoleData().getLoginResult();
        BigDecimal ttRecord = login.getScoretype("天梯积分");
        String userName = login.getRolename();
        g.setFont(UIUtils.TEXT_BOLD_FONT);
        if (this.loginResultList != null) {
            int x = this.loginResultList.size();
            if (x > 5) {
                x = 5;
            }
            g.setFont(UIUtils.TEXT_FONT);
            for (int i = 0; i < x; ++i) {
                LoginResult loginResult = (LoginResult)this.loginResultList.get(i);
                g.setColor(Color.black);
                String skin = loginResult.getSpecies_id().toString();
//                if (!nao.equals("新")) {
                    NewPart partOne = SpriteFactory.createPart(skin, 2, 1, null);
                    partOne.draw(g, 100 + i * 120, 270, 4, ImageMixDeal.userimg.getTime());
//                }
//                else {
//                    PartTwo partTwo = (PartTwo)SpriteFactory.createPart(skin, 2, 1, null);
//                    partTwo.draw(g, 100 + i * 120, 270, 4, ImageMixDeal.userimg.getTime());
//                }
                Ladder ladder = this.getLadder(loginResult.getGrade());
                g.setColor(ladder.getColor());
                g.drawString(loginResult.getRolename(), 95 + i * 115, 325);
                g.drawString(ladder.getLevel(), 95 + i * 115, 342);
                g.setColor(Color.black);
                g.drawString("胜" + loginResult.getTtVictory() + "/败" + loginResult.getTtFail(), 95 + i * 115, 380);
                g.drawString(loginResult.getScoretype("天梯积分").toString(), 95 + i * 115, 360);
            }
        }
        Boolean b = Boolean.valueOf(false);
        Integer index = Integer.valueOf(0);
        if (this.loginResultList != null) {
            for (LoginResult loginResult2 : this.loginResultList) {
                index = Integer.valueOf((int)index + 1);
                if (loginResult2.getRole_id().compareTo(login.getRole_id()) == 0) {
                    b = Boolean.valueOf(true);
                    break;
                }
            }
        }
        Ladder ladder2 = this.getLadder(login.getGrade());
        g.setColor(Color.black);
        g.drawString(userName, 150, 75);
        g.drawString(ladder2.getLevel(), 150, 105);
        g.drawString(ttRecord.intValue() + "", 150, 135);
        g.drawString("胜" + login.getTtVictory() + "/败" + login.getTtFail(), 350, 75);
        g.drawString(((boolean)b) ? ("第 " + index + " 名") : "暂未上榜", 350, 105);
        g.drawString("暂无排名", 350, 136);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(UIUtils.TEXT_HYK20);
        if (this.currSeason != null) {
            g2d.drawString(this.currSeason, 50, 40);
            g2d.setFont(UIUtils.TEXT_NAME_FONT);
            g2d.setColor(Color.red);
            g2d.drawString(this.seasonInfo, 50, 170);
        }
    }
    
    private Ladder getLadder(Integer grade) {
        Ladder ladder = new Ladder();
        if ((int)grade <= 102) {
            ladder.setColor(new Color(0, 255, 0));
            ladder.setLevel("0转" + grade + "级");
        }
        else if ((int)grade > 102 && (int)grade <= 210) {
            ladder.setColor(new Color(255, 140, 0));
            ladder.setLevel("1转" + ((int)grade - 102 + 14) + "级");
        }
        else if ((int)grade > 210 && (int)grade <= 338) {
            ladder.setColor(new Color(0, 245, 255));
            ladder.setLevel("2转" + ((int)grade - 210 + 14) + "级");
        }
        else if ((int)grade > 338 && (int)grade <= 459) {
            ladder.setColor(new Color(238, 44, 44));
            ladder.setLevel("3转" + ((int)grade - 338 + 59) + "级");
        }
        else if ((int)grade > 459) {
            ladder.setColor(new Color(155, 48, 255));
            ladder.setLevel("飞升" + ((int)grade - 459 + 139) + "级");
        }
        return ladder;
    }
}
