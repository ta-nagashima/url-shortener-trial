package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistMnpInActivation implements MnpInActivation {
    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public String getMnpActivationDueDateForApiValue() {
        return "";
    }
}
