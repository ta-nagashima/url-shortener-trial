package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceIdentificationResultNgEvent {

    private final static String tableName = "voice_identification_ng_e";


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
                        1: [
                                "EVENT_ID"     : "1",
                                "EVENT_TYPE"   : "insert",
                                "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                                "EVENT_PROCESS": " "
                        ] + IdentificationResultNgInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

    }

    public static class OneDelete {

        private final static Map value = [
                (tableName): [
                        1: [
                                "EVENT_ID"     : "1",
                                "EVENT_TYPE"   : "delete",
                                "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                                "EVENT_PROCESS": " "
                        ] + IdentificationResultNgInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

    }




    public static class OneDeleteInsert {

        private final static Map value = [
                (tableName): [
                        1: [
                                "EVENT_ID"     : "1",
                                "EVENT_TYPE"   : "delete",
                                "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                                "EVENT_PROCESS": " "
                        ] + IdentificationResultNgInitialData.firstRegistration,
                        2: [
                                "EVENT_ID"     : "2",
                                "EVENT_TYPE"   : "insert",
                                "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                                "EVENT_PROCESS": " "
                        ] + IdentificationResultNgInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForOBToOB() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 2, "identification_result_id"), "2")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }



    }

}
