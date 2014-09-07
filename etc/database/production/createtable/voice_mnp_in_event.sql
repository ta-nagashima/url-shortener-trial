CREATE TABLE voice_mnp_in_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  voice_msisdn VARCHAR2(13) NOT NULL, /* 090-1234-5678 */
  mnp_reservation_number VARCHAR2(15) NOT NULL, /* 00-111-12345 */
  CONSTRAINT pk_voice_mnp_in_event PRIMARY KEY( event_id )
  )