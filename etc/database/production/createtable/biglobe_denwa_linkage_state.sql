CREATE TABLE biglobe_denwa_linkage_state(
	msisdn VARCHAR2(13) NOT NULL,
	linkage_status VARCHAR2(32) NOT NULL,
	notification VARCHAR(15) NOT NULL,
	update_date DATE NOT NULL,
	apply_channel VARCHAR2(28) NOT NULL,
	CONSTRAINT pk_biglobe_denwa_linkage_state PRIMARY KEY(msisdn)
)