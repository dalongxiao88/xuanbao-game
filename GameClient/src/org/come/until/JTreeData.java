package org.come.until;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.entity.Friendtable;
import java.math.BigDecimal;
import org.come.model.Configure;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComponent;

public class JTreeData
{
    private String strNode;
    private String path;
    private JComponent jcp;
    
    public JTreeData(String str, String imagePath) {
        this.strNode = str;
        this.path = imagePath;
    }
    
    public JTreeData(String str) {
        this.strNode = str;
    }
    
    public String getString() {
        return this.strNode;
    }
    
    public void setString(String strNode) {
        this.strNode = strNode;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public JComponent getJcp() {
        return this.jcp;
    }
    
    public void setJcp(JComponent jcp) {
        this.jcp = jcp;
    }
    
    public static void ShowFriendMsg(DefaultMutableTreeNode top, JTree jTree) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        top.removeAllChildren();
        if (UserMessUntil.getFriendtables() != null && UserMessUntil.getFriendtables().size() > 0) {
            int num = UserMessUntil.getFriendtables().size();
            DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("大话精灵");
            DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("我的好友[" + num + "]");
            for (int i = 0; i < num; ++i) {
                Friendtable friendtable = (Friendtable)UserMessUntil.getFriendtables().get(i);
                if (nao.equals("新")) {
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new JTreeData(friendtable.getRolename(), "img/head/s" + friendtable.getSpecies_id() + "-1.png"));
                    node2.add(childNode);
                }
                else {
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new JTreeData(friendtable.getRolename(), "img/head/s" + friendtable.getSpecies_id() + ".png"));
                    node2.add(childNode);
                }
            }
            top.add(node1);
            top.add(node2);
            if (jTree != null) {
                jTree.updateUI();
            }
        }
        else {
            DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("大话精灵");
            DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("我的好友[0]");
            top.add(node3);
            top.add(node1);
            if (jTree != null) {
                jTree.updateUI();
            }
        }
    }
}
