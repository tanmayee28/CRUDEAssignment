package com.scp.serviceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.scp.constants.AppLevelConstants;
import com.scp.constants.AppLevelConstants.EmpConstants;
import com.scp.entity.EmployeeEntity;
import com.scp.hibernateUtil.HibernateUtil;
import com.scp.pojo.EmployeePojo;
import com.scp.service.EmployeeService;

public class ServiceImplementation implements EmployeeService{

	static SessionFactory sf=null;
	static 
	{
		 sf=HibernateUtil.sessionFactory();
	}

	public boolean addEmployee(EmployeePojo p) {
		// TODO Auto-generated method stub
		//SessionFactory sf=HibernateUtil.sessionFactory();
		Session se=null;
		Transaction tr=null;
		try {
			se=sf.openSession();
			 tr=se.beginTransaction();
			int id=(Integer) se.save(mapPojoToEntity(p));
			HibernateUtil.flushNCommit(se, tr);
			if(id!=0)
			{
				System.out.println("Record added successfully..");
				return true;
			}
			//return id!=0;
		}catch(Exception e) {
			tr.rollback();
		}
		finally {
			HibernateUtil.sessionClose(se);
		}
		return false;
	}

	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		System.out.println(id);
		EmployeeEntity emp=se.get(EmployeeEntity.class,id);
		System.out.println(emp);
		if(emp==null) {
			return false;
		}
		else {
			try {
					se.delete(emp);
					System.out.println("Record deleted successfully..");
					
					HibernateUtil.flushNCommit(se, tr);
					List<EmployeePojo>list=getAllEmployee();
					System.out.println(list);
					return true;
			}catch(Exception e) {
				tr.rollback();
			}finally
			{
				se.close();
			}
		}
		
		return false;
	}

	public EmployeePojo getEmployee(int id) {
		// TODO Auto-generated method stub
		Session se=null;
		Transaction tr=null;
		try {
			se=sf.openSession();
			tr=se.beginTransaction();
			EmployeePojo emp=mapEntityToPojo(se.get(EmployeeEntity.class,id));
			if(emp!=null)
			{
				return emp;
			}
			
		}catch(Exception e)
		{
			tr.rollback();
		}finally {
			HibernateUtil.sessionClose(se);
		}
		return null;
	}

	public boolean updateEmployee(EmployeePojo e) {
		// TODO Auto-generated method stub
		Session se=null;
		Transaction tr=null;
		EmployeeEntity emp=mapPojoToEntity(e);
		if(e==null)
		{
			System.out.println("Entity that your trying to update doesnt exist..");
			return false;
		}else
		{
		try {
			se=sf.openSession();
			tr=se.beginTransaction();
			se.merge(emp);
			HibernateUtil.flushNCommit(se, tr);
			System.out.println("Record Updated successfully..");
			return true;
		
		}
		catch(Exception e1){
			tr.rollback();
		}
		finally
		{
			HibernateUtil.sessionClose(se);
		}
		}
		return false;
	}

	public List<EmployeePojo> getAllEmployee() {
		// TODO Auto-generated method stub
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		try {
			
			List<EmployeePojo>list=mapListEntityTOListPojo(se.createQuery("from EmployeeEntity").list());
			HibernateUtil.flushNCommit(se, tr);
			System.out.println(list);
			
			return list;
		}catch(Exception e) {
			tr.rollback();
		}
		finally {
			HibernateUtil.sessionClose(se);
		}
		return null;
	}
	
	public List<EmployeePojo> searchEmployee(HashMap<AppLevelConstants.EmpConstants, String> searchElements) {
		// TODO Auto-generated method stub
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		Criteria cr=se.createCriteria(EmployeeEntity.class);
		List<EmployeePojo>list1=null;
		Set<Entry<EmpConstants,String>>entrylist=searchElements.entrySet();
		
		for (Entry<EmpConstants, String> entry2 : entrylist) {

			cr.add(Restrictions.eq(entry2.getKey().toString().toLowerCase(), entry2.getValue().toString()));
		
		}
		List <EmployeeEntity> elist =cr.list();			 
		System.out.println(list1);
		return mapListEntityTOListPojo(elist);
		
	}
	
	private EmployeePojo mapEntityToPojo(EmployeeEntity emp)
	{
		if(emp==null)
			return null;
		EmployeePojo emp1=new EmployeePojo();
		emp1.setAvtive(emp.isAvtive());
		emp1.setEmpid(emp.getEmpid());
		emp1.setEmpname(emp.getEmpname());
		emp1.setPassword(emp.getPassword());
		return emp1;
	}
	private EmployeeEntity mapPojoToEntity(EmployeePojo emp)
	{
		if(emp==null)
			return null;
		EmployeeEntity emp1=new EmployeeEntity();
		emp1.setEmpid(emp.getEmpid());
		emp1.setAvtive(emp.isAvtive());
		emp1.setEmpname(emp.getEmpname());
		emp1.setPassword(emp.getPassword());
		return emp1;
	}
	private List<EmployeePojo> mapListEntityTOListPojo(List<EmployeeEntity>list)
	{
		List<EmployeePojo>list1=new ArrayList<EmployeePojo>();
		if(list==null && list.isEmpty())
		{
	
			return null;
		}else {
				for(EmployeeEntity p:list)
				{
					list1.add(mapEntityToPojo(p));
				}
		}
	
		return list1;
	}

	public EmployeeEntity getById(int id) {
		// TODO Auto-generated method stub
		Session se=sf.openSession();
		Transaction tr=se.beginTransaction();
		EmployeeEntity emp=se.get(EmployeeEntity.class,id);
		return emp;
	}
	
	public EmployeePojo getByIdPojo(int id)
	{
		return mapEntityToPojo(getById(id));
	}
	
}
