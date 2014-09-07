CREATE TABLE voice_mnp_in_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  voice_msisdn VARCHAR2(13) NOT NULL, /* 090-1234-5678 */
  mnp_reservation_number VARCHAR2(12) NOT NULL, /* 00-111-12345 */
  CONSTRAINT pk_voice_mnp_in_state PRIMARY KEY( voice_engagement_number )
  )