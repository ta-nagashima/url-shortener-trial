package jp.co.biglobe.isp.mobile.biglobemember.datasource;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.wrapper.BiglobeMemberScenario2;
import jp.co.biglobe.isp.mobile.biglobemember.domain.*;
import jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.BiglobeMemberOutput;
import jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.BiglobeMemberScenario;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BiglobeMemberRepositoryScenario implements BiglobeMemberRepository {

    @Autowired
    private BiglobeMemberScenario biglobeMemberScenario;

    //todo バッチサーバにMemLegmの穴が空いていないので、ラッパー経由で呼び出す。９月中に穴あけ完了するので、そのタイミングでリファクタ
    @Autowired
    private BiglobeMemberScenario2 biglobeMemberScenario2;

    @Autowired
    private BiglobeMemberFactory biglobeMemberFactory;


    // 会員情報の取得を行なうシナリオの呼び出し
    @Override
    public BiglobeMember findByUserIdNoCafe(UserId userId){

        verifyUserId(userId);

        BiglobeMemberOutput biglobeMemberOutput = biglobeMemberScenario.findByBiglobeId(userId);

        if (isBiglobeMemberNotExist(biglobeMemberOutput)) {
            return new NotExistBiglobeMember();
        }

        return biglobeMemberFactory.create(userId, biglobeMemberOutput);
    }


    @Override
    public ValidBiglobeMember findByUserIdNoCafeForValid(UserId userId){

        verifyUserId(userId);

        BiglobeMemberOutput biglobeMemberOutput = biglobeMemberScenario2.findByBiglobeId(userId);

        if (isBiglobeMemberNotExist(biglobeMemberOutput)) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。会員が存在しませんでした。", LteThreegAlarmIdentifier.DEFAULT
            );
        }

        return biglobeMemberFactory.create(userId, biglobeMemberOutput);
    }

    /**
     * 利用しているシナリオ「M_MemberRefMeminfo2」で、ユーザID未設定の場合は
     * 全件参照になってしまう。
     * そのため、シナリオ呼び出し前にユーザIDが未設定の場合は例外をスローするようにする。
     */
    private void verifyUserId(UserId userId){

        if(userId == null){
            throw new SystemCheckException("会員参照で使用するユーザIDが未設定です。", LteThreegAlarmIdentifier.DEFAULT);
        }

        if(userId.getValue() == null){
            throw new SystemCheckException("会員参照で使用するユーザIDが未設定です。", LteThreegAlarmIdentifier.DEFAULT);
        }

        if(userId.getValue().equals("")){
            throw new SystemCheckException("会員参照で使用するユーザIDが未設定です。", LteThreegAlarmIdentifier.DEFAULT);
        }

        return;
    }

    private boolean isBiglobeMemberNotExist(BiglobeMemberOutput biglobeMemberOutput){
        return !biglobeMemberOutput.getBobioHeader().isEqualBOCLCode("0");
    }

}
