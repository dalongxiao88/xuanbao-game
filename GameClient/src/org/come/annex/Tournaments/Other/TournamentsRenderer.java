package org.come.annex.Tournaments.Other;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import org.come.annex.Tournaments.Frame.TournamentsJframe;
import org.come.until.CutButtonImage;

public class TournamentsRenderer extends JPanel implements ListCellRenderer<GameFigures> {
   private JLabel labelTitle;
   private JLabel victory;
   private JLabel failed;
   private JLabel integral;
   private JPanel avatarPanel;

   public TournamentsRenderer() {
      this.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.insets = new Insets(0, 0, 0, 5);
      this.labelTitle = new JLabel();
      this.labelTitle.setPreferredSize(new Dimension(100, 35));
      this.labelTitle.setOpaque(true);
      this.labelTitle.setFont(UIUtils.TEXT_FONT1);
      this.labelTitle.setHorizontalAlignment(0);
      this.add(this.labelTitle, gbc);
      this.avatarPanel = new JPanel();
      this.avatarPanel.setPreferredSize(new Dimension(225, 35));
      this.avatarPanel.setLayout(new FlowLayout(1, 5, 0));
      gbc.gridx++;
      gbc.weightx = 0.0;
      this.add(this.avatarPanel, gbc);

      for (int i = 0; i < 5; i++) {
         JButton avatarButton = new JButton();
         avatarButton.setPreferredSize(new Dimension(35, 35));
         avatarButton.setContentAreaFilled(false);
         avatarButton.setBorderPainted(false);
         avatarButton.setAlignmentX(0.5F);
         this.avatarPanel.add(avatarButton);
      }

      gbc.gridx++;
      this.victory = new JLabel();
      this.victory.setPreferredSize(new Dimension(80, 35));
      this.victory.setOpaque(true);
      this.victory.setFont(UIUtils.TEXT_FONT1);
      this.victory.setHorizontalAlignment(0);
      this.add(this.victory, gbc);
      gbc.gridx++;
      this.failed = new JLabel();
      this.failed.setPreferredSize(new Dimension(80, 35));
      this.failed.setOpaque(true);
      this.failed.setFont(UIUtils.TEXT_FONT1);
      this.failed.setHorizontalAlignment(0);
      this.add(this.failed, gbc);
      gbc.gridx++;
      this.integral = new JLabel();
      this.integral.setPreferredSize(new Dimension(70, 35));
      this.integral.setOpaque(true);
      this.integral.setFont(UIUtils.TEXT_FONT1);
      this.integral.setHorizontalAlignment(0);
      this.add(this.integral, gbc);
   }

   public Component getListCellRendererComponent(JList<? extends GameFigures> list, GameFigures value, int index, boolean isSelected, boolean cellHasFocus) {
      int currentIndex = TournamentsJframe.getTournamentsJpanel().getTournamentsCardJpanel().getTournamentsScreen2Jpanel().currentIndex;
      if (value != null) {
         String[] integralData = value.getIntegral().split("\\|");
         String[] successData = value.getGameSuccess().split("-");
         String[] failureData = value.getGameFailure().split("-");
         String[] teamMembersIdData = value.getTeamMembersId().split("\\|");
         String[] integralValue = integralData[0].split("-");
         if (currentIndex < successData.length && currentIndex < failureData.length) {
            String[] successValues = successData[currentIndex].split("-");
            String[] failureValues = failureData[currentIndex].split("-");
            int victoryCount = 0;

            for (String valueStr : successValues) {
               victoryCount += Integer.parseInt(valueStr.trim());
            }

            int failedCount = 0;

            for (String valueStr : failureValues) {
               failedCount += Integer.parseInt(valueStr.trim());
            }

            this.victory.setText(String.valueOf(victoryCount));
            this.failed.setText(String.valueOf(failedCount));
         }

         int teamMemberCount = Math.min(teamMembersIdData.length, 5);
         Component[] avatarButtons = this.avatarPanel.getComponents();

         for (int i = 0; i < 5; i++) {
            if (i < teamMemberCount) {
               String[] memberInfo = teamMembersIdData[i].split("-");
               String memberId = memberInfo[0];
               String memberName = memberInfo[1];
               String avatarId = memberInfo[2];
               ImageIcon icon = CutButtonImage.getImage("Dat/head/y" + avatarId + ".png", 28, 28);
               ((JButton)avatarButtons[i]).setIcon(icon);
               ((JButton)avatarButtons[i]).setToolTipText(memberName);
               ((JButton)avatarButtons[i]).setVisible(true);
            } else {
               ((JButton)avatarButtons[i]).setVisible(false);
            }
         }

         if (teamMemberCount > 0) {
            String[] firstMemberInfo = teamMembersIdData[0].split("-");
            this.labelTitle.setText(firstMemberInfo[1]);
         } else {
            this.labelTitle.setText("");
         }

         if (currentIndex < 2) {
            this.integral.setText(integralValue[currentIndex]);
         }
      }

      if (isSelected) {
         this.setBackground(new Color(255, 255, 255, 80));
         this.labelTitle.setForeground(new Color(207, 108, 3, 255));
         this.victory.setForeground(new Color(207, 108, 3, 255));
         this.failed.setForeground(new Color(207, 108, 3, 255));
         this.integral.setForeground(new Color(207, 108, 3, 255));
      } else {
         this.setBackground(list.getBackground());
         this.labelTitle.setBackground(list.getBackground());
         this.victory.setBackground(list.getBackground());
         this.failed.setBackground(list.getBackground());
         this.integral.setBackground(list.getBackground());
         this.avatarPanel.setBackground(list.getBackground());
         this.labelTitle.setForeground(new Color(125, 98, 64, 255));
         this.victory.setForeground(new Color(125, 98, 64, 255));
         this.failed.setForeground(new Color(125, 98, 64, 255));
         this.integral.setForeground(new Color(125, 98, 64, 255));
      }

      return this;
   }

   public JLabel getLabelTitle() {
      return this.labelTitle;
   }
}
