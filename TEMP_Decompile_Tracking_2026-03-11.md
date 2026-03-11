# Temporary Decompile Tracking (2026-03-11)

This file is the temporary working ledger for point-to-point decompile restoration. Keep project structure unchanged, fix both ends together, add comments in the touched files, and sync Git after each batch.

## Rules

- Keep project structure unchanged in this phase.
- Fix paired server/client modules point-to-point whenever both sides exist.
- Do not treat normal pinyin or pinyin initials as decompile evidence.
- Keep comments in touched files at or above the required maintenance level (target 75%+).
- After each batch, update this file and commit only that batch plus this file.

## Summary

| Metric | Server | Client | Total |
|---|---:|---:|---:|
| Java files | 1439 | 1858 | 3297 |
| Strong files | 67 | 10 | 77 |
| Medium files | 14 | 58 | 72 |
| FernFlower files | 0 | 0 | 0 |
| Files with typeN defs | 2 | 0 | 2 |
| Files with historical names | 20 | 1 | 21 |
| Files with p-signatures | 45 | 9 | 54 |

## Completed This Session

| Status | Module or File | Notes |
|---|---|---|
| done | `Skill` | Renamed the internal `p1-p7` storage on both ends, kept `getP*/setP*` compatibility methods, removed the unused server-side `getPetSkillswl()` legacy getter, and switched remaining server-side relation reads to `getSkillRelation()`. |
| done | `Baby` | Removed the unused legacy compatibility methods on both ends and kept only the semantic methods for equip slots and talents. |
| done | `RoleSummoning` | Switched server-side four-attribute reads to `getFourAttributeValue`, removed unused legacy getters on both ends, and dropped the unused client-side legacy merge/equip wrappers. |
| done | `RoleTableMapper` | Removed the dead commented-out batch delete declarations from the mapper interface and cleared the matching dead SQL comment block from `RoleTableMapper.xml`. |
| done | `UsePetAction / DrawnitemsAction / Consumptions` | Added semantic record-merge aliases, replaced the 52 `DrawnitemsAction.Splice(...)` calls in `UsePetAction`, and removed the last client-side `Consumptions.Splice(...)` helper. |
| done | `RoleChangeAction` | Switched all remaining score/four-attribute merge calls from `DrawnitemsAction.Splice(...)` to `DrawnitemsAction.mergeRecordEntry(...)`. |
| done | `SuitComposeAction` | Renamed all internal `typeN` handlers to `handleOperationX` and added a dispatch comment so the protocol-number mapping remains readable without keeping the decompile-style names. |
| done | `Client FernFlower headers` | Removed the remaining four FernFlower header blocks and replaced them with class-level behavior comments in the affected UI files. |
| done | `FightingSkill` | Renamed the internal `p1-p7` storage to semantic formula-parameter fields while preserving the external `getP*/setP*` API used by battle calculations. |
| done | `NpcCompose` | Renamed the empty `type71-81` NPC-composition handlers to `handleOperation71-81` and documented the protocol-number dispatch entry. |
| done | `DropUtil` | Replaced all remaining `DrawnitemsAction.Splice(...)` calls with `mergeRecordEntry(...)` and renamed the anonymous comparator parameters from `p1/p2` to semantic names. |
| done | `StarCard` | Renamed the internal `type51-59` handlers to `handleOperation51-59` and updated the dispatch call sites in `SuitComposeAction`. |
| done | `SuitPetEquip` | Renamed the internal `type41-46` handlers to `handleOperation41-46`, switched the remaining score updates to `mergeRecordEntry(...)`, and updated the dispatch call sites in `SuitComposeAction`. |
| done | `WingCompose` | Renamed the internal `type31-36/355` handlers to `handleOperation31-36/355` and aligned the dispatch call sites in `SuitComposeAction`. |
| done | `GemCompose` | Renamed the internal `type17-21` handlers to `handleOperation17-21`, updated the dispatch call sites in `SuitComposeAction`, and replaced the residual `p2` loop variable with a semantic name. |
| done | `GameClient/src/com/tool/btn/OptionUncheckBtn.java` | Repaired the corrupted fund labels so they match the caller values again. |

