package org.come.Jpanel;

import java.awt.Color;
import java.awt.image.ImageObserver;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.tool.ModerateTask.TaskRoleData;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import com.tool.tcpimg.UIUtils;
import java.awt.LayoutManager;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.model.Achieve;
import com.tool.btn.PartnerArenaBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartnerArenaExchangeModelPanel extends JPanel
{
    private JLabel labImg;
    private JLabel labName;
    private PartnerArenaBtn btnGet;
    private Achieve achieve;
    private ImageIcon icon;
    private ImageIcon expImg;
    private int expNum;
    
    public PartnerArenaExchangeModelPanel() {
        this.expNum = 1;
        this.setPreferredSize(new Dimension(203, 68));
        this.setLayout((LayoutManager)null);
        this.setOpaque(false);
        this.getLabImg();
        this.getLabName();
        this.getBtnGet();
    }
    
    public void showView(Achieve achieve, int winNum) {
        this.achieve = achieve;
        if (achieve != null) {
            this.labName.setForeground(UIUtils.getColor(achieve.getColor()));
            this.labName.setText(achieve.getName());
            this.labImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(achieve.getSkin(), 55, 53));
            this.refreshView(winNum);
        }
    }
    
    public void refreshView(int winNum) {
        if (this.achieve != null) {
            BigDecimal paln = new BigDecimal(winNum).divide(new BigDecimal(this.achieve.getNum()), 2, 1);
            paln = paln.multiply(new BigDecimal(115)).setScale(0, 1);
            this.expNum = ((paln.intValue() > 115) ? 115 : paln.intValue());
            int sumReceive = TaskRoleData.SumReceive(4, 3);
            int num = sumReceive >> this.achieve.getId() & 0x1;
            if (num == 1) {
                this.btnGet.setIcon(CutButtonImage.getImage("inkImg/button/36.png", 34, 17));
                this.btnGet.setBtn(-1);
                this.btnGet.setText("已领");
            }
            else if (winNum > this.achieve.getNum()) {
                try {
                    this.btnGet.setIcons(CutButtonImage.cuts("inkImg/button/2.png"));
                }
                catch (Exception var6) {
                    var6.printStackTrace();
                }
                this.btnGet.setBtn(1);
                this.btnGet.setText("领取");
            }
            else {
                this.btnGet.setIcon(CutButtonImage.getImage("inkImg/button/36.png", 34, 17));
                this.btnGet.setBtn(-1);
                this.btnGet.setText("领取");
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S182.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 203, 68, null);
            if (this.expImg == null) {
                this.expImg = CutButtonImage.getImage("inkImg/background/S28.png", -1, -1);
            }
            g.drawImage(this.expImg.getImage(), 74, 44, this.expNum, 12, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S182.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 203, 68, (ImageObserver)null);
            if (this.expImg == null) {
                this.expImg = CutButtonImage.getImage("inkImg/background/S28.png", -1, -1);
            }
            g.drawImage(this.expImg.getImage(), 74, 44, this.expNum, 12, this);
        }
    }
    
    public JLabel getLabImg() {
        if (this.labImg == null) {
            (this.labImg = new JLabel()).setBounds(8, 7, 55, 53);
            this.add(this.labImg);
        }
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            (this.labName = new JLabel("初来乍到")).setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT);
            this.labName.setBounds(74, 10, 80, 20);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public PartnerArenaBtn getBtnGet() {
        if (this.btnGet == null) {
            (this.btnGet = new PartnerArenaBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, "领取", UIUtils.TEXT_FONT, 4, this)).setBounds(160, 14, 34, 17);
            this.add(this.btnGet);
        }
        return this.btnGet;
    }
    
    public void setBtnGet(PartnerArenaBtn btnGet) {
        this.btnGet = btnGet;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public Achieve getAchieve() {
        return this.achieve;
    }
    
    public void setAchieve(Achieve achieve) {
        this.achieve = achieve;
    }
    
    public ImageIcon getExpImg() {
        return this.expImg;
    }
    
    public void setExpImg(ImageIcon expImg) {
        this.expImg = expImg;
    }
    
    public int getExpNum() {
        return this.expNum;
    }
    
    public void setExpNum(int expNum) {
        this.expNum = expNum;
    }
}
