package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


import jp.co.biglobe.isp.mobile.biglobedenwa.domain.alarm.BiglobeDenwaAlarmIdentifier;
import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
public class BiglobeDenwaRegisterInvalidStatusException extends BusinessException{

    public BiglobeDenwaRegisterInvalidStatusException(String message){
        super(message, BiglobeDenwaRegisterErrorStatus.INVALID_STATUS, BiglobeDenwaAlarmIdentifier.DEFAULT);
    }
}
