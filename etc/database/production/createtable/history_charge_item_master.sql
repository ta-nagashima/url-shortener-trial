CREATE TABLE history_charge_item_master
  ( 
     charge_id                 NUMBER(24) NOT NULL, 
     charge_cd                 VARCHAR2(24), 
     charge_name               VARCHAR2(255) NOT NULL, 
     call_kind VARCHAR2(24) NOT NULL,
     communication_method      VARCHAR2(24) NOT NULL, 
     tax_status                VARCHAR2(18) NOT NULL,
     unit_price                VARCHAR2(255),
     charge_start_date         DATE NOT NULL, 
     charge_end_date           DATE, 
     CONSTRAINT pk_hist_charge_item_master PRIMARY KEY (charge_id)
  ) 