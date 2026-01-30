
package org.come.Jpanel;

import com.tool.btn.PetOperationPanelBtn;
import com.tool.tcpimg.RichLabel;
import com.updateNew.MyIsif;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

public class AlchemyMainJpanel extends JPanel {
    private CardLayout car;
    private PetOperationPanelBtn ly,lf;
    private AlchemyJpanel alchemyJpanel;
    private SpiritualJpanel spiritualJpanel;
    private RichLabel namen;
    public AlchemyMainJpanel() throws Exception {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));


        car = new CardLayout();
        if(MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(559, 471));
        }else {
            this.setPreferredSize(new Dimension(485, 485));
        }
        this.setLayout(car);
        this.setOpaque(false);
        alchemyJpanel = new AlchemyJpanel();
        this.add(alchemyJpanel, "l1");
        spiritualJpanel = new SpiritualJpanel();
        this.add(spiritualJpanel, "l2");
//        car.show(this,"l1");


//    	if(MyIsif.getStyle().equals("水墨")) {
//
//            ly = new PetOperationPanelBtn("inkImg/button1/sm-ly-1.png", 1, 100, this);
//            ly.setBounds(46, 14, 75, 33);
//            this.add(ly);
//            lf = new PetOperationPanelBtn("inkImg/button1/sm-lf-2.png", 1, 101, this);
//            lf.setBounds(46+78, 18, 75, 33);
//            this.add(lf);
//
//    	}else {
//
//            ly = new PetOperationPanelBtn("inkImg/button1/hm-ly-1.png", 1, 100, this);
//            ly.setBounds(46, 14, 75, 33);
//            this.add(ly);
//            lf = new PetOperationPanelBtn("inkImg/button1/hm-lf-2.png", 1, 101, this);
//            lf.setBounds(46+78, 14, 75, 33);
//            this.add(lf);
//
//    	}
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
                getCar().show(this, "l1");
            } else {
                try {
                    ly.setIcons(CutButtonImage.cuts("inkImg/button1/sm-ly-2.png"));
                    lf.setIcons(CutButtonImage.cuts("inkImg/button1/sm-lf-1.png"));
                    ly.setBounds(46, 18, 75, 33);
                    lf.setBounds(46+78, 14, 75, 33);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getCar().show(this, "l2");
            }
        }else {
            if (p == 100) {
                try {
                    this.ly.setIcons(CutButtonImage.cuts("inkImg/button1/hm-ly-1.png"));
                    this.lf.setIcons(CutButtonImage.cuts("inkImg/button1/hm-lf-2.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                getCar().show(this, "l1");
            }
            else {
                try {
                    this.ly.setIcons(CutButtonImage.cuts("inkImg/button/hm-ly-2.png"));
                    this.lf.setIcons(CutButtonImage.cuts("inkImg/button/hm-lf-1.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                getCar().show(this, "l2");
            }
        }
    }

    public CardLayout getCar() {
        return car;
    }

    public void setCar(CardLayout car) {
        this.car = car;
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

    public AlchemyJpanel getAlchemyJpanel() {
        return alchemyJpanel;
    }

    public void setAlchemyJpanel(AlchemyJpanel alchemyJpanel) {
        this.alchemyJpanel = alchemyJpanel;
    }

    public SpiritualJpanel getSpiritualJpanel() {
        return spiritualJpanel;
    }

    public void setSpiritualJpanel(SpiritualJpanel spiritualJpanel) {
        this.spiritualJpanel = spiritualJpanel;
    }

    public RichLabel getNamen() {
        return namen;
    }

    public void setNamen(RichLabel namen) {
        this.namen = namen;
    }

}
