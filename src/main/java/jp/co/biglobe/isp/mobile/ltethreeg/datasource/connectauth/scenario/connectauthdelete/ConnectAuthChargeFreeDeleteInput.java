package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.AfterBuyChargeFreeId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthItemCode;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.annotation.MappingObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectAuthChargeFreeDeleteInput {


    @MappingObject
    private final ConnectAuthDeleteCommonInput connectAuthDeleteCommonInput = new ConnectAuthDeleteCommonInput();

    //解約申込日時
    @Mapping("kai_moushikomi_ymdh")
    private final KaiMoushikomiYmdh kaiMoushikomiYmdh;

    //被操作者ID
    @Mapping("sosaid")
    private final AfterBuyChargeFreeId sosaid;

    //被操作者ID
    @Mapping("hi_sosaid")
    private final AfterBuyChargeFreeId hi_sosaid;

    //利用口番号
    @Mapping("riyoguchi_no")
    private final RiyoguchiNo riyoguchiNo;

    //契約枝番
    @Mapping("keiyaku_edano")
    private final KeiyakuEdano keiyakuEdano;

    //契約終了日時
    @Mapping("keiyaku_ymdh_end")
    private final KeiyakuYmdhEnd keiyakuYmdhEnd;

    //契約終了期限日時
    @Mapping("kyk_kigen_ymd")
    private final KykKigenYmd kykKigenYmd;

}
