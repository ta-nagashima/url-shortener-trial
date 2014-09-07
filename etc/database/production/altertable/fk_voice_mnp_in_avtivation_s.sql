ALTER TABLE voice_mnp_in_activation_state ADD CONSTRAINT fk_voice_mnp_in_activation_s FOREIGN KEY ( voice_engagement_number ) REFERENCES voice_mnp_in_state( voice_engagement_number )
