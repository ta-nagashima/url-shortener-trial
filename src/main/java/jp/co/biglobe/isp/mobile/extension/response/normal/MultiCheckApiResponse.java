package jp.co.biglobe.isp.mobile.extension.response.normal;

import java.util.Map;

/**
 * 複数チェック系APIのデフォルトレスポンス
 */
public interface MultiCheckApiResponse {
    public Map build(Map businessStatusMap);

    public Map build(Map businessStatusMap, Map body);
}
