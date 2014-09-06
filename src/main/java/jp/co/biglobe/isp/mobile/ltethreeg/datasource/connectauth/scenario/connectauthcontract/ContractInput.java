package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthcontract;

import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractInput {

    // 必須属性チェック
    @Mapping("contract_auth_type[1]")
    protected final String contract_auth_type1 = "0";

    //支払方法チェック
    @Mapping("contract_auth_type[6]")
    protected final String contract_auth_type6 = "0";

    //性別チェック
    @Mapping("contract_auth_type[8]")
    protected final String contract_auth_type8 = "0";

    //年齢チェック
    @Mapping("contract_auth_type[9]")
    protected final String contract_auth_type9 = "0";

    //法人チェック
    @Mapping("contract_auth_type[10]")
    protected final String contract_auth_type10 = "0";

    //有料コンテンツ購入可否チェック
    @Mapping("contract_auth_type[13]")
    protected final String contract_auth_type13 = "0";

    //当月コース可否
    @Mapping("contract_auth_type[16]")
    protected final String contract_auth_type16 = "9";

    //商品サービス期間チェック
    @Mapping("contract_auth_type[17]")
    protected final String contract_auth_type17 = "1";

    //外部決済可否チェック
    @Mapping("contract_auth_type[24]")
    protected final String contract_auth_type24 = "0";

}
