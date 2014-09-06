-- 3000-3999 BIGLOBE電話
insert into isp_api_enum values(3000, '/domain/mobile/biglobedenwa/operator/linkage/refer', '(参照-01)連携状態参照' , SYSDATE);
insert into isp_api_enum values(3001, '/domain/mobile/biglobedenwa/operator/linkage/withbirthday/refer', '(参照-02)連携状態参照(生年月日指定)' , SYSDATE);
insert into isp_api_enum values(3002, '/domain/mobile/biglobedenwa/operator/linkage/apply/submit', '(登録-01)利用申込' , SYSDATE);
insert into isp_api_enum values(3003, '/domain/mobile/biglobedenwa/operator/linkage/update/registeredinother/submit', '(更新-01)他社登録済更新' , SYSDATE);
insert into isp_api_enum values(3004, '/domain/mobile/biglobedenwa/operator/rocky/upload/submit', '(アップロード-01)連携結果取込' , SYSDATE);
insert into isp_api_enum values(3005, '/domain/mobile/biglobedenwa/skip/linkage/apply/submit', '(バッチ-登録-01)利用申込' , SYSDATE);
insert into isp_api_enum values(3006, '/domain/mobile/biglobedenwa/skip/linkage/update/waitingregisterresult/submit', '(バッチ-更新-01)登録連携中更新' , SYSDATE);
insert into isp_api_enum values(3007, '/domain/mobile/biglobedenwa/skip/linkage/update/registered/submit', '(バッチ-更新-02)登録済更新' , SYSDATE);
insert into isp_api_enum values(3008, '/domain/mobile/biglobedenwa/skip/linkage/update/unabletoregister/submit', '(バッチ-更新-03)登録NG・再登録連携前更新' , SYSDATE);
insert into isp_api_enum values(3009, '/domain/mobile/biglobedenwa/skip/linkage/update/waitingreregisterresult/submit', '(バッチ-更新-04)再登録連携中更新' , SYSDATE);
insert into isp_api_enum values(3010, '/domain/mobile/biglobedenwa/skip/linkage/update/waitingremove/submit', '(バッチ-更新-05)解除連携前更新' , SYSDATE);
insert into isp_api_enum values(3011, '/domain/mobile/biglobedenwa/skip/linkage/update/waitingremoveresult/submit', '(バッチ-更新-06)解除連携中更新' , SYSDATE);
insert into isp_api_enum values(3012, '/domain/mobile/biglobedenwa/skip/linkage/remove/submit', '(バッチ-解除-01)解除' , SYSDATE);
insert into isp_api_enum values(3013, '/domain/mobile/biglobedenwa/outside/user/linkage/refer', '(参照-03)外部向け連携状態参照' , SYSDATE);

insert into isp_api_client_enum values(3000,3000,'vasap01','/phone/status/','BIGLOBE電話利用可否参照',SYSDATE);
insert into isp_api_client_enum values(3001,3001,'vasap01','/phone/iosstatus/','BIGLOBE電話利用可否参照(IOS)',SYSDATE);
insert into isp_api_client_enum values(3002,3002,'vasap01','/phone/register/','BIGLOBEでんわ登録',SYSDATE);
insert into isp_api_client_enum values(3003,3003,'vasap01','/phone/otherregister/','BIGLOBEでんわ他社利用中',SYSDATE);
insert into isp_api_client_enum values(3004,3004,'vasap01','/operation/denwa-result-upload/inputsubmit','ROCKY連携結果取込',SYSDATE);

insert into isp_api_client_enum values(3005,3005,'Scenario','C_BGDW_LinkageUsr_tim_reg_act','BIGLOBEでんわ 連携申込(ユーザ向け発送SIM)バッチ',SYSDATE);
insert into isp_api_client_enum values(3006,3005,'Scenario','C_BGDW_LinkageStr_tim_reg_act','BIGLOBEでんわ 連携申込(店舗向け発送SIM)バッチ',SYSDATE);
insert into isp_api_client_enum values(3007,3006,'Scenario','C_BGDW_FileRegRmv_tim_send_act','BIGLOBEでんわ 登録/解約依頼ファイル送信バッチ',SYSDATE);
insert into isp_api_client_enum values(3008,3007,'Scenario','C_BGDW_FileResultOk_tim_recv_act','BIGLOBEでんわ 登録/解約依頼結果ファイル受信バッチ(OKファイル)',SYSDATE);
insert into isp_api_client_enum values(3009,3008,'Scenario','C_BGDW_FileResultNg_tim_recv_act','BIGLOBEでんわ 登録/解約依頼結果ファイル受信バッチ(NGファイル)',SYSDATE);
insert into isp_api_client_enum values(3010,3009,'Scenario','C_BGDW_FileRereg_tim_send_act','BIGLOBEでんわ 再登録依頼ファイル送信バッチ',SYSDATE);
insert into isp_api_client_enum values(3011,3010,'Scenario','C_BGDW_LinkageNonAct_tim_del_act','BIGLOBEでんわ 連携解除(無効SIM)バッチ',SYSDATE);
insert into isp_api_client_enum values(3012,3011,'Scenario','C_BGDW_FileRegRmv_tim_send_act','BIGLOBEでんわ 登録/解約依頼ファイル送信バッチ',SYSDATE);
insert into isp_api_client_enum values(3013,3012,'Scenario','C_BGDW_FileResultOk_tim_recv_act','BIGLOBEでんわ 登録/解約依頼結果ファイル受信バッチ(OKファイル)',SYSDATE);
insert into isp_api_client_enum values(3014,3012,'Scenario','C_BGDW_FileResultNg_tim_recv_act','BIGLOBEでんわ 登録/解約依頼結果ファイル受信バッチ(NGファイル)',SYSDATE);

insert into isp_api_client_enum values(3015,3013,'vasap01','/contract/mobile','MyBiglobeモバイル契約情報画面',SYSDATE);