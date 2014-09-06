CREATE TABLE voice_engagement_cancel_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  cancel_reason VARCHAR2(25) NOT NULL,
  cancel_sys_receipt_date_time DATE NOT NULL,
  cancel_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_cancel_event PRIMARY KEY(event_id)
  );

CREATE TABLE voice_engagement_cancel_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  cancel_reason VARCHAR2(25) NOT NULL,
  cancel_sys_receipt_date_time DATE NOT NULL,
  cancel_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_cancel_state PRIMARY KEY( voice_engagement_number )
  );

CREATE TABLE voice_engagement_disengage_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  disengage_reason VARCHAR2(25) NOT NULL,
  disengage_sys_receipt_date_t DATE NOT NULL,
  disengage_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_disengage_event PRIMARY KEY(event_id)
  );

CREATE TABLE voice_engagement_disengage_s (
  voice_engagement_number NUMBER(9) NOT NULL,
  disengage_reason VARCHAR2(25) NOT NULL,
  disengage_sys_receipt_date_t DATE NOT NULL,
  disengage_user_order_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_disengage_state PRIMARY KEY( voice_engagement_number )
  );

CREATE TABLE voice_engagement_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  system_receipt_date_time DATE NOT NULL,
  voice_engagement_status VARCHAR2(18) NOT NULL,
  voice_user_order_date VARCHAR2(8) NOT NULL,
  engagement_start_date VARCHAR2(8) NOT NULL,
  engagement_end_date_time DATE NOT NULL,
  voice_join_code VARCHAR2(256) NOT NULL,
  equipment_number VARCHAR2(8) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_engagement_event PRIMARY KEY(event_id)
  );

CREATE TABLE voice_engagement_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  system_receipt_date_time DATE NOT NULL,
  voice_engagement_status VARCHAR2(18) NOT NULL,
  voice_user_order_date VARCHAR2(8) NOT NULL,
  engagement_start_date VARCHAR2(8) NOT NULL,
  engagement_end_date_time DATE NOT NULL,
  voice_join_code VARCHAR2(256) NOT NULL,
  equipment_number VARCHAR2(8) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_engagement_state PRIMARY KEY(voice_engagement_number),
  CONSTRAINT uk_voice_engagement_equip_num UNIQUE ( equipment_number )
  );

CREATE TABLE voice_first_month_charge_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  CONSTRAINT pk_voice_first_month_charge_e PRIMARY KEY( event_id ),
  CONSTRAINT uk_voice_first_month_charge_e UNIQUE ( voice_engagement_number )
  );

CREATE TABLE voice_identification_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL,
  identification_status VARCHAR2(30) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  voice_clerk_request_status VARCHAR2(20) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  CONSTRAINT pk_voice_identification_event PRIMARY KEY( event_id )
  );

CREATE TABLE voice_identification_ng_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_result_id NUMBER(9) NOT NULL,
  ng_reason VARCHAR2(256) NOT NULL,
  ng_reason_detail VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_voice_identification_ng_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_identification_ng_s (
  identification_result_id NUMBER(9) NOT NULL,
  ng_reason VARCHAR2(256) NOT NULL,
  ng_reason_detail VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_voice_identification_ng_s PRIMARY KEY( identification_result_id )
  );

CREATE TABLE voice_identification_result_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_result_id NUMBER(9) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  document_acceptance_means VARCHAR2(18) NOT NULL,
  identification_doc_type VARCHAR2(50) NOT NULL,
  identification_sub_doc_type VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_voice_identity_result_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_identification_result_s (
  identification_result_id NUMBER(9) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  document_acceptance_means VARCHAR2(18) NOT NULL,
  identification_doc_type VARCHAR2(50) NOT NULL,
  identification_sub_doc_type VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_voice_identity_result_s PRIMARY KEY( identification_result_id ),
  CONSTRAINT uk_voice_identity_result_s UNIQUE( identification_receipt_number )
  );

CREATE TABLE voice_identification_state (
  identification_receipt_number VARCHAR2(11) NOT NULL,
  identification_status VARCHAR2(30) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  lte_three_g_engagement_number VARCHAR2(8) NOT NULL,
  voice_clerk_request_status VARCHAR2(20) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  CONSTRAINT pk_voice_identification_state PRIMARY KEY( identification_receipt_number ),
  CONSTRAINT uk_voice_identification_state UNIQUE( voice_engagement_number )
  );

