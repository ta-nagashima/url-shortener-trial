-- 4000-4999 通話明細
insert into isp_api_enum values(4000,'/domain/mobile/history/outside/user/summary/refer','（参照-01)通話履歴サマリー情報参照',SYSDATE);
insert into isp_api_enum values(4001,'/domain/mobile/history/outside/user/detail/refer','（参照-02)通話履歴詳細情報参照',SYSDATE);

insert into isp_api_client_enum values(4000,4000,'member','/history/call','通話履歴画面',SYSDATE);
insert into isp_api_client_enum values(4001,4001,'member','/history/call','通話履歴画面',SYSDATE);
