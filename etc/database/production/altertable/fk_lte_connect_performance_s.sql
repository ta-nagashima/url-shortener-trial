ALTER TABLE lte_connect_performance_state ADD CONSTRAINT fk_lte_connect_performance_s FOREIGN KEY ( lte_three_g_engagement_number ) REFERENCES lte_contract_info( primary_key1 )