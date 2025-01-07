package com.example.conference.front.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pager {
    private int totalPages;

    private int currentPage;

    private final int ACCESSIBLE_PAGES_RANGE = 3;

    public Pager(int totalPages, int currentPage) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public List<Integer> getAccessiblePages(){
        int l = currentPage - ACCESSIBLE_PAGES_RANGE;
        int r = currentPage + ACCESSIBLE_PAGES_RANGE;
        List<Integer> result = new LinkedList<>();
        for (int i = l; i <= r; i++){
            if (i >= 0 && i <totalPages) result.add(i);
        }
        return  result;
    }
    public List<Integer> getAllPages(){
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < totalPages; i++){
             result.add(i);
        }
        return  result;
    }
    public int getFirstPage(){
        return 0;
    }

    public int getLastPage(){
        return totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
