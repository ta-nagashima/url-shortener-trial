CREATE TABLE voice_mnp_out_personal_info_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_full_name VARCHAR2(255) NOT NULL,
  mnp_out_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_out_birthday VARCHAR2(8) NOT NULL,
  mnp_out_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_psnl_inf_e PRIMARY KEY( event_id )
  )