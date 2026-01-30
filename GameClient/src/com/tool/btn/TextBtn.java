package com.tool.btn;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.awt.Desktop;
import org.come.Frame.ZhuFrame;
import io.netty.util.internal.StringUtil;
import org.come.socket.DownLoadTxt;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;

public class TextBtn extends MoBanBtn
{
    private int caozuo;
    private String petList;
    
    public TextBtn(String iconpath, int type, String text, String caozuo) {
        super(iconpath, type);
        this.petList = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(new Color(255, 255, 255));
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TextBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        if (caozuo == 5) {
            this.setForeground(Color.yellow);
        }
        else {
            this.setForeground(Color.white);
        }
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.petList != null) {
            String[] list = this.petList.split(",");
            "petlist".equals(list[0]);
        }
        else {
            switch (this.caozuo) {
                case 1: {
                    if (FightingMixDeal.State == 0) {
                        try {
                            String url = new DownLoadTxt().GetServerTxt("yueka.txt");
                            if (StringUtil.isNullOrEmpty(url)) {
                                ZhuFrame.getZhuJpanel().addPrompt2("暂未开放");
                                return;
                            }
                            Desktop desktop = Desktop.getDesktop();
                            URI uri = new URI(url);
                            desktop.browse(uri);
                        }
                        catch (IOException var5) {
                            var5.printStackTrace();
                        }
                        catch (Exception var6) {
                            var6.printStackTrace();
                        }
                    }
                }
                case 2:
                case 3:
                case 4:
                case 888: {
                    this.complainOpenWeb();
                    break;
                }
            }
        }
    }
    
    public void complainOpenWeb() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("resource\\other\\chongzhi.txt"));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                String stringParams = sb.toString();
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(stringParams));
                }
                catch (IOException var7) {
                    var7.printStackTrace();
                }
                catch (URISyntaxException var8) {
                    var8.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
