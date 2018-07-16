package com.scp.hibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.scp.constants.AppLevelConstants;
import com.scp.entity.EmployeeEntity;

public class Verification {
	
	public static String verification(String username,String password)
	{
		SessionFactory sf=HibernateUtil.sessionFactory();
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		Criteria cr=se.createCriteria(EmployeeEntity.class);
		cr.add(Restrictions.eq("empname",username));
		cr.add(Restrictions.eq("password",password));
		EmployeeEntity emp=(EmployeeEntity) cr.uniqueResult();
		HibernateUtil.flushNCommit(se, tr);
		if(emp==null)
		{
			System.out.println(AppLevelConstants.nonauthentic);
			//return"fail";
			return AppLevelConstants.fail;
		}
		else {
			System.out.println(AppLevelConstants.authenticate);
			return AppLevelConstants.success;
		}
		
		
	}

}
