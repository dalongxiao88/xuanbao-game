package org.come.control;

import org.come.Frame.YaZhuJframe;
import org.come.Frame.JieGuaJframe;
import org.apache.commons.lang.StringUtils;
import org.come.action.FromServerAction;

public class GUAControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (StringUtils.isBlank(mes)) {
            return;
        }
        String[] vals = mes.split("=");
        JieGuaJframe jieGuaJframe = JieGuaJframe.getJieGuaJframe();
        if (jieGuaJframe.isVisible()) {
            String text = mes;
            if (vals[0].equals("3")) {
                text = this.getPushnoteValue(vals[1]);
            }
            jieGuaJframe.getJieGuaJpanel().setState(text);
        }
        if (vals[0].equals("3")) {
            YaZhuJframe yaZhuJframe = YaZhuJframe.getYaZhuJframe();
            if (yaZhuJframe.isVisible()) {
                yaZhuJframe.getYaZhuJpanel().setText(vals[1]);
            }
        }
    }
    
    private String getPushnoteValue(String msg) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(3);
        buffer.append("=");
        String[] vals = msg.split("\\|");
        for (int i = 0; i < vals.length; ++i) {
            if (i > 0) {
                buffer.append("|");
            }
            long value = 0L;
            if (!vals[i].equals("null")) {
                String[] vs;
                for (String v : vs = vals[i].split(",")) {
                    value += Long.parseLong(v.split("_")[1]);
                }
            }
            buffer.append(value);
        }
        return buffer.toString();
    }
}
