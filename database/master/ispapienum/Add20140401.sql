insert into isp_api_enum values(14,'/domain/fmc/wifispot/outside/skip/priceplan/change/check','LTE・3G向けプライスプラン変更可否チェックAPI',SYSDATE);
insert into isp_api_enum values(15,'/domain/fmc/wifispot/outside/skip/priceplan/change/precheck','LTE・3G向けプライスプラン変更可否プレチェックAPI',SYSDATE);
insert into isp_api_enum values(16,'/domain/fmc/wifispot/outside/operator/remove/submit','運用者向けバンドル版解除API',SYSDATE);
insert into isp_api_enum values(17,'/domain/fmc/wifispot/outside/operator/ltethreegremoveall/submit','運用者向けバンドル版LTE・3G全解除API',SYSDATE);
insert into isp_api_enum values(18,'/domain/fmc/wifispot/refer/access-point-area','アクセスポイントエリアの参照API',SYSDATE);

insert into isp_api_client_enum values(14,14,'Scenario','C_B3GS_Various_mo_check','3G・LTE各種可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(15,15,'Scenario','C_B3GS_Various_mo_check','3G・LTE各種可否チェックシナリオ',SYSDATE);
insert into isp_api_client_enum values(16,16,'Scenario','C_WIFI_MacAddr_ope_del','Wifi運用者向けバンドル版解除API',SYSDATE);
insert into isp_api_client_enum values(17,17,'Scenario','C_WIFI_Lte3gAllMacAddr_ope_del','Wifi運用者向けバンドル版LTE・3G全解除API',SYSDATE);
insert into isp_api_client_enum values(18,18,'vasap01','/plus/wfspot-ssid','Wifi向けSSID一覧参照画面',SYSDATE);
insert into isp_api_client_enum values(19,18,'vasap01','/plus/wfspot-ssid/index','Wifi向けSSID一覧参照画面',SYSDATE);
