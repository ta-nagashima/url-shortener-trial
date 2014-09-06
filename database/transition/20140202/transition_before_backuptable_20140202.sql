REMARK ###############################################
REMARK 移行前の履歴テーブルのバックアップを取得する
REMARK ###############################################

spool transition_before_backuptable_20140202.log

CREATE TABLE scmg_ivt_contract_matl_hist_bk AS SELECT * FROM scmg_ivt_contract_matl_hist;
CREATE TABLE wifi_register_device_state_bk AS SELECT * FROM wifi_register_device_state;
CREATE TABLE wifi_register_device_event_bk  AS SELECT * FROM wifi_register_device_event;
CREATE TABLE wifi_use_rights_state_bk AS SELECT * FROM wifi_use_rights_state;
CREATE TABLE wifi_use_rights_event_bk AS SELECT * FROM wifi_use_rights_event;

REMARK 確認

select table_name from user_tables where table_name like '%BK';

/*
    TABLE_NAME
    ------------------------------
    SCMG_IVT_CONTRACT_MATL_HIST_BK
    WIFI_REGISTER_DEVICE_STATE_BK
    WIFI_USE_RIGHTS_STATE_BK
    WIFI_USE_RIGHTS_EVENT_BK
    WIFI_REGISTER_DEVICE_EVENT_BK
 */

spool off