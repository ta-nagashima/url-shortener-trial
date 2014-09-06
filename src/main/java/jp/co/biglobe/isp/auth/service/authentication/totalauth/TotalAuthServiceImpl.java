package jp.co.biglobe.isp.auth.service.authentication.totalauth;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import org.springframework.stereotype.Service;

@Service
public interface TotalAuthServiceImpl {
    public TotalAuthResult auth(TotalAuth totalAuth);
}
