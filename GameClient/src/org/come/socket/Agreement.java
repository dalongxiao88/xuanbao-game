package org.come.socket;

import org.come.until.AC999;
import org.come.until.NewAESUtil;

public class Agreement
{
    private static Agreement agreement;
    static final String FG = "//";
    
    public static Agreement getAgreement() {
        if (Agreement.agreement == null) {
            Agreement.agreement = new Agreement();
        }
        return Agreement.agreement;
    }
    
    public String auctionGoods(String content) {
        return AC999.AESJDKEncode("auctionGoods//" + content);
    }
    
    public String successLoginAgreement(String Content) {
        return AC999.AESJDKEncode("loginsuccess//" + Content);
    }
    
    public String erroLoginAgreement() {
        return AC999.AESJDKEncode("loginerror//");
    }
    
    public String inlineLoginAgreement() {
        return AC999.AESJDKEncode("inlinelogin//");
    }
    
    public String successRigisterAgreement() {
        return AC999.AESJDKEncode("rigistersuccess//");
    }
    public static String Updatexuan(String Content) {
//        return NewAESUtil.AESJDKEncode("updatexuan//" + Content);
        return AC999.AESJDKEncode(AgreementUtil.updatexuan + FG + Content);
    }
    public String itemAgreement(String Content) {
        return AC999.AESJDKEncode("item//" + Content);
    }
    
    public String erroRigisterAgreement() {
        return AC999.AESJDKEncode("rigistererror//");
    }
    
    public String successCreateAgreement(String Content) {
        return AC999.AESJDKEncode("createsuccess//" + Content);
    }
    
    public String errorCreateAgreement() {
        return AC999.AESJDKEncode("createerror//");
    }
    
    public String TeamApplyExistAgreement() {
        return AC999.AESJDKEncode("teamexist//");
    }
    
    public String UserRetreatAgreement(String Content) {
        return AC999.AESJDKEncode("userretreat//" + Content);
    }
    
    public String TransAllListAgreement(String Content) {
        return AC999.AESJDKEncode("transalllist//" + Content);
    }
    
    public String PetInfoAgreement(String Content) {
        return AC999.AESJDKEncode("petinfo//" + Content);
    }
    
    public String NpcMonsterAgreement(String Content) {
        return AC999.AESJDKEncode("npcmonster//" + Content);
    }
    
    public String PetAlchemyAgreement(String Content) {
        return AC999.AESJDKEncode("petalchemy//" + Content);
    }
    
    public String stringTeamCaptain(String Content) {
        return AC999.AESJDKEncode("stringTeamCaptain//" + Content);
    }
    
    public String PongAgreement() {
        return AC999.AESJDKEncode("pong//");
    }
    
    public String RecivePawnAgreement(String Content) {
        return AC999.AESJDKEncode("retrieve//" + Content);
    }
    
    public String VersionAgreement(String Content) {
        return AC999.AESJDKEncode("version//" + Content);
    }
    
    public String ZeropointAgreement(String Content) {
        return AC999.AESJDKEncode("zeropoint//" + Content);
    }
    
    public String bangtzAgreement(String Content) {
        return AC999.AESJDKEncode("bangtz//" + Content);
    }
    
    public String ChangeRoleNameAgreement(String Content) {
        return AC999.AESJDKEncode("changerolename//" + Content);
    }
    
    public String RoleLevelUpAgreement(String Content) {
        return AC999.AESJDKEncode("rolelevelup//" + Content);
    }
    
    public String LoginAgreement(String Content) {
        return AC999.AESJDKEncode("login//" + Content);
    }
    
    public String registerAgreement(String Content) {
        return AC999.AESJDKEncode("register//" + Content);
    }
    
    public String createRoleAgreement(String Content) {
        return AC999.AESJDKEncode("createrole//" + Content);
    }
    
    public String intogameAgreement(String Content) {
        return AC999.AESJDKEncode("intogame//" + Content);
    }
    
    public String chatAgreement(String Content) {
        return AC999.AESJDKEncode("chat//" + Content);
    }
    
    public String npcAgreement(String Content) {
        return AC999.AESJDKEncode("npc//" + Content);
    }
    
    public String packchangeAgreement(String Content) {
        return AC999.AESJDKEncode("packchange//" + Content);
    }
    
