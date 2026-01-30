
package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.CoordinateUtil;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 八十一难
 * 
 * @author admin
 *
 */
public class WestboundLevelSubJpanel extends JPanel {

	private ImageIcon imgwc = CutButtonImage.getImage("inkImg/hongmu/完成.png", 50, 45);

//	private static BjczBtn addBtn;

	public static Goodstable goods;

	public static JLabel[] sxmLab,sxzLab,sximgLab; 
	
	public static boolean falg = false;
	
	private static Sprite[] triples = new Sprite[81];
	private static JLabel[] textImgLabs = new JLabel[81];
	private static JLabel[] textLabs = new JLabel[81];
	
	private static Map<Integer,CoordinateUtil> xysMap = new HashMap<Integer, CoordinateUtil>(); 
	
	private static Map<Integer,CoordinateUtil> lxxysMap = new HashMap<Integer, CoordinateUtil>(); 
	
	public static int POX = 0;
	
	public static int createNum = RoleData.getRoleData().getLoginResult().getDifficultLevel();
	
	private static Sprite dqgk = SpriteFactory.VloadSprite("difficult/打架图标.was", null);//特效路径
	private static Sprite dqstgk = SpriteFactory.VloadSprite("difficult/师徒4人.was", null);//特效路径
	
	private static Sprite[] lxs = new Sprite[81];
	
	public ImageIcon wlqicon = CutButtonImage.getImage("img/item/79F3BED9.png", 40, 40);
	public ImageIcon ylqicon = CutButtonImage.getImage("img/item/E4EB4E88.png", 40, 40);
//	public Image wlqicon = ZipUtils.read(ZipUtils.getInputStream("difficult/79F3BED9.png"));
//	public Image ylqicon = ZipUtils.read(ZipUtils.getInputStream("difficult/E4EB4E88.png"));
	
	public static int lbnum = 0;
	
