package jp.co.biglobe.isp.mobile.biglobemember.domain.corporation;

import java.util.Arrays;

/**
 * 法人契約を表す数字の配列を保持しているクラス
 */
public final class CorporateContractNumArray {

    private final int[] ARRAY = {1, 3, 4, 7, 8};

    public boolean isCorporateContract(int i){
        Arrays.sort(ARRAY);
        if(Arrays.binarySearch(ARRAY, i) >= 0){
            return true;
        }else{
            return false;
        }
    }

}
