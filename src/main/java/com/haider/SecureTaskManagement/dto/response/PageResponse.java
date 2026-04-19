package com.haider.SecureTaskManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Generic paginated response")
public class PageResponse<T> {
    @Schema(
            description = "List of items for the current page",
            example = "[{...}, {...}]"
    )
    private List<T> content;

    @Schema(description = "Current page number (0-based)", example = "0")
    private int page;

    @Schema(description = "Number of elements per page", example = "10")
    private int size;

    @Schema(description = "Total number of elements", example = "45")
    private long totalElements;

    @Schema(description = "Total number of pages", example = "5")
    private int totalPages;

    @Schema(description = "Whether this is the last page", example = "false")
    private boolean last;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
