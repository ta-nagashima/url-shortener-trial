set linesize 300
set pagesize 50
set trim on
set trinspool on

alter session set nls_date_format='YYYY-MM-DD HH24:MI:SS'

-- 表の確認
select table_name from user_tables where table_name like 'VOICE%' order by 1;

-- 列の確認
select table_name || '.' || column_name from user_tab_columns where table_name like 'VOICE%' order by table_name;


-- シーケンスの確認
select sequence_name || ',' || min_value || ',' || max_value || ',' ||
increment_by || ',' || cycle_flag || ',' || last_number
from user_sequences where sequence_name like 'SEQ_VOICE%' order by 1;


-- 制約の確認
select CONSTRAINT_NAME || ',' || TABLE_NAME  || ',' || COLUMN_NAME
from user_cons_columns
where table_name like 'VOICE%' and SUBSTR(CONSTRAINT_NAME,0,2) in ('PK','UK','FK') order by 1

-- 制約の確認（参照制約）
select CONSTRAINT_NAME || ',' || TABLE_NAME || ',' || R_CONSTRAINT_NAME
from user_constraints
where table_name like 'VOICE%' and CONSTRAINT_TYPE = 'R'
order by 1

--　表の領域の確認
select table_name,tablespace_name,INITIAL_EXTENT,NEXT_EXTENT,PCT_INCREASE,MAX_EXTENTS
from user_tables where table_name like 'VOICE%' order by 1;

-- 索引の領域の確認
select index_name,table_name,tablespace_name,INITIAL_EXTENT,NEXT_EXTENT,PCT_INCREASE,MAX_EXTENTS
from user_indexes where table_name like 'VOICE%' order by 1;
