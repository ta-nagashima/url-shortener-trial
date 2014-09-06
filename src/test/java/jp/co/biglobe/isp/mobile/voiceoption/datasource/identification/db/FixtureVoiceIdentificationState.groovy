package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db

import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db.FixtureVoiceIdentificationUploadS
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationNgState
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db.FixtureVoiceIdentificationResultState
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.DocumentAcceptanceMeans
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentType
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationSubDocumentType
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReason
import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceIdentificationState {

    private final static String tableName = "voice_identification_state";


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
                        1: IdentificationInitialData.firstRegistration
                ]
        ]

        /**
         * デフォルト
         * （ドキュメント待ち、REQUESTED）
         */

        public static Map getDefaultData() {
            return value
        }

        /**
         * 仮受付状態
         */
        public static Map getForProvisional() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "unrequested")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForUnnecessaryProvisional() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "u_stop_shipping")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 発送管理連携済み
         */
        public static Map getForProvisionalResume() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "requested")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForUnnecessaryProvisionalResume() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "u_shipped")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 発送管理連携不要
         */
        public static Map getForNoShipping() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "unnecessary")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map getForNoShippingProvisional() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "u_stop_shipping")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認OK
         */
        public static Map getForIdentificationOK() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * アウトバウンド中
         * 実際はアップロード回数上限値(=1)の制限でエラーになるが、ステータスとしてはアップロード可である
         * アップロード回数の上限が増えたときの為に、upload_count=0でテストを通す
         */
        public static Map getForIdentificationNG() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * ドキュメント待ち
         */
        public static Map getForDocumentWaiting() {
            return value
        }

        /**
         * 本人確認書類の初回アップロード済み
         */
        public static Map getForFirstUploaded() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "identification_waiting")
            return Fixture.changeValueListForString(getDefaultData(), map) + FixtureVoiceIdentificationUploadS.One.getForFirstUploaded()
        }

    }

    /**
     * エンティティごとのアサートで使用する。
     *
     */


    public static class OneEntity {

        private final static Map value = [
                (tableName): [
                        1: IdentificationInitialData.firstRegistration
                ]
        ]

        /**
         * デフォルト
         * （ドキュメント待ち、REQUESTED）
         */

        public static Map getDefaultData() {
            return value
        }

        /**
         * ドキュメント待ち
         */
        public static Map getForDocumentWaiting() {
            return value
        }

        /**
         * 本人確認待ち
         */
        public static Map getForIdentificationWaiting() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "identification_waiting")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationUploadS.One.getForFirstUploaded())
        }

        /**
         * 本人確認NG
         */
        public static Map getForOutBoundNowFromFax() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                + FixtureVoiceIdentificationResultState.One.getFromFax()
                + FixtureVoiceIdentificationNgState.One.getDefaultData())
        }

        public static Map getForOutBoundNowFromFax2() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromFax2()
                    + FixtureVoiceIdentificationNgState.One.getForResultId2())
        }



        /**
         * 本人確認NG
         * 本人確認書類と補助書類を指定
         */
        public static Map getForOutBoundNowFromFax(String doc, String subDoc) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromFax(doc, subDoc)
                    + FixtureVoiceIdentificationNgState.One.getDefaultData())
        }

        public static Map getForOutBoundNowFromWeb() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromWeb()
                    + FixtureVoiceIdentificationNgState.One.getDefaultData())
        }

        public static Map getForOutBoundNowFromWeb(DateTime dt) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromWeb(dt)
                    + FixtureVoiceIdentificationNgState.One.getDefaultData())
        }


        public static Map getForOutBoundNowFromWeb(NgReason ngReason) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromWeb()
                    + FixtureVoiceIdentificationNgState.One.getVariousNgReason(ngReason))
        }

        public static Map getForOutBoundNowFromWeb(NgReason ngReason, String detail) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromWeb()
                    + FixtureVoiceIdentificationNgState.One.getVariousNgReason(ngReason, detail))
        }



        public static Map getForOutBoundNowFromWebDefaultNgReasonDetail() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromWeb()
                    + FixtureVoiceIdentificationNgState.One.getDefaultNgReasonDetail())
        }

        /**
         * 本人確認NG_アップロード（経路、資料、補助資料を指定可能）
         */
        public static Map getForOutBoundNowFromVariousUpload(IdentificationDocumentType identificationDocumentType,
                                                       IdentificationSubDocumentType identificationSubDocumentType,
                                                       DocumentAcceptanceMeans documentAcceptanceMeans,
                                                       NgReason ngReason,
                                                       String ngReasonDetail) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "outbound_now")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationUploadS.One.getForFirstUploaded()
                    + FixtureVoiceIdentificationResultState.One.getFromVarious(
                    identificationDocumentType,identificationSubDocumentType,documentAcceptanceMeans)
                    + FixtureVoiceIdentificationNgState.One.getVariousNgReason(ngReason,ngReasonDetail))

        }

        /**
         * 本人確認OK（Fax）
         */
        public static Map getForOKFromFax() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromFax())
        }

        public static Map getForOKFromFax2() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromFax2())
        }

        /**
         * 本人確認OK（Fax）
         * 本人確認書類と補助書類を指定
         */
        public static Map getForOKFromFax(String doc, String subDoc) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromFax(doc, subDoc))
        }

        /**
         * 本人確認OK（Post）
         * 本人確認書類と補助書類を指定
         */
        public static Map getForOKFromPost(String doc, String subDoc) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationResultState.One.getFromPost(doc, subDoc))
        }

        /**
         * 本人確認OK（Web）
         */
        public static Map getForOKFromWeb() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationUploadS.One.getForFirstUploaded()
                    + FixtureVoiceIdentificationResultState.One.getFromWeb())
        }


        /**
         * 本人確認OK_アップロード（経路、資料、補助資料を指定可能）
         */
        public static Map getForIdentificationOKFromVarious(IdentificationDocumentType identificationDocumentType,
                                                            IdentificationSubDocumentType identificationSubDocumentType,
                                                            DocumentAcceptanceMeans documentAcceptanceMeans) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationUploadS.One.getForFirstUploaded()
                    + FixtureVoiceIdentificationResultState.One.getFromVarious(
                    identificationDocumentType,identificationSubDocumentType,documentAcceptanceMeans)
            )
        }

        /**
         * 本人確認OK_アップロードなし（経路、資料、補助資料を指定可能）
         */
        public static Map getForIdentificationOKFromVariousNoUpload(IdentificationDocumentType identificationDocumentType,
                                                            IdentificationSubDocumentType identificationSubDocumentType,
                                                            DocumentAcceptanceMeans documentAcceptanceMeans) {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "identification_status"), "ok")
            return (Fixture.changeValueListForString(getDefaultData(), map)
                    + FixtureVoiceIdentificationUploadS.Nothing.getDefaultData()
                    + FixtureVoiceIdentificationResultState.One.getFromVarious(
                    identificationDocumentType,identificationSubDocumentType,documentAcceptanceMeans)
            )
        }

        /**
         * 仮受付状態
         */
        public static Map getForProvisional() {
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "voice_clerk_request_status"), "unrequested")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

    }

}
