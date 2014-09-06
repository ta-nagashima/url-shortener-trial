package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;

public interface IdentificationResultRepository {

    // 新規本人確認結果情報を登録する。もし、以前キャンセルされていてStateに情報が残っている場合は
    // 旧レコードを削除してから、新規レコードを登録する。
    public void register(ValidIdentification validIdentification);

    // 新規本人確認結果情報を更新する。
    public void update(ValidIdentification validIdentification);

}
