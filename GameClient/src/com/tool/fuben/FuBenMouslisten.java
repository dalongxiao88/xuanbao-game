package com.tool.fuben;

import com.tool.tcp.SpriteFactory;
import org.come.until.UserMessUntil;
import org.come.until.Util;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class FuBenMouslisten implements MouseListener, MouseMotionListener {
    private JList<String> listfuben;// 副本列表选中框

    private FuBenJpanel fubenJpanel;//



    public FuBenMouslisten(JList<String> listpet, FuBenJpanel fubenJpanel) {
        this.listfuben = listpet;
        this.fubenJpanel = fubenJpanel;
    }




    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        String name = listfuben.getSelectedValue();
        FuBenJpanel.index = listfuben.getSelectedIndex();
        System.out.println("选中的副本:" + name);
        TaskList taskList = UserMessUntil.getTaskListAll().getTaskByName(name);
        int n = Util.random.nextInt(8)+1;
        FuBenJframe.getFuBenJframe().getFuBenJpane().part= SpriteFactory.VloadSprite("img/fb/was/凤凰"+n+".tcp", null);
        FuBenJframe.getFuBenJframe().getFuBenJpane().UpShow(taskList.getTaskID(), taskList.getDrops(), taskList.getRole(), taskList.getLvl(), taskList.getNum() + "", taskList.getResetcycle() + "",  taskList.getHard(), taskList.getTime(),taskList.getResetcycle());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}