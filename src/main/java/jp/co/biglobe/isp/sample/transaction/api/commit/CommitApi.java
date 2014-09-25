package jp.co.biglobe.isp.sample.transaction.api.commit;

import jp.co.biglobe.isp.sample.transaction.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CommitApi {

    @Autowired
    private CommitResponse commitResponse;

    @Autowired
    private CommitService commitService;

    @RequestMapping(value = "/sample/commit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke() {
        commitService.commit();

        return commitResponse.build();
    }
}
