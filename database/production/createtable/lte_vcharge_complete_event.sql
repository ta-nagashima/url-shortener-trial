CREATE TABLE lte_vcharge_complete_event (
  vcharge_engagement_number VARCHAR2(8) NOT NULL,
  application_end_date_time DATE NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_lte_vcharge_complete_e PRIMARY KEY( vcharge_engagement_number )
  )