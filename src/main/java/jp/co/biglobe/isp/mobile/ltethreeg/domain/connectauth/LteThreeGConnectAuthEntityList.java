package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGConnectAuthEntityList {

    @Getter
    private final List<LteThreeGConnectAuthUserEntity> value;
}