	public static String[] basynText = {"金蝉遭贬","出胎几杀","满月抛江",
			"寻亲报冤","出城逢虎","落坑折从","双叉岭上","两界山头","陡涧换马","夜被火烧",
			"失却袈裟","收降八戒","黄风怪阻","请求灵吉","流沙难渡","收得沙僧","四圣显化",
			"五庄观中","难活人参","贬退心猿","黑松林失散","宝象国捎书","金銮殿变虎","平顶山逢魔",
			"莲花洞高悬","乌鸡国救主","被魔化身","号山逢怪","风摄圣僧","心猿遭害","请圣降妖",
			"黑河沉没","搬运车迟","大赌输赢","祛道兴僧","路逢大水","身落天河","鱼篮现身",
			"金兜山遇怪","普天神难伏","问佛根源","吃水遭毒","西梁国留婚","琵琶洞受苦",
			"再贬心猿","难辨猕猴","路阻火焰山","求取芭蕉扇","收缚魔王","赛城扫塔",
			"取宝救僧","棘林吟咏","小雷音遇难","诸天神遭困","稀柿衕秽阻","朱紫国行医","拯救疲癃","降妖取后",
			"七情迷没","多目遭伤","路阻狮驼","怪分三色","城里遇灾","请佛收魔","比丘救子","辨认真邪","松林救怪",
			"僧房卧病","无底洞遭困","灭法国难行","隐雾山遇魔","凤仙郡求雨","失落兵器","会庆钉钯","竹节山遭","玄英洞受苦","赶捉犀牛",
			"天竺招婚","铜台府监禁","凌云渡脱胎","通天河遇鼋湿经书"};
	//礼包ID
	public static String[] lbidText = {"66788","66788","66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788","66788",
			"66788","66788","66788","66788"};
	public WestboundLevelSubJpanel() {
		createNum = RoleData.getRoleData().getLoginResult().getDifficultLevel();;
		this.setPreferredSize(new Dimension(5700, 280));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);
		lxxy();
		sxmLab = new JLabel[4];
		sxzLab = new JLabel[4];
		sximgLab = new JLabel[4];
		Font font = new Font("隶书", 0, 10);
		for(int i=0;i<81;i++) {
			if(i>7) {
				lxs[i] = SpriteFactory.VloadSprite("difficult/路线"+(i%8+1)+".was", null);//特效路径
			}else {
				lxs[i] = SpriteFactory.VloadSprite("difficult/路线"+(i+1)+".was", null);//特效路径
			}
			if(i>7) {
				triples[i] = SpriteFactory.VloadSprite("difficult/建筑"+(i%8)+".was", null);//特效路径
				xysMap.put(i, setXy(i%8,i/8));
			}else {
				triples[i] = SpriteFactory.VloadSprite("difficult/建筑"+(i)+".was", null);//特效路径
				xysMap.put(i, setXy(i,i/8));
			}
		}
		for(int i=0;i<81;i++) {
			textLabs[i] = new JLabel();
            textLabs[i].setForeground(Color.black);
            textLabs[i].setFont(font);
            textLabs[i].setText(basynText[i]);
            add(textLabs[i]);
            textImgLabs[i] = new JLabel();
            textImgLabs[i].setIcon(wlqicon);
            textImgLabs[i].setBounds(xysMap.get(i).getX()-20-POX, xysMap.get(i).getY()+40, 40, 40);
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(lbidText[i]));
            int x = i;
            textImgLabs[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					ZhuFrame.getZhuJpanel().cleargoodtext();
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(RoleData.getRoleData().getLoginResult().getDifficultLevel()>x) {
						String msg = RoleData.getRoleData().getLoginResult().getDifficultrecord();
						List<Integer> sl = new ArrayList<>();
						if(StringUtils.isNotEmpty(msg)) {
							String[] lqmsg = msg.split("\\|");
							if(lqmsg!=null) {
								for(String c : lqmsg) {
									if(StringUtils.isNotBlank(c)){
										sl.add(Integer.parseInt(c));
									}

								}
							}
						}
						if(sl==null || !sl.contains(x+1)) {
							SendMessageUntil.toServer(Agreement.getAgreement().userAgreement("八十一难领取="+(x+1)));
						}else {
							ZhuFrame.getZhuJpanel().addPrompt("您已领取过该礼包！");
						}
					 }else {
						ZhuFrame.getZhuJpanel().addPrompt("请先通过本关卡！");
					}
				}
			});
            add(textImgLabs[i]);
		}
		WestboundLevelSubJpanel.POX = (5700/81)*WestboundLevelSubJpanel.createNum;
		
		
		WestboundLevelJpanel.updateDW();
	}

	public ImageIcon icon1 = CutButtonImage.getImage("difficult/ziditu.png", 5700, 280);
