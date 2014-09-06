package jp.co.biglobe.isp.mobile.extension.encrypt;

import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PGPPublicKeyRing {
    private final org.bouncycastle.openpgp.PGPPublicKeyRing pgpPublicKeyRing;

    /**
     * 公開鍵の鍵リングをファイルから取得する
     */
    public PGPPublicKeyRing(String fileName) throws IOException {
        this.pgpPublicKeyRing = new org.bouncycastle.openpgp.PGPPublicKeyRing(getBytesFromFile(fileName), new BcKeyFingerprintCalculator());
    }

    /**
     * ファイルデータを byte 配列で取得する
     */
    private static byte[] getBytesFromFile(String fileName) throws IOException {
        if (fileName.startsWith("~/")) {
            fileName = System.getProperty("user.home") + fileName.substring(1);
        }

        byte[] data = new byte[(int) (new File(fileName).length())];
        FileInputStream fs = new FileInputStream(fileName);
        try {
            fs.read(data);
        } finally {
            fs.close();
        }
        return data;
    }

    /**
     * 公開鍵を取得する
     */
    public PGPPublicKey getPublicKey() {
        return new PGPPublicKey(this.pgpPublicKeyRing.getPublicKey());
    }

    /**
     * 鍵IDを指定して公開鍵を取得する
     */
    public PGPPublicKey getPublicKey(long l) {
        return new PGPPublicKey(this.pgpPublicKeyRing.getPublicKey(l));
    }
}
