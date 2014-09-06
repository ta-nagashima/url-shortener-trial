package jp.co.biglobe.isp.auth.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public class UserAuthManagement {

    @Getter
    private final SessionId sessionId;

    public UserAuthMethodType judgesAuthMethodType(){
        return UserAuthMethodType.SESSION_ID;
    }

}
