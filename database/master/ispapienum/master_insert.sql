﻿-- api_id毎のサービス
-- 2000 〜 2999 ： 音声通話オプション

-- 音声通話オプション(2000 〜 2999) --
insert into isp_api_enum values(2000,'/domain/mobile/monitoring/check','アクセス確認モニタリング用',SYSDATE);
insert into isp_api_enum values(2001,'/domain/mobile/voiceoption/outside/skip/engagementapply/submit','(契約-01)契約開始',SYSDATE);
insert into isp_api_enum values(2002,'/domain/mobile/voiceoption/outside/skip/engagementdetail/refer','(契約-02)音声オプション契約情報参照',SYSDATE);
insert into isp_api_enum values(2003,'/domain/mobile/voiceoption/outside/skip/engagementdetaillist/refer','(契約-03)音声オプション契約情報一覧参照',SYSDATE);
insert into isp_api_enum values(2004,'/domain/mobile/voiceoption/outside/skip/engagementcount/refer','(契約-04)音声オプション契約数参照',SYSDATE);
insert into isp_api_enum values(2005,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpapply/submit','(新規-01)新規申込MNPあり',SYSDATE);
insert into isp_api_enum values(2006,'/domain/mobile/voiceoption/outside/skip/newordernomnpapply/submit','(新規-02)新規申込MNPなし',SYSDATE);
insert into isp_api_enum values(2007,'/domain/mobile/voiceoption/outside/skip/newordernomnpafterbuynosimapply/submit','(新規-03)新規申込MNPなし後からスキームSimなし',SYSDATE);
insert into isp_api_enum values(2008,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpafterbuynosimapply/submit','(新規-04)新規申込MNPあり後からスキームSimなし',SYSDATE);
insert into isp_api_enum values(2009,'/domain/mobile/voiceoption/outside/skip/newordernomnpalliancephonewebnosimapply/submit','(新規-05)新規申込MNPなし提携スマホWebSimなし',SYSDATE);
insert into isp_api_enum values(2010,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpalliancephonewebnosimapply/submit','(新規-06)新規申込MNPあり提携スマホWebSimなし',SYSDATE);
insert into isp_api_enum values(2011,'/domain/mobile/voiceoption/outside/skip/newordernomnpalliancephonepostnosimapply/submit','(新規-07)新規申込MNPなし提携スマホ郵送Simなし',SYSDATE);
insert into isp_api_enum values(2012,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpalliancephonepostnosimapply/submit','(新規-08)新規申込MNPあり提携スマホ郵送Simなし',SYSDATE);
insert into isp_api_enum values(2013,'/domain/mobile/voiceoption/outside/skip/newordernomnpalliancephonecounterwithsimapply/submit','(新規-09)新規申込MNPなし提携スマホ店頭Simあり',SYSDATE);
insert into isp_api_enum values(2014,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpalliancephonecounternosimapply/submit','(新規-10)新規申込MNPあり提携スマホ店頭Simなし',SYSDATE);
insert into isp_api_enum values(2015,'/domain/mobile/voiceoption/outside/skip/neworderrollbackapply/submit','(新規-11)新規契約ロールバック',SYSDATE);
insert into isp_api_enum values(2016,'/domain/mobile/voiceoption/outside/user/identificationupload/check','(本人確認-01)本人確認書類アップロード可否チェック',SYSDATE);
insert into isp_api_enum values(2017,'/domain/mobile/voiceoption/outside/user/identificationupload/submit','(本人確認-02)本人確認書類アップロード',SYSDATE);
insert into isp_api_enum values(2018,'/domain/mobile/voiceoption/outside/skip/identificationdetail/refer','(本人確認-03)本人確認情報参照',SYSDATE);
insert into isp_api_enum values(2019,'/domain/mobile/voiceoption/outside/skip/provisionalresumewithmnp/submit','(仮受付復旧-01)仮受付復旧MNPあり',SYSDATE);
insert into isp_api_enum values(2020,'/domain/mobile/voiceoption/outside/skip/provisionalresumenomnp/submit','(仮受付復旧-02)仮受付復旧MNPなし',SYSDATE);
insert into isp_api_enum values(2021,'/domain/mobile/voiceoption/outside/skip/newordercancelbyltethreegengagementnumber/check','(キャンセル-01)新規申込キャンセル可否チェック(契約番号)',SYSDATE);
insert into isp_api_enum values(2022,'/domain/mobile/voiceoption/outside/skip/newordercancel/submit','(キャンセル-03)新規申込キャンセル',SYSDATE);
insert into isp_api_enum values(2023,'/domain/mobile/voiceoption/outside/skip/mnpoutcompletionandnewordercancel/submit','(キャンセル-04)MNP転出確定兼新規申込キャンセル',SYSDATE);
insert into isp_api_enum values(2024,'/domain/mobile/voiceoption/outside/skip/simchange/check','(変更-01)Sim変更可否チェック',SYSDATE);
insert into isp_api_enum values(2025,'/domain/mobile/voiceoption/outside/skip/simchangereflectbyneworderresend/submit','(変更-02)Sim変更反映 新規再送申込',SYSDATE);
insert into isp_api_enum values(2026,'/domain/mobile/voiceoption/outside/skip/simchangereflectbygetactualshipping/submit','(変更-03)Sim変更反映 発送実績取得',SYSDATE);
insert into isp_api_enum values(2027,'/domain/mobile/voiceoption/outside/skip/mnpoutapply/check','(転出-01)MNP転出申込可否チェック',SYSDATE);
insert into isp_api_enum values(2028,'/domain/mobile/voiceoption/outside/skip/mnpoutapply/submit','(転出-02)MNP転出申込',SYSDATE);
insert into isp_api_enum values(2029,'/domain/mobile/voiceoption/outside/skip/mnpoutonnewordercancelapply/submit','(転出-03)MNP転出申込(新規申込キャンセル時)',SYSDATE);
insert into isp_api_enum values(2030,'/domain/mobile/voiceoption/outside/skip/mnpout/refer','(転出-04)MNP転出参照',SYSDATE);
insert into isp_api_enum values(2031,'/domain/mobile/voiceoption/outside/skip/mnpoutcancel/submit','(転出キャンセル-01)MNP転出キャンセル',SYSDATE);
insert into isp_api_enum values(2032,'/domain/mobile/voiceoption/outside/skip/disengagementbyltethreegengagementnumber/check','(解約-01)解約可否チェック(契約番号)',SYSDATE);
insert into isp_api_enum values(2033,'/domain/mobile/voiceoption/outside/skip/disengagementapply/submit','(解約-03)解約',SYSDATE);
insert into isp_api_enum values(2034,'/domain/mobile/voiceoption/outside/skip/mnpoutcompletionanddisengagementapply/submit','(解約-04)MNP転出確定兼即時解約',SYSDATE);
insert into isp_api_enum values(2035,'/domain/mobile/voiceoption/outside/skip/disengagementreservationcancel/submit','(解約キャンセル-01)解約予約キャンセル',SYSDATE);
insert into isp_api_enum values(2036,'/domain/mobile/voiceoption/skip/identificationresultreflect/submit','(バッチ-本人確認-01)本人確認結果反映',SYSDATE);
insert into isp_api_enum values(2037,'/domain/mobile/voiceoption/skip/mnpoutrequest/submit','(バッチ-転出-01)転出依頼',SYSDATE);
insert into isp_api_enum values(2038,'/domain/mobile/voiceoption/skip/mnpreservationnumberregister/submit','(バッチ-転出-02)MNP予約番号登録',SYSDATE);
insert into isp_api_enum values(2039,'/domain/mobile/voiceoption/outside/user/msisdndoubleregistration/check','(新規-12)MSISDN二重登録チェック',SYSDATE);
insert into isp_api_enum values(2040,'/domain/mobile/voiceoption/outside/skip/engagementmonthdisengagementchargeapply/submit','(バッチ-契約-01)契約月解約課金',SYSDATE);
insert into isp_api_enum values(2041,'/domain/mobile/voiceoption/skip/newordercancellist/refer','(バッチ-キャンセル-01)キャンセル依頼リスト出力情報参照',SYSDATE);
insert into isp_api_enum values(2042,'/domain/mobile/voiceoption/outside/skip/mnpoutconfirmfordisengage/refer','(バッチ-転出-03)連動解約用MNP転出意思確認参照',SYSDATE);
insert into isp_api_enum values(2043,'/domain/mobile/voiceoption/outside/skip/mnpoutconfirmforcancel/refer','(バッチ-転出-04)連動キャンセル用MNP転出意思確認参照',SYSDATE);

insert into isp_api_client_enum values(2000,2000,'monitor','/monitor01','アクセス確認モニタリング用',SYSDATE);
insert into isp_api_client_enum values(2001,2001,'Scenario','M_JKBS_Kakin_upd2','受注管理基盤 課金設定２シナリオ',SYSDATE);
insert into isp_api_client_enum values(2002,2002,'Scenario','C_JKBS_Contract_mo_ref','受注管理基盤 外部向け契約参照シナリオ',SYSDATE);
insert into isp_api_client_enum values(2003,2002,'Scenario','C_JKBS_ConfirmLet_tim_upd_act2','受注管理基盤 申込確認書送付連携アクションシナリオ',SYSDATE);
insert into isp_api_client_enum values(2004,2003,'Scenario','C_BDCM_VoOpt_mo_ref','LTE・3G 音声契約参照シナリオ',SYSDATE);
insert into isp_api_client_enum values(2005,2004,'Scenario','M_BDCM_VoOptCount_get','LTE・3G 音声オプション件数取得シナリオ',SYSDATE);
insert into isp_api_client_enum values(2006,2005,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2007,2006,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2008,2007,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2009,2008,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2010,2009,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2011,2010,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2012,2011,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2013,2012,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2014,2013,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2015,2014,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2016,2015,'Scenario','C_JKBS_Contract_mo_reg','受注管理基盤 申込登録シナリオ',SYSDATE);
insert into isp_api_client_enum values(2017,2015,'Scenario','C_JKBS_NoSend_Contract_mo_reg','受注管理基盤 配送無し対応版_申込登録シナリオ',SYSDATE);
insert into isp_api_client_enum values(2018,2016,'vasap01','/identification/upload/input','本人確認書類アップロード可否チェック画面',SYSDATE);
insert into isp_api_client_enum values(2019,2016,'vasap01','/identification/upload/inputsubmit','本人確認書類アップロード画面',SYSDATE);
insert into isp_api_client_enum values(2020,2017,'vasap01','/identification/upload/inputsubmit','本人確認書類アップロード画面',SYSDATE);
insert into isp_api_client_enum values(2021,2018,'Scenario','C_BDCM_VoOpt_mo_ref','LTE・3G 音声契約参照シナリオ',SYSDATE);
insert into isp_api_client_enum values(2022,2018,'Scenario','C_JKBS_Contract_mo_ref','受注管理基盤 外部向け契約参照シナリオ',SYSDATE);
insert into isp_api_client_enum values(2023,2019,'Scenario','C_JKBS_PreAccept_tim_upd_act','受注管理基盤 仮契約復旧アクションシナリオ',SYSDATE);
insert into isp_api_client_enum values(2024,2020,'Scenario','C_JKBS_PreAccept_tim_upd_act','受注管理基盤 仮契約復旧アクションシナリオ',SYSDATE);
insert into isp_api_client_enum values(2025,2021,'Scenario','M_JKBS_VoApply_mo_del','受注管理基盤 音声オプション解約・キャンセルサブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2026,2021,'Scenario','M_BDCM_VoOptVarious_check','LTE・3G 音声オプション各種可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(2027,2022,'Scenario','M_JKBS_VoApply_mo_del','受注管理基盤 音声オプション解約・キャンセルサブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2028,2023,'Scenario','M_JKBS_VoApply_mo_del','受注管理基盤 音声オプション解約・キャンセルサブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2029,2024,'Scenario','M_BDCM_VoOptVarious_check','LTE・3G 音声オプション各種可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(2030,2025,'Scenario','C_JKBS_Resend_mo_cre','受注管理基盤 再送登録シナリオ',SYSDATE);
insert into isp_api_client_enum values(2031,2026,'Scenario','M_JKBS_ChgKakin_upd','受注管理基盤 変更・交換課金設定シナリオ',SYSDATE);
insert into isp_api_client_enum values(2032,2027,'Scenario','C_BDCM_ResultSendVoOpt_tim_upd','LTE・3G 発送実績取得(音声用)シナリオ',SYSDATE);
insert into isp_api_client_enum values(2033,2027,'Scenario','C_BDCM_MnpOut_mo_check','LTE・3G MNP転出申込可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(2034,2028,'Scenario','C_BDCM_MnpOut_mo_reg','LTE・3G MNP転出申込シナリオ',SYSDATE);
insert into isp_api_client_enum values(2035,2029,'Scenario','C_BDCM_MnpOut_mo_reg','LTE・3G MNP転出申込シナリオ',SYSDATE);
insert into isp_api_client_enum values(2036,2030,'Scenario','M_BDCM_VoOptVarious_check','LTE・3G 音声オプション各種可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(2037,2030,'Scenario','C_BDCM_ResultSendVoOpt_tim_upd','LTE・3G 発送実績取得(音声用)シナリオ',SYSDATE);
insert into isp_api_client_enum values(2038,2030,'Scenario','C_BDCM_ResultDeliVoOpt_tim_upd','LTE・3G 配送結果取得シナリオ(音声用)',SYSDATE);
insert into isp_api_client_enum values(2039,2031,'Scenario','C_BDCM_MnpOut_mo_del','LTE・3G MNP転出取消シナリオ',SYSDATE);
insert into isp_api_client_enum values(2040,2032,'Scenario','M_JKBS_VoApply_mo_del','受注管理基盤 音声オプション解約・キャンセルサブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2041,2032,'Scenario','M_BDCM_VoOptVarious_check','LTE・3G 音声オプション各種可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(2042,2033,'Scenario','M_JKBS_VoApply_mo_del','受注管理基盤 音声オプション解約・キャンセルサブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2043,2034,'Scenario','M_JKBS_VoApply_mo_del','受注管理基盤 音声オプション解約・キャンセルサブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2044,2035,'Scenario','C_BDCM_ReApply_mo_upd','LTE・3G 解約取消シナリオ',SYSDATE);
insert into isp_api_client_enum values(2045,2036,'Scenario','C_VOICE_IdentResult_tim_reg_act','音声通話オプション 本人確認結果反映バッチ',SYSDATE);
insert into isp_api_client_enum values(2046,2037,'Scenario','C_VOICE_MnpReqList_tim_send_act','音声通話オプション MNP転出依頼リスト送付バッチ',SYSDATE);
insert into isp_api_client_enum values(2047,2038,'Scenario','C_VOICE_MnpRsrvNum_tim_reg_act','音声通話オプション MNP予約番号登録バッチ',SYSDATE);
insert into isp_api_client_enum values(2048,2002,'Scenario','C_JKBS_PreAccept_tim_upd_act','受注管理基盤 仮契約復旧アクションシナリオ',SYSDATE);
insert into isp_api_client_enum values(2049,2030,'Scenario','C_JKBS_EquipSingle_mo_chk','受注管理基盤 機器単体処理可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(2050,2030,'Scenario','C_BDCM_VoOptNonAc_tim_get_act','LTE・3G ノンアクティベーションリスト抽出バッチアクションシナリオ（音声通話オプション用）',SYSDATE);
insert into isp_api_client_enum values(2051,2039,'vasap01','/signup/aeonlteossvoicecenter/confirmsubmit','イオンスマホ通信契約申込画面',SYSDATE);
insert into isp_api_client_enum values(2052,2002,'Scenario','C_BDCM_VoOptNonAc_tim_get_act','LTE・3G ノンアクティベーションリスト抽出バッチアクションシナリオ（音声通話オプション用）',SYSDATE);
insert into isp_api_client_enum values(2053,2040,'Scenario','C_VOICE_EngMonDel_tim_charge_act','音声通話オプション 契約月解約課金バッチ',SYSDATE);
insert into isp_api_client_enum values(2054,2041,'Scenario','C_VOICE_IdentNotFin_tim_send_act','音声通話オプション 本人確未実施自動キャンセルバッチ',SYSDATE);
insert into isp_api_client_enum values(2055,2042,'Scenario','C_BDCM_MemStsJudge_tim_upd_act','LTE・3G 会員状態連動解約判定アクションシナリオ(音声対応版)',SYSDATE);
insert into isp_api_client_enum values(2056,2043,'Scenario','C_BDCM_MemStsJudge_tim_upd_act','LTE・3G 会員状態連動解約判定アクションシナリオ(音声対応版)',SYSDATE);