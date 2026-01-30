package org.come.until;

import java.util.ArrayList;
import org.come.model.Eshop;
import java.util.List;

public class DeviceEshopUntil
{
    private static List<Eshop> shopingMenuSkillJpanel;
    private static List<Eshop> shopingMenuTreasures;
    private static List<Eshop> shopingMenuAccessoriesJpanel;
    private static List<Eshop> shopingMenuFairyDeviceJpanel;
    private static List<Eshop> shopingMenuGodBeastJpanel;
    private static List<Eshop> shopingMenuPreferentialTreatmentJpanel;
    private static List<Eshop> shopingMenuSkillBookJpanel;
    private static List<Eshop> shopingMenuQuestJpanel;
    private static List<Eshop> shopingMenutrepanningJpanel;
    private static List<Eshop> shopingMenuTransfergoldJpanel;
    private static List<Eshop> shopingTX;
    private static List<Eshop> shopingZS;
    private static List<Eshop> shopingZJ;
    private static List<Eshop> shopingTXK;
    
    public static void initEshopUntil(List<Eshop> eshopGoodsTable) {
        for (int i = 0; i < eshopGoodsTable.size(); ++i) {
            Eshop eshop = (Eshop)eshopGoodsTable.get(i);
            if (!eshop.getEshoptype().equals("")) {
                int a = (int)Integer.valueOf(eshop.getEshoptype());
                switch (a) {
                    case 0: {
                        DeviceEshopUntil.shopingMenuTreasures.add(eshop);
                        break;
                    }
                    case 1: {
                        DeviceEshopUntil.shopingMenuSkillJpanel.add(eshop);
                        break;
                    }
                    case 2: {
                        DeviceEshopUntil.shopingMenuGodBeastJpanel.add(eshop);
                        break;
                    }
                    case 3: {
                        DeviceEshopUntil.shopingMenuFairyDeviceJpanel.add(eshop);
                        break;
                    }
                    case 4: {
                        DeviceEshopUntil.shopingMenuAccessoriesJpanel.add(eshop);
                        break;
                    }
                    case 5: {
                        DeviceEshopUntil.shopingMenuPreferentialTreatmentJpanel.add(eshop);
                        break;
                    }
                    case 6: {
                        DeviceEshopUntil.shopingMenuSkillBookJpanel.add(eshop);
                        break;
                    }
                    case 7: {
                        DeviceEshopUntil.shopingMenuQuestJpanel.add(eshop);
                        break;
                    }
                    case 8: {
                        DeviceEshopUntil.shopingMenutrepanningJpanel.add(eshop);
                        break;
                    }
                    case 9: {
                        DeviceEshopUntil.shopingMenuTransfergoldJpanel.add(eshop);
                        break;
                    }
                    case 11: {
                        DeviceEshopUntil.shopingTX.add(eshop);
                        break;
                    }
                    case 12: {
                        DeviceEshopUntil.shopingZS.add(eshop);
                        break;
                    }
                    case 13: {
                        DeviceEshopUntil.shopingZJ.add(eshop);
                        break;
                    }
                    case 14: {
                        DeviceEshopUntil.shopingTXK.add(eshop);
                        break;
                    }
                }
            }
        }
    }
    
    public static List<Eshop> getShopingMenuSkillJpanel() {
        return DeviceEshopUntil.shopingMenuSkillJpanel;
    }
    
    public static void setShopingMenuSkillJpanel(List<Eshop> shopingMenuSkillJpanel) {
        DeviceEshopUntil.shopingMenuSkillJpanel = shopingMenuSkillJpanel;
    }
    
    public static List<Eshop> getShopingMenuTreasures() {
        return DeviceEshopUntil.shopingMenuTreasures;
    }
    
    public static void setShopingMenuTreasures(List<Eshop> shopingMenuTreasures) {
        DeviceEshopUntil.shopingMenuTreasures = shopingMenuTreasures;
    }
    
    public static List<Eshop> getShopingMenuAccessoriesJpanel() {
        return DeviceEshopUntil.shopingMenuAccessoriesJpanel;
    }
    
    public static void setShopingMenuAccessoriesJpanel(List<Eshop> shopingMenuAccessoriesJpanel) {
        DeviceEshopUntil.shopingMenuAccessoriesJpanel = shopingMenuAccessoriesJpanel;
    }
    
    public static List<Eshop> getShopingMenuFairyDeviceJpanel() {
        return DeviceEshopUntil.shopingMenuFairyDeviceJpanel;
    }
    
