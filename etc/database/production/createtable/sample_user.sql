CREATE TABLE sample_user
(
    sample_user_id NUMBER(10) ,
    sample_user_name VARCHAR2(255) NOT NULL,
    sample_gender VARCHAR2(28) NOT NULL,
    CONSTRAINT pk_sample_user PRIMARY KEY( sample_user_id )
)