package jp.co.biglobe.isp.mobile.extension.encrypt;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class PGPPublicKey {
    private final org.bouncycastle.openpgp.PGPPublicKey pgpPublicKey;

    /**
     * コンストラクタで bouncycastle の公開鍵を保持する
     */
    public PGPPublicKey(org.bouncycastle.openpgp.PGPPublicKey pgpPublicKey) {
        this.pgpPublicKey = pgpPublicKey;
    }

    /**
     * PGP 公開鍵で暗号化されたデータ
     */
    public byte[] encrypt(byte[] data, String fileName, Date modificationTime) throws IOException, PGPException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            writeStreamPGPPacketTypes(outputStream, data, fileName, modificationTime, this.pgpPublicKey);
            outputStream.flush();
            return outputStream.toByteArray();
        } finally {
            outputStream.close();
        }
    }

    /**
     * PGP 公開鍵で暗号化されたデータ（RFC1991 2.4.1 ASCII Armor Formats）
     */
    public byte[] encryptAsciiArmorFormats(byte[] data, String fileName, Date modificationTime) throws IOException, PGPException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ArmoredOutputStream armoredOutputStream = new ArmoredOutputStream(outputStream);
            try {
                armoredOutputStream.setHeader("Version", "1.0");
                writeStreamPGPPacketTypes(armoredOutputStream, data, fileName, modificationTime, this.pgpPublicKey);
            } finally {
                armoredOutputStream.close();
            }
            outputStream.flush();
            return outputStream.toByteArray();
        } finally {
            outputStream.close();
        }
    }

    /**
     * RFC1991 6. PGP Packet Types を構成して出力する
     */
    private static void writeStreamPGPPacketTypes(OutputStream outputStream, byte[] data, String fileName, Date modificationTime, org.bouncycastle.openpgp.PGPPublicKey pgpPublicKey) throws IOException, PGPException {
        PGPEncryptedDataGenerator pgpEncryptedDataGenerator = new PGPEncryptedDataGenerator(new BcPGPDataEncryptorBuilder(PGPEncryptedDataGenerator.AES_256));
        try {
            pgpEncryptedDataGenerator.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(pgpPublicKey));
            // TODO キー長でバッファを設定すべき？！
            OutputStream encryptedOut = pgpEncryptedDataGenerator.open(outputStream, new byte[4096]);
            try {
                writeStreamPGPLiteralData(encryptedOut, data, fileName, modificationTime);
            } finally {
                encryptedOut.close();
            }
        } finally {
            pgpEncryptedDataGenerator.close();
        }
    }

    /**
     * RFC1991 6.1 Literal data packets を構成して出力する
     */
    private static void writeStreamPGPLiteralData(OutputStream outputStream, byte[] data, String fileName, Date modificationTime) throws IOException {
        PGPLiteralDataGenerator pgpLiteralDataGenerator = new PGPLiteralDataGenerator();
        try {
            OutputStream literalOut = pgpLiteralDataGenerator.open(outputStream, PGPLiteralDataGenerator.BINARY, fileName, data.length, modificationTime);
            try {
                literalOut.write(data);
            } finally {
                literalOut.close();
            }
        } finally {
            pgpLiteralDataGenerator.close();
        }
    }
}
