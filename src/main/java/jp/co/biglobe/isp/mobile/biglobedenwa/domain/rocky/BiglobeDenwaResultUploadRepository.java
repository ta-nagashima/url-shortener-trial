package jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky;

public interface BiglobeDenwaResultUploadRepository {
    public void upload(RockySuccessFile rockySuccessFile, RockyErrorFile rockyErrorFile);
}
