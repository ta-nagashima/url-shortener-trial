/**
 * voice_mnp_out_idを主キーにする理由は、転出キャンセル→再度転出申し込みがあったときに、どの転出でどのMNP転出番号が発行されたのかを
 * 記録しておくため
 */

CREATE TABLE voice_mnp_out_state (
  voice_mnp_out_id NUMBER(9) NOT NULL,
  voice_engagement_number NUMBER(9) NOT NULL,
  mnp_out_status VARCHAR2(40) NOT NULL,
  mnp_out_msisdn VARCHAR2(13) NOT NULL, /* 090-1234-5678 */
  cancel_reason VARCHAR2(18) NOT NULL,
  CONSTRAINT pk_voice_mnp_out_state PRIMARY KEY( voice_mnp_out_id ),
  CONSTRAINT uk_voice_mnp_out_state UNIQUE( voice_engagement_number )
  )
