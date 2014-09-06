package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;

public interface BiglobeDenwaLinkageRepository {

    public BiglobeDenwaLinkage findByMsisdn(BiglobeDenwaMsisdn biglobeDenwaMsisdn);

    public BiglobeDenwaLinkage findByMsisdnForUpdate(BiglobeDenwaMsisdn biglobeDenwaMsisdn);

    /**
     * 業務イベントごとにレポジトリーを作成
     */
//    public void applyFromApp(ValidBiglobeDenwaLinkage validLinkage);

    public void applyFromApp(BiglobeDenwaLinkage before, ValidBiglobeDenwaLinkage after);

    public void applyFromBatch(ValidBiglobeDenwaLinkage validLinkage);

    public void remove(ValidBiglobeDenwaLinkage validLinkage);

    public void updateRegistered(ValidBiglobeDenwaLinkage validLinkage);

    public void updateWaitingRegisterResult(ValidBiglobeDenwaLinkage validLinkage);

    public void updateWaitingReregister(ValidBiglobeDenwaLinkage validLinkage);

    public void updateWaitingReregisterResult(ValidBiglobeDenwaLinkage validLinkage);

    public void updateWaitingRemove(ValidBiglobeDenwaLinkage validLinkage);

    public void updateWaitingRemoveResult(ValidBiglobeDenwaLinkage validLinkage);

    public void updateUnableToRegister(ValidBiglobeDenwaLinkage validLinkage);

    public void updateRegisteredInOther(ValidBiglobeDenwaLinkage validLinkage);
}