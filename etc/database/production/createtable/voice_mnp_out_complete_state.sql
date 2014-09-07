CREATE TABLE voice_mnp_out_complete_state (
  voice_mnp_out_id NUMBER(9) NOT NULL, /* CONSTRAINT fk_voice_mnp_out_comp_s FOREIGN KEY ( voice_mnp_out_id ) REFERENCES voice_mnp_out_state2( voice_mnp_out_id ) */
  mnp_out_complete_confirm_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_comp_s PRIMARY KEY( voice_mnp_out_id )
  )
