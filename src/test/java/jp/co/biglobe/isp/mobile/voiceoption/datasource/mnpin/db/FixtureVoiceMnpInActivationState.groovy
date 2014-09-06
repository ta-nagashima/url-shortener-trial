package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

class FixtureVoiceMnpInActivationState {

    private final static String tableName = "voice_mnp_in_activation_state";


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
                        1: MnpInActivationInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

    }
}
