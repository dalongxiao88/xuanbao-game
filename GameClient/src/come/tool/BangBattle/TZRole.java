package come.tool.BangBattle;

import java.awt.Graphics;
import java.awt.Image;
import com.tool.tcpimg.RichLabel;

public class TZRole
{
    private RichLabel name;
    private RichLabel lvl;
    private Image head;
    
    public void drawFont(Graphics g, int x, int y) {
        g.translate(x, y);
        this.name.paint(g);
        g.translate(120, 0);
        x += 120;
        this.lvl.paint(g);
        g.translate(-x, -y);
    }
    
    public Image getHead() {
        return this.head;
    }
    
    public void setHead(Image head) {
        this.head = head;
    }
}
