package org.come.annex.Tournaments.Jpanel;

import com.tool.tcpimg.UIUtils;
import org.come.annex.Tournaments.Btn.TournamentsBtn;
import org.come.annex.Tournaments.Other.TournamentsData;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TournamentsScreen1Jpanel extends JPanel {
   private TournamentsBtn enrollBtn;
   private JLabel title1;
   private JLabel title2;
   private TournamentsCardJpanel tournamentsCardJpanel;
   public TournamentsData Data = new TournamentsData();
   private ImageIcon icon = new ImageIcon("inkImg/danxin/p/b7.png");
   private ImageIcon icon1 = new ImageIcon("inkImg/danxin/p/b6.png");

   public TournamentsScreen1Jpanel(TournamentsCardJpanel tournamentsCardJpanel) {
      this.tournamentsCardJpanel = tournamentsCardJpanel;
      this.setPreferredSize(new Dimension(639, 444));
      this.setOpaque(false);
      this.setLayout(null);
      this.title1 = new JLabel("赛事分组选择将于比赛服组队确认时完成");
      this.title1.setFont(UIUtils.TEXT_FONT);
      this.title1.setBounds(429, 425, 255, 20);
      this.title1.setHorizontalAlignment(2);
      this.title1.setForeground(new Color(205, 192, 157, 255));
      this.title1.setOpaque(false);
      this.add(this.title1);
      this.title2 = new JLabel("当前状态");
      this.title2.setFont(UIUtils.TEXT_FONT);
      this.title2.setBounds(444, 405, 180, 20);
      this.title2.setHorizontalAlignment(2);
      this.title2.setForeground(new Color(205, 192, 157, 255));
      this.title2.setOpaque(false);
      this.add(this.title2);
      this.enrollBtn = new TournamentsBtn("inkImg/danxin/p/186.png", 1, UIUtils.COLOR_BTNPUTONG3, "我要报名", UIUtils.TEXT_HY88, 10, this);
      this.enrollBtn.setBounds(555, 400, 94, 28);
      this.add(this.enrollBtn);
      this.updateStatus();
   }

   private void updateStatus() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日HH:mm");
      LocalDateTime startDateTime = LocalDateTime.parse(this.Data.StartTimeEnroll, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
      LocalDateTime endDateTime = LocalDateTime.parse(this.Data.EndTimeEnroll, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
      LocalDateTime now = LocalDateTime.now();
      if (now.isBefore(startDateTime)) {
         this.title2.setText(startDateTime.format(formatter) + " 开始");
         this.enrollBtn.setEnabled(false);
      } else if (now.isAfter(startDateTime) && now.isBefore(endDateTime)) {
         this.title2.setText(endDateTime.format(formatter) + " 前");
         this.enrollBtn.setEnabled(true);
      } else {
         this.title2.setText("报名结束");
         this.enrollBtn.setEnabled(false);
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(this.icon.getImage(), 15, 13, 640, 380, this);
      g.drawImage(this.icon1.getImage(), 15, 366, 640, 92, this);
   }
}
