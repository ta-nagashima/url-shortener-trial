package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.FixtureVoiceEngagementCancelState
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpInState {

    private final static String tableName = "voice_mnp_in_state";


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
                        1 : MnpInInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value + FixtureVoiceMnpInPersonalInfoState.One.getDefaultData()
        }

        public static Map getProvisionalData(){
            return value
        }

        /**
         * 仮受付状態
         */
        public static Map getForProvisional(){
            return value
        }

        /**
         * 本受付状態 男
         */
        public static Map getForConstancyForMale(){
            return value + FixtureVoiceMnpInPersonalInfoState.One.getDefaultData()
        }

        /**
         * 本受付状態 女
         */
        public static Map getForConstancyForFemale(){
            return value + FixtureVoiceMnpInPersonalInfoState.One.getForFemale()
        }

        /**
         * 本受付状態 不明
         */
        public static Map getForConstancyForUnknown(){
            return value + FixtureVoiceMnpInPersonalInfoState.One.getForUnknown()
        }

        /**
         * 本人確認OKアクティベーション予定日もあり
         */
        public static Map getForMnpInActivationDueDate(){
            return value + FixtureVoiceMnpInPersonalInfoState.One.getDefaultData() + FixtureVoiceMnpInActivationState.One.getDefaultData()

        }

    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : MnpInInitialData.firstRegistration,
                        2 : MnpInInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value + FixtureVoiceMnpInPersonalInfoState.Two.getDefaultData()
        }
        public static Map getOneMnpInPersonalInfoState(){
            return value + FixtureVoiceMnpInPersonalInfoState.One.getDefaultData()
        }

        /**
         * MSISDNが重複している場合
         */
        public static Map getDupulicateData(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 2, "voice_msisdn"), "090-1234-5678")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceEngagementCancelState.One.getDefaultData()
        }
    }

    public static class Second {

        private final static Map value = [
                (tableName) :  [
                        1 : MnpInInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value + FixtureVoiceMnpInPersonalInfoState.Second.getDefaultData()
        }

    }

    public static class Third {

        private final static Map value = [
                (tableName) :  [
                        1 : MnpInInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value + FixtureVoiceMnpInPersonalInfoState.Third.getDefaultData()
        }

    }

}
