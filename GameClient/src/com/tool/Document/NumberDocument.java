package com.tool.Document;

import javax.swing.text.BadLocationException;
import org.come.until.Util;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import javax.swing.text.AttributeSet;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class NumberDocument extends PlainDocument
{
    private static final long serialVersionUID = 1L;
    private InputNum inputNum;
    private JTextField field;
    private int p;
    
    public NumberDocument(JTextField field) {
        this.field = field;
        this.p = 4;
    }
    
    public NumberDocument(JTextField field, int p) {
        this.field = field;
        this.p = p;
    }
    
    public NumberDocument(JTextField field, int p, InputNum inputNum) {
        this.field = field;
        this.p = p;
        this.inputNum = inputNum;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException {
        if (this.inputNum == null || !this.inputNum.isChange()) {
            if (str != null && str.length() == 1) {
                int charstr = str.charAt(0);
                if (charstr >= 48 && charstr <= 57) {
                    super.insertString(offset, str, attrSet);
                    String value = null;
                    BigDecimal bigDecimal = null;
                    if ((this.p & 0x1) == 0x0) {
                        value = this.getText(0, this.getLength()).replaceAll(",", "");
                        super.remove(0, this.getLength());
                        super.insertString(0, value, attrSet);
                        int p = value.length();
                        while (p > 3) {
                            p -= 3;
                            super.insertString(p, ",", attrSet);
                        }
                    }
                    if ((this.p >>> 2 & 0x1) == 0x0) {
                        if (value == null) {
                            value = this.getText(0, this.getLength());
                            if ((this.p & 0x1) == 0x0) {
                                value.replaceAll(",", "");
                            }
                        }
                        bigDecimal = new BigDecimal(value);
                        if (bigDecimal.compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                            bigDecimal = RoleData.getRoleData().getLoginResult().getGold();
                            value = bigDecimal.toString();
                            super.remove(0, this.getLength());
                            super.insertString(0, value, attrSet);
                            if ((this.p & 0x1) == 0x0) {
                                int p = value.length();
                                while (p > 3) {
                                    p -= 3;
                                    super.insertString(p, ",", attrSet);
                                }
                            }
                        }
                    }
                    if ((this.p >>> 1 & 0x1) == 0x0) {
                        if (value == null) {
                            value = this.getText(0, this.getLength());
                            if ((this.p & 0x1) == 0x0) {
                                value.replaceAll(",", "");
                            }
                        }
                        bigDecimal = new BigDecimal(value);
                        Util.changeTextColor(this.field, bigDecimal);
                    }
                    if (this.inputNum != null) {
                        this.inputNum.upNum();
                    }
                }
            }
            else {
                super.insertString(offset, str, attrSet);
            }
        }
    }
    
    @Override
    public void remove(int offs, int len) throws BadLocationException {
        if (this.inputNum == null || !this.inputNum.isChange()) {
            super.remove(offs, len);
            if (this.getLength() != 0) {
                String value = this.getText(0, this.getLength()).replaceAll(",", "");
                if (!value.equals("")) {
                    BigDecimal bigDecimal = new BigDecimal(value);
                    if ((this.p >>> 1 & 0x1) == 0x0) {
                        Util.changeTextColor(this.field, bigDecimal);
                    }
                    if ((this.p & 0x1) == 0x0) {
                        super.remove(0, this.getLength());
                        super.insertString(0, value, null);
                        int p = value.length();
                        while (p > 3) {
                            p -= 3;
                            super.insertString(p, ",", null);
                        }
                    }
                }
            }
            if (this.inputNum != null) {
                this.inputNum.upNum();
            }
        }
    }
    
    public void replace(String value) {
        try {
            BigDecimal bigDecimal = new BigDecimal(value);
            if ((this.p >>> 1 & 0x1) == 0x0) {
                Util.changeTextColor(this.field, bigDecimal);
            }
            super.remove(0, this.getLength());
            super.insertString(0, value, null);
            if ((this.p & 0x1) == 0x0) {
                int p = value.length();
                while (p > 3) {
                    p -= 3;
                    super.insertString(p, ",", null);
                }
            }
        }
        catch (BadLocationException var4) {
            var4.printStackTrace();
        }
    }
    
    public BigDecimal getNum() {
        try {
            String value = this.getText(0, this.getLength()).replaceAll(",", "");
            if (!value.equals("")) {
                return new BigDecimal(value);
            }
        }
        catch (Exception var2) {
            var2.printStackTrace();
        }
        return new BigDecimal(0);
    }
}
