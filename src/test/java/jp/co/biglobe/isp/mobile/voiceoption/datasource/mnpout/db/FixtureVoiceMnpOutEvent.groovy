package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpOutEvent {

    private final static String tableName = "voice_mnp_out_event";


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
                                + MnpOutStateInitialData.firstRegistration,
                ]
        ]



        public static Map getDefaultData(){
            return value
        }

        /**
         * ユーザーからの転出申し込みを受付
         */
        public static Map getForMnpOutRequestFromNoRequest(){
            return value + FixtureVoiceMnpOutPersonalInfoEvent.One.getForMnpOutRequestFromNoRequest()
        }

        /**
         * 事務局にMNP予約番号発行を依頼済み
         */
        public static Map getForRequested(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_reservation_number_waiting")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 事務局がMNP予約番号発行済み
         */
        public static Map getForNumbered(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_waiting")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceMnpOutNumberEvent.One.getDefaultData()
        }

        /**
         * 転出完了済み
         */
        public static Map getForCompletion(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceMnpOutCompleteEvent.One.getDefaultData()
        }

        /**
         * 転出キャンセル
         */
        public static Map getForCancel(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "EVENT_TYPE"), "update")
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_cancel")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "user_request")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

    /**
     * キャンセルからの転出再申し込みのためのデータ
     */

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : ["EVENT_ID": "1", "EVENT_TYPE": "delete", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutStateInitialData.firstRegistration,

                        2 : ["EVENT_ID": "2", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutStateInitialData.firstRegistration,
                ]
        ]

        public static Map getDefaultData(){
            return value
        }


        /**
         * MNP転出番号発行　→　キャンセルされた状態からの転出再申し込み受付
         */
        public static Map getForMnpOutRequestFromMnpOutCancel(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_cancel")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "user_request")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoEvent.Two.getForMnpOutRequestFromMnpOutCancel()
                    + FixtureVoiceMnpOutNumberEvent.One.getForMnpOutRequestFromMnpOutCancel())
        }

        /**
         * 転出キャンセル
         */
        public static Map getForCancel(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_cancel")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "user_request")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 過去に申し込み実績あり(MNP転出番号なし)
         */
        public static Map getRequstedNoMnp(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "request_waiting")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "not_cancel")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoEvent.Two.getForMnpOutRequestFromMnpOutCancel())
        }

        /**
         * 過去に申し込み実績あり(MNP転出番号あり)
         */
        public static Map getRequstedWithMnp(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "request_waiting")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "not_cancel")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoEvent.Two.getForMnpOutRequestFromMnpOutCancel()
                    + FixtureVoiceMnpOutNumberEvent.One.getForMnpOutRequestFromMnpOutCancel())
        }

    }

}
