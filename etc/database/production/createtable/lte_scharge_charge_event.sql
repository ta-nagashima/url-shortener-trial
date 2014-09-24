CREATE TABLE lte_scharge_charge_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  scharge_engagement_number VARCHAR2(8) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  item_code VARCHAR2(10) NOT NULL,
  fee NUMBER(4) NOT NULL,
  CONSTRAINT pk_lte_scharge_charge_event PRIMARY KEY( event_id ),
  CONSTRAINT uk_lte_scharge_charge_event UNIQUE ( scharge_engagement_number )
  )