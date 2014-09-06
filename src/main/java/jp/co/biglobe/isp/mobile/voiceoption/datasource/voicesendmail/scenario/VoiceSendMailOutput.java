package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class VoiceSendMailOutput {

    @Getter
    private BobioHeader bobioHeader;

    // 非操作者IDのサービスが期限切れかどうかを返却
    // シナリオの仕様書「業務返却値（基本）」シートを参照
    public boolean isExpirationOfService(){
        return (bobioHeader.getBOCLCode().getValue().equals("98") &&
                bobioHeader.getBOCLDetailCode().getValue().equals("165107000") &&
                bobioHeader.getStatusStatus().getValue().equals("65") &&
                bobioHeader.getStatusDet().getValue().equals("BLSH121064"));
    }

    // 非操作者IDがサービス期限切れ以外のエラー
    public boolean isError(){
        if(isExpirationOfService()){ return false; }

        return !(bobioHeader.getBOCLCode().getValue().equals("0"));
    }

    public boolean isAnyError(){
        return isExpirationOfService() || isError();
    }

    // 本人確認結果メールの送信ステータスを返却する
    public VoiceSendMailStatus getIdentificationResultMailStatus(){
        if(isExpirationOfService()){
            return VoiceSendMailStatus.EXPIRATION_OF_SERVICE;
        }

        if(isError()){
            return VoiceSendMailStatus.ERROR;
        }

        return VoiceSendMailStatus.OK;
    }






}
