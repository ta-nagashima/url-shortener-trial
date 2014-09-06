package jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm;

import jp.co.biglobe.lib.publication.alarmidentifier.AlarmIdentifier;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
public enum LteThreegAlarmIdentifier implements AlarmIdentifier {
    DEFAULT("LTE000", "特に指定しない場合のデフォルト識別子"),
    NONE("XXXXXXXX", "メール通知をそもそもしないので、これが使われた場合はアプリケーションバグ");

    /**
     * アラーム識別子
     */
    @Getter
    private final String identifier;

    /**
     * アラームの意味
     */
    private final String message;

    /**
     * コンストラクタ
     */
    private LteThreegAlarmIdentifier(final String identifier, final String message) {
        verify(identifier);

        this.identifier = identifier;
        this.message = message;
    }

    /**
     * アラーム識別子の桁数チェック
     *
     * @param identifier アラーム識別子
     */
    private void verify(final String identifier){
        if(identifier.length() > 8){
            throw new RuntimeException("アラーム識別子は、８桁を指定してください");
        }
    }
}
