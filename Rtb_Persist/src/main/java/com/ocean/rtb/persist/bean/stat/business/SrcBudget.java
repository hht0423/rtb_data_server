package com.ocean.rtb.persist.bean.stat.business;

public class SrcBudget {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3458712676844841819L;
	private long total;
	private long waste;
	private long balance;
	public long getTotal() {
		return total;
	}
	public long getWaste() {
		return waste;
	}
	public long getBalance() {
		return balance;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public void setWaste(long waste) {
		this.waste = waste;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}

	
}
