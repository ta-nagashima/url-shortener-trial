package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.status;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LteThreeGEngagementStatusCode implements EnumConvertForDb {
    INIT(LteThreeGEngagementStatusCodeDbValue.INIT, LimitExclude.FALSE),
    ORDERED(LteThreeGEngagementStatusCodeDbValue.ORDERED, LimitExclude.FALSE),
    ENGAGED(LteThreeGEngagementStatusCodeDbValue.ENGAGED, LimitExclude.FALSE),
    DISENGAGED(LteThreeGEngagementStatusCodeDbValue.DISENGAGED, LimitExclude.TRUE),
    CANCELED(LteThreeGEngagementStatusCodeDbValue.CANCELED, LimitExclude.TRUE);

    private final DbValue dbValue;

    private final LimitExclude limitExclude;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    private LteThreeGEngagementStatusCode getCurrentStatusCode(LteThreeGEngagementEndDateTime lteThreeGEngagementEndDateTime){

        if(this.equals(LteThreeGEngagementStatusCode.DISENGAGED) == false){
            return this;
        }

        if(lteThreeGEngagementEndDateTime.isBefore()){
            return this;
        }

        return LteThreeGEngagementStatusCode.ENGAGED;

    }

    public boolean isConnectControlPolicyExemption(LteThreeGEngagementEndDateTime lteThreeGEngagementEndDateTime){

        LteThreeGEngagementStatusCode currentStatus = getCurrentStatusCode(lteThreeGEngagementEndDateTime);
        return currentStatus.limitExclude.isValue();


    }

    public boolean isOrdered(){
        return this.equals(LteThreeGEngagementStatusCode.ORDERED);
    }

    public boolean isDisEngaged(LteThreeGEngagementEndDateTime lteThreeGEngagementEndDateTime){
        return getCurrentStatusCode(lteThreeGEngagementEndDateTime).equals(LteThreeGEngagementStatusCode.DISENGAGED);
    }

    @AllArgsConstructor
    private enum LteThreeGEngagementStatusCodeDbValue implements DbValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        ORDERED("10" /* 文字列リテラルの変更禁止 */),
        ENGAGED("50" /* 文字列リテラルの変更禁止 */),
        DISENGAGED("90" /* 文字列リテラルの変更禁止 */),
        CANCELED("80" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum LimitExclude {
        TRUE(true),
        FALSE(false);

        @Getter
        private final boolean value;
    }
}
