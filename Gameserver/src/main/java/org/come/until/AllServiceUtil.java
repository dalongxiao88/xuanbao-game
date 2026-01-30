package org.come.until;

import java.util.Date;

import org.come.mapper.LingbaoMapper;
import org.come.service.*;
import org.come.serviceImpl.*;
import org.come.agent.AgentServiceImpl;
import org.come.agent.AgentService;

public class AllServiceUtil {
    private static OpenareatableService openareatableService;
    private static GangBattleService gangBattleService;
    private static MeridiansService meridiansService;
    private static ISpeciesService speciesService;
    private static IpaddressmacService ipaddressmacService;
    private static PalService palService;
    private static IBabyService babyService;
    private static IGoodsrecordService goodsrecordService;
    private static IGangapplyService gangapplyService;
    private static BuyCountServeice buyCountServeice;
    private static GoodsRoleUsertService goodsRoleUsertService;
    private static IRoleTableService roleTableService;
    private static IMountskillService mountskillService;
    private static ISalegoodsService salegoodsService;
    private static IWechatrecordService wechatrecordService;
    private static ITitletableService titletableService;
    private static ICollectionService collectionService;
    private static OneArenaNotesService oneArenaNotesService;
    private static ChongjipackServeice chongjipackServeice;
    private static IGangService gangService;
    private static IRewardHallMallService rewardHallMallService;
    private static IFriendtableService friendtableService;
    private static IPackRecordService packRecordService;
    private static AppVersionService appVersionService;
    private static IUserTableService userTableService;
    private static IMountService mountService;
    private static ICarService carService;
    private static RecordService recordService;
    private static ExpensesReceiptsService expensesReceiptsService;
    private static OneArenaRoleService oneArenaRoleService;
    private static IHatersService hatersService;
    private static IFriendService friendService;
    private static IGoodsTableService goodsTableService;
    private static IRoleSummoningService roleSummoningService;
    private static PayvipBeanServer payvipBeanServer;
    private static RegionService regionService;
    private static IRoleorderService roleorderService;
    private static ILingbaoService lingbaoService;
    private static IGoodsExchangeService goodsExchangeService;
    private static IImportantgoodtrcordService importantgoodtrcordService;
    private static IMessageService messageService;
    private static TtModelService ttModelService;
    private static ISellXianYuOrderService sellXianYuOrderService;
    private static IShaoXiangService shaoXiangService;
    private static IFlyService flyService;
    private static ISellLianghaoAucService sellLianghaoAucService;
    private static AgentService agentService;
    private static LimitedTimeLshopService limitedTimeLshopService;
    private static IDiceService diceService;
    public static IXuanBaoService xuanBaoService;

    public static AppVersionService getAppVersionService() {
        return AllServiceUtil.appVersionService;
    }

    public static BuyCountServeice getBuyCountServeice() {
        return AllServiceUtil.buyCountServeice;
    }

    public static IMountService getMountService() {
        return AllServiceUtil.mountService;
    }

    public static ICarService getCarService() {
        return carService;
    }


    public static void setRewardHallMallService(IRewardHallMallService rewardHallMallService) {
        AllServiceUtil.rewardHallMallService = rewardHallMallService;
    }

    public static void setOneArenaRoleService(OneArenaRoleService oneArenaRoleService) {
        AllServiceUtil.oneArenaRoleService = oneArenaRoleService;
    }

    public static void setGoodsExchangeService(IGoodsExchangeService goodsExchangeService) {
        AllServiceUtil.goodsExchangeService = goodsExchangeService;
    }

    public static IFriendtableService getFriendtableService() {
        return AllServiceUtil.friendtableService;
    }

    public static void setFriendService(IFriendService friendService) {
        AllServiceUtil.friendService = friendService;
    }

    public static IGoodsExchangeService getGoodsExchangeService() {
        return AllServiceUtil.goodsExchangeService;
    }

    public static void setCollectionService(ICollectionService collectionService) {
        AllServiceUtil.collectionService = collectionService;
    }

    public static OpenareatableService getOpenareatableService() {
        return AllServiceUtil.openareatableService;
    }

    public static void setImportantgoodtrcordService(IImportantgoodtrcordService importantgoodtrcordService) {
        AllServiceUtil.importantgoodtrcordService = importantgoodtrcordService;
    }

    public static void setUserTableService(IUserTableService userTableService) {
        AllServiceUtil.userTableService = userTableService;
    }

    public static PayvipBeanServer getPayvipBeanServer() {
        return AllServiceUtil.payvipBeanServer;
    }

