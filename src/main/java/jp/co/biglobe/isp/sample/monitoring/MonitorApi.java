package jp.co.biglobe.isp.sample.monitoring;


import jp.co.biglobe.lib.plugin.db.DbGlobalCache;
import jp.co.biglobe.lib.plugin.event.RequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonitorApi {

    @Autowired
    private DbGlobalCache dbGlobalCache;

    @Autowired
    private RequestEvent requestEvent;

    @RequestMapping(value = "/monitoring/check", method = RequestMethod.POST)
    @ResponseBody
    public String check() {
        return "ok";
    }

    @RequestMapping(value = "/monitoring/cacheClear", method = RequestMethod.POST)
    @ResponseBody
    public String cacheClear() {
        // mybatisのキャッシュクリア
        dbGlobalCache.clear();
        return "ok";
    }

    @RequestMapping(value = "/monitoring/requestId", method = RequestMethod.POST)
    @ResponseBody
    public String requestId() {
        return requestEvent.getRequestId();
    }

}
