package com.tool.Document;

import org.come.until.GsonUtil;
import java.math.BigDecimal;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import java.util.ArrayList;
import com.tool.tcpimg.InputBean;
import java.util.List;
import javax.swing.text.PlainDocument;

public class RichDocument extends PlainDocument
{
    private static final long serialVersionUID = 1L;
    private List<InputBean> inputs;
    
    public RichDocument() {
        this.inputs = new ArrayList<>();
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException {
        if (str != null) {
            for (int i = this.inputs.size() - 1; i >= 0; --i) {
                if (((InputBean)this.inputs.get(i)).isIndex(offset)) {
                    return;
                }
            }
            this.Offset(offset, str.length());
            super.insertString(offset, str, attrSet);
        }
    }
    
    public void insertRich(int offset, int type, BigDecimal id, String str, String color, AttributeSet attrSet) throws BadLocationException {
        if (str != null && this.inputs.size() < 3) {
            str = str.replace("#", " ");
            for (int i = this.inputs.size() - 1; i >= 0; --i) {
                if (((InputBean)this.inputs.get(i)).isIndex(offset)) {
                    return;
                }
            }
            this.Offset(offset, str.length());
            InputBean bean = new InputBean(offset, type, id, str, color);
            this.insertInput(bean);
            super.insertString(offset, str, attrSet);
        }
    }
    
    public void insertRich(int offset, InputBean inputBean, AttributeSet attrSet) throws BadLocationException {
        if (inputBean != null) {
            for (int i = this.inputs.size() - 1; i >= 0; --i) {
                if (((InputBean)this.inputs.get(i)).isIndex(offset)) {
                    return;
                }
            }
            this.Offset(offset, inputBean.getName().length());
            inputBean.setIndex(offset);
            this.insertInput(inputBean);
            super.insertString(offset, inputBean.getName(), attrSet);
        }
    }
    
    @Override
    public void remove(int offs, int len) throws BadLocationException {
        this.Offset(offs, -len);
        super.remove(offs, len);
    }
    
    public void insertInput(InputBean bean) {
        for (int i = 0; i < this.inputs.size(); ++i) {
            if (((InputBean)this.inputs.get(i)).getIndex() > bean.getIndex()) {
                this.inputs.add(i, bean);
                return;
            }
        }
        this.inputs.add(bean);
    }
    
    public void Offset(int offs, int offset) {
        for (int i = this.inputs.size() - 1; i >= 0; --i) {
            InputBean bean = (InputBean)this.inputs.get(i);
            if (bean.getIndex() >= offs) {
                bean.setIndex(bean.getIndex() + offset);
                if (bean.getIndex() < offs) {
                    this.inputs.remove(i);
                }
            }
        }
    }
    
    public String sendText() {
        try {
            String text = super.getText(0, this.getLength());
            if (this.inputs.size() == 0) {
                return text;
            }
            int cha = 0;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < this.inputs.size(); ++i) {
                InputBean bean = (InputBean)this.inputs.get(i);
                String a = text.substring(0, bean.getIndex() - cha);
                buffer.append(a);
                buffer.append("#V");
                buffer.append(GsonUtil.getGsonUtil().getgson().toJson(bean));
                buffer.append("#L");
                text = text.substring(bean.getIndex() + bean.getName().length() - cha);
                cha = bean.getIndex() + bean.getName().length();
            }
            buffer.append(text);
            return buffer.toString();
        }
        catch (BadLocationException var7) {
            var7.printStackTrace();
            return null;
        }
    }
}
