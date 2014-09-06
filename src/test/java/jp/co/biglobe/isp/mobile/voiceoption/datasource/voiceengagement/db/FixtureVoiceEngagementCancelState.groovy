package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceEngagementCancelState {

    private final static String tableName = "voice_engagement_cancel_state";


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
                        1 : VoiceEngagementCancelInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * 申込キャンセル 配送受取拒否
         */
        public static Map getForCancelForDelivery(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "delivery_receipt_refusal")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 申込キャンセル 退会_登録抹消_強制退会
         */
        public static Map getForCancelForWithdraw(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "withdraw_or_de_register")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 申込キャンセル コース変更
         */
        public static Map getForCancelForCourse(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "course_change")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 申込キャンセル 休会_サービス停止
         */
        public static Map getForCancelForAdjourn(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "adjourn_or_service_stop")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : VoiceEngagementCancelInitialData.firstRegistration,
                        2 : VoiceEngagementCancelInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }
    }
}
