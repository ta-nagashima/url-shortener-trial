package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

class FixtureVoiceMnpOutCompleteEvent {

    private final static String tableName = "voice_mnp_out_complete_event";


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
                                + MnpOutCompleteStateInitialData.firstRegistration,
                ]
        ]

        public static Map getDefaultData(){
            return value
        }
    }

}
