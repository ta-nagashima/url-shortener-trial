﻿REMARK ###############################################
REMARK ★不整合履歴データ物理削除
REMARK テーブル作成
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

spool transition_before_delete_gomidata.log

DELETE FROM wifi_register_device_event;
DELETE FROM wifi_register_device_state;
DELETE FROM wifi_use_rights_event;
DELETE FROM wifi_use_rights_state;
DELETE FROM wifi_connection_state;

DELETE FROM SCMG_IVT_CONTRACT_MATL_HIST A
WHERE A.HISTORY_SEQ IN (
	SELECT
		HISTORY_SEQ
	FROM (
		SELECT
		  HISTORY_SEQ
		, INDEX_FREE4
		, ITEM_FREE51
		, ITEM_FREE52
		, ITEM_FREE54
		, ITEM_FREE60
		, LAG( HISTORY_SEQ )  OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS PREV_SEQ
		, LAG( ITEM_FREE52 )  OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS PREV_ITEM_FREE52
		, LAG( ITEM_FREE54 )  OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS PREV_ITEM_FREE54
		, LAG( ITEM_FREE60 )  OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS PREV_ITEM_FREE60
		, LEAD( HISTORY_SEQ ) OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS NEXT_SEQ
		, LEAD( ITEM_FREE52 ) OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS NEXT_ITEM_FREE52
		, LEAD( ITEM_FREE54 ) OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS NEXT_ITEM_FREE54
		, LEAD( ITEM_FREE60 ) OVER( PARTITION BY PRIMARY_KEY2 ORDER BY HISTORY_SEQ ) AS NEXT_ITEM_FREE60
		FROM SCMG_IVT_CONTRACT_MATL_HIST
		WHERE INDEX_FREE7 = 'BWIFI'
	)
	WHERE INDEX_FREE4 = '200'
	  AND PREV_SEQ IS NOT NULL
	  AND NEXT_SEQ IS NOT NULL
	  AND NVL(PREV_ITEM_FREE52, '99991231235959') = NVL(NEXT_ITEM_FREE52, '99991231235959')
	  AND NVL(PREV_ITEM_FREE54, '99991231235959') = NVL(NEXT_ITEM_FREE54, '99991231235959')
	  AND NVL(PREV_ITEM_FREE60, '99991231235959') = NVL(NEXT_ITEM_FREE60, '99991231235959')
);


DELETE FROM SCMG_IVT_CONTRACT_MATL_HIST
WHERE PRIMARY_KEY2 in('WF00120130731023001','WF00120130801017001','WF00120130801028001','WF00120130802199001','WF00120130805022001');

spool off
