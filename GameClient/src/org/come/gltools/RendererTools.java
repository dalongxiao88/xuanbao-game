package org.come.gltools;

import come.tool.Fighting.FightingMixDeal;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.DefaultListCellRenderer;

public class RendererTools extends DefaultListCellRenderer
{
    private static final long serialVersionUID = 1L;
    private Color rowcolor;
    private int row;
    private int[] rows;
    
    public RendererTools(int row, Color color) {
        this.rowcolor = color;
        this.row = row;
    }
    
    public RendererTools(int[] rows, Color color) {
        this.rowcolor = color;
        this.rows = rows;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        int state = FightingMixDeal.State;
        if (this.rows == null) {
            if (index == this.row) {
                this.setBackground(this.rowcolor);
            }
        }
        else {
            for (int i = 0; i < this.rows.length; ++i) {
                if (index == this.rows[i]) {
                    this.setBackground(this.rowcolor);
                }
            }
        }
        return this;
    }
}
