CREATE TABLE biglobe_denwa_linkage_event(
	event_id NUMBER NOT NULL,
	event_type VARCHAR2(32) NOT NULL,
    event_date DATE NOT NULL,
    event_process VARCHAR2(256) NOT NULL,
	msisdn VARCHAR2(13) NOT NULL,
	linkage_status VARCHAR2(32) NOT NULL,
	notification VARCHAR2(15) NOT NULL,
	update_date DATE NOT NULL,
	apply_channel VARCHAR2(28) NOT NULL,
	CONSTRAINT pk_biglobe_denwa_linkage_event PRIMARY KEY(event_id)
)