package com.hck.zhuanqian.bean;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class UserBean {
	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("mac")
	private String mac;
	@JsonProperty("isok")
	private int isok;
	@JsonProperty("tg")
	private long tg;
	@JsonProperty("allMoney")
	private long allMoney;
	@JsonProperty("kedoubi")
	private long allKeDouBi;
	@JsonProperty("xinshou")
	private boolean isXinShou;
	@JsonProperty("qq")
	private String qq;
	@JsonProperty("zhifubao")
	private String zhifubao;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("shangjia1")
	private long shangjia1;
	@JsonProperty("shangjia2")
	private long shangjia2;
	@JsonProperty("shangjia3")
	private long shangjia3;
	@JsonProperty("shangjia4")
	private long shangjia4;
	@JsonProperty("shangjia5")
	private long shangjia5;
    @JsonProperty("TGMoney")
    private String TGMoney;
    
	public String getTGMoney() {
        return TGMoney;
    }
    public void setTGMoney(String tGMoney) {
        TGMoney = tGMoney;
    }
    public long getShangjia4() {
		return shangjia4;
	}
	public void setShangjia4(long shangjia4) {
		this.shangjia4 = shangjia4;
	}
	public long getShangjia5() {
		return shangjia5;
	}
	public void setShangjia5(long shangjia5) {
		this.shangjia5 = shangjia5;
	}
	public long getShangjia1() {
		return shangjia1;
	}
	public void setShangjia1(long shangjia1) {
		this.shangjia1 = shangjia1;
	}
	public long getShangjia2() {
		return shangjia2;
	}
	public void setShangjia2(long shangjia2) {
		this.shangjia2 = shangjia2;
	}
	public long getShangjia3() {
		return shangjia3;
	}
	public void setShangjia3(long shangjia3) {
		this.shangjia3 = shangjia3;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getZhifubao() {
		return zhifubao;
	}
	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isXinShou() {
		return isXinShou;
	}
	public void setXinShou(boolean isXinShou) {
		this.isXinShou = isXinShou;
	}
	public long getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(long allMoney) {
		this.allMoney = allMoney;
	}
	public long getAllKeDouBi() {
		return allKeDouBi;
	}
	public void setAllKeDouBi(long allKeDouBi) {
		this.allKeDouBi = allKeDouBi;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public int getIsok() {
		return isok;
	}
	public void setIsok(int isok) {
		this.isok = isok;
	}
	public long getTg() {
		return tg;
	}
	public void setTg(long tg) {
		this.tg = tg;
	}
}
