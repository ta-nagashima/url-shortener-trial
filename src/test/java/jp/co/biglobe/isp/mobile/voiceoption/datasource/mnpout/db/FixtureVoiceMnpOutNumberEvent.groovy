package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpOutNumberEvent {

    private final static String tableName = "voice_mnp_out_number_event";


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
                        1 : ["EVENT_ID": "1", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutNumberStateInitialData.firstRegistration,
                ]
        ]

        public static Map getDefaultData(){
            return value
        }


        /**
         * MNP転出番号発行　→　キャンセルされた状態からの転出再申し込み受付
         */
        public static Map getForMnpOutRequestFromMnpOutCancel() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 事務局にMNP予約番号発行を依頼済み
         */
        public static Map getForDelete(String num){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_mnp_out_id"), num)
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "delete")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

    public static class Two {

        private final static Map value = [
                (tableName): [
                        1: ["EVENT_ID": "1", "EVENT_TYPE": "delete", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutNumberStateInitialData.firstRegistration,
                        2: ["EVENT_ID": "2", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutNumberStateInitialData.firstRegistration,
                ]
        ]
    }
}
