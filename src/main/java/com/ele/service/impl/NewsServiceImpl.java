package com.ele.service.impl;

import com.ele.entity.News;
import com.ele.mapper.NewsMapper;
import com.ele.service.NewsService;
import com.ele.utils.DataGridView;
import com.ele.vo.NewsVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公告业务实现类
 * @Author dongwf
 * @Date 2019/10/14
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;


    @Override
    public void addNews(NewsVo newsVo) {
        newsMapper.insertNews(newsVo);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        newsMapper.updateNews(newsVo);
    }

    @Override
    public void deleteNews(Integer newsId) {
        newsMapper.deleteNewsById(newsId);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer id:ids){
            this.deleteNews(id);
        }
    }

    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<News> page = PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<News> newsList = newsMapper.queryAllNews(newsVo);
        return new DataGridView(page.getTotal(),newsList);
    }

    @Override
    public List<News> queryNewsList(NewsVo newVo) {
        return newsMapper.queryAllNews(newVo);
    }
}
