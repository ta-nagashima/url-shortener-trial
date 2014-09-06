package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db

import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db.FixtureVoiceIdentificationUploadE
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultEvent
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultNgEvent
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceIdentificationEvent {

    private final static String tableName = "voice_identification_event";


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
                                "EVENT_DATE"   : "2014-01-01 00:00:00",
                                "EVENT_PROCESS": " "
                        ] + IdentificationInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        /**
         * 仮受付状態
         */
        public static Map getForProvisional() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "unrequested")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 発送管理連携済み
         */
        public static Map getForProvisionalResume() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "requested")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForUnnecessaryProvisionalResume() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "u_shipped")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 発送管理連携不要
         */
        public static Map getForNoShipping() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "unnecessary")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForNoShippingProvisional() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "u_stop_shipping")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類の初回アップロード済み
         */
        public static Map getForFirstUploaded() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "identification_waiting")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * イベントタイプ DELETE
         */
        public static Map getForEventTypeToDelete() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

/**
 * エンティティごとのFixture
 */

    public static class OneEntity {

        private final static Map value = [
                (tableName): [
                        1: [
                                "EVENT_ID"     : "1",
                                "EVENT_TYPE"   : "insert",
                                "EVENT_DATE"   : "2014-01-01 00:00:00",
                                "EVENT_PROCESS": " "
                        ] + IdentificationInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForDocumentWaitingToOK() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultEvent.One.getForOKFromFax())
        }

        public static Map getForOutboundNowToOK2() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultEvent.OneEntityDeleteInsert.getForOBToOK()
                    + FixtureVoiceIdentificationResultNgEvent.OneDelete.getDefaultData())
        }

        public static Map getForOutboundNowToOutboundNow2() {
            return (FixtureVoiceIdentificationResultEvent.OneEntityDeleteInsert.getForOBToOB()
                    + FixtureVoiceIdentificationResultNgEvent.OneDeleteInsert.getForOBToOB())
        }


        public static Map getForIdentificationWaitingToOK() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultEvent.One.getForOKFromWeb())
        }

        public static Map getForIdentificationWaitingToOB() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultEvent.One.getForNGFromWeb()
                    + FixtureVoiceIdentificationResultNgEvent.One.getDefaultData()
                    + FixtureVoiceIdentificationUploadE.OneDelete.getForFirstUploaded())
        }

        public static Map getForDocumentWaitingToOB() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultEvent.One.getForNGFromFax()
                    + FixtureVoiceIdentificationResultNgEvent.One.getDefaultData())
        }


    }

}
