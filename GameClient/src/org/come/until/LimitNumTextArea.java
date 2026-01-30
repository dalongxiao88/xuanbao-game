package org.come.until;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class LimitNumTextArea extends JTextArea
{
    public LimitNumTextArea(int length) {
        this.setDocument(new LimitNumDocument(length));
    }
    
    private class LimitNumDocument extends PlainDocument
    {
        private int fLength;
        
        public LimitNumDocument(int length) {
            this.fLength = -1;
            this.fLength = length;
        }
        
        @Override
        public void insertString(int offs, String str, AttributeSet attr) throws BadLocationException {
            int originalLength = this.getLength();
            if (originalLength <= 0) {
                super.insertString(offs, str, attr);
            }
            else {
                char[] input = str.toCharArray();
                int inputLength = 0;
                for (int i = 0; i < input.length && originalLength + inputLength < this.fLength; ++inputLength, ++i) {}
                super.insertString(offs, new String(input, 0, inputLength), attr);
            }
        }
    }
}
