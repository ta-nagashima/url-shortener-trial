package jp.co.biglobe.isp.mobile.extension.ssh;

import com.jcraft.jsch.agentproxy.AgentProxyException;
import com.jcraft.jsch.agentproxy.Buffer;
import com.jcraft.jsch.agentproxy.Connector;
import com.jcraft.jsch.agentproxy.USocketFactory;
import com.jcraft.jsch.agentproxy.connector.SSHAgentConnector;
import com.jcraft.jsch.agentproxy.usocket.JNAUSocketFactory;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一般的に ssh-agent は環境変数 SSH_AUTH_SOCK から情報を取得するが、当該クラスは ssh-agent が出力した情報を読み込んで動作する
 *
 * com.jcraft.jsch.agentproxy.connector.SSHAgentConnector v.0.0.7 からパクって作成されています。
 */
public class BoSSHAgentConnector implements Connector {
    private final USocketFactory factory;
    private final String environmentSshAuthSock;

    /**
     * BO 環境で動作している ssh-agent に対応した Connector を作成する
     *
     * 　　注！SSHAgentConnector が対応できる場合は、当該クラスではなく SSHAgentConnector を返す
     */
    public static Connector createSSHAgentConnector(String environmentSshAuthSock) throws AgentProxyException {
        if (SSHAgentConnector.isConnectorAvailable()) {
            return new SSHAgentConnector(new JNAUSocketFactory());
        }
        return new BoSSHAgentConnector(new JNAUSocketFactory(), environmentSshAuthSock);
    }

    /**
     * コンストラクタ（createSSHAgentConnector からのみ呼び出される）
     * 　※コピー元 SSHAgentConnector に environmentSshAuthSock を追加しました。
     */
    private BoSSHAgentConnector(USocketFactory factory, String environmentSshAuthSock) throws AgentProxyException {
        this.factory = factory;
        this.environmentSshAuthSock = environmentSshAuthSock;

        // checking if factory is really functional.
        USocketFactory.Socket sock = null;
        try {
            sock = open();
        } catch (IOException e) {
            throw new AgentProxyException(e.toString());
        } catch (Exception e) {
            throw new AgentProxyException(e.toString());
        } finally {
            try {
                if (sock != null) {
                    sock.close();
                }
            } catch (IOException e) {
                throw new AgentProxyException(e.toString());
            }
        }
    }

    /**
     * コピー元と同一です。
     */
    @Override
    public String getName() {
        return "ssh-agent";
    }

    /**
     * コピー元とは、SSH 通信の判定条件を変えています。
     */
    @Override
    public boolean isAvailable() {
        return environmentSshAuthSock != null;
    }

    /**
     * コピー元と同一です。
     */
    @Override
    public void query(Buffer buffer) throws AgentProxyException {
        USocketFactory.Socket sock = null;
        try {
            sock = open();
            sock.write(buffer.buffer, 0, buffer.getLength());
            buffer.rewind();
            int i = sock.readFull(buffer.buffer, 0, 4);  // length
            i = buffer.getInt();
            buffer.rewind();
            buffer.checkFreeSize(i);
            i = sock.readFull(buffer.buffer, 0, i);
        } catch (IOException e) {
            throw new AgentProxyException(e.toString());
        } finally {
            try {
                if (sock != null) {
                    sock.close();
                }
            } catch (IOException e) {
                throw new AgentProxyException(e.toString());
            }
        }
    }

    /**
     * コピー元は環境変数から ssh-agent のソケットを取得していますが、コピーして書き換えています。
     */
    private USocketFactory.Socket open() throws IOException {
        if (environmentSshAuthSock == null) {
            throw new IOException("SSH_AUTH_SOCK is not defined.");
        }
        try {
            return factory.open(environmentSshAuthSock);
        } catch (IOException e) {
            throw new IOException(environmentSshAuthSock, e);
        }
    }

    /**
     * 以下、BO 環境に合わせて、ファイルから ssh-agent のソケットを取得する処理を追加しています。
     */

    /**
     * ssh-agent のソケットをファイルから取得する
     *
     * 指定が無い場合は null を返す
     *
     * @param sshAgentFileName ssh-agent が標準出力に出力した結果を格納したファイル
     */
    public static String getEnvSshAuthSocket(String sshAgentFileName) throws IOException {
        if (sshAgentFileName == null) {
            return null;
        }
        String envSshAuthSocket = getEnvSshAuthSocket(new File(sshAgentFileName));
        if (envSshAuthSocket == null) {
            throw new RuntimeException("SSH_AUTH_SOCK is not defined in " + sshAgentFileName);
        }
        return envSshAuthSocket;
    }

    /**
     * ssh-agent のソケットをファイルから取得する
     *
     * 指定が無い場合は null を返す
     *
     * @param sshAgentFile ssh-agent が標準出力に出力した結果を格納したファイル
     */
    private static String getEnvSshAuthSocket(File sshAgentFile) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(sshAgentFile);
        try {
            return getEnvSshAuthSocket(fileReader);
        } finally {
            fileReader.close();
        }
    }

    /**
     * ssh-agent のソケットを取得する
     */
    private static String getEnvSshAuthSocket(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            return getEnvSshAuthSocket(bufferedReader);
        } finally {
            bufferedReader.close();
        }
    }

    /**
     * ssh-agent のソケットを取得する
     *
     * "SSH_AUTH_SOCK=" で始まりセミコロンで終わる、途中の文字列を取得する
     */
    private static String getEnvSshAuthSocket(BufferedReader bufferedReader) throws IOException {
        Pattern pattern = Pattern.compile("^SSH_AUTH_SOCK=([^;]+);");
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                return m.group(1);
            }
        }
        return null;
    }
}
