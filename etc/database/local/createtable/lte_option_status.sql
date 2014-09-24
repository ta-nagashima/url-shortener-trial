/**
LTE・3G契約テーブル
foreign_key1：契約番号
index_free1： 契約中のオプションコード

 */
CREATE TABLE lte_option_status (
  foreign_key1 VARCHAR2(256) NOT NULL,
  index_free1 VARCHAR2(256)
)