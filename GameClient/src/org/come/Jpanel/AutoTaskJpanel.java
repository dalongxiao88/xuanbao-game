package org.come.Jpanel;

import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.util.ArrayList;
import org.come.model.ActiveBase;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.util.Arrays;
import org.come.until.UserMessUntil;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.ZhuShouBtn;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class AutoTaskJpanel extends JPanel
{
    private JScrollPane scrollPane;
    private List<ZhuShouTaskJpanel> taskJpanelList;
    private ZhuShouBtn zhiXingBtn;
    private ImageIcon iconBack;
    
    public AutoTaskJpanel() {
        this.setPreferredSize(new Dimension(862, 411));
        this.setOpaque(false);
        this.setLayout(null);
        this.getScrollPane();
        this.getTaskJpanelList();
        this.getZhiXingBtn();
        this.showTask(Arrays.asList(UserMessUntil.getAllVipActive().getBases()));
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBounds(210, 70, 490, 281);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            JPanel jPanel = new JPanel();
            jPanel.setBackground(null);
            jPanel.setOpaque(false);
            jPanel.setBorder(null);
            jPanel.setLayout(null);
            this.scrollPane.setViewportView(jPanel);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void showTask(List<ActiveBase> zhuShouBeans) {
        if (zhuShouBeans == null) {
            zhuShouBeans = new ArrayList<>();
        }
        this.taskJpanelList.clear();
        for (int i = 0; i < zhuShouBeans.size(); ++i) {
            ActiveBase activeBase = (ActiveBase)zhuShouBeans.get(i);
            if (activeBase.getNum() > 0) {
                ZhuShouTaskJpanel zhuShouTaskJpanel = new ZhuShouTaskJpanel(activeBase);
                this.taskJpanelList.add(zhuShouTaskJpanel);
            }
        }
        this.addData();
    }
    
    public void addData() {
        JPanel view = (JPanel)this.scrollPane.getViewport().getView();
        view.removeAll();
        view.repaint();
        int num = 0;
        for (int i = 0; i < this.taskJpanelList.size(); ++i) {
            ZhuShouTaskJpanel zhuShouTaskJpanel = (ZhuShouTaskJpanel)this.taskJpanelList.get(i);
            zhuShouTaskJpanel.setBounds(0, num * 58, 468, 53);
            view.add(zhuShouTaskJpanel);
            ++num;
        }
        view.setPreferredSize(new Dimension(468, num * 58));
        this.scrollPane.updateUI();
        this.scrollPane.invalidate();
        this.scrollPane.validate();
        this.scrollPane.repaint();
    }
    
    public List<ZhuShouTaskJpanel> getTaskJpanelList() {
        if (this.taskJpanelList == null) {
            this.taskJpanelList = new ArrayList<>();
        }
        return this.taskJpanelList;
    }
    
    public ZhuShouBtn getZhiXingBtn() {
        if (this.zhiXingBtn == null) {
            (this.zhiXingBtn = new ZhuShouBtn("inkImg/hongmu1/B458.png", 1, 0, this)).setBounds(580, 355, 101, 30);
            this.add(this.zhiXingBtn);
        }
        return this.zhiXingBtn;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/hongmu1/S341.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 862, 411, this);
    }
    
    public List<ZhuShouTaskJpanel> getAllActive() {
        List<ZhuShouTaskJpanel> zhuShouBeans = new ArrayList<>();
        for (int i = 0; i < this.taskJpanelList.size(); ++i) {
            ZhuShouTaskJpanel zhuShouTaskJpanel = (ZhuShouTaskJpanel)this.taskJpanelList.get(i);
            if (zhuShouTaskJpanel.isSlelct()) {
                zhuShouBeans.add(zhuShouTaskJpanel);
            }
        }
        return zhuShouBeans;
    }
}
