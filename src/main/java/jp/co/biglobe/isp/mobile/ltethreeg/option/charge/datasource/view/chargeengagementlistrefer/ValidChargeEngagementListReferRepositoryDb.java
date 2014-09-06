package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.view.chargeengagementlistrefer;


import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.view.chargeengagementlistrefer.db.ChargeEngagementRepositoryQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ValidChargeEngagementListReferRepositoryDb implements ChargeEngagementListReferRepository {

    @Autowired
    ChargeEngagementRepositoryQueryMapper chargeEngagementRepositoryQueryMapper;


    @Override
    public ChargeEngagementEntityTotalCount findByLteThreeGEngagementNumberForCount(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        return chargeEngagementRepositoryQueryMapper.findByLteThreeGEngagementNumberForCount(lteThreeGEngagementNumber);

    }

    @Override
    public ChargeEngagementEntityList findAllByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber, Paging paging) {


        List<ChargeEngagementEntity> list = chargeEngagementRepositoryQueryMapper.findAllByLteThreeGEngagementNumber(
                lteThreeGEngagementNumber, paging.getLow(), paging.getHigh());

        return new ChargeEngagementEntityList(list);
    }

}
