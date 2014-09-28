package jp.co.biglobe.isp.sample.transaction.api.rollback;

import jp.co.biglobe.isp.sample.transaction.service.RollbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RollbackApi {

    @Autowired
    private RollbackResponse rollbackResponse;

    @Autowired
    private RollbackService rollbackService;

    @RequestMapping(value = "/sample/rollback", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke() {
        rollbackService.rollback();

        return rollbackResponse.build();
    }
}
