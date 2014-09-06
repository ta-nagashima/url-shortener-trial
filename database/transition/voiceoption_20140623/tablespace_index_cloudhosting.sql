ALTER INDEX pk_voice_cancel_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_cancel_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_disengage_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_disengage_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_engagement_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_engagement_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX uk_voice_engagement_equip_num REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_first_month_charge_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX uk_voice_first_month_charge_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identification_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identification_ng_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identification_ng_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identity_result_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identity_result_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX uk_voice_identity_result_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identification_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX uk_voice_identification_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identity_upload_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_identity_upload_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_in_activate_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_in_activate_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_in_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_in_psnl_inf_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_in_psnl_inf_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_in_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_charge_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX uk_voice_mnp_out_charge_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_comp_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_comp_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_event REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_num_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_num_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_psnl_inf_e REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_psnl_inf_s REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX pk_voice_mnp_out_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX uk_voice_mnp_out_state REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);


ALTER INDEX i_vis_lte_3g_engagement_num REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX i_ves_lte_3g_engagement_num REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
ALTER INDEX i_ves_user_id REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      1024
            NEXT         1024
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED
);
