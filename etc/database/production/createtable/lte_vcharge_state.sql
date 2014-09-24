CREATE TABLE lte_vcharge_state (
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  vcharge_engagement_number VARCHAR2(8) NOT NULL,
  completion_status VARCHAR2(20) NOT NULL,
  update_date_time DATE NOT NULL,
  CONSTRAINT pk_lte_volume_charge_s PRIMARY KEY( lte_three_g_engagement_number )
)