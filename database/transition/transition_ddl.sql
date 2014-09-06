REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK シーケンス作成
REMARK 索引作成
REMARK 移行用VIEWテーブル作成
REMARK 利用権EVENTへの移行
REMARK 登録端末EVENTへの移行
REMARK 利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

spool transition_ddl.log

CREATE TABLE wifi_register_device_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  use_rights_id VARCHAR2(20) NOT NULL,
  macaddress CHAR(12) NOT NULL,
  macaddress_register_date DATE NOT NULL,
  authentication_id CHAR(64) NOT NULL,
  authentication_password CHAR(64) NOT NULL,
  biglobe_id VARCHAR2(8) NOT NULL,
  macaddress_update_day CHAR(8) NOT NULL,
  not_payment_target VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
CONSTRAINT pk_wifi_register_device_event PRIMARY KEY(event_id)
	USING INDEX TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
))TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE wifi_register_device_state (
  use_rights_id VARCHAR2(20) NOT NULL,
  macaddress CHAR(12) NOT NULL,
  macaddress_register_date DATE NOT NULL,
  authentication_id CHAR(64) NOT NULL,
  authentication_password CHAR(64) NOT NULL,
  CONSTRAINT pk_wifi_register_device_state PRIMARY KEY(use_rights_id)
	USING INDEX TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
))TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE wifi_use_rights_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  use_rights_id VARCHAR2(20) NOT NULL,
  use_rights_contract_end_day CHAR(8) NOT NULL,
  biglobe_id VARCHAR2(8) NOT NULL,
  last_update_month CHAR(6) NOT NULL,
  change_count NUMBER NOT NULL,
  use_rights_plan VARCHAR2(32) NOT NULL,
  bundle_type VARCHAR2(32) NOT NULL,
  contract_no VARCHAR2(32) NOT NULL,
  use_rights_status VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_wifi_use_rights_event PRIMARY KEY(event_id)
	USING INDEX TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
))TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE wifi_use_rights_state (
  use_rights_id VARCHAR2(20) NOT NULL,
  use_rights_contract_end_day CHAR(8) NOT NULL,
  biglobe_id VARCHAR2(8) NOT NULL,
  last_update_month CHAR(6) NOT NULL,
  change_count NUMBER NOT NULL,
  use_rights_plan VARCHAR2(32) NOT NULL,
  bundle_type VARCHAR2(32) NOT NULL,
  contract_no VARCHAR2(32) NOT NULL,
  use_rights_status VARCHAR2(32) NOT NULL, 
  CONSTRAINT pk_wifi_use_rights_state PRIMARY KEY(use_rights_id)
	USING INDEX TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
))TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE wifi_connection_state (
  carrier VARCHAR2(32) NOT NULL,
  macaddress CHAR(12) NOT NULL,
  macaddress_used_date DATE NOT NULL,
  CONSTRAINT pk_wifi_connection_state PRIMARY KEY(carrier,macaddress)
	USING INDEX TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
))TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE isp_api_client_enum (
  client_id NUMBER NOT NULL,
  api_id NUMBER NOT NULL,
  client_domain_identifier VARCHAR(20) NOT NULL,
  client_path VARCHAR(100) NOT NULL,
  client_name VARCHAR(100) NOT NULL,
  create_date DATE NOT NULL,
  CONSTRAINT
    pk_isp_api_client_enum PRIMARY KEY(client_id),
  CONSTRAINT
    uk_isp_api_client_enum UNIQUE(api_id, client_path, client_domain_identifier)
)TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE isp_api_enum (
  api_id NUMBER NOT NULL,
  api_path VARCHAR(100) NOT NULL,
  api_name VARCHAR(100) NOT NULL,
  create_date DATE NOT NULL,
  CONSTRAINT pk_isp_api_enum PRIMARY KEY(api_id)
)TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);

CREATE TABLE isp_use_api_event (
  api_id NUMBER NOT NULL,
  api_client_id NUMBER NOT NULL,
  not_found_api_name VARCHAR(100) NOT NULL,
  tat NUMBER NOT NULL,
  event_date DATE NOT NULL
)
TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED 
);


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK 索引作成
REMARK 移行用VIEWテーブル作成
REMARK 利用権EVENTへの移行
REMARK 登録端末EVENTへの移行
REMARK 利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

CREATE SEQUENCE seq_wifi_register_device_event;

CREATE SEQUENCE seq_wifi_use_rights_event;

CREATE SEQUENCE seq_wifi_use_rights_id;


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK 移行用VIEWテーブル作成
REMARK 利用権EVENTへの移行
REMARK 登録端末EVENTへの移行
REMARK 利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

create index i_wifi_device_state_macaddr
on wifi_register_device_state(macaddress)
 TABLESPACE event_data01
 STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );


