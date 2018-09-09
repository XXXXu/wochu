package com.wochu.enity;

public class Category {
    @Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", parentId=" + parentId
				+ ", categoryName=" + categoryName + ", level=" + level
				+ ", explain=" + explain + "]";
	}

	private Integer categoryId;

    private Integer parentId;

    private String categoryName;

    private Integer level;

    private String explain;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }
}