CREATE TABLE voice_engagement_cancel_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  cancel_reason VARCHAR2(25) NOT NULL,
  cancel_sys_receipt_date_time DATE NOT NULL,
  cancel_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_cancel_event PRIMARY KEY(event_id)
  )