insert into isp_api_enum values(2044,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpconfirmedidentificationapply/submit','（新規-13）新規申し込みMNPあり本人確認済み',SYSDATE);
insert into isp_api_enum values(2045,'/domain/mobile/voiceoption/outside/skip/newordernomnpconfirmedidentificationapply/submit','（新規-14）新規申し込みMNPなし本人確認済み',SYSDATE);

insert into isp_api_client_enum values(2057,2044,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2058,2045,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);



// 誤りがあったので修正用のUPDATE文

update isp_api_enum
set api_path = '/domain/mobile/voiceoption/outside/skip/neworderwithmnpwithconfirmedidentificationapply/submit'
where api_id = 2044

update isp_api_enum
set api_path = '/domain/mobile/voiceoption/outside/skip/newordernomnpwithconfirmedidentificationapply/submit'
where api_id = 2045

select * from isp_api_enum where api_id in(2044,2045)


// 修正済み（本番環境用）

insert into isp_api_enum values(2044,'/domain/mobile/voiceoption/outside/skip/neworderwithmnpwithconfirmedidentificationapply/submit','（新規-13）新規申し込みMNPあり本人確認済み',SYSDATE);
insert into isp_api_enum values(2045,'/domain/mobile/voiceoption/outside/skip/newordernomnpwithconfirmedidentificationapply/submit','（新規-14）新規申し込みMNPなし本人確認済み',SYSDATE);

insert into isp_api_client_enum values(2057,2044,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);
insert into isp_api_client_enum values(2058,2045,'Scenario','M_JKBS_VoOpt_reg','受注管理基盤 音声オプション申込登録サブシナリオ',SYSDATE);

