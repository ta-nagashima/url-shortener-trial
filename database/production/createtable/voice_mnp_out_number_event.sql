CREATE TABLE voice_mnp_out_number_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_reservation_number VARCHAR2(15) NOT NULL, /* 00-111-12345 */
  expire_date VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_num_e PRIMARY KEY( event_id )
  )
