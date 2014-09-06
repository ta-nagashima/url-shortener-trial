package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db

import org.joda.time.DateTime

class MnpInActivationInitialData {

    private final static Map firstRegistration = [
            "voice_engagement_number" : "1",
            "mnp_activation_due_date" : new DateTime().dayOfMonth().roundFloorCopy().toString("yyyyMMdd"),
    ]

}
