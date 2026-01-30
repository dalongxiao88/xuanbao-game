package org.come.annex.Tournaments.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.NewPart;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import org.come.action.IconButtonEffect;
import org.come.annex.Tournaments.Btn.TournamentsBtn;
import org.come.annex.Tournaments.Other.GameFigures;
import org.come.annex.Tournaments.Other.TournamentsData;
import org.come.socket.GameClient;
import org.come.until.CutButtonImage;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TournamentsResultsJpanel extends JPanel {
   public TournamentsData Data = new TournamentsData();
   public TournamentsBtn cancelBtn;
   public List<JLabel> detailsList = new ArrayList<>();
   public List<GameFigures> gameFiguresList = new ArrayList<>();
   public int current = 1;
   public JLabel[] avatar = new JLabel[10];
   public JLabel[] name = new JLabel[10];
   public JLabel labtext1;
   public String[] skin = new String[2];
   private ImageIcon icon1 = new ImageIcon("inkImg/danxin/p/S213.png");
   private ImageIcon icon1T = new ImageIcon("inkImg/danxin/p/S213F.png");
   private ImageIcon icon2 = new ImageIcon("inkImg/danxin/p/S212.png");
   private ImageIcon icon2T = new ImageIcon("inkImg/danxin/p/S212F.png");
   private ImageIcon icon3 = new ImageIcon("inkImg/danxin/p/f14.png");
   private ImageIcon icon4 = new ImageIcon("inkImg/danxin/p/f10.png");
   private ImageIcon icon5 = new ImageIcon("inkImg/danxin/p/f11.png");
   private ImageIcon icon6 = new ImageIcon("inkImg/danxin/p/f15.png");

   public TournamentsResultsJpanel() {
      this.setPreferredSize(new Dimension(800, 515));
      this.setOpaque(false);
      this.setLayout(null);
      FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/e/100.png", 1, 1148);
      offBtn.setBounds(755, 110, 25, 25);
      this.add(offBtn);
      String[] split = GameClient.BT.split("");

      for (int i = 0; i < split.length; i++) {
         this.labtext1 = new JLabel(split[i]);
         this.labtext1.setFont(UIUtils.TEXT_BT16);
         this.labtext1.setForeground(new Color(109, 98, 76, 250));
         this.labtext1.setBounds(176 + i * 16, 160, 24, 24);
         this.labtext1.setHorizontalAlignment(0);
         this.add(this.labtext1);
      }

      this.cancelBtn = new TournamentsBtn("inkImg/danxin/p/e8.png", 1, UIUtils.COLOR_WHITE4, "继续匹配", UIUtils.TEXT_HY88, 31, this);
      this.cancelBtn.setBounds(355, 430, 106, 26);
      this.add(this.cancelBtn);
   }

   public void setAcceptanceOfData(List<GameFigures> gameFigures, int index) {
      long currentTime = System.currentTimeMillis();
      this.clearPreviousContent();
      this.current = index;
      this.processData(gameFigures);
      this.revalidate();
      this.repaint();
   }

   private void processData(List<GameFigures> gameFigures) {
      this.processTeamData(gameFigures.get(0), this.Data.mode, 0);
      this.processTeamData(gameFigures.get(1), this.Data.mode, 1);
      long currentTime = System.currentTimeMillis();
      String[] Process = gameFigures.get(0).getGameProcess().split("-");
      String[] MembersId = gameFigures.get(0).getTeamMembersId().split("\\|");
      String[] members = MembersId[0].split("-");
      this.skin[1] = members[2];
      this.details(165, 300, 110, 20, members[1], new Color(141, 101, 69), UIUtils.TEXT_HYK18a, 2, this);
      String[] Success = gameFigures.get(0).getGameSuccess().split("-");
      String SuccessT = Success[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))];
      String[] Failure = gameFigures.get(0).getGameFailure().split("-");
      String failureT = Failure[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))];
      this.details(193, 328, 110, 20, "胜" + SuccessT + "/败" + failureT, new Color(141, 101, 69), UIUtils.TEXT_FONT, 2, this);
      String[] MembersId1 = gameFigures.get(1).getTeamMembersId().split("\\|");
      String[] members1 = MembersId1[0].split("-");
      this.skin[0] = members1[2];
      this.details(165, 188, 110, 20, members1[1], new Color(141, 101, 69), UIUtils.TEXT_HYK18a, 2, this);
      String[] Success1 = gameFigures.get(1).getGameSuccess().split("-");
      String SuccessT1 = Success1[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))];
      String[] Failure1 = gameFigures.get(1).getGameFailure().split("-");
      String failureT1 = Failure1[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))];
      this.details(193, 216, 110, 20, "胜" + SuccessT1 + "/败" + failureT1, new Color(141, 101, 69), UIUtils.TEXT_FONT, 2, this);
      if (Process[this.Data.getKnockedIndex(this.Data.getCurrentStage(currentTime))].equals("3")) {
         String[] Matches = gameFigures.get(0).getGameMatches().split("-");
         int number = this.Data.getStageIndex(this.Data.getCurrentStage(currentTime));
         if (number > 1) {
            this.details(
               210,
               430,
               135,
               26,
               "当前对局：" + Matches[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))] + "/" + this.Data.knockoutM,
               new Color(103, 74, 52),
               UIUtils.TEXT_FONT,
               4,
               this
            );
            this.cancelBtn.setVisible(true);
         } else if (this.Data.pointsRaceM == Integer.parseInt(Matches[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))])) {
            this.details(330, 430, 156, 26, "完成对战", new Color(103, 74, 52), UIUtils.TEXT_FONT, 0, this);
            this.cancelBtn.setVisible(false);
         } else {
            this.details(
               210,
               430,
               135,
               26,
               "当前对局：" + Matches[this.Data.getStageIndex(this.Data.getCurrentStage(currentTime))] + "/" + this.Data.pointsRaceM,
               new Color(103, 74, 52),
               UIUtils.TEXT_FONT,
               4,
               this
            );
            this.cancelBtn.setVisible(true);
         }
      } else if (Process[this.Data.getKnockedIndex(this.Data.getCurrentStage(currentTime)) + 1].equals("1")) {
         this.details(330, 430, 156, 26, "完成对战，晋级成功", new Color(103, 74, 52), UIUtils.TEXT_FONT, 0, this);
         this.cancelBtn.setVisible(false);
      } else if (Process[this.Data.getKnockedIndex(this.Data.getCurrentStage(currentTime)) + 1].equals("0")) {
         this.details(330, 430, 156, 26, "完成对战，晋级失败", new Color(103, 74, 52), UIUtils.TEXT_FONT, 0, this);
         this.cancelBtn.setVisible(false);
      }
   }

   private void processTeamData(GameFigures gameFigure, int mode, int teamIndex) {
      String[] members = gameFigure.getTeamMembersId().split("\\|");
      int count = mode == 1 ? 5 : (mode == 2 ? 3 : 1);

      for (int i = 0; i < count && i < members.length; i++) {
         String[] memberInfo = members[i].split("-");
         if (memberInfo.length == 3) {
            String memberId = memberInfo[0];
            String memberName = memberInfo[1];
            BigDecimal speciesId = new BigDecimal(memberInfo[2]);
            int index = teamIndex * count + i;
            this.avatar[index] = new JLabel();
            this.add(this.avatar[index]);
            this.updateLabroleimg(speciesId, index);
            this.updateMemberName(memberName, index, mode, teamIndex);
            this.setAvatarPosition(index, mode, teamIndex);
         }
      }
   }

   public void updateLabroleimg(BigDecimal species_id, int index) {
      if (this.avatar[index] == null) {
         this.avatar[index] = new JLabel();
         this.add(this.avatar[index]);
      }

      ImageIcon icon = CutButtonImage.getImage("Dat/head/y" + species_id + ".png", 52, 52);
      this.avatar[index].setIcon(icon);
      this.avatar[index].addMouseListener(new IconButtonEffect(this.avatar[index]));
   }

   private void setAvatarPosition(int index, int mode, int teamIndex) {
      int x = 0;
      int y = 0;
      if (mode == 1) {
         x = 294 + index % 5 * 85;
         y = teamIndex == 0 ? 304 : 164;
      } else if (mode == 2) {
         x = 379 + index % 3 * 85;
         y = teamIndex == 0 ? 304 : 164;
      } else if (mode == 3) {
         x = 464;
         y = teamIndex == 0 ? 304 : 164;
      }

      this.avatar[index].setBounds(x, y, 52, 52);
   }

   private void updateMemberName(String memberName, int index, int mode, int teamIndex) {
      this.name[index] = new JLabel(memberName);
      this.name[index].setFont(new Font("宋体", 0, 12));
      this.name[index].setHorizontalAlignment(0);
      this.name[index].setForeground(new Color(132, 125, 113, 255));
      int x = 0;
      int y = 0;
      if (mode == 1) {
         x = 290 + index % 5 * 85;
         y = teamIndex == 0 ? 364 : 224;
      } else if (mode == 2) {
         x = 375 + index % 3 * 85;
         y = teamIndex == 0 ? 364 : 224;
      } else if (mode == 3) {
         x = 460;
         y = teamIndex == 0 ? 364 : 224;
      }

      this.name[index].setBounds(x, y, 60, 15);
      this.add(this.name[index]);
   }

   private void clearPreviousContent() {
      for (JLabel label : this.detailsList) {
         this.remove(label);
      }

      this.detailsList.clear();

      for (int i = 0; i < this.avatar.length; i++) {
         if (this.avatar[i] != null) {
            this.remove(this.avatar[i]);
            this.avatar[i] = null;
         }
      }

      for (int ix = 0; ix < this.name.length; ix++) {
         if (this.name[ix] != null) {
            this.remove(this.name[ix]);
            this.name[ix] = null;
         }
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (this.current == 1) {
         g.drawImage(this.icon1.getImage(), 0, 91, 800, 424, this);
         g.drawImage(this.icon1T.getImage(), 0, 0, 752, 176, this);
      } else if (this.current == 2) {
         g.drawImage(this.icon2.getImage(), 0, 91, 800, 424, this);
         g.drawImage(this.icon2T.getImage(), 0, 0, 752, 176, this);
      }

      g.drawImage(this.icon3.getImage(), 90, 250, 620, 20, this);
      g.drawImage(this.icon5.getImage(), 165, 160, 95, 24, this);
      g.drawImage(this.icon6.getImage(), 165, 216, 23, 20, this);
      g.drawImage(this.icon6.getImage(), 165, 328, 23, 20, this);
      if (this.Data.mode == 3) {
         for (int i = 0; i < 5; i++) {
            g.drawImage(this.icon4.getImage(), 290 + i * 85, 160, 60, 60, this);
            g.drawImage(this.icon4.getImage(), 290 + i * 85, 300, 60, 60, this);
         }
      } else if (this.Data.mode == 2) {
         for (int i = 0; i < 3; i++) {
            g.drawImage(this.icon4.getImage(), 375 + i * 85, 160, 60, 60, this);
            g.drawImage(this.icon4.getImage(), 375 + i * 85, 300, 60, 60, this);
         }
      } else if (this.Data.mode == 4) {
         g.drawImage(this.icon4.getImage(), 290, 160, 60, 60, this);
         g.drawImage(this.icon4.getImage(), 290, 300, 60, 60, this);
      }

      for (int i = 0; i < 2; i++) {
         if (this.skin[i] != null && !this.skin[i].isEmpty()) {
            NewPart part = SpriteFactory.createPart(this.skin[i], 2, 1, null);
            if (part != null) {
               part.draw(g, 100, 240 + i * 150, 4, ImageMixDeal.userimg.getTime());
            }
         }
      }
   }

   public void details(int x, int y, int w, int h, String text, Color color, Font font, int align, TournamentsResultsJpanel panel) {
      JLabel detail = new JLabel();
      detail.setForeground(color);
      detail.setBounds(x, y, w, h);
      detail.setFont(font);
      detail.setText(text);
      detail.setHorizontalAlignment(align);
      detail.setVisible(true);
      panel.add(detail);
      panel.detailsList.add(detail);
   }
}
