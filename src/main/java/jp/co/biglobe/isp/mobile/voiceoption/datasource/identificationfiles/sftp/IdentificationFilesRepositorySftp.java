package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationfiles.sftp;

import jp.co.biglobe.isp.mobile.extension.encrypt.PGPPublicKey;
import jp.co.biglobe.isp.mobile.extension.encrypt.PGPPublicKeyRing;
import jp.co.biglobe.isp.mobile.extension.ssh.BoSSHAgentConnector;
import jp.co.biglobe.isp.mobile.extension.sftp.BoSftpSession;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.ValidIdentificationFile;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Repository("IdentificationFilesRepositorySftp")
public class IdentificationFilesRepositorySftp implements IdentificationFilesRepository {

    @Autowired
    private PropertyAccessor propertyAccessor;

    @Override
    public void register(IdentificationFiles identificationFiles) {
        // 暗号化に使用する公開鍵を取得する
        PGPPublicKey pgpPublicKey = getPgpPublicKey();

        // ディレクトリ名取得
        String baseDirectory = getStoreBaseDirectory();
        String makeDirectory = getMakeDirectoryName(identificationFiles);

        // SFTP サーバ接続
        BoSftpSession sftpSession = getSftpSession();

        try {
            // 仮ディレクトリ名作成
            String temporaryDirectory = "temp_" + makeDirectory;

            // 仮ディレクトリ作成
            int directoryPermissions = Integer.parseInt(propertyAccessor.getProperty("identification.sftp.put.directory.permissions"), 8);
            sftpSession.makeDirectory(baseDirectory, temporaryDirectory, directoryPermissions);

            // ファイル書き込み
            int filePermissions = Integer.parseInt(propertyAccessor.getProperty("identification.sftp.put.file.permissions"), 8);
            for (ValidIdentificationFile validIdentificationFile : identificationFiles.getIdentificationFiles()) {
                String fileName = getFileName(identificationFiles, validIdentificationFile);
                sftpSession.register(baseDirectory + temporaryDirectory, fileName, filePermissions, validIdentificationFile.getPGPEncryptedBytes(pgpPublicKey));
            }

            // ディレクトリ名変更
            sftpSession.rename(baseDirectory, temporaryDirectory, makeDirectory);
        } finally {
            // SFTP サーバ切断
            sftpSession.disConnect();
        }
    }

    /**
     * 本人確認資料 格納ディレクトリ（最後はスラッシュ）
     */
    private String getStoreBaseDirectory() {
        String sftpPostPath = propertyAccessor.getProperty("identification.sftp.put.path");
        if (sftpPostPath.matches("/$") == false) {
            sftpPostPath = sftpPostPath + "/";
        }
        return sftpPostPath;
    }

    /**
     * アップロード毎に作成するディレクトリ名
     * （テストの為 private を外しています）
     */
    static String getMakeDirectoryName(IdentificationFiles identificationFiles) {
        String identificationReceiptNumber = identificationFiles.getIdentificationReceiptNumber().getValue();
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(identificationFiles.getRequestTime());

        // アップロード毎に作成するディレクトリ名は、本人確認受付No + "_" + 年月日時分秒
        return identificationReceiptNumber + "_" + datetime;
    }

    /**
     * ファイル名作成
     * （テストの為 private を外しています）
     */
    static String getFileName(IdentificationFiles identificationFiles, ValidIdentificationFile validIdentificationFile) {
        // ファイル名：本人確認受付番号_受信年月日_nn.拡張子.pgp（nn は受信ファイルの番号）
        String fileName = identificationFiles.getIdentificationReceiptNumber().getValue() + "_" +
                new SimpleDateFormat("yyyyMMdd").format(identificationFiles.getRequestTime()) + "_" +
                validIdentificationFile.getKey() +
                validIdentificationFile.getFileSuffix().getValueLowerCaseWithDot() +
                ".pgp";
        return fileName;
    }

    /**
     * SFTP ソケットを取得する
     */
    private BoSftpSession getSftpSession() {
        String environmentSshAuthSocket;
        try {
            String sshAgentFileName = propertyAccessor.getProperty("ssh-agent.file");
            environmentSshAuthSocket = BoSSHAgentConnector.getEnvSshAuthSocket(sshAgentFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String sftpUser = propertyAccessor.getProperty("sftp.user");
        String sftpHost = propertyAccessor.getProperty("sftp.host");
        int sftpPort = Integer.parseInt(propertyAccessor.getProperty("sftp.port"));
        return new BoSftpSession(environmentSshAuthSocket, sftpUser, sftpHost, sftpPort);
    }

    /**
     * PGP 公開鍵の鍵リングから、先頭に格納されている PGP 公開鍵を取得する
     */
    private PGPPublicKey getPgpPublicKey() {
        try {
            return new PGPPublicKeyRing(propertyAccessor.getProperty("openpgp.publickeyring")).getPublicKey();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