//	public Image icon1 = ZipUtils.read(ZipUtils.getInputStream("difficult/ziditu.png"));
	

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(icon1.getImage(), 0, 0, 5700, 280, this);
		int xx = createNum = RoleData.getRoleData().getLoginResult().getDifficultLevel();
		dqstgk.updateToTime(ImageMixDeal.userimg.getTime(), 0);
		dqstgk.draw(g, xysMap.get(createNum>80?80:createNum).getX()-POX, xysMap.get(createNum>80?80:createNum).getY()+50);
		String msg = RoleData.getRoleData().getLoginResult().getDifficultrecord();
		int num = 0;
		List<Integer> sl = new ArrayList<>(); 
		if(StringUtils.isNotEmpty(msg)) {
			String[] lqmsg = msg.split("\\|");
			if(lqmsg!=null) {
				for(String c : lqmsg) {
					if(StringUtils.isNotEmpty(c) && !c.equals("null")) {
						sl.add(Integer.parseInt(c));
					}
				}
			}
		}
		for(int i=0;i<81;i++) {
			int x = xysMap.get(i).x - POX;
            int y = xysMap.get(i).y - 10;
            // 保存当前的变换状态
            AffineTransform oldTransform = g2d.getTransform();

            // 平移到文字位置
            g2d.translate(x, y);

            // 设置颜色和字体
            g2d.setColor(Color.BLACK);
            g2d.setColor(new Color(139, 0, 0));
            g2d.setFont(new Font("隶书", 0, 14));

            // 获取文字内容
            String text = textLabs[i].getText();

            // 计算每个字符的高度
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int charHeight = fontMetrics.getAscent();

            // 逐字符绘制
            for (int j = 0; j < text.length(); j++) {
                char character = text.charAt(j);
                // 绘制字符，每个字符向下偏移一个字符的高度
                g2d.drawString(String.valueOf(character), 0, j * charHeight);
            }
            // 恢复之前的变换状态
            g2d.setTransform(oldTransform);
			
			triples[i].updateToTime(ImageMixDeal.userimg.getTime(), 0);
			triples[i].draw(g, xysMap.get(i).getX()-POX, xysMap.get(i).getY());
			
			lxs[i].updateToTime(1, 0);
			lxs[i].draw(g, xysMap.get(i).getX()+lxxysMap.get(i%8).getX()-POX, xysMap.get(i).getY()+lxxysMap.get(i%8).getY());
			
			if(sl.isEmpty() || !sl.contains(i+1)) {
				textImgLabs[i].setIcon(wlqicon);
			}else {
				textImgLabs[i].setIcon(ylqicon);
			}
			textImgLabs[i].setBounds(xysMap.get(i).getX()-20-POX, xysMap.get(i).getY()+40, 40, 40);
		}
		if(createNum<80) {
			dqgk.updateToTime(ImageMixDeal.userimg.getTime(), 0);
			dqgk.draw(g, xysMap.get(createNum>80?80:createNum).getX()+20-POX, xysMap.get(createNum>80?80:createNum).getY()-30);
		}
		
	}

	public static void updateLhsx() {}
	
	
	
	public CoordinateUtil setXy(int num, int b) {
		int x = 575;
		CoordinateUtil coordinateUtil = new CoordinateUtil();
		if (num == 0) {
			coordinateUtil.setX(55 + x * b);
			coordinateUtil.setY(30);
		} else if (num == 1) {
			coordinateUtil.setX(163 + x * b);
			coordinateUtil.setY(69);
		} else if (num == 2) {
			coordinateUtil.setX(33 + x * b);
			coordinateUtil.setY(149);
		} else if (num == 3) {
			coordinateUtil.setX(140 + x * b);
			coordinateUtil.setY(199);
		} else if (num == 4) {
			coordinateUtil.setX(330 + x * b);
			coordinateUtil.setY(189);
		} else if (num == 5) {
			coordinateUtil.setX(320 + x * b);
			coordinateUtil.setY(90);
		} else if (num == 6) {
			coordinateUtil.setX(450 + x * b);
			coordinateUtil.setY(140);
		} else if (num == 7) {
			coordinateUtil.setX(443 + x * b);
			coordinateUtil.setY(30);
		}
		return coordinateUtil;
	}
	
	public void lxxy() {
		CoordinateUtil coordinateUtil = new CoordinateUtil();
		for(int i=0;i<8;i++) {
			coordinateUtil = new CoordinateUtil();
			if(i==0) {
				coordinateUtil.setX(60);
				coordinateUtil.setY(25);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==1) {
				coordinateUtil.setX(-90);
				coordinateUtil.setY(25);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==2) {
				coordinateUtil.setX(60);
				coordinateUtil.setY(30);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==3) {
				coordinateUtil.setX(100);
				coordinateUtil.setY(20);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==4) {
				coordinateUtil.setX(10);
				coordinateUtil.setY(-80);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==5) {
				coordinateUtil.setX(20);
				coordinateUtil.setY(25);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==6) {
				coordinateUtil.setX(20);
				coordinateUtil.setY(-50);
				lxxysMap.put(i, coordinateUtil);
			}else if(i==7) {
				coordinateUtil.setX(100);
				coordinateUtil.setY(30);
				lxxysMap.put(i, coordinateUtil);
			}
		}
	}
	
}
