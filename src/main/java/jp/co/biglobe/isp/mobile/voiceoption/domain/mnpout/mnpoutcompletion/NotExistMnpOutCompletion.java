package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class NotExistMnpOutCompletion implements MnpOutCompletion{

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isNotExist() {
        return !(isExist());
    }
}
