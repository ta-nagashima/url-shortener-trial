package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceIdentificationUploadE {

    private final static String tableName = "voice_identification_upload_e";


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
                (tableName) :  [
                        1: [
                                "EVENT_ID": "1",
                                "EVENT_TYPE": "insert",
                                "EVENT_DATE": "2014-01-01 00:00:00",
                                "EVENT_PROCESS": " "
                        ] + IdentificationUploadInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        /**
         * 本人確認書類の初回アップロード済み
         */
        public static Map getForFirstUploaded() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 1)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類の２回目アップロード済み
         */
        public static Map getForSecondUploaded() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 2)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類の３回目アップロード済み
         */
        public static Map getForMaxUploadCount() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 3)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

    public static class OneDelete {

        private final static Map value = [
                (tableName): [
                        1: [
                                "EVENT_ID"  : "1",
                                "EVENT_TYPE": "delete",
                                "EVENT_DATE": "2014-01-01 00:00:00",
                                "EVENT_PROCESS": " "
                        ] + IdentificationUploadInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForFirstUploaded() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 1)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }
}