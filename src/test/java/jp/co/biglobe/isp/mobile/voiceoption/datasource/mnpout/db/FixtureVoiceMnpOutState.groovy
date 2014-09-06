package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceMnpOutState {

    private final static String tableName = "voice_mnp_out_state";


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
                        1 : MnpOutStateInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * 事務局にMNP予約番号発行を依頼待ち
         */
        public static Map getForRequestedWaiting(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "request_waiting")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData())
        }

        /**
         * 事務局にMNP予約番号発行を依頼済み
         */
        public static Map getForRequested(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_reservation_number_waiting")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData())
        }

        /**
         * 事務局がMNP予約番号を発行済み
         */
        public static Map getForNumbered(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_waiting")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData())
        }

        public static Map getForNumbered(DateTime dt){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_waiting")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData(dt))
        }

        /**
         * 転出完了済み　男
         */
        public static Map getForCompletion(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData()
                    + FixtureVoiceMnpOutCompleteState.One.getDefaultData())
        }


        /**
         * 転出完了済み（通話履歴）
         * Mnp転出完了日を20140701
         */
        public static Map getForCallHistoryTest(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData()
                    + FixtureVoiceMnpOutCompleteState.One.getForCallHisotryTest())
        }

        public static Map getForCallHistoryTest2(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData()
                    + FixtureVoiceMnpOutCompleteState.One.getForCallHisotryTest2())
        }

        /**
         * 転出完了済み 女
         */
        public static Map getForCompletionForFemale(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getForFemale()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData()
                    + FixtureVoiceMnpOutCompleteState.One.getDefaultData())
        }

        /**
         * 転出完了済み　不明
         */
        public static Map getForCompletionForUnknown(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_completion")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getForUnknown()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData()
                    + FixtureVoiceMnpOutCompleteState.One.getDefaultData())
        }




        /**
         * 事務局にMNP予約番号発行を依頼待ちからのキャンセル
         */
        public static Map getForCancelFromRequestedWaiting(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_cancel")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "user_request")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData())
        }

        /**
         * 事務局にMNP予約番号発行を依頼済みからのキャンセル
         */
        public static Map getForCancelFromRequested(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_cancel")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "user_request")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData())
        }

        /**
         * 事務局がMNP予約番号を発行済みからのキャンセル
         */
        public static Map getForCancelFromNumbered(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_status"), "mnp_out_cancel")
            map.put(new FixtureChangeTarget(tableName, 1, "cancel_reason"), "user_request")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.One.getDefaultData()
                    + FixtureVoiceMnpOutNumberState.One.getDefaultData())
        }

    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : MnpOutStateInitialData.firstRegistration,
                        2 : MnpOutStateInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * 過去に申し込み実績あり(MNP転出番号あり)
         */
        public static Map getRequstedWithMnp(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceMnpOutPersonalInfoState.Two.getForMnpOutRequestFromMnpOutCancel()
                    + FixtureVoiceMnpOutNumberState.One.forMnpOutRequestFromMnpOutCancel)
        }

    }
}
