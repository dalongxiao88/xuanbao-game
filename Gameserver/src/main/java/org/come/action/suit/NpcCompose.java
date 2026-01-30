package org.come.action.suit;

import org.come.entity.Goodstable;
import java.util.List;
import java.math.BigDecimal;
import org.come.bean.SuitOperBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.LoginResult;

public class NpcCompose
{
    public static void typeNPC(LoginResult loginResult, ChannelHandlerContext ctx, SuitOperBean suitOperBean) {
        for (int i = suitOperBean.getGoods().size() - 1; i >= 0; --i) {
            BigDecimal rgid = (BigDecimal)suitOperBean.getGoods().get(i);
            int j = 0;
            while (j < i) {
                if (rgid.compareTo((BigDecimal)suitOperBean.getGoods().get(j)) == 0) {
                    suitOperBean.getGoods().remove(j);
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        if (suitOperBean.getGoods().size() != 2) {
            return;
        }
        List<Goodstable> goods = SuitComposeAction.getGoods(suitOperBean.getGoods(), loginResult.getRole_id(), 0);
        if (goods == null || goods.size() != 2) {
            return;
        }
        if (suitOperBean.getType() == 71) {
            type71(loginResult, ctx, goods);
        }
    }
    
    public static void type71(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type72(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type73(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type74(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type75(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type76(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type77(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type78(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type79(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type80(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
    
    public static void type81(LoginResult loginResult, ChannelHandlerContext ctx, List<Goodstable> goods) {
    }
}
