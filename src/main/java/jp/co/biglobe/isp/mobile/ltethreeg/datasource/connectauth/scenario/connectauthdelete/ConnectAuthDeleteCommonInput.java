package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthItemCode;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectAuthDeleteCommonInput {

    //操作者IDタイプ
    @Mapping("id_type")
    private final String idType = "1";

    //出力データ区分(出力項目のみ出力、入力項目は出力しない)
    @Mapping("output_type")
    private final String outputType = "1";

    //パスワード変換区分
    @Mapping("pwd_enc_type")
    private final String pwdEncType = "1";

    //担当者パスワード
    @Mapping("taotopwd")
    private final String tantopwd = "50415353303030";

    //チェックレベル
    @Mapping("op_auth_type")
    private final String opAuthType = "0000000000";

    //強制更新フラグ
    @Mapping("shnkyk_hnk_yoyaku_umuflg")
    private final String shnkykHnkYoyakuUmuflg = "1";

    //利用数量更新ログ発行対処F（0：発行しない）
    @Mapping("riyousuryo_updlog_flg")
    private final String riyousuryoUpdlogFlg = "0";

    //停止解約理由C（07：）
    @Mapping("teikaiyaku_cd_kai")
    private final String teikaiyakuCdDai = "07";

    //商品G
    @Mapping("shohin_gc")
    private final ConnectAuthItemCode connectAuthItemCode = new ConnectAuthItemCode();

    //契約ステータス
    @Mapping("kyk_sts_c")
    private final String kykStsC = "25";

}
