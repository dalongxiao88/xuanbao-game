package org.come.gltools;

import come.tool.Fighting.Fightingimage;
import java.util.List;
import come.tool.Fighting.TypeState;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import org.come.Jpanel.TestPetJpanel;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.DefaultListCellRenderer;

public class PetListRendererTools extends DefaultListCellRenderer
{
    private static final long serialVersionUID = 1L;
    private Color rowcolor;
    private int row;
    private int[] rows;
    
    public PetListRendererTools(int row, Color color) {
        this.rowcolor = color;
        this.row = row;
    }
    
    public PetListRendererTools(int[] rows, Color color) {
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
        if (TestPetJpanel.getWarNum() == index) {
            this.setForeground(Color.orange);
        }
        else if (FightingMixDeal.State != 0) {
            List<RoleSummoning> plist = UserMessUntil.getPetListTable();
            Fightingimage fightingimage = FightingMixDeal.getdata(0);
            if (fightingimage != null && fightingimage.getFightingManData() != null) {
                List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                if (data != null && data.size() > 0) {
                    RoleSummoning roleSummoning = (RoleSummoning)plist.get(index);
                    for (int j = 0; j < data.size(); ++j) {
                        if (((TypeState)data.get(j)).getState() != 0 && roleSummoning.getSid().intValue() == Integer.parseInt(((TypeState)data.get(j)).getType())) {
                            this.setForeground(Color.red);
                        }
                    }
                }
            }
        }
        return this;
    }
}
