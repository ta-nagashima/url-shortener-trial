CREATE TABLE voice_mnp_out_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_out_status VARCHAR2(40) NOT NULL,
  mnp_out_msisdn VARCHAR2(13) NOT NULL, /* 090-1234-5678 */
  cancel_reason VARCHAR2(18) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_event PRIMARY KEY( event_id )
  )