    public String EshopAgreement(String Content) {
        return AC999.AESJDKEncode("eshop//" + Content);
    }
    
    public String MountAgreement(String Content) {
        return AC999.AESJDKEncode("mount//" + Content);
    }
    
    public String MountAgreement() {
        return AC999.AESJDKEncode("mount//");
    }
    public String CarAgreement() {
        return AC999.AESJDKEncode("car//");
    }

    public String FlyAgreement() {
        return AC999.AESJDKEncode("fly//");
    }
    
    public static String useflyAgreement(String Content) {
        return AC999.AESJDKEncode("usefly//" + Content);
    }
    
    public static String useflyFuelAgreement(String Content) {
        return AC999.AESJDKEncode("flyFuel//" + Content);
    }
    
    public String friendAgreement(String Content) {
        return AC999.AESJDKEncode("friend//" + Content);
    }
    
    public String addFrientAgreement(String Content) {
        return AC999.AESJDKEncode("addfriend//" + Content);
    }
    
    public String friendchatAgreement(String Content) {
        return AC999.AESJDKEncode("friendchat//" + Content);
    }
    
    public String BuyNPCGoodsAgreement(String Content) {
        return AC999.AESJDKEncode("shop//" + Content);
    }
    
    public String BuyShopGoodsAgreement(String Content) {
        return AC999.AESJDKEncode("shopGood//" + Content);
    }
    
    public String ShopPriceAgreement(String Content) {
        return AC999.AESJDKEncode("shopPrice//" + Content);
    }
    
    public String ChangemapAgreement(String Content) {
        return AC999.AESJDKEncode("changemap//" + Content);
    }
    
    public String ganglistAgreement(String Content) {
        return AC999.AESJDKEncode("ganglist//" + Content);
    }
    
    public String giveAgreement(String Content) {
        return AC999.AESJDKEncode("give//" + Content);
    }
    
    public String petAgreement(String Content) {
        return AC999.AESJDKEncode("pet//" + Content);
    }
    
    public String petchangeAgreement(String Content) {
        return AC999.AESJDKEncode("shuanimabipetchange//" + Content);
    }
    
    public String petchangeSkillAgreement(String Content) {
        return AC999.AESJDKEncode("petchangeSkill//" + Content);
    }
    
    public String petDepositAction(String Content) {
        return AC999.AESJDKEncode("petDepositAction//" + Content);
    }
    
    public String packlockAgreement(String Content) {
        return AC999.AESJDKEncode("packlock//" + Content);
    }
    
    public String packgiftAgreement(String Content) {
        return AC999.AESJDKEncode("packgift//" + Content);
    }
    
    public String npcgiftAgreement(String Content) {
        return AC999.AESJDKEncode("npcgift//" + Content);
    }
    
    public String skilllearnAgreement(String Content) {
        return AC999.AESJDKEncode("skilllearn//" + Content);
    }
    
    public String MonsterRefreshAgreement(String Content) {
        return AC999.AESJDKEncode("monsterrefresh//" + Content);
    }
    
    public String CreepsFightAgreement(String Content) {
        return AC999.AESJDKEncode("creepsfight//" + Content);
    }
    
    public String SummonpetAgreement(String Content) {
        return AC999.AESJDKEncode("Summonpet//" + Content);
    }
    
    public String npccomposeAgreement(String Content) {
        return AC999.AESJDKEncode("npccompose//" + Content);
    }
    
    public String witchcomposeAgreement(String Content) {
        return AC999.AESJDKEncode("witchcompose//" + Content);
    }
    
    public String rolechangeAgreement(String Content) {
        return AC999.AESJDKEncode("rolechange//" + Content);
    }
    
    public String roleAttributeAgreement(String Content) {
        return AC999.AESJDKEncode("roleAttribute//" + Content);
    }
    
    public String summonpetAgreement(String Content) {
        return AC999.AESJDKEncode("summonpet//" + Content);
    }
    
    public String TitleListAgreement() {
        return AC999.AESJDKEncode("titlelist//");
    }
    
    public String TitleListAgreement(String Content) {
        return AC999.AESJDKEncode("titlelist//" + Content);
    }
    
