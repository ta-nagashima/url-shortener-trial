CREATE TABLE voice_first_month_charge_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  CONSTRAINT pk_voice_first_month_charge_e PRIMARY KEY( event_id ),
  CONSTRAINT uk_voice_first_month_charge_e UNIQUE ( voice_engagement_number )
  )