insert into isp_api_enum values(2040,'/domain/mobile/voiceoption/outside/skip/engagementmonthdisengagementchargeapply/submit','(バッチ-契約-01)契約月解約課金',SYSDATE);
insert into isp_api_enum values(2041,'/domain/mobile/voiceoption/skip/newordercancellist/refer','(バッチ-キャンセル-01)キャンセル依頼リスト出力情報参照',SYSDATE);
insert into isp_api_enum values(2042,'/domain/mobile/voiceoption/outside/skip/mnpoutconfirmfordisengage/refer','(バッチ-転出-03)連動解約用MNP転出意思確認参照',SYSDATE);
insert into isp_api_enum values(2043,'/domain/mobile/voiceoption/outside/skip/mnpoutconfirmforcancel/refer','(バッチ-転出-04)連動キャンセル用MNP転出意思確認参照',SYSDATE);

insert into isp_api_client_enum values(2053,2040,'Scenario','C_VOICE_EngMonDel_tim_charge_act','音声通話オプション 契約月解約課金バッチ',SYSDATE);
insert into isp_api_client_enum values(2054,2041,'Scenario','C_VOICE_IdentNotFin_tim_send_act','音声通話オプション 本人確未実施自動キャンセルバッチ',SYSDATE);
insert into isp_api_client_enum values(2055,2042,'Scenario','C_BDCM_MemStsJudge_tim_upd_act','LTE・3G 会員状態連動解約判定アクションシナリオ(音声対応版)',SYSDATE);
insert into isp_api_client_enum values(2056,2043,'Scenario','C_BDCM_MemStsJudge_tim_upd_act','LTE・3G 会員状態連動解約判定アクションシナリオ(音声対応版)',SYSDATE);