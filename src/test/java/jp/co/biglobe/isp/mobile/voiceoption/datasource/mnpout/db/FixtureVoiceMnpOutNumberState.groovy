package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceMnpOutNumberState {

    private final static String tableName = "voice_mnp_out_number_state";


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
                        1: MnpOutNumberStateInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }


        public static Map getDefaultData(String num) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_mnp_out_id"), num)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getDefaultData(DateTime dt) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "expire_date"), dt.toString("yyyyMMdd"))
            map.put(new FixtureChangeTarget(tableName, 1, "execution_date"), dt.minusDays(14).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }


        /**
         * MNP転出番号発行　→　キャンセルされた状態からの転出再申し込み受付2
         */
        public static Map getForMnpOutRequestFromMnpOutCancel() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_mnp_out_id"), "2")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

    public static class Two {

        private final static Map value = [
                (tableName): [
                        1: MnpOutNumberStateInitialData.firstRegistration,
                        2: MnpOutNumberStateInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData() {
            return value
        }

    }

}
