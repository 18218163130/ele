package com.ele.controller;

import com.ele.entity.Emp;
import com.ele.entity.News;
import com.ele.service.NewsService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

/**
 * 系统公告操作
 * @Author dongwf
 * @Date 2019/10/14
 */
@Controller
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 添加系统公告操作
     * @param newsVo
     * @return
     */
    @RequestMapping("addNews")
    @ResponseBody
    public ResultObj addNews(NewsVo newsVo){
        try{
            // 设置添加的时间
            newsVo.setCreateTime(new Date());
            // 获取session中的对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Emp emp = (Emp) request.getSession().getAttribute("emp");
            // 设置发布者名称
            newsVo.setEditer(emp.getEmpName());
            newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        }catch(Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载所有系统公告操作
     * @param newsVo
     * @return
     */
    @RequestMapping("loadAllNews")
    @ResponseBody
    public DataGridView loadAllNews(NewsVo newsVo){
        return newsService.queryAllNews(newsVo);
    }

    @RequestMapping("loadNewsList")
    @ResponseBody
    public List<News> loadNewsList(NewsVo newsVo, HttpSession session){
        return newsService.queryNewsList(newsVo);
    }

    /**
     * 修改系统公告
     * @param newsVo
     * @return
     */
    @RequestMapping("updateNews")
    @ResponseBody
    public ResultObj updateNews(NewsVo newsVo){
        try{
            // 设置添加的时间
            newsVo.setCreateTime(new Date());
            // 获取session中的对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Emp emp = (Emp) request.getSession().getAttribute("emp");
            // 设置发布者名称
            newsVo.setEditer(emp.getEmpName());
            newsService.updateNews(newsVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除系统公告记录
     * @param newsVo
     * @return
     */
    @RequestMapping("deleteNews")
    @ResponseBody
    public ResultObj deleteNews(NewsVo newsVo){
        try{
            newsService.deleteNews(newsVo.getNewsId());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 删除或批量删除系统公告
     * @param newsVo
     * @return
     */
    @RequestMapping("deleteBatchNews")
    @ResponseBody
    public ResultObj deleteBatchNews(NewsVo newsVo){
        try{
            newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

}