## Point-to-Point Priority

| Status | Priority | Module | Server | Client | Notes |
|---|---|---|---|---|---|

## Server Strong

| Status | Priority | Score | File | Evidence |
|---|---|---:|---|---|
| pending | P0 | 44 | `Gameserver/src/main/java/come/tool/Calculation/CalculationUtil.java` | historical names x11 |
| pending | P0 | 35 | `Gameserver/src/main/java/org/come/agent/AgentService.java` | p-signatures x7; placeholders x7 |
| pending | P0 | 35 | `Gameserver/src/main/java/org/come/mapper/AgentMapper.java` | p-signatures x7; placeholders x7 |
| pending | P0 | 32 | `Gameserver/src/main/java/com/gl/controller/AdminPetController.java` | historical names x8 |
| pending | P0 | 32 | `Gameserver/src/main/java/org/come/service/IAutoTaskService.java` | p-signatures x6; placeholders x8 |
| pending | P0 | 29 | `Gameserver/src/main/java/come/tool/Good/UseRoleAction.java` | historical names x3; p-signatures x2; placeholders x9 |
| pending | P1 | 27 | `Gameserver/src/main/java/org/come/server/GolemServer.java` | p-signatures x4; placeholders x11 |
| pending | P1 | 27 | `Gameserver/src/main/java/org/come/service/IShaoXiangService.java` | p-signatures x5; placeholders x7 |
| pending | P1 | 26 | `Gameserver/src/main/java/come/tool/FightingData/Calculation.java` | placeholders x26 |
| pending | P1 | 24 | `Gameserver/src/main/java/org/come/action/suit/GemIntensify.java` | typeN defs x4 |
| pending | P1 | 21 | `Gameserver/src/main/java/org/come/service/ChongjipackServeice.java` | p-signatures x4; placeholders x5 |
| pending | P1 | 20 | `Gameserver/src/main/java/org/come/mapper/GangMapper.java` | p-signatures x4; placeholders x4 |
| pending | P1 | 20 | `Gameserver/src/main/java/org/come/service/IDiceService.java` | p-signatures x4; placeholders x4 |
| pending | P1 | 20 | `Gameserver/src/main/java/org/come/service/IGangService.java` | p-signatures x4; placeholders x4 |
| pending | P1 | 20 | `Gameserver/src/main/java/org/come/service/PayvipBeanServer.java` | p-signatures x4; placeholders x4 |
| pending | P1 | 17 | `Gameserver/src/main/java/org/come/mapper/TitletableMapper.java` | p-signatures x3; placeholders x5 |
| pending | P1 | 17 | `Gameserver/src/main/java/org/come/service/ITitletableService.java` | p-signatures x3; placeholders x5 |
| pending | P1 | 16 | `Gameserver/src/main/java/org/come/action/buy/BuyShopAction.java` | historical names x2; p-signatures x1; placeholders x4 |
| pending | P1 | 16 | `Gameserver/src/main/java/org/come/action/gl/LxAction.java` | p-signatures x1; placeholders x12 |
| pending | P1 | 16 | `Gameserver/src/main/java/org/come/mapper/ChongjipackMapper.java` | p-signatures x3; placeholders x4 |
| pending | P2 | 16 | `Gameserver/src/main/java/org/come/service/OneArenaNotesService.java` | p-signatures x3; placeholders x4 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/mapper/BuyCountMapper.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/mapper/GangBattleMapper.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/mapper/IpaddressmacMapper.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/service/BuyCountServeice.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/service/GangBattleService.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/service/IpaddressmacService.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/service/LimitedTimeLshopService.java` | p-signatures x2; placeholders x7 |
| pending | P2 | 15 | `Gameserver/src/main/java/org/come/thread/DataBaseManage.java` | p-signatures x3; placeholders x3 |
| pending | P2 | 12 | `Gameserver/src/main/java/come/tool/FightingData/ManData.java` | historical names x1; placeholders x8 |
| pending | P2 | 12 | `Gameserver/src/main/java/org/come/action/reward/DrawnitemsAction.java` | historical names x3 |
| pending | P2 | 12 | `Gameserver/src/main/java/org/come/action/suit/SuitPalEquip.java` | typeN defs x2 |
| pending | P2 | 12 | `Gameserver/src/main/java/org/come/service/RegionService.java` | p-signatures x2; placeholders x4 |
| pending | P2 | 12 | `Gameserver/src/main/java/org/come/servlet/SaveGameDataServlet.java` | placeholders x12 |
| pending | P2 | 12 | `Gameserver/src/main/java/org/come/until/AchievemUtil.java` | historical names x3 |
| pending | P2 | 11 | `Gameserver/src/main/java/org/come/action/npc/NpcComposeAction.java` | p-signatures x2; placeholders x3 |
| pending | P2 | 11 | `Gameserver/src/main/java/org/come/mapper/PayvipBeanServerMapper.java` | p-signatures x2; placeholders x3 |
| pending | P2 | 11 | `Gameserver/src/main/java/org/come/service/MeridiansService.java` | p-signatures x2; placeholders x3 |
| pending | P2 | 11 | `Gameserver/src/main/java/org/come/service/RecordService.java` | p-signatures x2; placeholders x3 |
| pending | P2 | 11 | `Gameserver/src/main/java/org/come/service/selectRecordByType.java` | p-signatures x2; placeholders x3 |
| pending | P2 | 10 | `Gameserver/src/main/java/come/tool/FightingData/GxgfInterface.java` | p-signatures x1; placeholders x6 |
| pending | P2 | 10 | `Gameserver/src/main/java/org/come/service/GoodsRoleUsertService.java` | p-signatures x2; placeholders x2 |
| pending | P2 | 9 | `Gameserver/src/main/java/org/come/action/summoning/SummonPetAction.java` | p-signatures x1; placeholders x5 |
| pending | P2 | 8 | `Gameserver/src/main/java/com/gl/service/GoodsService.java` | p-signatures x1; placeholders x4 |
| pending | P2 | 8 | `Gameserver/src/main/java/com/gl/service/PlayerService.java` | historical names x2 |
| pending | P2 | 8 | `Gameserver/src/main/java/com/gl/util/GLUtil.java` | historical names x2 |
| pending | P2 | 8 | `Gameserver/src/main/java/come/tool/FightingDataAction/DataAction.java` | p-signatures x1; placeholders x4 |
| pending | P2 | 8 | `Gameserver/src/main/java/come/tool/FightingLingAction/LingAction.java` | p-signatures x1; placeholders x4 |
| pending | P2 | 8 | `Gameserver/src/main/java/come/tool/FightingSpellAction/SpellAction.java` | p-signatures x1; placeholders x4 |
| pending | P2 | 8 | `Gameserver/src/main/java/org/come/action/lottery/DrawUtil.java` | historical names x2 |
| pending | P2 | 8 | `Gameserver/src/main/java/org/come/action/lottery/LotteryAction.java` | historical names x2 |
| pending | P2 | 8 | `Gameserver/src/main/java/org/come/action/suit/StarCard.java` | historical names x2 |
| pending | P2 | 7 | `Gameserver/src/main/java/org/come/mapper/RecordMapper.java` | p-signatures x1; placeholders x3 |
| pending | P2 | 6 | `Gameserver/src/main/java/come/tool/FightingDataAction/Petdll.java` | p-signatures x1; placeholders x2 |
| pending | P2 | 6 | `Gameserver/src/main/java/come/tool/FightingDataAction/Yao.java` | p-signatures x1; placeholders x2 |
| pending | P2 | 6 | `Gameserver/src/main/java/org/come/action/IAction.java` | p-signatures x1; placeholders x2 |
| pending | P2 | 5 | `Gameserver/src/main/java/come/tool/Battle/BattleState.java` | p-signatures x1; placeholders x1 |
| pending | P2 | 5 | `Gameserver/src/main/java/org/come/mapper/TtModelMapper.java` | p-signatures x1; placeholders x1 |
| pending | P2 | 5 | `Gameserver/src/main/java/org/come/service/TtModelService.java` | p-signatures x1; placeholders x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/come/tool/BangBattle/BangFight.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/come/tool/Scene/CJ/CJScene.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/come/tool/Scene/JieGuaScene.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/come/tool/Scene/SLDH/SLDHScene.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/org/come/action/role/RoleAchievemAction.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/org/come/action/role/RoleDayDrawAction.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/org/come/entity/RolesummoningRoleUser.java` | historical names x1 |
| pending | P2 | 4 | `Gameserver/src/main/java/org/come/task/RefreshMonsterTask.java` | historical names x1 |

