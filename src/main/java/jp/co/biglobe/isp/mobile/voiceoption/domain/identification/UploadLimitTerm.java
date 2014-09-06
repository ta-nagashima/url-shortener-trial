package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInExecutionTerm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpreservationnumbervalidityterm.MnpReservationNumberValidityTerm;

/**
 * MNPありの場合の申込日からアップロードが可能な日数(Day)
 * limitDay=13(15-2:営業日のリスク期間2日含み),
 * 申込日=7/1の場合
 * 7/13(13日目＝12日後） 23:59:59 まで アップロード可
 * 7/14(14日目＝13日後） 00:00:00 から アップロード不可
 */
public class UploadLimitTerm {

    public static final int CAN_UPLOAD_AFTER_DAY
            = MnpReservationNumberValidityTerm.VALIDITY_TERM - (IdentificationExecutionTerm.EXECUTION_TERM + MnpInExecutionTerm.EXECUTION_TERM) - 1;

}
