ALTER TABLE voice_identification_state ADD CONSTRAINT fk_voice_identification_s_num FOREIGN KEY ( voice_engagement_number ) REFERENCES voice_engagement_state( voice_engagement_number )
