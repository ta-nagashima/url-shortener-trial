package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.UploadManagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesFactory;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IdentificationUploadService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpInRepository mnpInRepository;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private IdentificationFilesFactory identificationFilesFactory;

    @Autowired
    private IdentificationFilesRepository identificationFilesRepository;

    public void upload(UserId userId,
                       Date requestDate,
                       IdentificationFile identificationFile1,
                       IdentificationFile identificationFile2,
                       IdentificationFile identificationFile3) {

        // 本人確認 Entity 取得
        ValidIdentification validIdentification = getValidIdentification(userId);

        // 本人確認 Entity 更新
        identificationRepository.update(validIdentification.uploaded());

        // アップロードされたファイルを登録する
        IdentificationFiles identificationFiles = identificationFilesFactory.create(
                validIdentification.getIdentificationReceiptNumber(),
                requestDate,
                identificationFile1,
                identificationFile2,
                identificationFile3
        );
        identificationFilesRepository.register(identificationFiles);
    }

    /**
     * 本人確認 Entity 取得
     */
    private ValidIdentification getValidIdentification(UserId userId) {

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementRepository.findByUserIdForValid(userId);

        ValidIdentification validIdentification = identificationRepository.findByLteThreeGEngagementNumberForUpdate(validLteThreeGEngagement.getLteThreeGEngagementNumber());

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByVoiceEngagementNumberForValid(validIdentification.getVoiceEngagementNumber());

        MnpIn mnpIn = mnpInRepository.findByVoiceEngagementNumber(validVoiceEngagement);

        UploadManagement uploadManagement = new UploadManagement();
        UploadCheckStatus uploadCheckStatus = uploadManagement.verify(validLteThreeGEngagement, validIdentification, validVoiceEngagement, mnpIn);
        if (uploadCheckStatus.isNg()) {
            throw new SystemCheckException("Illegal Status for Update", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return validIdentification;
    }
}
