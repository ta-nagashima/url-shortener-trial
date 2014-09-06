package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistBiglobeDenwaLinkage implements BiglobeDenwaLinkage {

    @Override
    public boolean isNotExist() {
        return true;
    }

    @Override
    public ValidBiglobeDenwaLinkage applyFromApp(
            BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {

        return new ValidBiglobeDenwaLinkage(
                biglobeDenwaMsisdn,
                BiglobeDenwaLinkageEvent.WAITING_REGISTER.getToStatus(),
                biglobeDenwaStatusChangeDateTime,
                BiglobeDenwaNotification.UNNECESSARY,
                BiglobeDenwaApplyChannel.APPLY_FROM_APP);
    }

    @Override
    public ValidBiglobeDenwaLinkage applyFromBatch(
            BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime,
            BiglobeDenwaApplyChannel biglobeDenwaApplyChannel) {

        return new ValidBiglobeDenwaLinkage(
                biglobeDenwaMsisdn,
                BiglobeDenwaLinkageEvent.WAITING_REGISTER.getToStatus(),
                biglobeDenwaStatusChangeDateTime,
                BiglobeDenwaNotification.UNNECESSARY,
                biglobeDenwaApplyChannel);
    }

    @Override
    public ValidBiglobeDenwaLinkage remove(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateUnableToRegister(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateRegistered(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingRegisterResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingRemoveResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingRemove(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingReregisterResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateWaitingReregister(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public ValidBiglobeDenwaLinkage updateRegisteredInOther(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime) {
        throwBusinessException();
        return null;
    }

    @Override
    public boolean isApplyFromApp() {
        return false;
    }

    private void throwBusinessException(){
        throw new BusinessException("該当するでんわ連携なし", BiglobeDenwaRegisterErrorStatus.INVALID_STATUS, VoiceOptionAlarmIdentifier.DEFAULT);
    }

}
