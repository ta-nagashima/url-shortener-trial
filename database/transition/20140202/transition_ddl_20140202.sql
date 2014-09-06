REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★PANDA用のテーブル作成（WIFI_REGISTER_DEVICE_EVENT_P）
REMARK PANDA用のテーブル作成（WIFI_USE_RIGHTS_EVENT_P）
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

spool transition_ddl_20140202.log

CREATE TABLE WIFI_REGISTER_DEVICE_EVENT_P (
 record_id number(9) NOT NULL,
 primary_key1 number(9) NOT NULL,
 primary_key2 number(9) NOT NULL,
 primary_key3 number(9) NOT NULL,
 primary_key4 number(9) NOT NULL,
 foreign_key1 varchar2(256),
 foreign_key2 varchar2(256),
 foreign_key3 varchar2(256),
 foreign_key4 varchar2(256),
 foreign_key5 varchar2(256),
 foreign_key6 varchar2(256),
 foreign_key7 varchar2(256),
 foreign_key8 varchar2(256),
 foreign_key9 varchar2(256),
 foreign_key10 varchar2(256),
 foreign_key11 varchar2(256),
 foreign_key12 varchar2(256),
 foreign_key13 varchar2(256),
 foreign_key14 varchar2(256),
 foreign_key15 varchar2(256),
 foreign_key16 varchar2(256),
 foreign_key17 varchar2(256),
 foreign_key18 varchar2(256),
 foreign_key19 varchar2(256),
 foreign_key20 varchar2(256),
 namespaces1 varchar2(256),
 namespaces2 varchar2(256),
 namespaces3 varchar2(256),
 index_free1 varchar2(256),
 index_free2 varchar2(256),
 index_free3 varchar2(256),
 index_free4 varchar2(256),
 index_free5 varchar2(256),
 index_free6 varchar2(256),
 index_free7 varchar2(256),
 index_free8 varchar2(256),
 index_free9 varchar2(256),
 index_free10 varchar2(256),
 index_free_date1 date,
 index_free_date2 date,
 index_free_date3 date,
 index_free_date4 date,
 index_free_date5 date,
 index_free_date6 date,
 index_free_date7 date,
 index_free_date8 date,
 index_free_date9 date,
 index_free_date10 date,
 item_free1 varchar2(256),
 item_free2 varchar2(256),
 item_free3 varchar2(256),
 item_free4 varchar2(256),
 item_free5 varchar2(256),
 item_free6 varchar2(256),
 item_free7 varchar2(256),
 item_free8 varchar2(256),
 item_free9 varchar2(256),
 item_free10 varchar2(256),
 item_free11 varchar2(256),
 item_free12 varchar2(256),
 item_free13 varchar2(256),
 item_free14 varchar2(256),
 item_free15 varchar2(256),
 item_free16 varchar2(256),
 item_free17 varchar2(256),
 item_free18 varchar2(256),
 item_free19 varchar2(256),
 item_free20 varchar2(256),
 item_free21 varchar2(256),
 item_free22 varchar2(256),
 item_free23 varchar2(256),
 item_free24 varchar2(256),
 item_free25 varchar2(256),
 item_free26 varchar2(256),
 item_free27 varchar2(256),
 item_free28 varchar2(256),
 item_free29 varchar2(256),
 item_free30 varchar2(256),
 item_free31 varchar2(256),
 item_free32 varchar2(256),
 item_free33 varchar2(256),
 item_free34 varchar2(256),
 item_free35 varchar2(256),
 item_free36 varchar2(256),
 item_free37 varchar2(256),
 item_free38 varchar2(256),
 item_free39 varchar2(256),
 item_free40 varchar2(256),
 item_free41 varchar2(256),
 item_free42 varchar2(256),
 item_free43 varchar2(256),
 item_free44 varchar2(256),
 item_free45 varchar2(256),
 item_free46 varchar2(256),
 item_free47 varchar2(256),
 item_free48 varchar2(256),
 item_free49 varchar2(256),
 item_free50 varchar2(256),
 item_free51 varchar2(256),
 item_free52 varchar2(256),
 item_free53 varchar2(256),
 item_free54 varchar2(256),
 item_free55 varchar2(256),
 item_free56 varchar2(256),
 item_free57 varchar2(256),
 item_free58 varchar2(256),
 item_free59 varchar2(256),
 item_free60 varchar2(256),
 item_free61 varchar2(256),
 item_free62 varchar2(256),
 item_free63 varchar2(256),
 item_free64 varchar2(256),
 item_free65 varchar2(256),
 item_free66 varchar2(256),
 item_free67 varchar2(256),
 item_free68 varchar2(256),
 item_free69 varchar2(256),
 item_free70 varchar2(256),
 item_free71 varchar2(256),
 item_free72 varchar2(256),
 item_free73 varchar2(256),
 item_free74 varchar2(256),
 item_free75 varchar2(256),
 item_free76 varchar2(256),
 item_free77 varchar2(256),
 item_free78 varchar2(256),
 item_free79 varchar2(256),
 item_free80 varchar2(256),
 item_free81 varchar2(256),
 item_free82 varchar2(256),
 item_free83 varchar2(256),
 item_free84 varchar2(256),
 item_free85 varchar2(256),
 item_free86 varchar2(256),
 item_free87 varchar2(256),
 item_free88 varchar2(256),
 item_free89 varchar2(256),
 item_free90 varchar2(256),
 item_free91 varchar2(256),
 item_free92 varchar2(256),
 item_free93 varchar2(256),
 item_free94 varchar2(256),
 item_free95 varchar2(256),
 item_free96 varchar2(256),
 item_free97 varchar2(256),
 item_free98 varchar2(256),
 item_free99 varchar2(256),
 item_free100 varchar2(256),
 item_free101 varchar2(256),
 item_free102 varchar2(256),
 item_free103 varchar2(256),
 item_free104 varchar2(256),
 item_free105 varchar2(256),
 item_free106 varchar2(256),
 item_free107 varchar2(256),
 item_free108 varchar2(256),
 item_free109 varchar2(256),
 item_free110 varchar2(256),
 item_free111 varchar2(256),
 item_free112 varchar2(256),
 item_free113 varchar2(256),
 item_free114 varchar2(256),
 item_free115 varchar2(256),
 item_free116 varchar2(256),
 item_free117 varchar2(256),
 item_free118 varchar2(256),
 item_free119 varchar2(256),
 item_free120 varchar2(256),
 item_free121 varchar2(256),
 item_free122 varchar2(256),
 item_free123 varchar2(256),
 item_free124 varchar2(256),
 item_free125 varchar2(256),
 item_free126 varchar2(256),
 item_free127 varchar2(256),
 item_free128 varchar2(256),
 item_free129 varchar2(256),
 item_free130 varchar2(256),
 item_free131 varchar2(256),
 item_free132 varchar2(256),
 item_free133 varchar2(256),
 item_free134 varchar2(256),
 item_free135 varchar2(256),
 item_free136 varchar2(256),
 item_free137 varchar2(256),
 item_free138 varchar2(256),
 item_free139 varchar2(256),
 item_free140 varchar2(256),
 item_free141 varchar2(256),
 item_free142 varchar2(256),
 item_free143 varchar2(256),
 item_free144 varchar2(256),
 item_free145 varchar2(256),
 item_free146 varchar2(256),
 item_free147 varchar2(256),
 item_free148 varchar2(256),
 item_free149 varchar2(256),
 item_free150 varchar2(256),
 item_free151 varchar2(256),
 item_free152 varchar2(256),
 item_free153 varchar2(256),
 item_free154 varchar2(256),
 item_free155 varchar2(256),
 item_free156 varchar2(256),
 item_free157 varchar2(256),
 item_free158 varchar2(256),
 item_free159 varchar2(256),
 item_free160 varchar2(256),
 item_free161 varchar2(256),
 item_free162 varchar2(256),
 item_free163 varchar2(256),
 item_free164 varchar2(256),
 item_free165 varchar2(256),
 item_free166 varchar2(256),
 item_free167 varchar2(256),
 item_free168 varchar2(256),
 item_free169 varchar2(256),
 item_free170 varchar2(256),
 item_free171 varchar2(256),
 item_free172 varchar2(256),
 item_free173 varchar2(256),
 item_free174 varchar2(256),
 item_free175 varchar2(256),
 item_free176 varchar2(256),
 item_free177 varchar2(256),
 item_free178 varchar2(256),
 item_free179 varchar2(256),
 item_free180 varchar2(256),
 item_free181 varchar2(256),
 item_free182 varchar2(256),
 item_free183 varchar2(256),
 item_free184 varchar2(256),
 item_free185 varchar2(256),
 item_free186 varchar2(256),
 item_free187 varchar2(256),
 item_free188 varchar2(256),
 item_free189 varchar2(256),
 item_free190 varchar2(256),
 item_free191 varchar2(256),
 item_free192 varchar2(256),
 item_free193 varchar2(256),
 item_free194 varchar2(256),
 item_free195 varchar2(256),
 item_free196 varchar2(256),
 item_free197 varchar2(256),
 item_free198 varchar2(256),
 item_free199 varchar2(256),
 item_free200 varchar2(256),
 item_free_date1 date,
 item_free_date2 date,
 item_free_date3 date,
 item_free_date4 date,
 item_free_date5 date,
 item_free_date6 date,
 item_free_date7 date,
 item_free_date8 date,
 item_free_date9 date,
 item_free_date10 date,
 create_id varchar2(32) NOT NULL,
 create_date date NOT NULL,
 update_id varchar2(32) NOT NULL,
 update_date date NOT NULL,
 delete_date date,
 PRIMARY KEY(record_id,primary_key1,primary_key2,primary_key3,primary_key4)
USING INDEX TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED )
)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED )
;

