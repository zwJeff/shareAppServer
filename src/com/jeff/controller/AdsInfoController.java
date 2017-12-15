package com.jeff.controller;

import com.jeff.model.AdsStatusModel;
import com.jeff.model.ResponseToApp;
import com.jeff.respModel.ResourceRespModel;
import mUtils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AdsInfoController extends BaseController {


    @RequestMapping(value = "/get_ads_info.do")
    public void geAtdsInfo(HttpServletResponse resp) {

        List<AdsStatusModel> resourcesList = adsService
                .getAdsStatus();
        if (resourcesList == null) {
            mRespopnseData = new ResponseToApp("400", "error", 0, "");
        } else {
            mRespopnseData = new ResponseToApp("200", "success",
                    resourcesList.size(), resourcesList);
        }

        try {
            ResponseUtils.resposeToApp(resp, mRespopnseData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
