package org.cbg.panel;

import javax.swing.table.JTableHeader;
import java.awt.Dimension;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;

public class TraslationTableJPanel extends JPanel
{
    public static void TableModel(JTable jTable, JScrollPane jScrollPane) {
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setBorder(null);
        jScrollPane.setViewportView(jTable);
        jTable.setRowHeight(50);
        jTable.setBorder(null);
        jTable.setOpaque(false);
        jTable.setShowGrid(false);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setOpaque(false);
        jTable.setDefaultRenderer(Object.class, render);
        Dimension viewSize = new Dimension();
        viewSize.width = jTable.getColumnModel().getTotalColumnWidth();
        viewSize.height = 10 * jTable.getRowHeight();
        jTable.setPreferredScrollableViewportSize(viewSize);
        JTableHeader header = jTable.getTableHeader();
        header.setPreferredSize(new Dimension(0, 0));
        header.setOpaque(false);
        header.getTable().setOpaque(false);
    }
}
