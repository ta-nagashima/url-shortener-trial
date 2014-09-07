CREATE TABLE voice_identification_state (
  identification_receipt_number VARCHAR2(11) NOT NULL, /* YYMMDD_0001（連番） */
  identification_status VARCHAR2(30) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  voice_clerk_request_status VARCHAR2(20) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  CONSTRAINT pk_voice_identification_state PRIMARY KEY( identification_receipt_number ),
  CONSTRAINT uk_voice_identification_state UNIQUE( voice_engagement_number )
  )