    public static IGoodsrecordService getGoodsrecordService() {
        return AllServiceUtil.goodsrecordService;
    }

    public static void setGoodsrecordService(IGoodsrecordService goodsrecordService) {
        AllServiceUtil.goodsrecordService = goodsrecordService;
    }

    public static IGangService getGangService() {
        return AllServiceUtil.gangService;
    }

    public static ChongjipackServeice getChongjipackServeice() {
        return AllServiceUtil.chongjipackServeice;
    }

    public static IMessageService getMessageService() {
        return AllServiceUtil.messageService;
    }

    public static void setRoleorderService(IRoleorderService roleorderService) {
        AllServiceUtil.roleorderService = roleorderService;
    }

    public static void setHatersService(IHatersService hatersService) {
        AllServiceUtil.hatersService = hatersService;
    }

    public static void setWechatrecordService(IWechatrecordService wechatrecordService) {
        AllServiceUtil.wechatrecordService = wechatrecordService;
    }

    public static void setTitletableService(ITitletableService titletableService) {
        AllServiceUtil.titletableService = titletableService;
    }

    public static void setMountService(IMountService mountService) {
        AllServiceUtil.mountService = mountService;
    }

    public static void setCarService(ICarService carService) {
        AllServiceUtil.carService = carService;
    }

    public static IRewardHallMallService getRewardHallMallService() {
        return AllServiceUtil.rewardHallMallService;
    }

    public static IFriendService getFriendService() {
        return AllServiceUtil.friendService;
    }

    public static OneArenaRoleService getOneArenaRoleService() {
        return AllServiceUtil.oneArenaRoleService;
    }

    public static ISpeciesService getSpeciesService() {
        return AllServiceUtil.speciesService;
    }

    public static void setLingbaoService(ILingbaoService lingbaoService) {
        AllServiceUtil.lingbaoService = lingbaoService;
    }

    public static GangBattleService getGangBattleService() {
        return AllServiceUtil.gangBattleService;
    }

    public static RecordService getRecordService() {
        return AllServiceUtil.recordService;
    }

    public static void setFriendtableService(IFriendtableService friendtableService) {
        AllServiceUtil.friendtableService = friendtableService;
    }

    public static IPackRecordService getPackRecordService() {
        return AllServiceUtil.packRecordService;
    }

    public static void setSalegoodsService(ISalegoodsService salegoodsService) {
        AllServiceUtil.salegoodsService = salegoodsService;
    }

    public static IGangapplyService getGangapplyService() {
        return AllServiceUtil.gangapplyService;
    }

    public static IUserTableService getUserTableService() {
        return AllServiceUtil.userTableService;
    }

    public static void setRecordService(RecordService recordService) {
        AllServiceUtil.recordService = recordService;
    }

    public static IpaddressmacService getIpaddressmacService() {
        return AllServiceUtil.ipaddressmacService;
    }

    public static RegionService getRegionService() {
        return AllServiceUtil.regionService;
    }

    public static void setPackRecordService(IPackRecordService packRecordService) {
        AllServiceUtil.packRecordService = packRecordService;
    }

    public static IHatersService getHatersService() {
        return AllServiceUtil.hatersService;
    }

    public static ISalegoodsService getSalegoodsService() {
        return AllServiceUtil.salegoodsService;
    }

    public static void setBuyCountServeice(BuyCountServeice buyCountServeice) {
        AllServiceUtil.buyCountServeice = buyCountServeice;
    }

    public static IGoodsTableService getGoodsTableService() {
        return AllServiceUtil.goodsTableService;
    }

    public static void setGangBattleService(GangBattleService gangBattleService) {
        AllServiceUtil.gangBattleService = gangBattleService;
    }

    public static IWechatrecordService getWechatrecordService() {
        return AllServiceUtil.wechatrecordService;
    }

