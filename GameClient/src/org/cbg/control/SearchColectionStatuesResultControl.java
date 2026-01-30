package org.cbg.control;

import javax.swing.ImageIcon;
import com.updateNew.MyIsif;
import org.cbg.frame.TrslationMainJframe;
import org.cbg.btn.TrslationBtn;
import org.come.until.GsonUtil;
import org.come.action.FromServerAction;

public class SearchColectionStatuesResultControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Integer colectionStatues = (Integer)GsonUtil.getGsonUtil().getgson().fromJson(mes, Integer.class);
        TrslationBtn shoucang = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                switch ((int)colectionStatues) {
                    case 1: {
                        shoucang.setIcon(new ImageIcon("inkImg/button/73.png"));
                        break;
                    }
                    case 2: {
                        shoucang.setIcon(new ImageIcon("inkImg/button/74.png"));
                        break;
                    }
                    case 3: {
                        shoucang.setIcon(new ImageIcon("inkImg/button/74.png"));
                        break;
                    }
                    case 4: {
                        shoucang.setIcon(new ImageIcon("inkImg/button/73.png"));
                        break;
                    }
                }
            }
            else {
                switch ((int)colectionStatues) {
                    case 1: {
                        shoucang.setIcon(new ImageIcon("img/xy2uiimg/收藏w28px,h28px.png"));
                        break;
                    }
                    case 2: {
                        shoucang.setIcon(new ImageIcon("img/xy2uiimg/收藏(未)w28px,h28px.png"));
                        break;
                    }
                    case 3: {
                        shoucang.setIcon(new ImageIcon("img/xy2uiimg/收藏(未)w28px,h28px.png"));
                        break;
                    }
                    case 4: {
                        shoucang.setIcon(new ImageIcon("img/xy2uiimg/收藏w28px,h28px.png"));
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
