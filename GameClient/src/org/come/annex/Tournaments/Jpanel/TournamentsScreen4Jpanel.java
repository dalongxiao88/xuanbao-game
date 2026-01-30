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

public class TournamentsScreen4Jpanel extends JPanel {
   private TournamentsCardJpanel tournamentsCardJpanel;
   public List<JLabel> detailsList = new ArrayList<>();
   public JLabel[] avatar = new JLabel[6];
   public JLabel[] avatarX = new JLabel[2];
   public JLabel[] avatarL = new JLabel[4];
   public JLabel[] avatarLX = new JLabel[1];
   public JLabel[] goodsIcon = new JLabel[1];
   private TournamentsBtn raffle1Btn;
   private TournamentsBtn raffle2Btn;
   private TournamentsBtn raffle3Btn;
   int Width = 0;
   public TournamentsData Data = new TournamentsData();
   String[] dataTypes = null;
   private ImageIcon icon = new ImageIcon("inkImg/danxin/p/b2.png");
   private ImageIcon icon1 = new ImageIcon("inkImg/danxin/p/f6.png");

   public TournamentsScreen4Jpanel(TournamentsCardJpanel tournamentsCardJpanel) {
      this.tournamentsCardJpanel = tournamentsCardJpanel;
      this.setPreferredSize(new Dimension(639, 444));
      this.setOpaque(false);
      this.setLayout(null);
      this.raffle1Btn = new TournamentsBtn("inkImg/danxin/p/e11.png", 1, UIUtils.COLOR_BTNPUTONG5, "抽 奖", UIUtils.TEXT_HY88, 45, this);
      this.raffle1Btn.setBounds(273, 272, 120, 36);
      this.add(this.raffle1Btn);
      this.raffle2Btn = new TournamentsBtn("inkImg/danxin/p/e10.png", 1, UIUtils.COLOR_BTNPUTONG5, "抽 奖", UIUtils.TEXT_HY88, 46, this);
      this.raffle2Btn.setBounds(530, 338, 76, 36);
      this.add(this.raffle2Btn);
      this.raffle3Btn = new TournamentsBtn("inkImg/danxin/p/e10.png", 1, UIUtils.COLOR_BTNPUTONG5, "抽 奖", UIUtils.TEXT_HY88, 47, this);
      this.raffle3Btn.setBounds(530, 403, 76, 36);
      this.add(this.raffle3Btn);
      this.setReceiptData("0-0-0-未找到-1-1");
   }

   public void setReceiptData(String DataType) {
      this.clearPreviousContent();
      this.avatar = new JLabel[6];
      this.avatarX = new JLabel[2];
      this.avatarL = new JLabel[4];
      this.avatarLX = new JLabel[1];
      this.goodsIcon = new JLabel[1];
      this.dataTypes = DataType.split("-");
      if (this.dataTypes[0].equals("0")) {
         this.raffle1Btn.setEnabled(false);
      } else {
         this.raffle1Btn.setEnabled(true);
      }

      if (this.dataTypes[4].equals("1")) {
         this.raffle2Btn.setEnabled(false);
      } else {
         this.raffle2Btn.setEnabled(true);
      }

      if (this.dataTypes[5].equals("1")) {
         this.raffle3Btn.setEnabled(false);
      } else {
         this.raffle3Btn.setEnabled(true);
      }

      String[] foundationAward = this.Data.foundationAward.split("\\|");
      String[] foundationAwardGoods = foundationAward[0].split("-");
      final Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(foundationAwardGoods[0]));
      String goods1 = "参与全部两轮积分赛，可获得"
         + goodstable.getGoodsname()
         + "("
         + this.getTrade(Math.toIntExact(goodstable.getQuality()))
         + ")"
         + this.getNumber(Integer.parseInt(foundationAwardGoods[1]))
         + foundationAward[1];
      this.Width = this.getDetailedTextWidth(goods1, UIUtils.TEXT_FONT);
      SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
      SimpleDateFormat outputFormat = new SimpleDateFormat("MM月dd日HH:mm");
      String formattedPickUpTime = "";
      boolean canPickUp = false;

      try {
         Date pickUpDate = inputFormat.parse(this.Data.pickUpTime);
         formattedPickUpTime = outputFormat.format(pickUpDate);
         canPickUp = new Date().after(pickUpDate);
      } catch (ParseException var20) {
         var20.printStackTrace();
      }

      if (canPickUp) {
         this.details(35, 40, 400, 18, "您的排名是" + this.dataTypes[3] + ",可以领取奖励", new Color(105, 60, 38), UIUtils.TEXT_FONT, 2, this);
      } else {
         this.details(35, 40, 400, 18, formattedPickUpTime + "后可领取奖励", new Color(105, 60, 38), UIUtils.TEXT_FONT, 2, this);
      }

