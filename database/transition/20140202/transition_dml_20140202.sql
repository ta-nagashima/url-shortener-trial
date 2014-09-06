REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★移行用VIEWテーブル作成
REMARK ★利用権EVENTへの移行
REMARK 登録端末EVENTへの移行
REMARK 利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

spool transition_dml_20140202.log

REMARK ###########ステップ１：利用権登録イベントを登録###########


INSERT INTO wifi_use_rights_event
SELECT
	seq_wifi_use_rights_event.nextval AS event_id,
	'insert' AS event_type,
	PRIMARY_KEY2 AS use_rights_id,
	use_rights_contract_end_day,
	INDEX_FREE9 AS biglobe_id,
	TO_CHAR(min_update_date, 'YYYYMM') AS last_update_month,
	ITEM_FREE53 AS change_count,
	'bundle' AS use_rights_plan,
	bundle_type,
	ITEM_FREE58 AS contract_no,
	use_rights_status,
	min_update_date AS event_date,
	'Register' || ITEM_FREE57 AS event_process
FROM TEMP_VIEW
WHERE (min_update_date, primary_key2) IN(SELECT MIN(min_update_date), primary_key2 
	FROM TEMP_VIEW
	WHERE ITEM_FREE56 = 'regist'
	GROUP BY PRIMARY_KEY2);


REMARK ###########ステップ２：利用権更新イベントを登録###########

INSERT INTO wifi_use_rights_event
SELECT
	seq_wifi_use_rights_event.nextval AS event_id,
	'update' AS event_type,
	PRIMARY_KEY2 AS use_rights_id,
	use_rights_contract_end_day,
	INDEX_FREE9 AS biglobe_id,
	TO_CHAR(min_update_date, 'YYYYMM') AS last_update_month,
	ITEM_FREE53 AS change_count,
	'bundle' AS use_rights_plan,
	bundle_type,
	ITEM_FREE58 AS contract_no,
	use_rights_status,
	min_update_date AS event_date,
	'Change' || ITEM_FREE57 AS event_process
FROM TEMP_VIEW
WHERE (min_update_date, primary_key2) IN(
	SELECT min_update_date,primary_key2
	FROM TEMP_VIEW
	MINUS
	SELECT MIN(min_update_date), primary_key2 
	FROM TEMP_VIEW
	WHERE ITEM_FREE56 = 'regist'
	GROUP BY PRIMARY_KEY2
	);

REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★移行用VIEWテーブル作成
REMARK ★利用権EVENTへの移行
REMARK ★登録端末EVENTへの移行
REMARK 利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################


REMARK ##########ステップ１：新規登録イベントと再登録イベントを登録##########

INSERT INTO wifi_register_device_event 
SELECT
	seq_wifi_register_device_event.NEXTVAL AS event_id,
	'insert' AS event_type,
	PRIMARY_KEY2 AS use_rights_id,
	ITEM_FREE51 AS macaddress,
	min_update_date AS macaddress_register_date,	
	ITEM_FREE51 AS authentication_id,
	ITEM_FREE51 AS authentication_password,
	INDEX_FREE9 AS biglobe_id,									
	TO_CHAR(min_update_date, 'YYYYMMDD') AS macaddress_update_day,	
	not_payment_target,
	min_update_date AS event_date,	
	--ITEM_FREE57 AS event_process
	'Register' || ITEM_FREE57 AS event_process
FROM TEMP_VIEW
WHERE ITEM_FREE56 = 'regist';


REMARK ##########ステップ２：削除イベントと更新イベントのうち削除レコードを登録##########

INSERT INTO wifi_register_device_event 
SELECT
	seq_wifi_register_device_event.NEXTVAL AS event_id,
	'delete' AS event_type,
	PRIMARY_KEY2 AS use_rights_id,
	macaddress,
	macaddress_register_date,	
	authentication_id,
	authentication_id AS authentication_password,
	INDEX_FREE9 AS biglobe_id,									
	TO_CHAR(macaddress_update_day, 'YYYYMMDD') AS macaddress_update_day,
	not_payment_target,
	event_date,	
	event_process
