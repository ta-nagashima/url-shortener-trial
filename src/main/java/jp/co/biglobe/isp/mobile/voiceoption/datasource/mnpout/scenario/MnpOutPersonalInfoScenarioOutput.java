package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpBirthday;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpFullName;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpFullNameKana;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class MnpOutPersonalInfoScenarioOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("simei_kanji[1]")
    @Getter
    private MnpFullName fullName;

    @Mapping("simei_kana[1]")
    private String kana;

    /**
     * 男性は１、女性は２、その他は0
     */
    @Mapping("seibetu_c_db[1]")
    @Getter
    private Integer seibetu;

    @Mapping("birthday[1]")
    @Getter
    private MnpBirthday birthday;


    public MnpFullNameKana getKana() {

        return new MnpFullNameKana(StringConverter.convert(kana));

    }

}
