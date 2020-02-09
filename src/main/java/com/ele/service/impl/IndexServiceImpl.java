package com.ele.service.impl;

import com.ele.entity.Index;
import com.ele.mapper.IndexMapper;
import com.ele.service.IndexService;
import com.ele.utils.DataGridView;
import com.ele.vo.IndexVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 指标管理业务实现类
 * @Author dongwf
 * @Date 2019/10/15
 */
@Service
@Transactional
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;


    @Override
    public void addIndex(IndexVo indexVo) {
        indexMapper.addIndex(indexVo);
    }

    @Override
    public void updateIndex(IndexVo indexVo) {
        indexMapper.updateIndex(indexVo);
    }

    @Override
    public void deleteIndexById(Integer indexId) {
        indexMapper.deleteIndex(indexId);
    }

    @Override
    public void deleteBatchIndex(Integer[] ids) {
        for(Integer id:ids){
            this.deleteIndexById(id);
        }
    }

    @Override
    public DataGridView queryAllIndex(IndexVo indexVo) {
        // 分页
        Page page = PageHelper.startPage(indexVo.getPage(),indexVo.getLimit());
        List<Index> indexList = indexMapper.queryAllIndex(indexVo);
        return new DataGridView(page.getTotal(),indexList);
    }
}
