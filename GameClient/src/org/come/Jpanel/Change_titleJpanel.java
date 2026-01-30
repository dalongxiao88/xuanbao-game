package org.come.Jpanel;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import org.come.until.Util;
import java.util.Calendar;
import org.come.entity.Titletable;
import java.awt.Graphics;
import org.cbg.until.TraslationDemoScrollBarUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.model.Title;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.Color;
import org.come.until.UserMessUntil;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import org.come.Frame.Change_titleJframe;
import com.tool.btn.RoleOperationPanelBtn;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import com.tool.time.UpdataLimitTimerTask;
import javax.swing.JLabel;
import com.tool.time.TitleTimerTask;
import java.util.HashMap;
import java.util.Map;
import org.come.bean.TitleBean;
import javax.swing.JPanel;

public class Change_titleJpanel extends JPanel
{
    public static TitleBean titleBean;
    public static Map<String, String> titleColors;
    public static HashMap<String, TitleTimerTask> taskMap;
    private JLabel residueTime;
    private UpdataLimitTimerTask updataLimitTimerTask;
    private JLabel labnamech;
    private JScrollPane jScrollPane;
    private JList<String> listname;
    private DefaultListModel<String> modelname;
    private RoleOperationPanelBtn btnsure;
    private RoleOperationPanelBtn btnhide;
    private Change_titleJframe change_titleJframe;
    private JTextArea textArea;
    private ImageIcon icon;
    
