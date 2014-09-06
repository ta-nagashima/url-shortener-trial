-- 通話履歴テスト用
-- 注意：必要最小限のカラムにしかインサートしない

/*
LTE・3G契約テーブル
primary_key1：契約番号
index_free1：BIGLOBE ID
index_free_date4：契約終了日
item_free4：契約ステータス（10：申込中、50：契約中、80：キャンセル、90：解約済み）
item_free11：現在またはプラン変更前のプラン
item_free12：現在またはプラン変更後のプラン
item_free72：プラン切り替え日
item_free32：新規会員か既存会員か（０：新規　１：既存）
*/

-- voice 201407~
insert into lte_contract_info(primary_key1, index_free1)
values( 'E0001', 'abc11111' );

-- sms-share 201406~
insert into lte_contract_info(primary_key1, index_free1)
values( 'E0002', 'abc11112' );

-- sms ~201406
insert into lte_contract_info(primary_key1, index_free1)
values( 'E0003', 'abc11113' );

-- sms 201406~msisdn
insert into lte_contract_info(primary_key1, index_free1)
values( 'E0004', 'abc11114' );

--sms multi-engagement
insert into lte_contract_info(primary_key1, index_free1)
values( 'E0005', 'abc11115' );
insert into lte_contract_info(primary_key1, index_free1)
values( 'E0006', 'abc11115' );

/**
LTE・3G利用機器テーブル
primary_key1：利用機器番号
foreign_key1：契約番号
item_free21：MSISDN（ハイフン付き　090-9999-9999）
item_free2：有効フラグ(0:無効、1:有効)
item_free_date4：機器利用開始日
item_free_date6：機器利用期限日時
 */

-- voice 201407~
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00001', 'E0001', '090-1111-1111', DATE'2014-07-01', null);

-- sms-share 201406~
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00002', 'E0002', '090-1111-1112', DATE'2014-06-01', null);
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00003', 'E0002', '090-1111-1113', DATE'2014-06-01', DATE'2014-06-30' );
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00004', 'E0002', '090-1111-1114', DATE'2014-07-01', null);

-- sms ~201406
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00005', 'E0003', '090-1111-1115', DATE'2014-06-19', DATE'2014-06-20' );

-- sms 201406~msisdn
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00006', 'E0004', '090-1111-1115', DATE'2014-06-21', null);

--sms multi-engagement
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00007', 'E0005', '090-1111-1116', DATE'2014-06-01', DATE'2014-06-10' );
insert into lte_usable_equip(primary_key1, foreign_key1, item_free21, item_free_date4, item_free_date6 )
values( 'U00008', 'E0006', '090-1111-1117', DATE'2014-06-21', null);



-- charge-master

/*
CREATE TABLE hist_charge_item_master
(
  charge_id                 NUMBER(24) NOT NULL,
  charge_cd                 VARCHAR2(24),
  charge_name               VARCHAR2(255) NOT NULL,
  domestic_or_international VARCHAR2(16) NOT NULL,
  communication_method      VARCHAR2(24) NOT NULL,
  tax_status                VARCHAR2(18) NOT NULL,
  charge_start_date         DATE NOT NULL,
  charge_end_date           DATE,
  CONSTRAINT pk_hist_charge_item_master PRIMARY KEY (charge_id),
  CONSTRAINT uk_hist_common_detail UNIQUE (domestic_or_international,
                                           communication_method)
)
*/

insert into hist_charge_item_master
values(1, '0A001', 'SMS DOMESTIC', 'domestic', 'sms', 'tax_free', DATE'2014-04-01', null);
insert into hist_charge_item_master
values(2, '0A002', 'SMS INTERNATIONAL', 'international', 'sms', 'tax_free', DATE'2014-04-01', null);
insert into hist_charge_item_master
values(3, '0A003', 'VOICE DOMESTIC', 'domestic', 'voice', 'tax_free', DATE'2014-07-01', null);
insert into hist_charge_item_master
values(4, '0A004', 'VOICE INTERNATIONAL', 'international', 'voice', 'tax_free', DATE'2014-07-01', null);
insert into hist_charge_item_master
values(5, '0A005', 'DOMMY SERIVICE', 'international', 'denwa', 'tax_free', DATE'2014-04-01', DATE'2014-06-30' );


