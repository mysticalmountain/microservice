package com.andx.micro.api.core.dto;

/**
 * Created by andongxu on 17-6-14.
 */
public class PageResponse<T> extends Response<T> {

    private Long totalPages;
    private Long totalRecords;
    private Long currentPage;

    public PageResponse() {}

    public PageResponse(String errorCode, String errorMessage, Boolean success) {
        super(errorCode, errorMessage, success);
    }

    public PageResponse(String errorMessage, Boolean success) {
        super(errorMessage, success);
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }
}
