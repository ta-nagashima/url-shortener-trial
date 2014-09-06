package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceOrderType
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceEngagementEvent {

    private final static String tableName = "voice_engagement_event";


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
                (tableName): [
                        1: ["EVENT_ID": "1", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + VoiceEngagementInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        public static Map getForDataToVoice(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_order_type"), VoiceOrderType.CHANGE_DATA_TO_VOICE.getDbValue())
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
         * 契約中
         */
        public static Map getForEngagement(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "engaged")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 契約中、contract_start_dateに特定の日時を指定
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
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageEvent.One.getDefaultData()
        }

        /**
         * イベントタイプ DELETE
         */
        public static Map getForEventTypeToDelete(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getDefaultData()
        }

        /**
         * 申込キャンセル（イベントタイプ:UPDATE)
         */
        public static Map getForCancelEventToUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "canceled")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelEvent.One.getDefaultData()
        }

        /**
         * キャンセルリスト出力（イベントタイプ:UPDATE)
         */
        public static Map getForCancelListOutputEventToUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_cancel_list_op_status"), "output")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForCancelListOutputEventToUpdate(DateTime dt){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_cancel_list_op_status"), "output")
            return Fixture.changeValueListForString(getForOrdered(dt), map)
        }

        /**
         * 契約中、contract_start_dateに特定の日時を指定（イベントタイプ:UPDATE)
         */
        public static Map getForEngagementEventToUpdate(DateTime dateTime){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_start_date"), dateTime.toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getForEngagement(), map)
        }

        /**
         * 転出なし解約予約中（イベントタイプ:UPDATE)
         */
        public static Map getForDisEngagementReservedEventToUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), new DateTime().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd 23:59:59"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageEvent.One.getDefaultData()
        }

        /**
         * 解約予約キャンセル（イベントタイプ:UPDATE)
         */
        public static Map getForDisEngagedCancelEventToUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "engaged")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageEvent.One.getForDisEngagedCancel()
        }

        /**
         * 即時解約（イベントタイプ:UPDATE)
         */
        public static Map getForImmediateDisEngagementEventToUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_engagement_status"), "disengaged")
            map.put(new FixtureChangeTarget(tableName, 1, "engagement_end_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementDisengageEvent.One.getDefaultData()
        }

        /**
         * Sim更新時の機器番号を更新完了（イベントタイプ:UPDATE)
         */
        public static Map getForChangeCompleteEventToUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "equipment_number"), "00000002")
            return Fixture.changeValueListForString(getForEngagement(), map)
        }


    }

}
