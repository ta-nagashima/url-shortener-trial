CREATE TABLE voice_identification_ng_s (
  identification_result_id NUMBER(9) NOT NULL,
  ng_reason VARCHAR2(256) NOT NULL,
  ng_reason_detail VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_voice_identification_ng_s PRIMARY KEY( identification_result_id )
  )