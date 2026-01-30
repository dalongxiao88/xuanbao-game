package org.come.annex.Tournaments.Jpanel;

import com.tool.tcpimg.UIUtils;
import org.come.action.IconButtonEffect;
import org.come.annex.Tournaments.Btn.TournamentsBtn;
import org.come.annex.Tournaments.Other.*;
import org.come.until.CutButtonImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class TournamentsScreen2Jpanel extends JPanel {
   public TournamentsBtn outlinedBtn;
   public TournamentsCardJpanel tournamentsCardJpanel;
   public JButton[] filterButtons = new JButton[5];
   public String[] buttonTexts1 = new String[]{"第一轮积分赛", "第二轮积分赛", "淘汰赛", "季军赛", "总决赛"};
   public String[] buttonTexts2 = new String[5];
   public int currentIndex = 0;
   public TournamentsData Data = new TournamentsData();
   private JList<GameFigures> listPankList;
   private JScrollPane scrollPaneTrans;
   private DefaultListModel<GameFigures> listModel;
   public List<JLabel> detailsList = new ArrayList<>();
   public List<GameFigures> gameFiguresList;
   public SetupJpanel5 optionPanels;
   public JLabel textLabels;
   public State arrowButtons;
   public int flags;
   public int page = -1;
   private int currentPage = 0;
   public JLabel[] avatar = new JLabel[10];
   public JLabel[] name = new JLabel[10];
   public boolean isSelected = false;

   public TournamentsScreen2Jpanel(TournamentsCardJpanel tournamentsCardJpanel) {
      this.tournamentsCardJpanel = tournamentsCardJpanel;
      this.setPreferredSize(new Dimension(540, 405));
      this.setOpaque(false);
      this.setLayout(null);
      this.gameFiguresList = new ArrayList<>();
      this.outlinedBtn = new TournamentsBtn("inkImg/danxin/p/e2.png", 1, UIUtils.COLOR_NAVIGATIONAJ, "赛事简述", UIUtils.TEXT_FONT, 20, this);
      this.outlinedBtn.setBounds(551, 78, 81, 23);
      this.add(this.outlinedBtn);
      this.outlinedBtn = new TournamentsBtn("inkImg/danxin/p/e6.png", 1, UIUtils.COLOR_NAVIGATIONAJ, "争霸赛", UIUtils.TEXT_HY19, 21, this);
      this.outlinedBtn.setBounds(34, 35, 128, 29);
      this.add(this.outlinedBtn);
      this.createSetupPanel(33, 137, 130);
      this.add(this.getScrollPaneTrans());
      this.initializeButtonTexts();
      this.createFilterButtons();
      this.initializeDefaultButton();
   }

   private void initializeButtonTexts() {
      this.buttonTexts2[0] = this.Data.getDateRange(this.Data.StartTime1, this.Data.EndTime1);
      this.buttonTexts2[1] = this.Data.getDateRange(this.Data.StartTime2, this.Data.EndTime2);
      this.buttonTexts2[2] = this.Data.getKnockoutDateRange();
      this.buttonTexts2[3] = this.Data.formatDate(this.Data.StartTime4, 1);
      this.buttonTexts2[4] = this.Data.formatDate(this.Data.StartTime5, 1);
   }

   private void createFilterButtons() {
      for (int i = 0; i < this.filterButtons.length; i++) {
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(null);
         buttonPanel.setOpaque(false);
         buttonPanel.setBackground(null);
         buttonPanel.setBounds(28 + i * 100, 73, 120, 36);
         JButton button = new JButton();
         button.setIcon(new ImageIcon(new ImageIcon("inkImg/danxin/p/f2.png").getImage().getScaledInstance(120, 36, 4)));
         button.setBorderPainted(false);
         button.setContentAreaFilled(false);
         button.setFocusPainted(false);
         button.addActionListener(new FilterButtonActionListener(i, this));
         button.addMouseListener(new CustomMouseListener());
         button.setBounds(0, 0, 120, 36);
         JLabel label1 = new JLabel(this.buttonTexts1[i], 0);
         label1.setForeground(new Color(16, 24, 24, 255));
         label1.setFont(UIUtils.TEXT_FONT_BOLD);
         label1.setBounds(10, 0, 100, 18);
         JLabel label2 = new JLabel(this.buttonTexts2[i], 0);
         label2.setForeground(new Color(16, 24, 24, 255));
         label2.setFont(UIUtils.TEXT_FONT);
         label2.setBounds(10, 16, 100, 18);
         buttonPanel.add(label1);
         buttonPanel.add(label2);
         buttonPanel.add(button);
         this.add(buttonPanel);
         this.filterButtons[i] = button;
      }
   }

   public void initializeDefaultButton() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
      String currentTime = sdf.format(new Date());
      if (currentTime.compareTo(this.Data.StartTime1) < 0) {
         this.setSelectedButton(0);
      } else if (currentTime.compareTo(this.Data.StartTime5) > 0) {
         this.setSelectedButton(4);
      } else if (currentTime.compareTo(this.Data.StartTime4) > 0) {
         this.setSelectedButton(3);
      } else {
         for (int i = 0; i < this.filterButtons.length; i++) {
            if (currentTime.compareTo(this.Data.getStartTime(i)) >= 0 && currentTime.compareTo(this.Data.getEndTime(i)) <= 0) {
               this.setSelectedButton(i);
               break;
            }
         }
      }
   }

   public void setSelectedButton(int index) {
      this.currentIndex = index;
      this.optionPanels.setVisible(false);
      this.textLabels.setVisible(false);
      this.arrowButtons.setVisible(false);
      this.isSelected = false;
      this.flags = 1;

      for (JLabel detailLabel : this.detailsList) {
         this.remove(detailLabel);
      }

      this.removeExistingPanels();
      this.detailsList.clear();
      this.updatedData(index);
      if (index == 0) {
         this.Data.titleBar(this);
         this.scrollPaneTrans.setVisible(true);
      } else if (index == 1) {
         this.Data.titleBar(this);
         if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.StartTime2) < 0) {
            this.scrollPaneTrans.setVisible(false);
            this.Data.details(258, 275, 158, 15, this.Data.formatDate(this.Data.StartTime2, 2) + "开打", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, this);
            this.Data.details(258, 292, 158, 15, "每次结束后更新对阵情况", new Color(36, 62, 100), UIUtils.TEXT_FONT, 0, this);
         } else {
            this.scrollPaneTrans.setVisible(true);
         }
      } else if (index == 2) {
         this.scrollPaneTrans.setVisible(false);
         this.optionPanels.setVisible(false);
         this.textLabels.setVisible(true);
         this.arrowButtons.setVisible(true);
         this.flags = 1;
         this.playoffs();
      } else if (index == 3) {
         this.scrollPaneTrans.setVisible(false);
         this.Data.promptInformation(this.Data.StartTime4, this.Data.EndTime3D, this);
         this.isSelected = true;
         this.theFinalGame();
      } else if (index == 4) {
         this.scrollPaneTrans.setVisible(false);
         this.Data.promptInformation(this.Data.StartTime5, this.Data.EndTime3D, this);
         this.isSelected = true;
         this.theFinalGame();
      }

      for (int i = 0; i < this.filterButtons.length; i++) {
         JLabel label1 = (JLabel)this.filterButtons[i].getParent().getComponent(0);
         JLabel label2 = (JLabel)this.filterButtons[i].getParent().getComponent(1);
         if (i == index) {
            this.filterButtons[i].setIcon(new ImageIcon(new ImageIcon("inkImg/danxin/p/f3.png").getImage().getScaledInstance(120, 36, 4)));
            label1.setForeground(new Color(255, 255, 255, 255));
            label2.setForeground(new Color(255, 255, 255, 255));
         } else {
            this.filterButtons[i].setIcon(new ImageIcon(new ImageIcon("inkImg/danxin/p/f2.png").getImage().getScaledInstance(120, 36, 4)));
            label1.setForeground(new Color(16, 24, 24, 255));
            label2.setForeground(new Color(16, 24, 24, 255));
         }
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(this.Data.icon.getImage(), 26, 65, 616, 382, this);
      Graphics2D g2d = (Graphics2D)g;
      if (this.currentIndex == 4) {
         g.drawImage(this.Data.icon1.getImage(), 26, 113, 616, 333, this);
         if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.StartTime5) < 0) {
            GradientRectangle gradientRectangle = new GradientRectangle(258, 295, 158, 34, 130);
            gradientRectangle.draw(g2d);
         }
      } else if (this.currentIndex == 3) {
         g.drawImage(this.Data.icon2.getImage(), 26, 113, 616, 333, this);
         if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.StartTime4) < 0) {
            GradientRectangle gradientRectangle = new GradientRectangle(258, 295, 158, 34, 130);
            gradientRectangle.draw(g2d);
         }
      } else if (this.currentIndex == 2) {
         g.drawImage(this.Data.icon3.getImage(), 26, 113, 616, 29, this);
         if (this.currentIndex == 2) {
            g.drawImage(this.Data.icon5.getImage(), 33, 116, 242, 22, this);
         }
      } else {
         g.drawImage(this.Data.icon3.getImage(), 26, 113, 616, 29, this);
         g.drawImage(this.Data.icon4.getImage(), 26, 148, 616, 29, this);
         if (this.currentIndex == 2) {
            g.drawImage(this.Data.icon5.getImage(), 33, 116, 242, 22, this);
         }
      }

      if (this.page == 32 && this.currentIndex == 2) {
         if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime2) > 0) {
            for (int i = 0; i < 4; i++) {
               g.drawImage(this.Data.icon6.getImage(), 45, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 195, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 360, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 510, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon7.getImage(), 158, 165 + i * 75, 36, 36, this);
               g.drawImage(this.Data.icon7.getImage(), 473, 165 + i * 75, 36, 36, this);
            }
         }
      } else if (this.page == 16 && this.currentIndex == 2) {
         if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3A) > 0) {
            for (int i = 0; i < 4; i++) {
               g.drawImage(this.Data.icon6.getImage(), 45, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 195, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 360, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 510, 153 + i * 75, 110, 60, this);
               g.drawImage(this.Data.icon7.getImage(), 158, 165 + i * 75, 36, 36, this);
               g.drawImage(this.Data.icon7.getImage(), 473, 165 + i * 75, 36, 36, this);
            }
         }
      } else if (this.page == 8 && this.currentIndex == 2) {
         if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3B) > 0) {
            for (int i = 0; i < 2; i++) {
               g.drawImage(this.Data.icon6.getImage(), 45, 195 + i * 130, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 195, 195 + i * 130, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 360, 195 + i * 130, 110, 60, this);
               g.drawImage(this.Data.icon6.getImage(), 510, 195 + i * 130, 110, 60, this);
               g.drawImage(this.Data.icon7.getImage(), 158, 207 + i * 130, 36, 36, this);
               g.drawImage(this.Data.icon7.getImage(), 473, 207 + i * 130, 36, 36, this);
            }
         }
      } else if (this.page == 4 && this.currentIndex == 2 && new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3C) > 0) {
         for (int i = 0; i < 2; i++) {
            g.drawImage(this.Data.icon8.getImage(), 145, 195 + i * 130, 150, 60, this);
            g.drawImage(this.Data.icon8.getImage(), 370, 195 + i * 130, 150, 60, this);
            g.drawImage(this.Data.icon7.getImage(), 315, 207 + i * 130, 36, 36, this);
         }
      }

      if (this.isSelected
         && (this.currentIndex == 3 || this.currentIndex == 4)
         && new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3D) > 0) {
         if (this.Data.mode != 1) {
            if (this.Data.mode == 2) {
               for (int i = 0; i < 3; i++) {
                  g.drawImage(this.Data.icon10.getImage(), 52 + i * 78, 233, 60, 60, this);
                  g.drawImage(this.Data.icon10.getImage(), 403 + i * 78, 233, 60, 60, this);
               }
            } else if (this.Data.mode == 3) {
               for (int i = 0; i < 2; i++) {
                  g.drawImage(this.Data.icon10.getImage(), 150 + i * 311, 233, 60, 60, this);
               }
            }
         } else {
            for (int i = 0; i < 3; i++) {
               g.drawImage(this.Data.icon10.getImage(), 52 + i * 78, 191, 60, 60, this);
               g.drawImage(this.Data.icon10.getImage(), 403 + i * 78, 191, 60, 60, this);
            }

            for (int i = 0; i < 2; i++) {
               g.drawImage(this.Data.icon10.getImage(), 52 + i * 156, 290, 60, 60, this);
               g.drawImage(this.Data.icon10.getImage(), 403 + i * 156, 290, 60, 60, this);
            }
         }

         for (int i = 0; i < 2; i++) {
            g.drawImage(this.Data.icon11.getImage(), 140 + i * 311, 388, 84, 28, this);
         }
      }
   }

   public void updatedData(int index) {
      if (!this.gameFiguresList.isEmpty()) {
         this.listModel.clear();
         List<GameFigures> filteredList = this.gameFiguresList.stream().filter(figure -> {
            if (index == 2) {
               String selectedOption = this.textLabels.getText();
               String[] process = figure.getGameProcess().split("-");
               switch (this.Data.InitialPosition) {
                  case 8:
                     switch (selectedOption) {
                        case "第一轮:8晋级4":
                           this.page = 8;
                           return process.length >= 1 && "1".equals(process[2]);
                        case "第二轮:4晋级2":
                           this.page = 4;
                           return process.length >= 2 && "1".equals(process[3]);
                        default:
                           return figure.getIntegral() != null && !figure.getIntegral().isEmpty();
                     }
                  case 16:
                     switch (selectedOption) {
                        case "第一轮:16晋级8":
                           this.page = 16;
                           return process.length >= 1 && "1".equals(process[1]);
                        case "第二轮:8晋级4":
                           this.page = 8;
                           return process.length >= 2 && "1".equals(process[2]);
                        case "第三轮:4晋级2":
                           this.page = 4;
                           return process.length >= 3 && "1".equals(process[3]);
                        default:
                           return figure.getIntegral() != null && !figure.getIntegral().isEmpty();
                     }
                  case 32:
                     switch (selectedOption) {
                        case "第一轮:32晋级16":
                           this.page = 32;
                           return process.length >= 1 && "1".equals(process[0]);
                        case "第二轮:16晋级8":
                           this.page = 16;
                           return process.length >= 2 && "1".equals(process[1]);
                        case "第三轮:8晋级4":
                           this.page = 8;
                           return process.length >= 3 && "1".equals(process[2]);
                        case "第四轮:4晋级2":
                           this.page = 4;
                           return process.length >= 4 && "1".equals(process[3]);
                     }
               }
            } else {
               if (index == 3) {
                  String[] process = figure.getGameProcess().split("-");
                  return process.length >= 4 && "0".equals(process[4]);
               }

               if (index == 4) {
                  String[] process = figure.getGameProcess().split("-");
                  return process.length >= 4 && "1".equals(process[4]);
               }
            }

            return figure.getIntegral() != null && !figure.getIntegral().isEmpty();
         }).sorted((f1, f2) -> {
            if (index == 0 || index == 1) {
               String[] integral1 = f1.getIntegral().split("-");
               String[] integral2 = f2.getIntegral().split("-");
               int score1 = 0;
               int score2 = 0;

               try {
                  if (index == 0) {
                     score1 = Integer.parseInt(integral1[0]);
                     score2 = Integer.parseInt(integral2[0]);
                  } else if (index == 1) {
                     score1 = Integer.parseInt(integral1[1]);
                     score2 = Integer.parseInt(integral2[1]);
                  }
               } catch (NumberFormatException var8) {
               }

               return Integer.compare(score2, score1);
            } else {
               return index == 2 ? 0 : 0;
            }
         }).collect(Collectors.toList());
         filteredList.forEach(figure -> this.listModel.addElement(figure));
         this.gameFiguresList.stream().filter(figure -> figure.getIntegral() == null || figure.getIntegral().isEmpty()).forEach(this.listModel::addElement);
         this.listPankList.setModel(this.listModel);
      }
   }

   public JScrollPane getScrollPaneTrans() {
      if (this.scrollPaneTrans == null) {
         this.listModel = new DefaultListModel<>();
         this.listPankList = new JList<>(this.listModel);
         this.listPankList.setOpaque(false);
         this.listPankList.setSelectionBackground(new Color(33, 42, 52));
         this.listPankList.setSelectionForeground(Color.white);
         this.listPankList.setForeground(Color.white);
         this.listPankList.setFont(new Font("微软雅黑", 1, 14));
         this.listPankList.setBackground(UIUtils.Color_BACK);
         this.listPankList.setCellRenderer(new TournamentsRenderer());
         this.listPankList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if (SwingUtilities.isLeftMouseButton(e)) {
                  int index = TournamentsScreen2Jpanel.this.listPankList.locationToIndex(e.getPoint());
                  if (index != -1) {
                     Rectangle bounds = TournamentsScreen2Jpanel.this.listPankList.getCellBounds(index, index);
                     Point pointInCell = new Point(e.getX() - bounds.x, e.getY() - bounds.y);
                     TournamentsRenderer renderer = (TournamentsRenderer)TournamentsScreen2Jpanel.this.listPankList.getCellRenderer();
                     JLabel labelTitle = renderer.getLabelTitle();
                     if (labelTitle.getBounds().contains(pointInCell)) {
                        GameFigures var7 = TournamentsScreen2Jpanel.this.listModel.get(index);
                     }
                  }
               } else if (SwingUtilities.isRightMouseButton(e)) {
               }
            }
         });
         this.scrollPaneTrans = new JScrollPane(this.listPankList);
         this.scrollPaneTrans.setVerticalScrollBarPolicy(21);
         this.scrollPaneTrans.setHorizontalScrollBarPolicy(31);
         this.scrollPaneTrans.getVerticalScrollBar().setUnitIncrement(30);
         this.scrollPaneTrans.getViewport().setOpaque(false);
         this.scrollPaneTrans.setOpaque(false);
         this.scrollPaneTrans.setBounds(34, 178, 600, 265);
         this.scrollPaneTrans.setBorder(BorderFactory.createEmptyBorder());
         this.listPankList.addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            JViewport viewport = this.scrollPaneTrans.getViewport();
            Point viewPosition = viewport.getViewPosition();
            int newY = viewPosition.y + notches * 10;
            newY = Math.max(newY, 0);
            newY = Math.min(newY, this.listPankList.getHeight() - viewport.getHeight());
            viewport.setViewPosition(new Point(viewPosition.x, newY));
         });
      }

      return this.scrollPaneTrans;
   }

   public void createSetupPanel(int x, int y, int w) {
      String[] resolutionData1;
      switch (this.Data.InitialPosition) {
         case 8:
            resolutionData1 = new String[]{"第一轮:8晋级4", "第二轮:4晋级2"};
            break;
         case 16:
            resolutionData1 = new String[]{"第一轮:16晋级8", "第二轮:8晋级4", "第三轮:4晋级2"};
            break;
         case 32:
            resolutionData1 = new String[]{"第一轮:32晋级16", "第二轮:16晋级8", "第三轮:8晋级4", "第四轮:4晋级2"};
            break;
         default:
            resolutionData1 = new String[]{"第一轮:32晋级16", "第二轮:16晋级8", "第三轮:8晋级4", "第四轮:4晋级2"};
      }

      this.optionPanels = new SetupJpanel5(w, 100, resolutionData1);
      this.optionPanels.setBounds(x, y, w, 100);
      this.optionPanels.getJlist().addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            String getname = TournamentsScreen2Jpanel.this.optionPanels.getJlist().getSelectedValue();
            TournamentsScreen2Jpanel.this.textLabels.setText(getname);
            TournamentsScreen2Jpanel.this.optionPanels.setVisible(false);
            TournamentsScreen2Jpanel.this.flags = 1;
            int index = 2;
            TournamentsScreen2Jpanel.this.updatedData(index);
            TournamentsScreen2Jpanel.this.setSelectedButton(index);
         }
      });
      this.optionPanels.setVisible(false);
      this.add(this.optionPanels);
      this.textLabels = new JLabel(resolutionData1[0]);
      this.textLabels.setForeground(Color.white);
      this.textLabels.setFont(UIUtils.TEXT_FONT);
      this.textLabels.setBounds(x + 5, y - 22, w - 3, 20);
      this.add(this.textLabels);
      this.arrowButtons = new State("", 1);
      this.arrowButtons.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            if (TournamentsScreen2Jpanel.this.flags == 1) {
               TournamentsScreen2Jpanel.this.optionPanels.setVisible(true);
               TournamentsScreen2Jpanel.this.flags = 0;
            } else {
               TournamentsScreen2Jpanel.this.optionPanels.setVisible(false);
               TournamentsScreen2Jpanel.this.flags = 1;
            }
         }
      });

      try {
         this.arrowButtons.setIcons(CutButtonImage.cuts("inkImg/danxin/p/e7.png"));
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      this.arrowButtons.setBounds(x + (w - 20), y - 19, 18, 18);
      this.add(this.arrowButtons);
   }

   public void playoffs() {
      String selectedOption = this.textLabels.getText();
      if (this.Data.InitialPosition == 32) {
         this.Data.playoffsFor32(selectedOption, this);
      } else if (this.Data.InitialPosition == 16) {
         this.Data.playoffsFor16(selectedOption, this);
      } else if (this.Data.InitialPosition == 8) {
         this.Data.playoffsFor8(selectedOption, this);
      }

      switch (this.page) {
         case 4:
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3C) > 0) {
               this.displayKnockoutTeams();
            }
            break;
         case 8:
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3B) > 0) {
               this.displayKnockoutTeams();
            }
            break;
         case 16:
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3A) > 0) {
               this.displayKnockoutTeams();
            }
            break;
         case 32:
            if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime2) > 0) {
               this.displayKnockoutTeams();
            }
      }
   }

   public void displayKnockoutTeams() {
      List<TournamentsScreen2Jpanel.Pair<GameFigures, GameFigures>> matchedPairs = new ArrayList<>();
      Set<String> processedIds = new HashSet<>();

      for (int i = 0; i < this.listModel.getSize(); i++) {
         GameFigures team1 = this.listModel.getElementAt(i);
         int currentPhase = this.getCurrentPhase();
         String[] process = team1.getGameProcess().split("-");
         if (process.length > currentPhase && (process[currentPhase].equals("0") || process[currentPhase].equals("3"))) {
            String team1Id = team1.getCaptainId().toString();
            if (!processedIds.contains(team1Id)) {
               String rivalId = team1.getGameRival().toString();
               GameFigures team2 = null;

               for (int j = 0; j < this.listModel.getSize(); j++) {
                  GameFigures potential = this.listModel.getElementAt(j);
                  if (potential.getCaptainId().toString().equals(rivalId)) {
                     team2 = potential;
                     break;
                  }
               }

               if (team2 != null) {
                  if (team1.getId().compareTo(team2.getId()) <= 0) {
                     matchedPairs.add(new TournamentsScreen2Jpanel.Pair<>(team1, team2));
                  } else {
                     matchedPairs.add(new TournamentsScreen2Jpanel.Pair<>(team2, team1));
                  }

                  processedIds.add(team1Id);
                  processedIds.add(rivalId);
               }
            }
         }
      }

      if (this.page != 8 && this.page != 4) {
         byte var32 = 75;
      } else {
         short var10000 = 130;
      }

      int xSpacing = this.page == 4 ? 225 : 150;
      int pairsPerPage = this.page == 32 ? 8 : matchedPairs.size();
      int startIndex = this.page == 32 ? this.currentPage * 8 : 0;
      int endIndex = Math.min(startIndex + pairsPerPage, matchedPairs.size());

      for (int ix = startIndex; ix < endIndex; ix++) {
         TournamentsScreen2Jpanel.Pair<GameFigures, GameFigures> pair = matchedPairs.get(ix);
         int y = 0;
         int baseX = 0;
         if (this.page == 4) {
            int var25 = 195;
            int var27 = 145;
            int baseY = var25 + ix * 130;
            JPanel team1Panel = this.createTeamPanel(pair.getKey(), var27, baseY, true);
            JPanel team2Panel = this.createTeamPanel(pair.getValue(), var27 + 225, baseY, false);
            this.add(team1Panel);
            this.add(team2Panel);
         } else {
            int var23 = (ix - startIndex) / 2;
            int col = (ix - startIndex) % 2;
            y = this.page == 8 ? 195 : 153;
            baseX = 45 + col * 315;
            int baseY = y + var23 * (this.page == 8 ? 130 : 75);
            JPanel team1Panel = this.createTeamPanel(pair.getKey(), baseX, baseY, true);
            JPanel team2Panel = this.createTeamPanel(pair.getValue(), baseX + (this.page == 8 ? 150 : 150), baseY, false);
            this.add(team1Panel);
            this.add(team2Panel);
         }
      }

      if (this.page == 32 && matchedPairs.size() > 8) {
         this.addPageButtons();
      }

      this.revalidate();
      this.repaint();
   }

   private void addPageButtons() {
      JButton prevButton = new JButton("A组");
      JButton nextButton = new JButton("B组");
      ImageIcon buttonIcon = new ImageIcon(new ImageIcon("inkImg/danxin/p/f20.png").getImage().getScaledInstance(40, 20, 4));
      prevButton.setIcon(buttonIcon);
      nextButton.setIcon(buttonIcon);
      prevButton.setFont(UIUtils.TEXT_FONT);
      nextButton.setFont(UIUtils.TEXT_FONT);
      prevButton.setVerticalTextPosition(0);
      nextButton.setVerticalTextPosition(0);
      prevButton.setHorizontalTextPosition(0);
      nextButton.setHorizontalTextPosition(0);
      prevButton.setBounds(275, 117, 60, 23);
      nextButton.setBounds(325, 117, 60, 23);
      prevButton.setContentAreaFilled(false);
      nextButton.setContentAreaFilled(false);
      prevButton.setBorderPainted(false);
      nextButton.setBorderPainted(false);
      prevButton.addActionListener(e -> {
         this.currentPage = 0;
         this.updateButtonColors(prevButton, nextButton);
         this.removeExistingPanels();
         this.displayKnockoutTeams();
      });
      nextButton.addActionListener(e -> {
         this.currentPage = 1;
         this.updateButtonColors(prevButton, nextButton);
         this.removeExistingPanels();
         this.displayKnockoutTeams();
      });
      this.updateButtonColors(prevButton, nextButton);
      this.add(prevButton);
      this.add(nextButton);
   }

   private void updateButtonColors(JButton prevButton, JButton nextButton) {
      if (this.currentPage == 0) {
         prevButton.setForeground(Color.RED);
         nextButton.setForeground(Color.WHITE);
      } else {
         prevButton.setForeground(Color.WHITE);
         nextButton.setForeground(Color.RED);
      }
   }

   private void removeExistingPanels() {
      Component[] components = this.getComponents();

      for (Component comp : components) {
         if (comp.getName() != null
            && (
               comp.getName().startsWith("team_panel")
                  || comp.getName().equals("advance_icon")
                  || comp.getName().startsWith("avatar_icon")
                  || comp.getName().equals("name_text")
                  || comp.getName().equals("record_text")
            )) {
            this.remove(comp);
         }
      }

      for (Component compx : components) {
         if (compx instanceof JButton && (((JButton)compx).getText().equals("A组") || ((JButton)compx).getText().equals("B组"))) {
            this.remove(compx);
         }
      }
   }

   private JPanel createTeamPanel(GameFigures team, int x, int y, boolean isLeft) {
      JPanel panel = new JPanel(null);
      panel.setName("team_panel");
      int panelWidth = this.page == 4 ? 150 : 110;
      x = Math.min(x, this.getWidth() - panelWidth);
      panel.setBounds(x, y, panelWidth, 60);
      panel.setOpaque(false);
      String[] memberInfo = team.getTeamMembersId().split("\\|")[0].split("-");
      String playerName = memberInfo[1];
      String avatarId = memberInfo[2];

      try {
         ImageIcon avatar = CutButtonImage.getImage("Dat/head/y" + avatarId + ".png", 32, 32);
         JLabel avatarLabel = new JLabel(avatar);
         avatarLabel.addMouseListener(new IconButtonEffect(avatarLabel));
         avatarLabel.setBounds(isLeft ? 5 : (this.page == 4 ? 113 : 73), 5, 32, 32);
         panel.add(avatarLabel);
      } catch (Exception var16) {
         var16.printStackTrace();
      }

      String[] success = team.getGameSuccess().split("-");
      String[] failure = team.getGameFailure().split("-");
      int phaseIndex = this.getCurrentPhaseIndex();
      String record = success[phaseIndex] + "胜" + failure[phaseIndex] + "负";
      JLabel recordLabel = new JLabel(record);
      recordLabel.setForeground(new Color(96, 47, 20));
      recordLabel.setFont(UIUtils.TEXT_FONT);
      recordLabel.setHorizontalAlignment(isLeft ? 2 : 4);
      recordLabel.setBounds(isLeft ? 43 : 5, 5, this.page == 4 ? 105 : 65, 20);
      panel.add(recordLabel);
      JLabel nameLabel = new JLabel(playerName);
      nameLabel.setForeground(new Color(96, 47, 20));
      nameLabel.setFont(UIUtils.TEXT_BT16);
      nameLabel.setHorizontalAlignment(isLeft ? 4 : 2);
      nameLabel.setBounds(isLeft ? 5 : 5, 35, this.page == 4 ? 140 : 100, 20);
      panel.add(nameLabel);
      this.add(panel);
      if (this.hasAdvanced(team)) {
         this.addAdvanceIcon(x + (isLeft ? (this.page == 4 ? 118 : 78) : 0), y);
      }

      return panel;
   }

   private int getCurrentPhaseIndex() {
      switch (this.page) {
         case 4:
            return 5;
         case 8:
            return 4;
         case 16:
            return 3;
         case 32:
            return 2;
         default:
            return 0;
      }
   }

   private int getCurrentPhase() {
      switch (this.page) {
         case 4:
            return 4;
         case 8:
            return 3;
         case 16:
            return 2;
         case 32:
            return 1;
         default:
            return 0;
      }
   }

   private boolean hasAdvanced(GameFigures team) {
      String[] process = team.getGameProcess().split("-");
      int phaseIndex = this.getCurrentPhase();
      return process.length > phaseIndex && "1".equals(process[phaseIndex]);
   }

   private void addAdvanceIcon(int x, int y) {
      try {
         ImageIcon advanceIcon = new ImageIcon(new ImageIcon("inkImg/danxin/p/f19.png").getImage().getScaledInstance(32, 32, 4));
         JLabel iconLabel = new JLabel(advanceIcon);
         iconLabel.setName("advance_icon");
         iconLabel.setBounds(x, y, 32, 32);
         this.add(iconLabel);
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   public void theFinalGame() {
      if (new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(new Date()).compareTo(this.Data.EndTime3D) > 0) {
         for (int i = 0; i < this.listModel.getSize(); i++) {
            this.processTeamData(this.listModel.getElementAt(0), this.Data.mode, 0);
            this.processTeamData(this.listModel.getElementAt(1), this.Data.mode, 1);
         }
      }
   }

   public void updateLabroleimg(BigDecimal species_id, int index) {
      if (this.avatar[index] == null) {
         this.avatar[index] = new JLabel();
         this.add(this.avatar[index]);
      }

      ImageIcon icon = CutButtonImage.getImage("Dat/head/y" + species_id + ".png", 52, 52);
      this.avatar[index].setIcon(icon);
      this.avatar[index].setName("avatar_icon");
      this.avatar[index].addMouseListener(new IconButtonEffect(this.avatar[index]));
   }

   private void processTeamData(GameFigures gameFigure, int mode, int teamIndex) {
      String[] members = gameFigure.getTeamMembersId().split("\\|");
      int count = mode == 1 ? 5 : (mode == 2 ? 3 : 1);
      String[] success = gameFigure.getGameSuccess().split("-");
      String[] failure = gameFigure.getGameFailure().split("-");
      int phaseIndex = 6;
      String record = success[phaseIndex] + "胜" + failure[phaseIndex] + "负";
      String[] process = gameFigure.getGameProcess().split("-");
      boolean isWinner = process.length > 5 && "1".equals(process[5]);
      JLabel recordLabel = new JLabel(record);
      recordLabel.setForeground(new Color(96, 47, 20));
      recordLabel.setFont(UIUtils.TEXT_FONT1);
      recordLabel.setBounds(140 + teamIndex * 311, 385, 84, 28);
      recordLabel.setHorizontalAlignment(0);
      recordLabel.setVerticalAlignment(0);
      recordLabel.setName("record_text");
      this.add(recordLabel);
      if (isWinner) {
         try {
            ImageIcon advanceIcon = new ImageIcon(new ImageIcon("inkImg/danxin/p/f19.png").getImage().getScaledInstance(32, 32, 4));
            JLabel iconLabel = new JLabel(advanceIcon);
            iconLabel.setName("advance_icon");
            iconLabel.setBounds(140 + teamIndex * 311 + 60, 375, 32, 32);
            this.add(iconLabel);
         } catch (Exception var19) {
            var19.printStackTrace();
         }
      }

      for (int i = 0; i < count && i < members.length; i++) {
         String[] memberInfo = members[i].split("-");
         if (memberInfo.length == 3) {
            String memberId = memberInfo[0];
            String memberName = memberInfo[1];
            BigDecimal speciesId = new BigDecimal(memberInfo[2]);
            int index = teamIndex * count + i;
            this.avatar[index] = new JLabel();
            this.add(this.avatar[index]);
            this.updateLabroleimg(speciesId, index);
            this.updateMemberName(memberName, index, mode, teamIndex);
            this.setAvatarPosition(index, mode, teamIndex);
         }
      }
   }

   private void setAvatarPosition(int index, int mode, int teamIndex) {
      int x = 0;
      int y = 0;
      if (mode == 1) {
         if (teamIndex == 0) {
            x = index < 3 ? 56 + index % 3 * 78 : 56 + index % 2 * 156;
            y = index < 3 ? 195 : 294;
         } else {
            x = index < 8 ? 491 + index % 3 * 78 : 491 + index % 2 * 156;
            y = index < 8 ? 195 : 294;
         }
      } else if (mode == 2) {
         x = teamIndex == 0 ? 56 + index % 3 * 78 : 407 + index % 3 * 78;
         y = 237;
      } else if (mode == 3) {
         x = 154 + teamIndex * 311;
         y = 237;
      }

      this.avatar[index].setBounds(x, y, 52, 52);
   }

   private void updateMemberName(String memberName, int index, int mode, int teamIndex) {
      this.name[index] = new JLabel(memberName);
      this.name[index].setFont(UIUtils.TEXT_FONT);
      this.name[index].setHorizontalAlignment(0);
      int x = 0;
      int y = 0;
      if (mode == 1) {
         if (teamIndex == 0) {
            x = index < 3 ? 48 + index % 3 * 78 : 52 + index % 2 * 156;
            y = index < 3 ? 253 : 352;
         } else {
            x = index < 8 ? 483 + index % 3 * 78 : 483 + index % 2 * 156;
            y = index < 8 ? 253 : 352;
         }
      } else if (mode == 2) {
         x = teamIndex == 0 ? 48 + index % 3 * 78 : 399 + index % 3 * 78;
         y = 295;
      } else if (mode == 3) {
         x = 146 + teamIndex * 311;
         y = 295;
      }

      this.name[index].setBounds(x, y, 70, 15);
      this.name[index].setName("name_text");
      this.name[index].setForeground(new Color(255, 255, 255));
      this.add(this.name[index]);
   }

   private static class Pair<K, V> {
      private final K key;
      private final V value;

      public Pair(K key, V value) {
         this.key = key;
         this.value = value;
      }

      public K getKey() {
         return this.key;
      }

      public V getValue() {
         return this.value;
      }
   }
}
