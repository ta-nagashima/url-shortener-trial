package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationresult.db

import org.joda.time.DateTime

class IdentificationResultInitialData {

    private final static Map defaultDate = [
            "operator_id" : "xyz12345",
            "document_acceptance_means" : "web",
            "identification_doc_type" : "license",
            "identification_sub_doc_type" : "nothing"
    ]

    public final static Map firstRegistration = [
            "identification_result_id" : "1",
            "identification_receipt_number" : "140101" + "_" + "0001",
            "execution_date" : "20140101"
    ] + IdentificationResultInitialData.defaultDate

    public final static Map secondRegistration = [
            "identification_result_id" : "2",
            "identification_receipt_number" : "140101" + "_"  + "0002",
            "execution_date" : "20140101"
    ] + IdentificationResultInitialData.defaultDate


}
