package com.tool.btn;

import java.util.Iterator;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.Frame.LingHelpListJframe;
import java.util.Arrays;
import org.come.model.Lingbao;
import java.math.BigDecimal;
import java.util.ArrayList;
import com.tool.role.RoleLingFa;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;

public class LingBaoZhiYuanBtn extends MoBanBtn
{
    public LingBaoZhiYuanBtn(String iconpath, int type, String text) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.getText().equals("支援列表")) {
            RoleData roleData = RoleData.getRoleData();
            String helpLing = roleData.getPackRecord().getHelpLing();
            if (StringUtils.isBlank(helpLing)) {
                Lingbao[] lingBaos = RoleLingFa.getRoleLingFa().getLingBaos();
                List<Lingbao> s = new ArrayList<>();
                for (Lingbao lingBao : lingBaos) {
                    if (lingBao != null) {
                        s.add(lingBao);
                    }
                }
                if (s.size() > 0) {
                    BigDecimal[] array = (BigDecimal[])s.<Lingbao>stream().map(Lingbao::getBaoid).toArray(BigDecimal[]::new);
                    String join = StringUtils.join(array, "|");
                    RoleData.getRoleData().sendHelpLingbao(join);
                }
            }
            String helpLing2 = RoleData.getRoleData().getPackRecord().getHelpLing();
            List<Lingbao> lingbaoList = new ArrayList<>();
            List<String> delList = new ArrayList<>();
            if (StringUtils.isNotEmpty(helpLing2)) {
                for (Lingbao lingBao2 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                    if (lingBao2 != null && !helpLing2.contains(lingBao2.getBaoid().toString())) {
                        helpLing2 = helpLing2 + "|" + lingBao2.getBaoid().toString();
                    }
                }
                String[] split = helpLing2.split("\\|");
                for (String s2 : split) {
                    Boolean b = Boolean.valueOf(true);
                    try {
                        for (Lingbao lingBao3 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                            if (lingBao3 != null && lingBao3.getBaoid().toString().equals(s2)) {
                                lingbaoList.add(lingBao3);
                                b = Boolean.valueOf(false);
                                break;
                            }
                        }
                    }
                    catch (Exception e2) {
                        continue;
                    }
                    if ((boolean)b) {
                        delList.add(s2);
                    }
                }
                List<String> collect = Arrays.asList(split);
                List<String> collect2 = new ArrayList<>();
                for (String s2 : collect) {
                    Boolean b = Boolean.valueOf(true);
                    Iterator<String> iterator2 = delList.iterator();
                    if (iterator2.hasNext()) {
                        String string = (String)iterator2.next();
                        b = Boolean.valueOf(false);
                    }
                    if ((boolean)b) {
                        collect2.add(s2);
                    }
                }
                if (collect2.size() > 0) {
                    lingbaoList.clear();
                    String[] lingIds = new String[collect2.size()];
                    collect2.toArray(lingIds);
                    String join2 = StringUtils.join(lingIds, "|");
                    RoleData.getRoleData().sendHelpLingbao(join2);
                    for (String s3 : lingIds) {
                        for (Lingbao lingBao4 : RoleLingFa.getRoleLingFa().getLingBaos()) {
                            if (lingBao4 != null && lingBao4.getBaoid().toString().equals(s3.toString())) {
                                lingbaoList.add(lingBao4);
                                break;
                            }
                        }
                    }
                }
                LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().init(lingbaoList);
            }
            else {
                LingHelpListJframe.getLingHelpListJframe().getLingHelpListJpanel().init(lingbaoList);
            }
            FormsManagement.showForm(622);
        }
    }
}
