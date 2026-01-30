package org.come.control;

import org.come.tt.LadderJpanel;
import org.come.Jpanel.RankingListJpanel;
import org.come.until.Music;
import org.come.until.FormsManagement;
import com.tool.image.ImageMixDeal;
import java.math.BigDecimal;
import org.come.until.AnalysisString;
import org.come.bean.LoginResult;
import java.util.Vector;
import org.come.tt.LadderJframe;
import org.come.until.GsonUtil;
import org.come.bean.UserRoleArrBean;
import org.come.Frame.RankingListJframe;
import org.come.action.FromServerAction;

public class RankingListControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        int n = -1;
        switch (type.hashCode()) {
            case 1214843986: {
                if (type.equals("pankinglist")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                this.accessPankingList(mes);
                break;
            }
        }
    }
    
    public void accessPankingList(String mes) {
        RankingListJpanel rankingListJpanel = RankingListJframe.getRankingListJframe().getRankingListJpanel();
        for (int size = rankingListJpanel.getTableModel().getRowCount(), j = 0; j < size; ++j) {
            rankingListJpanel.getTableModel().removeRow(0);
        }
        UserRoleArrBean listRoleMes = (UserRoleArrBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, UserRoleArrBean.class);
        if (listRoleMes == null || listRoleMes.getList() == null || listRoleMes.getList().size() == 0) {
            return;
        }
        int index = listRoleMes.getIndex();
        if (index == 8) {
            LadderJpanel ladderJpanel = LadderJframe.getLadderJframe().getLadderJpanel();
            ladderJpanel.setLoginResultList(listRoleMes.getList());
            ladderJpanel.setCurrSeason(listRoleMes.getCurrSeason());
            ladderJpanel.setSeasonInfo(listRoleMes.getSeasonInfo());
            return;
        }
        int ranking = 0;
        for (int i = 0; i < listRoleMes.getList().size(); ++i) {
            Vector<String> veStrings = new Vector<>();
            veStrings.add(i + 1 + "");
            veStrings.add(((LoginResult)listRoleMes.getList().get(i)).getRolename());
            veStrings.add(AnalysisString.lvl((int)((LoginResult)listRoleMes.getList().get(i)).getGrade()) + "级");
            if (((LoginResult)listRoleMes.getList().get(i)).getGold() != null && ((LoginResult)listRoleMes.getList().get(i)).getGold().compareTo(new BigDecimal(0)) > 0) {
                veStrings.add(((LoginResult)listRoleMes.getList().get(i)).getGold().toString());
            }
            if (rankingListJpanel.getListPank().getSelectedIndex() == 6) {
                if (((LoginResult)listRoleMes.getList().get(i)).getHjmax() != null) {
                    int intValue = (int)((LoginResult)listRoleMes.getList().get(i)).getHjmax();
                    int num = 0;
                    int lvl = 0;
                    if (intValue != 0) {
                        num = ((intValue % 6 != 0) ? (intValue / 6 + 1) : (intValue / 6));
                        lvl = ((intValue % 6 == 0) ? 6 : (intValue % 6));
                    }
                    veStrings.add(num + "层" + lvl + "关");
                }
                else {
                    veStrings.add("0层0关");
                }
            }
            if (((LoginResult)listRoleMes.getList().get(i)).getPkrecord() != null) {
                BigDecimal pkrecord = ((LoginResult)listRoleMes.getList().get(i)).getPkrecord();
                String pk = rankingListJpanel.getPk(pkrecord);
                veStrings.add(pk + ((LoginResult)listRoleMes.getList().get(i)).getPkrecord() + "点");
            }
            rankingListJpanel.getTableModel().addRow(veStrings);
            if (((LoginResult)listRoleMes.getList().get(i)).getRolename().equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                ranking = i + 1;
            }
            else {
                rankingListJpanel.getLabtext4().setText(i + 1 + "");
            }
        }
        if (ranking != 0) {
            rankingListJpanel.getLabtext4().setText(ranking + "");
        }
        else {
            rankingListJpanel.getLabtext4().setText("暂时未能上榜，请继续加油");
        }
        if (!RankingListJframe.getRankingListJframe().isVisible()) {
            FormsManagement.showForm(60);
        }
        Music.addyinxiao("开关窗口.mp3");
    }
}
