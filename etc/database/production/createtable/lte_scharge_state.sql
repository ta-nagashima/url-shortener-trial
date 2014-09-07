CREATE TABLE lte_scharge_state (
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  scharge_engagement_number VARCHAR2(8) NOT NULL,
  completion_status VARCHAR2(20) NOT NULL,
  update_date_time DATE NOT NULL,
  CONSTRAINT pk_lte_scharge_state PRIMARY KEY( lte_three_g_engagement_number )
  )