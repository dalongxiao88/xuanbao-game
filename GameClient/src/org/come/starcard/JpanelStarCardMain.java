package org.come.starcard;

import org.come.Frame.TestpackJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.Util;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import java.util.ArrayList;
import org.come.bean.SuitOperBean;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import java.awt.Color;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import com.tool.tcpimg.RichLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 星卡主面板
 * <p>
 * Title : JpanelStarCardMain
 * </p>
 *
 * @author : HGC
 * @date : 2019年8月6日 上午10:38:11
 * @version : 1.0.0
 */
public class JpanelStarCardMain extends JPanel
{
    /**
     * cardBackImg星卡图片边框<br>
     * cardImg星卡图片(属性)<br>
     * starCardImg星卡图片(重铸)<br>
     * materialsImgs消耗材料图片<br>
     * radioOneImg单选第一个<br>
     * radioTwoImg单选第二个<br>
     * consumeOneLab消耗栏第一个<br>
     * consumeTwoLab消耗栏第二个<br>
     * starArryBackImg星阵边框<br>
     * starArryImg星阵图片<br>
     */
    private JLabel cardBackImg;
    private JLabel cardImg;
    private JLabel starCardImg;
    private JLabel materialsImgs;
    private JLabel radioOneImg;
    private JLabel radioTwoImg;
    private JLabel consumeOneLab;
    private JLabel consumeTwoLab;
    private JLabel starArrayBackImg;
    private JLabel starArryImg;
    private BtnStarCard lockBtn;
    private BtnStarCard unlockBtn;
    private BtnStarCard attributeMenuBtn;
    private BtnStarCard recastMenuBtn;
    private BtnStarCard cultivateBtn;
    private BtnStarCard lianStarBtn;
    private BtnStarCard upgradeBtn;
    private BtnStarCard redoBtn;
    private BtnStarCard caozuoBtn;
    private BtnStarCard joinBtn;
    private BtnStarCard fetchBtn;
    private BtnStarCard soulBackBtn;
    private BtnStarCard exchangeBtn;
    private BtnStarCard supplementBtn;
    private BtnStarCard deleteBtn;
    private BtnStarCard transferBtn;
    private JLabel[] materialsLab;
    private JLabel[] starCardLab;
    private RichLabel nameLvlLab;
    private RichLabel powerLab;
    private RichLabel fiveElementsLab;
    private RichLabel StarArrayLab;
    private int bigType;
    private int smallType;
    private int radioType;
    /** 当前选中的星卡Id */
    private BigDecimal chooseStarCardId;
    /**
     * 当前选中的材料Id
     */
    private BigDecimal chooseMaterialsId;
    /** 消耗金钱 */
    private BigDecimal money;
    private long materialType;
    private ImageIcon iconBack;
    private ImageIcon iconLeft;
    private ImageIcon iconRight;
    private ImageIcon iconName;
    private ImageIcon iconWire;
    
