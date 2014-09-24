CREATE TABLE voice_mnp_in_personal_info_s (
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_in_full_name VARCHAR2(255) NOT NULL,
  mnp_in_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_in_birthday VARCHAR2(8) NOT NULL,
  mnp_in_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_psnl_inf_s PRIMARY KEY( voice_engagement_number )
  )