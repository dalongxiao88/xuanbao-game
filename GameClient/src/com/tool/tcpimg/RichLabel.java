package com.tool.tcpimg;

import org.come.bean.ConfigureBean;

import java.util.Map;

import org.come.bean.AllSuit;
import org.come.bean.RoleSuitBean;

import java.math.BigDecimal;

import org.come.model.Configure;
import org.come.until.UserMessUntil;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.come.until.GsonUtil;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Frame;
import org.come.until.CutButtonImage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.List;
import java.util.regex.Pattern;

import com.tool.tcp.Animation;

import java.util.HashMap;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;

public class RichLabel extends JComponent {
    private static final long serialVersionUID = 4898130145332371300L;
    public ArrayList<Object> sectionList;
    private boolean isInteractive;
    private long startTime;
    private long currTime;
    private Font font;
    private String text;
    private static HashMap<String, Animation> faceAnimations;
    private static Pattern pattern;
    public static List<String> num;
    public Boolean b;
    private static String PATH;
    private String lastStr;
    private InputBean lastBean;
    private Point point;
    static BufferedImage temp;

    public RichLabel() {
        this(null, null);
    }

    public RichLabel(String text, Font font) {
        this.b = Boolean.valueOf(false);
        this.point = new Point(0, 0);
        this.sectionList = new ArrayList<>();
        this.setBackground(Color.RED);
        this.setForeground(Color.BLUE);
        this.setIgnoreRepaint(true);
        this.setBorder(null);
        this.setOpaque(false);
        this.text = text;
        this.setSize(98, 16);
        this.parseText(text);
        this.font = font;
    }

    public void clearAddText(final String text, final Font font) {
        sectionList.clear();
        this.text = "";
        this.text = text;
        this.font = font;
        this.parseText(text);
    }

    public RichLabel(String text, Font font, int size) {
        this.b = Boolean.valueOf(false);
        this.point = new Point(0, 0);
        this.sectionList = new ArrayList<>();
        this.setBackground(Color.RED);
        this.setForeground(Color.BLUE);
        this.setIgnoreRepaint(true);
        this.setBorder(null);
        this.setOpaque(false);
        this.parseText(this.text = text);
        this.font = font;
        this.setSize(this.computeSize(size));
    }

    public RichLabel(String text, Font font, String type) {
        sectionList = new ArrayList<Object>();
        setBackground(Color.RED);
        setForeground(Color.BLUE);
        setIgnoreRepaint(true);
        setBorder(null);
        setOpaque(false);
        this.text = text;
        setSize(28 * 7, 16);
        parseText(text);
        this.font = font;
    }

    public RichLabel(String text) {
        this.b = Boolean.valueOf(false);
        this.point = new Point(0, 0);
        this.point = new Point(0, 0);
        this.sectionList = new ArrayList<>();
        this.setBackground(Color.RED);
        this.setForeground(Color.BLUE);
        this.setIgnoreRepaint(true);
        this.setBorder(null);
        this.setOpaque(false);
        this.text = text;
        this.setSize(98, 16);
        this.parseText(text);
    }

    public RichLabel(int size) {
        this.b = Boolean.valueOf(false);
        this.point = new Point(0, 0);
        this.point = new Point(0, 0);
        this.sectionList = new ArrayList<>();
        this.setBackground(Color.RED);
        this.setForeground(Color.BLUE);
        this.setIgnoreRepaint(true);
        this.setBorder(null);
        this.setOpaque(false);
        this.setSize(size, 16);
        this.parseText(this.text);
    }

