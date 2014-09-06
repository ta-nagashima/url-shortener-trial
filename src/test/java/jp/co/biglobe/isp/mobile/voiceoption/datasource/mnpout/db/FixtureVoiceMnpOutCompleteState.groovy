package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceMnpOutCompleteState {

    private final static String tableName = "voice_mnp_out_complete_state";


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
                        1 : MnpOutCompleteStateInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        public static Map getForCallHisotryTest(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_complete_confirm_date"), "20140701")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForCallHisotryTest2(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "mnp_out_complete_confirm_date"), "20140630")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

}
