package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ValidSimList {

    @Getter
    private final List<ValidSimEntity> value;

    public List<ValidMsisdn> getValidMsisdnList(){

        List<ValidMsisdn> validMsisdnList = new ArrayList<>();

        for(ValidSimEntity e : value){
            if(e.getMsisdn().isExist()) {
                validMsisdnList.add((ValidMsisdn)e.getMsisdn());
            }
        }

        return validMsisdnList;
    }

}
