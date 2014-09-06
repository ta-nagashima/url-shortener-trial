package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpInPersonalInfoEvent {

    private final static String tableName = "voice_mnp_in_personal_info_e";


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
                        1: ["EVENT_ID": "1", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                        + MnpInPersonalInfoStateInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        public static Map getForUpdate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForEventTypeToDelete() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

}
