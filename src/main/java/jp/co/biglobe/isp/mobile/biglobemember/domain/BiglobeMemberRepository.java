package jp.co.biglobe.isp.mobile.biglobemember.domain;

import jp.co.biglobe.isp.auth.domain.user.UserId;

public interface BiglobeMemberRepository {
    // 会員情報の取得を行なうシナリオの呼び出し
    public BiglobeMember findByUserIdNoCafe(UserId userId);

    public ValidBiglobeMember findByUserIdNoCafeForValid(UserId userId);
}
