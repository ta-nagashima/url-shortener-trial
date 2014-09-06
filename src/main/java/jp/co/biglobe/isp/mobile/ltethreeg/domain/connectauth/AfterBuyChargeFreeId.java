package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.auth.domain.user.BiglobeId;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
public class AfterBuyChargeFreeId implements BiglobeId {
    private final String value;

//    @Autowired
//    private PropertyAccessor propertyAccessor;

//    public AfterBuyChargeFreeId(){
//        this.value = propertyAccessor.getProperty("after.buy.charge.free.id");
//    }

//    public AfterBuyChargeFreeId(){
//        this.value = "abc12345";
//    }

}
