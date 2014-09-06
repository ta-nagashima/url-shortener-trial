package jp.co.biglobe.isp.auth.domain.user;


import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * BIGLOBE ID
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class UserId implements BiglobeId, ValueObjectConvertForApi {
    @Getter
    private final String value;

    @Override
    public String getApiValue(){
        return value;
    }

    public boolean isDifferent(UserId targetUserId){
        return !equals(targetUserId);
    }
}
