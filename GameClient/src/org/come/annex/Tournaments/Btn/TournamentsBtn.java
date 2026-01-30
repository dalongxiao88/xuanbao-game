package org.come.annex.Tournaments.Btn;

import com.tool.btn.MoBanBtn;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.come.Frame.ZhuFrame;
import org.come.annex.Tournaments.Jpanel.TournamentsResultsJpanel;
import org.come.annex.Tournaments.Jpanel.TournamentsScreen1Jpanel;
import org.come.annex.Tournaments.Jpanel.TournamentsScreen2Jpanel;
import org.come.annex.Tournaments.Jpanel.TournamentsScreen3Jpanel;
import org.come.annex.Tournaments.Jpanel.TournamentsScreen4Jpanel;
import org.come.annex.Tournaments.Jpanel.TournamentsTheJpanel;
import org.come.annex.Tournaments.Other.GameFigures;
import org.come.annex.Tournaments.Other.GameFiguresBean;
import org.come.annex.Tournaments.Other.TournamentsData;
import org.come.bean.LoginResult;
import org.come.entity.TeamRole;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;

public class TournamentsBtn extends MoBanBtn {
   private int caozuo;
   private TournamentsScreen1Jpanel tournamentsScreen1Jpanel;
   private TournamentsScreen2Jpanel tournamentsScreen2Jpanel;
   private TournamentsScreen3Jpanel tournamentsScreen3Jpanel;
   private TournamentsScreen4Jpanel tournamentsScreen4Jpanel;
   private TournamentsTheJpanel tournamentsTheJpanel;
   private TournamentsResultsJpanel tournamentsResultsJpanel;
   public TournamentsData Data = new TournamentsData();
   private int subscript;

   public TournamentsBtn(String iconpath, int type, int caozuo, int subscript, TournamentsScreen1Jpanel tournamentsScreen1Jpanel) {
      super(iconpath, type);
      this.caozuo = caozuo;
      this.subscript = subscript;
      this.tournamentsScreen1Jpanel = tournamentsScreen1Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, int caozuo, int subscript, TournamentsScreen2Jpanel tournamentsScreen2Jpanel) {
      super(iconpath, type);
      this.caozuo = caozuo;
      this.subscript = subscript;
      this.tournamentsScreen2Jpanel = tournamentsScreen2Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, int caozuo, int subscript, TournamentsScreen3Jpanel tournamentsScreen3Jpanel) {
      super(iconpath, type);
      this.caozuo = caozuo;
      this.subscript = subscript;
      this.tournamentsScreen3Jpanel = tournamentsScreen3Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, int caozuo, int subscript, TournamentsScreen4Jpanel tournamentsScreen4Jpanel) {
      super(iconpath, type);
      this.caozuo = caozuo;
      this.subscript = subscript;
      this.tournamentsScreen4Jpanel = tournamentsScreen4Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TournamentsScreen1Jpanel tournamentsScreen1Jpanel) {
      super(iconpath, type, colors);
      this.setText(text);
      this.setVerticalTextPosition(0);
      this.setHorizontalTextPosition(0);
      this.setFont(font);
      this.caozuo = caozuo;
      this.tournamentsScreen1Jpanel = tournamentsScreen1Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TournamentsScreen2Jpanel tournamentsScreen2Jpanel) {
      super(iconpath, type, colors);
      this.setText(text);
      this.setVerticalTextPosition(0);
      this.setHorizontalTextPosition(0);
      this.setFont(font);
      this.caozuo = caozuo;
      this.tournamentsScreen2Jpanel = tournamentsScreen2Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TournamentsScreen3Jpanel tournamentsScreen3Jpanel) {
      super(iconpath, type, colors);
      this.setText(text);
      this.setVerticalTextPosition(0);
      this.setHorizontalTextPosition(0);
      this.setFont(font);
      this.caozuo = caozuo;
      this.tournamentsScreen3Jpanel = tournamentsScreen3Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TournamentsScreen4Jpanel tournamentsScreen4Jpanel) {
      super(iconpath, type, colors);
      this.setText(text);
      this.setVerticalTextPosition(0);
      this.setHorizontalTextPosition(0);
      this.setFont(font);
      this.caozuo = caozuo;
      this.tournamentsScreen4Jpanel = tournamentsScreen4Jpanel;
   }

