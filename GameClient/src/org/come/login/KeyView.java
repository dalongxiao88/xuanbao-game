package org.come.login;

import org.come.until.Util;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.come.view.View;

public class KeyView extends View
{
    private JButton[] keyButton;
    private ImageIcon icon;
    
    public KeyView(LoginView loginView) {
        this.setPreferredSize(new Dimension(700, 170));
        String[] keyBord = { "a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i", "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q", "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "-", "=", "<", ">", ",", ".", "/", "?", ";", ":", "'", "'", "|", "\\" };
        this.keyButton = new JButton[keyBord.length];
        this.keyButtonInit(keyBord, loginView);
        for (int i = 0; i < this.keyButton.length; ++i) {
            this.keyButton[i].setBounds(i % 23 * 29 + 18, i / 23 * 29 + 10, 27, 27);
            this.add(this.keyButton[i]);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/键盘背景.JPG");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 700, 170, this);
    }
    
    public void keyButtonInit(String[] keyBord, LoginView loginView) {
        this.sortingInsanity(keyBord);
        ImageIcon icon = new ImageIcon("img/xy2uiimg/按钮.JPG");
        icon.setImage(icon.getImage().getScaledInstance(27, 27, 1));
        for (int i = 0; i < keyBord.length; ++i) {
            (this.keyButton[i] = new JButton(keyBord[i], icon)).setOpaque(false);
            this.keyButton[i].setContentAreaFilled(true);
            this.keyButton[i].setMargin(new Insets(0, 0, 0, 0));
            this.keyButton[i].setFocusPainted(false);
            this.keyButton[i].setBorderPainted(false);
            this.keyButton[i].setBorder(null);
            this.keyButton[i].setHorizontalTextPosition(0);
            this.keyButton[i].setForeground(Color.yellow);
            this.keyButton[i].setText(keyBord[i]);
            this.keyButton[i].addMouseListener(new KeyMouslisten(keyBord[i], loginView));
        }
    }
    
    public void sortingInsanity(String[] keyBord) {
        String valuse = null;
        for (int i = 0; i < keyBord.length; ++i) {
            int positionone = Util.random.nextInt(keyBord.length);
            int positiononetwo = Util.random.nextInt(keyBord.length);
            if (positionone != positiononetwo) {
                valuse = keyBord[positionone];
                keyBord[positionone] = keyBord[positiononetwo];
                keyBord[positiononetwo] = valuse;
            }
        }
    }
}
