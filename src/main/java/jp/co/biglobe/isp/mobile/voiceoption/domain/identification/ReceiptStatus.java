package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReceiptStatus implements EnumConvertForApi {
    PROVISIONAL(
            "仮受付",
            VoiceClerkRequestStatusApiValue.PROVISIONAL,
            VoiceClerkRequestStatus.UNREQUESTED,
            MnpInInputItemsValidation.FALSE
    ),
    CONSTANCY(
            "本受付",
            VoiceClerkRequestStatusApiValue.CONSTANCY,
            VoiceClerkRequestStatus.REQUESTED,
            MnpInInputItemsValidation.TRUE
    );

    private final String message;

    private final ApiValue apiValue;

    @Getter
    private final VoiceClerkRequestStatus voiceClerkRequestStatus;

    private final MnpInInputItemsValidation mnpInInputItemsValidation;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    public boolean validatorMnpInputItemsUnnecessary(){
        return !mnpInInputItemsValidation.noRefactoringValue;
    }

    /**
     * APIに提示される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum VoiceClerkRequestStatusApiValue implements ApiValue {
        PROVISIONAL("provisional" /* 文字列リテラルの変更禁止 */),
        CONSTANCY("constancy" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum MnpInInputItemsValidation{
        TRUE(true),
        FALSE(false);

        @Getter
        private final boolean noRefactoringValue;
    }
}
