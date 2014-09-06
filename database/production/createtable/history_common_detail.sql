CREATE TABLE history_common_detail
  ( 
     history_id           NUMBER(19, 0) NOT NULL, 
     biglobe_id           VARCHAR2(12) NOT NULL, 
     engagement_id        VARCHAR2(36) NOT NULL, 
     msisdn               VARCHAR2(36) NOT NULL,
     start_date_time      DATE, 
     number_dialed        VARCHAR2(36),
     connect_country      VARCHAR2(255),
     charge_id            NUMBER(24) NOT NULL, 
     duration_sec         NUMBER(24, 3),
     charge_amount        NUMBER(19, 4) NOT NULL,
     adjust_charge_amount NUMBER(19, 4) NOT NULL,
     use_month    VARCHAR2(6) NOT NULL,
     CONSTRAINT pk_hist_common_detail PRIMARY KEY (history_id) 
  ) 