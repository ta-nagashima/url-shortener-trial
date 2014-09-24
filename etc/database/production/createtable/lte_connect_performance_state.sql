CREATE TABLE lte_connect_performance_state (
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  limit_status_72hour VARCHAR2(10) NOT NULL,
  limit_status_1month VARCHAR2(10) NOT NULL,
  destination_status VARCHAR2(30) NOT NULL,
  volume_charge_status VARCHAR2(20) NOT NULL,
  speed_charge_status VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_lte_connect_performance_s PRIMARY KEY( lte_three_g_engagement_number )
  )