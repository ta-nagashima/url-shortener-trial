package jp.co.biglobe.isp.mobile.voiceoption.api.identification.identificationuploadcheck

import jp.co.biglobe.lib.plugin.response.normal.CheckApiResponse
import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMember
import jp.co.biglobe.isp.mobile.biglobemember.domain.address.BiglobeMemberAddress
import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IdentificationUploadCheckResponse {

    @Autowired
    private CheckApiResponse checkApiResponse;

    public Map build(UploadCheckStatus uploadCheckStatus, BiglobeMember biglobeMember){
        if (biglobeMember.isExist()) {
            ValidBiglobeMember validBiglobeMember = (ValidBiglobeMember) biglobeMember;
            BiglobeMemberAddress biglobeMemberAddress = validBiglobeMember.getAddress();
            checkApiResponse.build(uploadCheckStatus,
                    [
                            "biglobeMember": [
                                    "userId"        : validBiglobeMember.getUserId().getApiValue(),
                                    "fullName"      : validBiglobeMember.getFullName().getApiValue(),
                                    "zip"           : biglobeMemberAddress.getZipCode().getApiValue(),
                                    "prefectureCode": biglobeMemberAddress.getPrefectureCode().getApiValue(),
                                    "city"          : biglobeMemberAddress.getCity().getApiValue(),
                                    "houseNumber"   : biglobeMemberAddress.getHouseNumber().getApiValue(),
                                    "building"      : biglobeMemberAddress.getBuilding().getApiValue(),
                            ]
                    ]
            );
        } else {
            checkApiResponse.build(uploadCheckStatus);
        }
    }
}
