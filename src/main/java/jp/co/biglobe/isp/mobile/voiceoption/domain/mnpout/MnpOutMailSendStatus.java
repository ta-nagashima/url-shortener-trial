package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum MnpOutMailSendStatus implements EnumConvertForApi {
    SEND("MNP転出申込受付メールを送信する", MnpOutMailSendStatusApiValue.SEND, true),
    NOT_SEND("MNP転出申込受付メールを送信しない", MnpOutMailSendStatusApiValue.NOT_SEND, false);

    private final String message;

    private final MnpOutMailSendStatusApiValue mnpOutMailSendStatusApiValue;

    private final boolean sendMail;

    public boolean isSend() { return sendMail; }

    @Override
    public String getApiValue() { return mnpOutMailSendStatusApiValue.getNoRefactoringValue(); }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    public enum MnpOutMailSendStatusApiValue implements ApiValue {
        SEND("send" /* 文字列リテラルの変更禁止 */),
        NOT_SEND("not_send" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
