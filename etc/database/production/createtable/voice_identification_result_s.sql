/**
 * identification_result_idを主キーにする理由は、本人確認NG→本人確認NG→本人確認OKと遷移したときに、
 * どの本人確認業務でどのNG理由かを区別できるようにするため
 */

CREATE TABLE voice_identification_result_s (
  identification_result_id NUMBER(9) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL, /* YYMMDD_0001（連番） */
  operator_id VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  document_acceptance_means VARCHAR2(18) NOT NULL,
  identification_doc_type VARCHAR2(50) NOT NULL,
  identification_sub_doc_type VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_voice_identity_result_s PRIMARY KEY( identification_result_id ),
  CONSTRAINT uk_voice_identity_result_s UNIQUE( identification_receipt_number )
  )