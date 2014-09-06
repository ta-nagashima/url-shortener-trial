package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


public interface BiglobeDenwaLinkage {

    public boolean isNotExist();

    public ValidBiglobeDenwaLinkage applyFromApp(
            BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage applyFromBatch(
            BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime,
            BiglobeDenwaApplyChannel biglobeDenwaApplyChannel);

    public ValidBiglobeDenwaLinkage remove(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateUnableToRegister(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateRegistered(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateWaitingRegisterResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateWaitingRemoveResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateWaitingRemove(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateWaitingReregisterResult(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateWaitingReregister(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public ValidBiglobeDenwaLinkage updateRegisteredInOther(BiglobeDenwaStatusChangeDateTime biglobeDenwaStatusChangeDateTime);

    public boolean isApplyFromApp();

}