    public Change_titleJpanel(Change_titleJframe change_titleJframe) throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.change_titleJframe = change_titleJframe;
            this.setPreferredSize(new Dimension(288, 398));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 10);
            offBtn.setBounds(251, 10, 25, 25);
            this.add(offBtn);
            this.modelname = new DefaultListModel<>();
            this.listname = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            };
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Color color = UserMessUntil.getTitleColorsByName((String)value);
                    if (color != null) {
                        this.setForeground(color);
                    }
                    return this;
                }
            };
            this.listname.setCellRenderer(cellRenderer);
            this.listname.setSelectionBackground(new Color(33, 42, 52));
            this.listname.setSelectionForeground(Color.CYAN);
            this.listname.setForeground(Color.CYAN);
            this.listname.setFont(new Font("微软雅黑", 1, 14));
            this.listname.setOpaque(false);
            this.listname.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String selectedValue = (String)Change_titleJpanel.this.listname.getSelectedValue();
                    if (selectedValue != null) {
                        Change_titleJpanel.this.textArea.setText("");
                        Title title = UserMessUntil.getTitle(selectedValue);
                        if (title == null) {
                            return;
                        }
                        if (title.getText() != null) {
                            Change_titleJpanel.this.textArea.append(title.getText() + "\r\n");
                        }
                        if (title.getValue() != null) {
                            Change_titleJpanel.this.textArea.append(title.getValue().replace("\\|", ","));
                        }
                        Change_titleJpanel.this.showCountdown(selectedValue, Change_titleJpanel.this.residueTime);
                    }
                }
            });
            this.listname.setBackground(new Color(0, 0, 0, 0));
            this.listname.setModel(this.modelname);
            (this.jScrollPane = new JScrollPane(this.listname)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(58, 78, 204, 130);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.labnamech = new JLabel()).setBounds(130, 33, 128, 16);
            this.labnamech.setForeground(Color.CYAN);
            this.labnamech.setFont(new Font("微软雅黑", 0, 12));
            this.add(this.labnamech);
            (this.residueTime = new JLabel()).setBounds(52, 320, 177, 16);
            this.residueTime.setForeground(Color.MAGENTA);
            this.residueTime.setFont(new Font("微软雅黑", 0, 12));
            this.add(this.residueTime);
            (this.btnsure = new RoleOperationPanelBtn("inkImg/button1/B22.png", 1, "更改称谓", this, null)).setBounds(45, 356, 99, 24);
            this.add(this.btnsure);
            (this.btnhide = new RoleOperationPanelBtn("inkImg/button1/B22.png", 1, "隐藏称谓", this, null)).setBounds(164, 356, 99, 24);
            this.add(this.btnhide);
            (this.textArea = new JTextArea()).setBounds(56, 210, 202, 100);
            this.textArea.setEditable(false);
            this.textArea.setLineWrap(true);
            this.textArea.setOpaque(false);
            this.textArea.setForeground(Color.white);
            this.add(this.textArea);
        }
        else {
            this.change_titleJframe = change_titleJframe;
            this.setPreferredSize(new Dimension(266, 417));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 10);
            offBtn.setBounds(241, 0, 25, 25);
            this.add(offBtn);
            this.modelname = new DefaultListModel<>();
            this.listname = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            };
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Color color = UserMessUntil.getTitleColorsByName((String)value);
                    if (color != null) {
                        this.setForeground(color);
                    }
                    return this;
                }
            };
            this.listname.setCellRenderer(cellRenderer);
            this.listname.setSelectionBackground(new Color(33, 42, 52));
            this.listname.setSelectionForeground(Color.CYAN);
            this.listname.setForeground(Color.CYAN);
            this.listname.setFont(new Font("微软雅黑", 1, 14));
            this.listname.setOpaque(false);
            this.listname.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String selectedValue = (String)Change_titleJpanel.this.listname.getSelectedValue();
                    if (selectedValue != null) {
                        Change_titleJpanel.this.textArea.setText("");
                        Title title = UserMessUntil.getTitle(selectedValue);
                        if (title == null) {
                            if (Change_titleJpanel.this.updataLimitTimerTask != null) {
                                Change_titleJpanel.this.updataLimitTimerTask.setIsrun(false);
                            }
                            return;
                        }
                        else {
                            if (title.getText() != null) {
                                Change_titleJpanel.this.textArea.append(title.getText() + "\r\n");
                            }
                            if (title.getValue() != null) {
                                Change_titleJpanel.this.textArea.append(title.getValue().replace("\\|", ","));
                            }
                            Change_titleJpanel.this.showCountdown(selectedValue, Change_titleJpanel.this.residueTime);
                        }
                    }
                }
            });
            this.listname.setBackground(new Color(0, 0, 0, 0));
            this.listname.setModel(this.modelname);
            (this.jScrollPane = new JScrollPane(this.listname)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new TraslationDemoScrollBarUI(this.jScrollPane));
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(29, 88, 204, 129);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.labnamech = new JLabel()).setBounds(102, 47, 128, 16);
            this.labnamech.setForeground(Color.CYAN);
            this.labnamech.setFont(new Font("微软雅黑", 0, 12));
            this.add(this.labnamech);
            (this.residueTime = new JLabel()).setBounds(30, 325, 177, 16);
            this.residueTime.setForeground(Color.CYAN);
            this.residueTime.setFont(new Font("微软雅黑", 0, 12));
            this.add(this.residueTime);
            (this.btnsure = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "更改称谓", this)).setBounds(38, 346, 80, 26);
            this.add(this.btnsure);
            (this.btnhide = new RoleOperationPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "隐藏称谓", this)).setBounds(138, 346, 80, 26);
            this.add(this.btnhide);
            (this.textArea = new JTextArea()).setBounds(29, 219, 204, 107);
            this.textArea.setEditable(false);
            this.textArea.setLineWrap(true);
            this.textArea.setOpaque(false);
            this.textArea.setForeground(Color.white);
            this.add(this.textArea);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B232.png");
            g.drawImage(this.icon.getImage(), 0, 0, 288, 398, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/更改称谓_w266,h417.png");
            g.drawImage(this.icon.getImage(), 0, 0, 266, 417, this);
        }
    }
    
    public void showCountdown(String selectedValue, JLabel residueTime) {
        List<Titletable> titletables = Change_titleJpanel.titleBean.getTitletables();
        if (Change_titleJpanel.taskMap.get(selectedValue) != null) {
            if (this.updataLimitTimerTask != null) {
                this.updataLimitTimerTask.setIsrun(true);
                this.updataLimitTimerTask.setTitleTimerTask((TitleTimerTask)Change_titleJpanel.taskMap.get(selectedValue));
            }
        }
        else {
            List<Titletable> titletables2 = Change_titleJpanel.titleBean.getTitletables();
            if (titletables != null && titletables.size() > 0) {
                for (Titletable titletable : titletables) {
                    if (titletable.getTitlename() != null && titletable.getTitlename().equals(selectedValue)) {
                        int addTime = titletable.getLimitTime();
                        if (addTime >= 0) {
                            Date recordTime = titletable.getRecordTime();
                            Calendar c = Calendar.getInstance();
                            c.setTime(recordTime);
                            c.add(12, addTime);
                            long endTime = c.getTimeInMillis();
                            long startTime = Util.getTime();
                            long midTime = (endTime - startTime) / 1000L;
                            TitleTimerTask titleTimerTask = new TitleTimerTask(midTime, selectedValue);
                            Timer timer = new Timer();
                            timer.schedule(titleTimerTask, 0L);
                            Change_titleJpanel.taskMap.put(selectedValue, titleTimerTask);
                            if (this.updataLimitTimerTask != null) {
                                this.updataLimitTimerTask.setIsrun(true);
                                this.updataLimitTimerTask.setTitleTimerTask(titleTimerTask);
                            }
                            else {
                                this.updataLimitTimerTask = new UpdataLimitTimerTask(this.residueTime, titleTimerTask);
                                Timer timer2 = new Timer();
                                timer2.schedule(this.updataLimitTimerTask, 0L);
                            }
                        }
                        else if (this.updataLimitTimerTask != null) {
                            this.updataLimitTimerTask.setIsrun(false);
                        }
                        else {
                            continue;
                        }
                    }
                }
                residueTime.setText("");
            }
        }
    }
    
    public JLabel getLabnamech() {
        return this.labnamech;
    }
    
    public void setLabnamech(JLabel labnamech) {
        this.labnamech = labnamech;
    }
    
    public JLabel getResidueTime() {
        return this.residueTime;
    }
    
    public void setResidueTime(JLabel residueTime) {
        this.residueTime = residueTime;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public JList<String> getListname() {
        return this.listname;
    }
    
    public void setListname(JList<String> listname) {
        this.listname = listname;
    }
    
    public DefaultListModel<String> getModelname() {
        return this.modelname;
    }
    
    public void setModelname(DefaultListModel<String> modelname) {
        this.modelname = modelname;
    }
    
    public RoleOperationPanelBtn getBtnsure() {
        return this.btnsure;
    }
    
    public void setBtnsure(RoleOperationPanelBtn btnsure) {
        this.btnsure = btnsure;
    }
    
    public RoleOperationPanelBtn getBtnhide() {
        return this.btnhide;
    }
    
    public void setBtnhide(RoleOperationPanelBtn btnhide) {
        this.btnhide = btnhide;
    }
    
    public Change_titleJframe getChange_titleJframe() {
        return this.change_titleJframe;
    }
    
    public void setChange_titleJframe(Change_titleJframe change_titleJframe) {
        this.change_titleJframe = change_titleJframe;
    }
    
    static {
        Change_titleJpanel.titleBean = new TitleBean();
        Change_titleJpanel.titleColors = new HashMap<>();
        Change_titleJpanel.taskMap = new HashMap<>();
    }
}
