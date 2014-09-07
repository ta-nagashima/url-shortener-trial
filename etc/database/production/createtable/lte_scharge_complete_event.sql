CREATE TABLE lte_scharge_complete_event (
  scharge_engagement_number VARCHAR2(8) NOT NULL,
  application_end_date_time DATE NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_lte_scharge_complete_e PRIMARY KEY( scharge_engagement_number )
  )