## Client Strong

| Status | Priority | Score | File | Evidence |
|---|---|---:|---|---|
| pending | P0 | 13 | `GameClient/src/com/tool/tab/Main.java` | p-signatures x2; placeholders x5 |
| pending | P0 | 12 | `GameClient/src/com/tool/btn/PetOperationPanelBtn.java` | p-signatures x2; placeholders x4 |
| pending | P0 | 12 | `GameClient/src/org/come/Jpanel/BoothBoxJpanel.java` | p-signatures x2; placeholders x4 |
| pending | P0 | 8 | `GameClient/src/org/come/until/UserData.java` | historical names x2 |
| pending | P0 | 6 | `GameClient/src/come/tool/FightingEffect/Effect.java` | p-signatures x1; placeholders x2 |
| pending | P0 | 6 | `GameClient/src/come/tool/JDialog/TiShiChuLi.java` | p-signatures x1; placeholders x2 |
| pending | P1 | 5 | `GameClient/src/com/tool/btn/BtnInterface.java` | p-signatures x1; placeholders x1 |
| pending | P1 | 5 | `GameClient/src/com/tool/btn/MoBanBtn.java` | p-signatures x1; placeholders x1 |
| pending | P1 | 5 | `GameClient/src/come/tool/handle/Handle.java` | p-signatures x1; placeholders x1 |
| pending | P1 | 5 | `GameClient/src/org/come/action/NpcMenuAction.java` | p-signatures x1; placeholders x1 |

