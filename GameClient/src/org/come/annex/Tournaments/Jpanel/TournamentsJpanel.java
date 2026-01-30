package org.come.annex.Tournaments.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import org.come.annex.Tournaments.Btn.TournamentsNavBtn;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

public class TournamentsJpanel extends JPanel {
   private TournamentsNavBtn btnTournamentsScreen1;
   private TournamentsNavBtn btnTournamentsScreen2;
   private TournamentsNavBtn btnTournamentsScreen3;
   private TournamentsNavBtn btnTournamentsScreen4;
   private JLabel titleImage;
   private JLabel titleXS;
   private TournamentsCardJpanel tournamentsCardJpanel;
   ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
   Map<BigDecimal, Configure> item = this.allConfigure.getAllConfigure();
   Configure configure200 = this.item.get(new BigDecimal(200));
   String title = this.configure200.getJumpurl();
   private ImageIcon icon = new ImageIcon("inkImg/danxin/p/S210.png");

   public TournamentsJpanel() {
      this.setPreferredSize(new Dimension(709, 474));
      this.setOpaque(false);
      this.setLayout(null);
      FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/danxin/p/e1.png", 1, 1146);
      offBtn.setBounds(634, 15, 18, 18);
      this.add(offBtn);
      this.titleXS = new JLabel(this.title);
      this.titleXS.setFont(UIUtils.HYI_WBJ19S);
      this.titleXS.setBounds(214, 2, 241, 25);
      this.titleXS.setHorizontalAlignment(0);
      this.titleXS.setForeground(new Color(249, 231, 191, 250));
      this.titleXS.setOpaque(false);
      this.add(this.titleXS);
      this.titleImage = new JLabel();
      this.titleImage.setIcon(new ImageIcon("inkImg/danxin/p/f1X.png"));
      this.titleImage.setBounds(214, -2, 241, 43);
      this.titleImage.setOpaque(false);
      this.add(this.titleImage);
      this.getBtnTournamentsScreen1();
      this.getBtnTournamentsScreen2();
      this.getBtnTournamentsScreen3();
      this.getBtnTournamentsScreen4();
      this.tournamentsCardJpanel = new TournamentsCardJpanel();
      this.tournamentsCardJpanel.setBounds(0, 0, 709, 474);
      this.add(this.tournamentsCardJpanel);
   }

   public TournamentsNavBtn getBtnTournamentsScreen1() {
      if (this.btnTournamentsScreen1 == null) {
         this.btnTournamentsScreen1 = new TournamentsNavBtn("inkImg/danxin/p/e4.png", 1, 10, this, "赛事报名", 1);
         this.btnTournamentsScreen1.setBounds(660, 15, 45, 103);
         this.btnTournamentsScreen1.setColors(UIUtils.COLOR_NAVIGATIONS);
         this.add(this.btnTournamentsScreen1);
      }

      return this.btnTournamentsScreen1;
   }

   public void setBtnTournamentsScreen1(TournamentsNavBtn btnTournamentsScreen1) {
      this.btnTournamentsScreen1 = btnTournamentsScreen1;
   }

   public TournamentsNavBtn getBtnTournamentsScreen2() {
      if (this.btnTournamentsScreen2 == null) {
         this.btnTournamentsScreen2 = new TournamentsNavBtn("inkImg/danxin/p/e3.png", 1, 11, this, "比赛赛程", 1);
         this.btnTournamentsScreen2.setBounds(660, 122, 40, 103);
         this.btnTournamentsScreen2.setColors(UIUtils.COLOR_NAVIGATIONSW);
         this.add(this.btnTournamentsScreen2);
      }

      return this.btnTournamentsScreen2;
   }

   public void setBtnTournamentsScreen2(TournamentsNavBtn btnTournamentsScreen2) {
      this.btnTournamentsScreen2 = btnTournamentsScreen2;
   }

   public TournamentsNavBtn getBtnTournamentsScreen3() {
      if (this.btnTournamentsScreen3 == null) {
         this.btnTournamentsScreen3 = new TournamentsNavBtn("inkImg/danxin/p/e3.png", 1, 12, this, "赛事奖励", 1);
         this.btnTournamentsScreen3.setBounds(660, 229, 40, 103);
         this.btnTournamentsScreen3.setColors(UIUtils.COLOR_NAVIGATIONSW);
         this.add(this.btnTournamentsScreen3);
      }

      return this.btnTournamentsScreen3;
   }

   public void setBtnTournamentsScreen3(TournamentsNavBtn btnTournamentsScreen3) {
      this.btnTournamentsScreen3 = btnTournamentsScreen3;
   }

   public TournamentsNavBtn getBtnTournamentsScreen4() {
      if (this.btnTournamentsScreen4 == null) {
         this.btnTournamentsScreen4 = new TournamentsNavBtn("inkImg/danxin/p/e3.png", 1, 13, this, "参与有奖", 1);
         this.btnTournamentsScreen4.setBounds(660, 336, 40, 103);
         this.btnTournamentsScreen4.setColors(UIUtils.COLOR_NAVIGATIONSW);
         this.add(this.btnTournamentsScreen4);
      }

      return this.btnTournamentsScreen4;
   }

   public void setBtnTournamentsScreen4(TournamentsNavBtn btnTournamentsScreen4) {
      this.btnTournamentsScreen4 = btnTournamentsScreen4;
   }

   public TournamentsCardJpanel getTournamentsCardJpanel() {
      return this.tournamentsCardJpanel;
   }

   public void setTournamentsCardJpanel(TournamentsCardJpanel tournamentsCardJpanel) {
      this.tournamentsCardJpanel = tournamentsCardJpanel;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(this.icon.getImage(), 0, 0, 669, 474, this);
   }
}
