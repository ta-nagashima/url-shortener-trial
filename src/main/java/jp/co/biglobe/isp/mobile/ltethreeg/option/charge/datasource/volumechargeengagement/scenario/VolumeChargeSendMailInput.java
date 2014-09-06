package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement.scenario;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VolumeChargeSendMailInput {

    @Mapping("send_mail_kbn")
    private final Integer sendMailKbn = new Integer(1);

    @Mapping("to_address_kbn")
    private final Integer toAddressKbn = new Integer(1);

    @Mapping("umekomi_kbn")
    private final Integer umekomiKbn = new Integer(1);

    @Mapping("biglobeid")
    private UserId userId;

    @Mapping("mail_template")
    private final String mailTemplate = "BLTE_volumecharge_recept";

    /**
     * ここから埋め込みパラメーター
     */

    // ボリュームチャージ購入日 : YYYYMMDDhh24mi
    // (itme_free4)
    @Mapping("moji1")
    private String volumeChargeOrderDate;

    // ボリュームチャージ購入量(MB) : 1,000 (3桁カンマ区切り)
    // (itme_free7)
    @Mapping("moji4")
    private String purchasedVolumeMB;

    // ボリュームチャージ購入料金　税込み : 1,080 (3桁カンマ区切り)
    // (item_free8)
    // ボリュームチャージ購入通知のメールテンプレート上には存在しない変数名
    // 消費税移行期間完了後(消費税10%になったら？)税込み表示に切り替わる
    // 切り替え時にメールテンプレートの置き換えのみで対応できるよう、税込みの値も渡しておく
    @Mapping("moji5")
    private String volumeChargeFeeWithTax;

    // ボリュームチャージ購入料金　税抜 : 1,000 (3桁カンマ区切り)
    // (item_free9)
    @Mapping("moji6")
    private String volumeChargeFeeWithOutTax;

}
