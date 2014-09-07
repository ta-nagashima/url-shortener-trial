CREATE TABLE voice_identification_result_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_result_id NUMBER(9) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL, /* YYMMDD_0001（連番） */
  operator_id VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  document_acceptance_means VARCHAR2(18) NOT NULL,
  identification_doc_type VARCHAR2(50) NOT NULL,
  identification_sub_doc_type VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_voice_identity_result_e PRIMARY KEY( event_id )
  )