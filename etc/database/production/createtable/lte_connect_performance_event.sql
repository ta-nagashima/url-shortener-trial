CREATE TABLE lte_connect_performance_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  limit_status_72hour VARCHAR2(10) NOT NULL,
  limit_status_1month VARCHAR2(10) NOT NULL,
  destination_status VARCHAR2(30) NOT NULL,
  volume_charge_status VARCHAR2(20) NOT NULL,
  speed_charge_status VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_lte_connect_performance_e PRIMARY KEY( event_id )
  )