/*---------------------------------------------------------------------------*/
/* 有償MACアドレス件数 */
/*---------------------------------------------------------------------------*/
/* ※指定月＝201312 */
/* ①.指定月時点で課金対象のMACアドレス（有償） */
set trimspool on
set time off
set termout off
spool old_macaddress_201312.log

SELECT
	DISTINCT ITEM_FREE51 AS MAC_LIST
FROM SCMG_IVT_CONTRACT_MATL_HIST
WHERE INDEX_FREE7 = 'BWIFI'
AND INDEX_FREE4 = '200'
AND INDEX_FREE6 = '1'
AND ITEM_FREE51 IS NOT NULL
AND HISTORY_SEQ IN (
	/* 指定月前月までの商材契約ID最新のデータ */
	SELECT MAX(A.HISTORY_SEQ) FROM SCMG_IVT_CONTRACT_MATL_HIST A
	WHERE A.INDEX_FREE7 = 'BWIFI'
	AND  (CASE 
			WHEN A.ITEM_FREE54 IS NULL         THEN A.ITEM_FREE52 
			WHEN A.ITEM_FREE52 < A.ITEM_FREE54 THEN A.ITEM_FREE54 
			ELSE                                    A.ITEM_FREE52 
		END) BETWEEN '00000000000000' AND '20131130235959'
	GROUP BY A.PRIMARY_KEY2
)
UNION
/* ②.指定月に登録されたMACアドレス（有償・無償含む） */
SELECT
	DISTINCT ITEM_FREE51 AS MAC_LIST
FROM SCMG_IVT_CONTRACT_MATL_HIST
WHERE INDEX_FREE7 = 'BWIFI'
AND INDEX_FREE4 = '200'
AND INDEX_FREE6 = '1'
AND ITEM_FREE51 IS NOT NULL
AND  (CASE 
		WHEN ITEM_FREE54 IS NULL       THEN ITEM_FREE52 
		WHEN ITEM_FREE52 < ITEM_FREE54 THEN ITEM_FREE54 
		ELSE                                ITEM_FREE52 
	END) BETWEEN '20131201000000' AND '20131231235959'
MINUS (
	/* ③.指定月に登録され、指定月末時点で登録中のMACアドレス（無償かも） */
	SELECT
		DISTINCT ITEM_FREE51 AS MAC_LIST
	FROM SCMG_IVT_CONTRACT_MATL_HIST
	WHERE INDEX_FREE7 = 'BWIFI'
	AND INDEX_FREE4 = '200'
	AND INDEX_FREE6 = '1'
	AND ITEM_FREE51 IS NOT NULL
	AND HISTORY_SEQ IN (
		/* 指定月に登録解除された商材契約ID最新のデータ */
		SELECT MAX(A.HISTORY_SEQ)
		FROM SCMG_IVT_CONTRACT_MATL_HIST A
		WHERE A.INDEX_FREE7 = 'BWIFI'
		AND  (CASE 
				WHEN A.ITEM_FREE54 IS NULL         THEN A.ITEM_FREE52 
				WHEN A.ITEM_FREE52 < A.ITEM_FREE54 THEN A.ITEM_FREE54 
				ELSE                                    A.ITEM_FREE52 
			END) BETWEEN '20131201000000' AND '20131231235959'
		GROUP BY A.PRIMARY_KEY2
	)
	MINUS
	/* ④.③リストの中で、指定月以前に登録されたことがあるMACアドレス（有償） */
	SELECT
		DISTINCT ITEM_FREE51 AS MAC_LIST
	FROM SCMG_IVT_CONTRACT_MATL_HIST
	WHERE INDEX_FREE7 = 'BWIFI'
	AND INDEX_FREE4 = '200'
	AND INDEX_FREE6 = '1'
	AND ITEM_FREE51 IS NOT NULL
	AND  (CASE 
			WHEN ITEM_FREE54 IS NULL       THEN ITEM_FREE52 
			WHEN ITEM_FREE52 < ITEM_FREE54 THEN ITEM_FREE54 
			ELSE                                ITEM_FREE52 
		END) BETWEEN '00000000000000' AND '20131130235959'
	AND ITEM_FREE51 IN (
		/* ③.指定月に登録され、指定月末時点で登録中のMACアドレス（無償かも） */
		SELECT
			DISTINCT B.ITEM_FREE51
		FROM SCMG_IVT_CONTRACT_MATL_HIST B
		WHERE B.INDEX_FREE7 = 'BWIFI'
		AND B.INDEX_FREE4 = '200'
		AND B.INDEX_FREE6 = '1'
		AND B.ITEM_FREE51 IS NOT NULL
		AND B.HISTORY_SEQ IN (
			/* 指定月に登録解除された商材契約ID最新のデータ */
			SELECT MAX(A.HISTORY_SEQ) FROM SCMG_IVT_CONTRACT_MATL_HIST A
			WHERE A.INDEX_FREE7 = 'BWIFI'
			AND  (CASE 
					WHEN A.ITEM_FREE54 IS NULL         THEN A.ITEM_FREE52 
					WHEN A.ITEM_FREE52 < A.ITEM_FREE54 THEN A.ITEM_FREE54 
					ELSE                                    A.ITEM_FREE52 
				END) BETWEEN '20131201000000' AND '20131231235959'
			GROUP BY A.PRIMARY_KEY2
		)
	)
)
UNION
/* ⑤指定月に登録され、指定月末に連動解約されたMACアドレス（有償） */
SELECT
	DISTINCT ITEM_FREE51 AS MAC_LIST
