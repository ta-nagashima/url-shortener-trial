ALTER TABLE voice_mnp_in_personal_info_s ADD CONSTRAINT fk_voice_mnp_in_psnl_inf_s FOREIGN KEY ( voice_engagement_number ) REFERENCES voice_mnp_in_state( voice_engagement_number )
