set trimspool on
set pagesize 0
set time off

spool check_authentication_id_password_20140202.log

select macaddress ||','|| authentication_id ||','|| authentication_password from wifi_register_device_state
order by 1;

spool off