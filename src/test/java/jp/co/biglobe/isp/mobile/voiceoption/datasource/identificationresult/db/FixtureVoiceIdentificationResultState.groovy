package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.DocumentAcceptanceMeans
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentType
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationSubDocumentType
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceIdentificationResultState {

    private final static String tableName = "voice_identification_result_s";


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
                        1 : IdentificationResultInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        /**
         * 本人確認OK（Fax）
         */
        public static Map getFromFax(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "fax")
            map.put(new FixtureChangeTarget(tableName, 1, "execution_date"), "20140101")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getFromFax2(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "fax")
            map.put(new FixtureChangeTarget(tableName, 1, "execution_date"), "20140101")
            map.put(new FixtureChangeTarget(tableName, 1, "identification_result_id"), "2")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類と補助書類を指定する
         */
        public static Map getFromFax(String doc, String subDoc){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_doc_type"), doc)
            map.put(new FixtureChangeTarget(tableName, 1, "identification_sub_doc_type"), subDoc)
            return Fixture.changeValueListForString(getFromFax(), map)
        }


        /**
         * 本人確認OK（Post）
         */
        public static Map getFromPost(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), "post")
            map.put(new FixtureChangeTarget(tableName, 1, "execution_date"), "20140101")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類と補助書類を指定する
         */
        public static Map getFromPost(String doc, String subDoc){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_doc_type"), doc)
            map.put(new FixtureChangeTarget(tableName, 1, "identification_sub_doc_type"), subDoc)
            return Fixture.changeValueListForString(getFromPost(), map)
        }




        /**
         * 本人確認OK（書類種別、補助書類種別、受付経路をパラメータで指定する）
         */
        public static Map getFromWeb(){
            return value
        }

        public static Map getFromVarious(IdentificationDocumentType identificationDocumentType,
                                         IdentificationSubDocumentType identificationSubDocumentType,
                                         DocumentAcceptanceMeans documentAcceptanceMeans){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_doc_type"), identificationDocumentType.dbValue)
            map.put(new FixtureChangeTarget(tableName, 1, "identification_sub_doc_type"), identificationSubDocumentType.dbValue)
            map.put(new FixtureChangeTarget(tableName, 1, "document_acceptance_means"), documentAcceptanceMeans.dbValue)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getFromWeb(DateTime dt) {

            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "execution_date"), dt.toString("yyyyMMdd"));
            return Fixture.changeValueListForString(getDefaultData(), map)
        }


    }

}
