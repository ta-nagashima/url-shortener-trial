package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMember;
import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationBiglobeMemberReferService {

    @Autowired
    private BiglobeMemberRepository biglobeMemberRepository;


    public BiglobeMember refer(UserId userId){
        return biglobeMemberRepository.findByUserIdNoCafe(userId);
    }
}
