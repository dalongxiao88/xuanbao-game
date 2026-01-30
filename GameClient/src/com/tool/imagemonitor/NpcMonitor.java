package com.tool.imagemonitor;

import org.come.until.SplitStringTool;
import com.tool.ModerateTask.Task;
import org.come.Frame.NPCJfram;
import io.netty.util.internal.StringUtil;
import org.come.bean.NpcSureMenuBean;
import org.come.until.ControlNpcXmlUntil;
import org.come.bean.NpcMenuBean;
import com.tool.ModerateTask.Hero;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.image.ImageMixDeal;
import org.come.bean.NpcInfoBean;
import org.come.until.MessagrFlagUntil;
import com.tool.image.ManimgAttribute;
import java.util.List;

public class NpcMonitor
{
    public static final List<String> NPC;
    
    public static void npc(ManimgAttribute attribute) {
        if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1) || MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12) || MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
            npc(attribute.getNpc());
        }
        else if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE3)) {
            PlayerMonitor.give(attribute);
        }
    }
    
    public static void npc(NpcInfoBean npc) {
        String[] v = ImageMixDeal.userimg.getTeams();
        int npctype = Integer.parseInt(npc.getNpctable().getNpctype());
        if (npctype == 1002 || npctype == 1107 || npctype == 521 || (npctype >= 1101 && npctype <= 1105) || (npctype >= 510 && npctype <= 514) || npctype == 2020 || npctype == 2022 || npctype == 2023 || npctype == 886 || npctype == 4002 || npctype == 4003 || npctype == 4004 || npctype == 4005) {
            String sendmes = Agreement.getAgreement().gangmonitor(npc.getNpctable().getNpctype());
            SendMessageUntil.toServer(sendmes);
            return;
        }
        Task task = Hero.getHero().PartFinish("问候", npc.getNpctable().getNpcname());
        if (v != null && v.length > 1 && NpcMonitor.NPC.contains(npc.getNpctable().getNpctype())) {
            String serverMes = Agreement.getAgreement().NPCDialogAgreement(npc.getNpctable().getNpcid());
            SendMessageUntil.toServer(serverMes);
            System.out.println("问候：" + npc.getNpctable().getNpcid() + " type:" + npc.getNpctable().getNpctype());
        }
        else {
            ControlNpcXmlUntil.setNpcmenubean(new NpcMenuBean());
            ControlNpcXmlUntil.setSureBean(new NpcSureMenuBean());
            ControlNpcXmlUntil.setType(npc.getNpctable().getNpctype());
            try {
                ControlNpcXmlUntil.GetXmlPath("npcMenu.xml");
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            if (task == null && StringUtil.isNullOrEmpty(npc.getNpctable().getTalkid()) && npc.getNpctable().getNpctype().equals("0") && StringUtil.isNullOrEmpty(npc.getNpctable().getNpcway()) && npc.getDoors() == null && StringUtil.isNullOrEmpty(npc.getNpctable().getBinding())) {
                return;
            }
            NPCJfram.getNpcJfram().getNpcjpanel().npc(ControlNpcXmlUntil.getNpcmenubean(), npc);
        }
        if (task != null) {
            String mes = Agreement.getAgreement().TaskNAgreement("W" + task.getTaskId() + "|" + npc.getNpctable().getNpcname());
            SendMessageUntil.toServer(mes);
        }
    }
    
    public static void npc(int npctype) {
        if (npctype == 1002 || npctype == 1107 || npctype == 521 || (npctype >= 1101 && npctype <= 1105) || (npctype >= 510 && npctype <= 514) || npctype == 2020 || npctype == 2022 || npctype == 2023) {
            String sendmes = Agreement.getAgreement().gangmonitor(npctype + "");
            SendMessageUntil.toServer(sendmes);
            return;
        }
        ControlNpcXmlUntil.setNpcmenubean(new NpcMenuBean());
        ControlNpcXmlUntil.setSureBean(new NpcSureMenuBean());
        ControlNpcXmlUntil.setType(npctype + "");
        try {
            ControlNpcXmlUntil.GetXmlPath("npcMenu.xml");
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        NPCJfram.getNpcJfram().getNpcjpanel().npc(ControlNpcXmlUntil.getNpcmenubean(), npctype);
    }
    
    static {
        NPC = SplitStringTool.splitString("5-9|12-18|31|38-40|44-48|50|54|57|58|60|61|66|69|71|77|78");
    }
}
