package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetailrefer

import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementCharge
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceEngagementDetailReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(VoiceEngagementDetailReferContainer voiceEngagementDetailRefer) {


        final String VOICE_ENGAGEMENT_RESULT = "voiceEngagementResult";
        VoiceEngagement voiceEngagement = voiceEngagementDetailRefer.getVoiceEngagement()
        Identification identification = voiceEngagementDetailRefer.getIdentification()
        MnpIn mnpIn = voiceEngagementDetailRefer.getMnpIn()
        MnpOut mnpOut = voiceEngagementDetailRefer.getMnpOut()
        DisengagementCharge disengagementCharge = voiceEngagementDetailRefer.getDisengagementCharge()
        EngagementMonthDisengagementCharge engagementMonthDisengagementCharge = voiceEngagementDetailRefer.getEngagementMonthDisengagementCharge()


        if (voiceEngagement.isNotExist()) {
            return template.build([(VOICE_ENGAGEMENT_RESULT): StereotypedCharacters.NOT_EXIST]);

        }

        return template.build(
                [
                        (VOICE_ENGAGEMENT_RESULT): StereotypedCharacters.EXIST,
                ]
                        + buildVoiceEngagement(voiceEngagement)
                        + buildIdentification(identification)
                        + buildDisengagementCharge(disengagementCharge)
                        + buildEngagementMonthDisengagementCharge(engagementMonthDisengagementCharge)
                        + buildMnpIn(mnpIn)
                        + buildMnpOut(mnpOut)
        );
    }

    public Map buildVoiceEngagement(VoiceEngagement voiceEngagement) {


        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagement

        String voiceEngagementStatus = validVoiceEngagement.isDisengageReserved() ?
                "disengaged_reservation" : validVoiceEngagement.getVoiceEngagementStatus().getApiValue()

        return [
                "voiceEngagement": [
                        "voiceEngagementNumber"     : validVoiceEngagement.getVoiceEngagementNumber().getApiValue(),
                        "equipmentNumber"           : validVoiceEngagement.getVoiceEngagementLinkage().getEquipmentNumber().getApiValue(),
                        "lteThreeGEngagementNumber" : validVoiceEngagement.getVoiceEngagementLinkage().getLteThreeGEngagementNumber().getApiValue(),
                        "userId"                    : validVoiceEngagement.getVoiceEngagementLinkage().getUserId().getApiValue(),
                        "voiceJoinCode"             : validVoiceEngagement.getNewOrderInfo().getVoiceJoinCode().getApiValue(),
                        "voiceEngagementStatus"     : voiceEngagementStatus,
                        "voiceUserOrderDate"        : validVoiceEngagement.getNewOrderInfo().getVoiceUserOrderDate().getApiValue(),
                        "voiceSystemReceiptDateTime": validVoiceEngagement.getNewOrderInfo().getVoiceSystemReceiptDateTime().getApiValue(),
                        "voiceEngagementStartDate"  : validVoiceEngagement.getVoiceEngagementStartDate().getApiValue(),
                        "voiceEngagementEndDateTime": validVoiceEngagement.getVoiceEngagementEndDateTime().getApiValue(),
                ]
        ]
    }

    /**
     * 仮受付復旧時のメールの出し分けのために準備した。
     *
     */

    public Map buildIdentification(Identification identification) {

        final String identificationResult = "identificationResult";

        if (identification.isNotExist()) {
            return [(identificationResult): StereotypedCharacters.NOT_EXIST]
        }

        ValidIdentification validIdentification = (ValidIdentification)identification

        return [
                (identificationResult): StereotypedCharacters.EXIST,
                "identification": [
                        "voiceClerkRequestStatus"     : validIdentification.getVoiceClerkRequestStatus().getApiValue()
                ]
        ]
    }



    public Map buildDisengagementCharge(DisengagementCharge disengagementCharge) {
        return [
                "disengagement_charge":
                        [
                                "status"   : disengagementCharge.getApiValueForCostingStatus(),
                                "end_month": disengagementCharge.getApiValueForCostingEndMonth(),
                                "amount"   : disengagementCharge.getApiValueForCostingAmount()
                        ]
        ]
    }

    public Map buildEngagementMonthDisengagementCharge(EngagementMonthDisengagementCharge engagementMonthDisengagementCharge) {

        return [
                "engagement_month_disengagement_charge":
                        [
                                "executeDateTime"   : engagementMonthDisengagementCharge.getApiValueForExecuteDateTime(),
                                "amount"   : engagementMonthDisengagementCharge.getApiValueForChargeAmount()
                        ]
        ]
    }

    public Map buildMnpIn(MnpIn mnpIn) {

        final String mnpInResult = "mnpInResult";

        if (mnpIn.isNotExist()) {
            return [(mnpInResult): StereotypedCharacters.NOT_EXIST]
        }

        ValidMnpIn validMnpIn = (ValidMnpIn) mnpIn;
        return [
                (mnpInResult): StereotypedCharacters.EXIST,
                "mnpIn"      : [
                        "mnpMsisdn"           : validMnpIn.getVoiceMsisdn().getApiValue(),
                        "mnpReservationNumber": validMnpIn.getMnpReservationNumber().getApiValue(),
                        "mnpFullName"         : validMnpIn.getMnpInPersonalInfo().getMnpFullNameForApiValue(),
                        "mnpFullNameKana"     : validMnpIn.getMnpInPersonalInfo().getMnpFullNameKanaForApiValue(),
                        "mnpGender"           : validMnpIn.getMnpInPersonalInfo().getMnpGenderForApiValue(),
                        "mnpBirthday"         : validMnpIn.getMnpInPersonalInfo().getMnpBirthdayForApiValue(),
                        "activationDueDate"   : validMnpIn.getMnpActivationDueDateForApiValue(),
                ]
        ]
    }

    public Map buildMnpOut(MnpOut mnpOut) {

        final String mnpOutResult = "mnpOutResult";

        if (mnpOut.isNotExist()) {
            return [(mnpOutResult): StereotypedCharacters.NOT_EXIST]
        }

        ValidMnpOut validMnpOut = (ValidMnpOut) mnpOut;
        return [
                (mnpOutResult): StereotypedCharacters.EXIST,
                "mnpOut"      : [
                        "mnpMsisdn"           : validMnpOut.getMnpOutMsisdn().getApiValue(),
                        "mnpFullName"         : validMnpOut.getValidMnpOutPersonalInfo().getMnpFullNameForApiValue(),
                        "mnpFullNameKana"     : validMnpOut.getValidMnpOutPersonalInfo().getMnpFullNameKanaForApiValue(),
                        "mnpGender"           : validMnpOut.getValidMnpOutPersonalInfo().getMnpGenderForApiValue(),
                        "mnpBirthday"         : validMnpOut.getValidMnpOutPersonalInfo().getMnpBirthdayForApiValue(),
                        "mnpReservationNumber": validMnpOut.getMnpOutReservationNumber().getMnpReservationNumberForApiValue(),
                        "expireDate"          : validMnpOut.getMnpOutReservationNumber().getExpireDateForApiValue(),
                        "executionDate"       : validMnpOut.getMnpOutReservationNumber().getExecutionDateForApiValue(),
                        "mnpOutStatus"        : validMnpOut.getMnpOutStatus().getApiValue()
                ]
        ]
    }
}