    public String IntogangAgreement(String Content) {
        return AC999.AESJDKEncode("intogang//" + Content);
    }
    
    public String GangCreateAgreement(String Content) {
        return AC999.AESJDKEncode("gangcreate//" + Content);
    }
    
    public String GangApplyAgreement(String Content) {
        return AC999.AESJDKEncode("gangapply//" + Content);
    }
    
    public static String GangRetreatAgreement(String Content) {
        return AC999.AESJDKEncode("gangretreat//" + Content);
    }
    
    public static String GangChangeAgreementrest(String Content) {
        return AC999.AESJDKEncode("gangchangerest//" + Content);
    }
    
    public static String GangShotAgreement(String Content) {
        return AC999.AESJDKEncode("gangshot//" + Content);
    }
    
    public static String GangAgreeAgreement(String Content) {
        return AC999.AESJDKEncode("gangagree//" + Content);
    }
    
    public static String GangRefuseAgreement(String Content) {
        return AC999.AESJDKEncode("gangrefuse//" + Content);
    }
    
    public static String GangAppointAgreement(String Content) {
        return AC999.AESJDKEncode("gangappoint//" + Content);
    }
    
    public static String GangChangeAgreement(String Content) {
        return AC999.AESJDKEncode("gangchange//" + Content);
    }
    
    public static String npccureAgreement(String Content) {
        return AC999.AESJDKEncode("npccure//" + Content);
    }
    
    public static String givemoneyAgreement(String Content) {
        return AC999.AESJDKEncode("givemoney//" + Content);
    }
    
    public static String marryAgreement(String Content) {
        return AC999.AESJDKEncode("marry//" + Content);
    }
    
    public static String makeloveAgreement(String Content) {
        return AC999.AESJDKEncode("makelove//" + Content);
    }
    
    public String PromptAgreement(String Content) {
        return AC999.AESJDKEncode("prompt//" + Content);
    }
    
    public String TitleChangeAgreement(String Content) {
        return AC999.AESJDKEncode("titlechange//" + Content);
    }
    
    public String TitleExpireAgreement(String Content) {
        return AC999.AESJDKEncode("titleexpirechange//" + Content);
    }
    
    public String PetReleaseAgreement(String Content) {
        return AC999.AESJDKEncode("petrelease//" + Content);
    }
    
    public String PetReleaseFPAgreement(String Content) {
        return AC999.AESJDKEncode("petreleasefp//" + Content);
    }
    
    public String BabyReleaseAgreement(String Content) {
        return AC999.AESJDKEncode("babyrelease//" + Content);
    }
    
    public String BuyMingChaoAgreement() {
        return AC999.AESJDKEncode("buymingchao//");
    }
    
    public String RacialTransformationAgreement(String Content) {
        return AC999.AESJDKEncode("racialtransformation//" + Content);
    }
    
    public String GetTheTaskAgreement(String Content) {
        return AC999.AESJDKEncode("getthetask//" + Content);
    }
    
    public String GiveUpTheTaskAgreement(String Content) {
        return AC999.AESJDKEncode("giveupthetask//" + Content);
    }
    
    public String delectFriend(String Content) {
        return AC999.AESJDKEncode("deletefriend//" + Content);
    }
    
    public String addMountSkill(String Content) {
        return AC999.AESJDKEncode("addmountskill//" + Content);
    }
    
    public String missMountSkill(String Content) {
        return AC999.AESJDKEncode("missmountskill//" + Content);
    }
    
    public String changeMountValue(String Content) {
        return AC999.AESJDKEncode("changemountvalue//" + Content);
    }
    
    public String mountGet(String Content) {
        return AC999.AESJDKEncode("mountget//" + Content);
    }
    
    public String getBaby(String Content) {
        return AC999.AESJDKEncode("baby//" + Content);
    }
    
    public String unMarry(String Content) {
        return AC999.AESJDKEncode("unmarry//" + Content);
    }
    
    public static String EquipmentLing(String Content) {
        return AC999.AESJDKEncode("equipmentLing//" + Content);
    }
    
    public static String Ling(String Content) {
        return AC999.AESJDKEncode("ling//" + Content);
    }
    
    public static String UpdateLing(String Content) {
        return AC999.AESJDKEncode("updateling//" + Content);
    }
    
