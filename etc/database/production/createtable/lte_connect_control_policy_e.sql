CREATE TABLE lte_connect_control_policy_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  connect_control_policy VARCHAR2(30) NOT NULL,
  application_date_time DATE NOT NULL,
  CONSTRAINT pk_lte_connect_ctrl_policy_e PRIMARY KEY( event_id )
  )