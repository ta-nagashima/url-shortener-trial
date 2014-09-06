package jp.co.biglobe.isp.auth.domain.user;

import jp.co.biglobe.lib.publication.scenario.authentication.ScenarioSessionId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

/**
 * セッションID（ユーザ入力でのみ使用）
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SessionId implements ScenarioSessionId {
    
    @Getter
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String value;


    /**
     * セッションIDを文字列として取得
     *
     * @return セッションIDの文字列
     */
    public String getStringSessionId(){
        return value;
    }
}
