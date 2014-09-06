CREATE TABLE voice_mnp_in_activation_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_activation_due_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_activate_event PRIMARY KEY( event_id )
  )