package com.tool.btn;

import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.role.RoleTX;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.ImageDressJpanel;

public class ImageDressBtn extends MoBanBtn
{
    private int caozuo;
    private ImageDressJpanel imageDressJpanel;
    
    public ImageDressBtn(String iconpath, int type, int caozuo, String text, ImageDressJpanel imageDressJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.imageDressJpanel = imageDressJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String cbkg = configure.getCbgnkg();
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                if (this.caozuo == 1) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B120.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B121.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B123.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("inkImg/button/B125.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk.png"));
                }
                else if (this.caozuo == 2) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B119.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B122.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B123.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("inkImg/button/B125.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk.png"));
                }
                else if (this.caozuo == 3) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B119.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B121.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B124.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("inkImg/button/B125.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk.png"));
                }
                else if (this.caozuo == 4) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B119.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B121.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B123.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("inkImg/button/B126.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk.png"));
                }
                else if (this.caozuo == 5) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("inkImg/button/B119.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("inkImg/button/B121.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("inkImg/button/B123.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("inkImg/button/B125.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("inkImg/button/txk1.png"));
                }
                RoleTX.getRoleTX().Toggle(this.caozuo - 1, 0);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        else {
            try {
                if (this.caozuo == 1) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_0.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_3.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_5.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_7.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_9.png"));
                }
                else if (this.caozuo == 2) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_1.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_2.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_5.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_7.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_9.png"));
                }
                else if (this.caozuo == 3) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_1.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_3.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_4.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_7.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_9.png"));
                }
                else if (this.caozuo == 4) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_1.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_3.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_5.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_6.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_9.png"));
                }
                else if (this.caozuo == 5) {
                    this.imageDressJpanel.getBtnTx().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_1.png"));
                    this.imageDressJpanel.getBtnZsp().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_3.png"));
                    this.imageDressJpanel.getBtnZj().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_5.png"));
                    if (cbkg.equals("开")) {
                        this.imageDressJpanel.getBtnWing().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_7.png"));
                    }
                    this.imageDressJpanel.getBtnTxk().setIcons(CutButtonImage.cuts("img/xy2uiimg/imagedress_8.png"));
                }
                RoleTX.getRoleTX().Toggle(this.caozuo - 1, 0);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
