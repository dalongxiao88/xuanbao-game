package org.come.control;

import come.tool.Fighting.Fightingimage;
import org.come.Jpanel.FrameMessageChangeJpanel;
import come.tool.Fighting.FightingState;
import org.come.until.GsonUtil;
import come.tool.Fighting.FightingEvents;
import come.tool.Fighting.FightingMixDeal;
import org.come.action.FromServerAction;

public class FightingroundControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (FightingMixDeal.State <= 3) {
            FightingEvents fightingEvents = (FightingEvents)GsonUtil.getGsonUtil().getgson().fromJson(mes, FightingEvents.class);
            if (FightingMixDeal.camp == fightingEvents.getOriginator().getCamp()) {
                Fightingimage fightingimage = FightingMixDeal.CurrentDataImage(fightingEvents.getOriginator().getCamp(), fightingEvents.getOriginator().getMan());
                StringBuffer buffer = new StringBuffer();
                if (fightingimage.getFightingManData().getType() == 1) {
                    Fightingimage fightingimage2 = FightingMixDeal.CurrentDataImage(fightingEvents.getOriginator().getCamp(), fightingEvents.getOriginator().getMan() - 5);
                    buffer.append("#G");
                    buffer.append(fightingimage2.getFightingManData().getManname());
                    buffer.append("#W");
                    buffer.append("的");
                }
                buffer.append("#G");
                buffer.append(fightingimage.getFightingManData().getManname());
                if (fightingEvents.getAccepterlist() != null && ((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp() != -1 && ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan() != -1) {
                    Fightingimage fightingimage3 = FightingMixDeal.CurrentDataImage(((FightingState)fightingEvents.getAccepterlist().get(0)).getCamp(), ((FightingState)fightingEvents.getAccepterlist().get(0)).getMan());
                    if (fightingimage3 != null) {
                        buffer.append("#W");
                        buffer.append("对");
                        buffer.append("#R");
                        buffer.append(fightingimage3.getFightingManData().getManname());
                    }
                }
                String operationName = fightingEvents.getOriginator().getStartState();
                buffer.append("#W");
                if (operationName.startsWith("召唤")) {
                    buffer.append("召唤");
                    buffer.append("#G");
                    if (operationName.startsWith("召唤灵宝")) {
                        buffer.append(operationName.split("\\&")[2]);
                    }
                    else {
                        buffer.append(operationName.split("\\&")[2]);
                    }
                }
                else {
                    buffer.append("使用");
                    buffer.append("#Y");
                    int n = -1;
                    switch (operationName.hashCode()) {
                        case 817455244: {
                            if (operationName.equals("普通攻击")) {
                                n = 0;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        case 814717: {
                            if (operationName.equals("技能")) {
                                n = 1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        case 33647: {
                            if (operationName.equals("药")) {
                                n = 2;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                    }
                    switch (n) {
                        case 0: {
                            operationName = "物理攻击";
                            break;
                        }
                        case 1: {
                            operationName = fightingEvents.getOriginator().getEndState();
                            break;
                        }
                        case 2: {
                            operationName = fightingEvents.getOriginator().getText();
                            break;
                        }
                    }
                    buffer.append(operationName);
                }
                FrameMessageChangeJpanel.addtext(11, buffer.toString(), null, null);
                fightingimage.setOperation(true);
            }
        }
    }
}
