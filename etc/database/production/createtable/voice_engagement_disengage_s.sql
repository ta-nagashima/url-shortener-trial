CREATE TABLE voice_engagement_disengage_s (
  voice_engagement_number NUMBER(9) NOT NULL,
  disengage_reason VARCHAR2(25) NOT NULL,
  disengage_sys_receipt_date_t DATE NOT NULL,
  disengage_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_disengage_state PRIMARY KEY( voice_engagement_number )
  )