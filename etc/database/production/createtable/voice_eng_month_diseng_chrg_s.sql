CREATE TABLE voice_eng_month_diseng_chrg_s (
  voice_engagement_number NUMBER(9) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  item_code VARCHAR2(10) NOT NULL,
  fee NUMBER(4) NOT NULL,
  charge_date_time DATE NOT NULL,
  CONSTRAINT pk_voice_e_month_diseng_chrg_s PRIMARY KEY( voice_engagement_number )
  )