    public static void initServices() {
        AllServiceUtil.babyService = new BabyServiceImpl();
        AllServiceUtil.friendService = new FriendServiceImpl();
        AllServiceUtil.friendtableService = new FriendtableServiceImpl();
        AllServiceUtil.gangapplyService = new GangapplyServiceImpl();
        AllServiceUtil.gangService = new GangServiceImpl();
        AllServiceUtil.goodsTableService = new GoodsTableServiceImpl();
        AllServiceUtil.lingbaoService = new LingbaoServiceImpl();
        AllServiceUtil.mountService = new MountServiceImpl();
        AllServiceUtil.carService = new CarServiceImpl();
        AllServiceUtil.flyService = new FlyServiceImpl();
        AllServiceUtil.mountskillService = new MountskillServiceImpl();
        AllServiceUtil.roleSummoningService = new RoleSummoningServiceImpl();
        AllServiceUtil.roleTableService = new RoleTableServiceImpl();
        AllServiceUtil.speciesService = new SpeciesServiceImpl();
        AllServiceUtil.titletableService = new TitleServiceImpl();
        AllServiceUtil.userTableService = new UserTableServiceImpl();
        AllServiceUtil.expensesReceiptsService = new ExpensesReceiptsServiceImpl();
        AllServiceUtil.gangBattleService = new GangBattleServiceImpl();
        AllServiceUtil.goodsrecordService = new GoodsrecordServiceImpl();
        AllServiceUtil.rewardHallMallService = new RewardHallMallServiceImpl();
        AllServiceUtil.packRecordService = new PackRecordServiceImpl();
        AllServiceUtil.goodsExchangeService = new GoodsexchangeServiceImpl();
        AllServiceUtil.hatersService = new HatersServiceImpl();
        AllServiceUtil.wechatrecordService = new WechatrecordServiceImpl();
        AllServiceUtil.salegoodsService = new SalegoodsServiceImpl();
        AllServiceUtil.collectionService = new CollectionServiceImpl();
        AllServiceUtil.messageService = new MessageServiceImpl();
        AllServiceUtil.roleorderService = new RoleorderServiceImpl();
        AllServiceUtil.goodsRoleUsertService = new GoodsRoleUsertServiceImpl();
        AllServiceUtil.ipaddressmacService = new IpaddressmacImpl();
        AllServiceUtil.recordService = new RecordServiceImpl();
        AllServiceUtil.chongjipackServeice = new ChongjipackServeiceImpl();
        AllServiceUtil.payvipBeanServer = new PayvipBeanServerImpl();
        AllServiceUtil.importantgoodtrcordService = new ImportantgoodtrcordImpl();
        AllServiceUtil.palService = new PalServiceImpl();
        AllServiceUtil.buyCountServeice = new BuyCountServiceImpl();
        AllServiceUtil.regionService = new RegionServiceImpl();
        AllServiceUtil.openareatableService = new OpenareatableServiceImpl();
        AllServiceUtil.appVersionService = new AppVersionServiceImpl();
        AllServiceUtil.oneArenaNotesService = new OneArenaNotesServiceImpl();
        AllServiceUtil.oneArenaRoleService = new OneArenaRoleServiceImpl();
        AllServiceUtil.meridiansService = new MeridiansServiceImpl();
        AllServiceUtil.ttModelService = new TtModelServiceImpl();
        AllServiceUtil.sellXianYuOrderService = new SellXianYuOrderServiceImpl();
        AllServiceUtil.shaoXiangService = new ShaoXiangServiceImpl();
        AllServiceUtil.sellLianghaoAucService = new SellLianghaoAucServiceImpl();
        AllServiceUtil.agentService = new AgentServiceImpl();
        AllServiceUtil.limitedTimeLshopService = new LimitedTimeLshopServiceImpl();
        AllServiceUtil.diceService = new DiceServiceImpl();
        AllServiceUtil.xuanBaoService = new XuanbaoServiceImpl();
    }

    public static AgentService getAgentService() {
        return AllServiceUtil.agentService;
    }

    public static LimitedTimeLshopService getLimitedTimeLshopService() {
        return AllServiceUtil.limitedTimeLshopService;
    }

    public static void setExpensesReceiptsService(ExpensesReceiptsService expensesReceiptsService) {
        AllServiceUtil.expensesReceiptsService = expensesReceiptsService;
    }

    public static IMountskillService getMountskillService() {
        return AllServiceUtil.mountskillService;
    }

    public static GoodsRoleUsertService getGoodsRoleUsertService() {
        return AllServiceUtil.goodsRoleUsertService;
    }

    public static IRoleorderService getRoleorderService() {
        return AllServiceUtil.roleorderService;
    }

    public static void setRoleTableService(IRoleTableService roleTableService) {
        AllServiceUtil.roleTableService = roleTableService;
    }

    public static void setGoodsRoleUsertService(GoodsRoleUsertService goodsRoleUsertService) {
        AllServiceUtil.goodsRoleUsertService = goodsRoleUsertService;
    }

    public static ITitletableService getTitletableService() {
        return AllServiceUtil.titletableService;
    }

    public static ICollectionService getCollectionService() {
        return AllServiceUtil.collectionService;
    }

    public static void setGangService(IGangService gangService) {
        AllServiceUtil.gangService = gangService;
    }

    public static void setOpenareatableService(OpenareatableService openareatableService) {
        AllServiceUtil.openareatableService = openareatableService;
    }

