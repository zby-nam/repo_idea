package com.lagou.domain;

public class ResourseVO {
    private int currentPage;
    private int pageSize;
    private String name;
    private int categoryId;
    private String url;

    @Override
    public String toString() {
        return "ResourseVO{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", url='" + url + '\'' +
                '}';
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
