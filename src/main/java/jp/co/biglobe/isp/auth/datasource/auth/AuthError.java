package jp.co.biglobe.isp.auth.datasource.auth;

import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthError {

    private final BobioHeader bobioHeader;

    public boolean isSuccess(){
        if(bobioHeader.getBOCLCode().getValue().equals(BOCLCodeValue.SUCCESS.getRawValue())){
            return true;
        }else{
            return false;
        }
    }

    public boolean isInvalidIdPwError(){
        if(isInvalidIdPwErrorHelper("150107000", "50")){
            return true;
        }else if(isInvalidIdPwErrorHelper("151107000", "51")){
            return true;
        }else{
            return false;
        }
    }

    private boolean isInvalidIdPwErrorHelper(String boCLDetailCode, String statusStatus){
        if(bobioHeader.getBOCLCode().getValue().equals(BOCLCodeValue.ERROR_INVALID_STATUS.getRawValue()) &&
                bobioHeader.getBOCLDetailCode().getValue().equals(boCLDetailCode) &&
                bobioHeader.getStatusStatus().getValue().equals(statusStatus)){
            return true;
        }else{
            return false;
        }
    }


    public boolean  isBeforeServiceError(){
        return isServiceError("BLSH121063");
    }

    public boolean isEndServiceError(){
        return isServiceError("BLSH121064");
    }

    private boolean isServiceError(String statusDet){
        if(bobioHeader.getBOCLCode().getValue().equals(BOCLCodeValue.ERROR_INVALID_STATUS.getRawValue()) &&
                bobioHeader.getBOCLDetailCode().getValue().equals("165107000") &&
                bobioHeader.getStatusStatus().getValue().equals("65") &&
                bobioHeader.getStatusDet().getValue().equals(statusDet)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isSessionTimeoutError(){
        if(isSessionTimeoutErrorHelper(BOCLCodeValue.ERROR_SESSION_TIMEOUT_1.getRawValue())){
            return true;
        }else if(isSessionTimeoutErrorHelper(BOCLCodeValue.ERROR_SESSION_TIMEOUT_100.getRawValue())){
            return true;
        }else{
            return false;
        }
    }

    private boolean isSessionTimeoutErrorHelper(String boCLCode){
        if(bobioHeader.getBOCLCode().getValue().equals(boCLCode) &&
                bobioHeader.getBOCLDetailCode().getValue().matches("^100[0-9]{3}000$")){
            return true;
        }else{
            return false;
        }

    }

}
