package org.come.annex.Tournaments.Other;

import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.action.FromServerAction;
import org.come.annex.Tournaments.Frame.TournamentsJframe;
import org.come.annex.Tournaments.Frame.TournamentsResultsJframe;
import org.come.annex.Tournaments.Frame.TournamentsTheJframe;
import org.come.annex.Tournaments.Jpanel.TournamentsResultsJpanel;
import org.come.annex.Tournaments.Jpanel.TournamentsTheJpanel;
import org.come.bean.LoginResult;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;

public class TournamentsControl implements FromServerAction {
   @Override
   public void controlMessFromServer(String mes, String type) {
      GameFiguresBean gameFiguresBean = GsonUtil.getGsonUtil().getgson().fromJson(mes, GameFiguresBean.class);
      LoginResult roleInfo = RoleData.getRoleData().getLoginResult();
      if (gameFiguresBean != null) {
         if (gameFiguresBean.getTotal() == 1) {
            this.initialize(roleInfo, gameFiguresBean, 1);
         } else if (gameFiguresBean.getTotal() == 2) {
            this.initialize(roleInfo, gameFiguresBean, 2);
         } else if (gameFiguresBean.getTotal() != 5) {
            if (gameFiguresBean.getTotal() == 6) {
               FormsManagement.showForm(1146);
               this.initialize(roleInfo, gameFiguresBean, 2);
            } else if (gameFiguresBean.getTotal() == 20) {
               this.matchInformation(roleInfo, gameFiguresBean, 0);
            } else if (gameFiguresBean.getTotal() == 21) {
               this.matchInformation(roleInfo, gameFiguresBean, 1);
            } else if (gameFiguresBean.getTotal() == 22) {
               this.matchInformation(roleInfo, gameFiguresBean, 2);
            } else if (gameFiguresBean.getTotal() == 23) {
               if (gameFiguresBean.getMsg() != null) {
                  ZhuFrame.getZhuJpanel().addPrompt2(gameFiguresBean.getMsg() + " 取消匹配，请重新匹配");
               }

               this.matchInformation(roleInfo, gameFiguresBean, 3);
            } else if (gameFiguresBean.getTotal() == 25) {
               this.battleResults(gameFiguresBean, 1);
            } else if (gameFiguresBean.getTotal() == 26) {
               this.battleResults(gameFiguresBean, 2);
            } else if (gameFiguresBean.getTotal() == 27) {
               if (gameFiguresBean.getMsg() != null) {
                  ZhuFrame.getZhuJpanel().addPrompt2(gameFiguresBean.getMsg());
               }

               this.matchInformation(roleInfo, gameFiguresBean, 3);
            } else if (gameFiguresBean.getTotal() == 98) {
               TournamentsJframe.getTournamentsJpanel().getTournamentsCardJpanel().getTournamentsScreen3Jpanel().setReceiptData(gameFiguresBean.getMsg());
            } else if (gameFiguresBean.getTotal() == 99) {
               TournamentsJframe.getTournamentsJpanel().getTournamentsCardJpanel().getTournamentsScreen4Jpanel().setReceiptData(gameFiguresBean.getMsg());
            }
         }
      }
   }

   public void battleResults(GameFiguresBean gameFiguresBean, int kind) {
      if (FormsManagement.getframe(1147).isVisible()) {
         FormsManagement.HideForm(1147);
      }

      FormsManagement.showForm(1148);
      TournamentsResultsJpanel tournamentsResultsJpanel = TournamentsResultsJframe.getTournamentsResultsJpanel();
      tournamentsResultsJpanel.gameFiguresList.clear();
      tournamentsResultsJpanel.setAcceptanceOfData(gameFiguresBean.getGameFigures(), kind);
   }

   public void initialize(LoginResult roleInfo, GameFiguresBean gameFiguresBean, int kind) {
      TournamentsJframe.getTournamentsJpanel().getTournamentsCardJpanel().getTournamentsScreen2Jpanel().gameFiguresList.clear();
      if (kind != 1 && kind == 2) {
         TournamentsJframe.getTournamentsJpanel().getTournamentsCardJpanel().getTournamentsScreen2Jpanel().gameFiguresList = gameFiguresBean.getGameFigures();
      }
   }

   public void matchInformation(LoginResult roleInfo, GameFiguresBean gameFiguresBean, int kind) {
      if (FormsManagement.getframe(1148).isVisible()) {
         FormsManagement.HideForm(1148);
      }

      FormsManagement.showForm(1147);
      TournamentsTheJpanel tournamentsTheJpanel = TournamentsTheJframe.getTournamentsTheJpanel();
      tournamentsTheJpanel.gameFiguresList.clear();
      tournamentsTheJpanel.setAcceptanceOfData(gameFiguresBean.getGameFigures(), kind);
   }
}
