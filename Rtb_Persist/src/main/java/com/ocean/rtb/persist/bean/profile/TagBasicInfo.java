package com.ocean.rtb.persist.bean.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.inveno.base.BaseModel;

@Entity
@Table(name = "rtb_profile_tag")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagBasicInfo    extends BaseModel {
	
	private static final long serialVersionUID = -7193561112873794887L;
	@Id
	@Column(name = "pt_id")
	@GeneratedValue(generator = "TagBasicInfoGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "TagBasicInfoGenerate", strategy = "native")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "tag_type")
	private String tagType;
	@Column(name = "op_type")
	private String opType;
	@Column(name = "parent")
	private String parent;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTagType() {
		return tagType;
	}
	public String getOpType() {
		return opType;
	}
	public String getParent() {
		return parent;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
}
