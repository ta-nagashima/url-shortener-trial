ALTER TABLE voice_mnp_out_number_state ADD CONSTRAINT fk_voice_mnp_out_num_s FOREIGN KEY ( voice_mnp_out_id ) REFERENCES voice_mnp_out_state( voice_mnp_out_id )