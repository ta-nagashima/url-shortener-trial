ALTER TABLE voice_mnp_in_state ADD CONSTRAINT fk_voice_mnp_in_state_engage FOREIGN KEY ( voice_engagement_number ) REFERENCES voice_engagement_state( voice_engagement_number )
