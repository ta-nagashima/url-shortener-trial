package jp.co.biglobe.isp.sample.user.domain.authentication;


import jp.co.biglobe.lib.publication.scenario.authentication.ScenarioSessionId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * セッションID（ユーザ入力でのみ使用）
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public final class SessionId implements ScenarioSessionId {

    @Getter
    private final String value;


    /**
     * セッションIDを文字列として取得
     *
     * @return セッションIDの文字列
     */
    public String getStringSessionId() {
        return value;
    }
}

