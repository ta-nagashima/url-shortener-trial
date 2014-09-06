package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReason
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget

class FixtureVoiceIdentificationNgState {

    private final static String tableName = "voice_identification_ng_s";


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
                        1 : IdentificationResultNgInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        public static Map getVariousNgReason(NgReason ngReason, String ngReasonDetail){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ng_reason"), ngReason.getDbValue())
            map.put(new FixtureChangeTarget(tableName, 1, "ng_reason_detail"), ngReasonDetail)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getVariousNgReason(NgReason ngReason){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ng_reason"), ngReason.getDbValue())
            return Fixture.changeValueListForString(getDefaultNgReasonDetail(), map)
        }

        public static Map getDefaultNgReasonDetail(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ng_reason_detail"), "データ未登録")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForResultId2(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_result_id"), "2")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }


    }

}
