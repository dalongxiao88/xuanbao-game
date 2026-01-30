
package org.come.Jpanel;

import com.tool.btn.PetOperationPanelBtn;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.come.Frame.AlchemyJframe;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.CutButtonImage;

import javax.swing.*;
import java.awt.*;

public class AlchemyCardJpanel extends JPanel {
    private PetOperationPanelBtn ly,lf;
    private AlchemyMainJpanel alchemyMainJpanel;
    public AlchemyCardJpanel() throws Exception {
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        if(MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(700, 700));
        }else {
            this.setPreferredSize(new Dimension(700, 700));
        }
        if(MyIsif.getStyle().equals("水墨")) {
            ly = new PetOperationPanelBtn("inkImg/button1/sm-ly-1.png", 1, 100, this);
            ly.setBounds(46, 14, 75, 33);
            this.add(ly);
            lf = new PetOperationPanelBtn("inkImg/button1/sm-lf-2.png", 1, 101, this);
            lf.setBounds(46+78, 18, 75, 33);
            this.add(lf);
        }else {
            ly = new PetOperationPanelBtn("inkImg/button/hm-ly.png", 1, 100, this);
            ly.setBounds(46, 14, 75, 33);
            this.add(ly);
            lf = new PetOperationPanelBtn("inkImg/button/hm-lf-2.png", 1, 101, this);
            lf.setBounds(46+78, 14, 75, 33);
            this.add(lf);
            ly.setBounds(35, 30, 75, 33);
            lf.setBounds(35+65, 32, 75, 33);
        }
        alchemyMainJpanel = new AlchemyMainJpanel();
        this.alchemyMainJpanel.setBounds(0, 0, 700, 700);
        this.add(alchemyMainJpanel);
    }


    /** 切换 */
    public void qh(final int p) {
        if(MyIsif.getStyle().equals("水墨")) {
            if (p == 100) {
                try {
                    ly.setIcons(CutButtonImage.cuts("inkImg/button1/sm-ly-1.png"));
                    lf.setIcons(CutButtonImage.cuts("inkImg/button1/sm-lf-2.png"));
                    ly.setBounds(46, 14, 75, 33);
                    lf.setBounds(46+78, 18, 75, 33);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getCar().show(AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel(), "l1");
            } else {
                try {
                    ly.setIcons(CutButtonImage.cuts("inkImg/button1/sm-ly-2.png"));
                    lf.setIcons(CutButtonImage.cuts("inkImg/button1/sm-lf-1.png"));
                    ly.setBounds(46, 18, 75, 33);
                    lf.setBounds(46+78, 14, 75, 33);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getCar().show(AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel(), "l2");
                GoodsMouslisten.lianyao();
            }
        }else {
            if (p == 100) {
                try {
                    this.ly.setIcons(CutButtonImage.cuts("inkImg/button/hm-ly.png"));
                    this.lf.setIcons(CutButtonImage.cuts("inkImg/button/hm-lf-2.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                ly.setBounds(35, 30, 75, 33);
                lf.setBounds(35+65, 32, 75, 33);
                AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getCar().show(AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel(), "l1");
            }
            else {
                try {
                    this.ly.setIcons(CutButtonImage.cuts("inkImg/button/hm-ly-2.png"));
                    this.lf.setIcons(CutButtonImage.cuts("inkImg/button/hm-lf-1.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                ly.setBounds(35, 32, 75, 33);
                lf.setBounds(35+65, 30, 75, 33);
                AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getCar().show(AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel(), "l2");
                GoodsMouslisten.lianyao();
            }
        }
    }



    public PetOperationPanelBtn getLy() {
        return ly;
    }

    public void setLy(PetOperationPanelBtn ly) {
        this.ly = ly;
    }

    public PetOperationPanelBtn getLf() {
        return lf;
    }

    public void setLf(PetOperationPanelBtn lf) {
        this.lf = lf;
    }


    public AlchemyMainJpanel getAlchemyMainJpanel() {
        return alchemyMainJpanel;
    }

    public void setAlchemyMainJpanel(AlchemyMainJpanel alchemyMainJpanel) {
        this.alchemyMainJpanel = alchemyMainJpanel;
    }
}
