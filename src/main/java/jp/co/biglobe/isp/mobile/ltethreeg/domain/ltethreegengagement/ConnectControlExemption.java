package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumElseConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConnectControlExemption implements EnumElseConvertForDb <ConnectControlExemption> {

    EXEMPTION("接続制限を免除する",ConnectControlExemptionForDb.EXEMPTION,false),
    NO_EXEMPTION("接続制限を免除しない",ConnectControlExemptionForDb.NO_EXEMPTION,true);

    private final String message;

    private final DbValue dbValue;

    private final boolean dbElseValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    public boolean isExemption(){
        return this.equals(ConnectControlExemption.EXEMPTION);
    }

    public ConnectControlExemption getDbElseValue(){
        return ConnectControlExemption.NO_EXEMPTION;
    }

    @AllArgsConstructor
    private enum ConnectControlExemptionForDb implements DbValue{
        EXEMPTION("1"),
        NO_EXEMPTION("");

        @Getter
        private final String noRefactoringValue;
    }

}
