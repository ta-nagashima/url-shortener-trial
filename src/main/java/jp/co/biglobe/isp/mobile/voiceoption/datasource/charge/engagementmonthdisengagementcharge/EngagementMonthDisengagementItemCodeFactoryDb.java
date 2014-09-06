package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementItemCode;

/**
 * 契約月解約課金商品コードのオブジェクトを作成
 */
public class EngagementMonthDisengagementItemCodeFactoryDb {

    // 現在の音声月額課金金額をもとに新規作成
    public static EngagementMonthDisengagementItemCode create(){
        return new EngagementMonthDisengagementItemCode("0AA0002989");
    };

}
