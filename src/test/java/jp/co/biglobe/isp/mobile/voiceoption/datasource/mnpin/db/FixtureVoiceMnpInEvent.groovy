package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpInEvent {

    private final static String tableName = "voice_mnp_in_event";


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
                        + MnpInInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value + FixtureVoiceMnpInPersonalInfoEvent.One.getDefaultData()
        }

        public static Map getProvisionalData(){
            return value
        }

        public static Map getForActivated(){
            return FixtureVoiceMnpInActivationEvent.One.getDefaultData()
        }

        /**
         * イベントタイプ DELETE
         */
        public static Map getForEventTypeToDelete(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return (Fixture.changeValueListForString(getProvisionalData(), map)
                    + FixtureVoiceMnpInPersonalInfoEvent.One.getForEventTypeToDelete())
        }

        /**
         * イベントタイプ DELETE
         */
        public static Map getForEventTypeToDeleteWithActivationDate(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return (Fixture.changeValueListForString(getProvisionalData(), map)
                    + FixtureVoiceMnpInPersonalInfoEvent.One.getForEventTypeToDelete()
                    + FixtureVoiceMnpInActivationEvent.One.getForEventTypeToDelete())
        }

        private final static Map valueSecond = [
                (tableName) :  [
                        1: ["EVENT_ID": "1", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpInInitialData.secondRegistration
                ]
        ]

        public static Map getForSecondData(){
            return valueSecond
        }

    }

}
