package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;

public class UploadManagement {

    public UploadCheckStatus verify(LteThreeGEngagementEntity lteThreeGEngagementEntity, Identification identification,
                                    VoiceEngagement voiceEngagement, MnpIn mnpIn){

        if (lteThreeGEngagementEntity.isInvalid()) {
            return UploadCheckStatus.LTE_THREE_G_NOT_ENGAGEMENT;
        }

        if (identification.isNotExist()) {
            return UploadCheckStatus.ENGAGEMENT_NOT_ORDERED;
        }

        if (voiceEngagement.isNotExist()) {
            return UploadCheckStatus.ENGAGEMENT_NOT_ORDERED;
        }

        ValidIdentification validIdentification = (ValidIdentification)identification;
        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement)voiceEngagement;

        if (validVoiceEngagement.notCanIdentificationUploadStatus()) {
            return UploadCheckStatus.ENGAGEMENT_NOT_ORDERED;
        }

        // MNPありの場合は受付日時から15日間経過したらアップロード不可
        if (mnpIn.isIdentificationUploadDeadlineOver(validVoiceEngagement)) {
            return UploadCheckStatus.RECEIPT_LIMIT_TIME_OVER;
        }

        // 本人確認のチェック
        return validIdentification.verifyUpload();

    }
}
