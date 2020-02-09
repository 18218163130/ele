package com.ele.service;

import com.ele.entity.News;
import com.ele.utils.DataGridView;
import com.ele.vo.NewsVo;

import java.util.List;

/**
 * 系统公告业务接口
 * @Author dongwf
 * @Date 2019/10/14
 */
public interface NewsService {
    /**
     * 添加一条新的公告
     * @param newsVo
     */
    void addNews(NewsVo newsVo);

    /**
     * 修改公告内容
     * @param newsVo
     */
    void updateNews(NewsVo newsVo);

    /**
     * 根据Id删除公告内容
     * @param newsId
     */
    void deleteNews(Integer newsId);

    /**
     * 批量删除公告
     * @param ids
     */
    void deleteBatchNews(Integer[] ids);

    /**
     * 查询所有公告，并分页
     * @param newsVo
     * @return
     */
    DataGridView queryAllNews(NewsVo newsVo);

    /**
     *
     * @param newVo
     * @return
     */
    List<News> queryNewsList(NewsVo newVo);

}
