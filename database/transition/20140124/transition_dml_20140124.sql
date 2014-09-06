REMARK ###############################################
REMARK API利用ログ用 初期データパッチ
REMARK ###############################################

spool transition_dml_20140124.log

insert into isp_api_enum values(0, '/domain/fmc/monitoring/check' ,'アクセス確認モニタリング用' , SYSDATE);
insert into isp_api_enum values(1,'/domain/fmc/wifispot/bundle/register/check/macaddress','Wifiバンドル版登録MacアドレスチェックAPI',SYSDATE);
insert into isp_api_enum values(2,'/domain/fmc/wifispot/bundle/register/submit','Wifiバンドル版登録API',SYSDATE);
insert into isp_api_enum values(3,'/domain/fmc/wifispot/bundle/re-register/check/macaddress','Wifiバンドル版再登録MacアドレスチェックAPI',SYSDATE);
insert into isp_api_enum values(4,'/domain/fmc/wifispot/bundle/re-register/submit','Wifiバンドル版再登録API',SYSDATE);
insert into isp_api_enum values(5,'/domain/fmc/wifispot/bundle/change/check/macaddress','Wifiバンドル版変更MacアドレスチェックAPI',SYSDATE);
insert into isp_api_enum values(6,'/domain/fmc/wifispot/bundle/change/submit','Wifiバンドル版変更API',SYSDATE);
insert into isp_api_enum values(7,'/domain/fmc/wifispot/bundle/remove/submit','Wifiバンドル版解除API',SYSDATE);
insert into isp_api_enum values(8,'/domain/fmc/wifispot/list','Wifi一覧参照API',SYSDATE);
insert into isp_api_enum values(9,'/domain/fmc/wifispot/app/privateid/refer/auth-sim','Wifiアプリ向け専用ID取得（SIM認証）',SYSDATE);
insert into isp_api_enum values(10,'/domain/fmc/wifispot/app/bundle/register/check','Wifiアプリ向けバンドル版登録可否チェック',SYSDATE);
insert into isp_api_enum values(11,'/domain/fmc/wifispot/app/bundle/register/submit','Wifiアプリ向けバンドル版登録',SYSDATE);
insert into isp_api_enum values(12,'/domain/fmc/wifispot/app/bundle/registerstatus/refer','Wifiアプリ向けバンドル版登録状況の確認',SYSDATE);
insert into isp_api_enum values(13,'/domain/fmc/wifispot/outside/contractstatus','Wifi外部向け契約状態参照API',SYSDATE);

insert into isp_api_client_enum values(0, 0, 'monitor' , '/monitor01', 'アクセス確認モニタリング用' , SYSDATE);
insert into isp_api_client_enum values(1,1,'vasap01','/plus/wfspot-bundle-register/inputsubmit','Wifiバンドル版登録入力画面',SYSDATE);
insert into isp_api_client_enum values(2,2,'vasap01','/plus/wfspot-bundle-register/confirmsubmit','Wifiバンドル版登録確認画面',SYSDATE);
insert into isp_api_client_enum values(3,3,'vasap01','/plus/wfspot-bundle-re-register/inputsubmit','Wifiバンドル版再登録入力画面',SYSDATE);
insert into isp_api_client_enum values(4,4,'vasap01','/plus/wfspot-bundle-re-register/confirmsubmit','Wifiバンドル版再登録確認画面',SYSDATE);
insert into isp_api_client_enum values(5,5,'vasap01','/plus/wfspot-bundle-change/inputsubmit','Wifiバンドル版変更入力画面',SYSDATE);
insert into isp_api_client_enum values(6,6,'vasap01','/plus/wfspot-bundle-change/confirmsubmit','Wifiバンドル版変更確認画面',SYSDATE);
insert into isp_api_client_enum values(7,7,'vasap01','/plus/wfspot-bundle-remove/confirmsubmit','Wifiバンドル版解除確認画面',SYSDATE);
insert into isp_api_client_enum values(8,8,'vasap01','/plus/wfspot-list/index','Wifi一覧画面',SYSDATE);
insert into isp_api_client_enum values(9,9,'vasap01','/plus/wlanprivateidref/','Wifiアプリ向け専用ID取得（SIM認証）',SYSDATE);
insert into isp_api_client_enum values(10,10,'vasap01','/plus/wlanchk/','Wifiアプリ向けバンドル版登録可否チェック',SYSDATE);
insert into isp_api_client_enum values(11,11,'vasap01','/plus/wlanpur/','Wifiアプリ向けバンドル版登録',SYSDATE);
insert into isp_api_client_enum values(12,12,'vasap01','/plus/wlanref/','Wifiアプリ向けバンドル版登録状況の確認',SYSDATE);
insert into isp_api_client_enum values(13,13,'member','/contract/mobile','Wifi利用明細モバイル契約情報参照画面',SYSDATE);

REMARK "確認"

select API_ID, API_PATH from isp_api_enum order by API_ID;

/*

    API_ID API_PATH
---------- ----------------------------------------------------------------------------------------------------
         0 /domain/fmc/monitoring/check
         1 /domain/fmc/wifispot/bundle/register/check/macaddress
         2 /domain/fmc/wifispot/bundle/register/submit
         3 /domain/fmc/wifispot/bundle/re-register/check/macaddress
         4 /domain/fmc/wifispot/bundle/re-register/submit
         5 /domain/fmc/wifispot/bundle/change/check/macaddress
         6 /domain/fmc/wifispot/bundle/change/submit
         7 /domain/fmc/wifispot/bundle/remove/submit
         8 /domain/fmc/wifispot/list
         9 /domain/fmc/wifispot/app/privateid/refer/auth-sim
        10 /domain/fmc/wifispot/app/bundle/register/check
        11 /domain/fmc/wifispot/app/bundle/register/submit
        12 /domain/fmc/wifispot/app/bundle/registerstatus/refer
        13 /domain/fmc/wifispot/outside/contractstatus

*/

REMARK "確認"

select CLIENT_ID, API_ID, CLIENT_DOMAIN_IDENTIFIER, CLIENT_PATH from isp_api_client_enum order by CLIENT_ID;

/*

 CLIENT_ID     API_ID CLIENT_DOMAIN_IDENTI CLIENT_PATH
---------- ---------- -------------------- ----------------------------------------------------------------------------------------------------
         0          0 monitor              /monitor01
         1          1 vasap01              /plus/wfspot-bundle-register/inputsubmit
         2          2 vasap01              /plus/wfspot-bundle-register/confirmsubmit
         3          3 vasap01              /plus/wfspot-bundle-re-register/inputsubmit
         4          4 vasap01              /plus/wfspot-bundle-re-register/confirmsubmit
         5          5 vasap01              /plus/wfspot-bundle-change/inputsubmit
         6          6 vasap01              /plus/wfspot-bundle-change/confirmsubmit
         7          7 vasap01              /plus/wfspot-bundle-remove/confirmsubmit
         8          8 vasap01              /plus/wfspot-list/index
         9          9 vasap01              /plus/wlanprivateidref/
        10         10 vasap01              /plus/wlanchk/
        11         11 vasap01              /plus/wlanpur/
        12         12 vasap01              /plus/wlanref/
        13         13 member               /contract/mobile

*/

commit;

spool off