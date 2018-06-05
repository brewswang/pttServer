package com.dventus.ptt.ftp;

import com.dventus.dao.common.Impl.GenericDaoImpl;

public class PttFTPFactory {

    private GenericDaoImpl dao;

	public PttFTPFactory(GenericDaoImpl dao1) {
		super();
		this.dao = dao1;
	}

	public GenericDaoImpl getDao() {
		return this.dao;
	}

	public void setDao(GenericDaoImpl dao1) {
		this.dao = dao1;
	}


	public PttFTP createFeeder() {
		final PttFTP pttftp = new PttFTP(this.getDao());
		return pttftp;
	}

}
