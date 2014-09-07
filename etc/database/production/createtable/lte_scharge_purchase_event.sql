CREATE TABLE lte_scharge_purchase_event (
  scharge_engagement_number VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  order_date_time DATE NOT NULL,
  purchased_volume NUMBER(4) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_lte_scharge_purchase_e PRIMARY KEY( scharge_engagement_number )
  )