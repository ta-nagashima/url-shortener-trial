package jp.co.biglobe.isp.mobile.extension.domain;

public class TaxCalculator {


    // todo 税率をどこで持とう。共有ライブラリで全war共通のほうがよいか？
    // todo 共有ライブラリにした場合、このクラスをautowiredする必要あり
    private static double TAX = 1.08;

    public static int getTruncationPoint(int value){

        double valueWithTax = value * TAX;
        return (int)valueWithTax;
    }
}
