package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceEngagementDisengageEvent {

    private final static String tableName = "voice_engagement_disengage_e";


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
                                + VoiceEngagementDisengageInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * 解約予約キャンセル
         */
        public static Map getForDisEngagedCancel(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

}
