package jp.co.biglobe.isp.sample.user.fixture

import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleUser
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureSampleUser {

    private final static String TABLE_NAME = "sample_user";


    public static class Nothing {

        private final static Map VALUE = [
                (TABLE_NAME): [
                        1: [],
                ]
        ]

        public static Map getDefaultData() {
            return VALUE
        }
    }


    public static class One {

        private final static Map VALUE = [
                (TABLE_NAME): [
                        1: ["sample_user_id": "1", "sample_user_name": "小池直樹", "sample_gender": "male"]
                ]
        ]

        public static Map getDefaultData() {
            return VALUE
        }

        public static Map getExpected(SampleUser expected) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(TABLE_NAME, expected.getSampleUserId().getValue(), "sample_user_name"), expected.getSampleUserName().getValue())
            map.put(new FixtureChangeTarget(TABLE_NAME, expected.getSampleUserId().getValue(), "sample_gender"), expected.getSampleGender().getDbValue())
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

    /**
     * キャンセルからの転出再申し込みのためのデータ
     */

    public static class Two {

        private final static Map VALUE = [
                (TABLE_NAME): [
                        1: ["sample_user_id": "1", "sample_user_name": "小池直樹", "sample_gender": "male"],
                        2: ["sample_user_id": "2", "sample_user_name": "小池直子", "sample_gender": "female"],
                ]
        ]

        public static Map getDefaultData() {
            return VALUE
        }
    }

}
