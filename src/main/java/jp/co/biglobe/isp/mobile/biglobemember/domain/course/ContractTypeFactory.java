package jp.co.biglobe.isp.mobile.biglobemember.domain.course;

import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporateContractNumArray;

/**
 * CourseId情報からContractType（法人か個人か）を作り出すクラス。
 * 現状、法人会員か個人会員かはコースIDの十の位を元に
 * 判断しているらしいが、CourseIdにContractTypeを生成する責務を
 * 与えるのは意味的におかしいと思うので、専用のファクトリーを用意した。
 */
public class ContractTypeFactory {

    public ContractType getContractType(CourseId courseId){
        int distinctNum = getDistinctNum(courseId);
        CorporateContractNumArray corporateContractNumArray = new CorporateContractNumArray();
        if(corporateContractNumArray.isCorporateContract(distinctNum)){
            return ContractType.CORPORATION;
        }else{
            return ContractType.INDIVIDUAL;
        }
    }

    /**
     * CourseIdの十の位をint型で返却する
     * @param courseId
     * @return int
     */
    private int getDistinctNum(CourseId courseId){
        /**
         * カフェ会員はコースIDがないという情報に基づき、
         * コースIDが空文字の場合は個人契約とする。
         * また、普通はあり得ないはずだが、コースIDが１桁の場合も
         * 個人契約に倒した。
         * カフェ会員以外は正しいコースIDが入っている。
         */
        String courseIdValue = courseId.getValue();
        String distinctStr;
        if(courseIdValue.length() == 0 || courseIdValue.length() == 1){
            distinctStr = "2";
        }else{
            distinctStr = courseIdValue.substring(courseIdValue.length()-2, courseIdValue.length()-1);
        }
        return Integer.parseInt(distinctStr);
    }


}
