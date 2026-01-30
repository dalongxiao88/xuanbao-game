package org.come.annex.Tournaments.Jpanel;

import com.tool.tcpimg.UIUtils;
import org.come.action.IconButtonEffect;
import org.come.annex.Tournaments.Btn.TournamentsBtn;
import org.come.annex.Tournaments.Other.TournamentsData;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.MessagrFlagUntil;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TournamentsScreen3Jpanel extends JPanel {
   private TournamentsCardJpanel tournamentsCardJpanel;
   public List<JLabel> detailsList = new ArrayList<>();
   public JLabel[] avatar = new JLabel[4];
   private TournamentsBtn collectBtn;
   String[] dataTypes = null;
   public TournamentsData Data = new TournamentsData();
   private ImageIcon icon = new ImageIcon("inkImg/danxin/p/b5X.png");

   public TournamentsScreen3Jpanel(TournamentsCardJpanel tournamentsCardJpanel) {
      this.tournamentsCardJpanel = tournamentsCardJpanel;
      this.setPreferredSize(new Dimension(639, 444));
      this.setOpaque(false);
      this.setLayout(null);
      this.setReceiptData("1-未找到");
   }

   public void setReceiptData(String DataType) {
      this.clearPreviousContent();
      this.collectBtn = new TournamentsBtn("inkImg/danxin/p/e9.png", 1, UIUtils.COLOR_BTNPUTONG4, "领取礼包", UIUtils.TEXT_HY99, 40, this);
      this.collectBtn.setBounds(562, 35, 77, 25);
      this.add(this.collectBtn);
      this.avatar = new JLabel[5];
      this.dataTypes = DataType.split("-");
      String[] winner = this.Data.winner.split("\\|");
      String[] runner = this.Data.runner.split("\\|");
      String[] thirdRunnerUp = this.Data.thirdRunnerUp.split("\\|");
      String[] theImperialArmy = this.Data.theImperialArmy.split("\\|");
      String[] otherPrizes = this.Data.otherPrizes.split("-");
      SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
      SimpleDateFormat outputFormat = new SimpleDateFormat("MM月dd日HH:mm");
      String formattedPickUpTime = "";
      boolean canPickUp = false;

      try {
         Date pickUpDate = inputFormat.parse(this.Data.pickUpTime);
         formattedPickUpTime = outputFormat.format(pickUpDate);
         canPickUp = new Date().after(pickUpDate);
      } catch (ParseException var15) {
         var15.printStackTrace();
      }

      if (canPickUp) {
         this.details(35, 40, 400, 18, "您的排名是" + this.dataTypes[1] + ",可以领取奖励", new Color(105, 60, 38), UIUtils.TEXT_FONT, 2, this);
         this.collectBtn.setVisible(true);
      } else {
         this.details(35, 40, 400, 18, formattedPickUpTime + "后可领取奖励", new Color(105, 60, 38), UIUtils.TEXT_FONT, 2, this);
         this.collectBtn.setVisible(false);
      }

      this.details(56, 98, 90, 18, "淘汰赛奖品", new Color(250, 243, 196), UIUtils.TEXT_HYK16a, 0, this);
      this.details(360, 97, 90, 18, "决赛奖品", new Color(250, 243, 196), UIUtils.TEXT_HYK16a, 0, this);

      for (int i = 0; i < winner.length; i++) {
         String[] winnerGoods = winner[i].split("-");
         if (winnerGoods.length > 0) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(winnerGoods[0]));
            if (winnerGoods[1].equals("1")) {
               this.details(405, 285 + i * 18, 110, 18, "• " + goodstable.getGoodsname(), new Color(214, 204, 187), UIUtils.TEXT_FONT, 2, this);
            } else {
               this.details(
                  405, 285 + i * 18, 110, 18, "• " + goodstable.getGoodsname() + "x" + winnerGoods[1], new Color(214, 204, 187), UIUtils.TEXT_FONT, 2, this
               );
            }
         }
      }

      for (int ix = 0; ix < runner.length; ix++) {
         String[] runnerGoods = runner[ix].split("-");
         if (runnerGoods.length > 0) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(runnerGoods[0]));
            if (runnerGoods[1].equals("1")) {
               this.details(295, 305 + ix * 18, 98, 18, "• " + goodstable.getGoodsname(), new Color(214, 204, 187), UIUtils.TEXT_FONT, 2, this);
            } else {
               this.details(
                  295, 305 + ix * 18, 98, 18, "• " + goodstable.getGoodsname() + "x" + runnerGoods[1], new Color(214, 204, 187), UIUtils.TEXT_FONT, 2, this
               );
            }
         }
      }

      for (int ixx = 0; ixx < thirdRunnerUp.length; ixx++) {
         String[] thirdRunnerUpGoods = thirdRunnerUp[ixx].split("-");
         if (thirdRunnerUpGoods.length > 0) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(thirdRunnerUpGoods[0]));
            if (thirdRunnerUpGoods[1].equals("1")) {
               this.details(530, 305 + ixx * 18, 98, 18, "• " + goodstable.getGoodsname(), new Color(214, 204, 187), UIUtils.TEXT_FONT, 2, this);
            } else {
               this.details(
                  530,
                  305 + ixx * 18,
                  98,
                  18,
                  "• " + goodstable.getGoodsname() + "x" + thirdRunnerUpGoods[1],
                  new Color(214, 204, 187),
                  UIUtils.TEXT_FONT,
                  2,
                  this
               );
            }
         }
      }

      for (int ixxx = 0; ixxx < theImperialArmy.length; ixxx++) {
         String[] theImperialArmyGoods = theImperialArmy[ixxx].split("-");
         if (theImperialArmyGoods.length > 0) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(theImperialArmyGoods[0]));
            if (theImperialArmyGoods[1].equals("1")) {
               this.details(195, 323 + ixxx * 18, 98, 18, "• " + goodstable.getGoodsname(), new Color(214, 204, 187), UIUtils.TEXT_FONT, 2, this);
            } else {
               this.details(
                  195,
                  323 + ixxx * 18,
                  98,
                  18,
                  "• " + goodstable.getGoodsname() + "x" + theImperialArmyGoods[1],
                  new Color(214, 204, 187),
                  UIUtils.TEXT_FONT,
                  2,
                  this
               );
            }
         }
      }

      for (int ixxxx = 0; ixxxx < otherPrizes.length; ixxxx++) {
         if (otherPrizes.length > 0) {
            final Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(otherPrizes[ixxxx]));
            if (this.avatar[ixxxx] == null) {
               this.avatar[ixxxx] = new JLabel();
               this.add(this.avatar[ixxxx]);
            }

            ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 27, 27);
            this.avatar[ixxxx].setIcon(icon);
            this.avatar[ixxxx].setName("avatar_icon");
            this.avatar[ixxxx].addMouseListener(new IconButtonEffect(this.avatar[ixxxx]));
            this.avatar[ixxxx].addMouseListener(new MouseAdapter() {
               @Override
               public void mouseEntered(MouseEvent e) {
                  MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                  TournamentsBtn.claimTheDraw(100, String.valueOf(goodstable.getGoodsid()));
               }

               @Override
               public void mouseExited(MouseEvent e) {
                  MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                  FormsManagement.HideForm(24);
               }
            });
            this.avatar[ixxxx].setBounds(86, 135 + ixxxx * 62, 27, 27);
            String[] otherPrizesName = new String[]{"32 强", "16 强", "8 强", "积分赛"};
            this.details(65, 164 + ixxxx * 61, 72, 18, otherPrizesName[ixxxx], new Color(255, 235, 175), UIUtils.TEXT_FONT, 0, this);
         }
      }
   }

   private void clearPreviousContent() {
      for (JLabel label : this.detailsList) {
         this.remove(label);
      }

      this.detailsList.clear();
      Component[] components = this.getComponents();

      for (Component comp : components) {
         if (comp.getName() != null && (comp.getName().equals("advance_icon") || comp.getName().startsWith("avatar_icon"))) {
            this.remove(comp);
         }
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(this.icon.getImage(), 11, 63, 647, 388, this);
   }

   public void details(int x, int y, int w, int h, String text, Color color, Font font, int align, TournamentsScreen3Jpanel panel) {
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
