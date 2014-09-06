package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpInPersonalInfoState {

    private final static String tableName = "voice_mnp_in_personal_info_s";


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
                        1: MnpInPersonalInfoStateInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForFemale() {

            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_in_gender"), "female")
            return (Fixture.changeValueListForString(getDefaultData(), map))

        }

        public static Map getForUnknown() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_in_gender"), "unknown")
            return (Fixture.changeValueListForString(getDefaultData(), map))
        }

    }

    public static class Two {

        private final static Map value = [
                (tableName): [
                        1: MnpInPersonalInfoStateInitialData.firstRegistration,
                        2: MnpInPersonalInfoStateInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

    }

    public static class Second{

        private final static Map value = [
                (tableName) :  [
                        2 : MnpInPersonalInfoStateInitialData.secondRegistration2
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

    }

    public static class Third{

        private final static Map value = [
                (tableName) :  [
                        2 : MnpInPersonalInfoStateInitialData.thirdRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

    }
}
