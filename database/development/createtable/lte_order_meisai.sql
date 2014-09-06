/**
オーダー明細テーブル
foreign_key2：商品機種コード
item_free11：登録＝１０、登録（破棄）＝８０
 */
CREATE TABLE lte_order_meisai (
  foreign_key2 VARCHAR2(256),
  item_free11 VARCHAR2(256)
)