-- hist_common_sum_bid
/*
CREATE TABLE hist_common_sum_bid
  (
     biglobe_id        VARCHAR2(12) NOT NULL,
     engagement_id     VARCHAR2(36) NOT NULL,
     charge_year_month VARCHAR2(6) NOT NULL,
     charge_id         NUMBER(24) NOT NULL,
     sum_duration_sec  NUMBER(24, 3),
     sum_ajt_charge    NUMBER(19, 0) NOT NULL,
     count_trans       NUMBER(19, 0),
     CONSTRAINT pk_hist_common_sum_bid PRIMARY KEY (biglobe_id, engagement_id,
     charge_year_month, charge_id)
  )
 */

insert into hist_common_sum_bid
values( 'abc11111', 'E0001', '201407', 3, 240.1, 4000, null);
insert into hist_common_sum_bid
values( 'abc11111', 'E0001', '201407', 4, 60.0, 360, null);
insert into hist_common_sum_bid
values( 'abc11111', 'E0001', '201407', 1, null, 30, null);

insert into hist_common_sum_bid
values( 'abc11112', 'E0002', '201406', 1, null, 30, null);
insert into hist_common_sum_bid
values( 'abc11112', 'E0002', '201407', 1, null, 180, null);
insert into hist_common_sum_bid
values( 'abc11112', 'E0002', '201407', 2, null, 240, null);

insert into hist_common_sum_bid
values( 'abc11113', 'E0003', '201406', 1, null, 900, null);

insert into hist_common_sum_bid
values( 'abc11114', 'E0004', '201406', 1, null, 1100, null);

insert into hist_common_sum_bid
values( 'abc11115', 'E0005', '201406', 1, null, 2400, null);
insert into hist_common_sum_bid
values( 'abc11115', 'E0006', '201406', 1, null, 90, null);

/*
CREATE TABLE hist_common_sum_msisdn
  (
     biglobe_id        VARCHAR2(12) NOT NULL,
     engagement_id     VARCHAR2(36) NOT NULL,
     msisdn            VARCHAR2(18) NOT NULL,
     charge_year_month VARCHAR2(6) NOT NULL,
     charge_id         NUMBER(24) NOT NULL,
     sum_duration_sec  NUMBER(24, 3),
     sum_ajt_charge    NUMBER(19, 0) NOT NULL,
     count_trans       NUMBER(19, 0),
     CONSTRAINT pk_hist_common_sum_msisdn PRIMARY KEY (biglobe_id, engagement_id
     , msisdn, charge_year_month, charge_id)
  )
 */
insert into hist_common_sum_msisdn
values( 'abc11111', 'E0001', '090-1111-1111', '201407', 3, 240.1, 4000, null);
insert into hist_common_sum_msisdn
values( 'abc11111', 'E0001', '090-1111-1111', '201407', 4, 60.0, 360, null);
insert into hist_common_sum_msisdn
values( 'abc11111', 'E0001', '090-1111-1111', '201407', 1, null, 30, null);

insert into hist_common_sum_msisdn
values( 'abc11112', 'E0002', '090-1111-1113', '201406', 1, null, 20, null);
insert into hist_common_sum_msisdn
values( 'abc11112', 'E0002', '090-1111-1112', '201406', 1, null, 10, null);
insert into hist_common_sum_msisdn
values( 'abc11112', 'E0002', '090-1111-1112', '201407', 1, null, 180, null);
insert into hist_common_sum_msisdn
values( 'abc11112', 'E0002', '090-1111-1112', '201407', 2, null, 240, null);

insert into hist_common_sum_msisdn
values( 'abc11113', 'E0003', '090-1111-1115', '201406', 1, null, 900, null);

insert into hist_common_sum_msisdn
values( 'abc11114', 'E0004', '090-1111-1115', '201406', 1, null, 1100, null);

insert into hist_common_sum_msisdn
values( 'abc11115', 'E0005', '090-1111-1116', '201406', 1, null, 2400, null);

insert into hist_common_sum_msisdn
values( 'abc11115', 'E0006', '090-1111-1116', '201406', 1, null, 900, null);
