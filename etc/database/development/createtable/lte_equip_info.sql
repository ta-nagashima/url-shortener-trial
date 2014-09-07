/**
LTE・3G機器マスタテーブル
primary_key1：商品機種コード
item_free2：削除フラグ(0:通常、1:削除)
item_free4：回線有無（0:回線無し、1:回線有り）
item_free47:音声利用有無（1:音声通話利用有り,null:音声通話利用無し）
 */
CREATE TABLE lte_equip_info (
  primary_key1 VARCHAR2(256) NOT NULL,
  item_free2 VARCHAR2(256),
  item_free4 VARCHAR2(256),
  item_free47 VARCHAR2(256),
  CONSTRAINT pk_lte_equip_info PRIMARY KEY(primary_key1)
)