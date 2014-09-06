package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceIdentificationResultEvent {

    private final static String tableName = "voice_identification_result_e";


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
                        ] + IdentificationResultInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        /**
         * 本人確認OK
         */

        public static Map getForOKFromFax() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "fax")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForOKFromWeb() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "web")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForNGFromWeb() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "web")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForNGFromFax() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "fax")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }




    }

    public static class OneEntityDeleteInsert {

        private final static Map value = [
                (tableName): [
                        1: [
                                "EVENT_ID"     : "1",
                                "EVENT_TYPE"   : "delete",
                                "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                                "EVENT_PROCESS": " "
                        ] + IdentificationResultInitialData.firstRegistration,
                        2: [
                                "EVENT_ID"     : "2",
                                "EVENT_TYPE"   : "insert",
                                "EVENT_DATE"   : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyy-MM-dd HH:mm:ss"),
                                "EVENT_PROCESS": " "
                        ] + IdentificationResultInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        /**
         * OB→OKの遷移
         */
        public static Map getForOBToOK() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "fax")
            map.put(new FixtureChangeTarget(tableName, 2, "document_acceptance_means"), "fax")
            map.put(new FixtureChangeTarget(tableName, 2, "identification_result_id"), "2")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * OB→OBの遷移
         */
        public static Map getForOBToOB() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "fax")
            map.put(new FixtureChangeTarget(tableName, 2, "document_acceptance_means"), "fax")
            map.put(new FixtureChangeTarget(tableName, 2, "identification_result_id"), "2")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

}