FROM
(SELECT
	ITEM_FREE56,
	PRIMARY_KEY2,
	LAG(ITEM_FREE51) OVER(ORDER BY PRIMARY_KEY2,min_update_date) AS macaddress,
	LAG(min_update_date) OVER(ORDER BY PRIMARY_KEY2,min_update_date) AS macaddress_register_date,
	LAG(ITEM_FREE51) OVER(ORDER BY PRIMARY_KEY2,min_update_date) AS authentication_id,
	INDEX_FREE9,
	not_payment_target,									
	min_update_date AS macaddress_update_day,	
	min_update_date AS event_date,
	--ITEM_FREE57
	DECODE(ITEM_FREE56,'delete','Delete' || delete_kind,'change','Change') || ITEM_FREE57 AS event_process
FROM TEMP_VIEW) tab
WHERE ITEM_FREE56 IN('delete','change') and macaddress is not null;


REMARK ##########ステップ３：更新イベントのうち新規登録レコードを登録##########

INSERT INTO wifi_register_device_event 
SELECT
	seq_wifi_register_device_event.NEXTVAL AS event_id,
	'insert' AS event_type,
	PRIMARY_KEY2 AS use_rights_id,
	ITEM_FREE51 AS macaddress,
	min_update_date AS macaddress_register_date,	
	ITEM_FREE51 AS authentication_id,
	ITEM_FREE51 AS authentication_password,
	INDEX_FREE9 AS biglobe_id,									
	TO_CHAR(min_update_date, 'YYYYMMDD') AS macaddress_update_day,
	not_payment_target,
	min_update_date AS event_date,	
	'Change' || ITEM_FREE57 AS event_process
FROM TEMP_VIEW
WHERE ITEM_FREE56='change';


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★移行用VIEWテーブル作成
REMARK ★利用権EVENTへの移行
REMARK ★登録端末EVENTへの移行
REMARK ★利用権STATEへの移行
REMARK 登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

INSERT INTO wifi_use_rights_state
SELECT 
	e1.use_rights_id,
	use_rights_contract_end_day,
	biglobe_id,
	last_update_month,
	change_count,
	use_rights_plan,
	bundle_type,
	contract_no,
	use_rights_status
FROM (SELECT use_rights_id, MAX(event_date) ed 
FROM wifi_use_rights_event GROUP BY use_rights_id) e1 JOIN wifi_use_rights_event e2 
ON e1.ed = e2.event_date 
AND e1.use_rights_id = e2.use_rights_id
AND e2.event_type != 'delete';


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★移行用VIEWテーブル作成
REMARK ★利用権EVENTへの移行
REMARK ★登録端末EVENTへの移行
REMARK ★利用権STATEへの移行
REMARK ★登録端末STATEへの移行
REMARK Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################


INSERT INTO wifi_register_device_state
SELECT 
	e1.use_rights_id,
	macaddress,
	macaddress_register_date,
	authentication_id,
	authentication_password
FROM (SELECT use_rights_id, MAX(event_date) ed 
FROM wifi_register_device_event GROUP BY use_rights_id) e1 JOIN wifi_register_device_event e2
ON e1.ed = e2.event_date 
AND e1.use_rights_id = e2.use_rights_id
AND e2.event_type != 'delete';


REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK ★テーブル作成
REMARK ★シーケンス作成
REMARK ★索引作成
REMARK ★移行用VIEWテーブル作成
REMARK ★利用権EVENTへの移行
REMARK ★登録端末EVENTへの移行
REMARK ★利用権STATEへの移行
REMARK ★登録端末STATEへの移行
REMARK ★Wi-Fi利用実績テーブルへの移行
REMARK 移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

INSERT INTO wifi_connection_state (carrier, macaddress, macaddress_used_date)
SELECT
	'wi2',
	item_free51,
	TO_DATE(MAX(item_free60), 'YYYYMMDDHH24MISS')
FROM scmg_ivt_contract_matl_hist
WHERE item_free51 IS NOT NULL
  AND item_free60 IS NOT NULL
GROUP BY item_free51;

commit;

spool off