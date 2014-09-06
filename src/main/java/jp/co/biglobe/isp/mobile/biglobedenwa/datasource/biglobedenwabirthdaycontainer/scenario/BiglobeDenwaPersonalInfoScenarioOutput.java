package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer.scenario;


import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthday;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class BiglobeDenwaPersonalInfoScenarioOutput {

    @Getter
    private BobioHeader bobioHeader;


    @Mapping("birthday[1]")
    private String birthday;

    public BiglobeDenwaBirthday convertBirthDayStringToBiglobeDenwaBirthDay(){
        return new BiglobeDenwaBirthday(this.birthday.substring(4));
    }


}
