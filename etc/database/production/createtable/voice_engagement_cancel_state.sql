CREATE TABLE voice_engagement_cancel_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  cancel_reason VARCHAR2(25) NOT NULL,
  cancel_sys_receipt_date_time DATE NOT NULL,
  cancel_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_cancel_state PRIMARY KEY( voice_engagement_number )
  )