FROM SCMG_IVT_CONTRACT_MATL_HIST
WHERE HISTORY_SEQ IN (
	SELECT MAX(B.HISTORY_SEQ)
	FROM SCMG_IVT_CONTRACT_MATL_HIST B
	WHERE B.INDEX_FREE7 = 'BWIFI'
	AND B.INDEX_FREE4 = '200'
	AND B.INDEX_FREE6 = '1'
	AND B.ITEM_FREE51 IS NOT NULL
	AND B.PRIMARY_KEY2 IN (
		/* 指定月翌月月初に連動解約された商材契約ID */
		SELECT
			DISTINCT A.PRIMARY_KEY2
		FROM SCMG_IVT_CONTRACT_MATL_HIST A
		WHERE A.INDEX_FREE7 = 'BWIFI'
		AND A.INDEX_FREE4 = '800'
		AND A.ITEM_FREE59 IS NOT NULL
		AND A.ITEM_FREE54 LIKE '20140101%'
	)
	AND  (CASE 
			WHEN B.ITEM_FREE54 IS NULL         THEN B.ITEM_FREE52 
			WHEN B.ITEM_FREE52 < B.ITEM_FREE54 THEN B.ITEM_FREE54 
			ELSE                                    B.ITEM_FREE52 
		END) BETWEEN '20131201000000' AND '20131231235959'
	GROUP BY B.PRIMARY_KEY2
)
MINUS (
	/* ⑥指定月前月に連動解除されたMACアドレス（無償） */
	SELECT
		DISTINCT ITEM_FREE51 AS MAC_LIST
	FROM SCMG_IVT_CONTRACT_MATL_HIST
	WHERE HISTORY_SEQ IN (
		SELECT MAX(B.HISTORY_SEQ)
		FROM SCMG_IVT_CONTRACT_MATL_HIST B
		WHERE B.INDEX_FREE7 = 'BWIFI'
		AND B.INDEX_FREE4 = '200'
		AND B.INDEX_FREE6 = '1'
		AND B.ITEM_FREE51 IS NOT NULL
		AND B.PRIMARY_KEY2 IN (
			/* 指定月翌月月初に連動解約された商材契約ID */
			SELECT
				DISTINCT A.PRIMARY_KEY2
			FROM SCMG_IVT_CONTRACT_MATL_HIST A
			WHERE A.INDEX_FREE7 = 'BWIFI'
			AND A.INDEX_FREE4 = '800'
			AND A.ITEM_FREE59 IS NOT NULL
			AND A.ITEM_FREE54 LIKE '20131201%'
		)
		AND  (CASE 
				WHEN B.ITEM_FREE54 IS NULL         THEN B.ITEM_FREE52 
				WHEN B.ITEM_FREE52 < B.ITEM_FREE54 THEN B.ITEM_FREE54 
				ELSE                                    B.ITEM_FREE52 
			END) BETWEEN '00000000000000' AND '20131130235959'
		GROUP BY B.PRIMARY_KEY2
	)
	MINUS
	/* ⑦指定月前月に連動解除されたが、指定月に再登録されたMACアドレス（有償） */
	SELECT
		DISTINCT ITEM_FREE51 AS MAC_LIST
	FROM SCMG_IVT_CONTRACT_MATL_HIST
	WHERE HISTORY_SEQ IN (
		SELECT MAX(B.HISTORY_SEQ)
		FROM SCMG_IVT_CONTRACT_MATL_HIST B
		WHERE B.INDEX_FREE7 = 'BWIFI'
		AND B.INDEX_FREE4 = '200'
		AND B.INDEX_FREE6 = '1'
		AND B.ITEM_FREE51 IS NOT NULL
		AND B.ITEM_FREE51 IN (
			/* 指定月前月に連動解除されたMACアドレス */
			SELECT
				DISTINCT ITEM_FREE51 AS MAC_LIST
			FROM SCMG_IVT_CONTRACT_MATL_HIST
			WHERE HISTORY_SEQ IN (
				SELECT MAX(B.HISTORY_SEQ)
				FROM SCMG_IVT_CONTRACT_MATL_HIST B
				WHERE B.INDEX_FREE7 = 'BWIFI'
				AND B.INDEX_FREE4 = '200'
				AND B.INDEX_FREE6 = '1'
				AND B.ITEM_FREE51 IS NOT NULL
				AND B.PRIMARY_KEY2 IN (
					/* 指定月翌月月初に連動解約された商材契約ID */
					SELECT
						DISTINCT A.PRIMARY_KEY2
					FROM SCMG_IVT_CONTRACT_MATL_HIST A
					WHERE A.INDEX_FREE7 = 'BWIFI'
					AND A.INDEX_FREE4 = '800'
					AND A.ITEM_FREE59 IS NOT NULL
					AND A.ITEM_FREE54 LIKE '20131201%'
				)
				AND  (CASE 
						WHEN B.ITEM_FREE54 IS NULL         THEN B.ITEM_FREE52 
						WHEN B.ITEM_FREE52 < B.ITEM_FREE54 THEN B.ITEM_FREE54 
						ELSE                                    B.ITEM_FREE52 
					END) BETWEEN '00000000000000' AND '20131130235959'
				GROUP BY B.PRIMARY_KEY2
			)
		)
		AND  (CASE 
				WHEN B.ITEM_FREE54 IS NULL         THEN B.ITEM_FREE52 
				WHEN B.ITEM_FREE52 < B.ITEM_FREE54 THEN B.ITEM_FREE54 
				ELSE                                    B.ITEM_FREE52 
			END) BETWEEN '20131201000000' AND '20131231235959'
		GROUP BY B.PRIMARY_KEY2
	)
)
MINUS
/* ⑧他社登録済みエラーで登録されたかのようなMACアドレス */
SELECT
	DISTINCT ITEM_FREE51
