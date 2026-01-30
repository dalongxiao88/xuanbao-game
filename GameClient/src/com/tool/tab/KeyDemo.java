package com.tool.tab;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Frame;

public class KeyDemo
{
    private Frame f;
    private Button bt;
    private TextField tf;
    
    KeyDemo() {
        this.madeFrame();
    }
    
    public void madeFrame() {
        (this.f = new Frame("My Frame")).setBounds(300, 100, 600, 500);
        this.f.setLayout(new FlowLayout(1, 5, 5));
        this.bt = new Button("My Button");
        this.tf = new TextField(20);
        this.f.add(this.tf);
        this.f.add(this.bt);
        this.myEvent();
        this.f.setVisible(true);
    }
    
    private void myEvent() {
        this.f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("窗体执行关闭！");
                System.exit(0);
            }
        });
        this.bt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    System.exit(0);
                }
                else if (e.isControlDown() && e.getKeyCode() == 10) {
                    System.exit(0);
                }
                else {
                    System.out.println(e.getKeyChar() + "..." + KeyEvent.getKeyText(e.getKeyCode()));
                }
            }
        });
        this.tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code < 48 || code > 57) {
                    System.out.println(code + "...是非法的");
                    e.consume();
                }
            }
        });
    }
    
    public static void main(String[] agrs) {
        new KeyDemo();
    }
}
