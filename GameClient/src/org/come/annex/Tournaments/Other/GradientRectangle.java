package org.come.annex.Tournaments.Other;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class GradientRectangle {
   public int x;
   public int y;
   public int width;
   public int height;
   public int clarity;

   public GradientRectangle(int x, int y, int width, int height, int clarity) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.clarity = clarity;
   }

   public void draw(Graphics2D g2d) {
      GradientPaint leftGradient = new GradientPaint(this.x, this.y, new Color(0, 0, 0, 0), this.x + this.width / 2, this.y, new Color(0, 0, 0, this.clarity));
      g2d.setPaint(leftGradient);
      g2d.fillRect(this.x, this.y, this.width / 2, this.height);
      GradientPaint rightGradient = new GradientPaint(
         this.x + this.width / 2, this.y, new Color(0, 0, 0, this.clarity), this.x + this.width, this.y, new Color(0, 0, 0, 0)
      );
      g2d.setPaint(rightGradient);
      g2d.fillRect(this.x + this.width / 2, this.y, this.width / 2, this.height);
   }
}
