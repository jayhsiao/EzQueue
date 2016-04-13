package com.ezqueue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezqueue.util.StringUtil;


@Entity
@Table(name = "queue_type")
public class QueueType extends ModelBase implements Serializable{

	private static final long serialVersionUID = 6643933935884393276L;
	
	@Id
	@Column(name = "queue_type_id")
	private String queueTypeId;
	@Column(name = "dscr")
	private String dscr;
	@Column(name = "icon_class")
	private String iconClass;
	@Column(name = "display_order")
	private Integer displayOrder;
	
	@Override
	public String toString() {
		return StringUtil.reflectionToString(this);
	}

	public String getQueueTypeId() {
		return queueTypeId;
	}

	public void setQueueTypeId(String queueTypeId) {
		this.queueTypeId = queueTypeId;
	}

	public String getDscr() {
		return dscr;
	}

	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

}
