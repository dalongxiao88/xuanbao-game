package org.come.protocol;

import org.come.nettyClient.Clinet_NewAESUtil;
import org.come.tool.NewAESUtil_extInter;
import org.come.tool.NewAESUtil;

public class Agreement
{
    private static Agreement agreement;
    static final String FG = "//";
    static String FB;
    
    private Agreement() {
    }
    
    public static Agreement getAgreement() {
        if (Agreement.agreement == null) {
            Agreement.agreement = new Agreement();
        }
        return Agreement.agreement;
    }
    
    public String MonsterMatchAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("MonsterMatch//" + Content);
    }
    
    public String movedAgreement(String Content) {
        return "movd//" + Content + Agreement.FB;
    }
    
    public String successLoginAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("loginsuccess//" + Content);
    }
    
    public String checkPhoneAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.checkPhone + "//" + Content);
    }
    
    public String erroLoginAgreement(String content) {
        return NewAESUtil.AESJDKEncode("loginerror//" + content);
    }
    
    public String getVersionAgreement(String content) {
        return NewAESUtil.AESJDKEncode("getVersion_inter//" + content);
    }
    
    public static String FithtlingbaoAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("fightinglingbao//" + Content);
    }
    
    public String readtxtAgreement(String content) {
        return NewAESUtil.AESJDKEncode("readtxt_inter//" + content);
    }
    
    public static String FightingRoundInfoAgreement(String Content) {
        return "fig9//" + Content + Agreement.FB;
    }
    
    public String auctionGoods(String content) {
        return NewAESUtil.AESJDKEncode("auctionGoods//" + content);
    }
    
    public String erroLoginAgreement() {
        return NewAESUtil.AESJDKEncode("loginerror//");
    }
    
    public String inlineLoginAgreement() {
        return NewAESUtil.AESJDKEncode("inlinelogin//");
    }
    
    public String successRigisterAgreement() {
        return NewAESUtil.AESJDKEncode("rigistersuccess//");
    }
    
    public String errorCreateAgreement(String content) {
        return NewAESUtil.AESJDKEncode("createerror//" + content);
    }
    
    public String PhoneNumberAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("PhoneNumber//" + Content);
    }
    
    public String getareaAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("GETAREA//" + Content);
    }
    
    public String accRecharDetailAgreement(String content) {
        return NewAESUtil_extInter.AESJDKEncode("accRecharDetail_inter//" + content);
    }
    
    public String phoneVersionAgreement(String content) {
        return NewAESUtil.AESJDKEncode("phoneVersion//" + content);
    }
    
    public String successCreateAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("createsuccess//" + Content);
    }
    
    public String errorCreateAgreement() {
        return NewAESUtil.AESJDKEncode("createerror//");
    }
    
    public String TeamApplyExistAgreement() {
        return NewAESUtil.AESJDKEncode("teamexist//");
    }
    
    public String UserRetreatAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("userretreat//" + Content);
    }
    
    public String TransAllListAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("transalllist//" + Content);
    }
    
    public String PetInfoAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("petinfo//" + Content);
    }
    
    public String FlyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("fly//" + Content);
    }
    
    public String KejuAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("kejunpc//" + Content);
    }
    
    public String FlyCarryAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("flycarry//" + Content);
    }
    
    public String NpcMonsterAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("npcmonster//" + Content);
    }
    
    public String PetAlchemyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("petalchemy//" + Content);
    }
    
    public String stringTeamCaptain(String Content) {
        return NewAESUtil.AESJDKEncode("stringTeamCaptain//" + Content);
    }
    
    public String PongAgreement() {
        return NewAESUtil.AESJDKEncode("pong//");
    }
    
    public String RecivePawnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("retrieve//" + Content);
    }
    
    public String VersionAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("version//" + Content);
    }
    
    public String ZeropointAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("zeropoint//" + Content);
    }
    
    public String bangtzAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("bangtz//" + Content);
    }
    
    public String ChangeRoleNameAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("changerolename//" + Content);
    }
    
    public String RoleLevelUpAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("rolelevelup//" + Content);
    }
    
    public String LoginAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("login//" + Content);
    }
    
    public String sjLoginAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("sjlogin//" + Content);
    }
    
    public String registerAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("register//" + Content);
    }
    
    public String createRoleAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("createrole//" + Content);
    }
    
    public String intogameAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("intogame//" + Content);
    }
    
    public String chatAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("chat//" + Content);
    }
    
    public String npcAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("npc//" + Content);
    }
    
    public String packchangeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("packchange//" + Content);
    }
    
    public String EshopAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("eshop//" + Content);
    }
    
    public String MountAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("mount//" + Content);
    }
    public String CarAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("car//" + Content);
    }

    public String MountAgreement() {
        return NewAESUtil.AESJDKEncode("mount//");
    }
    
    public String groupApplyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("groupApply//" + Content);
    }
    
    public String groupaccessAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("groupaccess//" + Content);
    }
    
    public String friendAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("friend//" + Content);
    }
    
    public String addFrientAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("addfriend//" + Content);
    }
    
    public String friendchatAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("friendchat//" + Content);
    }
    
    public String BuyNPCGoodsAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("shop//" + Content);
    }
    
    public String BuyShopGoodsAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("shopGood//" + Content);
    }
    
    public String ShopPriceAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("shopPrice//" + Content);
    }
    
    public String ChangemapAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("changemap//" + Content);
    }
    
    public String ganglistAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("ganglist//" + Content);
    }
    
    public String giveAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("give//" + Content);
    }
    
    public String petAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("pet//" + Content);
    }
    
    public String petchangeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("petchange//" + Content);
    }
    
    public String packlockAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("packlock//" + Content);
    }
    
    public String packgiftAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("packgift//" + Content);
    }
    
    public String npcgiftAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("npcgift//" + Content);
    }
    
    public String skilllearnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("skilllearn//" + Content);
    }
    
    public String MonsterRefreshAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("monsterrefresh//" + Content);
    }
    
    public String CreepsFightAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("creepsfight//" + Content);
    }
    
    public String SummonpetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Summonpet//" + Content);
    }
    
    public String npccomposeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("npccompose//" + Content);
    }
    
    public String rolechangeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("rolechange//" + Content);
    }
    
    public String summonpetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("summonpet//" + Content);
    }
    
    public String TitleListAgreement() {
        return NewAESUtil.AESJDKEncode("titlelist//");
    }
    
    public String TitleListAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("titlelist//" + Content);
    }
    
    public String GetTitleListAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gettitlelist//" + Content);
    }
    
    public String IntogangAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("intogang//" + Content);
    }
    
    public String GangCreateAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangcreate//" + Content);
    }
    
    public String GangApplyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangapply//" + Content);
    }
    
    public static String GangRetreatAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangretreat//" + Content);
    }
    
    public static String GangShotAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangshot//" + Content);
    }
    
    public static String GangAgreeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangagree//" + Content);
    }
    
    public static String GangRefuseAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangrefuse//" + Content);
    }
    
    public static String GangAppointAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangappoint//" + Content);
    }
    
    public static String GangChangeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("gangchange//" + Content);
    }
    
    public static String GangChangeAgreementrest(String Content) {
        return NewAESUtil.AESJDKEncode("gangchangerest//" + Content);
    }
    
    public static String npccureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("npccure//" + Content);
    }
    
    public static String givemoneyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("givemoney//" + Content);
    }
    
    public static String marryAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("marry//" + Content);
    }
    
    public static String makeloveAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("makelove//" + Content);
    }
    
    public String PromptAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("prompt//" + Content);
    }
    
    public String TitleChangeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("titlechange//" + Content);
    }
    
    public String TitleExpireAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("titleexpirechange//" + Content);
    }
    
    public String MountCarryAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("mountcarry//" + Content);
    }
    
    public String PetReleaseAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("petrelease//" + Content);
    }
    
    public String BabyReleaseAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("babyrelease//" + Content);
    }
    
    public String BuyMingChaoAgreement() {
        return NewAESUtil.AESJDKEncode("buymingchao//");
    }
    
    public String RacialTransformationAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("racialtransformation//" + Content);
    }
    
    public String GetTheTaskAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("getthetask//" + Content);
    }
    
    public String GiveUpTheTaskAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("giveupthetask//" + Content);
    }
    
    public String delectFriend(String Content) {
        return NewAESUtil.AESJDKEncode("deletefriend//" + Content);
    }
    
    public String addMountSkill(String Content) {
        return NewAESUtil.AESJDKEncode("addmountskill//" + Content);
    }
    
    public String missMountSkill(String Content) {
        return NewAESUtil.AESJDKEncode("missmountskill//" + Content);
    }
    
    public String changeMountValue(String Content) {
        return NewAESUtil.AESJDKEncode("changemountvalue//" + Content);
    }
    
    public String mountGet(String Content) {
        return NewAESUtil.AESJDKEncode("mountget//" + Content);
    }
    
    public String getBaby(String Content) {
        return NewAESUtil.AESJDKEncode("baby//" + Content);
    }
    
    public String unMarry(String Content) {
        return NewAESUtil.AESJDKEncode("unmarry//" + Content);
    }
    
    public static String EquipmentLing(String Content) {
        return NewAESUtil.AESJDKEncode("equipmentLing//" + Content);
    }
    
    public static String Ling(String Content) {
        return NewAESUtil.AESJDKEncode("ling//" + Content);
    }
    
    public static String UpdateLing(String Content) {
        return NewAESUtil.AESJDKEncode("updateling//" + Content);
    }
    
    public static String UpdateFa(String Content) {
        return NewAESUtil.AESJDKEncode("updatefa//" + Content);
    }
    
    public static String Deductiontael(String Content) {
        return NewAESUtil.AESJDKEncode("deductiontael//" + Content);
    }
    
    public String laddArenaAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("laddArena//" + Content);
    }
    
    public String babyborn(String Content) {
        return NewAESUtil.AESJDKEncode("babyborn//" + Content);
    }
    
    public String applyPay(String Content) {
        return NewAESUtil.AESJDKEncode("applypay//" + Content);
    }
    
    public String BabyCustodayAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("babycustoday//" + Content);
    }
    
    public String pawnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("pawn//" + Content);
    }
    
    public String serverstopAgreement() {
        return NewAESUtil.AESJDKEncode("serverstop//");
    }
    
    public String battleConnectionAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("battleconnection//" + Content);
    }
    
    public String NPCDialogAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("npcdialog//" + Content);
    }
    
    public String MiddleAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("middle//" + Content);
    }
    
    public String QuoteOutAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("quoteout//" + Content);
    }
    
    public String AddGood(String Content) {
        return NewAESUtil.AESJDKEncode("addgood//" + Content);
    }
    
    public String updababy(String Content) {
        return NewAESUtil.AESJDKEncode("updababy//" + Content);
    }
    
    public String gangmonitor(String Content) {
        return NewAESUtil.AESJDKEncode("gangmonitor//" + Content);
    }
    
    public String gangbattle(String Content) {
        return NewAESUtil.AESJDKEncode("gangbattle//" + Content);
    }
    
    public String gangstate(String Content) {
        return NewAESUtil.AESJDKEncode("gangstate//" + Content);
    }
    
    public String stallAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("stall//" + Content);
    }
    
    public String stallgetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("stallget//" + Content);
    }
    
    public String stallbuyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("stallbuy//" + Content);
    }
    
    public String assetAgreement(String Content) {
        String str = NewAESUtil.AESJDKEncode("asset//" + Content);
        return str;
    }
    
    public String addTimeNpcAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("addTimeNpc//" + Content);
    }
    
    public String stallstateAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("stallstate//" + Content);
    }
    
    public String mountreleaseAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("mountrelease//" + Content);
    }
    
    public String updateMonstersAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("updatemonsters//" + Content);
    }
    
    public String clickMonstersAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("clickmonsters//" + Content);
    }
    
    public String throwinarticleAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("throwinarticle//" + Content);
    }
    
    public String obtainarticleAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("obtainarticle//" + Content);
    }
    
    public String obtainarticleAgreement() {
        return NewAESUtil.AESJDKEncode("obtainarticle//");
    }
    
    public String drawnitemsAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("drawnitems//" + Content);
    }
    
    public String drawnitemsAgreement() {
        return NewAESUtil.AESJDKEncode("drawnitems//");
    }
    
    public String drawnitemsfailAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("drawnitemsfail//" + Content);
    }
    
    public String bindingmobileAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("bindingmobile//" + Content);
    }
    
    public String pankinglistAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("pankinglist//" + Content);
    }
    
    public String nbuyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("buygood//" + Content);
    }
    
    public String userAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("user//" + Content);
    }
    
    public String userpetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("userpet//" + Content);
    }
    
    public String usermountAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("usermount//" + Content);
    }
    
    public String userlingAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("userling//" + Content);
    }
    
    public String userbabyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("userbaby//" + Content);
    }
    
    public String usercardAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("usercard//" + Content);
    }
    
    public String userpalAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("userpal//" + Content);
    }
    
    public String dropAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("drop//" + Content);
    }
    
    public String packRecordAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("1//" + Content);
    }
    
    public String enterGameAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("enterGame//" + Content);
    }
    
    public String roleSystemAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("roleSystem//" + Content);
    }
    
    public String registrationAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("registration//" + Content);
    }
    
    public String enterfiledAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("enterfiled//" + Content);
    }
    
    public String tipAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("tip//" + Content);
    }
    
    public String rolePrivateAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("rolePrivate//" + Content);
    }
    
    public String bookofchalgAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("bookofchalg//" + Content);
    }
    
    public String refusechalgAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("refusechalg//" + Content);
    }
    
    public String getfivemsgAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("getfivemsg//" + Content);
    }
    
    public static String FightingendAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("fig6//" + Content);
    }
    
    public static String FightingForeseeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("fig2//" + Content);
    }
    
    public static String FightingRoundDealAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("fig5//" + Content);
    }
    
    public static String ExtrattroperAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("extrattroper//" + Content);
    }
    
    public String HatchvalueAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("hatchvalue//" + Content);
    }
    
    public String HatchaddAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("hatchadd//" + Content);
    }
    
    public String GivegoodsAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("givegoods//" + Content);
    }
    
    public String bindingMobileAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("bindingMobile//" + Content);
    }
    
    public String richMAgreement(int type, String Content) {
        return NewAESUtil.AESJDKEncode("richm//" + type + Content);
    }
    
    public String searcahChatRoleIdAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("searcahChatRoleId//" + Content);
    }
    
    public String searcahChatRoleNameAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("searcahChatRoleName//" + Content);
    }
    
    public String searchChatRecordeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("searchChatRecorde//" + Content);
    }
    
    public String duelBoradDataAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("DUELBOARD//" + Content);
    }
    
    public String CBGSearch1Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch1//" + Content);
    }
    
    public String CBGSearch2Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch2//" + Content);
    }
    
    public String CBGSearch3Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch3//" + Content);
    }
    
    public String CBGSearch4Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch4//" + Content);
    }
    
    public String CBGSearch5Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch5//" + Content);
    }
    
    public String CBGSearch6Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch6//" + Content);
    }
    
    public String CBGSearch7Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGSearch7//" + Content);
    }
    
    public String CBGBuyAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGBuy//" + Content);
    }
    
    public String CBGBackAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("CBGBack//" + Content);
    }
    
    public String TransStateAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("TransState//" + Content);
    }
    
    public String TransGoodAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("TransGood//" + Content);
    }
    
    public String TaskNAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("taskN//" + Content);
    }
    
    public String fightQlAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("fig8//" + Content);
    }
    
    public String upRoleShowAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("upRoleShow//" + Content);
    }
    
    public static String battleroundAgreement(String Content) {
        return "fig1//" + Content + Agreement.FB;
    }
    
    public static String FightingRoundEndAgreement(String Content) {
        return "fig4//" + Content + Agreement.FB;
    }
    
    public String battleStateAgreement(String Content) {
        return "fig7//" + Content + Agreement.FB;
    }
    
    public String moveAgreement(String Content) {
        return "move//" + Content + Agreement.FB;
    }
    
    public String PhoneBangAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("PhoneBang//" + Content);
    }
    
    public String UnPhoneBangAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("UnPhoneBang//" + Content);
    }
    
    public String PhoneNumberIsNoGetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("PhoneNumberIsNoGet//" + Content);
    }
    
    public String GetvipgradepackAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Getvipgradepack//" + Content);
    }
    
    public String VipgradesureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Vipgradesure//" + Content);
    }
    
    public String PaydaygradepayAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Paydaygradepay//" + Content);
    }
    
    public String PaydaygradesureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Paydaygradesure//" + Content);
    }
    
    public String DayforweekgradegetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Dayforweekgradeget//" + Content);
    }
    
    public String DayforweekgradesureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Dayforweekgradesure//" + Content);
    }
    
    public String PayLJgradesureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("PayLJgradesure//" + Content);
    }
    
    public String DayforonegetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Dayforoneget//" + Content);
    }
    
    public String LimitedTimeLshopAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("LimitedTimeShop//" + Content);
    }
    
    public String DayforonesureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Dayforonesure//" + Content);
    }
    
    public String chongjipackgetAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("chongjipackget//" + Content);
    }
    
    public String ChongjipacksureAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Chongjipacksure//" + Content);
    }
    
    public String ChongjipackopenAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("Chongjipackopen//" + Content);
    }
    
    public String sceneAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("scene//" + Content);
    }
    
    public String enlistAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("enlist//" + Content);
    }
    
    public String team1Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("team1//" + Content);
    }
    
    public String team2Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("team2//" + Content);
    }
    
    public String team3Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("team3//" + Content);
    }
    
    public String team4Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("team4//" + Content);
    }
    
    public String team5Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("team5//" + Content);
    }
    
    public String team6Agreement(String Content) {
        return NewAESUtil.AESJDKEncode("team6//" + Content);
    }
    
    public String findWayAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("findway//" + Content);
    }
    
    public String confirmAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("confirm//" + Content);
    }
    
    public String oneArenaAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("oneArena//" + Content);
    }
    
    public String teamArenaAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("teamArena//" + Content);
    }
    
    public String returnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("RETAREA//" + Content);
    }
    
    public String uidAndQidForRoleAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("UidAndQidForRole//" + Content);
    }
    
    public String PhoneNumberReturnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("PhoneNumberReturn//" + Content);
    }
    
    public String goodsBuyAgreement(String content) {
        return NewAESUtil.AESJDKEncode("goodsBuy_inter//" + content);
    }
    
    public String salesGoodsChangeOrderAgreement(String content) {
        return NewAESUtil.AESJDKEncode("salesGoodsChangeOrder_inter//" + content);
    }
    
    public String saleGoodsStatuesAgreement(String content) {
        return NewAESUtil.AESJDKEncode("saleGoodsStatues_inter//" + content);
    }
    
    public String Account_BindingAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("ACCOUNT_BINDING//" + Content);
    }
    
    public String Account_UpdateAgreement(String Content) {
        return Clinet_NewAESUtil.AESJDKEncode("ACCOUNT_UPDATE//" + Content);
    }
    
    public String Account_RegisterAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("ACCOUNT_REGISTER//" + Content);
    }
    
    public String AreaStatuesAgreement(String content) {
        return NewAESUtil.AESJDKEncode("AREASTATUES//" + content);
    }
    
    public String Ip_ActionAgreement(String Content) {
        return Clinet_NewAESUtil.AESJDKEncode("IP_ACTION//" + Content);
    }
    
    public String LingXiAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("lingxi//" + Content);
    }
    
    public String skillfmlearnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("skilllearn2//" + Content);
    }
    
    public String QDAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("qd//" + Content);
    }
    
    public String skillxplearnAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("rolechange1//" + Content);
    }
    
    public String tiantiqianwuAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("tiantiqianwu//" + Content);
    }
    
    public String tiantigerenpaimingAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("tiantigerenpaiming//" + Content);
    }
    
    public String tiantichangshuAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("tiantichangshu//" + Content);
    }
    
    public String lingquAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("lingqu//" + Content);
    }
    
    public String tiantiAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("tiantipipei//" + Content);
    }
    
    public String goodsRecordQueryAgreement(String content) {
        return NewAESUtil.AESJDKEncode("goodsRecordQuery_inter//" + content);
    }
    
    public String shopBuyTypeAgreement(String content) {
        return NewAESUtil.AESJDKEncode("shopBuyType_inter//" + content);
    }
    
    public String shopBuyQueryAgreement(String content) {
        return NewAESUtil.AESJDKEncode("shopBuyQuery_inter//" + content);
    }
    
    public String usertableSafenumberAgreement(String content) {
        return NewAESUtil.AESJDKEncode("usertableSafenumber_inter//" + content);
    }
    
    public String userTableActionAgreement(String content) {
        return NewAESUtil.AESJDKEncode("userTableAction_inter//" + content);
    }
    
    public String userRoleChangeAgreement(String content) {
        return NewAESUtil.AESJDKEncode("userRoleChange_inter//" + content);
    }
    
    public String goodsRecordAgreement(String content) {
        return NewAESUtil.AESJDKEncode("goodsRecord_inter//" + content);
    }
    
    public String importantgoodtrcordGoodsAgreement(String content) {
        return NewAESUtil.AESJDKEncode("importantgoodtrcordGoods_inter//" + content);
    }
    
    public String GMshopItemAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("GMSHOP//" + Content);
    }
    
    public String LotteryCPAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("QMJC//" + Content);
    }
    
    public String roleAttributeAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("roleAttribute//" + Content);
    }
    
    public String expAddAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("expAdd//" + Content);
    }
    
    public String serverstopDeleteRoleAgreement() {
        return NewAESUtil.AESJDKEncode("serverstopDeleteRole//");
    }
    
    public String successDeleteRoleAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("deleteRole//" + Content);
    }
    
    public String JingCaiAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("jingcai//" + Content);
    }
    
    public String PlaygameAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.playgame + "//" + Content);
    }
    
    public String Account_LoginAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("ACCOUNT_LOGIN//" + Content);
    }
    
    public String erroRigisterAgreement(String content) {
        return NewAESUtil.AESJDKEncode("rigistererror//" + content);
    }
    
    public String getInlinePeopleAgreement(String content) {
        return NewAESUtil_extInter.AESJDKEncode("getInlinePeople_inter//" + content);
    }
    
    public String checkPhoneRetrunAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.checkPhoneRetrun + "//" + Content);
    }
    
    public String searchSellXianYuAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("sellxianyu//" + Content);
    }
    
    public String getShaoXiangLimitAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("shaoxiang//" + Content);
    }
    
    public String getAutoTaskAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("AutoTask//" + Content);
    }
    
    public String getLotteryAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("lottery//" + Content);
    }
    
    public String getZxpackAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("zxpack//" + Content);
    }
    
    public String searchSellLiangHao(String Content) {
        return NewAESUtil.AESJDKEncode("selllianghao//" + Content);
    }
    
    public String HDXCPAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("HDX//" + Content);
    }
    
    public String JieGuaAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("GUA//" + Content);
    }
    
    public String ShouHuAgreement(String Content) {
        return NewAESUtil.AESJDKEncode("shouhuPMxy//" + Content);
    }
    
    static {
        Agreement.FB = "\n";
    }
    public String roleAchieveAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.ROLEACHIEVE + FG + Content);
    }
    public String roledaydrawAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.ROLEDAYDRAW + FG + Content);
    }
    public String updateTxtAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.UPDATETEXT + FG + Content);
    }
    public String roleZXGOODAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.ZXDOOD + FG + Content);
    }
    public String XYDJLSAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.xydjls + FG + Content);
    }
    //51劳动节特别任务 排行数据
    public String laborAgreement(String Content) {
        return NewAESUtil.AESJDKEncode(AgreementUtil.labor + FG + Content);
    }
}
