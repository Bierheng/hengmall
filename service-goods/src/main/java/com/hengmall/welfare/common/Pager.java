package com.hengmall.welfare.common;

import java.io.Serializable;
import java.util.List;

/**
 * <b> 分页通用类 </b>
 * @param <T>
 */
public class Pager<T> implements Serializable {

    /**
     * currentPage 当前页
     */
    private int currentPage = 1;
    /**
     * pageSize 每页大小
     */
    private int pageSize = 10;
    /**
     * pageTotal 总页数
     */
    private int pageTotal;
    /**
     * recordTotal 总条数
     */
    private long recordTotal = 0;
    /**
     * content 每页的内容
     */
    private List<T> content;


    public Pager() {

    }

    public Pager(int currentPage, int pageSize, int pageTotal, long recordTotal, List<T> content) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.recordTotal = recordTotal;
        this.content = content;
    }

    /**
     * 设置当前页 <br>
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 设置每页大小,也可以不用赋值,默认大小为10条 <br>
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置总条数,默认为0 <br>
     * @param recordTotal
     */
    public void setRecordTotal(long recordTotal) {
        this.recordTotal = recordTotal;
    }

    /**
     * 设置分页内容 <br>
     * @param content
     */
    public void setContent(List<T> content) {
        this.content = content;
    }



    // 放开私有属性
    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public long getRecordTotal() {
        return recordTotal;
    }


    public List<T> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Pager [currentPage=" + currentPage + ", pageSize=" + pageSize
                + ", pageTotal=" + pageTotal + ", recordTotal=" + recordTotal
                + ", content=" + content + "]";
    }



}
