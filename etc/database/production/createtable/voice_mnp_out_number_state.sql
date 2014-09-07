CREATE TABLE voice_mnp_out_number_state (
  voice_mnp_out_id NUMBER(9) NOT NULL, /* CONSTRAINT fk_voice_mnp_out_num_s FOREIGN KEY ( voice_mnp_out_id ) REFERENCES voice_mnp_out_state2( voice_mnp_out_id ) */
  mnp_reservation_number VARCHAR2(15) NOT NULL, /* 00-111-12345 */
  expire_date VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_num_s PRIMARY KEY( voice_mnp_out_id )
  )
