package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.*;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public enum IdentificationStatusEvent implements EnumConvertForApi {

    ORDER(
            Arrays.asList(IdentificationStatus.INIT),
            IdentificationStatus.DOCUMENT_WAITING,
            "本人確認書類待ち",
            UploadResetStatus.UNRESET,
            IdentificationStatusEventApiValue.ORDER,
            null,
            null,
            null
    ),
    RECEIVE_DOCUMENT(
            Arrays.asList(
                    IdentificationStatus.DOCUMENT_WAITING,
                    IdentificationStatus.OUTBOUND_NOW
            ),
            IdentificationStatus.IDENTIFICATION_WAITING,
            "本人確認書類入手、本人確認待ち",
            UploadResetStatus.UNRESET,
            IdentificationStatusEventApiValue.RECEIVE_DOCUMENT,
            null,
            null,
            null
    ),
    OK(
            Arrays.asList(
                    IdentificationStatus.DOCUMENT_WAITING,
                    IdentificationStatus.IDENTIFICATION_WAITING,
                    IdentificationStatus.OUTBOUND_NOW
            ),
            IdentificationStatus.OK,
            "本人確認完了",
            UploadResetStatus.UNRESET,
            IdentificationStatusEventApiValue.OK,
            NgReasonValidation.TRUE,
            ActivationDueDateValidation.FALSE,
            DocumentTypeValidation.FALSE
    ),
    NG(
            Arrays.asList(
                    IdentificationStatus.DOCUMENT_WAITING,
                    IdentificationStatus.IDENTIFICATION_WAITING,
                    IdentificationStatus.OUTBOUND_NOW
            ),
            IdentificationStatus.OUTBOUND_NOW,
            "本人確認失敗、アウトバウンド待ち",
            UploadResetStatus.RESET,
            IdentificationStatusEventApiValue.NG,
            NgReasonValidation.FALSE,
            ActivationDueDateValidation.TRUE,
            DocumentTypeValidation.TRUE
    );

    private final List<IdentificationStatus> fromStatues;

    @Getter
    private final IdentificationStatus toStatus;

    private final String message;

    private final UploadResetStatus uploadResetStatus;

    private final ApiValue apiValue;

    private final NgReasonValidation ngReasonValidation;

    private final ActivationDueDateValidation activationDueDateValidation;

    private final DocumentTypeValidation documentTypeValidation;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }


    boolean isNgFromStatus(IdentificationStatus identificationStatus){
        return !fromStatues.contains(identificationStatus);
    }

    public boolean validatorNgReasonsUnnecessary(){
        return ngReasonValidation.noRefactoringValue;
    }

    public boolean validatorActivationDueDateUnnecessary(){
        return activationDueDateValidation.noRefactoringValue;
    }

    public boolean documentTypeValidationUnnecessary(){
        return documentTypeValidation.noRefactoringValue;
    }


    /**
     * アップロード回数などをリセットするかどうかのステータス
     */
    @AllArgsConstructor
    private enum UploadResetStatus {
        RESET("リセットする", true),
        UNRESET("リセットしない", false);

        private final String message;

        @Getter
        private final boolean isReset;


    }

    /**
     * APIに提示される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum IdentificationStatusEventApiValue implements ApiValue{
        ORDER("order" /* 文字列リテラルの変更禁止 */),
        RECEIVE_DOCUMENT("receive_document" /* 文字列リテラルの変更禁止 */),
        OK("ok" /* 文字列リテラルの変更禁止 */),
        NG("ng" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;

    }

    @AllArgsConstructor
    private enum NgReasonValidation{
        TRUE("バリデーションが不要", true),
        FALSE("バリデーションが必要",false);

        private String message;

        @Getter
        private final boolean noRefactoringValue;
    }

    @AllArgsConstructor
    private enum ActivationDueDateValidation{
        TRUE("バリデーションが不要",true),
        FALSE("バリデーションが必要",false);

        private String message;

        @Getter
        private final boolean noRefactoringValue;
    }

    @AllArgsConstructor
    private enum DocumentTypeValidation{
        TRUE("バリデーションが不要",true),
        FALSE("バリデーションが必要",false);

        private String message;

        @Getter
        private final boolean noRefactoringValue;
    }



}
