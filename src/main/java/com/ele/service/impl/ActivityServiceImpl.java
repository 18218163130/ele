package com.ele.service.impl;

import com.ele.entity.Activity;
import com.ele.mapper.ActivityMapper;
import com.ele.service.ActivityService;
import com.ele.utils.DataGridView;
import com.ele.vo.ActivityVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/22
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void joinActivity(ActivityVo activityVo) {
        activityMapper.insert(activityVo);
    }

    @Override
    public Activity findActivity(Integer activityId, String userId) {
        return activityMapper.select(activityId, userId);
    }

    @Override
    public DataGridView findActivityList(ActivityVo activityVo) {
        Page page = PageHelper.startPage(activityVo.getPage(), activityVo.getLimit());
        List<Activity> activityList = activityMapper.selectActivity(activityVo);
        return new DataGridView(page.getTotal(), activityList);
    }

    @Override
    public void deleteActivityById(Integer activityId) {
        activityMapper.delete(activityId);
    }

    @Override
    public void deleteBatchActivity(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteActivityById(id);
        }
    }

    @Override
    public List<Activity> batchExceportActivity(Integer[] ids) {
        return activityMapper.queryActivityById(ids);
    }

}
