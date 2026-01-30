package com.tool.btn.spot;

import com.tool.Stall.Stall;
import org.come.Jpanel.spot.buy.SpotBuyPurchaseJpanel;
import org.come.Jpanel.spot.buy.SpotBuySellJpanel;
import org.come.Jpanel.spot.buy.SpotBuyBaseJpanel;
import com.tool.imagemonitor.PlayerMonitor;
import org.come.Jpanel.spot.stall.SpotStallRecordJpanel;
import org.come.Jpanel.spot.stall.SpotStallPurchaseJpanel;
import org.come.Jpanel.spot.stall.SpotStallSellJpanel;
import org.come.Jpanel.spot.stall.SpotStallBaseJpanel;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.UserStallUntil;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.spot.buy.SpotBuyJpanel;
import org.come.Jpanel.spot.stall.SpotStallJpanel;
import com.tool.btn.MoBanBtn;

public class SpotPublishBtn extends MoBanBtn
{
    private int operationId;
    private SpotStallJpanel spotStallJpanel;
    private SpotBuyJpanel spotBuyJpanel;
    
    public SpotPublishBtn(String path, int type, Color[] colors, Font font, String text, int operationId, SpotStallJpanel spotStallJpanel) {
        super(path, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.operationId = operationId;
        this.spotStallJpanel = spotStallJpanel;
    }
    
    public SpotPublishBtn(String path, int type, int operationId, SpotStallJpanel spotStallJpanel) {
        super(path, type);
        this.operationId = operationId;
        this.spotStallJpanel = spotStallJpanel;
    }
    
    public SpotPublishBtn(String path, int type, int operationId, SpotBuyJpanel spotBuyJpanel) {
        super(path, type);
        this.operationId = operationId;
        this.spotBuyJpanel = spotBuyJpanel;
    }
    
    public SpotPublishBtn(String path, int type, Color[] colors, Font font, String text, int operationId, SpotBuyJpanel spotBuyJpanel) {
        super(path, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.operationId = operationId;
        this.spotBuyJpanel = spotBuyJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.spotStallJpanel != null) {
            if (this.operationId == 0) {
                UserStallUntil.endStall();
            }
            else if (this.operationId == 1) {
                String stallName = this.spotStallJpanel.getStallName();
                if (stallName.length() > 7) {
                    ZhuFrame.getZhuJpanel().addPrompt2("#R最大7个字符！");
                    return;
                }
                Stall stall = UserStallUntil.copyStall();
                stall.setStall(stallName);
                SendMessageUntil.toServer(Agreement.getAgreement().updateStallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else if (this.spotStallJpanel instanceof SpotStallBaseJpanel) {
                SpotStallBaseJpanel spotStallBaseJpanel = (SpotStallBaseJpanel)this.spotStallJpanel;
                if (this.operationId == 10) {
                    UserStallUntil.showBuyStall();
                }
                else if (spotStallBaseJpanel instanceof SpotStallSellJpanel) {
                    SpotStallSellJpanel spotStallSellJpanel = (SpotStallSellJpanel)spotStallBaseJpanel;
                    if (this.operationId == 100) {
                        if (this.getText().equals("上架")) {
                            spotStallSellJpanel.changePublishBtn(true);
                            spotStallSellJpanel.listing();
                        }
                        else if (this.getText().equals("下架")) {
                            spotStallSellJpanel.changePublishBtn(false);
                            spotStallSellJpanel.withdraw();
                        }
                    }
                    else if (this.operationId != 101) {
                        if (this.operationId == 102) {
                            spotStallSellJpanel.setShareVisible();
                        }
                        else if (this.operationId == 103) {
                            UserStallUntil.shareStallPoint();
                            spotStallSellJpanel.setShareVisible(false);
                        }
                        else if (this.operationId == 104) {
                            UserStallUntil.shareStall();
                            spotStallSellJpanel.setShareVisible(false);
                        }
                    }
                }
                else if (spotStallBaseJpanel instanceof SpotStallPurchaseJpanel) {
                    SpotStallPurchaseJpanel spotStallPurchaseJpanel = (SpotStallPurchaseJpanel)spotStallBaseJpanel;
                    if (this.operationId == 110) {
                        spotStallPurchaseJpanel.search();
                    }
                    else if (this.operationId == 111) {
                        spotStallPurchaseJpanel.listing();
                    }
                }
            }
            else if (this.spotStallJpanel instanceof SpotStallRecordJpanel && (this.operationId >= 21 || this.operationId <= 22)) {
                UserStallUntil.updateBuyStall();
            }
        }
        else if (this.spotBuyJpanel != null) {
            if (this.operationId == 0) {
                Stall stall2 = this.spotBuyJpanel.getStall();
                PlayerMonitor.addFriend(stall2.getRoleid(), stall2.getStall());
            }
            else if (this.operationId == 1) {
                Stall stall2 = this.spotBuyJpanel.getStall();
                UserStallUntil.updateFollow(Integer.valueOf(stall2.getId()));
            }
            else if (this.spotBuyJpanel instanceof SpotBuyBaseJpanel) {
                SpotBuyBaseJpanel spotBuyBaseJpanel = (SpotBuyBaseJpanel)this.spotBuyJpanel;
                if (this.operationId == 10) {
                    spotBuyBaseJpanel.conductTransactions();
                }
                else if (spotBuyBaseJpanel instanceof SpotBuySellJpanel) {
                    SpotBuySellJpanel spotBuySellJpanel = (SpotBuySellJpanel)spotBuyBaseJpanel;
                }
                else if (spotBuyBaseJpanel instanceof SpotBuyPurchaseJpanel) {
                    SpotBuyPurchaseJpanel spotBuyPurchaseJpanel = (SpotBuyPurchaseJpanel)spotBuyBaseJpanel;
                }
            }
        }
    }
}
