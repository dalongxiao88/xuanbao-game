package com.tool.btn;

import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.come.model.Lingbao;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.LingHelpListJpanel;

public class LingHelpBtn extends MoBanBtn
{
    private LingHelpListJpanel jpanel;
    private int p;
    
    public LingHelpBtn(String iconpath, int type, int p, LingHelpListJpanel jpanel) {
        super(iconpath, type);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    public LingHelpBtn(String iconpath, int type, int p, String text, LingHelpListJpanel jpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        int w = this.jpanel.getListpet().getSelectedIndex();
        if (w == -1) {
            return;
        }
        RoleData data = RoleData.getRoleData();
        List<String> list = null;
        if (this.p == 0) {
            int i = w - 1;
            Lingbao lingbao = (Lingbao)this.jpanel.getListModel().get(i);
            Lingbao lingbao2 = (Lingbao)this.jpanel.getListModel().get(w);
            this.jpanel.getListModel().set(i, lingbao2);
            this.jpanel.getListModel().set(w, lingbao);
            this.jpanel.getListpet().setSelectedIndex(i);
        }
        else if (this.p == 1) {
            int i = w + 1;
            Lingbao lingbao = (Lingbao)this.jpanel.getListModel().get(i);
            Lingbao lingbao2 = (Lingbao)this.jpanel.getListModel().get(w);
            this.jpanel.getListModel().set(i, lingbao2);
            this.jpanel.getListModel().set(w, lingbao);
            this.jpanel.getListpet().setSelectedIndex(i);
        }
        else if (this.p == 2) {
            int i = 0;
            Lingbao lingbao = (Lingbao)this.jpanel.getListModel().get(i);
            Lingbao lingbao2 = (Lingbao)this.jpanel.getListModel().get(w);
            this.jpanel.getListModel().set(i, lingbao2);
            this.jpanel.getListModel().set(w, lingbao);
            this.jpanel.getListpet().setSelectedIndex(i);
        }
        else {
            int i = this.jpanel.getListModel().getSize() - 1;
            Lingbao lingbao = (Lingbao)this.jpanel.getListModel().get(i);
            Lingbao lingbao2 = (Lingbao)this.jpanel.getListModel().get(w);
            this.jpanel.getListModel().set(i, lingbao2);
            this.jpanel.getListModel().set(w, lingbao);
            this.jpanel.getListpet().setSelectedIndex(i);
        }
        this.initLings();
    }
    
    private void initLings() {
        List<Lingbao> lings = new ArrayList<>();
        for (int j = 0; j < this.jpanel.getListModel().getSize(); ++j) {
            lings.add(this.jpanel.getListModel().get(j));
        }
        if (lings.size() > 0) {
            BigDecimal[] array = (BigDecimal[])lings.<Lingbao>stream().map(Lingbao::getBaoid).toArray(BigDecimal[]::new);
            String join = StringUtils.join(array, "|");
            RoleData.getRoleData().sendHelpLingbao(join);
        }
    }
}
