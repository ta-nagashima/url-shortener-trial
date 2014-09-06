package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;

/**
 * Created by masahirodoi on 2014/05/28.
 */
public interface NgReasons extends CommonEntity {

    public String getNgReasonForApiValue();

    public String getNgReasonDetailForApiValue();

}
