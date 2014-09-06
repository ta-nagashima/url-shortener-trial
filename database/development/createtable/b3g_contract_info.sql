/**
3G契約テーブル
primary_key1：契約番号
index_free1：BIGLOBE ID
index_free_date4：契約終了日
item_free4：契約ステータス（10：申込中、50：契約中、80：キャンセル、90：解約済み）
item_free11：現在またはプラン変更前のプラン
item_free12：現在またはプラン変更後のプラン
item_free72：プラン切り替え日
item_free32：新規会員か既存会員か（０：新規　１：既存）

tem_free11      | item_free12      | item_free72
-----------------------------------------------------------------
(NULL)          | LTA00001         |  (NULL)            …①
LTA00003        | LTA00001         | 20130617　         …②
LTA00001        | LTA00006         | 20140401　       　…③

①…コース変更の予約なし（今のプランはLTA00001 = LTEスタンダード）
②…切り替え日が過去（今のプランはLTA00001 = LTEスタンダード　20130617までのプランはLTA00003　＝　ライトM）
③…切り替え日が未来＝予約中（今のプランはLTA00001 = LTEスタンダード　20140401からはLTA00006　＝　エントリー）

 */
CREATE TABLE b3g_contract_info (
  primary_key1 VARCHAR2(256) NOT NULL,
  index_free1 VARCHAR2(256),
  index_free_date4 DATE,
  item_free3 VARCHAR2(256),
  item_free4 VARCHAR2(256),
  item_free11 VARCHAR2(256),
  item_free12 VARCHAR2(256),
  item_free72 VARCHAR2(256),
  item_free32 VARCHAR2(256),
  CONSTRAINT pk_b3g_contract_info PRIMARY KEY(primary_key1)
)