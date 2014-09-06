package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceMnpInActivationEvent {

    private final static String tableName = "voice_mnp_in_activation_event";


    public static class Nothing {

        private final static Map value = [
                (tableName): [
                        1: [],
                ]
        ]

        public static Map getDefaultData() {
            return value
        }
    }


    public static class One {

        private final static Map value = [
                (tableName): [
                        1: ["EVENT_ID"     : "1",
                            "EVENT_TYPE"   : "insert",
                            "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                            "EVENT_PROCESS": " "]
                                + MnpInActivationInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForEventTypeToDelete() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }
}