   public TournamentsBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TournamentsTheJpanel tournamentsTheJpanel) {
      super(iconpath, type, colors);
      this.setText(text);
      this.setVerticalTextPosition(0);
      this.setHorizontalTextPosition(0);
      this.setFont(font);
      this.caozuo = caozuo;
      this.tournamentsTheJpanel = tournamentsTheJpanel;
   }

   public TournamentsBtn(String iconpath, int type, Color[] colors, String text, Font font, int caozuo, TournamentsResultsJpanel tournamentsResultsJpanel) {
      super(iconpath, type, colors);
      this.setText(text);
      this.setVerticalTextPosition(0);
      this.setHorizontalTextPosition(0);
      this.setFont(font);
      this.caozuo = caozuo;
      this.tournamentsResultsJpanel = tournamentsResultsJpanel;
   }

   @Override
   public void chooseyes() {
   }

   @Override
   public void chooseno() {
   }

   @Override
   public void nochoose(MouseEvent e) {
      LoginResult loginResult = RoleData.getRoleData().getLoginResult();
      if (this.caozuo == 10) {
         this.enroll(loginResult);
      } else if (this.caozuo != 20) {
         if (this.caozuo == 30) {
            GameFiguresBean gameFiguresBean = new GameFiguresBean();
            if (this.tournamentsTheJpanel.cancelBtn.getText().equals("取消匹配")) {
               gameFiguresBean.setTotal(10);
            } else if (this.tournamentsTheJpanel.cancelBtn.getText().equals("重新匹配")) {
               if (this.Data.mode != 3 && ImageMixDeal.userimg.getRoleShow().getCaptian() != 1) {
                  ZhuFrame.getZhuJpanel().addPrompt2("只有队长可以匹配准备");
                  return;
               }

               gameFiguresBean.setTotal(11);
            }

            String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
            SendMessageUntil.toServer(mes);
         } else if (this.caozuo == 31) {
            GameFiguresBean gameFiguresBean = new GameFiguresBean();
            if (this.tournamentsResultsJpanel.cancelBtn.getText().equals("继续匹配")) {
               if (this.Data.mode != 3 && ImageMixDeal.userimg.getRoleShow().getCaptian() != 1) {
                  ZhuFrame.getZhuJpanel().addPrompt2("只有队长可以匹配准备");
                  return;
               }

               gameFiguresBean.setTotal(11);
            }

            String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
            SendMessageUntil.toServer(mes);
         } else if (this.caozuo == 40) {
            claimTheDraw(1, null);
         } else if (this.caozuo == 45) {
            claimTheDraw(2, null);
         } else if (this.caozuo == 46) {
            claimTheDraw(3, null);
         } else if (this.caozuo == 47) {
            claimTheDraw(4, null);
         }
      }
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      super.mouseEntered(e);
   }

   @Override
   public void mouseExited(MouseEvent e) {
      super.mouseExited(e);
   }

   public static void claimTheDraw(int type, String Msg) {
      TournamentsData Data = new TournamentsData();
      SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
      SimpleDateFormat outputFormat = new SimpleDateFormat("MM月dd日HH:mm");
      String formattedPickUpTime = "";
      boolean canPickUp = false;

      try {
         Date pickUpDate = inputFormat.parse(Data.pickUpTime);
         formattedPickUpTime = outputFormat.format(pickUpDate);
         canPickUp = new Date().after(pickUpDate);
      } catch (ParseException var9) {
         var9.printStackTrace();
      }

      if (Msg == null) {
         if (canPickUp) {
            GameFiguresBean gameFiguresBean = new GameFiguresBean();
            if (type == 1) {
               gameFiguresBean.setTotal(40);
            } else if (type == 2) {
               gameFiguresBean.setTotal(45);
            } else if (type == 3) {
               gameFiguresBean.setTotal(46);
            } else if (type == 4) {
               gameFiguresBean.setTotal(47);
            } else if (type == 5) {
               gameFiguresBean.setTotal(48);
            }

            String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
            SendMessageUntil.toServer(mes);
         } else if (type == 1) {
            ZhuFrame.getZhuJpanel().addPrompt2(formattedPickUpTime + "之后可领取奖励");
         } else {
            ZhuFrame.getZhuJpanel().addPrompt2(formattedPickUpTime + "之后才可以抽奖");
         }
      } else {
         GameFiguresBean gameFiguresBean = new GameFiguresBean();
         gameFiguresBean.setTotal(type);
         gameFiguresBean.setMsg(Msg);
         String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
         SendMessageUntil.toServer(mes);
      }
   }

   public void enroll(LoginResult loginResult) {
      GameFiguresBean gameFiguresBean = new GameFiguresBean();
      gameFiguresBean.setTotal(5);
      GameFigures gameFigures = new GameFigures();
      gameFigures.setGameId(this.Data.gameId);
      gameFigures.setCaptainId(loginResult.getRole_id());
      if (this.Data.mode != 3) {
         if (RoleData.getRoleData().getTeamBean() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2(this.Data.mode == 1 ? "5v5争霸赛需要组队参加" : "3v3争霸赛需要组队参加");
            return;
         }

         if (ImageMixDeal.userimg.getRoleShow().getCaptian() != 1) {
            ZhuFrame.getZhuJpanel().addPrompt2("只有队长可以报名参加比赛");
            return;
         }

         StringBuilder teamMembersBuilder = new StringBuilder();

         for (TeamRole teamRole : RoleData.getRoleData().getTeamBean().teams) {
            if (teamMembersBuilder.length() > 0) {
               teamMembersBuilder.append("|");
            }

            teamMembersBuilder.append(teamRole.getRoleId()).append("-").append(teamRole.getName()).append("-").append(teamRole.getSpeciesId());
         }

         gameFigures.setTeamMembersId(teamMembersBuilder.toString());
         String[] arrayOfPeople = gameFigures.getTeamMembersId().split("\\|");
         if (this.Data.mode == 1) {
            if (arrayOfPeople.length < 5) {
               ZhuFrame.getZhuJpanel().addPrompt2("你的队伍不足5个人");
               return;
            }
         } else if (this.Data.mode == 2) {
            if (arrayOfPeople.length < 3) {
               ZhuFrame.getZhuJpanel().addPrompt2("你的队伍不足3个人");
               return;
            }

            if (arrayOfPeople.length > 3) {
               ZhuFrame.getZhuJpanel().addPrompt2("你的队伍已超过3个人");
               return;
            }
         }
      } else {
         if (RoleData.getRoleData().getTeamBean() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("1v1争霸赛无法组队参加");
            return;
         }

         gameFigures.setTeamMembersId(loginResult.getRole_id() + "-" + loginResult.getRolename() + "-" + loginResult.getSpecies_id());
      }

      List<GameFigures> gameFiguresList = new ArrayList<>();
      gameFiguresList.add(gameFigures);
      gameFiguresBean.setGameFigures(gameFiguresList);
      String mes = Agreement.getAgreement().TournamentsAgreement(GsonUtil.getGsonUtil().getgson().toJson(gameFiguresBean));
      SendMessageUntil.toServer(mes);
   }
}
