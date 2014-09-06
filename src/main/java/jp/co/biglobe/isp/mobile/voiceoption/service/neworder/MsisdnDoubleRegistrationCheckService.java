package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;


import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.VoiceMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MsisdnDoubleRegistrationCheckStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsisdnDoubleRegistrationCheckService {

    @Autowired
    private MnpInRepository mnpInRepository;

    public MsisdnDoubleRegistrationCheckStatus check(VoiceMsisdn voiceMsisdn) {

        MnpIn mnpIn = mnpInRepository.findByVoiceMsisdnUnderValid(voiceMsisdn);

        return mnpIn.verifyMsisdnDoubleRegistration();
    }
}
