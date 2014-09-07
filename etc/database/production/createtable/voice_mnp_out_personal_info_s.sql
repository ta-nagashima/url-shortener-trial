CREATE TABLE voice_mnp_out_personal_info_s (
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_full_name VARCHAR2(255) NOT NULL,
  mnp_out_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_out_birthday VARCHAR2(8) NOT NULL,
  mnp_out_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_psnl_inf_s PRIMARY KEY( voice_mnp_out_id )
  )