    public static String UpdateFa(String Content) {
        return AC999.AESJDKEncode("updatefa//" + Content);
    }
    
    public static String Deductiontael(String Content) {
        return AC999.AESJDKEncode("deductiontael//" + Content);
    }
    
    public String babyborn(String Content) {
        return AC999.AESJDKEncode("babyborn//" + Content);
    }
    
    public String applyPay(String Content) {
        return AC999.AESJDKEncode("applypay//" + Content);
    }
    
    public String BabyCustodayAgreement(String Content) {
        return AC999.AESJDKEncode("babycustoday//" + Content);
    }
    
    public String pawnAgreement(String Content) {
        return AC999.AESJDKEncode("pawn//" + Content);
    }
    
    public String battleConnectionAgreement(String Content) {
        return AC999.AESJDKEncode("battleconnection//" + Content);
    }
    
    public String NPCDialogAgreement(String Content) {
        return AC999.AESJDKEncode("npcdialog//" + Content);
    }
    
    public String MiddleAgreement(String Content) {
        return AC999.AESJDKEncode("middle//" + Content);
    }
    
    public String QuoteOutAgreement(String Content) {
        return AC999.AESJDKEncode("quoteout//" + Content);
    }
    
    public String AddGood(String Content) {
        return AC999.AESJDKEncode("addgood//" + Content);
    }
    
    public String updababy(String Content) {
        return AC999.AESJDKEncode("updababy//" + Content);
    }
    
    public String getreward(String Content) {
        return AC999.AESJDKEncode("getreward//" + Content);
    }
    
    public String gangmonitor(String Content) {
        return AC999.AESJDKEncode("gangmonitor//" + Content);
    }
    
    public String gangbattle(String Content) {
        return AC999.AESJDKEncode("gangbattle//" + Content);
    }
    
    public String gangstate(String Content) {
        return AC999.AESJDKEncode("gangstate//" + Content);
    }
    
    public String stallAgreement(String Content) {
        return AC999.AESJDKEncode("stall//" + Content);
    }
    
    public String listingAgreement(String Content) {
        return AC999.AESJDKEncode("listing//" + Content);
    }
    
    public String updateStallAgreement(String content) {
        return AC999.AESJDKEncode("stallUpdate//" + content);
    }
    
    public String stallgetAgreement(String Content) {
        return AC999.AESJDKEncode("stallget//" + Content);
    }
    
    public String stallbuyAgreement(String Content) {
        return AC999.AESJDKEncode("stallbuy//" + Content);
    }
    
    public String assetAgreement(String Content) {
        return AC999.AESJDKEncode("asset//" + Content);
    }
    
    public String stallstateAgreement(String Content) {
        return AC999.AESJDKEncode("stallstate//" + Content);
    }
    
    public String mountreleaseAgreement(String Content) {
        return AC999.AESJDKEncode("mountrelease//" + Content);
    }
    
    public String updateMonstersAgreement(String Content) {
        return AC999.AESJDKEncode("updatemonsters//" + Content);
    }
    
    public String clickMonstersAgreement(String Content) {
        return AC999.AESJDKEncode("clickmonsters//" + Content);
    }
    
    public String throwinarticleAgreement(String Content) {
        return AC999.AESJDKEncode("throwinarticle//" + Content);
    }
    
    public String obtainarticleAgreement(String Content) {
        return AC999.AESJDKEncode("obtainarticle//" + Content);
    }
    
    public String obtainarticleAgreement() {
        return AC999.AESJDKEncode("obtainarticle//");
    }
    
    public String drawnitemsAgreement(String Content) {
        return AC999.AESJDKEncode("drawnitems//" + Content);
    }
    
    public String drawnitemsAgreement() {
        return AC999.AESJDKEncode("drawnitems//");
    }
    
    public String drawnitemsfailAgreement(String Content) {
        return AC999.AESJDKEncode("drawnitemsfail//" + Content);
    }
    
    public String pankinglistAgreement(String Content) {
        return AC999.AESJDKEncode("pankinglist//" + Content);
    }
    
    public String nbuyAgreement(String Content) {
        return AC999.AESJDKEncode("buygood//" + Content);
    }
    
    public String userAgreement(String Content) {
        return AC999.AESJDKEncode("user//" + Content);
    }
    
