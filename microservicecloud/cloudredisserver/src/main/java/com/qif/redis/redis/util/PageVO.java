package com.qif.redis.redis.util;

import lombok.Data;

/**
 * 分装分页参数VO，所有用到分页的实体继承此类，接受分页参数
 * Created by wujian on 2017/5/16.
 */
@Data
public class PageVO {

    private int pageIndex;

    //偏移量
    private int offset;

    //默认每页条数
    private int pageSize;

    public  void  setPageIndex(int pageIndex){
        this.pageIndex = pageIndex;
        initParamter();
    }

    public  void  setPageSize (int pageSize){
        this.pageSize = pageSize;
        initParamter();
    }

    /**
     * 根据前端传值，计算分页参数
     */
    private void initParamter(){
        if(pageIndex<1){
            pageIndex = 1;//默认第一页
        }
        if (this.pageSize == 0) {
            this.pageSize = 10;//默认每页显示10条
        }

        final int offset = (this.pageIndex - 1) * this.pageSize;
        setOffset(offset);
    }

}
