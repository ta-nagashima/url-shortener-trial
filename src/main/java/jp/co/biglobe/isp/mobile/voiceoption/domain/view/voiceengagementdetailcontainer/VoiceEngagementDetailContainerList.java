package jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class VoiceEngagementDetailContainerList {

    @Getter
    private final List<VoiceEngagementDetailContainer> list;

    public int getCount(){
        return list.size();
    }
}
