package org.come.until;

import org.come.Frame.DuelBoardJframe;
import com.tool.time.TimeLimit;
import com.tool.image.ImageMixDeal;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.control.TestMain;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.Jpanel.GameJpanel;
import org.come.Frame.ZhuFrame;

public class ScrenceUntil
{
    public static int Screen_x;
    public static int Screen_y;
    public static int ChatFram_X;
    public static int ChatFram_y;
    public static int SendMsg_x;
    public static int TeamImg_x;
    
    public static void ScreceChange(int type) {
        switch (type) {
            case 0: {
                ScrenceUntil.Screen_x = 800;
                ScrenceUntil.Screen_y = 595;
                ScrenceUntil.ChatFram_y = 600;
                ScrenceUntil.SendMsg_x = ScrenceUntil.Screen_x - 628;
                ScrenceUntil.TeamImg_x = 200;
                ZhuFrame.getZhuJpanel().getjLabelSendMes().setIcon(CutButtonImage.getImage("inkImg/hongmu/s258.png", ScrenceUntil.SendMsg_x, 43));
                ZhuFrame.getZhuJpanel().showHotKey();
                break;
            }
            case 1: {
                ScrenceUntil.Screen_x = 1024;
                ScrenceUntil.Screen_y = 768;
                ScrenceUntil.ChatFram_y = 768;
                ScrenceUntil.SendMsg_x = ScrenceUntil.Screen_x - 628;
                ScrenceUntil.TeamImg_x = 350;
                ZhuFrame.getZhuJpanel().getjLabelSendMes().setIcon(CutButtonImage.getImage("inkImg/hongmu/s258.png", ScrenceUntil.SendMsg_x, 43));
                ZhuFrame.getZhuJpanel().showHotKey();
                break;
            }
            case 2: {
                ScrenceUntil.Screen_x = 1366;
                ScrenceUntil.Screen_y = 768;
                ScrenceUntil.ChatFram_y = 768;
                ScrenceUntil.SendMsg_x = ScrenceUntil.Screen_x - 628;
                ScrenceUntil.TeamImg_x = 450;
                ZhuFrame.getZhuJpanel().getjLabelSendMes().setIcon(CutButtonImage.getImage("inkImg/hongmu/s258.png", ScrenceUntil.SendMsg_x, 43));
                ZhuFrame.getZhuJpanel().showHotKey();
                break;
            }
        }
        change();
    }
    
    public static void change() {
        GameJpanel.getGameJpanel().remove(ZhuFrame.getzhuframe());
        ZhuFrame.getzhuframe().remove(ZhuFrame.getZhuJpanel());
        ZhuFrame.getzhuframe().remove(ZhuFrame.getzhuframe().getChangeJpanel());
        if (FrameMessageChangeJpanel.chatbox.isDisplay()) {
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + 6, ScrenceUntil.Screen_y + 28);
        }
        else if (MyIsif.ifs.equals("D")) {
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y + 28);//加了这里
        }
        else {
            TestMain.gameJframe.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X - 6, ScrenceUntil.Screen_y);
        }
        ZhuFrame.getzhuframe().getChangeJpanel().BtnChange();
        ZhuFrame.getzhuframe().getChangeJpanel().setPreferredSize(new Dimension(ScrenceUntil.ChatFram_X, ScrenceUntil.ChatFram_y));
        ZhuFrame.getZhuJpanel().setPreferredSize(new Dimension(ScrenceUntil.Screen_x, ScrenceUntil.Screen_y));
        ZhuFrame.getZhuJpanel().BtnChange();
        ZhuFrame.getzhuframe().add(ZhuFrame.getZhuJpanel(), "West");
        ZhuFrame.getzhuframe().add(ZhuFrame.getzhuframe().getChangeJpanel(), "East");
        ZhuFrame.getzhuframe().pack();
        ZhuFrame.getzhuframe().setBounds(0, 0, ZhuFrame.getzhuframe().getWidth(), ZhuFrame.getzhuframe().getHeight());
        GameJpanel.getGameJpanel().getLabel().setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y);
        GameJpanel.getGameJpanel().add(ZhuFrame.getzhuframe());
        Util.mapmodel.force(ImageMixDeal.userimg.getRoleShow().getX(), ImageMixDeal.userimg.getRoleShow().getY());
        if (TimeLimit.timeLimit != null) {
            TimeLimit.getLimits().Sorting();
        }
        if (FormsManagement.getInternalForm2(9) != null && FormsManagement.getframe(9).isVisible()) {
            if (DuelBoardJframe.getDuelBoardJframe().getDuelBoardJpanel().isShowType()) {
                FormsManagement.getframe(9).setBounds(ScrenceUntil.Screen_x - 210, 215, FormsManagement.getframe(9).getWidth(), FormsManagement.getframe(9).getHeight());
            }
            else {
                FormsManagement.getframe(9).setBounds(ScrenceUntil.Screen_x - 18, 215, FormsManagement.getframe(9).getWidth(), FormsManagement.getframe(9).getHeight());
            }
        }
    }
    
    static {
        ScrenceUntil.Screen_x = 800;
        ScrenceUntil.Screen_y = 600;
        ScrenceUntil.ChatFram_X = 275;
        ScrenceUntil.ChatFram_y = 600;
        ScrenceUntil.SendMsg_x = 400;
        ScrenceUntil.TeamImg_x = 200;
    }
}