    public static void setShopingMenuFairyDeviceJpanel(List<Eshop> shopingMenuFairyDeviceJpanel) {
        DeviceEshopUntil.shopingMenuFairyDeviceJpanel = shopingMenuFairyDeviceJpanel;
    }
    
    public static List<Eshop> getShopingMenuGodBeastJpanel() {
        return DeviceEshopUntil.shopingMenuGodBeastJpanel;
    }
    
    public static void setShopingMenuGodBeastJpanel(List<Eshop> shopingMenuGodBeastJpanel) {
        DeviceEshopUntil.shopingMenuGodBeastJpanel = shopingMenuGodBeastJpanel;
    }
    
    public static List<Eshop> getShopingMenuPreferentialTreatmentJpanel() {
        return DeviceEshopUntil.shopingMenuPreferentialTreatmentJpanel;
    }
    
    public static void setShopingMenuPreferentialTreatmentJpanel(List<Eshop> shopingMenuPreferentialTreatmentJpanel) {
        DeviceEshopUntil.shopingMenuPreferentialTreatmentJpanel = shopingMenuPreferentialTreatmentJpanel;
    }
    
    public static List<Eshop> getShopingTX() {
        return DeviceEshopUntil.shopingTX;
    }
    
    public static void setShopingTX(List<Eshop> shopingTX) {
        DeviceEshopUntil.shopingTX = shopingTX;
    }
    
    public static List<Eshop> getShopingZS() {
        return DeviceEshopUntil.shopingZS;
    }
    
    public static void setShopingZS(List<Eshop> shopingZS) {
        DeviceEshopUntil.shopingZS = shopingZS;
    }
    
    public static List<Eshop> getShopingZJ() {
        return DeviceEshopUntil.shopingZJ;
    }
    
    public static void setShopingZJ(List<Eshop> shopingZJ) {
        DeviceEshopUntil.shopingZJ = shopingZJ;
    }
    
    public static List<Eshop> getShopingTXK() {
        return DeviceEshopUntil.shopingTXK;
    }
    
    public static void setShopingTXK(List<Eshop> shopingTXK) {
        DeviceEshopUntil.shopingTXK = shopingTXK;
    }
    
    public static List<Eshop> getShopingMenuSkillBookJpanel() {
        return DeviceEshopUntil.shopingMenuSkillBookJpanel;
    }
    
    public static void setShopingMenuSkillBookJpanel(List<Eshop> shopingMenuSkillBookJpanel) {
        DeviceEshopUntil.shopingMenuSkillBookJpanel = shopingMenuSkillBookJpanel;
    }
    
    public static List<Eshop> getShopingMenuQuestJpanel() {
        return DeviceEshopUntil.shopingMenuQuestJpanel;
    }
    
    public static void setShopingMenuQuestJpanel(List<Eshop> shopingMenuQuestJpanel) {
        DeviceEshopUntil.shopingMenuQuestJpanel = shopingMenuQuestJpanel;
    }
    
    public static List<Eshop> getShopingMenutrepanningJpanel() {
        return DeviceEshopUntil.shopingMenutrepanningJpanel;
    }
    
    public static void setShopingMenutrepanningJpanel(List<Eshop> shopingMenutrepanningJpanel) {
        DeviceEshopUntil.shopingMenutrepanningJpanel = shopingMenutrepanningJpanel;
    }
    
    public static List<Eshop> getShopingMenuTransfergoldJpanel() {
        return DeviceEshopUntil.shopingMenuTransfergoldJpanel;
    }
    
    public static void setShopingMenuTransfergoldJpanel(List<Eshop> shopingMenuTransfergoldJpanel) {
        DeviceEshopUntil.shopingMenuTransfergoldJpanel = shopingMenuTransfergoldJpanel;
    }
    
    static {
        DeviceEshopUntil.shopingMenuSkillJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuTreasures = new ArrayList<>();
        DeviceEshopUntil.shopingMenuAccessoriesJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuFairyDeviceJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuGodBeastJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuPreferentialTreatmentJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuSkillBookJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuQuestJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenutrepanningJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingMenuTransfergoldJpanel = new ArrayList<>();
        DeviceEshopUntil.shopingTX = new ArrayList<>();
        DeviceEshopUntil.shopingZS = new ArrayList<>();
        DeviceEshopUntil.shopingZJ = new ArrayList<>();
        DeviceEshopUntil.shopingTXK = new ArrayList<>();
    }
}