    public void parseText(String text) {
        if (text == null) {
            return;
        }
        Matcher m = RichLabel.pattern.matcher(text);
        while (m.find()) {
            String section = m.group();
            if (section.startsWith("#")) {
                Animation anim = (Animation) RichLabel.faceAnimations.get(section);
                if (anim == null && section.charAt(1) >= '0' && section.charAt(1) <= '9') {
                    String value = section.substring(1);
                    if (value.equals("880")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("inkImg/background/gemBackImg.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("881")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("inkImg/background/gemImg3.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("882")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("inkImg/background/gemImg1.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("883")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("inkImg/background/gemImg2.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("884")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("inkImg/background/gemImg4.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("885")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("inkImg/background/gemImg5.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (this.gettzm(text) != "" && Integer.parseInt(value) > 90 && !this.getjntp(Integer.parseInt(value)) && !this.getbstp(Integer.parseInt(value))) {
                        String v = this.gettzm(text);
                        String[] vs = v.split("=");
                        String[] vss = vs[1].split("\\|");
                        String sa = vs[0];
                        String sad = value.replace("9" + sa, "");
                        boolean bs = false;
                        if (RichLabel.num.size() > 0) {
                            for (String nu : RichLabel.num) {
                                if (nu.equals(sad)) {
                                    RichLabel.num.remove(nu);
                                    bs = true;
                                    break;
                                }
                            }
                        }
                        if (bs) {
                            anim = new Animation();
                            Image sx = CutButtonImage.getImage("img/item/tz" + sa + "_" + sad + ".png", 30, 30).getImage();
                            Frame frame2 = new Frame(sx, 0, 0);
                            anim.addFrame(frame2);
                        } else {
                            anim = new Animation();
                            Frame frames = new Frame(CutButtonImage.getImage("img/fighting-skill/tz/htz" + sa + "_" + sad + ".png", 30, 30).getImage(), 0, 0);
                            anim.addFrame(frames);
                        }
                    } else if (this.getjntp(Integer.parseInt(value))) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/fighting-skill/" + value + ".png", 13, 13).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (this.getbstp(Integer.parseInt(value))) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/item/" + value + ".png", 17, 17).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else {
                        anim = SpriteFactory.loadAnimation(RichLabel.PATH + value + ".was");
                    }
                    if (anim != null) {
                        RichLabel.faceAnimations.put(section, anim);
                    }
                } else if (section.startsWith("#x")) {
                    String value = section.substring(2);
                    if (value.equals("60")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyd1.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("61")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyd2.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("62")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyd3.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("63")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyd4.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("64")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyc1.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("65")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyc2.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("66")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyc3.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("67")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xyc4.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("68")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/lingbao/msg/圆封.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("69")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/lingbao/msg/圆开.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("70")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/hongfengyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("71")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/hongpanyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("72")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/lanshayin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("73")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/langangyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("74")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/lanpoyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("75")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/lanyingyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("76")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/lushengyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("77")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/luxiyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("78")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/lulingyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("79")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/luyuanyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("80")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/huangdingyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("81")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/图标1/huangrenyin.png", 28, 28).getImage(), 0, 0);
                        anim.addFrame(frame);
                    } else if (value.equals("82")) {
                        anim = new Animation();
                        Frame frame = new Frame(CutButtonImage.getImage("img/xuan/xy+.png", -1, -1).getImage(), 0, 0);
                        anim.addFrame(frame);
                    }
                } else if (section.equals("#T") || section.equals("#X") || section.equals("#J") || section.equals("#Q") || section.equals("#S") || section.equals("#D") || section.equals("#P") || section.equals("#Z")) {
                    anim = (Animation) RichLabel.faceAnimations.get(section);
                    if (anim == null) {
                        anim = SpriteFactory.loadAnimation(RichLabel.PATH + this.gethead(section.substring(1)) + ".was");
                        if (anim != null) {
                            RichLabel.faceAnimations.put(section, anim);
                        }
                    }
                }
                if (anim != null) {
                    this.sectionList.add(anim);
                    this.b = Boolean.valueOf(true);
                } else if (section.startsWith("#c")) {
                    if (section.length() == 8) {
                        Color color = UIUtils.getColor(section);
                        this.sectionList.add(color);
                    } else {
                        continue;
                    }
                } else if (section.equals("#R")) {
                    this.sectionList.add(Color.RED);
                } else if (section.equals("#G")) {
                    this.sectionList.add(Color.GREEN);
                } else if (section.equals("#B")) {
                    this.sectionList.add(Color.BLUE);
                } else if (section.equals("#W")) {
                    this.sectionList.add(Color.WHITE);
                } else if (section.equals("#K")) {
                    this.sectionList.add(Color.BLACK);
                } else if (section.equals("#Y")) {
                    this.sectionList.add(Color.YELLOW);
                } else if (section.equals("#r")) {
                    this.sectionList.add(new Integer(-1));
                } else if (section.equals("#n")) {
                    this.sectionList.add(Color.WHITE);
                } else if (section.equals("#V")) {
                    try {
                        if (m.find()) {
                            String cr = m.group();
                            if (cr.startsWith("c4f0f0f")) {
                                this.sectionList.add(new InputBean(-1));
                            } else {
                                InputBean bean = (InputBean) GsonUtil.getGsonUtil().getgson().fromJson(cr, InputBean.class);
                                if (bean.getColor() != null && !bean.getColor().equals("")) {
                                    Color color2 = null;
                                    if (bean.getColor().startsWith("c")) {
                                        String color3 = section.substring(2);
                                        if (!color3.equals("")) {
                                            color2 = Color.decode("0x" + color3);
                                        }
                                    } else if (bean.getColor().equals("R")) {
                                        color2 = Color.RED;
                                    } else if (bean.getColor().equals("G")) {
                                        color2 = Color.GREEN;
                                    } else if (bean.getColor().equals("B")) {
                                        color2 = Color.BLUE;
                                    } else if (bean.getColor().equals("W")) {
                                        color2 = Color.WHITE;
                                    } else if (bean.getColor().equals("K")) {
                                        color2 = Color.BLACK;
                                    } else if (bean.getColor().equals("Y")) {
                                        color2 = Color.YELLOW;
                                    }
                                    bean.setColor2(color2);
                                }
                                bean.setColor(null);
                                this.sectionList.add(bean);
                                if (StringUtils.isNotBlank(bean.getGoodNum())) {
                                    bean.setGoodNum(null);
                                    InputBean bean2 = (InputBean) GsonUtil.getGsonUtil().getgson().fromJson(cr, InputBean.class);
                                    this.sectionList.add(bean2);
                                    this.sectionList.add(Color.GREEN);
                                    if (bean.getType() == 5) {
                                        this.sectionList.add("]");
                                    } else {
                                        this.sectionList.add("]");
                                    }
                                    this.sectionList.add(Color.WHITE);
                                }
                                this.isInteractive = true;
                            }
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    continue;
                }
            } else {
                this.sectionList.add(section);
            }
        }
    }

    public void addList(Object object) {
        this.sectionList.add(object);
    }

    public int gethead(String head) {
        if (head.equals("T")) {
            return 400;
        }
        if (head.equals("X")) {
            return 401;
        }
        if (head.equals("J")) {
            return 402;
        }
        if (head.equals("Q")) {
            return 403;
        }
        if (head.equals("D")) {
            return 404;
        }
        if (head.equals("S")) {
            return 405;
        }
        if (head.equals("P")) {
            return 406;
        }
        if (head.equals("Z")) {
            return 407;
        }
        return 408;
    }

    public void paint(Graphics g, int x, int y, int maxwidth) {
        g.setFont(UIUtils.TEXT_FONT1);
        g.setColor(Color.WHITE);
        if (this.startTime == 0L) {
            this.startTime = System.currentTimeMillis();
        }
        this.currTime = System.currentTimeMillis();
        int rowWidth = 0;
        int rowHeight = 0;
        int count = this.sectionList.size();
        int start = 0;
        for (int i = 0; i < count; ++i) {
            Object obj = this.sectionList.get(i);
            if (obj instanceof String) {
                String str = (String) obj;
                FontMetrics fm = g.getFontMetrics();
                rowHeight = Math.max(rowHeight, fm.getHeight());
                int dx = fm.stringWidth(str);
                if (rowWidth + dx <= maxwidth) {
                    rowWidth += dx;
                } else {
                    Point p = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                    start = i + 1;
                    rowWidth = p.x;
                    rowHeight = fm.getHeight();
                    x = p.x;
                    y = p.y;
                }
            } else if (obj instanceof Animation) {
                Animation anim = (Animation) obj;
                if (anim.getWidth() + rowWidth > maxwidth) {
                    this.paintRichText(g, x, y, maxwidth, rowHeight, start, i);
                    start = i;
                    x = 0;
                    y += rowHeight;
                    rowWidth = anim.getWidth();
                    rowHeight = anim.getHeight();
                } else {
                    rowHeight = Math.max(rowHeight, anim.getHeight());
                    rowWidth += anim.getWidth();
                }
            } else if (obj instanceof Integer) {
                this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                start = i;
                x = 0;
                y += rowHeight;
                rowWidth = 0;
                rowHeight = 0;
            } else if (obj instanceof InputBean) {
                InputBean bean = (InputBean) obj;
                if (bean.getType() != -1) {
                    String str2 = bean.getName();
                    FontMetrics fm2 = g.getFontMetrics();
                    rowHeight = Math.max(rowHeight, fm2.getHeight());
                    int dx2 = fm2.stringWidth(str2);
                    if (rowWidth + dx2 <= maxwidth) {
                        rowWidth += dx2;
                    } else {
                        Point p2 = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                        start = i + 1;
                        rowWidth = p2.x;
                        rowHeight = fm2.getHeight();
                        x = p2.x;
                        y = p2.y;
                    }
                }
            }
        }
        this.paintRichText(g, x, y, maxwidth, rowHeight, start, count);
    }

    public void paint(Graphics g, int x, int y) {
        if (this.font != null) {
            g.setFont(this.font);
        } else {
            g.setFont(UIUtils.TEXT_FONT);
        }
        g.setColor(Color.WHITE);
        if (this.startTime == 0L) {
            this.startTime = System.currentTimeMillis();
        }
        this.currTime = System.currentTimeMillis();
        int maxwidth = this.getWidth();
        int rowWidth = 0;
        int rowHeight = 0;
        int count = this.sectionList.size();
        int start = 0;
        for (int i = 0; i < count; ++i) {
            Object obj = this.sectionList.get(i);
            if (obj instanceof String) {
                String str = (String) obj;
                FontMetrics fm = g.getFontMetrics();
                rowHeight = Math.max(rowHeight, fm.getHeight());
                int dx = fm.stringWidth(str);
                if (rowWidth + dx <= maxwidth) {
                    rowWidth += dx;
                } else {
                    Point p = this.npcPaintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                    start = i + 1;
                    rowWidth = p.x;
                    rowHeight = fm.getHeight();
                    x = p.x;
                    y = p.y;
                }
            } else if (obj instanceof Animation) {
                Animation anim = (Animation) obj;
                if (anim.getWidth() + rowWidth > maxwidth) {
                    this.npcPaintRichText(g, x, y, maxwidth, rowHeight, start, i);
                    start = i;
                    y += rowHeight;
                    rowWidth = anim.getWidth();
                    rowHeight = anim.getHeight();
                } else {
                    rowHeight = Math.max(rowHeight, anim.getHeight());
                    rowWidth += anim.getWidth();
                }
            } else if (obj instanceof Integer) {
                this.npcPaintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                start = i;
                y += rowHeight;
                rowWidth = 0;
                rowHeight = 0;
            } else if (obj instanceof InputBean) {
                InputBean bean = (InputBean) obj;
                if (bean.getType() != -1) {
                    String str2 = bean.getName();
                    FontMetrics fm2 = g.getFontMetrics();
                    rowHeight = Math.max(rowHeight, fm2.getHeight());
                    int dx2 = fm2.stringWidth(str2);
                    if (rowWidth + dx2 <= maxwidth) {
                        rowWidth += dx2;
                    } else {
                        Point p2 = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                        start = i + 1;
                        rowWidth = p2.x;
                        rowHeight = fm2.getHeight();
                        x = p2.x;
                        y = p2.y;
                    }
                }
            }
        }
        this.npcPaintRichText(g, x, y, maxwidth, rowHeight, start, count);
    }

    private Point npcPaintRichText(Graphics g, int x, int y, int width, int rowh, int start, int end) {
        if (this.lastStr != null) {
            if (this.lastBean != null) {
                Color color = g.getColor();
                FontMetrics fm = g.getFontMetrics();
                if (this.lastBean.getColor2() != null) {
                    g.setColor(this.lastBean.getColor2());
                }
                g.drawString(this.lastStr, 0, y + rowh);
                g.drawString(this.lastStr, 0, y + rowh);
                g.drawString(this.lastStr, 0, y + rowh);
                if (this.lastBean.getType() != 1) {
                    g.drawLine(this.lastBean.isM() ? 1 : 0, y + 3 + rowh + (this.lastBean.isM() ? 1 : 0), fm.stringWidth(this.lastStr) + (this.lastBean.isM() ? 1 : 0), y + 3 + rowh + (this.lastBean.isM() ? 1 : 0));
                }
                g.setColor(color);
            } else {
                g.drawString(this.lastStr, 10, y + rowh);
            }
            this.lastStr = null;
            this.lastBean = null;
        }
        for (int i = start; i < end; ++i) {
            Object obj = this.sectionList.get(i);
            if (obj instanceof String) {
                String str = (String) obj;
                FontMetrics fm2 = g.getFontMetrics();
                int len = str.length();
                int begin = 0;
                int dx = 0;
                int index = 0;
                while (index < len) {
                    for (dx = fm2.charWidth(str.charAt(index)); x + dx <= width && ++index < len; dx += fm2.charWidth(str.charAt(index))) {
                    }
                    String s = str.substring(begin, index);
                    if (i == end - 1 && index >= len && x + dx < width && this.sectionList.size() > end) {
                        this.lastStr = s;
                        Object nextObj = this.sectionList.get(end);
                        if (nextObj instanceof Animation) {
                            Animation anim = (Animation) nextObj;
                            if (anim.getWidth() + x + dx > width) {
                                g.drawString(s, x, y + rowh);
                                this.lastStr = null;
                            }
                        }
                    } else {
                        g.drawString(s, x, y + rowh);
                        g.drawString(s, x, y + rowh);
                        g.drawString(s, x, y + rowh);
                        g.drawString(s, x, y + rowh);
                    }
                    if (index < len || x + dx == width) {
                        begin = index;
                        x = 10;
                        y += rowh;
                        rowh = fm2.getHeight();
                    } else {
                        x += fm2.stringWidth(s);
                    }
                }
            } else if (obj instanceof Color) {
                g.setColor((Color) obj);
            } else if (obj instanceof Animation) {
                Animation anim2 = (Animation) obj;
                anim2.updateToTime(this.currTime - this.startTime);
                g.drawImage(anim2.getImage(), x, y + 5 + rowh - anim2.getHeight(), null);
                x += anim2.getWidth();
            } else if (obj instanceof InputBean) {
                InputBean bean = (InputBean) obj;
                if (bean.getType() != -1) {
                    Color color2 = g.getColor();
                    if (bean.getColor2() != null) {
                        g.setColor(bean.getColor2());
                    }
                    String str2 = bean.getName();
                    FontMetrics fm3 = g.getFontMetrics();
                    if (bean.getHeight() == null) {
                        bean.setS_x(Integer.valueOf(x));
                        bean.setS_y(Integer.valueOf(y - fm3.getHeight() + rowh));
                    }
                    int len2 = str2.length();
                    int begin2 = 0;
                    int dx2 = 0;
                    int index2 = 0;
                    while (index2 < len2) {
                        for (dx2 = fm3.charWidth(str2.charAt(index2)); x + dx2 <= width && ++index2 < len2; dx2 += fm3.charWidth(str2.charAt(index2))) {
                        }
                        String s2 = str2.substring(begin2, index2);
                        if (i == end - 1 && index2 >= len2 && x + dx2 < width && this.sectionList.size() > end) {
                            this.lastStr = s2;
                            this.lastBean = bean;
                            Object nextObj2 = this.sectionList.get(end);
                            if (nextObj2 instanceof Animation) {
                                Animation anim3 = (Animation) nextObj2;
                                if (anim3.getWidth() + x + dx2 > width) {
                                    g.drawString(s2, x + (bean.isM() ? 1 : 0), y + rowh + (bean.isM() ? 1 : 0));
                                    if (bean.getType() != 1) {
                                        g.drawLine(x + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0), x + fm3.stringWidth(s2) + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0));
                                    }
                                    this.lastStr = null;
                                    this.lastBean = null;
                                }
                            }
                        } else {
                            g.drawString(s2, x + (bean.isM() ? 1 : 0), y + rowh + (bean.isM() ? 1 : 0));
                            if (bean.getType() != 1) {
                                g.drawLine(x + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0), x + fm3.stringWidth(s2) + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0));
                            }
                        }
                        if (index2 < len2 || x + dx2 == width) {
                            begin2 = index2;
                            x = 0;
                            y += rowh;
                            rowh = fm3.getHeight();
                        } else {
                            x += fm3.stringWidth(s2);
                        }
                    }
                    if (bean.getHeight() == null) {
                        bean.setHeight(Integer.valueOf(fm3.getHeight()));
                        bean.setE_x(Integer.valueOf(x));
                        bean.setE_y(Integer.valueOf(y - fm3.getHeight() + rowh));
                    }
                    g.setColor(color2);
                }
            }
        }
        this.point.move(x, y);
        return this.point;
    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        if (this.font != null) {
            g.setFont(this.font);
        } else {
            g.setFont(UIUtils.TEXT_FONT);
        }
        g.setColor(Color.WHITE);
        if (this.startTime == 0L) {
            this.startTime = System.currentTimeMillis();
        }
        this.currTime = System.currentTimeMillis();
        int maxwidth = this.getWidth();
        int rowWidth = 0;
        int y = 0;
        int rowHeight = 0;
        int count = this.sectionList.size();
        int start = 0;
        int x = 0;
        for (int i = 0; i < count; ++i) {
            Object obj = this.sectionList.get(i);
            if (obj instanceof String) {
                String str = (String) obj;
                FontMetrics fm = g.getFontMetrics();
                rowHeight = Math.max(rowHeight, fm.getHeight());
                int dx = fm.stringWidth(str);
                if (rowWidth + dx <= maxwidth) {
                    rowWidth += dx;
                } else {
                    Point p = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                    start = i + 1;
                    rowWidth = p.x;
                    rowHeight = fm.getHeight();
                    x = p.x;
                    y = p.y;
                }
            } else if (obj instanceof Animation) {
                Animation anim = (Animation) obj;
                if (anim.getWidth() + rowWidth > maxwidth) {
                    this.paintRichText(g, x, y, maxwidth, rowHeight, start, i);
                    start = i;
                    x = 0;
                    y += rowHeight;
                    rowWidth = anim.getWidth();
                    rowHeight = anim.getHeight();
                } else {
                    rowHeight = Math.max(rowHeight, anim.getHeight());
                    rowWidth += anim.getWidth();
                }
            } else if (obj instanceof Integer) {
                this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                start = i;
                x = 0;
                y += rowHeight;
                rowWidth = 0;
                rowHeight = 0;
            } else if (obj instanceof InputBean) {
                InputBean bean = (InputBean) obj;
                if (bean.getType() != -1) {
                    String str2 = bean.getName();
                    FontMetrics fm2 = g.getFontMetrics();
                    rowHeight = Math.max(rowHeight, fm2.getHeight());
                    int dx2 = fm2.stringWidth(str2);
                    String goodNumType = StringUtils.isNotBlank(bean.getGoodNumType()) ? bean.getGoodNumType() : "0";
                    if (StringUtils.isNotBlank(bean.getGoodNum())) {
                        dx2 = bean.getGoodNum().length() * 14;
                    }
                    if (rowWidth + dx2 <= maxwidth) {
                        rowWidth += dx2;
                    } else {
                        Point p2 = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                        start = i + 1;
                        rowWidth = p2.x;
                        rowHeight = fm2.getHeight();
                        x = p2.x;
                        y = p2.y;
                    }
                }
            }
        }
        this.paintRichText(g, x, y, maxwidth, rowHeight, start, count);
    }

    private Point paintRichText(Graphics g, int x, int y, int width, int rowh, int start, int end) {
        if (this.lastStr != null) {
            if (this.lastBean != null) {
                Color color = g.getColor();
                FontMetrics fm = g.getFontMetrics();
                if (this.lastBean.getColor2() != null) {
                    g.setColor(this.lastBean.getColor2());
                }
                g.drawString(this.lastStr, 0, y + rowh);
                if (this.lastBean.getType() != 1) {
                    g.drawLine(this.lastBean.isM() ? 1 : 0, y + 3 + rowh + (this.lastBean.isM() ? 1 : 0), fm.stringWidth(this.lastStr) + (this.lastBean.isM() ? 1 : 0), y + 3 + rowh + (this.lastBean.isM() ? 1 : 0));
                }
                g.setColor(color);
            } else {
                g.drawString(this.lastStr, 0, y + rowh);
            }
            this.lastStr = null;
            this.lastBean = null;
        }
        for (int i = start; i < end; ++i) {
            Object obj = this.sectionList.get(i);
            if (obj instanceof String) {
                String str = (String) obj;
                FontMetrics fm2 = g.getFontMetrics();
                int len = str.length();
                int begin = 0;
                int dx = 0;
                int index = 0;
                while (index < len) {
                    for (dx = fm2.charWidth(str.charAt(index)); x + dx <= width && ++index < len; dx += fm2.charWidth(str.charAt(index))) {
                    }
                    String s = str.substring(begin, index);
                    if (i == end - 1 && index >= len && x + dx < width && this.sectionList.size() > end) {
                        this.lastStr = s;
                        Object nextObj = this.sectionList.get(end);
                        if (nextObj instanceof Animation) {
                            Animation anim = (Animation) nextObj;
                            if (anim.getWidth() + x + dx > width) {
                                g.drawString(s, x, y + rowh);
                                this.lastStr = null;
                            }
                        }
                    } else {
                        g.drawString(s, x, y + rowh);
                        g.drawString(s, x, y + rowh);
                        g.drawString(s, x, y + rowh);
                    }
                    if (index < len || x + dx == width) {
                        begin = index;
                        x = 0;
                        y += rowh;
                        rowh = fm2.getHeight();
                    } else {
                        x += fm2.stringWidth(s);
                    }
                }
            } else if (obj instanceof Color) {
                g.setColor((Color) obj);
            } else if (obj instanceof Animation) {
                Animation anim2 = (Animation) obj;
                anim2.updateToTime(this.currTime - this.startTime);
                g.drawImage(anim2.getImage(), x, y + 4 + rowh - anim2.getHeight(), null);
                x += anim2.getWidth();
            } else if (obj instanceof InputBean) {
                InputBean bean = (InputBean) obj;
                if (bean.getType() != -1) {
                    if (StringUtils.isNotBlank(bean.getGoodNum())) {
                        String goodNumType = StringUtils.isNotBlank(bean.getGoodNumType()) ? bean.getGoodNumType() : "0";
                        String str2 = bean.getGoodNum();
                        int wh = 14;
                        if (bean.getHeight() == null) {
                            bean.setS_x(Integer.valueOf(x));
                            bean.setS_y(Integer.valueOf(y - wh + rowh));
                        }
                        int len2 = str2.length();
                        int begin2 = 0;
                        int dx2 = 0;
                        int index2 = 0;
                        while (index2 < len2) {
                            for (dx2 = 14; x + dx2 <= width && ++index2 < len2; dx2 += 14) {
                            }
                            String s2 = str2.substring(begin2, index2);
                            if (i == end - 1 && index2 >= len2 && x + dx2 < width && this.sectionList.size() > end) {
                                this.lastStr = s2;
                                this.lastBean = bean;
                                Object nextObj2 = this.sectionList.get(end);
                                if (nextObj2 instanceof Animation) {
                                    Animation anim3 = (Animation) nextObj2;
                                    if (anim3.getWidth() + x + dx2 > width) {
                                        ImageIcon icon = CutButtonImage.getImage("inkImg/number/" + goodNumType + s2 + ".png", 12, 12);
                                        g.drawImage(icon.getImage(), x + (bean.isM() ? 1 : 0), y + (bean.isM() ? 1 : 0), null);
                                        if (bean.getType() != 1) {
                                            g.drawLine(x + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0), x + 14 + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0));
                                        }
                                        this.lastStr = null;
                                        this.lastBean = null;
                                    }
                                }
                            } else if (s2.length() > 0) {
                                String[] singleChar = s2.split("");
                                for (int k = 0; k < singleChar.length; ++k) {
                                    ImageIcon icon = CutButtonImage.getImage("inkImg/number/" + goodNumType + singleChar[k] + ".png", 12, 12);
                                    g.drawImage(icon.getImage(), x + 3 + k * 13 + (bean.isM() ? 1 : 0), y + (rowh - 11) + (bean.isM() ? 1 : 0), null);
                                }
                            }
                            if (index2 < len2 || x + dx2 == width) {
                                begin2 = index2;
                                x = 0;
                                y += rowh;
                                rowh = 14;
                            } else {
                                x += 14 * s2.length();
                            }
                        }
                        if (bean.getHeight() == null) {
                            bean.setHeight(Integer.valueOf(14));
                            bean.setE_x(Integer.valueOf(x));
                            bean.setE_y(Integer.valueOf(y - 14 + rowh));
                        }
                    } else {
                        Color color2 = g.getColor();
                        if (bean.getColor2() != null) {
                            g.setColor(bean.getColor2());
                        }
                        String str2 = bean.getName();
                        FontMetrics fm3 = g.getFontMetrics();
                        if (bean.getHeight() == null) {
                            bean.setS_x(Integer.valueOf(x));
                            bean.setS_y(Integer.valueOf(y - fm3.getHeight() + rowh));
                        }
                        int len2 = str2.length();
                        int begin2 = 0;
                        int dx2 = 0;
                        int index2 = 0;
                        while (index2 < len2) {
                            for (dx2 = fm3.charWidth(str2.charAt(index2)); x + dx2 <= width && ++index2 < len2; dx2 += fm3.charWidth(str2.charAt(index2))) {
                            }
                            String s2 = str2.substring(begin2, index2);
                            if (i == end - 1 && index2 >= len2 && x + dx2 < width && this.sectionList.size() > end) {
                                this.lastStr = s2;
                                this.lastBean = bean;
                                Object nextObj2 = this.sectionList.get(end);
                                if (nextObj2 instanceof Animation) {
                                    Animation anim3 = (Animation) nextObj2;
                                    if (anim3.getWidth() + x + dx2 > width) {
                                        g.drawString(s2, x + (bean.isM() ? 1 : 0), y + rowh + (bean.isM() ? 1 : 0));
                                        if (bean.getType() != 1) {
                                            g.drawLine(x + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0), x + fm3.stringWidth(s2) + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0));
                                        }
                                        this.lastStr = null;
                                        this.lastBean = null;
                                    }
                                }
                            } else {
                                g.drawString(s2, x + (bean.isM() ? 1 : 0), y + rowh + (bean.isM() ? 1 : 0));
                                if (bean.getType() != 1 && bean.getType() != 5) {
                                    g.drawLine(x + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0), x + fm3.stringWidth(s2) + (bean.isM() ? 1 : 0), y + 3 + rowh + (bean.isM() ? 1 : 0));
                                }
                            }
                            if (index2 < len2 || x + dx2 == width) {
                                begin2 = index2;
                                x = 0;
                                y += rowh;
                                rowh = fm3.getHeight();
                            } else {
                                x += fm3.stringWidth(s2);
                            }
                        }
                        if (bean.getHeight() == null) {
                            bean.setHeight(Integer.valueOf(fm3.getHeight()));
                            bean.setE_x(Integer.valueOf(x));
                            bean.setE_y(Integer.valueOf(y - fm3.getHeight() + rowh));
                        }
                        g.setColor(color2);
                    }
                }
            }
        }
        if (this.point != null)
            this.point.move(x, y);
        return this.point;
    }

    public void setText(String text) {
        this.text = text;
        this.sectionList.clear();
        this.isInteractive = false;
        this.parseText(text);
    }

    public void setTextSize(String text, int size) {
        this.text = text;
        this.sectionList.clear();
        this.isInteractive = false;
        this.parseText(text);
        this.setSize(this.computeSize(size));
    }

    public void addText(String text) {
        this.text += text;
        this.parseText(text);
    }

    public void addText(String text, Font font) {
        this.text += text;
        this.font = font;
        this.parseText(text);
    }

    public String getText() {
        return this.text;
    }

    public void remove() {
        this.sectionList.clear();
        this.isInteractive = false;
        this.disable();
    }

    public static int fontWidth(Font font, String text) {
        synchronized (RichLabel.temp) {
            Graphics g = RichLabel.temp.getGraphics();
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            return fm.stringWidth(text);
        }
    }

    public Dimension computeSize(int maxwidth) {
        if (this.sectionList.size() == 0) {
            return new Dimension(1, 1);
        }
        synchronized (RichLabel.temp) {
            int rowWidth = 0;
            int y = 0;
            int rowHeight = 0;
            int count = this.sectionList.size();
            int start = 0;
            int x = 0;
            Graphics g = RichLabel.temp.getGraphics();
            if (this.font != null) {
                g.setFont(this.font);
            } else {
                g.setFont(UIUtils.TEXT_FONT);
            }
            boolean is = false;
            for (int i = 0; i < count; ++i) {
                Object obj = this.sectionList.get(i);
                if (obj instanceof String) {
                    String str = (String) obj;
                    FontMetrics fm = g.getFontMetrics();
                    rowHeight = Math.max(rowHeight, fm.getHeight());
                    int dx = fm.stringWidth(str);
                    if (rowWidth + dx <= maxwidth) {
                        rowWidth += dx;
                    } else {
                        Point p = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                        start = i + 1;
                        rowWidth = p.x;
                        rowHeight = fm.getHeight();
                        x = p.x;
                        y = p.y;
                    }
                } else if (obj instanceof Animation) {
                    Animation anim = (Animation) obj;
                    if (anim.getWidth() + rowWidth > maxwidth) {
                        this.paintRichText(g, x, y, maxwidth, rowHeight, start, i);
                        start = i;
                        x = 0;
                        y += rowHeight;
                        rowWidth = anim.getWidth();
                        rowHeight = anim.getHeight();
                    } else {
                        rowHeight = Math.max(rowHeight, anim.getHeight());
                        rowWidth += anim.getWidth();
                    }
                } else if (obj instanceof Integer) {
                    this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                    start = i;
                    x = 0;
                    y += rowHeight;
                    rowWidth = 0;
                    rowHeight = 0;
                } else if (obj instanceof InputBean) {
                    InputBean bean = (InputBean) obj;
                    if (bean.getType() == -1) {
                        is = true;
                    } else if (StringUtils.isNotBlank(bean.getGoodNum())) {
                        String str2 = bean.getGoodNum();
                        rowHeight = Math.max(rowHeight, 14);
                        int dx = str2.length() * 14;
                        if (rowWidth + dx <= maxwidth) {
                            rowWidth += dx;
                        } else {
                            Point p = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                            start = i + 1;
                            rowWidth = p.x;
                            rowHeight = 14;
                            x = p.x;
                            y = p.y;
                        }
                    } else {
                        String str2 = bean.getName();
                        FontMetrics fm2 = g.getFontMetrics();
                        rowHeight = Math.max(rowHeight, fm2.getHeight());
                        int dx2 = fm2.stringWidth(str2);
                        if (rowWidth + dx2 <= maxwidth) {
                            rowWidth += dx2;
                        } else {
                            Point p2 = this.paintRichText(g, x, y, maxwidth, rowHeight, start, i + 1);
                            start = i + 1;
                            rowWidth = p2.x;
                            rowHeight = fm2.getHeight();
                            x = p2.x;
                            y = p2.y;
                        }
                    }
                }
            }
            Point p3 = this.paintRichText(g, x, y, maxwidth, rowHeight, start, count);
            if (is) {
                maxwidth = 0;
            } else if (y == 0 && p3.x != 0) {
                maxwidth = p3.x;
            }
            return new Dimension(maxwidth, y + rowHeight + 4);
        }
    }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {
    }

    public InputBean isMonitor(int x, int y) {
        if (this.isInteractive) {
            for (int count = this.sectionList.size(), i = 0; i < count; ++i) {
                Object obj = this.sectionList.get(i);
                if (obj instanceof InputBean) {
                    InputBean bean = (InputBean) obj;
                    if (bean.getType() != -1 && bean.isMonitor(x, y)) {
                        return bean;
                    }
                }
            }
        }
        return null;
    }

    public String gettzm(String value) {
        String name = "";
        String namet = "";
        Pattern regex = Pattern.compile("\\【([^】]*)\\】");
        Matcher matcher = regex.matcher(value);
        while (matcher.find()) {
            if (matcher.group(1).contains("套装名称")) {
                name = matcher.group(1).split(":")[1];
                break;
            }
        }
        AllSuit allSuit = UserMessUntil.getAllSuit();
        Map<Integer, RoleSuitBean> sas = allSuit.getRolesuit();
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure) item.get(new BigDecimal(1));
        if (configure.getLzjskg().equals("5")) {
            for (int i = 1; i < sas.size(); ++i) {
                RoleSuitBean bean = (RoleSuitBean) sas.get(Integer.valueOf(i));
                if (bean != null && bean.getSname().equals(name)) {
                    namet = bean.getSuitID() + "=" + bean.getHaveParts();
                    break;
                }
            }
        } else {
            for (int i = 1; i < 103; ++i) {
                RoleSuitBean bean = (RoleSuitBean) sas.get(Integer.valueOf(i));
                if (bean != null && bean.getSname().equals(name)) {
                    namet = bean.getSuitID() + "=" + bean.getHaveParts();
                    break;
                }
            }
        }
        return namet;
    }

    public boolean getjntp(int val) {
        return val >= 6001 && val <= 6042;
    }

    public boolean getbstp(int val) {
        return val >= 746 && val <= 769;
    }

    static {
        RichLabel.num = new ArrayList<>();
        RichLabel.faceAnimations = new HashMap(100);
        String REGULAR = "#([RGBKYWTXJESQDPUVLZnbur#]|[1][0-5][0-9]|[1][6][0-8]|[6][0][0-4][0-9]|[x][6-9][0-9]|[1][0-5][0-9]|[7][4-6][0-9]|[7][0-1][0-9]|[9][1-9][0-9][0-9][0-9][0-9]|[9][1-9][0-9][0-9][0-9]|[9][1-9][0-9][0-9]|[8][8][0-5]|[1-9]\\d|[0-9]|"//修复装备打造宝石以后不能使用的问题
                + "c[0-9A-Fa-f]?[0-9A-Fa-f]?[0-9A-Fa-f]?[0-9A-Fa-f]?[0-9A-Fa-f]?[0-9A-Fa-f]?)|"
                + "[^#]+";
        RichLabel.pattern = Pattern.compile(REGULAR);
        RichLabel.PATH = "resource/emoticons/";
        RichLabel.temp = new BufferedImage(1, 1, 8);
    }
}
