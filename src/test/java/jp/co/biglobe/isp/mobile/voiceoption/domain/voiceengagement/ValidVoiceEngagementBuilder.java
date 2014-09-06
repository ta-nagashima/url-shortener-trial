package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.NotExistVoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.NotExistVoiceEngagementDisengage;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengage;
import org.joda.time.DateTime;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class ValidVoiceEngagementBuilder {

    private VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);
    private VoiceSystemReceiptDateTime voiceSystemReceiptDateTime = new VoiceSystemReceiptDateTime(
            new SimpleDateFormat("yyyyMMddHHmmss").parse("20140101000000",new ParsePosition(0))
    );
    private VoiceEngagementStatus voiceEngagementStatus = VoiceEngagementStatus.ORDERED;
    private VoiceUserOrderDate voiceUserOrderDate = new VoiceUserOrderDate("20140101");
    private VoiceEngagementStartDate voiceEngagementStartDate = new VoiceEngagementStartDate("29991231");
    private VoiceEngagementEndDateTime voiceEngagementEndDateTime = new VoiceEngagementEndDateTime(
            new SimpleDateFormat("yyyyMMddHHmmss").parse("29991231235959",new ParsePosition(0))
    );
    private VoiceJoinCode voiceJoinCode = new VoiceJoinCode("webyn002");
    private EquipmentNumber equipmentNumber = new EquipmentNumber("00000001");
    private UserId userId = new UserId("abc12345");
    private VoiceEngagementCancel voiceEngagementCancel = new NotExistVoiceEngagementCancel();
    private VoiceEngagementDisengage voiceEngagementDisengage = new NotExistVoiceEngagementDisengage();
    private LteThreeGEngagementNumber lteThreeGEngagementNumber = new LteThreeGEngagementNumber("00000001");
    private VoiceEngagementLinkage voiceEngagementLinkage= new VoiceEngagementLinkage(userId, lteThreeGEngagementNumber, equipmentNumber);
    private NewOrderInfo newOrderInfo = new NewOrderInfo(voiceSystemReceiptDateTime, voiceUserOrderDate, voiceJoinCode);
    private CancelListOutPutStatus cancelListOutPutStatus = CancelListOutPutStatus.NOT_OUTPUT;
    private VoiceOrderType voiceOrderType = VoiceOrderType.ORDER_WITH_LTE;



    public ValidVoiceEngagementBuilder() { }

    public ValidVoiceEngagement build() {
        return new ValidVoiceEngagement(
                voiceEngagementNumber,
                voiceEngagementStatus,
                voiceEngagementStartDate,
                voiceEngagementEndDateTime,
                voiceEngagementCancel,
                voiceEngagementDisengage,
                voiceEngagementLinkage,
                newOrderInfo,
                cancelListOutPutStatus,
                voiceOrderType
        );
    }

    public ValidVoiceEngagementBuilder orderDate(DateTime dateTime){
        voiceSystemReceiptDateTime = new VoiceSystemReceiptDateTime(dateTime.toDate());
        voiceUserOrderDate = new VoiceUserOrderDate(dateTime.toString("yyyyMMdd"));
        newOrderInfo = new NewOrderInfo(voiceSystemReceiptDateTime, voiceUserOrderDate, voiceJoinCode);
        return this;

    }

    public ValidVoiceEngagementBuilder voiceEngagementNumber(VoiceEngagementNumber value) {
        voiceEngagementNumber = value;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceEngagementNumber(Integer i) {
        VoiceEngagementNumber newNumber = new VoiceEngagementNumber(i);
        voiceEngagementNumber = newNumber;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceSystemReceiptDateTime(VoiceSystemReceiptDateTime value) {
        voiceSystemReceiptDateTime = value;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceSystemReceiptDateTime(DateTime value) {
        VoiceSystemReceiptDateTime newDateTime = new VoiceSystemReceiptDateTime(value.toDate());
        return voiceSystemReceiptDateTime(newDateTime);
    }

    public ValidVoiceEngagementBuilder voiceEngagementStatus(VoiceEngagementStatus value) {
        voiceEngagementStatus = value;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceUserOrderDate(VoiceUserOrderDate value) {
        voiceUserOrderDate = value;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceEngagementStartDate(VoiceEngagementStartDate value) {
        voiceEngagementStartDate = value;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceEngagementEndDateTime(VoiceEngagementEndDateTime value) {
        voiceEngagementEndDateTime = value;
        return this;
    }

    public ValidVoiceEngagementBuilder voiceEngagementEndDateTime(DateTime value) {
        VoiceEngagementEndDateTime newDateTime = new VoiceEngagementEndDateTime(value.toDate());
        return voiceEngagementEndDateTime(newDateTime);
    }

    public ValidVoiceEngagementBuilder voiceSalesChannel(VoiceJoinCode value) {
        voiceJoinCode = value;
        return this;
    }

    public ValidVoiceEngagementBuilder orderMethodStatus(VoiceOrderType value) {
        voiceOrderType = value;
        return this;
    }

    public ValidVoiceEngagementBuilder cancelOperationListOutPutStatus(CancelListOutPutStatus value) {
        cancelListOutPutStatus = value;
        return this;
    }

    public ValidVoiceEngagementBuilder newOrderInfo(NewOrderInfo value) {
        newOrderInfo = value;
        return this;
    }

}