create index i_wifi_rights_state_biglobeid
on wifi_use_rights_state(biglobe_id)
 TABLESPACE event_data01
 STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );

create index i_wifi_device_event_rightsid
on wifi_register_device_event(use_rights_id)
 TABLESPACE event_data01
 STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );

create index i_wifi_rights_event_rightsid
 on wifi_use_rights_event(use_rights_id)
 TABLESPACE event_data01
 STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );

create index i_isp_api_enum
on isp_api_enum(api_path)
 TABLESPACE event_data01
 STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );

create index i_isp_use_api_event
on isp_use_api_event(event_date)
 TABLESPACE event_data01
 STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );


REMARK "WIFIテーブルの確認"

select table_name,tablespace_name from user_tables where table_name like 'WIFI%';

/*
	TABLE_NAME                     TABLESPACE_NAME
	------------------------------ ------------------------------
	WIFI_CONNECTION_STATE          EVENT_DATA01
	WIFI_REGISTER_DEVICE_EVENT     EVENT_DATA01
	WIFI_REGISTER_DEVICE_STATE     EVENT_DATA01
	WIFI_USE_RIGHTS_EVENT          EVENT_DATA01
	WIFI_USE_RIGHTS_STATE          EVENT_DATA01

*/

REMARK "ISPテーブルの確認"

select table_name,tablespace_name from user_tables where table_name like 'ISP%';

/*
	TABLE_NAME                     TABLESPACE_NAME
	------------------------------ ------------------------------
	ISP_API_CLIENT_ENUM            EVENT_DATA01
	ISP_API_ENUM                   EVENT_DATA01
	ISP_USE_API_EVENT              EVENT_DATA01

*/

REMARK "シーケンスの確認"

select sequence_name,min_value,max_value,increment_by,cycle_flag,last_number from user_sequences where sequence_name like 'SEQ%';

/*

  SEQUENCE_NAME                   MIN_VALUE  MAX_VALUE INCREMENT_BY C LAST_NUMBER
  ------------------------------ ---------- ---------- ------------ - -----------
  SEQ_WIFI_REGISTER_DEVICE_EVENT          1 1.0000E+28            1 N           1
  SEQ_WIFI_USE_RIGHTS_EVENT               1 1.0000E+28            1 N           1

 */


REMARK "WIFIインデックスの確認"

select index_name,tablespace_name from user_indexes where index_name like '%WIFI%' order by 1;

/*

	INDEX_NAME                     TABLESPACE_NAME
	------------------------------ ------------------------------
	I_WIFI_DEVICE_EVENT_RIGHTSID   EVENT_DATA01
	I_WIFI_DEVICE_STATE_MACADDR    EVENT_DATA01
	I_WIFI_RIGHTS_EVENT_RIGHTSID   EVENT_DATA01
	I_WIFI_RIGHTS_STATE_BIGLOBEID  EVENT_DATA01
	PK_WIFI_CONNECTION_STATE       EVENT_DATA01
	PK_WIFI_REGISTER_DEVICE_EVENT  EVENT_DATA01
	PK_WIFI_REGISTER_DEVICE_STATE  EVENT_DATA01
	PK_WIFI_USE_RIGHTS_EVENT       EVENT_DATA01
	PK_WIFI_USE_RIGHTS_STATE       EVENT_DATA01


*/

REMARK "ISPインデックスの確認"

select index_name,tablespace_name from user_indexes where index_name like '%ISP%' order by 1;

/*

	INDEX_NAME                     TABLESPACE_NAME
	------------------------------ ------------------------------
	I_ISP_API_ENUM                 EVENT_DATA01
	I_ISP_USE_API_EVENT            EVENT_DATA01
	PK_ISP_API_CLIENT_ENUM         EVENT_DATA01
	PK_ISP_API_ENUM                EVENT_DATA01
	UK_ISP_API_CLIENT_ENUM         EVENT_DATA01

*/


REMARK "インデックス列の確認"

col COLUMN_NAME format a30

select index_name,table_name,column_name from user_ind_columns where index_name like '%WIFI%'
or index_name like '%ISP%'  order by 1;

