package com.sparrow.opinion.base.bean;

public class AjaxPageListResult extends BaseAjaxResult {

    /**
     *
     */
    private static final long serialVersionUID = -7921353326639739527L;
    private int pageNumber;
    private int pageSize;
    private int total;
    private Object rows;

    private AjaxPageListResult() {
        super();
    }

    public static AjaxPageListResult success(int pageNumber, int pageSize, int total, Object rows) {
        AjaxPageListResult result = new AjaxPageListResult();
        result.setStatus(0);
        result.setPageNumber(pageNumber);
        result.setPageSize(pageSize);
        result.setTotal(total);
        result.setRows(rows);
        return result;
    }

    public static AjaxPageListResult fail(String message) {
        AjaxPageListResult result = new AjaxPageListResult();
        result.setStatus(500);
        result.setMessage(message);
        return result;
    }

    public static AjaxPageListResult fail(int status, String message) {
        AjaxPageListResult result = new AjaxPageListResult();
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public int getPageCount() {
        if (pageSize > 0) {
            return total / pageSize + (total % pageSize == 0 ? 0 : 1);
        } else {
            return 0;
        }
    }

}
