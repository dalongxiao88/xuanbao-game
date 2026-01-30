package org.come.until;

import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.GradientPaint;
import java.awt.Color;
import com.updateNew.MyIsif;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.plaf.metal.MetalSliderUI;

public class MySliderUI extends MetalSliderUI
{
    Image image;
    Image image1;
    
    public MySliderUI() {
        this.image = new ImageIcon("inkImg/button/B89.png").getImage();
        this.image1 = new ImageIcon("inkImg/hongmu1/43.png").getImage();
    }
    
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.thumbRect.setSize(13, 21);
        if (MyIsif.getStyle().equals("水墨")) {
            g2d.drawImage(this.image, this.thumbRect.x, this.thumbRect.y, this.thumbRect.width, this.thumbRect.height, null);
        }
        else {
            g2d.drawImage(this.image1, this.thumbRect.x, this.thumbRect.y, this.thumbRect.width, this.thumbRect.height, null);
        }
    }
    
    @Override
    public void paintTrack(Graphics g) {
        Rectangle trackBounds = this.trackRect;
        if (this.slider.getOrientation() == 0) {
            Graphics2D g2 = (Graphics2D)g;
            int cy = trackBounds.height / 2 - 2;
            int cw = trackBounds.width;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.translate(trackBounds.x, trackBounds.y + cy);
            g2.setPaint(new Color(0, 0, 0, 0));
            g2.fillRect(0, -cy, cw, cy * 2);
            int trackLeft = 0;
            int trackRight = 0;
            trackRight = this.trackRect.width - 1;
            int middleOfThumb = 0;
            int fillLeft = 0;
            int fillRight = 0;
            middleOfThumb = this.thumbRect.x + this.thumbRect.width / 2;
            middleOfThumb -= this.trackRect.x;
            if (!this.drawInverted()) {
                fillLeft = (this.slider.isEnabled() ? (trackLeft + 1) : trackLeft);
                fillRight = middleOfThumb;
            }
            else {
                fillLeft = middleOfThumb;
                fillRight = (this.slider.isEnabled() ? (trackRight - 2) : (trackRight - 1));
            }
            if (MyIsif.getStyle().equals("水墨")) {
                g2.setPaint(new GradientPaint(0.0f, 0.0f, new Color(127, 176, 164), (float)cw, 0.0f, new Color(127, 176, 164), true));
            }
            else {
                g2.setPaint(new GradientPaint(0.0f, 0.0f, new Color(187, 165, 75), (float)cw, 0.0f, new Color(187, 165, 75), true));
            }
            g2.fillRect(0, -cy, fillRight - fillLeft, cy * 2);
            g2.setPaint(this.slider.getBackground());
            Polygon polygon = new Polygon();
            polygon.addPoint(0, cy);
            polygon.addPoint(0, -cy);
            polygon.addPoint(cw, -cy);
            g2.fillPolygon(polygon);
            polygon.reset();
            g2.setPaint(Color.WHITE);
            g2.drawLine(0, cy, cw - 1, cy);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.translate(-trackBounds.x, -(trackBounds.y + cy));
        }
        else {
            super.paintTrack(g);
        }
    }
}
