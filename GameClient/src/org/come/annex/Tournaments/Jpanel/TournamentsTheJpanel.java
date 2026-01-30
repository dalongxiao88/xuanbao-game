package org.come.annex.Tournaments.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import org.come.action.IconButtonEffect;
import org.come.annex.Tournaments.Btn.TournamentsBtn;
import org.come.annex.Tournaments.Other.GameFigures;
import org.come.annex.Tournaments.Other.GameFiguresBean;
import org.come.annex.Tournaments.Other.TournamentsData;
import org.come.bean.LoginResult;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TournamentsTheJpanel extends JPanel {
   public TournamentsData Data = new TournamentsData();
   public TournamentsBtn cancelBtn;
   public List<GameFigures> gameFiguresList = new ArrayList<>();
   public List<JLabel> detailsList = new ArrayList<>();
   private JLabel countdownLabel;
   private JLabel header;
   private Timer timer;
   private int remainingTime = 300;
   public JLabel labtext1;
   public JLabel[] avatar = new JLabel[10];
   public JLabel[] name = new JLabel[10];
   public int current = 0;
   public boolean ready1 = false;
   public boolean ready2 = false;
   private ImageIcon icon = new ImageIcon("inkImg/danxin/p/S211X.png");
   private ImageIcon icon1 = new ImageIcon("inkImg/danxin/p/f10.png");
   private ImageIcon icon2 = new ImageIcon("inkImg/danxin/p/f12.png");
   private ImageIcon icon3 = new ImageIcon("inkImg/danxin/p/f9.png");

   public TournamentsTheJpanel() {
      this.setPreferredSize(new Dimension(810, 415));
      this.setOpaque(false);
      this.setLayout(null);
      FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/e/100.png", 1, 1147);
      offBtn.setBounds(750, 25, 25, 25);
      this.add(offBtn);
      this.cancelBtn = new TournamentsBtn("inkImg/danxin/p/e8.png", 1, UIUtils.COLOR_WHITE4, "取消匹配", UIUtils.TEXT_HY88, 30, this);
      this.cancelBtn.setBounds(355, 360, 106, 26);
      this.add(this.cancelBtn);
   }

   public void setAcceptanceOfData(List<GameFigures> gameFigures, int index) {
      this.clearPreviousContent();
      this.current = index;
      this.processData(gameFigures, index);
      this.setupCountdown(index);
      this.displayMatchStatus(index);
      this.setCancelButtonState(index);
      this.gameFiguresList = gameFigures;
      this.revalidate();
      this.repaint();
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

   private void processData(List<GameFigures> gameFigures, int index) {
      if (index == 0 || index == 2 || index == 3) {
         this.processTeamData(gameFigures.get(0), this.Data.mode, 0);
      } else if (index == 1) {
         this.processTeamData(gameFigures.get(0), this.Data.mode, 0);
         this.processTeamData(gameFigures.get(1), this.Data.mode, 1);
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

   private void setAvatarPosition(int index, int mode, int teamIndex) {
      int x = 0;
      int y = 0;
      if (mode == 1) {
         if (teamIndex == 0) {
            x = index < 3 ? 130 + index % 3 * 78 : 130 + index % 2 * 156;
            y = index < 3 ? 157 : 256;
         } else {
            x = index < 8 ? 491 + index % 3 * 78 : 491 + index % 2 * 156;
            y = index < 8 ? 157 : 256;
         }
      } else if (mode == 2) {
         x = teamIndex == 0 ? 130 + index % 3 * 78 : 491 + index % 3 * 78;
         y = 199;
      } else if (mode == 3) {
         x = 208 + teamIndex * 361;
         y = 199;
      }

      this.avatar[index].setBounds(x, y, 52, 52);
   }

   private void updateMemberName(String memberName, int index, int mode, int teamIndex) {
      this.name[index] = new JLabel(memberName);
      this.name[index].setFont(new Font("宋体", 0, 12));
      this.name[index].setHorizontalAlignment(0);
      int x = 0;
      int y = 0;
      if (mode == 1) {
         if (teamIndex == 0) {
            x = index < 3 ? 126 + index % 3 * 78 : 126 + index % 2 * 156;
            y = index < 3 ? 215 : 314;
         } else {
            x = index < 8 ? 487 + index % 3 * 78 : 487 + index % 2 * 156;
            y = index < 8 ? 215 : 314;
         }
      } else if (mode == 2) {
         x = teamIndex == 0 ? 126 + index % 3 * 78 : 487 + index % 3 * 78;
         y = 257;
      } else if (mode == 3) {
         x = 204 + teamIndex * 361;
         y = 257;
      }

      this.name[index].setBounds(x, y, 70, 15);
      this.add(this.name[index]);
   }

   private void setupCountdown(int index) {
      if (this.timer != null) {
         this.timer.cancel();
         this.timer = null;
      }

      if (index == 0) {
         this.remainingTime = this.Data.matchWaiting * 60;
      } else {
         if (index != 1) {
            if (this.countdownLabel != null) {
               this.remove(this.countdownLabel);
               this.countdownLabel = null;
            }

            if (this.header != null) {
               this.remove(this.header);
               this.header = null;
            }

            return;
         }

         this.remainingTime = this.Data.waitingForSuccess;
      }

      this.startCountdown();
   }

   private void displayMatchStatus(int index) {
      String[] statusText = new String[]{"正在匹配", "匹配成功", "匹配超时", "匹配失败"};
      if (index >= 0 && index < statusText.length) {
         String[] split = statusText[index].split("");

         for (int i = 0; i < split.length; i++) {
            this.labtext1 = new JLabel(split[i]);
            this.labtext1.setFont(UIUtils.TEXT_BT65);
            this.labtext1.setForeground(new Color(232, 199, 114, 250));
            this.labtext1.setBounds(20, 60 + i * 58, 65, 65);
            this.labtext1.setHorizontalAlignment(0);
            this.add(this.labtext1);
            this.detailsList.add(this.labtext1);
         }
      }
   }

   private void setCancelButtonState(int index) {
      if (index == 0) {
         this.header.setText("搜索匹配中");
         this.cancelBtn.setText("取消匹配");
         this.cancelBtn.setVisible(true);
         this.ready1 = true;
         this.ready2 = false;
      } else if (index == 1) {
         this.header.setText("后进入比斗");
         this.cancelBtn.setVisible(false);
         this.ready1 = true;
         this.ready2 = true;
      } else {
         this.cancelBtn.setText("重新匹配");
         this.cancelBtn.setVisible(true);
         this.ready1 = false;
         this.ready2 = false;
      }
   }

   private void updateCountdownLabel() {
      if (this.countdownLabel != null) {
         String timeString;
         String flats;
         if (this.remainingTime > 60) {
            int minutes = this.remainingTime / 60;
            int seconds = this.remainingTime % 60;
            timeString = String.format("%02d:%02d", minutes, seconds);
            flats = "分";
            this.countdownLabel.setBounds(340, 60, 70, 20);
            this.header.setBounds(410, 60, 200, 20);
         } else if (this.remainingTime >= 10) {
            timeString = String.format("%02d", this.remainingTime);
            flats = "秒";
            this.countdownLabel.setBounds(325, 60, 70, 20);
            this.header.setBounds(395, 60, 200, 20);
         } else {
            timeString = String.format("%d", this.remainingTime);
            flats = "秒";
            this.countdownLabel.setBounds(320, 60, 70, 20);
            this.header.setBounds(390, 60, 200, 20);
         }

         this.countdownLabel.setText(timeString + flats);
      }

      if (this.remainingTime <= 0) {
         if (this.current == 0) {
            GameFiguresBean gameFiguresBean = new GameFiguresBean();
            gameFiguresBean.setTotal(12);
            String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
            if (this.Data.mode == 1 || this.Data.mode == 2) {
               if (ImageMixDeal.userimg.getRoleShow().getCaptian() != 1) {
                  return;
               }

               SendMessageUntil.toServer(mes);
            } else if (this.Data.mode == 3) {
               SendMessageUntil.toServer(mes);
            }
         } else if (this.current == 1) {
            this.enterTheBattle(this.gameFiguresList);
            if (FormsManagement.getframe(1147).isVisible()) {
               FormsManagement.HideForm(1147);
            }
         }
      }
   }

   public void enterTheBattle(List<GameFigures> gameFigures) {
      LoginResult roleInfo = RoleData.getRoleData().getLoginResult();
      if (gameFigures.size() > 0) {
         if (this.Data.mode == 1 || this.Data.mode == 2) {
            if (ImageMixDeal.userimg.getRoleShow().getCaptian() != 1) {
               System.out.println("只有队长才能控制进入战斗");
               return;
            }

            this.processBattle(gameFigures, roleInfo);
         } else if (this.Data.mode == 3) {
            this.processBattle(gameFigures, roleInfo);
         }
      }
   }

   private void processBattle(List<GameFigures> gameFigures, LoginResult roleInfo) {
      GameFiguresBean gameFiguresBean = new GameFiguresBean();
      gameFiguresBean.setTotal(15);

      for (GameFigures figure : gameFigures) {
         String[] members = figure.getTeamMembersId().split("\\|");

         for (String member : members) {
            String[] memberInfo = member.split("-");
            if (memberInfo.length >= 1 && memberInfo[0].equals(roleInfo.getRole_id().toString()) && figure.getGameOrder() == 1) {
               gameFiguresBean.setCaptainId1(figure.getCaptainId());

               for (GameFigures otherFigure : gameFigures) {
                  if (!otherFigure.equals(figure)) {
                     gameFiguresBean.setCaptainId2(otherFigure.getCaptainId());
                     break;
                  }
               }

               String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
               SendMessageUntil.toServer(mes);
               return;
            }
         }
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(this.icon.getImage(), 0, 0, 810, 415, this);
      if (this.Data.mode == 1) {
         for (int i = 0; i < 3; i++) {
            g.drawImage(this.icon1.getImage(), 126 + i * 78, 153, 60, 60, this);
            g.drawImage(this.icon1.getImage(), 487 + i * 78, 153, 60, 60, this);
         }

         for (int i = 0; i < 2; i++) {
            g.drawImage(this.icon1.getImage(), 126 + i * 156, 252, 60, 60, this);
            g.drawImage(this.icon1.getImage(), 487 + i * 156, 252, 60, 60, this);
         }
      } else if (this.Data.mode == 2) {
         for (int i = 0; i < 3; i++) {
            g.drawImage(this.icon1.getImage(), 126 + i * 78, 195, 60, 60, this);
            g.drawImage(this.icon1.getImage(), 487 + i * 78, 195, 60, 60, this);
         }
      } else if (this.Data.mode == 3) {
         for (int i = 0; i < 2; i++) {
            g.drawImage(this.icon1.getImage(), 204 + i * 361, 195, 60, 60, this);
         }
      }

      if (this.ready1) {
         g.drawImage(this.icon3.getImage(), 203, 343, 62, 29, this);
      }

      if (this.ready2) {
         g.drawImage(this.icon3.getImage(), 564, 343, 62, 29, this);
      }
   }

   private void startCountdown() {
      this.countdownLabel = new JLabel();
      this.countdownLabel.setForeground(new Color(0, 255, 0, 255));
      this.countdownLabel.setFont(UIUtils.TEXT_HYK18a);
      this.countdownLabel.setHorizontalAlignment(4);
      this.countdownLabel.setBounds(340, 60, 70, 20);
      this.add(this.countdownLabel);
      this.detailsList.add(this.countdownLabel);
      this.header = new JLabel();
      this.header.setForeground(new Color(255, 246, 216, 255));
      this.header.setFont(UIUtils.TEXT_HYK18a);
      this.header.setHorizontalAlignment(2);
      this.header.setBounds(410, 60, 200, 20);
      this.add(this.header);
      this.detailsList.add(this.header);
      this.timer = new Timer();
      this.timer.scheduleAtFixedRate(new TimerTask() {
         @Override
         public void run() {
            if (TournamentsTheJpanel.this.remainingTime > 0) {
               TournamentsTheJpanel.this.remainingTime--;
               TournamentsTheJpanel.this.updateCountdownLabel();
            } else {
               TournamentsTheJpanel.this.timer.cancel();
            }
         }
      }, 0L, 1000L);
   }

   public void details(int x, int y, int w, int h, String text, Color color, Font font, int align, TournamentsTheJpanel panel) {
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