CREATE INDEX index_free1_WIFI_RD_EVENT
ON WIFI_REGISTER_DEVICE_EVENT_P (index_free1)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED );

CREATE INDEX index_free2_WIFI_RD_EVENT
ON WIFI_REGISTER_DEVICE_EVENT_P (index_free2)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED );

CREATE INDEX update_date_WIFI_RD_EVENT
ON WIFI_REGISTER_DEVICE_EVENT_P (update_date)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED );


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★PANDA用のテーブル作成（WIFI_REGISTER_DEVICE_EVENT_P）
REMARK ★PANDA用のテーブル作成（WIFI_USE_RIGHTS_EVENT_P）
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

CREATE TABLE WIFI_USE_RIGHTS_EVENT_P (
 record_id number(9) NOT NULL,
 primary_key1 number(9) NOT NULL,
 primary_key2 number(9) NOT NULL,
 primary_key3 number(9) NOT NULL,
 primary_key4 number(9) NOT NULL,
 foreign_key1 varchar2(256),
 foreign_key2 varchar2(256),
 foreign_key3 varchar2(256),
 foreign_key4 varchar2(256),
 foreign_key5 varchar2(256),
 foreign_key6 varchar2(256),
 foreign_key7 varchar2(256),
 foreign_key8 varchar2(256),
 foreign_key9 varchar2(256),
 foreign_key10 varchar2(256),
 foreign_key11 varchar2(256),
 foreign_key12 varchar2(256),
 foreign_key13 varchar2(256),
 foreign_key14 varchar2(256),
 foreign_key15 varchar2(256),
 foreign_key16 varchar2(256),
 foreign_key17 varchar2(256),
 foreign_key18 varchar2(256),
 foreign_key19 varchar2(256),
 foreign_key20 varchar2(256),
 namespaces1 varchar2(256),
 namespaces2 varchar2(256),
 namespaces3 varchar2(256),
 index_free1 varchar2(256),
 index_free2 varchar2(256),
 index_free3 varchar2(256),
 index_free4 varchar2(256),
 index_free5 varchar2(256),
 index_free6 varchar2(256),
 index_free7 varchar2(256),
 index_free8 varchar2(256),
 index_free9 varchar2(256),
 index_free10 varchar2(256),
 index_free_date1 date,
 index_free_date2 date,
 index_free_date3 date,
 index_free_date4 date,
 index_free_date5 date,
 index_free_date6 date,
 index_free_date7 date,
 index_free_date8 date,
 index_free_date9 date,
 index_free_date10 date,
 item_free1 varchar2(256),
 item_free2 varchar2(256),
 item_free3 varchar2(256),
 item_free4 varchar2(256),
 item_free5 varchar2(256),
 item_free6 varchar2(256),
 item_free7 varchar2(256),
 item_free8 varchar2(256),
 item_free9 varchar2(256),
 item_free10 varchar2(256),
 item_free11 varchar2(256),
 item_free12 varchar2(256),
 item_free13 varchar2(256),
 item_free14 varchar2(256),
 item_free15 varchar2(256),
 item_free16 varchar2(256),
 item_free17 varchar2(256),
 item_free18 varchar2(256),
 item_free19 varchar2(256),
 item_free20 varchar2(256),
 item_free21 varchar2(256),
 item_free22 varchar2(256),
 item_free23 varchar2(256),
 item_free24 varchar2(256),
 item_free25 varchar2(256),
 item_free26 varchar2(256),
 item_free27 varchar2(256),
 item_free28 varchar2(256),
 item_free29 varchar2(256),
 item_free30 varchar2(256),
 item_free31 varchar2(256),
 item_free32 varchar2(256),
 item_free33 varchar2(256),
 item_free34 varchar2(256),
 item_free35 varchar2(256),
 item_free36 varchar2(256),
 item_free37 varchar2(256),
 item_free38 varchar2(256),
 item_free39 varchar2(256),
 item_free40 varchar2(256),
 item_free41 varchar2(256),
 item_free42 varchar2(256),
 item_free43 varchar2(256),
 item_free44 varchar2(256),
 item_free45 varchar2(256),
 item_free46 varchar2(256),
 item_free47 varchar2(256),
 item_free48 varchar2(256),
 item_free49 varchar2(256),
 item_free50 varchar2(256),
 item_free51 varchar2(256),
 item_free52 varchar2(256),
 item_free53 varchar2(256),
 item_free54 varchar2(256),
 item_free55 varchar2(256),
 item_free56 varchar2(256),
 item_free57 varchar2(256),
 item_free58 varchar2(256),
 item_free59 varchar2(256),
 item_free60 varchar2(256),
 item_free61 varchar2(256),
 item_free62 varchar2(256),
 item_free63 varchar2(256),
 item_free64 varchar2(256),
 item_free65 varchar2(256),
 item_free66 varchar2(256),
 item_free67 varchar2(256),
 item_free68 varchar2(256),
 item_free69 varchar2(256),
 item_free70 varchar2(256),
 item_free71 varchar2(256),
 item_free72 varchar2(256),
 item_free73 varchar2(256),
 item_free74 varchar2(256),
 item_free75 varchar2(256),
 item_free76 varchar2(256),
 item_free77 varchar2(256),
 item_free78 varchar2(256),
 item_free79 varchar2(256),
 item_free80 varchar2(256),
 item_free81 varchar2(256),
 item_free82 varchar2(256),
 item_free83 varchar2(256),
 item_free84 varchar2(256),
 item_free85 varchar2(256),
 item_free86 varchar2(256),
 item_free87 varchar2(256),
 item_free88 varchar2(256),
 item_free89 varchar2(256),
 item_free90 varchar2(256),
 item_free91 varchar2(256),
 item_free92 varchar2(256),
 item_free93 varchar2(256),
 item_free94 varchar2(256),
 item_free95 varchar2(256),
 item_free96 varchar2(256),
 item_free97 varchar2(256),
 item_free98 varchar2(256),
 item_free99 varchar2(256),
 item_free100 varchar2(256),
 item_free101 varchar2(256),
 item_free102 varchar2(256),
 item_free103 varchar2(256),
 item_free104 varchar2(256),
 item_free105 varchar2(256),
 item_free106 varchar2(256),
 item_free107 varchar2(256),
 item_free108 varchar2(256),
 item_free109 varchar2(256),
 item_free110 varchar2(256),
 item_free111 varchar2(256),
 item_free112 varchar2(256),
 item_free113 varchar2(256),
 item_free114 varchar2(256),
 item_free115 varchar2(256),
 item_free116 varchar2(256),
 item_free117 varchar2(256),
 item_free118 varchar2(256),
 item_free119 varchar2(256),
 item_free120 varchar2(256),
 item_free121 varchar2(256),
 item_free122 varchar2(256),
 item_free123 varchar2(256),
 item_free124 varchar2(256),
 item_free125 varchar2(256),
 item_free126 varchar2(256),
 item_free127 varchar2(256),
 item_free128 varchar2(256),
 item_free129 varchar2(256),
 item_free130 varchar2(256),
 item_free131 varchar2(256),
 item_free132 varchar2(256),
 item_free133 varchar2(256),
 item_free134 varchar2(256),
 item_free135 varchar2(256),
 item_free136 varchar2(256),
 item_free137 varchar2(256),
 item_free138 varchar2(256),
 item_free139 varchar2(256),
 item_free140 varchar2(256),
 item_free141 varchar2(256),
 item_free142 varchar2(256),
 item_free143 varchar2(256),
 item_free144 varchar2(256),
 item_free145 varchar2(256),
 item_free146 varchar2(256),
 item_free147 varchar2(256),
 item_free148 varchar2(256),
 item_free149 varchar2(256),
 item_free150 varchar2(256),
 item_free151 varchar2(256),
 item_free152 varchar2(256),
 item_free153 varchar2(256),
 item_free154 varchar2(256),
 item_free155 varchar2(256),
 item_free156 varchar2(256),
 item_free157 varchar2(256),
 item_free158 varchar2(256),
 item_free159 varchar2(256),
 item_free160 varchar2(256),
 item_free161 varchar2(256),
 item_free162 varchar2(256),
 item_free163 varchar2(256),
 item_free164 varchar2(256),
 item_free165 varchar2(256),
 item_free166 varchar2(256),
 item_free167 varchar2(256),
 item_free168 varchar2(256),
 item_free169 varchar2(256),
 item_free170 varchar2(256),
 item_free171 varchar2(256),
 item_free172 varchar2(256),
 item_free173 varchar2(256),
 item_free174 varchar2(256),
 item_free175 varchar2(256),
 item_free176 varchar2(256),
 item_free177 varchar2(256),
 item_free178 varchar2(256),
 item_free179 varchar2(256),
 item_free180 varchar2(256),
 item_free181 varchar2(256),
 item_free182 varchar2(256),
 item_free183 varchar2(256),
 item_free184 varchar2(256),
 item_free185 varchar2(256),
 item_free186 varchar2(256),
 item_free187 varchar2(256),
 item_free188 varchar2(256),
 item_free189 varchar2(256),
 item_free190 varchar2(256),
 item_free191 varchar2(256),
 item_free192 varchar2(256),
 item_free193 varchar2(256),
 item_free194 varchar2(256),
 item_free195 varchar2(256),
 item_free196 varchar2(256),
 item_free197 varchar2(256),
 item_free198 varchar2(256),
 item_free199 varchar2(256),
 item_free200 varchar2(256),
 item_free_date1 date,
 item_free_date2 date,
 item_free_date3 date,
 item_free_date4 date,
 item_free_date5 date,
 item_free_date6 date,
 item_free_date7 date,
 item_free_date8 date,
 item_free_date9 date,
 item_free_date10 date,
 create_id varchar2(32) NOT NULL,
 create_date date NOT NULL,
 update_id varchar2(32) NOT NULL,
 update_date date NOT NULL,
 delete_date date,
 PRIMARY KEY(record_id,primary_key1,primary_key2,primary_key3,primary_key4)
USING INDEX TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED )
)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED )
;

CREATE INDEX index_free1_WIFI_UR_EVENT
ON WIFI_USE_RIGHTS_EVENT_P (index_free1)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED );

CREATE INDEX index_free2_WIFI_UR_EVENT
ON WIFI_USE_RIGHTS_EVENT_P (index_free2)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED );

CREATE INDEX update_date_WIFI_UR_EVENT
ON WIFI_USE_RIGHTS_EVENT_P (update_date)
TABLESPACE event_data01
STORAGE(
INITIAL      50M
NEXT         50M
PCTINCREASE 0
MAXEXTENTS  UNLIMITED );


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★PANDA用のテーブル作成（WIFI_REGISTER_DEVICE_EVENT_P）
REMARK ★PANDA用のテーブル作成（WIFI_USE_RIGHTS_EVENT_P）
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


