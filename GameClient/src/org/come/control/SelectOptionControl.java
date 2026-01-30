package org.come.control;

import org.come.until.SendRoleAndRolesummingUntil;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.NPCJfram;
import org.come.action.NpcMenuAction;

public class SelectOptionControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("HP")) {
            this.selectValue("HP");
        }
        else if (type.equals("MP")) {
            this.selectValue("MP");
        }
        else if (type.equals("AP")) {
            this.selectValue("AP");
        }
        else if (type.equals("SP")) {
            this.selectValue("SP");
        }
        else if (type.equals("是")) {
            this.selectChange("是");
        }
        else if (type.equals("否")) {
            this.selectChange("否");
        }
    }
    
    public void selectValue(String flag) {
        String[] mes = NPCJfram.getNpcJfram().getNpcjpanel().getPettype().split("\\|");
        int value = 0;
        if (flag.equals("HP")) {
            value = 0;
        }
        else if (flag.equals("MP")) {
            value = 1;
        }
        else if (flag.equals("AP")) {
            value = 2;
        }
        else if (flag.equals("SP")) {
            value = 3;
        }
        String sendmes = Agreement.getAgreement().userpetAgreement("N|" + mes[0] + "|" + value);
        SendMessageUntil.toServer(sendmes);
    }
    
    public void selectChange(String flag) {
        if (!flag.equals("是")) {
            return;
        }
        String[] mes = NPCJfram.getNpcJfram().getNpcjpanel().getPettype().split("\\|");
        BigDecimal sid = new BigDecimal(mes[0]);
        RoleSummoning pet = null;
        if (sid != null) {
            int i = 0;
            while (i < UserMessUntil.getPetListTable().size()) {
                if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().longValue() == sid.longValue()) {
                    pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        if (pet == null) {
            return;
        }
        this.otherPetId(pet);
        SendRoleAndRolesummingUntil.sendRoleSummingSkill(pet);
    }
    
    public void changePifu(RoleSummoning pet) {
        if (pet.getSummoningid().equals("200102")) {
            pet.setSummoningskin("400105");
        }
        else if (pet.getSummoningid().equals("200103")) {
            pet.setSummoningskin("400102");
        }
        else if (pet.getSummoningid().equals("200104")) {
            pet.setSummoningskin("400106");
        }
        else if (pet.getSummoningid().equals("200105")) {
            pet.setSummoningskin("400105");
        }
        else if (pet.getSummoningid().equals("200106")) {
            pet.setSummoningskin("400103");
        }
        else if (pet.getSummoningid().equals("200107")) {
            pet.setSummoningskin("400104");
        }
    }
    
    public void otherPetId(RoleSummoning pet) {
        int flag = -1;
        if (pet.getRevealNum() == 1) {
            flag = 0;
        }
        else if (pet.getRevealNum() == 2) {
            flag = 1;
        }
        if (flag == 0) {
            if (pet.getSummoningid().equals("200102")) {
                pet.setSummoningskin("400078");
                pet.setGold("0");
                pet.setWood("0");
                pet.setSoil("5");
                pet.setWater("0");
                pet.setFire("95");
                pet.setSkill("1056|1057|1058|1059");
            }
            else if (pet.getSummoningid().equals("200103")) {
                pet.setSummoningskin("400080");
                pet.setGold("40");
                pet.setWood("0");
                pet.setSoil("0");
                pet.setWater("0");
                pet.setFire("60");
            }
            else if (pet.getSummoningid().equals("200104")) {
                pet.setSummoningskin("400083");
                pet.setGold("0");
                pet.setWood("0");
                pet.setSoil("60");
                pet.setWater("40");
                pet.setFire("0");
            }
            else if (pet.getSummoningid().equals("200105")) {
                pet.setSummoningskin("400072");
                pet.setGold("5");
                pet.setWood("0");
                pet.setSoil("0");
                pet.setWater("95");
                pet.setFire("0");
                pet.setSkill("1051|1052|1053|1054");
            }
            else if (pet.getSummoningid().equals("200106")) {
                pet.setSummoningskin("400079");
                pet.setGold("0");
                pet.setWood("60");
                pet.setSoil("0");
                pet.setWater("40");
                pet.setFire("0");
            }
            else if (pet.getSummoningid().equals("200107")) {
                pet.setSummoningskin("400064");
                pet.setGold("0");
                pet.setWood("0");
                pet.setSoil("15");
                pet.setWater("85");
                pet.setFire("0");
            }
        }
        else if (flag == 1) {
            if (pet.getSummoningid().equals("200102")) {
                pet.setSummoningskin("400105");
                pet.setGold("0");
                pet.setWood("0");
                pet.setSoil("30");
                pet.setWater("70");
                pet.setFire("0");
                pet.setSkill("1051|1052|1053|1054");
            }
            else if (pet.getSummoningid().equals("200103")) {
                pet.setSummoningskin("400102");
                pet.setGold("0");
                pet.setWood("35");
                pet.setSoil("15");
                pet.setWater("50");
                pet.setFire("0");
            }
            else if (pet.getSummoningid().equals("200104")) {
                pet.setSummoningskin("400106");
                pet.setGold("25");
                pet.setWood("0");
                pet.setSoil("0");
                pet.setWater("0");
                pet.setFire("75");
            }
            else if (pet.getSummoningid().equals("200105")) {
                pet.setSummoningskin("400105");
                pet.setGold("0");
                pet.setWood("0");
                pet.setSoil("30");
                pet.setWater("70");
                pet.setFire("0");
                pet.setSkill("1051|1052|1053|1054");
            }
            else if (pet.getSummoningid().equals("200106")) {
                pet.setSummoningskin("400103");
                pet.setGold("75");
                pet.setWood("0");
                pet.setSoil("0");
                pet.setWater("25");
                pet.setFire("0");
            }
            else if (pet.getSummoningid().equals("200107")) {
                pet.setSummoningskin("400104");
                pet.setGold("40");
                pet.setWood("0");
                pet.setSoil("0");
                pet.setWater("60");
                pet.setFire("0");
                pet.setSkill("1046|1047|1048|1049");
            }
        }
    }
}