    public String userpetAgreement(String Content) {
        return AC999.AESJDKEncode("userpet//" + Content);
    }
    
    public String usermountAgreement(String Content) {
        return AC999.AESJDKEncode("usermount//" + Content);
    }
    
    public String userlingAgreement(String Content) {
        return AC999.AESJDKEncode("userling//" + Content);
    }
    
    public String userbabyAgreement(String Content) {
        return AC999.AESJDKEncode("userbaby//" + Content);
    }
    
    public String usercardAgreement(String Content) {
        return AC999.AESJDKEncode("usercard//" + Content);
    }
    
    public String userpalAgreement(String Content) {
        return AC999.AESJDKEncode("userpal//" + Content);
    }
    
    public String dropAgreement(String Content) {
        return AC999.AESJDKEncode("drop//" + Content);
    }
    
    public String packRecordAgreement(String Content) {
        return AC999.AESJDKEncode("1//" + Content);
    }
    
    public String enterGameAgreement(String Content) {
        return AC999.AESJDKEncode("enterGame//" + Content);
    }
    
    public String roleSystemAgreement(String Content) {
        return AC999.AESJDKEncode("roleSystem//" + Content);
    }
    
    public String tipAgreement(String Content) {
        return AC999.AESJDKEncode("tip//" + Content);
    }
    
    public String registrationAgreement() {
        return AC999.AESJDKEncode("registration//");
    }
    
    public String enterfiledAgreement() {
        return AC999.AESJDKEncode("enterfiled//");
    }
    
    public String rolePrivateAgreement(String Content) {
        return AC999.AESJDKEncode("rolePrivate//" + Content);
    }
    
    public String bookofchalgAgreement(String Content) {
        return AC999.AESJDKEncode("bookofchalg//" + Content);
    }
    
    public String refusechalgAgreement() {
        return AC999.AESJDKEncode("refusechalg//");
    }
    
    public static String getFiveMsgAgreement(String Content) {
        return AC999.AESJDKEncode("getfivemsg//" + Content);
    }
    
    public static String exchangeGoodsAgreement(String Content) {
        return AC999.AESJDKEncode("exchangegoods//" + Content);
    }
    
    public static String suitOperateAgreement(String Content) {
        return AC999.AESJDKEncode("suitoperate//" + Content);
    }
    
    public static String extrAttrOperAgreement(String Content) {
        return AC999.AESJDKEncode("extrattroper//" + Content);
    }
    
    public static String FightingendAgreement(String Content) {
        return AC999.AESJDKEncode("fig6//" + Content);
    }
    
    public static String FightingForeseeAgreement(String Content) {
        return AC999.AESJDKEncode("fig2//" + Content);
    }
    
    public static String FightingRoundDealAgreement(String Content) {
        return AC999.AESJDKEncode("fig5//" + Content);
    }
    
    public String HatchvalueAgreement(String Content) {
        return AC999.AESJDKEncode("hatchvalue//" + Content);
    }
    
    public String HatchaddAgreement(String Content) {
        return AC999.AESJDKEncode("hatchadd//" + Content);
    }
    
    public String GivegoodsAgreement(String Content) {
        return AC999.AESJDKEncode("givegoods//" + Content);
    }
    
    public String richMAgreement(String Content) {
        return AC999.AESJDKEncode("richm//" + Content);
    }
    
    public String searcahChatRoleIdAgreement(String Content) {
        return AC999.AESJDKEncode("searcahChatRoleId//" + Content);
    }
    
    public String searcahChatRoleNameAgreement(String Content) {
        return AC999.AESJDKEncode("searcahChatRoleName//" + Content);
    }
    
    public String searchChatRecordeAgreement(String Content) {
        return AC999.AESJDKEncode("searchChatRecorde//" + Content);
    }
    
    public String battleroundAgreement(String Content) {
        return "fig1//" + Content;
    }
    
    public static String FightingRoundEndAgreement(String Content) {
        return "fig4//" + Content;
    }
    
    public String battleStateAgreement(String Content) {
        return "fig7//" + Content;
    }
    
    public String moveAgreement(String Content) {
        return "move//" + Content;
    }
    
