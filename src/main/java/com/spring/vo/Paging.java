package com.spring.vo;

public class Paging {
	private int rootPage; // 0 >> 1, 2, 3, 4, 5 // 1 >> 6, 7, 8, 9, 10 버튼 표시 
	// 루트페이지에 5개의 페이지 씩 존재
	private int nowPage; // 현재 페이지 넘버 1, 2, ...
	private int perPage; // 한 페이지 당 게시물의 수 >>> 현재 2개로 설정됨
	private int startPage;
	private int lastPage;
	 
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
		this.rootPage = (nowPage - 1) / 5;
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

	public int getRootPage() {
		return rootPage;
	}

	public void setRootPage(int rootPage) {
		this.rootPage = rootPage;
	}	
}
