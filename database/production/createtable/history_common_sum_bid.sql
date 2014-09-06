CREATE TABLE history_common_sum_bid
  ( 
     biglobe_id        VARCHAR2(12) NOT NULL, 
     engagement_id     VARCHAR2(36) NOT NULL, 
     use_month VARCHAR2(6) NOT NULL,
     charge_id         NUMBER(24) NOT NULL, 
     sum_duration_sec  NUMBER(24, 3), 
     sum_ajt_charge    NUMBER(19, 4) NOT NULL,
     call_or_send_count       NUMBER(19, 0),
     CONSTRAINT pk_hist_common_sum_bid PRIMARY KEY (biglobe_id, engagement_id, 
     use_month, charge_id)
  ) 