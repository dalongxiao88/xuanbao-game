package org.come.control;

import org.come.Frame.Lottery1Frame;
import org.come.Frame.LotteryFrame;
import org.come.Jpanel.AthChartJPanel;
import org.come.Jpanel.TrueFeedbackLotteyJPanel;
import org.come.Jpanel.TrueFeedbackMainJpanel;
import org.come.Frame.AthChartJframe;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.Frame.TrueFeedbackMainJframe;
import org.come.until.GsonUtil;
import org.come.bean.LaborRank;
import org.come.action.FromServerAction;

public class LaborControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        LaborRank laborRank = (LaborRank)GsonUtil.getGsonUtil().getgson().fromJson(mes, LaborRank.class);
        int type2 = laborRank.getType();
        if (type2 <= 2) {
            TrueFeedbackMainJpanel trueFeedbackMainJpanel = TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel();
            trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().showViewData(laborRank);
            if (FormsManagement.getInternalForm2(109) == null) {
                FormsManagement.showForm(109);
            }
            else if (!FormsManagement.getframe(109).isVisible()) {
                FormsManagement.showForm(109);
            }
            try {
                if (type2 == 0) {
                    trueFeedbackMainJpanel.getBtnAddRecharge().setIcons(CutButtonImage.cuts("inkImg/button/B318.png"));
                    trueFeedbackMainJpanel.getBtntimeSummon().setIcons(CutButtonImage.cuts("inkImg/button/B321.png"));
                    trueFeedbackMainJpanel.getBtnLottey().setIcons(CutButtonImage.cuts("inkImg/button/B319.png"));
                    trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().changeShowView(type2);
                }
                else if (type2 == 1) {
                    trueFeedbackMainJpanel.getBtnAddRecharge().setIcons(CutButtonImage.cuts("inkImg/button/B317.png"));
                    trueFeedbackMainJpanel.getBtntimeSummon().setIcons(CutButtonImage.cuts("inkImg/button/B322.png"));
                    trueFeedbackMainJpanel.getBtnLottey().setIcons(CutButtonImage.cuts("inkImg/button/B319.png"));
                    trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().changeShowView(type2);
                }
                else if (type2 == 2) {
                    trueFeedbackMainJpanel.getBtnAddRecharge().setIcons(CutButtonImage.cuts("inkImg/button/B317.png"));
                    trueFeedbackMainJpanel.getBtntimeSummon().setIcons(CutButtonImage.cuts("inkImg/button/B321.png"));
                    trueFeedbackMainJpanel.getBtnLottey().setIcons(CutButtonImage.cuts("inkImg/button/B320.png"));
                    trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().changeShowView(type2);
                }
            }
            catch (Exception ex) {}
        }
        else if (type2 != 10 && type2 != 11) {
            if (type2 == 12) {
                TrueFeedbackLotteyJPanel trueFeedbackLotteyJPanel = TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel().getTrueFeedbackCardJPanel().getTrueFeedbackLotteyJPanel();
                trueFeedbackLotteyJPanel.lotteyGoods(laborRank);
            }
            else if (type2 == 13) {
//                AthChartJPanel athChartJPanel = AthChartJframe.getAthChartJPanel();
//                athChartJPanel.lotteyGoods(laborRank);
//                AthChartJframe.getAthChartJPanel().showViewData(laborRank);

                LotteryFrame lotteryFrame = LotteryFrame.getLotteryFrame();
                lotteryFrame.getLotteryJpanel().showViewData(laborRank);
                if (!lotteryFrame.isVisible()) {
                    FormsManagement.showForm(3004);
                }
            }
            else if (type2 == 113) {
//                AthChartJPanel athChartJPanel = AthChartJframe.getAthChartJPanel();
//                athChartJPanel.lotteyGoods(laborRank);
//                AthChartJframe.getAthChartJPanel().showViewData(laborRank);

                Lottery1Frame lottery1Frame = Lottery1Frame.getLottery1Frame();
                laborRank.setType(13);
                lottery1Frame.getLotteryJpanel().showViewData(laborRank);
                if (!lottery1Frame.isVisible()) {
                    FormsManagement.showForm(3005);
                }
            }
        }
    }
}
