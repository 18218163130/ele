package com.ele.service.impl;

import com.ele.entity.Log;
import com.ele.mapper.LogMapper;
import com.ele.service.LogService;
import com.ele.utils.DataGridView;
import com.ele.vo.LogVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志业务操作实体类
 * @Author dongwf
 * @Date 2019/10/12
 */
@Transactional // 打开事务管理
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLogInfo(LogVo logVo) {
        logMapper.insertLogInfo(logVo);
    }

    @Override
    public void deleteLogInfo(Integer logId) {
        logMapper.deleteLogById(logId);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer id:ids){
            this.deleteLogInfo(id);
        }
    }

    @Override
    public DataGridView queryAllLog(LogVo logVo) {
        // 分页
        Page page = PageHelper.startPage(logVo.getPage(),logVo.getLimit());
        List<Log> logs = logMapper.queryAllLog(logVo);
        return new DataGridView(page.getTotal(),logs);
    }
}
