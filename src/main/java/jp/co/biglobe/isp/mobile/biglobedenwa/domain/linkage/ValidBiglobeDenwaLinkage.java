package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


import jp.co.biglobe.isp.mobile.biglobedenwa.domain.alarm.BiglobeDenwaAlarmIdentifier;
import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidBiglobeDenwaLinkage implements BiglobeDenwaLinkage {

    @Getter
    private final BiglobeDenwaMsisdn biglobeDenwaMsisdn;
    @Getter
    private final BiglobeDenwaLinkageStatus biglobeDenwaLinkageStatus;
    @Getter
    private final BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime;
    @Getter
    private final BiglobeDenwaNotification biglobeDenwaNotification;
    @Getter
    private final BiglobeDenwaApplyChannel biglobeDenwaApplyChannel;

    @Deprecated
    public ValidBiglobeDenwaLinkage(
            BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            BiglobeDenwaLinkageStatus biglobeDenwaLinkageStatus,
            BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime,
            BiglobeDenwaNotification biglobeDenwaNotification) {

        this(
                biglobeDenwaMsisdn,
                biglobeDenwaLinkageStatus,
                biglobeDenwaStatusChangeDateTime,
                biglobeDenwaNotification,
                BiglobeDenwaApplyChannel.APPLY_FROM_APP
        );
    }


    public ValidBiglobeDenwaLinkage(
            BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime,
            BiglobeDenwaApplyChannel biglobeDenwaApplyChannel) {

        this(
                biglobeDenwaMsisdn,
                BiglobeDenwaLinkageStatus.NO_LINKAGE,
                biglobeDenwaStatusChangeDateTime,
                BiglobeDenwaNotification.UNNECESSARY,
                biglobeDenwaApplyChannel
        );
    }

    @Override
    public boolean isNotExist() {
        return false;
    }

    private ValidBiglobeDenwaLinkage changeStatus(BiglobeDenwaLinkageEvent event, BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {

        if (event.isNgFromStatus(biglobeDenwaLinkageStatus)) {
            throw new BusinessException(
                    "でんわ連携のステータスが異常",
                    BiglobeDenwaRegisterErrorStatus.INVALID_LINKAGE_STATUS,
                    VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return new ValidBiglobeDenwaLinkage(
                this.biglobeDenwaMsisdn,
                event.getToStatus(),
                biglobeDenwaStatusChangeDateTime,
                this.biglobeDenwaNotification,
                biglobeDenwaApplyChannel
        );
    }

    @Override
    public ValidBiglobeDenwaLinkage applyFromApp(BiglobeDenwaMsisdn biglobeDenwaMsisdn, BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {

        BiglobeDenwaLinkageEvent event = BiglobeDenwaApplyFromAppEvent.APPLAY_FROM_APP.toStatus(biglobeDenwaLinkageStatus);
        return changeStatus(event, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage applyFromBatch(BiglobeDenwaMsisdn biglobeDenwaMsisdn, BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime, BiglobeDenwaApplyChannel biglobeDenwaApplyChannel) {
        throw new BusinessException("すでに登録済みのMSISDNです",
                BiglobeDenwaRegisterErrorStatus.INVALID_STATUS,
                BiglobeDenwaAlarmIdentifier.DEFAULT);
    }

    @Override
    public ValidBiglobeDenwaLinkage remove(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.REMOVE, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingRegisterResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.WAITING_REGISTER_RESULT, biglobeDenwaStatusChangeDateTime);
    }

    public ValidBiglobeDenwaLinkage updateWaitingReregisterResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.WAITING_REREGISTER_RESULT, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateRegistered(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.REGISTERED, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingRemove(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.WAITING_REMOVE, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingRemoveResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime){
        return changeStatus(BiglobeDenwaLinkageEvent.WAITING_REMOVE_RESULT, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingReregister(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.WAITING_REREGISTER, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateUnableToRegister(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.UNABLE_TO_REGISTER, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public ValidBiglobeDenwaLinkage updateRegisteredInOther(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        return changeStatus(BiglobeDenwaLinkageEvent.REGISTERED_IN_OTHER, biglobeDenwaStatusChangeDateTime);
    }

    @Override
    public boolean isApplyFromApp() {
        if(biglobeDenwaApplyChannel==BiglobeDenwaApplyChannel.APPLY_FROM_APP){
            return true;
        }

        return false;
    }

}
