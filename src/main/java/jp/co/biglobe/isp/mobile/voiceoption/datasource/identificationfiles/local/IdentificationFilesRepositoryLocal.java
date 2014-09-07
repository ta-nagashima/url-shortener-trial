package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationfiles.local;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.ValidIdentificationFile;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.text.SimpleDateFormat;

@Repository
public class IdentificationFilesRepositoryLocal implements IdentificationFilesRepository {
    @Autowired
    private PropertyAccessor propertyAccessor;

    @Override
    public void register(IdentificationFiles identificationFiles) {
        // ディレクトリ名取得
        String directoryName = getDirectoryName(identificationFiles);
        // ディレクトリ作成
        makeDirectory(directoryName);
        // ファイル書き込み
        for (ValidIdentificationFile validIdentificationFile : identificationFiles.getIdentificationFiles()) {
            registerPGPEncryptedFile(directoryName, identificationFiles, validIdentificationFile);
        }
    }

    /**
     * ディレクトリ名作成（ローカル）
     */
    String getDirectoryName(IdentificationFiles identificationFiles) {
        String identificationReceiptNumber = identificationFiles.getIdentificationReceiptNumber().getValue();
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(identificationFiles.getRequestTime());
        String directoryName = identificationReceiptNumber + "_" + datetime;
        // ローカルの場合はフルパスを返す
        String fullPathFileName = propertyAccessor.getProperty("identification.store") + "/" + directoryName;
        if (fullPathFileName.substring(0, 2).equals("~/")) {
            fullPathFileName = System.getProperty("user.home") + fullPathFileName.substring(1);
        }
        return fullPathFileName;
    }

    /**
     * ディレクトリ作成（ローカル）
     */
    private static void makeDirectory(String directoryName) {
        File f = new File(directoryName);
        if (!f.mkdir()) {
            throw new RuntimeException("Failure Make Directory " + directoryName);
        }
    }

    /**
     * PGP 暗号化済ファイル書き込み（ローカル）
     */
    private void registerPGPEncryptedFile(String directoryName, IdentificationFiles identificationFiles, ValidIdentificationFile validIdentificationFile) {

    }

    /**
     * ファイル名作成
     */
    private static String getFileName(IdentificationFiles identificationFiles, ValidIdentificationFile validIdentificationFile) {
        // ファイル名：本人確認受付番号_受信年月日_nn.拡張子.pgp（nn は受信ファイルの番号）
        String fileName = identificationFiles.getIdentificationReceiptNumber().getValue() + "_" +
                new SimpleDateFormat("yyyyMMdd").format(identificationFiles.getRequestTime()) + "_" +
                validIdentificationFile.getKey() +
                validIdentificationFile.getFileSuffix().getValueLowerCaseWithDot() +
                ".pgp";
        return fileName;
    }

}
