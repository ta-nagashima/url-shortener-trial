/*
ALTER TABLE SAMPLE_USER MOVE TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );

ALTER INDEX SAMPLE_USER_NAME_SAMPLE_USER REBUILD TABLESPACE event_data01
            STORAGE(
            INITIAL      50M
            NEXT         50M
            PCTINCREASE 0
            MAXEXTENTS  UNLIMITED );
            */