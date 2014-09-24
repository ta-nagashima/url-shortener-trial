CREATE TABLE voice_mnp_in_activation_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_activation_due_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_activate_state PRIMARY KEY( voice_engagement_number )
  )