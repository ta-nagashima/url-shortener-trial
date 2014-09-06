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
REMARK ★移行用VIEWテーブル削除
REMARK 登録端末EVENTの専用ID更新
REMARK 登録端末STATEの専用ID更新
REMARK ###############################################

spool transition_after_20140202.log

drop table temp_view;

spool off