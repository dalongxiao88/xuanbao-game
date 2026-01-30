package com.tool.btn;

import org.come.until.Util;
import java.awt.event.MouseEvent;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.tool.tcp.Sprite;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public abstract class MoBanBtn extends JLabel implements MouseListener, BtnInterface
{
    protected ImageIcon[] icons;
    protected Color[] colors;
    protected int btn;
    protected int zhen;
    protected int imgzhen;
    protected Integer V;
    protected int type;
    protected int num;
    protected int isup;
    private Sprite sprite;
    private int sx;
    private int sy;
    private boolean ischoose;

    public MoBanBtn(String iconpath, int type) {
        this.num = -1;
        this.isup = 0;
        try {
            this.btn = type;
            if (iconpath != null) {
                this.icons = CutButtonImage.cuts(iconpath);
                this.btnchange(0);
            }
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MoBanBtn(String iconpath, int type, Sprite sprite, int sx, int xy, boolean ischoose) {
        this.num = -1;
        this.isup = 0;
        this.sprite = sprite;
        this.sx = sx;
        this.sy = xy;
        this.ischoose = ischoose;
        try {
            this.btn = type;
            if (iconpath != null) {
                this.icons = CutButtonImage.cuts(iconpath);
                this.btnchange(0);
            }
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MoBanBtn(String iconpath, int type, Color[] colors) {
        this.num = -1;
        this.isup = 0;
        try {
            this.btn = type;
            this.colors = colors;
            if (iconpath != null) {
                this.icons = CutButtonImage.cuts(iconpath);
                this.btnchange(0);
            }
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MoBanBtn(String iconpath, int type, Color[] colors, int num, int isup) {
        this.num = -1;
        this.isup = 0;
        try {
            this.btn = type;
            this.colors = colors;
            this.num = num;
            this.isup = isup;
            if (iconpath != null) {
                this.icons = CutButtonImage.cuts(iconpath);
                this.btnchange(0);
            }
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MoBanBtn(byte[] iconByte, String fileName, int type, Color[] colorWhite2) {
        this.num = -1;
        this.isup = 0;
        try {
            this.btn = type;
            if (iconByte != null) {
                this.icons = CutButtonImage.cuts(iconByte, fileName);
                this.btnchange(0);
            }
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIschoose(boolean ischoose) {
        this.ischoose = ischoose;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.type == 2) {
            g.translate(1, 1);
            super.paintComponent(g);
            g.translate(-1, -1);
        }
        else {
            super.paintComponent(g);
        }
        if (this.sprite != null && this.ischoose) {
            this.sprite.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            this.sprite.draw(g, this.sx, this.sy);
        }
        if (this.V != null) {
            g.drawImage(CutButtonImage.getJT().getImage(), this.getWidth() - 10, 0, null);
        }
    }

    public MoBanBtn(ImageIcon[] icon, int type) {
        this.num = -1;
        this.isup = 0;
        try {
            this.btn = type;
            this.icons = icon;
            this.btnchange(0);
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MoBanBtn(ImageIcon[] icon, int type, Color[] colors) {
        this.num = -1;
        this.isup = 0;
        try {
            this.btn = type;
            this.icons = icon;
            this.colors = colors;
            this.btnchange(0);
            this.addMouseListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 按钮状态切换
     */
   // public void btnchange(int change) {
   //     this.imgchange(this.zhen = change);
    //}
    public void btnchange(int change) {
        this.zhen = change;
        this.imgchange(this.zhen);
    }
    /**
     * 按钮图片切换
     */
    public void imgchange(int change) {
        this.imgzhen = change;
        if (this.btn == 0) {
            this.setIcon(null);
        }
        else {
            if (this.icons != null) {
                this.setIcon(this.icons[change]);
            }
            if (this.colors != null) {
                this.setForeground(this.colors[change]);
            }
        }
    }

    public void btnchangeWH(int is) {
        if (this.num != -1) {
            if (is > 1) {
                return;
            }
            if (is < 0) {
                return;
            }
            if (this.isup == 1) {
                this.setBounds(this.getX(), this.getY() - this.num, this.getWidth(), this.getHeight() + this.num);
                this.isup = 0;
            }
            else {
                this.setBounds(this.getX(), this.getY() + this.num, this.getWidth(), this.getHeight() - this.num);
                this.isup = 1;
            }
            this.repaint();
        }
    }

    public void btntypechange(int change) {
        this.btn = change;
        this.type = 0;
        this.btnchange(1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
     //   System.out.println("dianji btn:" + btn + ",type:" + type + ",id:" + e.getSource().toString());
        this.dianji();
    }
    /**
     * 模拟点击
     */
    public void dianji() {
        if (this.btn == -1 || this.btn == 0) {
            return;
        }
        if (this.btn == 2 || this.btn == 3) {
            if (this.zhen != 2) {
                this.type = 2;
                this.btnchange(2);
                this.chooseyes();
            }
            else if (this.btn == 3) {
                this.type = 1;
                this.btnchange(1);
                this.chooseno();
            }
        }
        else {
            this.type=2;
            this.btnchange(2);
        }
        System.out.println("dj->"+type);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.shifang(e);
    }
    /**
     * 模拟释放
     */
    public void shifang(MouseEvent e) {
      //  System.out.println("shifang btn:" + btn + ",type:" + type + ",id:" + e.getSource().toString());
        if (this.btn == 1) {
            if (this.type != 0) {
                this.type=0;
                this.btnchange(this.type);
                if (Util.isM() && !(e.getSource() instanceof TitlelBtn)) {
                    return;
                }
                this.nochoose(e);
            }
            else {
                this.btnchange(this.type);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.btn == -1) {
            return;
        }
        if (this.btn != 0 && this.zhen != 2) {
            this.btnchange(1);
        }
        this.type = 1;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn == -1) {
            return;
        }
        if ((this.btn != 0 || this.btn != -1) && this.zhen != 2) {
            this.btnchange(0);
        }
        this.type = 0;
    }

    @Override
    public abstract void chooseyes();

    @Override
    public abstract void chooseno();

    @Override
    public abstract void nochoose(MouseEvent p0);

    public int getZhen() {
        return this.zhen;
    }

    public void setZhen(int zhen) {
        this.zhen = zhen;
    }

    public int getImgzhen() {
        return this.imgzhen;
    }

    public void setImgzhen(int imgzhen) {
        this.imgzhen = imgzhen;
    }

    public ImageIcon[] getIcons() {
        return this.icons;
    }

    public void setIcons(ImageIcon[] icons) {
        this.icons = icons;
        this.btnchange(0);
    }

    public int getBtn() {
        return this.btn;
    }

    public void setBtn(int btn) {
        this.btn = btn;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIsup() {
        return this.isup;
    }

    public void setIsup(int isup) {
        this.isup = isup;
    }

    public void onOffRed(int v) {
        if (v == 0) {
            this.V = null;
        }
        else {
            this.V = Integer.valueOf(v);
        }
    }

    public Color[] getColors() {
        return this.colors;
    }

    public void setColors(Color[] colors) {
        this.setForeground(colors[0]);
        this.colors = colors;
    }
}
