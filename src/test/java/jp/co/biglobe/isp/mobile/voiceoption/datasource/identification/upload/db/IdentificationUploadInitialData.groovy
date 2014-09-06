package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db

import org.joda.time.DateTime

class IdentificationUploadInitialData {

    private final static Map defaultDate = [
            "first_upload_date_time" : new DateTime().toString("yyyy-MM-dd 00:00:00"),
            "upload_count" : 0
    ]

    public final static Map firstRegistration = ["identification_receipt_number" : "140101_0001",] + IdentificationUploadInitialData.defaultDate

    public final static Map secondRegistration = ["identification_receipt_number" : "140101_0002",] + IdentificationUploadInitialData.defaultDate

}
