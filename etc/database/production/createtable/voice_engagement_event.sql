CREATE TABLE voice_engagement_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  system_receipt_date_time DATE NOT NULL,
  voice_engagement_status VARCHAR2(18) NOT NULL,
  voice_user_order_date VARCHAR2(8) NOT NULL,
  engagement_start_date VARCHAR2(8) NOT NULL,
  engagement_end_date_time DATE NOT NULL,
  voice_join_code VARCHAR2(256) NOT NULL,  /* 敗北感　LTE側も保持しているが、ノーガードなので、どんな値が来るかわからないため、256にしている */
  voice_cancel_list_op_status VARCHAR2(12) NOT NULL,
  voice_order_type VARCHAR2(24) NOT NULL,
  equipment_number VARCHAR2(8) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_engagement_event PRIMARY KEY(event_id)
  )