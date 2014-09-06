CREATE TABLE voice_mnp_out_complete_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_complete_confirm_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_comp_e PRIMARY KEY( event_id )
  )
