/**
 精算指定月＠＠201310＠＠
 有償MACアドレス取得
 */
/* ①指定月初時点で課金対象のMACアドレス（有償） */
set trimspool on
set time off
set termout off
spool new_macaddress_201310.log

SELECT DISTINCT macaddress
FROM wifi_register_device_event
WHERE event_type = 'insert'
AND (use_rights_id, event_date) IN (
	SELECT use_rights_id, MAX(event_date) FROM wifi_register_device_event
	WHERE event_date < TO_DATE('20131001000000', 'YYYYMMDDHH24MISS') GROUP BY use_rights_id
)
AND macaddress NOT IN (
	/* ①'指定月前月末に連動解約されたMACアドレスは有償の対象外とする */
	SELECT DISTINCT macaddress
	FROM wifi_register_device_event
	WHERE event_type = 'delete'
	AND not_payment_target = 'no_target'
	AND event_date BETWEEN TO_DATE('20131001000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20131001235959', 'YYYYMMDDHH24MISS')
)
UNION
(
	/* ②指定月に登録されたMACアドレス */
	SELECT THIS_MONTH_REGISTERD_MAC.macaddress
	FROM (
		/* 指定月に登録されたMACアドレス */
		SELECT DISTINCT macaddress
		FROM wifi_register_device_event
		WHERE event_type = 'insert'
		AND event_date BETWEEN TO_DATE('20131001000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20131031235959', 'YYYYMMDDHH24MISS')
	) THIS_MONTH_REGISTERD_MAC
	WHERE EXISTS (
		/* 指定月終了以前に解除されたことがあるMACアドレス */
		SELECT 'X'
		FROM (
			SELECT DISTINCT macaddress
			FROM wifi_register_device_event
			WHERE event_type = 'delete'
			AND event_date < TO_DATE('20131101000000', 'YYYYMMDDHH24MISS')
		) DELETED_MAC
		WHERE THIS_MONTH_REGISTERD_MAC.macaddress = DELETED_MAC.macaddress
	)
)
UNION
(
	/* ③指定月末に連動解約されたMACアドレスは有償の対象とする */
	SELECT DISTINCT macaddress
	FROM wifi_register_device_event
	WHERE event_type = 'delete'
	AND not_payment_target = 'no_target'
	AND event_date BETWEEN TO_DATE('20131101000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20131101235959', 'YYYYMMDDHH24MISS')
)
ORDER BY 1
;

spool off
set termout on
set time on