    public JpanelStarCardMain() {
        this.bigType = -1;
        this.smallType = -1;
        this.radioType = -1;
        this.materialType = 0L;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(637, 425));
            this.setOpaque(false);
            this.setLayout(null);
            // 关闭按钮事件
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 95);
            offBtn.setBounds(600, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(611, 447));
            this.setOpaque(false);
            this.setLayout(null);
            // 关闭按钮事件
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 95);
            offBtn.setBounds(586, 0, 25, 25);
            this.add(offBtn);
        }
        /** 添加组件 */
        this.getCardBackImg();
        this.getCardImg();
        this.getLockBtn();
        this.getUnlockBtn();
        this.getAttributeMenuBtn();
        this.getRecastMenuBtn();
        this.getCultivateBtn();
        this.getLianStarBtn();
        this.getUpgradeBtn();
        this.getRedoBtn();
        this.getStarCardImg();
        this.getMaterialsImgs();
        this.getRadioTwoImg();
        this.getRadioOneImg();
        this.getSupplementBtn();
        this.getConsumeTwoLab();
        this.getConsumeOneLab();
        this.getMaterialsLab();
        this.getStarCardLab();
        this.getCaozuoBtn();
        this.getJoinBtn();
        this.getFetchBtn();
        this.getSoulBackBtn();
        this.getExchangeBtn();
        this.getNameLvlLab();
        this.getPowerLab();
        this.getFiveElementsLab();
        this.getStarArrayLab();
        this.getStarArryImg();
        this.getStarArrayBackImg();
        this.getDeleteBtn();
        this.getTransferBtn();
        this.changeBigMenuLeftView(1);
    }
    /**
     * 大菜单项切换面板
     *
     * @param type
     */
    public void changeBigMenuLeftView(int type) {
        if (type == -1) {
            if (this.bigType == -1) {
                type = 1;
            }
            else {
                return;
            }
        }
        this.bigType = type;
        this.clearAttributeShow();
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.bigType == 1) {
                this.viewRecastMenuBtn(false);
                this.viewAttributeMenuBtn(true);
                this.iconLeft = CutButtonImage.getImage("inkImg/background1/B284.png", 159, 114);
            }
            else if (this.bigType == 2) {
                this.viewAttributeMenuBtn(false);
                this.viewRecastMenuBtn(true);
                this.iconLeft = CutButtonImage.getImage("inkImg/background1/B285.png", 309, 105);
            }
        }
        else if (this.bigType == 1) {
            this.viewRecastMenuBtn(false);
            this.viewAttributeMenuBtn(true);
            this.iconLeft = CutButtonImage.getImage("img/xy2uiimg/战力显示框-W：162-H：115.png", 162, 114);
        }
        else if (this.bigType == 2) {
            this.viewAttributeMenuBtn(false);
            this.viewRecastMenuBtn(true);
            this.iconLeft = CutButtonImage.getImage("img/xy2uiimg/下面面板框-W：309-H：105.png", 309, 105);
        }
    }
    /**
     * 小菜单项面板切换
     *
     */
    public void changeSmallMenuLeftView(int type) {
        if (type == -1) {
            if (this.smallType == -1) {
                type = 1;
            }
            else {
                this.viewChange(false);
                return;
            }
        }
        this.viewChange(false);
        if (this.bigType == 2) {
            this.smallType = type;
            this.viewChange(true);
        }
    }
    /**
     * 切换重洗面板单选框展示面板
     */
    public void changeRadioView(int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type == -1) {
                if (this.radioType == -1) {
                    type = 1;
                    this.radioType = type;
                }
                if (this.chooseMaterialsId != null) {
                    this.recoverMaterialsImg();
                }
                if (this.radioType == 1) {
                    this.materialType = 524L;
                    this.materialsImgs.setText("浑天石");
                }
                else {
                    this.materialType = 523L;
                    this.materialsImgs.setText("易象符");
                }
            }
            else {
                this.radioType = type;
                if (this.radioType == 1) {
                    this.materialType = 524L;
                    this.radioOneImg.setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
                    this.radioTwoImg.setIcon(null);
                    this.materialsImgs.setText("浑天石");
                }
                else if (this.radioType == 2) {
                    this.materialType = 523L;
                    this.radioOneImg.setIcon(null);
                    this.radioTwoImg.setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
                    this.materialsImgs.setText("易象符");
                }
                if (this.chooseMaterialsId != null) {
                    this.recoverMaterialsImg();
                }
            }
        }
        else if (type == -1) {
            if (this.radioType == -1) {
                type = 1;
                this.radioType = type;
            }
            if (this.chooseMaterialsId != null) {
                this.recoverMaterialsImg();
            }
            if (this.radioType == 1) {
                this.materialType = 524L;
                this.materialsImgs.setText("浑天石");
            }
            else {
                this.materialType = 523L;
                this.materialsImgs.setText("易象符");
            }
        }
        else {
            this.radioType = type;
            if (this.radioType == 1) {
                this.materialType = 524L;
                this.radioOneImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
                this.radioTwoImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 19, 19));
                this.materialsImgs.setText("浑天石");
            }
            else if (this.radioType == 2) {
                this.materialType = 523L;
                this.radioOneImg.setIcon(null);
                this.radioOneImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 19, 19));
                this.radioTwoImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
                this.materialsImgs.setText("易象符");
            }
            if (this.chooseMaterialsId != null) {
                this.recoverMaterialsImg();
            }
        }
    }
    /** 清空并恢复第一个展示框 */
    public void recoverStarCardImg() {
        this.starCardImg.setIcon(null);
        this.starCardImg.setText("地煞星");
        this.chooseStarCardId = null;
    }
    /** 选中星卡展示 */
    public void chooseStarCardImg(Goodstable goodstable) {
        this.chooseStarCardId = goodstable.getRgid();
        this.starCardImg.setText("");
        this.starCardImg.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
    }
    /** 选中材料展示 */
    public void chooseMaterialsImg(Goodstable goodstable) {
        this.chooseMaterialsId = goodstable.getRgid();
        this.materialsImgs.setText("");
        this.materialsImgs.setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 55, 53));
    }
    /** 清空选中的材料 */
    public void recoverMaterialsImg() {
        this.chooseMaterialsId = null;
        if (this.smallType == 1) {
            this.materialsImgs.setText("观星册");
        }
        else if (this.smallType == 2) {
            this.materialsImgs.setText("矿石");
        }
        else if (this.smallType == 3) {
            this.changeRadioView(-1);
        }
        else if (this.smallType == 4) {
            this.materialsImgs.setText("炼星石");
        }
        this.materialsImgs.setIcon(null);
    }
    /**
     * 根据小菜单项编号隐藏或者展示对应面板
     *
     */
    public void viewChange(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.smallType == 1) {
                this.iconRight = CutButtonImage.getImage("inkImg/background1/B289.png", 290, 200);
                this.viewCultivateBtn(type);
            }
            else if (this.smallType == 2) {
                this.iconRight = CutButtonImage.getImage("inkImg/background1/B290.png", 290, 200);
                this.viewUpgradeBtn(type);
            }
            else if (this.smallType == 3) {
                this.iconRight = CutButtonImage.getImage("inkImg/background1/B291.png", 290, 200);
                this.viewRedoBtn(type);
            }
            else if (this.smallType == 4) {
                this.iconRight = CutButtonImage.getImage("inkImg/background1/B288.png", 290, 200);
                this.viewLianStarBtn(type);
            }
        }
        else if (this.smallType == 1) {
            this.iconRight = CutButtonImage.getImage("img/xy2uiimg/培养面板-W：288-H：198.png", 288, 198);
            this.viewCultivateBtn(type);
        }
        else if (this.smallType == 2) {
            this.iconRight = CutButtonImage.getImage("img/xy2uiimg/升级面板-W：288-H：198.png", 288, 198);
            this.viewUpgradeBtn(type);
        }
        else if (this.smallType == 3) {
            this.iconRight = CutButtonImage.getImage("img/xy2uiimg/重洗面板-W：288-H：198.png", 288, 198);
            this.viewRedoBtn(type);
        }
        else if (this.smallType == 4) {
            this.iconRight = CutButtonImage.getImage("img/xy2uiimg/练星面板-W：288-H：198.png", 288, 198);
            this.viewLianStarBtn(type);
        }
    }
    /**
     * 大菜单项属性面板
     *
     */
    public void viewAttributeMenuBtn(boolean type) {
        this.cardBackImg.setVisible(type);
        this.lockBtn.setVisible(type);
        this.unlockBtn.setVisible(type);
        this.cardImg.setVisible(type);
        this.joinBtn.setVisible(type);
        this.consumeOneLab.setVisible(type);
        this.consumeTwoLab.setVisible(type);
        this.supplementBtn.setVisible(type);
        this.nameLvlLab.setVisible(type);
        this.powerLab.setVisible(type);
        this.fiveElementsLab.setVisible(type);
        this.StarArrayLab.setVisible(type);
        this.showStarArray(false, null);
        if (type) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.consumeOneLab.setBounds(98, 305, 103, 16);
                this.consumeTwoLab.setBounds(98, 330, 103, 16);
            }
            else {
                this.consumeOneLab.setBounds(73, 320, 103, 16);
                this.consumeTwoLab.setBounds(73, 345, 103, 16);
            }
            this.nameLvlLab.setText("");
            this.powerLab.setText("");
            this.fiveElementsLab.setText("");
            this.StarArrayLab.setText("");
        }
    }
    /**
     * 大菜单项重铸面板切换
     *
     */
    public void viewRecastMenuBtn(boolean type) {
        this.caozuoBtn.setVisible(type);
        this.starCardImg.setVisible(type);
        this.materialsImgs.setVisible(type);
        this.cultivateBtn.setVisible(type);
        this.redoBtn.setVisible(type);
        this.upgradeBtn.setVisible(type);
        this.lianStarBtn.setVisible(type);
        for (int i = 0; i < this.materialsLab.length; ++i) {
            this.materialsLab[i].setVisible(type);
        }
        this.changeSmallMenuLeftView(this.smallType);
    }
    /**
     * 培养按钮面板切换
     *
     * @param type
     */
    public void viewCultivateBtn(boolean type) {
        this.consumeTwoLab.setVisible(type);
        if (type) {
            this.materialType = 522L;
            this.money = new BigDecimal(10000000);
            if (MyIsif.getStyle().equals("水墨")) {
                this.starCardImg.setBounds(162, 83, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("培养");
                this.materialsImgs.setBounds(249, 83, 55, 53);
                this.recoverMaterialsImg();
                this.consumeTwoLab.setBounds(198, 187, 108, 16);
                this.consumeTwoLab.setText("");
            }
            else {
                this.starCardImg.setBounds(137, 98, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("培养");
                this.materialsImgs.setBounds(224, 98, 55, 53);
                this.recoverMaterialsImg();
                this.consumeTwoLab.setBounds(173, 202, 108, 16);
                this.consumeTwoLab.setText("");
            }
        }
    }
    /**
     * 重洗按钮面板切换
     *
     * @param type
     */
    public void viewRedoBtn(boolean type) {
        this.radioOneImg.setVisible(type);
        this.radioTwoImg.setVisible(type);
        if (type) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.starCardImg.setBounds(156, 103, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("重洗");
                this.money = new BigDecimal(100000);
                this.materialsImgs.setBounds(243, 103, 55, 53);
                this.recoverMaterialsImg();
            }
            else {
                this.starCardImg.setBounds(131, 118, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("重洗");
                this.money = new BigDecimal(100000);
                this.materialsImgs.setBounds(218, 118, 55, 53);
                this.recoverMaterialsImg();
            }
        }
    }
    /**
     * 升级按钮面板切换
     *
     * @param type
     */
    public void viewUpgradeBtn(boolean type) {
        if (type) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.materialType = 500L;
                this.starCardImg.setBounds(155, 77, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("升级");
                this.materialsImgs.setBounds(242, 77, 55, 53);
                this.recoverMaterialsImg();
                this.money = new BigDecimal(0);
            }
            else {
                this.materialType = 500L;
                this.starCardImg.setBounds(130, 92, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("升级");
                this.materialsImgs.setBounds(217, 92, 55, 53);
                this.recoverMaterialsImg();
                this.money = new BigDecimal(0);
            }
        }
    }
    /**
     * 练星按钮面板切换
     *
     * @param type
     */
    public void viewLianStarBtn(boolean type) {
        this.consumeOneLab.setVisible(type);
        if (type) {
            if (MyIsif.getStyle().equals("水墨")) {
                this.materialType = 521L;
                this.starCardImg.setBounds(155, 110, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("练星");
                this.materialsImgs.setBounds(242, 110, 55, 53);
                this.recoverMaterialsImg();
                this.consumeOneLab.setBounds(245, 169, 48, 16);
                this.consumeOneLab.setText("");
            }
            else {
                this.materialType = 521L;
                this.starCardImg.setBounds(130, 125, 55, 53);
                this.recoverStarCardImg();
                this.caozuoBtn.setText("练星");
                this.materialsImgs.setBounds(217, 125, 55, 53);
                this.recoverMaterialsImg();
                this.consumeOneLab.setBounds(220, 184, 48, 16);
                this.consumeOneLab.setText("");
            }
        }
    }
    /** 面板变动 */
    public void viewChange(Goodstable goodstable) {
        if (goodstable == null) {
            if (this.chooseStarCardId == null && this.smallType == 1) {
                this.consumeTwoLab.setText("");
            }
            else if (this.smallType == 4) {
                this.consumeOneLab.setText("");
            }
            return;
        }
        else {
            if (goodstable.getType() == 520L) {
                String[] value = goodstable.getValue().split("\\|");
                String[] level = value[0].split("=")[1].split("/");
                // 当前等级
                int lvlNow = Integer.parseInt(level[0]);
                if (this.smallType == 1) {
                    if (lvlNow >= 14) {
                        this.consumeTwoLab.setText("");
                    }
                    else {
                        int power = Integer.parseInt(value[1].split("=")[1]);
                        this.consumeTwoLab.setText(power + "/" + lvlNow * 200);
                    }
                }
                else if (this.smallType == 2) {
                    this.chooseStarCardImg(goodstable);
                    this.money = new BigDecimal(lvlNow * 20000000);
                }
                else {
                    if (this.chooseMaterialsId == null) {
                        return;
                    }
                    Goodstable chooseMaterial = GoodsListFromServerUntil.getRgid(this.chooseMaterialsId);
                    if (chooseMaterial == null) {
                        return;
                    }
                    if (this.smallType == 4) {
                        int lvlMax2 = Integer.parseInt(level[1]);
                        if (lvlMax2 >= 14) {
                            this.consumeOneLab.setForeground(Color.white);
                            this.consumeOneLab.setText("0/0");
                        }
                        else {
                            int num = lvlMax2 / 2 + 2;
                            if (num < chooseMaterial.getUsetime()) {
                                this.consumeOneLab.setForeground(Color.white);
                                this.consumeOneLab.setText(num + "/" + chooseMaterial.getUsetime());
                            }
                            else {
                                this.consumeOneLab.setForeground(Color.red);
                                this.consumeOneLab.setText(num + "/" + chooseMaterial.getUsetime());
                            }
                        }
                        return;
                    }
                }
            }
            else {
                if (this.chooseStarCardId == null) {
                    return;
                }
                Goodstable chooseStarCard = GoodsListFromServerUntil.getRgid(this.chooseStarCardId);
                if (chooseStarCard == null) {
                    return;
                }
                String[] value2 = chooseStarCard.getValue().split("\\|");
                String[] level2 = value2[0].split("=")[1].split("/");
                if (this.smallType == 4) {
                    int lvlMax3 = Integer.parseInt(level2[1]);
                    if (lvlMax3 >= 14) {
                        this.consumeOneLab.setText("0/0");
                    }
                    else {
                        int num2 = lvlMax3 / 2 + 2;
                        if (num2 <= goodstable.getUsetime()) {
                            this.consumeOneLab.setForeground(Color.white);
                            this.consumeOneLab.setText(num2 + "/" + goodstable.getUsetime());
                        }
                        else {
                            this.consumeOneLab.setForeground(Color.red);
                            this.consumeOneLab.setText(num2 + "/" + goodstable.getUsetime());
                        }
                    }
                    return;
                }
            }
        }
    }
    /** 属性面板信息展示 */
    public void attributeImgShow(Goodstable goodstable) {
        if (goodstable == null) {
            this.chooseStarCardId = null;
            this.nameLvlLab.setText("");
            this.powerLab.setText("");
            this.fiveElementsLab.setText("");
            this.StarArrayLab.setText("");
            this.consumeOneLab.setText("");
            this.consumeTwoLab.setText("");
            this.cardImg.setIcon(null);
            this.showStarArray(false, null);
            this.joinBtn.setText("参战");
            return;
        }
        this.chooseStarCardId = goodstable.getRgid();
        if ((int)goodstable.getStatus() == 1) {
            this.joinBtn.setText("待机");
        }
        else {
            this.joinBtn.setText("参战");
        }
        this.cardImg.setIcon(CutButtonImage.getImage("img/head/" + goodstable.getSkin() + ".png", -1, -1));
        String[] value = goodstable.getValue().split("\\|");
        String[] level = value[0].split("=")[1].split("/");
        int warPower = Integer.parseInt(value[2].split("=")[1]);
        int lvlNow = Integer.parseInt(level[0]);
        int lvlMax = Integer.parseInt(level[1]);
        int power = Integer.parseInt(value[1].split("=")[1]);
        this.consumeOneLab.setForeground(Color.white);
        this.consumeOneLab.setText(power + "/" + lvlNow * 200);
        this.consumeTwoLab.setText(warPower + "");
        this.nameLvlLab.setText("#cD3D001  " + goodstable.getGoodsname() + "#r #r#W【等级】 " + lvlNow + " / " + lvlMax);
        String[] strings = value[3].split("&");
        this.powerLab.setText("#W【神通】 资质：" + strings[1].split("=")[1] + "/100#r#G" + this.getRefiningAttribute(strings[2], "+") + "#r" + this.getRefiningAttribute(strings[3], "+"));
        String[] fiveElements = value[4].split("&");
        StringBuffer buffer = new StringBuffer();
        buffer.append("#W【五行】");
        for (int i = 1; i < fiveElements.length; ++i) {
            buffer.append("#r#Y" + this.getRefiningAttribute(fiveElements[i], " "));
        }
        boolean is = false;
        double num = 0.0;
        for (int j = 0; j < strings.length; ++j) {
            if (strings[j].startsWith("星阵属性")) {
                String[] satrArry = strings[j].split("=");
                for (int k = 1; k < fiveElements.length; ++k) {
                    String[] split = fiveElements[k].split("=");
                    num += fiveElementRestrainCreate(satrArry[2], split[0], split[1]);
                }
                buffer.append("#r#c43E4D2五行加成星阵之力 " + String.format("%.1f", new Object[] { Double.valueOf(num) }) + "%");
                this.StarArrayLab.setText("#W【星阵之力】 " + satrArry[1]);
                this.showStarArray(true, CutButtonImage.getImage("img/skill/" + satrArry[1] + ".png", 47, 47));
                is = true;
            }
        }
        if (!is) {
            buffer.append("#r#Y无星阵，五行暂不生效");
            this.StarArrayLab.setText("#W【星阵之力】 无");
            this.showStarArray(false, null);
        }
        this.fiveElementsLab.setText(buffer.toString());
    }
    /** 星阵属性展示 */
    public void showStarArray(boolean type, ImageIcon iconPath) {
        this.starArrayBackImg.setVisible(type);
        this.starArryImg.setVisible(type);
        this.starArryImg.setIcon(iconPath);
        this.deleteBtn.setVisible(type);
        this.transferBtn.setVisible(type);
    }
    /** 判断相克五行属性 */
    public static Integer fiveElementRestrainNum(String attr) {
        if (attr.startsWith("金")) {
            return Integer.valueOf(1);
        }
        if (attr.startsWith("木")) {
            return Integer.valueOf(2);
        }
        if (attr.startsWith("土")) {
            return Integer.valueOf(3);
        }
        if (attr.startsWith("水")) {
            return Integer.valueOf(4);
        }
        if (attr.startsWith("火")) {
            return Integer.valueOf(5);
        }
        return null;
    }
    /** 判断相生五行属性 */
    public static Integer fiveElementCreateNum(String attr) {
        if (attr.startsWith("金")) {
            return Integer.valueOf(1);
        }
        if (attr.startsWith("水")) {
            return Integer.valueOf(2);
        }
        if (attr.startsWith("木")) {
            return Integer.valueOf(3);
        }
        if (attr.startsWith("火")) {
            return Integer.valueOf(4);
        }
        if (attr.startsWith("土")) {
            return Integer.valueOf(5);
        }
        return null;
    }
    /** 判断相生相克 */
    public static double fiveElementRestrainCreate(String attr, String value, String num) {
        Integer num2 = fiveElementRestrainNum(attr);
        Integer num3 = fiveElementRestrainNum(value);
        int abs = Math.abs((int)num2 - (int)num3);
        if ((abs == 1 || abs == 4) && (((int)num2 == 1 && (int)num3 == 5) || (int)num2 > (int)num3)) {
            return (double)Integer.parseInt(num) * 0.1;
        }
        num2 = fiveElementCreateNum(attr);
        num3 = fiveElementCreateNum(value);
        abs = Math.abs((int)num2 - (int)num3);
        if ((abs == 1 || abs == 4) && (((int)num2 == 1 && (int)num3 == 5) || (int)num2 > (int)num3)) {
            return (double)Integer.parseInt(num) * 0.3;
        }
        return (double)Integer.parseInt(num) * 0.2;
    }
    
    public String getRefiningAttribute(String value, String interval) {
        String[] split = value.split("=");
        return split[0] + interval + split[1];
    }
    
    public void clearAttributeShow() {
        this.cardImg.setIcon(null);
        this.chooseStarCardId = null;
        this.consumeOneLab.setText("");
        this.consumeTwoLab.setText("");
    }
    
    public void caoZuoStarCard() {
        if (this.chooseStarCardId == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
            return;
        }
        Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.chooseStarCardId);
        if (goodstable == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择要培养的星卡");
            return;
        }
        if (this.chooseMaterialsId == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择材料");
            return;
        }
        String[] values = goodstable.getValue().split("\\|");
        String[] level = values[0].split("=")[1].split("/");
        int lvlNow = Integer.parseInt(level[0]);
        int lvlMax = Integer.parseInt(level[1]);
        int power = Integer.parseInt(values[1].split("=")[1]);
        Goodstable chooseMaterialsGoods = GoodsListFromServerUntil.getRgid(this.chooseMaterialsId);
        if (chooseMaterialsGoods == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请放入物品");
            return;
        }
        int num = 1;
        int type = 0;
        if (this.smallType == 1) {
            if (lvlNow >= 14) {
                ZhuFrame.getZhuJpanel().addPrompt2("等级已经抵达上限");
                return;
            }
            if ((long)chooseMaterialsGoods.getType() != 522L) {
                ZhuFrame.getZhuJpanel().addPrompt2("物品不是观星册");
                return;
            }
            if ((int)chooseMaterialsGoods.getUsetime() < num) {
                ZhuFrame.getZhuJpanel().addPrompt2("观星册数量不足");
                return;
            }
            if (power >= lvlNow * 200) {
                ZhuFrame.getZhuJpanel().addPrompt2("神力已经抵达上限，无法培养");
                return;
            }
            if (this.money.compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                return;
            }
            type = 51;
        }
        else if (this.smallType == 2) {
            if (power < lvlNow * 200) {
                ZhuFrame.getZhuJpanel().addPrompt2("神力没有抵达上限，无法升级");
                return;
            }
            if (lvlNow >= lvlMax) {
                ZhuFrame.getZhuJpanel().addPrompt2("等级已经抵达等级上限,无法继续升级");
                return;
            }
            if ((long)chooseMaterialsGoods.getType() != 500L) {
                ZhuFrame.getZhuJpanel().addPrompt2("物品不是矿石");
                return;
            }
            int materialsLvl = Integer.parseInt(chooseMaterialsGoods.getValue().split("=")[1]);
            if (materialsLvl != 11) {
                ZhuFrame.getZhuJpanel().addPrompt2("物品不是11级矿石");
                return;
            }
            if ((int)chooseMaterialsGoods.getUsetime() < num) {
                ZhuFrame.getZhuJpanel().addPrompt2("矿石数量不足");
                return;
            }
            if (this.money.compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                return;
            }
            type = 52;
        }
        else if (this.smallType == 3) {
            if (this.radioType == 1) {
                if ((long)chooseMaterialsGoods.getType() != 524L) {
                    ZhuFrame.getZhuJpanel().addPrompt2("物品不是浑天石");
                    return;
                }
                if ((int)chooseMaterialsGoods.getUsetime() < num) {
                    ZhuFrame.getZhuJpanel().addPrompt2("浑天石数量不足");
                    return;
                }
                if (this.money.compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    return;
                }
                type = 53;
            }
            else if (this.radioType == 2) {
                if ((long)chooseMaterialsGoods.getType() != 523L) {
                    ZhuFrame.getZhuJpanel().addPrompt2("物品不是易象符");
                    return;
                }
                if ((int)chooseMaterialsGoods.getUsetime() < num) {
                    ZhuFrame.getZhuJpanel().addPrompt2("易象符数量不足");
                    return;
                }
                if (this.money.compareTo(RoleData.getRoleData().getLoginResult().getGold()) > 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("大话币不足");
                    return;
                }
                type = 54;
            }
        }
        else if (this.smallType == 4) {
            if (lvlMax >= 14) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经升到最大等级");
                return;
            }
            if ((long)chooseMaterialsGoods.getType() != 521L) {
                ZhuFrame.getZhuJpanel().addPrompt2("物品不是炼星石");
                return;
            }
            num = lvlMax / 2 + 2;
            if ((int)chooseMaterialsGoods.getUsetime() < num) {
                ZhuFrame.getZhuJpanel().addPrompt2("炼星石数量不足");
                return;
            }
            type = 55;
        }
        SuitOperBean suitOperBean = new SuitOperBean();
        suitOperBean.setType(type);
        List<BigDecimal> goods = new ArrayList<>();
        goods.add(this.chooseStarCardId);
        goods.add(this.chooseMaterialsId);
        suitOperBean.setGoods(goods);
        this.deductGoods(chooseMaterialsGoods, num);
        String sendmes = Agreement.suitOperateAgreement(GsonUtil.getGsonUtil().getgson().toJson(suitOperBean));
        SendMessageUntil.toServer(sendmes);
    }
    
    public void deductGoods(Goodstable goodstable, int num) {
        goodstable.goodxh(num);
        if ((int)goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
            if (this.bigType == 1) {
                GoodsListFromServerUntil.getStarCardList().remove(goodstable);
            }
            else if (this.bigType == 2) {
                this.recoverMaterialsImg();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B283.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 637, 425, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("星芒") + "", 297, 39);
            GoodsListFromServerUntil.drawStarCardImg(g, 402, 48);
            if (this.bigType == 1) {
                if (this.iconWire == null) {
                    this.iconWire = CutButtonImage.getImage("inkImg/background1/B286.png", 190, 1);
                }
                g.drawImage(this.iconWire.getImage(), 213, 123, 190, 1, this);
                g.drawImage(this.iconWire.getImage(), 213, 177, 190, 1, this);
                g.drawImage(this.iconWire.getImage(), 213, 303, 190, 1, this);
                if (this.iconLeft == null) {
                    this.iconLeft = CutButtonImage.getImage("inkImg/background1/B284.png", 159, 114);
                }
                g.drawImage(this.iconLeft.getImage(), 53, 273, 159, 114, this);
                if (this.iconName == null) {
                    this.iconName = CutButtonImage.getImage("inkImg/background1/B287.png", 150, 30);
                }
                g.drawImage(this.iconName.getImage(), 213, 51, 150, 30, this);
            }
            else if (this.bigType == 2) {
                g.drawImage(this.iconLeft.getImage(), 65, 280, 309, 105, this);
                if (this.iconRight == null) {
                    this.iconRight = CutButtonImage.getImage("img/xy2uiimg/培养面板-W：288-H：198.png", 288, 198);
                }
                g.drawImage(this.iconRight.getImage(), 89, 57, 290, 200, this);
                g.setColor(Color.white);
                g.setFont(UIUtils.TEXT_FONT1);
                g.drawString("拥有材料:", 69, 273);
                GoodsListFromServerUntil.drawStarCardCultivateMaterialImg(g, this.materialType, 67, 282);
                if (this.smallType == 1) {
                    if (this.money != null) {
                        Util.drawPrice(g, this.money, 199, 179);
                    }
                }
                else if (this.smallType == 2) {
                    if (this.money != null) {
                        Util.drawPrice(g, this.money, 205, 176);
                    }
                    Util.drawMoney(g, 205, 202);
                }
                else if (this.smallType == 3) {
                    if (this.money != null) {
                        Util.drawPrice(g, this.money, 201, 180);
                    }
                    Util.drawMoney(g, 201, 205);
                }
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/地煞星总界面-W：611-H：447.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 611, 447, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("星芒") + "", 272, 54);
            GoodsListFromServerUntil.drawStarCardImg(g, 379, 60);
            if (this.bigType == 1) {
                if (this.iconWire == null) {
                    this.iconWire = CutButtonImage.getImage("img/xy2uiimg/属性-条形-W：183-H：3.png", 183, 3);
                }
                g.drawImage(this.iconWire.getImage(), 188, 138, 183, 3, this);
                g.drawImage(this.iconWire.getImage(), 188, 192, 183, 3, this);
                g.drawImage(this.iconWire.getImage(), 188, 318, 183, 3, this);
                if (this.iconLeft == null) {
                    this.iconLeft = CutButtonImage.getImage("img/xy2uiimg/战力显示框-W：162-H：115.png", 162, 115);
                }
                g.drawImage(this.iconLeft.getImage(), 28, 285, 162, 115, this);
                if (this.iconName == null) {
                    this.iconName = CutButtonImage.getImage("img/xy2uiimg/煞星名字显示-W：101-H：21.png", 101, 21);
                }
                g.drawImage(this.iconName.getImage(), 188, 66, 101, 21, this);
            }
            else if (this.bigType == 2) {
                g.drawImage(this.iconLeft.getImage(), 40, 295, 309, 105, this);
                if (this.iconRight == null) {
                    this.iconRight = CutButtonImage.getImage("img/xy2uiimg/培养面板-W：288-H：198.png", 288, 198);
                }
                g.drawImage(this.iconRight.getImage(), 64, 72, 288, 198, this);
                g.setColor(Color.white);
                g.setFont(UIUtils.TEXT_FONT1);
                g.drawString("拥有材料:", 44, 288);
                GoodsListFromServerUntil.drawStarCardCultivateMaterialImg(g, this.materialType, 42, 297);
                if (this.smallType == 1) {
                    if (this.money != null) {
                        Util.drawPrice(g, this.money, 174, 194);
                    }
                }
                else if (this.smallType == 2) {
                    if (this.money != null) {
                        Util.drawPrice(g, this.money, 180, 217);
                    }
                }
                else if (this.smallType == 3 && this.money != null) {
                    Util.drawPrice(g, this.money, 176, 195);
                }
            }
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getIconLeft() {
        return this.iconLeft;
    }
    
    public void setIconLeft(ImageIcon iconLeft) {
        this.iconLeft = iconLeft;
    }
    
    public ImageIcon getIconRight() {
        return this.iconRight;
    }
    
    public void setIconRight(ImageIcon iconRight) {
        this.iconRight = iconRight;
    }
    
    public ImageIcon getIconName() {
        return this.iconName;
    }
    
    public void setIconName(ImageIcon iconName) {
        this.iconName = iconName;
    }
    
    public ImageIcon getIconWire() {
        return this.iconWire;
    }
    
    public void setIconWire(ImageIcon iconWire) {
        this.iconWire = iconWire;
    }
    
    public JLabel getCardBackImg() {
        if (this.cardBackImg == null) {
            this.cardBackImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.cardBackImg.setBounds(52, 51, 160, 221);
                this.cardBackImg.setIcon(CutButtonImage.getImage("inkImg/background1/B230.png", 160, 221));
            }
            else {
                this.cardBackImg.setBounds(28, 65, 160, 221);
                this.cardBackImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/地煞星显示框-W：160-H：221.png", 160, 221));
            }
            this.add(this.cardBackImg);
        }
        return this.cardBackImg;
    }
    
    public void setCardBackImg(JLabel cardBackImg) {
        this.cardBackImg = cardBackImg;
    }
    
    public BtnStarCard getLockBtn() {
        if (this.lockBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.lockBtn = new BtnStarCard("inkImg/button1/K156.png", 1, "", 11, this)).setBounds(110, 277, 18, 18);
            }
            else {
                (this.lockBtn = new BtnStarCard("img/xy2uiimg/25_png.button.btn_lock.png", 1, "", 11, this)).setBounds(85, 292, 19, 20);
            }
            this.add(this.lockBtn);
        }
        return this.lockBtn;
    }
    
    public void setLockBtn(BtnStarCard lockBtn) {
        this.lockBtn = lockBtn;
    }
    
    public BtnStarCard getUnlockBtn() {
        if (this.unlockBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.unlockBtn = new BtnStarCard("inkImg/button1/K157.png", 1, "", 12, this)).setBounds(135, 277, 18, 18);
            }
            else {
                (this.unlockBtn = new BtnStarCard("img/xy2uiimg/27_png.button.btn_unlock.png", 1, "", 12, this)).setBounds(110, 292, 19, 20);
            }
            this.add(this.unlockBtn);
        }
        return this.unlockBtn;
    }
    
    public void setUnlockBtn(BtnStarCard unlockBtn) {
        this.unlockBtn = unlockBtn;
    }
    
    public BtnStarCard getAttributeMenuBtn() {
        if (this.attributeMenuBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.attributeMenuBtn = new BtnStarCard("inkImg/button/B198.png", 1, "", 1, this)).setBounds(57, 18, 63, 26);
            }
            else {
                (this.attributeMenuBtn = new BtnStarCard("img/xy2uiimg/属性按钮w63,h78.png", 1, "", 1, this)).setBounds(30, 32, 63, 26);
            }
            this.add(this.attributeMenuBtn);
        }
        return this.attributeMenuBtn;
    }
    
    public void setAttributeMenuBtn(BtnStarCard attributeMenuBtn) {
        this.attributeMenuBtn = attributeMenuBtn;
    }
    
    public BtnStarCard getRecastMenuBtn() {
        if (this.recastMenuBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.recastMenuBtn = new BtnStarCard("inkImg/button/B199.png", 1, "", 2, this)).setBounds(122, 18, 63, 26);
            }
            else {
                (this.recastMenuBtn = new BtnStarCard("img/xy2uiimg/重铸未w63,h78px.png", 1, "", 2, this)).setBounds(95, 32, 63, 26);
            }
            this.add(this.recastMenuBtn);
        }
        return this.recastMenuBtn;
    }
    
    public void setRecastMenuBtn(BtnStarCard recastMenuBtn) {
        this.recastMenuBtn = recastMenuBtn;
    }
    
    public BtnStarCard getCultivateBtn() {
        if (this.cultivateBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.cultivateBtn = new BtnStarCard("inkImg/button/B204.png", 1, "", 3, this)).setBounds(59, 57, 30, 48);
            }
            else {
                (this.cultivateBtn = new BtnStarCard("img/xy2uiimg/培养按钮2-W：30-H：138.png", 1, "", 3, this)).setBounds(34, 72, 30, 46);
            }
            this.add(this.cultivateBtn);
        }
        return this.cultivateBtn;
    }
    
    public void setCultivateBtn(BtnStarCard cultivateBtn) {
        this.cultivateBtn = cultivateBtn;
    }
    
    public BtnStarCard getLianStarBtn() {
        if (this.lianStarBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.lianStarBtn = new BtnStarCard("inkImg/button/B201.png", 1, "", 6, this)).setBounds(59, 207, 30, 48);
            }
            else {
                (this.lianStarBtn = new BtnStarCard("img/xy2uiimg/练星按钮-W：30-H：138.png", 1, "", 6, this)).setBounds(34, 216, 30, 46);
            }
            this.add(this.lianStarBtn);
        }
        return this.lianStarBtn;
    }
    
    public void setLianStarBtn(BtnStarCard lianStarBtn) {
        this.lianStarBtn = lianStarBtn;
    }
    
    public BtnStarCard getUpgradeBtn() {
        if (this.upgradeBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.upgradeBtn = new BtnStarCard("inkImg/button/B205.png", 1, "", 4, this)).setBounds(59, 107, 30, 48);
            }
            else {
                (this.upgradeBtn = new BtnStarCard("img/xy2uiimg/升级按钮-W：30-H：138.png", 1, "", 4, this)).setBounds(34, 120, 30, 46);
            }
            this.add(this.upgradeBtn);
        }
        return this.upgradeBtn;
    }
    
    public void setUpgradeBtn(BtnStarCard upgradeBtn) {
        this.upgradeBtn = upgradeBtn;
    }
    
    public BtnStarCard getRedoBtn() {
        if (this.redoBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.redoBtn = new BtnStarCard("inkImg/button/B207.png", 1, "", 5, this)).setBounds(59, 157, 30, 48);
            }
            else {
                (this.redoBtn = new BtnStarCard("img/xy2uiimg/重洗按钮-W：30-H：138.png", 1, "", 5, this)).setBounds(34, 168, 30, 46);
            }
            this.add(this.redoBtn);
        }
        return this.redoBtn;
    }
    
    public void setRedoBtn(BtnStarCard redoBtn) {
        this.redoBtn = redoBtn;
    }
    
    public int getStarCardType() {
        return this.bigType;
    }
    
    public void setStarCardType(int starCardType) {
        this.bigType = starCardType;
    }
    
    public JLabel getCardImg() {
        if (this.cardImg == null) {
            this.cardImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.cardImg.setBounds(53, 50, 160, 220);
            }
            else {
                this.cardImg.setBounds(28, 65, 160, 220);
            }
            this.cardImg.setOpaque(false);
            this.add(this.cardImg);
        }
        return this.cardImg;
    }
    
    public void setCardImg(JLabel cardImg) {
        this.cardImg = cardImg;
    }
    
    public JLabel getStarCardImg() {
        if (this.starCardImg == null) {
            this.starCardImg = new JLabel("地煞星", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.starCardImg.setBounds(162, 83, 55, 53);
            }
            else {
                this.starCardImg.setBounds(137, 98, 55, 53);
            }
            this.starCardImg.setOpaque(false);
            this.starCardImg.setForeground(Color.gray);
            this.starCardImg.setFont(UIUtils.TEXT_FONT);
            this.starCardImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 3) {
                        if (JpanelStarCardMain.this.chooseStarCardId == null) {
                            return;
                        }
                        JpanelStarCardMain.this.recoverStarCardImg();
                        JpanelStarCardMain.this.viewChange(null);
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (JpanelStarCardMain.this.chooseStarCardId == null) {
                        return;
                    }
                    Goodstable goodstable = GoodsListFromServerUntil.getRgid(JpanelStarCardMain.this.chooseStarCardId);
                    if (goodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TestpackJframe.getTestpackJframe().getJpac().ClearText();
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
            });
            this.add(this.starCardImg);
        }
        return this.starCardImg;
    }
    
    public void setStarCardImg(JLabel starCardImg) {
        this.starCardImg = starCardImg;
    }
    
    public JLabel getMaterialsImgs() {
        if (this.materialsImgs == null) {
            this.materialsImgs = new JLabel("观星册", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.materialsImgs.setBounds(249, 83, 55, 53);
            }
            else {
                this.materialsImgs.setBounds(224, 98, 55, 53);
            }
            this.materialsImgs.setOpaque(false);
            this.materialsImgs.setForeground(Color.gray);
            this.materialsImgs.setFont(UIUtils.TEXT_FONT);
            this.materialsImgs.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 3) {
                        if (JpanelStarCardMain.this.chooseMaterialsId == null) {
                            return;
                        }
                        JpanelStarCardMain.this.recoverMaterialsImg();
                        JpanelStarCardMain.this.viewChange(null);
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (JpanelStarCardMain.this.chooseMaterialsId == null) {
                        return;
                    }
                    Goodstable goodstable = GoodsListFromServerUntil.getRgid(JpanelStarCardMain.this.chooseMaterialsId);
                    if (goodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    TestpackJframe.getTestpackJframe().getJpac().ClearText();
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
            });
            this.add(this.materialsImgs);
        }
        return this.materialsImgs;
    }
    
    public void setMaterialsImgs(JLabel materialsImgs) {
        this.materialsImgs = materialsImgs;
    }
    
    public JLabel getRadioOneImg() {
        if (this.radioOneImg == null) {
            this.radioOneImg = new JLabel("", 0);
            if (MyIsif.getStyle().equals("水墨")) {
                this.radioOneImg.setBounds(133, 64, 19, 19);
                this.radioOneImg.setVisible(false);
                this.radioOneImg.setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
            }
            else {
                this.radioOneImg.setBounds(107, 78, 19, 19);
                this.radioOneImg.setVisible(false);
                this.radioOneImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_选中_w19,h19.png", 19, 19));
            }
            this.radioOneImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JpanelStarCardMain.this.changeRadioView(1);
                }
            });
            this.add(this.radioOneImg);
        }
        return this.radioOneImg;
    }
    
    public void setRadioOneImg(JLabel radioOneImg) {
        this.radioOneImg = radioOneImg;
    }
    
    public JLabel getRadioTwoImg() {
        if (this.radioTwoImg == null) {
            this.radioTwoImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.radioTwoImg.setBounds(246, 64, 19, 19);
                this.radioTwoImg.setVisible(false);
                this.radioTwoImg.setIcon(null);
            }
            else {
                this.radioTwoImg.setBounds(215, 78, 19, 19);
                this.radioTwoImg.setVisible(false);
                this.radioTwoImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/单选按钮_未选中_w19,h19.png", 19, 19));
            }
            this.radioTwoImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JpanelStarCardMain.this.changeRadioView(2);
                }
            });
            this.add(this.radioTwoImg);
        }
        return this.radioTwoImg;
    }
    
    public void setRadioTwoImg(JLabel radioTwoImg) {
        this.radioTwoImg = radioTwoImg;
    }
    
    public JLabel getConsumeOneLab() {
        if (this.consumeOneLab == null) {
            (this.consumeOneLab = new JLabel()).setBounds(173, 181, 108, 16);
            this.consumeOneLab.setForeground(Color.white);
            this.consumeOneLab.setFont(UIUtils.TEXT_FONT);
            this.consumeOneLab.setOpaque(false);
            this.consumeOneLab.setVisible(false);
            this.add(this.consumeOneLab);
        }
        return this.consumeOneLab;
    }
    
    public void setConsumeOneLab(JLabel consumeOneLab) {
        this.consumeOneLab = consumeOneLab;
    }
    
    public JLabel getConsumeTwoLab() {
        if (this.consumeTwoLab == null) {
            (this.consumeTwoLab = new JLabel()).setBounds(173, 202, 108, 16);
            this.consumeTwoLab.setForeground(Color.white);
            this.consumeTwoLab.setFont(UIUtils.TEXT_FONT);
            this.consumeTwoLab.setOpaque(false);
            this.consumeTwoLab.setVisible(false);
            this.add(this.consumeTwoLab);
        }
        return this.consumeTwoLab;
    }
    
    public void setConsumeTwoLab(JLabel consumeTwoLab) {
        this.consumeTwoLab = consumeTwoLab;
    }
    
    public int getBigType() {
        return this.bigType;
    }
    
    public void setBigType(int bigType) {
        this.bigType = bigType;
    }
    
    public int getSmallType() {
        return this.smallType;
    }
    
    public void setSmallType(int smallType) {
        this.smallType = smallType;
    }
    
    public JLabel[] getMaterialsLab() {
        if (this.materialsLab == null) {
            this.materialsLab = new JLabel[12];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.materialsLab.length; ++i) {
                    (this.materialsLab[i] = new JLabel()).setBounds(67 + i % 6 * 51, 282 + i / 6 * 51, 50, 50);
                    this.materialsLab[i].setOpaque(false);
                    this.materialsLab[i].addMouseListener(new MouseListenerMaterials(i, this));
                    this.add(this.materialsLab[i]);
                }
            }
            else {
                for (int i = 0; i < this.materialsLab.length; ++i) {
                    (this.materialsLab[i] = new JLabel()).setBounds(42 + i % 6 * 51, 297 + i / 6 * 51, 50, 50);
                    this.materialsLab[i].setOpaque(false);
                    this.materialsLab[i].addMouseListener(new MouseListenerMaterials(i, this));
                    this.add(this.materialsLab[i]);
                }
            }
        }
        return this.materialsLab;
    }
    
    public void setMaterialsLab(JLabel[] materialsLab) {
        this.materialsLab = materialsLab;
    }
    
    public int getRadioType() {
        return this.radioType;
    }
    
    public void setRadioType(int radioType) {
        this.radioType = radioType;
    }
    
    public BigDecimal getChooseStarCardId() {
        return this.chooseStarCardId;
    }
    
    public void setChooseStarCardId(BigDecimal chooseStarCardId) {
        this.chooseStarCardId = chooseStarCardId;
    }
    
    public BigDecimal getChooseMaterialsId() {
        return this.chooseMaterialsId;
    }
    
    public void setChooseMaterialsId(BigDecimal chooseMaterialsId) {
        this.chooseMaterialsId = chooseMaterialsId;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public JLabel[] getStarCardLab() {
        if (this.starCardLab == null) {
            this.starCardLab = new JLabel[24];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.starCardLab.length; ++i) {
                    (this.starCardLab[i] = new JLabel()).setBounds(402 + i % 4 * 51, 48 + i / 4 * 51, 50, 50);
                    this.starCardLab[i].setOpaque(false);
                    this.starCardLab[i].addMouseListener(new MouseListenerStarCardMain(i, this));
                    this.add(this.starCardLab[i]);
                }
            }
            else {
                for (int i = 0; i < this.starCardLab.length; ++i) {
                    (this.starCardLab[i] = new JLabel()).setBounds(379 + i % 4 * 51, 60 + i / 4 * 51, 50, 50);
                    this.starCardLab[i].setOpaque(false);
                    this.starCardLab[i].addMouseListener(new MouseListenerStarCardMain(i, this));
                    this.add(this.starCardLab[i]);
                }
            }
        }
        return this.starCardLab;
    }
    
    public void setStarCardLab(JLabel[] starCardLab) {
        this.starCardLab = starCardLab;
    }
    
    public BtnStarCard getCaozuoBtn() {
        if (this.caozuoBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.caozuoBtn = new BtnStarCard("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "", 20, this)).setBounds(205, 225, 59, 24);
            }
            else {
                (this.caozuoBtn = new BtnStarCard("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_HY88, "", 20, this)).setBounds(180, 240, 60, 26);
            }
            this.caozuoBtn.setVisible(false);
            this.add(this.caozuoBtn);
        }
        return this.caozuoBtn;
    }
    
    public void setCaozuoBtn(BtnStarCard caozuoBtn) {
        this.caozuoBtn = caozuoBtn;
    }
    
    public BtnStarCard getJoinBtn() {
        if (this.joinBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.joinBtn = new BtnStarCard("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "参战", 21, this)).setBounds(107, 355, 59, 24);
            }
            else {
                (this.joinBtn = new BtnStarCard("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG2, UIUtils.TEXT_FONT_17, "参战", 21, this)).setBounds(82, 370, 60, 26);
            }
            this.add(this.joinBtn);
        }
        return this.joinBtn;
    }
    
    public void setJoinBtn(BtnStarCard joinBtn) {
        this.joinBtn = joinBtn;
    }
    
    public BtnStarCard getFetchBtn() {
        if (this.fetchBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.fetchBtn = new BtnStarCard("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "取出", 22, this)).setBounds(425, 365, 59, 24);
            }
            else {
                (this.fetchBtn = new BtnStarCard("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT_17, "取出", 22, this)).setBounds(395, 372, 60, 26);
            }
            this.add(this.fetchBtn);
        }
        return this.fetchBtn;
    }
    
    public void setFetchBtn(BtnStarCard fetchBtn) {
        this.fetchBtn = fetchBtn;
    }
    
    public BtnStarCard getSoulBackBtn() {
        if (this.soulBackBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.soulBackBtn = new BtnStarCard("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "魂归", 23, this)).setBounds(525, 365, 59, 24);
            }
            else {
                (this.soulBackBtn = new BtnStarCard("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT_17, "魂归", 23, this)).setBounds(495, 372, 60, 26);
            }
            this.add(this.soulBackBtn);
        }
        return this.soulBackBtn;
    }
    
    public void setSoulBackBtn(BtnStarCard soulBackBtn) {
        this.soulBackBtn = soulBackBtn;
    }
    
    public BtnStarCard getExchangeBtn() {
        if (this.exchangeBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.exchangeBtn = new BtnStarCard("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "兑换", 24, this)).setBounds(363, 26, 34, 17);
            }
            else {
                (this.exchangeBtn = new BtnStarCard("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "兑换", 24, this)).setBounds(340, 40, 34, 17);
            }
            this.add(this.exchangeBtn);
        }
        return this.exchangeBtn;
    }
    
    public void setExchangeBtn(BtnStarCard exchangeBtn) {
        this.exchangeBtn = exchangeBtn;
    }
    
    public BtnStarCard getSupplementBtn() {
        if (this.supplementBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.supplementBtn = new BtnStarCard("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "补充", 25, this)).setBounds(169, 331, 34, 17);
            }
            else {
                (this.supplementBtn = new BtnStarCard("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "补充", 25, this)).setBounds(143, 345, 34, 17);
            }
            this.add(this.supplementBtn);
        }
        return this.supplementBtn;
    }
    
    public void setSupplementBtn(BtnStarCard supplementBtn) {
        this.supplementBtn = supplementBtn;
    }
    
    public RichLabel getNameLvlLab() {
        if (this.nameLvlLab == null) {
            this.nameLvlLab = new RichLabel("#cD3D001  星卡名称#r #r#W【等级】 8 / 14", UIUtils.TEXT_NAME_FONT);
            if (MyIsif.getStyle().equals("水墨")) {
                this.nameLvlLab.setBounds(213, 49, 180, 66);
            }
            else {
                this.nameLvlLab.setBounds(188, 64, 180, 66);
            }
            this.add(this.nameLvlLab);
        }
        return this.nameLvlLab;
    }
    
    public void setNameLvlLab(RichLabel nameLvlLab) {
        this.nameLvlLab = nameLvlLab;
    }
    
    public RichLabel getPowerLab() {
        if (this.powerLab == null) {
            this.powerLab = new RichLabel("#W【神通】 资质：100/100#r#G雷系狂暴程度 +11.2%#r雷系狂暴程度 +12.0%", UIUtils.TEXT_FONT2);
            if (MyIsif.getStyle().equals("水墨")) {
                this.powerLab.setBounds(215, 127, 180, 80);
            }
            else {
                this.powerLab.setBounds(190, 142, 180, 80);
            }
            this.add(this.powerLab);
        }
        return this.powerLab;
    }
    
    public void setPowerLab(RichLabel powerLab) {
        this.powerLab = powerLab;
    }
    
    public RichLabel getFiveElementsLab() {
        if (this.fiveElementsLab == null) {
            this.fiveElementsLab = new RichLabel("#W【五行】#r#Y金 51/100#r#Y木 66/100#r#Y水 41/100#r#Y火 23/100#r土 99/100#r#Y无星阵，五行暂不生效", UIUtils.TEXT_FONT2);
            if (MyIsif.getStyle().equals("水墨")) {
                this.fiveElementsLab.setBounds(215, 178, 180, 116);
            }
            else {
                this.fiveElementsLab.setBounds(190, 193, 180, 116);
            }
            this.add(this.fiveElementsLab);
        }
        return this.fiveElementsLab;
    }
    
    public void setFiveElementsLab(RichLabel fiveElementsLab) {
        this.fiveElementsLab = fiveElementsLab;
    }
    
    public RichLabel getStarArrayLab() {
        if (this.StarArrayLab == null) {
            this.StarArrayLab = new RichLabel("#W【星阵之力】 朱雀 ", UIUtils.TEXT_FONT2);
            if (MyIsif.getStyle().equals("水墨")) {
                this.StarArrayLab.setBounds(215, 305, 180, 20);
            }
            else {
                this.StarArrayLab.setBounds(190, 320, 180, 20);
            }
            this.add(this.StarArrayLab);
        }
        return this.StarArrayLab;
    }
    
    public void setStarArrayLab(RichLabel starArrayLab) {
        this.StarArrayLab = starArrayLab;
    }
    
    public long getMaterialType() {
        return this.materialType;
    }
    
    public void setMaterialType(long materialType) {
        this.materialType = materialType;
    }
    
    public JLabel getStarArrayBackImg() {
        if (this.starArrayBackImg == null) {
            this.starArrayBackImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.starArrayBackImg.setIcon(CutButtonImage.getImage("inkImg/background/S116.png", 51, 51));
                this.starArrayBackImg.setBounds(250, 328, 51, 51);
            }
            else {
                this.starArrayBackImg.setIcon(CutButtonImage.getImage("img/xy2uiimg/方框w51,h51px.png", 51, 51));
                this.starArrayBackImg.setBounds(225, 343, 51, 51);
            }
            this.add(this.starArrayBackImg);
        }
        return this.starArrayBackImg;
    }
    
    public void setStarArrayBackImg(JLabel starArrayBackImg) {
        this.starArrayBackImg = starArrayBackImg;
    }
    
    public JLabel getStarArryImg() {
        if (this.starArryImg == null) {
            this.starArryImg = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.starArryImg.setBounds(252, 330, 47, 47);
            }
            else {
                this.starArryImg.setBounds(227, 345, 47, 47);
            }
            this.starArryImg.setOpaque(false);
            this.add(this.starArryImg);
        }
        return this.starArryImg;
    }
    
    public void setStarArryImg(JLabel starArryImg) {
        this.starArryImg = starArryImg;
    }
    
    public BtnStarCard getDeleteBtn() {
        if (this.deleteBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.deleteBtn = new BtnStarCard("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "删除", 26, this)).setBounds(305, 330, 34, 17);
            }
            else {
                (this.deleteBtn = new BtnStarCard("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "删除", 26, this)).setBounds(280, 345, 34, 17);
            }
            this.add(this.deleteBtn);
        }
        return this.deleteBtn;
    }
    
    public void setDeleteBtn(BtnStarCard deleteBtn) {
        this.deleteBtn = deleteBtn;
    }
    
    public BtnStarCard getTransferBtn() {
        if (this.transferBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.transferBtn = new BtnStarCard("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "转移", 27, this)).setBounds(305, 350, 34, 17);
            }
            else {
                (this.transferBtn = new BtnStarCard("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "转移", 27, this)).setBounds(280, 365, 34, 17);
            }
            this.add(this.transferBtn);
        }
        return this.transferBtn;
    }
    
    public void setTransferBtn(BtnStarCard transferBtn) {
        this.transferBtn = transferBtn;
    }
}