    public String searchGoodsAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch1//" + Content);
    }
    
    public String searchMyWaresAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch2//" + Content);
    }
    
    public String searchMyOrderAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch3//" + Content);
    }
    
    public String searchAppointAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch4//" + Content);
    }
    
    public String searchMyGoodsAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch5//" + Content);
    }
    
    public String searchNewsAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch6//" + Content);
    }
    
    public String searchCollectionQueryAgreement(String Content) {
        return AC999.AESJDKEncode("CBGSearch7//" + Content);
    }
    
    public String searchCollectionAgreement(String Content) {
        return AC999.AESJDKEncode("CBGCollect//" + Content);
    }
    
    public String searchDeleteNewsAgreement(String Content) {
        return AC999.AESJDKEncode("CBGMes//" + Content);
    }
    
    public String searchShelfGoodsAgreement(String Content) {
        return AC999.AESJDKEncode("CBGShelf//" + Content);
    }
    
    public String searchOperationGoodAgreement(String Content) {
        return AC999.AESJDKEncode("CBGGood//" + Content);
    }
    
    public String goodsBuyAgreement(String Content) {
        return AC999.AESJDKEncode("goodsBuy_inter//" + Content);
    }
    
    public String searchBuyAgreement(String Content) {
        return AC999.AESJDKEncode("CBGBuy//" + Content);
    }
    
    public String searchGoodsBackAgreement(String Content) {
        return AC999.AESJDKEncode("CBGBack//" + Content);
    }
    
    public String goodsBuyNowAgreement(String content) {
        return AC999.AESJDKEncode("goodsBuy_now//" + content);
    }
    
    public String TransStateAgreement(String Content) {
        return AC999.AESJDKEncode("TransState//" + Content);
    }
    
    public String TransGoodAgreement(String Content) {
        return AC999.AESJDKEncode("TransGood//" + Content);
    }
    
    public String MonitorAgreement(int type, String Content) {
        return AC999.AESJDKEncode("Monitor//" + type + Content);
    }
    
    public String duelBoradDataAgreement(String Content) {
        return AC999.AESJDKEncode("DUELBOARD//" + Content);
    }
    
    public String TaskNAgreement(String Content) {
        return AC999.AESJDKEncode("taskN//" + Content);
    }
    
    public String fightQlAgreement(String Content) {
        return AC999.AESJDKEncode("fig8//" + Content);
    }
    
    public String upRoleShowAgreement(String Content) {
        return AC999.AESJDKEncode("upRoleShow//" + Content);
    }
    
    public String PhoneBangAgreement(String Content) {
        return AC999.AESJDKEncode("PhoneBang//" + Content);
    }
    
    public String UnPhoneBangAgreement(String Content) {
        return AC999.AESJDKEncode("UnPhoneBang//" + Content);
    }
    
    public String PhoneNumberIsNoGetAgreement() {
        return AC999.AESJDKEncode("PhoneNumberIsNoGet//");
    }
    
    public String sceneAgreement(String Content) {
        return AC999.AESJDKEncode("scene//" + Content);
    }
    
    public String GetvipgradepackAgreement() {
        return AC999.AESJDKEncode("Getvipgradepack//");
    }
    
    public String VipgradesureAgreement(String Content) {
        return AC999.AESJDKEncode("Vipgradesure//" + Content);
    }
    
    public String PaydaygradepayAgreement() {
        return AC999.AESJDKEncode("Paydaygradepay//");
    }
    
    public String PaydaygradesureAgreement(String Content) {
        return AC999.AESJDKEncode("Paydaygradesure//" + Content);
    }
    
    public String DayforweekgradegetAgreement() {
        return AC999.AESJDKEncode("Dayforweekgradeget//");
    }
    
    public String DayforweekgradesureAgreement(String Content) {
        return AC999.AESJDKEncode("Dayforweekgradesure//" + Content);
    }
    
    public String PayLJgradesureAgreement(String Content) {
        return AC999.AESJDKEncode("PayLJgradesure//" + Content);
    }
    
    public String DayforonegetAgreement() {
        return AC999.AESJDKEncode("Dayforoneget//");
    }
    
    public String LimitedTimeLshopAgreement() {
        return AC999.AESJDKEncode("LimitedTimeShop//");
    }
    
    public String DayforonesureAgreement() {
        return AC999.AESJDKEncode("Dayforonesure//");
    }
    
    public String chongjipackgetAgreement(String Content) {
        return AC999.AESJDKEncode("chongjipackget//" + Content);
    }
    
    public String ChongjipacksureAgreement(String Content) {
        return AC999.AESJDKEncode("Chongjipacksure//" + Content);
    }
    
    public String ChongjipackopenAgreement(String Content) {
        return AC999.AESJDKEncode("Chongjipackopen//" + Content);
    }
    
    public String PhoneNumberAgreement(String Content) {
        return AC999.AESJDKEncode("PhoneNumber//" + Content);
    }
    
    public String PhoneNumberReturnAgreement(String Content) {
        return AC999.AESJDKEncode("PhoneNumberReturn//" + Content);
    }
    
    public String uidAndQidForRoleAgreement(String Content) {
        return AC999.AESJDKEncode("UidAndQidForRole//" + Content);
    }
    
    public String getareaAgreement(String Content) {
        return AC999.AESJDKEncode("GETAREA//" + Content);
    }
    
    public String returnAgreement(String Content) {
        return AC999.AESJDKEncode("RETAREA//" + Content);
    }
    
    public String addRoleShopCartAgreement(String content) {
        return AC999.AESJDKEncode("addRoleShopCart//" + content);
    }
    
    public String getRoleShopCartAgreement(String content) {
        return AC999.AESJDKEncode("getRoleShopCart//" + content);
    }
    
    public String delRoleShopCartAgreement(String content) {
        return AC999.AESJDKEncode("delRoleShopCart//" + content);
    }
    
    public String buyRoleShopCartAgreement(String content) {
        return AC999.AESJDKEncode("buyRoleShopCart//" + content);
    }
    
    public String getRoleShopCartnumber(String content) {
        return AC999.AESJDKEncode("getRoleShopCartnumber//" + content);
    }
    
    public String enlistAgreement(String Content) {
        return AC999.AESJDKEncode("enlist//" + Content);
    }
    
    public String team1Agreement(String Content) {
        return AC999.AESJDKEncode("team1//" + Content);
    }
    
    public String team2Agreement(String Content) {
        return AC999.AESJDKEncode("team2//" + Content);
    }
    
    public String team3Agreement(String Content) {
        return AC999.AESJDKEncode("team3//" + Content);
    }
    
    public String team4Agreement(String Content) {
        return AC999.AESJDKEncode("team4//" + Content);
    }
    
    public String team5Agreement(String Content) {
        return AC999.AESJDKEncode("team5//" + Content);
    }
    
    public String team6Agreement(String Content) {
        return AC999.AESJDKEncode("team6//" + Content);
    }
    
    public String findWayAgreement(String Content) {
        return AC999.AESJDKEncode("findway//" + Content);
    }
    
    public String confirmAgreement(String Content) {
        return AC999.AESJDKEncode("confirm//" + Content);
    }
    
    public String oneArenaAgreement(String Content) {
        return AC999.AESJDKEncode("oneArena//" + Content);
    }
    
    public String teamArenaAgreement(String Content) {
        return AC999.AESJDKEncode("teamArena//" + Content);
    }
    
    public String Account_BindingAgreement(String Content) {
        return AC999.AESJDKEncode("ACCOUNT_BINDING//" + Content);
    }
    
    public String Account_GologinAgreement(String Content) {
        return AC999.AESJDKEncode("ACCOUNT_GOLOGIN//" + Content);
    }
    
    public String Account_LoginAgreement(String Content) {
        return AC999.AESJDKEncode("ACCOUNT_LOGIN//" + Content);
    }
    
    public String Account_RegisterAgreement(String Content) {
        return AC999.AESJDKEncode("ACCOUNT_REGISTER//" + Content);
    }
    
    public String AreaStatuesAgreement(String content) {
        return AC999.AESJDKEncode("AREASTATUES//" + content);
    }
    
    public String viconAgreement(String Content) {
        return AC999.AESJDKEncode("vicon//" + Content);
    }
    public String TournamentsAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("tournamentsData//" + Content);
    }
    public String laborAgreement(String Content) {
        return AC999.AESJDKEncode("labor//" + Content);
    }
    
    public String hjslAgreement(String Content) {
        return AC999.AESJDKEncode("hjsl//" + Content);
    }
    
    public String findDropAgreement(String Content) {
        return AC999.AESJDKEncode("findDrop//" + Content);
    }
    
    public String getEggAgreement(String Content) {
        return AC999.AESJDKEncode("getEgg//" + Content);
    }
    
    public String LingXiAgreement(String Content) {
        return AC999.AESJDKEncode("lingxi//" + Content);
    }
    
    public String APPQIANDAOAgreement(String Content) {
        return AC999.AESJDKEncode("APPQIANDAO//" + Content);
    }
    
    public String GOODSFORGOODSAPPLYAgreement(String Content) {
        return AC999.AESJDKEncode("GOODSFORGOODSAPPLY//" + Content);
    }
    
    public String goodforgoodstAgreement(String Content) {
        return AC999.AESJDKEncode("GOODSFORGOODS//" + Content);
    }
    
    public String useraircraftAgreement(String Content) {
        return AC999.AESJDKEncode("useraircraft//" + Content);
    }
    
    public String skillfmlearnAgreement(String Content) {
        return AC999.AESJDKEncode("skilllearn2//" + Content);
    }
    
    public String qdAgreement(String Content) {
        return AC999.AESJDKEncode("qd//" + Content);
    }
    
    public String skillxplearnAgreement(String Content) {
        return AC999.AESJDKEncode("rolechange1//" + Content);
    }
    
    public String GMshopItemAgreement(String Content) {
        return AC999.AESJDKEncode("GMSHOP//" + Content);
    }
    
    public String LotteryCPAgreement(String Content) {
        return AC999.AESJDKEncode("QMJC//" + Content);
    }
    
    public String laddArenaAgreement(String Content) {
        return AC999.AESJDKEncode("laddArena//" + Content);
    }
    
    public String expaddAgreement(String Content) {
        return AC999.AESJDKEncode("expAdd//" + Content);
    }
    
    public static String updateXianyu(String Content) {
        return AC999.AESJDKEncode("updateXianyu//" + Content);
    }
    
    public String getTitleAgreement() {
        return AC999.AESJDKEncode("gettitlelist//");
    }
    
    public String sellxianyuAgreement(String Content) {
        return AC999.AESJDKEncode("sellxianyu//" + Content);
    }
    
    public String shaoxiangAgreement(String Content) {
        return AC999.AESJDKEncode("shaoxiang//" + Content);
    }
    
    public String setLiangHaoType(String Content) {
        return AC999.AESJDKEncode("setlianghao//" + Content);
    }
    
    public String getLotteryGoods(String Content) {
        return AC999.AESJDKEncode("lottery//" + Content);
    }
    
    public String selllianghaoAgreement(String Content) {
        return AC999.AESJDKEncode("selllianghao//" + Content);
    }
    
    public String HDXCPAgreement(String Content) {
        return AC999.AESJDKEncode("HDX//" + Content);
    }
    
    public String JieGuaAgreement(String Content) {
        return AC999.AESJDKEncode("GUA//" + Content);
    }
    
    public String autoTaskAgreement(String Content) {
        return AC999.AESJDKEncode("AutoTask//" + Content);
    }
    
    public String shouhuAgreement(String Content) {
        return AC999.AESJDKEncode("shouhuPMxy//" + Content);
    }
    
    public String getZxpackAgreement(String Content) {
        return AC999.AESJDKEncode("zxpack//" + Content);
    }
    
    public String QzCUNQUAgreement(String Content) {
        return AC999.AESJDKEncode("QzCUNQU//" + Content);
    }

    public String roleAchieveAgreement(String Content) {
        return AC999.AESJDKEncode(AgreementUtil.ROLEACHIEVE + FG + Content);
    }
    public String fubenAgreement(String Content) {
        return AC999.AESJDKEncode(AgreementUtil.fuben + FG + Content);
    }
    public String roledaydrawAgreement(String Content) {
        return AC999.AESJDKEncode(AgreementUtil.ROLEDAYDRAW + FG + Content);
    }
    public String roleZXLBAgreement(String Content) {
        return AC999.AESJDKEncode(AgreementUtil.ZXDOOD + FG + Content);
    }
}
