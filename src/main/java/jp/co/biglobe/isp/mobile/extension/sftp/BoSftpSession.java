package jp.co.biglobe.isp.mobile.extension.sftp;

import com.jcraft.jsch.*;
import com.jcraft.jsch.agentproxy.AgentProxyException;
import com.jcraft.jsch.agentproxy.Connector;
import com.jcraft.jsch.agentproxy.RemoteIdentityRepository;
import jp.co.biglobe.isp.mobile.extension.ssh.BoSSHAgentConnector;

import java.io.ByteArrayInputStream;
import java.util.Properties;

public class BoSftpSession {
    private final Session session;
    private final ChannelSftp channelSftp;

    /**
     * SFTP サーバ接続
     */
    public BoSftpSession(String sftpAgentFileName, String username, String hostname, int portNumber) {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();

            // BO 環境に合わせた ssh-agent 用コネクタを作成する
            Connector connector = BoSSHAgentConnector.createSSHAgentConnector(sftpAgentFileName);
            jsch.setIdentityRepository(new RemoteIdentityRepository(connector));

            session = jsch.getSession(username, hostname, portNumber);

            session.setConfig(getSessionProperties());
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
        } catch (JSchException e) {
            disConnect(session, channelSftp);
            throw new RuntimeException(e);
        } catch (AgentProxyException e) {
            disConnect(session, channelSftp);
            throw new RuntimeException(e);
        }
        this.session = session;
        this.channelSftp = channelSftp;
    }

    private static Properties getSessionProperties() {
        Properties configSession = new Properties();
        configSession.put("StrictHostKeyChecking", "no");
//      configSession.put("PreferredAuthentications", "publickey"); // サンプルでは設定しているが、不要っぽい
        return configSession;
    }

    /**
     * ディレクトリ作成(SFTP)
     */
    public void makeDirectory(String baseDirectory, String makeDirectory, int permissions) {
        try {
            this.channelSftp.cd(baseDirectory);
            this.channelSftp.mkdir(makeDirectory);
            this.channelSftp.chmod(permissions, makeDirectory);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ファイル書き込み（SFTP）
     */
    public void register(String directoryName, String fileName, int permissions, byte[] data) {
        try {
            this.channelSftp.cd(directoryName);
            this.channelSftp.put(new ByteArrayInputStream(data), fileName);
            this.channelSftp.chmod(permissions, fileName);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * リネーム
     */
    public void rename(String baseDirectory, String oldPath, String newPath) {
        try {
            this.channelSftp.cd(baseDirectory);
            this.channelSftp.rename(oldPath, newPath);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SFTP サーバ切断
     */
    public void disConnect() {
        disConnect(session, channelSftp);
    }
    private void disConnect(Session session, ChannelSftp channelSftp) {
        if (channelSftp != null && channelSftp.isConnected()) {
            channelSftp.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
