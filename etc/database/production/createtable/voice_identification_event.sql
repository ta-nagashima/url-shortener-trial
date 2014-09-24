CREATE TABLE voice_identification_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL, /* YYMMDD_0001（連番） */
  identification_status VARCHAR2(30) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  voice_clerk_request_status VARCHAR2(20) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  CONSTRAINT pk_voice_identification_event PRIMARY KEY( event_id )
  )