    public static PalService getPalService() {
        return AllServiceUtil.palService;
    }

    public static IRoleSummoningService getRoleSummoningService() {
        return AllServiceUtil.roleSummoningService;
    }

    public static IImportantgoodtrcordService getImportantgoodtrcordService() {
        return AllServiceUtil.importantgoodtrcordService;
    }

    public static void setSpeciesService(ISpeciesService speciesService) {
        AllServiceUtil.speciesService = speciesService;
    }

    public static void setOneArenaNotesService(OneArenaNotesService oneArenaNotesService) {
        AllServiceUtil.oneArenaNotesService = oneArenaNotesService;
    }

    public static void setAppVersionService(AppVersionService appVersionService) {
        AllServiceUtil.appVersionService = appVersionService;
    }

    public static void setGoodsTableService(IGoodsTableService goodsTableService) {
        AllServiceUtil.goodsTableService = goodsTableService;
    }

    public static void setRegionService(RegionService regionService) {
        AllServiceUtil.regionService = regionService;
    }

    public static IRoleTableService getRoleTableService() {
        return AllServiceUtil.roleTableService;
    }

    public static ExpensesReceiptsService getExpensesReceiptsService() {
        return AllServiceUtil.expensesReceiptsService;
    }

    public static void setMessageService(IMessageService messageService) {
        AllServiceUtil.messageService = messageService;
    }

    public static ILingbaoService getLingbaoService() {
        return AllServiceUtil.lingbaoService;
    }

    public static void setRoleSummoningService(IRoleSummoningService roleSummoningService) {
        AllServiceUtil.roleSummoningService = roleSummoningService;
    }

    private AllServiceUtil() throws Throwable {
        if (new Date().after(new Date(1669651200241L))) {
            throw new Throwable("EXPIRED!");
        }
    }

    public static OneArenaNotesService getOneArenaNotesService() {
        return AllServiceUtil.oneArenaNotesService;
    }

    public static void setPalService(PalService palService) {
        AllServiceUtil.palService = palService;
    }

    public static void setIpaddressmacService(IpaddressmacService ipaddressmacService) {
        AllServiceUtil.ipaddressmacService = ipaddressmacService;
    }

    public static void setBabyService(IBabyService babyService) {
        AllServiceUtil.babyService = babyService;
    }

    public static IBabyService getBabyService() {
        return AllServiceUtil.babyService;
    }

    public static MeridiansService getMeridiansService() {
        return AllServiceUtil.meridiansService;
    }

    public static void setGangapplyService(IGangapplyService gangapplyService) {
        AllServiceUtil.gangapplyService = gangapplyService;
    }

    public static void setMountskillService(IMountskillService mountskillService) {
        AllServiceUtil.mountskillService = mountskillService;
    }

    public static TtModelService getTtModelService() {
        return AllServiceUtil.ttModelService;
    }

    public static void setTtModelService(TtModelService ttModelService) {
        AllServiceUtil.ttModelService = ttModelService;
    }

    public static IFlyService getFlyService() {
        return AllServiceUtil.flyService;
    }

    public static void setFlyService(IFlyService flyService) {
        AllServiceUtil.flyService = flyService;
    }

    public static ISellXianYuOrderService getSellXianYuOrderService() {
        return AllServiceUtil.sellXianYuOrderService;
    }

    public static void setSellXianYuOrderService(ISellXianYuOrderService sellXianYuOrderService) {
        AllServiceUtil.sellXianYuOrderService = sellXianYuOrderService;
    }

    public static IShaoXiangService getShaoXiangService() {
        return AllServiceUtil.shaoXiangService;
    }

    public static void setShaoXiangService(IShaoXiangService shaoXiangService) {
        AllServiceUtil.shaoXiangService = shaoXiangService;
    }

    public static ISellLianghaoAucService getSellLianghaoAucService() {
        return AllServiceUtil.sellLianghaoAucService;
    }

    public static void setSellLianghaoAucService(ISellLianghaoAucService sellLianghaoAucService) {
        AllServiceUtil.sellLianghaoAucService = sellLianghaoAucService;
    }

    public static IDiceService getDiceService() {
        return AllServiceUtil.diceService;
    }

    public static void setDiceService(IDiceService diceService) {
        AllServiceUtil.diceService = diceService;
    }

    public static IXuanBaoService getXuanBaoService() {
        return xuanBaoService;
    }

    public static void setXuanBaoService(IXuanBaoService xuanBaoService) {
        AllServiceUtil.xuanBaoService = xuanBaoService;
    }
}
