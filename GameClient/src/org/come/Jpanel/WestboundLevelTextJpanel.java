
package org.come.Jpanel;

import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 八十一难
 * 
 * @author admin
 *
 */
public class WestboundLevelTextJpanel extends JPanel {

	public static boolean falg = false;
	public static boolean GBTS = false;
	
	private int currentChar = 0;
	private Timer timer; // 定时器变量
	public static String text = "";
	
	public static String[] basynText = {
		    "第一难：金蝉遭贬", "第二难：出胎几杀", "第三难：满月抛江",
		    "第四难：寻亲报冤", "第五难：出城逢虎", "第六难：落坑折从", "第七难：双叉岭上", "第八难：两界山头",
		    "第九难：陡涧换马", "第十难：夜被火烧", "第十一难：失却袈裟", "第十二难：收降八戒", "第十三难：黄风怪阻",
		    "第十四难：请求灵吉", "第十五难：流沙难渡", "第十六难：收得沙僧", "第十七难：四圣显化", "第十八难：五庄观中",
		    "第十九难：难活人参", "第二十难：贬退心猿", "第二十一难：黑松林失散", "第二十二难：宝象国捎书",
		    "第二十三难：金銮殿变虎", "第二十四难：平顶山逢魔", "第二十五难：莲花洞高悬", "第二十六难：乌鸡国救主",
		    "第二十七难：被魔化身", "第二十八难：号山逢怪", "第二十九难：风摄圣僧", "第三十难：心猿遭害",
		    "第三十一难：请圣降妖", "第三十二难：黑河沉没", "第三十三难：搬运车迟", "第三十四难：大赌输赢",
		    "第三十五难：祛道兴僧", "第三十六难：路逢大水", "第三十七难：身落天河", "第三十八难：鱼篮现身",
		    "第三十九难：金兜山遇怪", "第四十难：普天神难伏", "第四十一难：问佛根源", "第四十二难：吃水遭毒",
		    "第四十三难：西梁国留婚", "第四十四难：琵琶洞受苦", "第四十五难：再贬心猿", "第四十六难：难辨猕猴",
		    "第四十七难：路阻火焰山", "第四十八难：求取芭蕉扇", "第四十九难：收缚魔王", "第五十难：赛城扫塔",
		    "第五十一难：取宝救僧", "第五十二难：棘林吟咏", "第五十三难：小雷音遇难", "第五十四难：诸天神遭困",
		    "第五十五难：稀柿衕秽阻", "第五十六难：朱紫国行医", "第五十七难：拯救疲癃", "第五十八难：降妖取后",
		    "第五十九难：七情迷没", "第六十难：多目遭伤", "第六十一难：路阻狮驼", "第六十二难：怪分三色",
		    "第六十三难：城里遇灾", "第六十四难：请佛收魔", "第六十五难：比丘救子", "第六十六难：辨认真邪",
		    "第六十七难：松林救怪", "第六十八难：僧房卧病", "第六十九难：无底洞遭困", "第七十难：灭法国难行",
		    "第七十一难：隐雾山遇魔", "第七十二难：凤仙郡求雨", "第七十三难：失落兵器", "第七十四难：会庆钉钯",
		    "第七十五难：竹节山遭", "第七十六难：玄英洞受苦", "第七十七难：赶捉犀牛", "第七十八难：天竺招婚",
		    "第七十九难：铜台府监禁", "第八十难：凌云渡脱胎", "第八十一难：通天河遇鼋湿经书"
		};
	
	private static Sprite tsz = SpriteFactory.VloadSprite("difficult/唐三藏.was", null);//特效路径
	public WestboundLevelTextJpanel() {
		this.setPreferredSize(new Dimension(315, 112));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);
	}

	public ImageIcon icon1 = CutButtonImage.getImage("difficult/提示框.png", 315, 112);
//	public Image icon1 = ZipUtils.read(ZipUtils.getInputStream("difficult/提示框.png"));
	
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(icon1.getImage(), 0, 0, 315, 112, this);
	    
	    if (GBTS) {
	        String displayedText = text.substring(0, currentChar);
	        int breakIndex = 13; // 设置换行的位置
	        String firstLine, secondLine;
	        
	        if (currentChar > breakIndex) {
	            firstLine = displayedText.substring(0, breakIndex);
	            secondLine = displayedText.substring(breakIndex); // 剩下的字符
	        } else {
	            firstLine = displayedText;
	            secondLine = "";
	        }
	        
	        g.setColor(Color.YELLOW);
	        g.setFont(UIUtils.TEXT_NAME_FONT);
	        
	        // 计算文本宽度
	        FontMetrics fm = g.getFontMetrics();
	        int x1 = (getWidth() - fm.stringWidth(firstLine)) / 2;
	        int y1 = 40; // 第一行的y坐标
	        
	        int x2 = (getWidth() - fm.stringWidth(secondLine)) / 2;
	        int y2 = y1 + fm.getHeight(); // 第二行的y坐标
	        
	        // 绘制文本
	        g.drawString(firstLine, x1, y1);
	        if (!secondLine.isEmpty()) {
	            g.drawString(secondLine, x2, y2);
	        }
	    }
	    g.setColor(Color.white);
	    g.drawString("点击继续", 220, 80);
	}

	
	public void initTimer() {
		if (timer != null && timer.isRunning()) {
			timer.stop(); // 如果定时器正在运行，先停止
		}
		currentChar = 0; // 重置当前字符计数
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentChar++;
				if (currentChar >= text.length()) {
					currentChar = text.length();
					timer.stop(); // 停止定时器
				}
				repaint(); // 重绘面板
			}
		});
		timer.start(); // 启动定时器
	}
}
