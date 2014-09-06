package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

class FixtureVoiceMnpOutPersonalInfoEvent {

    private final static String tableName = "voice_mnp_out_personal_info_e";


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
                                + MnpOutPersonalInfoStateInitialData.firstRegistration,
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * ユーザーからの転出申し込みを受付
         */
        public static Map getForMnpOutRequestFromNoRequest(){
            return value
        }


    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : ["EVENT_ID": "1", "EVENT_TYPE": "delete", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutPersonalInfoStateInitialData.firstRegistration,

                        2 : ["EVENT_ID": "2", "EVENT_TYPE": "insert", "EVENT_DATE": "2014-01-01 00:00:00", "EVENT_PROCESS": " "]
                                + MnpOutPersonalInfoStateInitialData.firstRegistration,
                ]
        ]

        public static Map getForMnpOutRequestFromMnpOutCancel(){
            return value
        }
    }
}
