package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.disengagementcharge.refer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeAmount;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeEndMonth;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class VoiceDisengagementChargeReferOutput {

    @Getter
    private BobioHeader bobioHeader;

    /**
     * iyaku_endym[1]について
     *
     * 【重要】：↓以下の記述は違約金契約の種類によって異なるので注意！！！！
     *
     * 音声の契約契約解除料の場合、期間が２年で201405に申し込むとiyaku_endym[1]は201604を返す。
     * つまり、iyaku_endym[1]が返す値は「この月が違約金がかかる最後の月＝翌月に解約したら０円」という月
     * （↑の場合、２０１６年５月にやめる場合は０円）
     */

    /**
     * 契約解除料がない場合（契約前）は以下の項目は返さない。
     */

    @Mapping("iyaku_endym[1]")
    @Getter
    private VoiceDisengagementChargeEndMonth voiceDisengagementChargeEndMonth;

    /**
     * 契約解除料金額（税込み）を返す。
     * 税抜き価格を返却する事もできるが、消費税が上がったときに影響するので、
     * 消費税の計算は契約解除料システムに委譲するようにした。
     */

    @Mapping("iyaku_current_fee[1]")
    @Getter
    private VoiceDisengagementChargeAmount voiceDisengagementChargeAmount;

}
