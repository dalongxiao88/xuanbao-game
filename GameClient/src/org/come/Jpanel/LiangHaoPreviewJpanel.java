package org.come.Jpanel;

import org.come.bean.LoginResult;
import java.awt.Font;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.GetLiangHaoJframe;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import org.come.bean.ImgZoom;
import javax.swing.JPanel;

public class LiangHaoPreviewJpanel extends JPanel
{
    private final ImgZoom imgZoom;
    
    public LiangHaoPreviewJpanel() {
        this.imgZoom = CutButtonImage.cuts("inkImg/tupiankuang/lianghao.png", 14, 7, true);
        this.setPreferredSize(new Dimension(175, 50));
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
    }
    
    @Override
    public void paint(Graphics g) {
        this.imgZoom.draw(g);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        Graphics g2 = g.create(0, 5, 175, 50);
        g2.dispose();
        String num = GetLiangHaoJframe.getGetLiangHaoJframe().getGetLiangHaoJpanel().getGetliangHaoTabJpanel().getGetLiangHaoTwoJpanel().getPreViewLiangHao();
        Integer type = Integer.valueOf(5);
        if (StringUtils.isNotEmpty(num)) {
            String[] nums = num.split("");
            for (int j = 0; j < nums.length; ++j) {
                int xnum = 23 * loginResult.getRolename().length() + 11 * j;
                int ynum = 17;
                g.drawImage(CutButtonImage.getImage("inkImg/number/" + String.valueOf(type) + nums[j] + ".png", 11, 15).getImage(), xnum, ynum, 11, 15, this);
                g.setFont(new Font("宋体", 0, 14));
                g.setColor(Color.WHITE);
                g.drawString("[" + loginResult.getRolename(), 18, 30);
                g.drawString("]", 18 * loginResult.getRolename().length() + 14 * num.length(), 30);
            }
        }
        super.paint(g);
    }
}