CREATE TABLE voice_identification_upload_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  identification_receipt_number VARCHAR2(11) NOT NULL,
  first_upload_date_time DATE NOT NULL,
  upload_count NUMBER(1) NOT NULL,
  CONSTRAINT pk_voice_identity_upload_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_identification_upload_s (
  identification_receipt_number VARCHAR2(11) NOT NULL,
  first_upload_date_time DATE NOT NULL,
  upload_count NUMBER(1) NOT NULL,
  CONSTRAINT pk_voice_identity_upload_s PRIMARY KEY( identification_receipt_number )
  );

CREATE TABLE voice_mnp_in_activation_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_activation_due_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_activate_event PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_in_activation_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_activation_due_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_activate_state PRIMARY KEY( voice_engagement_number )
  );

CREATE TABLE voice_mnp_in_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  voice_msisdn VARCHAR2(13) NOT NULL,
  mnp_reservation_number VARCHAR2(15) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_event PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_in_personal_info_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_in_full_name VARCHAR2(255) NOT NULL,
  mnp_in_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_in_birthday VARCHAR2(8) NOT NULL,
  mnp_in_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_psnl_inf_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_in_personal_info_s (
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_in_full_name VARCHAR2(255) NOT NULL,
  mnp_in_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_in_birthday VARCHAR2(8) NOT NULL,
  mnp_in_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_psnl_inf_s PRIMARY KEY( voice_engagement_number )
  );

CREATE TABLE voice_mnp_in_state (
  voice_engagement_number NUMBER(9) NOT NULL,
  voice_msisdn VARCHAR2(13) NOT NULL,
  mnp_reservation_number VARCHAR2(12) NOT NULL,
  CONSTRAINT pk_voice_mnp_in_state PRIMARY KEY( voice_engagement_number )
  );

CREATE TABLE voice_mnp_out_charge_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  user_id VARCHAR2(8) NOT NULL,
  item_code VARCHAR2(10) NOT NULL,
  fee NUMBER(4) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_charge_event PRIMARY KEY( event_id ),
  CONSTRAINT uk_voice_mnp_out_charge_event UNIQUE ( voice_engagement_number )
  );

CREATE TABLE voice_mnp_out_complete_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_complete_confirm_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_comp_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_out_complete_state (
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_complete_confirm_date VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_comp_s PRIMARY KEY( voice_mnp_out_id )
  );

CREATE TABLE voice_mnp_out_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_out_status VARCHAR2(40) NOT NULL,
  mnp_out_msisdn VARCHAR2(13) NOT NULL,
  cancel_reason VARCHAR2(18) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_event PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_out_number_event (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_reservation_number VARCHAR2(15) NOT NULL,
  expire_date VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_num_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_out_number_state (
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_reservation_number VARCHAR2(15) NOT NULL,
  expire_date VARCHAR2(8) NOT NULL,
  execution_date VARCHAR2(8) NOT NULL,
  operator_id VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_num_s PRIMARY KEY( voice_mnp_out_id )
  );

CREATE TABLE voice_mnp_out_personal_info_e (
  event_id NUMBER NOT NULL,
  event_type VARCHAR2(32) NOT NULL,
  event_date DATE NOT NULL,
  event_process VARCHAR2(256) NOT NULL,
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_full_name VARCHAR2(255) NOT NULL,
  mnp_out_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_out_birthday VARCHAR2(8) NOT NULL,
  mnp_out_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_psnl_inf_e PRIMARY KEY( event_id )
  );

CREATE TABLE voice_mnp_out_personal_info_s (
  voice_mnp_out_id NUMBER(9) NOT NULL,
  mnp_out_full_name VARCHAR2(255) NOT NULL,
  mnp_out_full_name_kana VARCHAR2(255) NOT NULL,
  mnp_out_birthday VARCHAR2(8) NOT NULL,
  mnp_out_gender VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_psnl_inf_s PRIMARY KEY( voice_mnp_out_id )
  );

CREATE TABLE voice_mnp_out_state (
  voice_mnp_out_id NUMBER(9) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_out_status VARCHAR2(40) NOT NULL,
  mnp_out_msisdn VARCHAR2(13) NOT NULL,
  cancel_reason VARCHAR2(18) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_state PRIMARY KEY( voice_mnp_out_id ),
  CONSTRAINT uk_voice_mnp_out_state UNIQUE( voice_engagement_number )
);
