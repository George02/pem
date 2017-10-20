/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.ui.model;

/**
 *
 * @author george
 */
public class Paginator {

    private int startPosition;
    private int itemsCount;
    private int itemsPerPage;
    private int currentPageIndex;
    private int lastPageIndex;
    private int nrOfPages;

    public Paginator() {
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startIndex) {
        this.startPosition = startIndex;
    }

    public int getMaxResults() {
        return itemsPerPage;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getNrOfPages() {
        return (int) Math.ceil((double) itemsCount / (double) itemsPerPage);
    }

}