FROM (
	SELECT
	  INDEX_FREE4
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
) A
WHERE (CASE 
		WHEN ITEM_FREE54 IS NULL       THEN ITEM_FREE52 
		WHEN ITEM_FREE52 < ITEM_FREE54 THEN ITEM_FREE54 
		ELSE                                ITEM_FREE52 
	END) BETWEEN '20131201000000' AND '20131231235959'
  AND INDEX_FREE4 = '200'
  AND PREV_SEQ IS NOT NULL
  AND NEXT_SEQ IS NOT NULL
  AND NVL(PREV_ITEM_FREE52, '99991231235959') = NVL(NEXT_ITEM_FREE52, '99991231235959')
  AND NVL(PREV_ITEM_FREE54, '99991231235959') = NVL(NEXT_ITEM_FREE54, '99991231235959')
  AND NVL(PREV_ITEM_FREE60, '99991231235959') = NVL(NEXT_ITEM_FREE60, '99991231235959')
UNION
/* ⑨.指定月に登録→解除→登録されたMACアドレス（有償）*/
SELECT
	DISTINCT NEW_MAC.ITEM_FREE51 AS ITEM_FREE51
FROM
(
	SELECT DISTINCT
		  B.ITEM_FREE51
		, (CASE WHEN B.ITEM_FREE54 IS NULL THEN B.ITEM_FREE52 WHEN B.ITEM_FREE52 < B.ITEM_FREE54 THEN B.ITEM_FREE54 ELSE B.ITEM_FREE52 END) AS UPDATE_DATE
	FROM SCMG_IVT_CONTRACT_MATL_HIST B
	WHERE B.INDEX_FREE7 = 'BWIFI'
	AND B.INDEX_FREE4 = '200'
	AND B.INDEX_FREE6 = '1'
	AND B.ITEM_FREE51 IS NOT NULL
	AND B.HISTORY_SEQ IN (
		/* 指定月に登録解除された商材契約ID最新のデータ */
		SELECT MAX(A.HISTORY_SEQ) FROM SCMG_IVT_CONTRACT_MATL_HIST A
		WHERE A.INDEX_FREE7 = 'BWIFI'
		AND  (CASE 
				WHEN A.ITEM_FREE54 IS NULL         THEN A.ITEM_FREE52 
				WHEN A.ITEM_FREE52 < A.ITEM_FREE54 THEN A.ITEM_FREE54 
				ELSE                                    A.ITEM_FREE52 
			END) BETWEEN '20131201000000' AND '20131231235959'
		GROUP BY A.PRIMARY_KEY2
	)
) NEW_MAC
,(
	SELECT DISTINCT
		  C.ITEM_FREE51
		, (CASE WHEN C.ITEM_FREE54 IS NULL THEN C.ITEM_FREE52 WHEN C.ITEM_FREE52 < C.ITEM_FREE54 THEN C.ITEM_FREE54 ELSE C.ITEM_FREE52 END) AS UPDATE_DATE
	FROM SCMG_IVT_CONTRACT_MATL_HIST C
	WHERE C.INDEX_FREE7 = 'BWIFI'
	AND C.INDEX_FREE4 = '200'
	AND C.INDEX_FREE6 = '1'
	AND C.ITEM_FREE51 IS NOT NULL
	AND C.HISTORY_SEQ IN (
		/* 指定月に登録されたMACアドレス最少のデータ */
		SELECT MIN(A.HISTORY_SEQ) FROM SCMG_IVT_CONTRACT_MATL_HIST A
		WHERE A.INDEX_FREE7 = 'BWIFI'
		AND A.INDEX_FREE4 = '200'
		AND A.INDEX_FREE6 = '1'
		AND A.ITEM_FREE51 IS NOT NULL
		AND  (CASE 
				WHEN A.ITEM_FREE54 IS NULL         THEN A.ITEM_FREE52 
				WHEN A.ITEM_FREE52 < A.ITEM_FREE54 THEN A.ITEM_FREE54 
				ELSE                                    A.ITEM_FREE52 
			END) BETWEEN '20131201000000' AND '20131231235959'
		GROUP BY A.ITEM_FREE51
	)
) OLD_MAC
WHERE NEW_MAC.ITEM_FREE51 = OLD_MAC.ITEM_FREE51
  AND NEW_MAC.UPDATE_DATE <> OLD_MAC.UPDATE_DATE
ORDER BY 1
;

spool off
set termout on
set time on