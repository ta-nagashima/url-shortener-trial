CREATE TABLE lte_connect_limit_history_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  reason VARCHAR2(30) NOT NULL,
  before_connect_control_policy VARCHAR2(30) NOT NULL,
  after_connect_control_policy VARCHAR2(30) NOT NULL,
  before_speed_limit_status VARCHAR2(10) NOT NULL,
  after_speed_limit_status VARCHAR2(10) NOT NULL,
  before_destination_lim_status VARCHAR2(30) NOT NULL,
  after_destination_lim_status VARCHAR2(30) NOT NULL,
  request_date_time DATE NOT NULL,
  CONSTRAINT pk_lte_connect_limit_history_e PRIMARY KEY( event_id )
  )