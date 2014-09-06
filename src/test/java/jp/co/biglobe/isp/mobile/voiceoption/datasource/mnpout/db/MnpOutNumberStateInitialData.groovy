package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

class MnpOutNumberStateInitialData {

    private final static Map defaultDate = [
            "mnp_reservation_number" : "11-222-33333",
            "expire_date" : "20140415",
            "execution_date" : "20140401",
            "operator_id" : "abc12345"
    ]

    public final static Map firstRegistration = ["voice_mnp_out_id" : "1",] + MnpOutNumberStateInitialData.defaultDate
    public final static Map secondRegistration = ["voice_mnp_out_id" : "2",] + MnpOutNumberStateInitialData.defaultDate
}
