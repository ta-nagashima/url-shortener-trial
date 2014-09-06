package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.UploadLimitTerm
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceOrderType
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceEngagementState {

    private final static String tableName = "voice_engagement_state";


    public static class Nothing{

        private final static Map value = [
                (tableName) : [
                        1 : [],
                ]
        ]

        public static Map getDefaultData(){
            return value
        }
    }


    public static class One{

        private final static Map value = [
                (tableName) :  [
                        1 : VoiceEngagementInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * 申込キャンセル ユーザリクエスト
         */
        public static Map getForCancelForUserRequest(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "canceled")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getDefaultData()
        }

        /**
         * 申込キャンセル 配送受取拒否
         */
        public static Map getForCancelForDelivery(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "canceled")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getForCancelForDelivery()
        }

        /**
         * 申込キャンセル 退会_登録抹消_強制退会
         */
        public static Map getForCancelForWithdraw(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "canceled")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getForCancelForWithdraw()
        }

        /**
         * 申込キャンセル コース変更
         */
        public static Map getForCancelForCourse(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "canceled")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getForCancelForCourse()
        }

        /**
         * 申込キャンセル 休会_サービス停止
         */
        public static Map getForCancelForAdjourn(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "canceled")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getForCancelForAdjourn()
        }

        /**
         * 申込中
         */
        public static Map getForOrdered(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "ordered")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 申し込み中（申込日指定）
         */

        public static Map getForOrdered(DateTime dt){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "ordered")
            map.put(new FixtureChangeTarget(tableName, 1, "system_receipt_date_time"), dt.toString("yyyy-MM-dd HH:mm:ss"))
            map.put(new FixtureChangeTarget(tableName, 1, "voice_user_order_date"), dt.toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }


        /**
         * 申し込み中＋キャンセル依頼リスト出力済み
         */

        public static Map getForOrderedForOutput(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "ordered")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_cancel_list_op_status"), "output")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForOrderedForOutput(DateTime dt){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "ordered")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_cancel_list_op_status"), "output")
            return Fixture.changeValueListForString(getForOrdered(dt), map)
        }

        /**
         * 契約中
         */
        public static Map getForEngagement(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "engaged")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 契約中、engagement_start_dateに特定の日時を指定
         */
        public static Map getForEngagement(DateTime dateTime){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_start_date"), dateTime.toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getForEngagement(), map)
        }

        /**
         * 転出なし解約予約中
         */
        public static Map getForDisEngagementReserved(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), new DateTime().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd 23:59:59"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }

        /**
         * 転出なし解約済み
         */
        public static Map getForDisEngaged(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), new DateTime().minusDays(1).toString("yyyy-MM-dd 23:59:59"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }

        /**
         * 転出なし解約済み engagement_start_date、engagement_end_date_timeに特定の日時を指定
         */
        public static Map getForDisengaged(DateTime startDateTime, DateTime endDateTime){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_start_date"), startDateTime.toString("yyyyMMdd"))
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), endDateTime.toString("yyyy-MM-dd 23:59:59"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }

        /**
         * 即時解約
         */
        public static Map getForImmediateDisEngagement(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }

        /**
         * 即時解約 日付固定版
         */
        public static Map getForImmediateDisEngagement2(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), "2014-01-01 15:00:00")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }

        /**
         * 本人確認書類のアップロードが期限切れ直前（申込日）
         */
        public static Map getForInDeadLineUpload(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "system_receipt_date_time"), new DateTime().minusDays(UploadLimitTerm.CAN_UPLOAD_AFTER_DAY).toString("yyyy-MM-dd HH:mm:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "voice_user_order_date"), new DateTime().minusDays(UploadLimitTerm.CAN_UPLOAD_AFTER_DAY).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類のアップロードが期限切れ（申込日）
         */
        public static Map getForExpirationUpload(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "system_receipt_date_time"), new DateTime().minusDays(UploadLimitTerm.CAN_UPLOAD_AFTER_DAY + 1).toString("yyyy-MM-dd HH:mm:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "voice_user_order_date"), new DateTime().minusDays(UploadLimitTerm.CAN_UPLOAD_AFTER_DAY + 1).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * Sim更新時の機器番号を更新完了
         */
        public static Map getForChangeComplete(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "equipment_number"), "00000002")
            return Fixture.changeValueListForString(getForEngagement(), map)
        }

        public static Map getForDataToVoice() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_order_type"), VoiceOrderType.CHANGE_DATA_TO_VOICE.getDbValue())
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 通話履歴テスト用
         */
        public static Map getForCallHistoryTest() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "lte_three_g_engagement_number"), "E0001")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : VoiceEngagementInitialData.firstRegistration,
                        2 : VoiceEngagementInitialData.secondRegistration,
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        public static Map getForDefaultPlusDisEngaged(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 2, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 2, "engagement_end_date_time"), new DateTime().minusDays(1).toString("yyyy-MM-dd 23:59:59"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }

        public static Map getForDisEngagedPlusDefault(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), new DateTime().minusDays(1).toString("yyyy-MM-dd 23:59:59"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageState.One.getDefaultData()
        }


    }
}
