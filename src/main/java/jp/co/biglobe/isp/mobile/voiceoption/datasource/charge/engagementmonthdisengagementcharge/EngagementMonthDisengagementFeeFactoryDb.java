package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementFee;

/**
 * 契約月解約課金金額のオブジェクトを作成
 */
public class EngagementMonthDisengagementFeeFactoryDb {

    // 現在の音声月額課金金額をもとに新規作成
    public static EngagementMonthDisengagementFee create(){
        return new EngagementMonthDisengagementFee(900);
    };

}
