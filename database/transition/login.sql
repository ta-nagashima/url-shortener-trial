set linesize 450
set pagesize 60
set trim on
set trimspool on
set time on

column event_type format a8
column use_rights_id format a24
column biglobe_id format a12
column last_update_month format a6
column change_count format 9999
column use_rights_plan format a8
column bundle_type format a12
column contract_no format a12
column event_date format a18
column macaddress format a12
column AUTHENTICATION_ID format a64
column macaddress_update_day format a16
column MACADDRESS_REGISTER_DATE format a20
column EVENT_PROCESS format a20

column PRIMARY_KEY2 format a20
column INDEX_FREE6 format a12
column INDEX_FREE9 format a12
column INDEX_FREE4 format a12
column ITEM_FREE51 format a12
column ITEM_FREE52 format a12
column ITEM_FREE53 format a12
column ITEM_FREE54 format a12
column ITEM_FREE55 format a12
column ITEM_FREE56 format a12
column ITEM_FREE57 format a12
column ITEM_FREE58 format a12
column ITEM_FREE59 format a12
column ITEM_FREE6 format a12


alter session set nls_date_format = 'YYYY/MM/DD HH24:mi:ss';
