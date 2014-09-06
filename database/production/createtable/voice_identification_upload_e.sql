CREATE TABLE voice_identification_upload_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL, /* YYMMDD_0001（連番） */
  first_upload_date_time DATE NOT NULL,
  upload_count NUMBER(1) NOT NULL,
  CONSTRAINT pk_voice_identity_upload_e PRIMARY KEY( event_id )
  )