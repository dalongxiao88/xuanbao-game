package org.come.annex.Tournaments.Other;

import com.tool.tcpimg.UIUtils;
import org.come.annex.Tournaments.Jpanel.TournamentsScreen2Jpanel;
import org.come.bean.ConfigureBean;
import org.come.model.Configure;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TournamentsData {
   public ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
   public Map<BigDecimal, Configure> item = this.allConfigure.getAllConfigure();
   public Configure configure200 = this.item.get(new BigDecimal(200));
   public Configure configure201 = this.item.get(new BigDecimal(201));
   public Configure configure202 = this.item.get(new BigDecimal(202));
   public Configure configure203 = this.item.get(new BigDecimal(203));
   public Configure configure204 = this.item.get(new BigDecimal(204));
   public BigDecimal gameId = new BigDecimal(this.configure200.getZqsld());
   public int mode = Integer.parseInt(this.configure200.getZqjnsx());
   public int pointsRaceM = Integer.parseInt(this.configure200.getNdslsx());
   public int knockoutM = Integer.parseInt(this.configure201.getNdslsx());
   public int matchWaiting = Integer.parseInt(this.configure201.getZqjnsx());
   public int waitingForSuccess = Integer.parseInt(this.configure201.getJumpurl());
   public String switchStatus = this.configure201.getZqsld();
   public String title = this.configure200.getJumpurl();
   public int InitialPosition = Integer.parseInt(this.configure200.getXdzhssx());
   public String StartTimeEnroll = this.configure200.getLyssx();
   public String EndTimeEnroll = this.configure200.getLywsx();
   public String StartTime1 = this.configure200.getRwdjsx();
   public String EndTime1 = this.configure200.getZhsdjsx();
   public String StartTime2 = this.configure201.getLyssx();
   public String EndTime2 = this.configure201.getLywsx();
   public String StartTime3A = this.configure201.getRwdjsx();
   public String EndTime3A = this.configure201.getZhsdjsx();
   public String StartTime3B = this.configure202.getLyssx();
   public String EndTime3B = this.configure202.getLywsx();
   public String StartTime3C = this.configure202.getRwdjsx();
   public String EndTime3C = this.configure202.getZhsdjsx();
   public String StartTime3D = this.configure203.getLyssx();
   public String EndTime3D = this.configure203.getLywsx();
   public String StartTime4 = this.configure203.getRwdjsx();
   public String StartTime5 = this.configure203.getZhsdjsx();
   public String EndTime4 = this.configure204.getRwdjsx();
   public String EndTime5 = this.configure204.getZhsdjsx();
   public String pickUpTime = this.configure202.getZqsld();
   public String winner = this.configure202.getZqjnsx();
   public String runner = this.configure202.getJumpurl();
   public String thirdRunnerUp = this.configure202.getXdzhssx();
   public String theImperialArmy = this.configure202.getNdslsx();
   public String otherPrizes = this.configure203.getZqsld();
   public String engagementTitle = this.configure204.getXdzhssx();
   public String foundationAward = this.configure203.getZqjnsx();
   public String winningDraw = this.configure203.getJumpurl();
   public String involved = this.configure203.getXdzhssx();
   public String victory = this.configure203.getNdslsx();
   public ImageIcon icon = new ImageIcon("inkImg/danxin/p/b1.png");
   public ImageIcon icon1 = new ImageIcon("inkImg/danxin/p/b4.png");
   public ImageIcon icon2 = new ImageIcon("inkImg/danxin/p/b3.png");
   public ImageIcon icon3 = new ImageIcon("inkImg/danxin/p/f4.png");
   public ImageIcon icon4 = new ImageIcon("inkImg/danxin/p/f5.png");
   public ImageIcon icon5 = new ImageIcon("inkImg/danxin/p/f8.png");
   public ImageIcon icon6 = new ImageIcon("inkImg/danxin/p/f16.png");
   public ImageIcon icon7 = new ImageIcon("inkImg/danxin/p/f17.png");
   public ImageIcon icon8 = new ImageIcon("inkImg/danxin/p/f18.png");
   public ImageIcon icon9 = new ImageIcon("inkImg/danxin/p/f19.png");
   public ImageIcon icon10 = new ImageIcon("inkImg/danxin/p/f10.png");
   public ImageIcon icon11 = new ImageIcon("inkImg/danxin/p/f21.png");

   public String getCurrentStage(long currentTime) {
      if (this.isTimeInRange(currentTime, this.StartTime1, this.EndTime1)) {
         return "FIRST_POINTS";
      } else if (this.isTimeInRange(currentTime, this.StartTime2, this.EndTime2)) {
         return "SECOND_POINTS";
      } else if (this.isTimeInRange(currentTime, this.StartTime3A, this.EndTime3A)) {
         return "TOP_32";
      } else if (this.isTimeInRange(currentTime, this.StartTime3B, this.EndTime3B)) {
         return "TOP_16";
      } else if (this.isTimeInRange(currentTime, this.StartTime3C, this.EndTime3C)) {
         return "TOP_8";
      } else if (this.isTimeInRange(currentTime, this.StartTime3D, this.EndTime3D)) {
         return "TOP_4";
      } else if (this.isTimeInRange(currentTime, this.StartTime4, this.EndTime4)) {
         return "THIRD_PLACE";
      } else {
         return this.isTimeInRange(currentTime, this.StartTime5, this.EndTime5) ? "FINAL" : "UNKNOWN";
      }
   }

   public int getStageIndex(String stage) {
      switch (stage) {
         case "FIRST_POINTS":
            return 0;
         case "SECOND_POINTS":
            return 1;
         case "TOP_32":
            return 2;
         case "TOP_16":
            return 3;
         case "TOP_8":
            return 4;
         case "TOP_4":
            return 5;
         case "THIRD_PLACE":
         case "FINAL":
            return 6;
         default:
            return 0;
      }
   }

   public int getKnockedIndex(String stage) {
      switch (stage) {
         case "TOP_32":
            return 0;
         case "TOP_16":
            return 1;
         case "TOP_8":
            return 2;
         case "TOP_4":
            return 3;
         case "THIRD_PLACE":
         case "FINAL":
            return 4;
         default:
            return 0;
      }
   }

   private boolean isTimeInRange(long currentTime, String startTimeStr, String endTimeStr) {
      long startTime = this.parseTime(startTimeStr);
      long endTime = this.parseTime(endTimeStr);
      return currentTime >= startTime && currentTime <= endTime;
   }

   private long parseTime(String timeStr) {
      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
         return sdf.parse(timeStr).getTime();
      } catch (ParseException var3) {
         var3.printStackTrace();
         return 0L;
      }
   }

   public String getStartTime(int index) {
      switch (index) {
         case 0:
            return this.StartTime1;
         case 1:
            return this.StartTime2;
         case 2:
            return this.getKnockoutStartTime();
         case 3:
            return this.StartTime4;
         case 4:
            return this.StartTime5;
         default:
            return null;
      }
   }

   private String getKnockoutStartTime() {
      if (this.InitialPosition == 32) {
         return this.StartTime3A;
      } else {
         return this.InitialPosition == 16 ? this.StartTime3B : this.StartTime3C;
      }
   }

   public String getEndTime(int index) {
      switch (index) {
         case 0:
            return this.EndTime1;
         case 1:
            return this.EndTime2;
         case 2:
            return this.EndTime3D;
         case 3:
            return this.StartTime4;
         case 4:
            return this.StartTime5;
         default:
            return null;
      }
   }

   public String formatDate(String dateStr, int type) {
      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
         Date date = sdf.parse(dateStr);
         String pattern;
         switch (type) {
            case 0:
               pattern = "dd日";
               break;
            case 1:
               pattern = "MM月dd日";
               break;
            case 2:
               pattern = "MM月dd日 HH:mm";
               break;
            default:
               return "";
         }

         SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);
         return outputFormat.format(date);
      } catch (ParseException var7) {
         var7.printStackTrace();
         return "";
      }
   }

   public String getDateRange(String start, String end) {
      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
         Date startDate = sdf.parse(start);
         Date endDate = sdf.parse(end);
         long diff = (endDate.getTime() - startDate.getTime()) / 86400000L;
         return diff > 0L ? this.formatDate(start, 1) + "-" + this.formatDate(end, 0) : this.formatDate(start, 1);
      } catch (ParseException var8) {
         var8.printStackTrace();
         return "";
      }
   }

   public String getKnockoutDateRange() {
      String startTime;
      if (this.InitialPosition == 32) {
         startTime = this.StartTime3A;
      } else if (this.InitialPosition == 16) {
         startTime = this.StartTime3B;
      } else {
         startTime = this.StartTime3C;
      }

      return this.getDateRange(startTime, this.EndTime3D);
   }

   public void playoffsFor32(String selectedOption, TournamentsScreen2Jpanel panel) {
      switch (selectedOption) {
         case "第一轮:32晋级16":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime2) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime2, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3A, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
            break;
         case "第二轮:16晋级8":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime3A) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime3A, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3B, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
            break;
         case "第三轮:8晋级4":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime3B) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime3B, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3C, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
            break;
         case "第四轮:4晋级2":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime3C) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime3C, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3D, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
      }
   }

   public void playoffsFor16(String selectedOption, TournamentsScreen2Jpanel panel) {
      switch (selectedOption) {
         case "第一轮:16晋级8":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime2) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime2, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3B, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
            break;
         case "第二轮:8晋级4":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime3B) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime3B, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3C, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
            break;
         case "第三轮:4晋级2":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime3C) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime3C, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3D, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
      }
   }

   public void playoffsFor8(String selectedOption, TournamentsScreen2Jpanel panel) {
      switch (selectedOption) {
         case "第一轮:8晋级4":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime2) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime2, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3C, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
            break;
         case "第二轮:4晋级2":
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.EndTime3C) < 0) {
               panel.getScrollPaneTrans().setVisible(false);
               this.details(258, 275, 158, 15, this.formatDate(this.EndTime3C, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
               this.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, panel);
            }

            this.details(177, 118, 96, 18, this.formatDate(this.StartTime3D, 2), new Color(255, 255, 255), UIUtils.TEXT_FONT, 0, panel);
      }
   }

   public void promptInformation(String StartTime, String StartTime2, TournamentsScreen2Jpanel panel) {
      String currentTime = new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date());
      if (currentTime.compareTo(StartTime2) > 0 && currentTime.compareTo(StartTime) < 0) {
         this.details(258, 295, 158, 15, this.formatDate(StartTime, 2), Color.WHITE, UIUtils.TEXT_FONT, 0, panel);
         this.details(258, 312, 158, 15, "开始比赛", Color.WHITE, UIUtils.TEXT_FONT, 0, panel);
      } else if (currentTime.compareTo(StartTime2) < 0) {
         this.details(258, 295, 158, 15, this.formatDate(StartTime, 2), Color.WHITE, UIUtils.TEXT_FONT, 0, panel);
         this.details(258, 312, 158, 15, "开始比赛", Color.WHITE, UIUtils.TEXT_FONT, 0, panel);
         this.details(130, 280, 100, 16, "虚位以待", Color.WHITE, UIUtils.TEXT_HYK16a, 0, panel);
         this.details(438, 280, 100, 16, "虚位以待", Color.WHITE, UIUtils.TEXT_HYK16a, 0, panel);
      }
   }

   public void titleBar(TournamentsScreen2Jpanel panel) {
      this.details(73, 153, 40, 20, "队伍", new Color(96, 47, 20), UIUtils.TEXT_FONTZS1, 0, panel);
      this.details(240, 153, 40, 20, "人员", new Color(96, 47, 20), UIUtils.TEXT_FONTZS1, 0, panel);
      this.details(378, 153, 80, 20, "胜利场次", new Color(96, 47, 20), UIUtils.TEXT_FONTZS1, 0, panel);
      this.details(465, 153, 80, 20, "失败场次", new Color(96, 47, 20), UIUtils.TEXT_FONTZS1, 0, panel);
      this.details(565, 153, 40, 20, "积分", new Color(96, 47, 20), UIUtils.TEXT_FONTZS1, 0, panel);
   }

   public void details(int x, int y, int w, int h, String text, Color color, Font font, int align, TournamentsScreen2Jpanel panel) {
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
