package org.come.until;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class NumberValidator extends PlainDocument
{
    private static final long serialVersionUID = 1L;
    private int limit;
    
    public NumberValidator(int limit) {
        this.limit = limit;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (this.getLength() + str.length() <= this.limit) {
            char[] upper = str.toCharArray();
            int length = 0;
            for (int i = 0; i < upper.length; ++i) {
                if (upper[i] >= '0' && upper[i] <= '9') {
                    upper[length++] = upper[i];
                }
            }
            super.insertString(offset, new String(upper, 0, length), attr);
        }
    }
}
