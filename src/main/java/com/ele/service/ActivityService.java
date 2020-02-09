package com.ele.service;

import com.ele.entity.Activity;
import com.ele.mapper.ActivityMapper;
import com.ele.utils.DataGridView;
import com.ele.vo.ActivityVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/22
 */
public interface ActivityService {
    /**
     * 参加报名
     * @param activityVo
     */
    void joinActivity(ActivityVo activityVo);

    Activity findActivity(Integer activityId,String userId);

    DataGridView findActivityList(ActivityVo activityVo);

    void deleteActivityById(Integer activityId);

    void deleteBatchActivity(Integer[] ids);

    List<Activity> batchExceportActivity(Integer[] ids);

}
