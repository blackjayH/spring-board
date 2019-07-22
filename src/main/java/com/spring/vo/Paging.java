package com.spring.vo;

public class Paging {
	private int nowPage; // 페이지 넘버 1, 2, ...
	private int perPage; // 한 페이지 수 10 = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
	private int startPage;
	private int lastPage;
	private int display;
	
	// 0 >> 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
	public Paging(int nowPage, int perPage, int count) {
		super();
		if (nowPage < 1)
			nowPage = 1;
		// 페이지 마지막 초과
		if (nowPage > (count - 1) / perPage + 1)
			nowPage = (count - 1) / perPage + 1;
		this.startPage = (nowPage - 1) * perPage; // 시작 게시물 번호
		this.lastPage = (count - 1) / perPage + 1;
		this.nowPage = nowPage;
		this.perPage = perPage;
		this.display = (nowPage - 1) / 5;
	}

	public void setPaging() {
		
	}
	
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}	
}