## Server Medium

| Status | Score | File | Evidence |
|---|---:|---|---|
| pending | 8 | `Gameserver/src/main/java/come/tool/FightingSpellAction/ZSAction.java` | placeholders x8 |
| pending | 8 | `Gameserver/src/main/java/org/come/servlet/UserInfoShowServlet.java` | placeholders x8 |
| pending | 6 | `Gameserver/src/main/java/come/tool/FightingData/Battlefield.java` | placeholders x6 |
| pending | 6 | `Gameserver/src/main/java/org/come/pay/check/CheckCounterfeit.java` | placeholders x6 |
| pending | 5 | `Gameserver/src/main/java/org/come/pay/ModifyInviteCodeServlet.java` | placeholders x5 |
| pending | 5 | `Gameserver/src/main/java/org/come/pay/ModifyInviteNameServlet.java` | placeholders x5 |
| pending | 5 | `Gameserver/src/main/java/org/come/pay/ModifyUserPwdServlet.java` | placeholders x5 |
| pending | 4 | `Gameserver/src/main/java/org/come/mapper/OneArenaNotesMapper.java` | placeholders x4 |
| pending | 4 | `Gameserver/src/main/java/org/come/mapper/RegionMapper.java` | placeholders x4 |
| pending | 4 | `Gameserver/src/main/java/org/come/server/GolemScript.java` | placeholders x4 |
| pending | 4 | `Gameserver/src/main/java/org/come/servlet/SaveDBServlet.java` | placeholders x4 |
| pending | 2 | `Gameserver/src/main/java/org/come/mapper/GoodsRoleUsertMapper.java` | placeholders x2 |
| pending | 2 | `Gameserver/src/main/java/org/come/pay/CatAllUserRoleServlet.java` | placeholders x2 |
| pending | 2 | `Gameserver/src/main/java/org/come/readUtil/ReadXuanBaoUtil.java` | placeholders x2 |

## Client Medium

