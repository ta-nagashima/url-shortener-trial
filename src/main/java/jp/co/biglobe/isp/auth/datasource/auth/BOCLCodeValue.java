package jp.co.biglobe.isp.auth.datasource.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
enum BOCLCodeValue {

    SUCCESS("0"),
    ERROR_SESSION_TIMEOUT_1("1"),
    ERROR_SESSION_TIMEOUT_100("100"),
    ERROR_INVALID_STATUS("98");

    @Getter
    private String rawValue;

}
