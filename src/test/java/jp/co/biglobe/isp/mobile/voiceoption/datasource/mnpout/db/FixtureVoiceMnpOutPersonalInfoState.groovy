package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpOutPersonalInfoState {

    private final static String tableName = "voice_mnp_out_personal_info_s";


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
                        1: MnpOutPersonalInfoStateInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForFemale() {

            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_gender"), "female")
            return (Fixture.changeValueListForString(getDefaultData(), map))

        }

        public static Map getForUnknown() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_gender"), "unknown")
            return (Fixture.changeValueListForString(getDefaultData(), map))
        }

    }

    public static class Two {

        private final static Map value = [
                (tableName): [
                        1: MnpOutPersonalInfoStateInitialData.firstRegistration,
                        2: MnpOutPersonalInfoStateInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

        public static Map getForMnpOutRequestFromMnpOutCancel(){
            return value
        }

    }
}
