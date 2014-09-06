package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

class FixtureVoiceEngagementDisengageState {

    private final static String tableName = "voice_engagement_disengage_s";


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
                        1 : VoiceEngagementDisengageInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : VoiceEngagementDisengageInitialData.firstRegistration,
                        2 : VoiceEngagementDisengageInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }
    }
}
