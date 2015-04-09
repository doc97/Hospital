package com.tint.hospital;

public class EconomySystem {
	private int money;

	public int getMoney() {
		return money;
	}
	
	public void addMoney(int money) {
		setMoney(this.money + money);
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
