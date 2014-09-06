package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.end.ChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.end.NoExistChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.end.ValidChargeApplicationEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class ChargeEngagementEntity{

    @Getter
    private final Integer id;

    @Getter
    private final String optionJapaneseName;

    @Getter
    private final String chargeEngagementNumber;

    @Getter
    private final Integer purchasedVolumeMB;

    @Getter
    private final String chargeCompletionStatus;

    private final Date chargeOrderDateTime;

    @Getter
    private final ChargeApplicationEnd chargeApplicationEnd;

    public ChargeEngagementEntity(
            Integer id,
            String optionJapaneseName,
            String chargeEngagementNumber,
            Integer purchasedVolumeMB,
            String chargeCompletionStatus,
            Date chargeOrderDateTime,
            ValidChargeApplicationEnd chargeApplicationEnd){

        this.id = id;
        this.optionJapaneseName = optionJapaneseName;
        this.chargeEngagementNumber = chargeEngagementNumber;
        this.purchasedVolumeMB = purchasedVolumeMB;
        this.chargeCompletionStatus = chargeCompletionStatus;
        this.chargeOrderDateTime = chargeOrderDateTime;
        this.chargeApplicationEnd = chargeApplicationEnd == null ? new NoExistChargeApplicationEnd() : chargeApplicationEnd;
    }

    public String getChargeOrderDateTime(){
        return DateToString.get_yyyyMMddHHmmss(chargeOrderDateTime);
    }
}
