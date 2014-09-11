/**
LTE・3G利用機器テーブル
primary_key1：利用機器番号
foreign_key1：契約番号
item_free21：MSISDN（ハイフン付き　090-9999-9999）
item_free2：有効フラグ(0:無効、1:有効)
item_free_date4：機器利用開始日
item_free_date6：機器利用期限日時
 */
CREATE TABLE lte_usable_equip (
  primary_key1 VARCHAR2(256) NOT NULL,
  foreign_key1 VARCHAR2(256) NOT NULL,
  item_free11 VARCHAR2(256),
  item_free21 VARCHAR2(256),
  item_free2 VARCHAR2(256),
  item_free3 VARCHAR2(256),
  item_free_date4 TIMESTAMP,
  item_free_date6 TIMESTAMP,
  item_free_date7 TIMESTAMP,
  CONSTRAINT pk_lte_usable_equip PRIMARY KEY(primary_key1)
)