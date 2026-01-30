package org.come.Jpanel.spot;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.List;
import com.tool.Stall.Stall;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.JLabel;
import com.tool.tcpimg.RichLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class RecordBoxJpanel extends JPanel
{
    private final JScrollPane jScrollPane1;
    private final JScrollPane jScrollPane2;
    private final RichLabel richLabel1;
    private final RichLabel richLabel2;
    private final JLabel jLabel1;
    private final JLabel jLabel2;
    private int size;
    
    public RecordBoxJpanel() {
        this.setLayout(null);
        this.setOpaque(false);
        (this.jLabel1 = new JLabel("暂无收购记录", 0)).setFont(UIUtils.TEXT_FONT1);
        this.jLabel1.setForeground(new Color(171, 167, 156));
        this.add(this.jLabel1);
        (this.jLabel2 = new JLabel("暂无收购记录", 0)).setFont(UIUtils.TEXT_FONT1);
        this.jLabel2.setForeground(new Color(171, 167, 156));
        this.add(this.jLabel2);
        this.richLabel1 = new RichLabel("", UIUtils.TEXT_FONT1);
        (this.jScrollPane1 = new JScrollPane(this.richLabel1)).setVerticalScrollBarPolicy(22);
        this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane1.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane1.getViewport().setOpaque(false);
        this.jScrollPane1.setOpaque(false);
        this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane1.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane1);
        this.richLabel2 = new RichLabel("", UIUtils.TEXT_FONT1);
        (this.jScrollPane2 = new JScrollPane(this.richLabel2)).setVerticalScrollBarPolicy(22);
        this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane2.getViewport().setOpaque(false);
        this.jScrollPane2.setOpaque(false);
        this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane2);
    }
    
    public void setBounds(int x, int y, int width, int height, int interval) {
        this.setBounds(x, y, width * 2 + interval, height);
        this.jScrollPane1.setBounds(0, 0, width, height);
        this.jScrollPane2.setBounds(width + interval, 0, width, height);
        this.jLabel1.setBounds(this.jScrollPane1.getX() + width / 2 - 65, this.jScrollPane1.getY() + height / 2 - 16, 120, 18);
        this.jLabel2.setBounds(this.jScrollPane2.getX() + width / 2 - 65, this.jScrollPane2.getY() + height / 2 - 16, 120, 18);
        this.size = width - 20;
    }
    
    public void setRecordInfo(Stall stall) {
        List<String> sellMsg = stall.getSellMsgs();
        this.richLabel1.setText(this.getInfoToString(sellMsg));
        List<String> collectMsg = stall.getCollectMsg();
        this.richLabel2.setText(this.getInfoToString(collectMsg));
        this.jLabel1.setVisible(sellMsg == null || sellMsg.size() <= 0);
        this.jLabel2.setVisible(collectMsg == null || collectMsg.size() <= 0);
        Dimension dimension1 = this.richLabel1.computeSize(this.size);
        this.richLabel1.setSize(dimension1);
        this.richLabel1.setPreferredSize(dimension1);
        Dimension dimension2 = this.richLabel2.computeSize(this.size);
        this.richLabel2.setSize(dimension2);
        this.richLabel2.setPreferredSize(dimension2);
    }
    
    private String getInfoToString(List<String> infos) {
        StringBuffer buffer = new StringBuffer();
        if (infos != null) {
            for (int i = 0; i < infos.size(); ++i) {
                if (i > 0) {
                    buffer.append("#r");
                }
                buffer.append((String)infos.get(i));
            }
        }
        return buffer.toString();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF));
    }
}
