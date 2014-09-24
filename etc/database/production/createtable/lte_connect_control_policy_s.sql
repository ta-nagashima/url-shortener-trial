CREATE TABLE lte_connect_control_policy_s (
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  connect_control_policy VARCHAR2(30) NOT NULL,
  application_date_time DATE NOT NULL,
  CONSTRAINT pk_lte_connect_ctrl_policy_s PRIMARY KEY( lte_three_g_engagement_number )
  )