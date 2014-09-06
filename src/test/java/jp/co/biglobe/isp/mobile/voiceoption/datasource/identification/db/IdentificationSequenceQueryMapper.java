package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db;

public interface IdentificationSequenceQueryMapper {

    /**
     * シーケンスで払い出す番号をカウントアップさせるために用意した（テスト用）
     */

    public void countUpIdentificationResultIdSequence();

}
