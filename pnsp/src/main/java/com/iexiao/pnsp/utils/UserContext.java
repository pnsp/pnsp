package com.iexiao.pnsp.utils;

import com.iexiao.pnsp.user.bean.PnspUserT;

public class UserContext {
	
	private static final ThreadLocal<PnspUserT> USER_HOLDER = new ThreadLocal<>();
	
	public static void setUser(PnspUserT user) {
		USER_HOLDER.set(user);
	}
	
	public static void remove() {
		USER_HOLDER.remove();
	}
	
	public static PnspUserT getUser() {
		return USER_HOLDER.get();
	}
}