| Status | Score | File | Evidence |
|---|---:|---|---|
| pending | 8 | `GameClient/src/come/tool/map/MapDecoder.java` | placeholders x8 |
| pending | 7 | `GameClient/src/com/tool/imagemonitor/PlayerMonitor.java` | placeholders x7 |
| pending | 6 | `GameClient/src/org/come/Jpanel/GameJpanel.java` | placeholders x6 |
| pending | 6 | `GameClient/src/org/come/annex/Tournaments/Other/TournamentsData.java` | placeholders x6 |
| pending | 6 | `GameClient/src/org/come/log/AegisterView.java` | placeholders x6 |
| pending | 5 | `GameClient/src/org/come/annex/Tournaments/Jpanel/TournamentsScreen4Jpanel.java` | placeholders x5 |
| pending | 4 | `GameClient/src/com/tool/Document/NumberDocument.java` | placeholders x4 |
| pending | 4 | `GameClient/src/com/tool/btn/CommonBtn.java` | placeholders x4 |
| pending | 4 | `GameClient/src/com/tool/btn/TeamPanelBtn.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/Jpanel/ZhuJpanel.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/mouslisten/TakeOffEquipmentMouslisten.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/ActiveSrcollPanelUI.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/NewAESForServerUtil.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/ScrollUI.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/ScrollUIS.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/SrcollPanePHlUI.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/SrcollPaneTJlUI.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/SrcollPaneXYDJlUI.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/SrcollPanelUI.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/WebTimeUntil.java` | placeholders x4 |
| pending | 4 | `GameClient/src/org/come/until/YASUO.java` | placeholders x4 |
| pending | 3 | `GameClient/src/org/come/login/LoginFrame.java` | placeholders x3 |
| pending | 3 | `GameClient/src/org/come/login/LoginJpanel.java` | placeholders x3 |
| pending | 3 | `GameClient/src/org/come/socket/DownLoadTxt.java` | placeholders x3 |
| pending | 3 | `GameClient/src/org/come/until/AESUtil.java` | placeholders x3 |
| pending | 2 | `GameClient/src/com/tool/Document/RichDocument.java` | placeholders x2 |
| pending | 2 | `GameClient/src/com/tool/btn/AircraftBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/com/tool/btn/LotteryBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/com/tool/btn/PartnerBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/com/tool/btn/TrueFeedbackBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/com/tool/btn/WorkshopRefiningBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/com/tool/tcp/FileRandom.java` | placeholders x2 |
| pending | 2 | `GameClient/src/come/tool/JDialog/SFCSJDialog.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/Frame/MsgJframe5.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/Jpanel/ChaojifeiListJpanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/Jpanel/PartnerArenaExchangeModelPanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/Jpanel/SeventyTwoChangesJpanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/Jpanel/WorldMapJpanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/XuanBao/XuanBaoAttributeJpanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/XuanBao/XuanBaoEquipmentJpanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/annex/Tournaments/Btn/TournamentsBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/annex/Tournaments/Btn/TournamentsNavBtn.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/annex/Tournaments/Jpanel/TournamentsScreen3Jpanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/control/NPCDialogControl.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/good/BabyGood.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/good/Lingbaogood.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/mouslisten/IncludedPartsMpuslisten.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/mouslisten/ShopingOnlineBuyBtnForXianYuMouslisten.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/npc/Creeps.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/npc/FlightChessTP.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/npc/GangInformation.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/npc/Gotochangan.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/until/AES.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/until/CreateTextUtil.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/come/until/Util.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/lottery/panel/LotteryMainPanel.java` | placeholders x2 |
| pending | 2 | `GameClient/src/org/wing/btn/WingBtn.java` | placeholders x2 |
| pending | 1 | `GameClient/src/com/tool/image/test/AbstractBufferedImageOp.java` | placeholders x1 |

## Git Sync Rule

1. Update this file after each finished batch.
2. `git add` only the changed code files plus this file.
3. Suggested commit message: `fix(decompile): module-or-file`
4. Push immediately after commit to avoid losing context.

## Current Suggested Order

1. Re-scan and choose the next highest-yield module from the updated strong list
2. Continue shrinking client medium files after strong files stabilize
