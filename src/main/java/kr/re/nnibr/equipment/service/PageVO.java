/**
 * 
 */
package kr.re.nnibr.equipment.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author user
 * @Date : 2016. 10. 25. 
 * @author : 오정수, Oh Jeongsu
 */
public class PageVO implements Serializable {
	
	/** 검색시작일 */
    private String searchBgnDe = "";
    
    /** 검색조건 */
    private String searchCnd = "";
    
    /** 검색종료일 */
    private String searchEndDe = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 검색단어 리스트1 */
    private List<String> searchWrdList1;
    
    /** 검색단어  리스트2*/
    private List<String> searchWrdList2;
    
    /** 검색단어  리스트3*/
    private List<String> searchWrdList3;
    
    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 0;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** rowNo */
    private int rowNo = 0;

//	==============	공통 항목	=============
    private int moderatorID;
	private int createResearcherID;
	private String createResearcherName;
	private String createDate;
	private int modifyResearcherID;
	private String modifyResearcherName;
	private String modifyDate;
	private int deleteResearcherID;
	private String deleteResearcherName;
	private String deleteDate;
//	==============	.공통 항목	=============
	
	public String getSearchBgnDe() {
		return searchBgnDe;
	}
	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}
	public String getSearchCnd() {
		return searchCnd;
	}
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}
	public String getSearchEndDe() {
		return searchEndDe;
	}
	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}
	public String getSearchWrd() {
		return searchWrd;
	}
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	public List<String> getSearchWrdList1() {
		return searchWrdList1;
	}
	public void setSearchWrdList1(List<String> searchWrdList1) {
		this.searchWrdList1 = searchWrdList1;
	}
	public List<String> getSearchWrdList2() {
		return searchWrdList2;
	}
	public void setSearchWrdList2(List<String> searchWrdList2) {
		this.searchWrdList2 = searchWrdList2;
	}
	public List<String> getSearchWrdList3() {
		return searchWrdList3;
	}
	public void setSearchWrdList3(List<String> searchWrdList3) {
		this.searchWrdList3 = searchWrdList3;
	}
	public String getSortOrdr() {
		return sortOrdr;
	}
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public int getModeratorID() {
		return moderatorID;
	}
	public void setModeratorID(int moderatorID) {
		this.moderatorID = moderatorID;
	}
	public int getCreateResearcherID() {
		return createResearcherID;
	}
	public void setCreateResearcherID(int createResearcherID) {
		this.createResearcherID = createResearcherID;
	}
	public String getCreateResearcherName() {
		return createResearcherName;
	}
	public void setCreateResearcherName(String createResearcherName) {
		this.createResearcherName = createResearcherName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getModifyResearcherID() {
		return modifyResearcherID;
	}
	public void setModifyResearcherID(int modifyResearcherID) {
		this.modifyResearcherID = modifyResearcherID;
	}
	public String getModifyResearcherName() {
		return modifyResearcherName;
	}
	public void setModifyResearcherName(String modifyResearcherName) {
		this.modifyResearcherName = modifyResearcherName;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getDeleteResearcherID() {
		return deleteResearcherID;
	}
	public void setDeleteResearcherID(int deleteResearcherID) {
		this.deleteResearcherID = deleteResearcherID;
	}
	public String getDeleteResearcherName() {
		return deleteResearcherName;
	}
	public void setDeleteResearcherName(String deleteResearcherName) {
		this.deleteResearcherName = deleteResearcherName;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

}
