package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db

class FixtureVoiceEngagementMonthDisengagementChargeState {
    private final static String tableName = "voice_eng_month_diseng_chrg_s";


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
                        1: EngagementMonthDisengagementChargeInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

    }

}
