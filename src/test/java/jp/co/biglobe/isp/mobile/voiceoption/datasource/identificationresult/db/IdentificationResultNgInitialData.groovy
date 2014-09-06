package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db

class IdentificationResultNgInitialData {

    private final static Map defaultDate = [
            "identification_result_id" : "1",
            "ng_reason" : "incomplete_document",
            "ng_reason_detail" : "未登録"
    ]

    public final static Map firstRegistration = IdentificationResultNgInitialData.defaultDate

}
