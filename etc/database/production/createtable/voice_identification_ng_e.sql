CREATE TABLE voice_identification_ng_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_result_id NUMBER(9) NOT NULL,
  ng_reason VARCHAR2(256) NOT NULL,
  ng_reason_detail VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_voice_identification_ng_e PRIMARY KEY( event_id )
  )