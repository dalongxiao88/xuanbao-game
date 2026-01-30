package org.skill.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;


public class WrapTextPanel
        extends JPanel {
    private String text;
    private String text1;
    private String text2;
    private Font font;
    private int maxWidth;
    private float alpha = 1.0F;
    private float alpha1 = 1.0F;
    private float alpha2 = 1.0F;
    private float alpha3 = 1.0F;
    private Color color = Color.white;
    private int y = 0;

    public WrapTextPanel(String text, Font font, int maxWidth) {
        this.text = text;
        this.font = font;
        this.maxWidth = maxWidth;
        setPreferredSize(new Dimension(maxWidth - 20, 1));
        setOpaque(false);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.setFont(this.font);
        float y = 1.0F;
        if (this.text != null && !this.text.isEmpty()) {
            y = drawTextBlock(g2d, this.text, Color.RED, 0, this.alpha1);
        }
        if (this.text1 != null && !this.text1.isEmpty()) {
            y = drawTextBlock(g2d, this.text1, Color.BLUE, (int) y, this.alpha2);
        }
        if (this.text2 != null && !this.text2.isEmpty()) {
            y = drawTextBlock(g2d, this.text2, Color.RED, (int) y, this.alpha3);
        }
        y = 0;
        this.y = (int) y;
        setPreferredSize(new Dimension(this.maxWidth, (int) y));
    }

    private float drawTextBlock(Graphics2D g2d, String text, Color circleColor, int blockIndex, float alpha) {
        g2d.setComposite(AlphaComposite.getInstance(3, alpha));
        int circleX = 0;
        (new String[1])[0] = "";
        String[] mes = extractBracketContents(text).isEmpty() ? new String[1] : ((String) extractBracketContents(text).get(0)).split(",");
        if (mes.length == 2) {
            if (mes[0].equals("蓝")) {
                g2d.setColor(Color.CYAN);
                g2d.fillOval(circleX, blockIndex + 15, 10, 10);
            } else if (mes[0].equals("红")) {
                g2d.setColor(Color.red);
                g2d.fillOval(circleX, blockIndex + 15, 10, 10);
            } else if (mes[0].equals("绿")) {
                g2d.setColor(Color.GREEN);
                g2d.fillOval(circleX, blockIndex + 15, 10, 10);
            } else if (mes[0].equals("黄")) {
                g2d.setColor(Color.yellow);
                g2d.fillOval(circleX, blockIndex + 15, 10, 10);
            }
            if (mes[1].equals("蓝")) {
                g2d.setColor(Color.CYAN);
                g2d.fillOval(circleX + 15, blockIndex + 15, 10, 10);
            } else if (mes[1].equals("红")) {
                g2d.setColor(Color.red);
                g2d.fillOval(circleX + 15, blockIndex + 15, 10, 10);
            } else if (mes[1].equals("绿")) {
                g2d.setColor(Color.GREEN);
                g2d.fillOval(circleX + 15, blockIndex + 15, 10, 10);
            } else if (mes[1].equals("黄")) {
                g2d.setColor(Color.yellow);
                g2d.fillOval(circleX + 15, blockIndex + 15, 10, 10);
            }
        }
        g2d.setColor(this.color);
        AttributedString attributedString = new AttributedString(text.replace("x", "50"));
        attributedString.addAttribute(TextAttribute.FONT, this.font, 0, attributedString.getIterator().getEndIndex());
        int startIndex = text.indexOf("x");
        if (startIndex != -1) {
            int endIndex = startIndex + 2;
            attributedString.addAttribute(TextAttribute.FOREGROUND, Color.RED, startIndex, endIndex);
        }
        LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(attributedString.getIterator(), g2d.getFontRenderContext());
        float y = (blockIndex + 15);
        while (lineBreakMeasurer.getPosition() < text.length()) {
            TextLayout layout = lineBreakMeasurer.nextLayout(this.maxWidth);
            layout.draw(g2d, (circleX + 30), y);
            y += layout.getAscent() + layout.getDescent() + layout.getLeading();
        }
        return y;
    }

    public List<String> extractBracketContents(String input) {
        List<String> contents = new ArrayList<>();
        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String content = matcher.group(1);
            contents.add(content);
        }
        return contents;
    }

    public void setText(String text, float alpha, Color font) {
        this.alpha1 = alpha;
        this.text = text;
        if (font != null) {
            this.color = font;
        }
        repaint();
        revalidate();
    }

    public void setText1(String text1, float alpha) {
        this.text1 = text1;
        this.alpha2 = alpha;
        repaint();
        revalidate();
    }

    public void setText2(String text2, float alpha) {
        this.text2 = text2;
        this.alpha3 = alpha;
        repaint();
        revalidate();
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }


    public int getY() {
        return this.y;
    }
}


