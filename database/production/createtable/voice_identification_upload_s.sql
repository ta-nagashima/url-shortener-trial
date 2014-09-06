CREATE TABLE voice_identification_upload_s (
  identification_receipt_number VARCHAR2(11) NOT NULL, /* YYMMDD_0001（連番） */
  first_upload_date_time DATE NOT NULL,
  upload_count NUMBER(1) NOT NULL,
  CONSTRAINT pk_voice_identity_upload_s PRIMARY KEY( identification_receipt_number )
  )