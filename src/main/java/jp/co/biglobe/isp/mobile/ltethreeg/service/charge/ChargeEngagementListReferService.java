package jp.co.biglobe.isp.mobile.ltethreeg.service.charge;

import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChargeEngagementListReferService {

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private ChargeEngagementListReferRepository chargeEngagementListReferRepository;

    public ChargeEngagementListReferContainer refer(LteThreeGEngagementNumber lteThreeGEngagementNumber, Paging paging){

        ConnectPerformanceEntity connectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        ChargeEngagementEntityTotalCount chargeEngagementEntityTotalCount =
                chargeEngagementListReferRepository.findByLteThreeGEngagementNumberForCount(lteThreeGEngagementNumber);

        ChargeEngagementEntityList chargeEngagementEntityList =
                chargeEngagementListReferRepository.findAllByLteThreeGEngagementNumber(lteThreeGEngagementNumber, paging);

        return new ChargeEngagementListReferContainer(connectPerformanceEntity, chargeEngagementEntityTotalCount, chargeEngagementEntityList);
    }

    @AllArgsConstructor
    public class ChargeEngagementListReferContainer{

        private final ConnectPerformanceEntity connectPerformanceEntity;

        @Getter
        private final ChargeEngagementEntityTotalCount chargeEngagementEntityTotalCount;

        @Getter
        private final ChargeEngagementEntityList chargeEngagementEntityList;

        public String getCurrentStatus(int id){

            int listId = id - 1;

            if(connectPerformanceEntity.isNotExist()){
                return "";
            }
            ValidConnectPerformanceEntity validConnectPerformanceEntity = (ValidConnectPerformanceEntity)connectPerformanceEntity;

            if(isCompletion(listId)){
                return getEngagementStatus(listId);
            }

            if(isSpeedCharge(listId)){
                return getConnectPerformanceSpeedStatus(validConnectPerformanceEntity);
            }

            return getConnectPerformanceVolumeStatus(validConnectPerformanceEntity);

        }

        private boolean isCompletion(int listId){
            return "completion".equals(getEngagementStatus(listId));
        }

        private String getEngagementStatus(int listId){
            return chargeEngagementEntityList.getList().get(listId).getChargeCompletionStatus();
        }


        private boolean isSpeedCharge(int listId){
            return "スピードチャージ".equals(chargeEngagementEntityList.getList().get(listId).getOptionJapaneseName());
        }

        private String getConnectPerformanceVolumeStatus(ValidConnectPerformanceEntity validConnectPerformanceEntity){
            return validConnectPerformanceEntity.getSpeedStatus().getVolumeChargeStatus().getApiValue();
        }

        private String getConnectPerformanceSpeedStatus(ValidConnectPerformanceEntity validConnectPerformanceEntity){
            return validConnectPerformanceEntity.getSpeedStatus().getSpeedChargeStatus().getApiValue();
        }
    }
}
