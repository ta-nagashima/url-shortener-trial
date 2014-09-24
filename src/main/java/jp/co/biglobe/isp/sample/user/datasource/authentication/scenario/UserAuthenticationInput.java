package jp.co.biglobe.isp.sample.user.datasource.authentication.scenario;


import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.Setter;

public class UserAuthenticationInput {
    // ドメイン
    @Mapping("REMOTE_HOST")
    @Setter
    private String remoteHost;

    // IPアドレス
    @Mapping("REMOTE_ADDR")
    @Setter
    private String remoteAddr;

    // 使用ブラウザ
    @Mapping("HTTP_USER_AGENT")
    @Setter
    private String httpUserAgent;

    // ユーザ
    @Mapping("REMOTE_USER")
    @Setter
    private String remoteUser;
}