      this.details(20, 55, 630, 60, this.Data.engagementTitle, new Color(154, 72, 22, 230), UIUtils.TEXT_BT40s, 0, this);
      this.details(320 - this.Width / 2, 105, this.Width, 20, goods1, new Color(105, 60, 38), UIUtils.TEXT_FONT, 0, this);
      if (this.goodsIcon[0] == null) {
         this.goodsIcon[0] = new JLabel();
         this.add(this.goodsIcon[0]);
      }

      ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 26, 26);
      this.goodsIcon[0].setIcon(icon);
      this.goodsIcon[0].setName("goodsIcon_icon");
      this.goodsIcon[0].addMouseListener(new IconButtonEffect(this.goodsIcon[0]));
      this.goodsIcon[0].addMouseListener(new MouseAdapter() {
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

         @Override
         public void mouseClicked(MouseEvent e) {
            TournamentsBtn.claimTheDraw(5, null);
         }
      });
      this.goodsIcon[0].setBounds(320 - this.Width / 2 + this.Width + 5, 104, 20, 20);
      this.details(20, 235, 595, 18, "当前可抽奖次数:", new Color(146, 92, 69), UIUtils.TEXT_FONTZS, 0, this);
      this.details(380, 229, 38, 30, this.dataTypes[0], new Color(225, 61, 48), UIUtils.TEXT_FONTZSRL, 0, this);
      this.details(18, 255, 630, 18, "淘汰赛开始每胜利一场，可多获得一次抽奖机会", new Color(102, 73, 49), UIUtils.TEXT_FONT, 0, this);
      this.details(18, 307, 630, 18, "淘汰赛/参与-获胜", new Color(255, 248, 200), UIUtils.TEXT_FONTZS, 0, this);
      this.details(333, 335, 300, 25, "参与 5 场淘汰赛", new Color(130, 69, 48), UIUtils.TEXT_FONTZS2, 2, this);
      this.details(333, 400, 300, 25, "获胜 5 场淘汰赛", new Color(130, 69, 48), UIUtils.TEXT_FONTZS2, 2, this);
      this.details(320, 358, 192, 15, this.processNumber(this.dataTypes[1]) + "/5", new Color(100, 27, 17), UIUtils.TEXT_FONT, 0, this);
      this.details(320, 423, 192, 15, this.processNumber(this.dataTypes[2]) + "/5", new Color(100, 27, 17), UIUtils.TEXT_FONT, 0, this);
      String[] winningDrawResult = this.parseDrawData(this.Data.winningDraw, 5);
      String mainProduct = winningDrawResult[4];
      String[] setVictoryDrawData = new String[4];
      System.arraycopy(winningDrawResult, 0, setVictoryDrawData, 0, 4);
      String[] involvedResult = this.parseDrawData(this.Data.involved, 4);
      String mainProduct1 = involvedResult[3];
      String[] sweepstakesItems1 = new String[3];
      System.arraycopy(involvedResult, 0, sweepstakesItems1, 0, 3);
      String[] victoryResult = this.parseDrawData(this.Data.victory, 4);
      String mainProduct2 = victoryResult[3];
      String[] sweepstakesItems2 = new String[3];
      System.arraycopy(victoryResult, 0, sweepstakesItems2, 0, 3);
      this.setVictoryDrawData(setVictoryDrawData, mainProduct);
      this.setSweepstakesItemsData(sweepstakesItems1, mainProduct1, 0);
      this.setSweepstakesItemsData(sweepstakesItems2, mainProduct2, 1);
   }

   public void setVictoryDrawData(String[] setVictoryDrawData, String mainProduct) {
      for (int i = 0; i < setVictoryDrawData.length; i++) {
         if (setVictoryDrawData.length > 0) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(setVictoryDrawData[i]));
            if (this.avatarL[i] == null) {
               this.avatarL[i] = new JLabel();
               this.add(this.avatarL[i]);
            }

            if (i < 2) {
               ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 55, 55);
               this.avatarL[i].setIcon(icon);
               this.avatarL[i].setName("avatarL_icon");
               this.avatarL[i].addMouseListener(new IconButtonEffect(this.avatarL[i]));
               this.avatarL[i].addMouseListener(this.createItemTooltipListener(goodstable));
               this.avatarL[i].setBounds(75 + i * 461, 218, 55, 55);
            } else {
               ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 55, 55);
               this.avatarL[i].setIcon(icon);
               this.avatarL[i].setName("avatarL_icon");
               this.avatarL[i].addMouseListener(new IconButtonEffect(this.avatarL[i]));
               this.avatarL[i].addMouseListener(this.createItemTooltipListener(goodstable));
               this.avatarL[i].setBounds(168 + (i - 2) * 275, 162, 55, 55);
            }
         }
      }

      Goodstable goodstablex = UserMessUntil.getgoodstable(new BigDecimal(mainProduct));
      if (this.avatarLX[0] == null) {
         this.avatarLX[0] = new JLabel();
         this.add(this.avatarLX[0]);
      }

      ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstablex.getSkin() + ".png", 80, 80);
      this.avatarLX[0].setIcon(icon);
      this.avatarLX[0].setName("avatarLX_icon");
      this.avatarLX[0].addMouseListener(new IconButtonEffect(this.avatarLX[0]));
      this.avatarLX[0].addMouseListener(this.createItemTooltipListener(goodstablex));
      this.avatarLX[0].setBounds(300, 130, 80, 80);
   }

   public void setSweepstakesItemsData(String[] sweepstakesItems, String mainProduct, int index) {
      for (int i = 0; i < sweepstakesItems.length; i++) {
         if (sweepstakesItems.length > 0) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(sweepstakesItems[i]));
            if (this.avatar[i + index * 3] == null) {
               this.avatar[i + index * 3] = new JLabel();
               this.add(this.avatar[i + index * 3]);
            }

            ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 35, 35);
            this.avatar[i + index * 3].setIcon(icon);
            this.avatar[i + index * 3].setName("avatar_icon");
            this.avatar[i + index * 3].addMouseListener(new IconButtonEffect(this.avatar[i + index * 3]));
            this.avatar[i + index * 3].addMouseListener(this.createItemTooltipListener(goodstable));
            this.avatar[i + index * 3].setBounds(150 + i * 57, 339 + index * 65, 35, 35);
         }
      }

      Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(mainProduct));
      if (this.avatarX[index] == null) {
         this.avatarX[index] = new JLabel();
         this.add(this.avatarX[index]);
      }

      ImageIcon icon = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 55, 55);
      this.avatarX[index].setIcon(icon);
      this.avatarX[index].setName("avatarX_icon");
      this.avatarX[index].addMouseListener(new IconButtonEffect(this.avatarX[index]));
      this.avatarX[index].addMouseListener(this.createItemTooltipListener(goodstable));
      this.avatarX[index].setBounds(73, 328 + index * 65, 55, 55);
   }

   private void clearPreviousContent() {
      for (JLabel label : this.detailsList) {
         this.remove(label);
      }

      this.detailsList.clear();
      Component[] components = this.getComponents();

      for (Component comp : components) {
         if (comp.getName() != null
            && (
               comp.getName().equals("goodsIcon_icon")
                  || comp.getName().startsWith("avatar_icon")
                  || comp.getName().startsWith("avatarX_icon")
                  || comp.getName().startsWith("avatarL_icon")
                  || comp.getName().startsWith("avatarLX_icon")
            )) {
            this.remove(comp);
         }
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.drawImage(this.icon.getImage(), 19, 17, 631, 436, this);
      g.drawImage(this.icon1.getImage(), 320 - this.Width / 2 + this.Width + 2, 101, 28, 28, this);
      this.drawProgressBar(g2d, 322, 359, this.processNumber(this.dataTypes[1]));
      this.drawProgressBar(g2d, 322, 424, this.processNumber(this.dataTypes[2]));
   }

   private void drawProgressBar(Graphics2D g2d, int x, int y, String value) {
      int width = 192;
      int height = 12;
      int progress = Math.min(Integer.parseInt(value), 5);
      int filledWidth = (int)(progress / 5.0 * width);
      g2d.setColor(new Color(255, 255, 255, 100));
      g2d.fillRoundRect(x, y, width, height, 6, 6);
      g2d.setColor(new Color(255, 200, 0, 200));
      g2d.fillRoundRect(x, y, filledWidth, height, 6, 6);
      g2d.setColor(new Color(100, 100, 100));
      g2d.drawRoundRect(x, y, width, height, 6, 6);
   }

   public void details(int x, int y, int w, int h, String text, Color color, Font font, int align, TournamentsScreen4Jpanel panel) {
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

   private int getDetailedTextWidth(String text, Font font) {
      FontMetrics metrics = this.getFontMetrics(font);
      int width = 0;

      for (int i = 0; i < text.length(); i++) {
         char c = text.charAt(i);
         width += metrics.charWidth(c);
      }

      return width + metrics.getLeading();
   }

   private String getNumber(int number) {
      switch (number) {
         case 1:
            return "一";
         case 2:
            return "二";
         case 3:
            return "三";
         case 4:
            return "四";
         case 5:
            return "五";
         case 6:
            return "六";
         case 7:
            return "七";
         case 8:
            return "八";
         case 9:
            return "九";
         case 10:
            return "十";
         default:
            return String.valueOf(number);
      }
   }

   private String getTrade(int trade) {
      switch (trade) {
         case 1:
         case 3:
            return "不可交易";
         default:
            return "可交易";
      }
   }

   private String[] parseDrawData(String data, int count) {
      String[] lastItems = new String[count];
      if (data != null && !data.isEmpty()) {
         try {
            String[] sections = data.split("\\|");
            List<String> allItems = new ArrayList<>();

            for (String section : sections) {
               if (section.startsWith("物品=")) {
                  String[] items = section.substring(3).split("&");

                  for (String item : items) {
                     allItems.add(item);
                  }
               }
            }

            int size = allItems.size();

            for (int i = 0; i < count && i < size; i++) {
               lastItems[i] = allItems.get(size - count + i);
            }
         } catch (Exception var15) {
            var15.printStackTrace();
         }

         return lastItems;
      } else {
         return lastItems;
      }
   }

   private MouseAdapter createItemTooltipListener(final Goodstable goodstable) {
      return new MouseAdapter() {
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
      };
   }

   private String processNumber(String number) {
      try {
         int value = Integer.parseInt(number);
         return String.valueOf(Math.min(value, 5));
      } catch (NumberFormatException var3) {
         return "0";
      }
   }
}
