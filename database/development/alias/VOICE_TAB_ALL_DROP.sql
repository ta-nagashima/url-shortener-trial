/*

CREATE OR REPLACE PROCEDURE voice_tab_all_drop
IS
  CURSOR c1 IS SELECT * FROM user_tables WHERE table_name like 'VOICE%';
  tbl varchar(30);
  stmt varchar(200);

BEGIN
  DBMS_OUTPUT.PUT_LINE('start...');
  FOR rec IN c1 LOOP

    tbl := rec.table_name;
    stmt := 'DROP TABLE ' || tbl || ' CASCADE CONSTRAINT';

    execute immediate stmt;
    END LOOP;
END;
/

*/