/*

  INDEX_NAME                     TABLE_NAME                     COLUMN_NAME
  ------------------------------ ------------------------------ ------------------------------
  I_ISP_API_ENUM                 ISP_API_ENUM                   API_PATH
  I_ISP_USE_API_EVENT            ISP_USE_API_EVENT              EVENT_DATE
  I_WIFI_DEVICE_EVENT_RIGHTSID   WIFI_REGISTER_DEVICE_EVENT     USE_RIGHTS_ID
  I_WIFI_DEVICE_STATE_MACADDR    WIFI_REGISTER_DEVICE_STATE     MACADDRESS
  I_WIFI_RIGHTS_EVENT_RIGHTSID   WIFI_USE_RIGHTS_EVENT          USE_RIGHTS_ID
  I_WIFI_RIGHTS_STATE_BIGLOBEID  WIFI_USE_RIGHTS_STATE          BIGLOBE_ID
  PK_ISP_API_CLIENT_ENUM         ISP_API_CLIENT_ENUM            CLIENT_ID
  PK_ISP_API_ENUM                ISP_API_ENUM                   API_ID
  PK_WIFI_CONNECTION_STATE       WIFI_CONNECTION_STATE          CARRIER
  PK_WIFI_CONNECTION_STATE       WIFI_CONNECTION_STATE          MACADDRESS
  PK_WIFI_REGISTER_DEVICE_EVENT  WIFI_REGISTER_DEVICE_EVENT     EVENT_ID
  PK_WIFI_REGISTER_DEVICE_STATE  WIFI_REGISTER_DEVICE_STATE     USE_RIGHTS_ID
  PK_WIFI_USE_RIGHTS_EVENT       WIFI_USE_RIGHTS_EVENT          EVENT_ID
  PK_WIFI_USE_RIGHTS_STATE       WIFI_USE_RIGHTS_STATE          USE_RIGHTS_ID
  UK_ISP_API_CLIENT_ENUM         ISP_API_CLIENT_ENUM            CLIENT_DOMAIN_IDENTIFIER
  UK_ISP_API_CLIENT_ENUM         ISP_API_CLIENT_ENUM            API_ID
  UK_ISP_API_CLIENT_ENUM         ISP_API_CLIENT_ENUM            CLIENT_PATH

*/

REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★移行用VIEWテーブル作成
REMARK 利用権EVENTへの移行
REMARK 登録端末EVENTへの移行
REMARK 利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

/*
PRIMARY_KEY2	商材契約ID
INDEX_FREE4	ステータス（200：契約、800：解約確定）
INDEX_FREE7	商材ID（BWIFI）
INDEX_FREE9	BIGLOBE ID
ITEM_FREE51	MACアドレス
ITEM_FREE52	登録日時
ITEM_FREE53	登録変更回数
ITEM_FREE54	最終変更日時
ITEM_FREE55	契約種類（3G／LTE・3G）
ITEM_FREE56	ユースケース情報（regist：登録、change：ＭＡＣアドレス変更、delete：解除）
ITEM_FREE57	経路情報（WEB：web画面、Apli：アプリ）
ITEM_FREE58	契約番号（3G/LTE・3G)
ITEM_FREE59	解約理由
ITEM_FREE60	最終利用日時
*/


CREATE TABLE TEMP_VIEW
AS
SELECT
PRIMARY_KEY2,
INDEX_FREE9,
INDEX_FREE4,
ITEM_FREE51,
MIN(update_date) AS min_update_date,
DECODE(SUBSTR(item_free59,-4), '連動解約',TO_CHAR(MIN(update_date),'YYYYMMDD'), '29991231') AS use_rights_contract_end_day,
ITEM_FREE53,
--DECODE(SUBSTR(item_free59,-4), '連動解約', 'removed', 'registered') AS use_rights_status,
DECODE(ITEM_FREE56, 'delete', 'removed', 'registered') AS use_rights_status,
ITEM_FREE56,
DECODE(ITEM_FREE57, 'WEB', 'Web', 'Apli', 'App') AS ITEM_FREE57,
ITEM_FREE58,
DECODE(item_free59, 
	'3G解約に伴う連動解約','Decontract3G',
	'LTE・3G解約に伴う連動解約', 'DecontractLTE', 
	'一定期間利用無しのため解除','NoUse', 'Normal') AS delete_kind,
	item_free59,
DECODE(ITEM_FREE55,'3G','three_g','lte_three_g') AS bundle_type,
CASE
 WHEN ITEM_FREE59 IS NOT NULL AND TO_CHAR(MIN(update_date), 'fmDD') = '1'
 THEN 'no_target'
 ELSE 'undesided'
END AS not_payment_target
FROM SCMG_IVT_CONTRACT_MATL_HIST
GROUP BY 
	PRIMARY_KEY2,
	INDEX_FREE6,
	INDEX_FREE9,
	INDEX_FREE4,
	ITEM_FREE51,
	ITEM_FREE52,
	ITEM_FREE53,
	ITEM_FREE54,
	ITEM_FREE55,
	ITEM_FREE56,
	ITEM_FREE57,
	ITEM_FREE58,
	ITEM_FREE59
;

CREATE INDEX i_temp1 ON temp_view(PRIMARY_KEY2, min_update_date);

spool off


