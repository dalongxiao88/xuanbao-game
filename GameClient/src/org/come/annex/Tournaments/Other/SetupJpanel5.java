package org.come.annex.Tournaments.Other;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SetupJpanel5 extends JPanel {
   private String backPath;
   private int width;
   private int heigth;
   private JList<String> jlist;
   private DefaultListModel<String> listModel;
   public static JScrollPane jScrollPane;

   public JList<String> getJlist() {
      if (this.jlist == null) {
         this.jlist = new JList<>();
         this.jlist.setSelectionBackground(new Color(122, 117, 112));
         this.jlist.setSelectionForeground(Color.white);
         this.jlist.setForeground(Color.white);
         this.jlist.setFont(UIUtils.TEXT_FONT1);
         this.jlist.setBackground(UIUtils.Color_BACK);
         this.jlist.setOpaque(false);
         this.jlist.setModel(this.getListModel());
      }

      return this.jlist;
   }

   public void setJlist(JList<String> jlist) {
      this.jlist = jlist;
   }

   public DefaultListModel<String> getListModel() {
      if (this.listModel == null) {
         this.listModel = new DefaultListModel<>();
      }

      return this.listModel;
   }

   public void setListModel(DefaultListModel<String> listModel) {
      this.listModel = listModel;
   }

   public String getBackPath() {
      return this.backPath;
   }

   public void setBackPath(String backPath) {
      this.backPath = backPath;
   }

   @Override
   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeigth() {
      return this.heigth;
   }

   public void setHeigth(int heigth) {
      this.heigth = heigth;
   }

   public SetupJpanel5(int width, int heigth, String[] rowData) {
      this.width = width;
      this.heigth = heigth;
      this.setPreferredSize(new Dimension(width, heigth));
      this.setLayout(null);
      jScrollPane = new JScrollPane(this.getJlist());
      jScrollPane.setVerticalScrollBarPolicy(22);
      jScrollPane.getViewport().setOpaque(false);
      jScrollPane.setOpaque(false);
      jScrollPane.setBounds(5, 3, width, heigth + 3);
      jScrollPane.setBorder(BorderFactory.createEmptyBorder());
      jScrollPane.setHorizontalScrollBarPolicy(31);
      jScrollPane.setVerticalScrollBarPolicy(21);
      jScrollPane.setHorizontalScrollBarPolicy(31);
      this.add(jScrollPane);
      if (rowData != null) {
         for (int i = 0; i < rowData.length; i++) {
            this.getListModel().add(i, rowData[i]);
         }
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
   }

   public static JScrollPane getjScrollPane() {
      return jScrollPane;
   }

   public static void setjScrollPane(JScrollPane jScrollPane) {
      SetupJpanel5.jScrollPane = jScrollPane;
   }
}
