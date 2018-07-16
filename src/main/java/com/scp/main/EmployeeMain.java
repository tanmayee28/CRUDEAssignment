package com.scp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.hibernate.service.Service;

import com.scp.constants.AppLevelConstants;
import com.scp.constants.AppLevelConstants.EmpConstants;
import com.scp.entity.EmployeeEntity;
import com.scp.hibernateUtil.Verification;

import com.scp.pojo.EmployeePojo;
import com.scp.service.EmployeeService;
import com.scp.serviceImplementation.ServiceImplementation;

public class EmployeeMain {

	public static void main(String[]args) throws IOException {
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	EmployeeService s1=new ServiceImplementation();
		
	System.out.println("R you a authenticated User:(y/n) ");
	String option=br.readLine();
	if(option.equalsIgnoreCase("y")) 
	{
	
		System.out.println("Verification System......");
		System.out.println("Enter User name:");
		String uname1=br.readLine();
		System.out.println("Enter Password:");
		String pwdnew=br.readLine();
		
		String result=Verification.verification(uname1,pwdnew);
		if(result.equals(AppLevelConstants.success))
		{
			while(true)
			{
				System.out.println("Enter your Option:(1.UpdateUser 2.DeleteUser 3.getAllUSers 4.searchEmployee 5.getById 6.exit");
				int opt=Integer.parseInt(br.readLine());
				switch(opt)
				{
					
		            
			    	case 1:
			    		System.out.println("Enter Your Id(Your id should be same)");
			    		int uid=Integer.parseInt(br.readLine());
			    		EmployeePojo emp=s1.getByIdPojo(uid);
			    		System.out.println("Enter Updated User name:");
			    		String user=br.readLine();
			    		emp.setEmpname(user);
			    		System.out.println("Enter your new Password:");
			    		String newpwd=br.readLine();
			    		emp.setPassword(newpwd);
			    		s1.updateEmployee(emp);
			    		break;
			    	case 2:
			    		System.out.println("Enter id of user you want to delete:");
			    		int opt1=Integer.parseInt(br.readLine());
			    		s1.deleteEmployee(opt1);
			    		System.out.println("Deleted successfully");
			    		break;
			    		
			    	case 3:
			    		System.out.println("List of Users :");
			    		List<EmployeePojo>list=s1.getAllEmployee();
			    		System.out.println(list);
			    		break;
			    		
			    	case 4:
			    			HashMap<AppLevelConstants.EmpConstants,String>map=new HashMap<AppLevelConstants.EmpConstants, String>();
			    			System.out.println("how you want search(1.Id 2.name 3.password)");
			    			System.out.println("Enter your option(1/2/3) :");
			    			int option1=Integer.parseInt(br.readLine());
			    			switch(option1)
			    			{
			    			
			    			case 1:
			    					System.out.println("Enter Employee Id you want to search: ");
			    					int searchid=Integer.parseInt(br.readLine());
			    					EmployeePojo pojo=s1.getByIdPojo(searchid);
			    					System.out.println(pojo);
			    					break;
			    			case 2:
			    				System.out.println("Enter Employee Name you want to search:");
				    			String str=br.readLine();
				    			map.put(EmpConstants.EMPNAME,str);
				    			System.out.println("mylist is   "+s1.searchEmployee(map));
				    			break;
				    			
			    			case 3:
			    				System.out.println("Enter password you want to search:");
				    			String str1=br.readLine();
				    			map.put(EmpConstants.PASSWORD,str1);
				    			System.out.println("mylist is   "+s1.searchEmployee(map));
				    			break;
			    				
			    			}
			    			
			    			break;
			    		
			    	case 5:
			    		System.out.println("See you soon..");
			    		System.exit(0);
			    		
			    	default:
			    		System.out.println("You can still select other option :");
			    		continue;
			}  		
		}
		}
		else 
		{
			System.out.println(AppLevelConstants.nonauthentic);
			System.out.println("User Id and Password incorrect...");
			System.exit(0);
		}
	
	}
	else {
			if(option.equalsIgnoreCase("n"))
			{
				System.out.println("Create account :");
				System.out.println("Enter Id:");
        		int id=Integer.parseInt(br.readLine());
        		System.out.println("Enter UserName:");
        		String uname=br.readLine();
	            System.out.println("Enter Your Password :");
	            String pwd=br.readLine();
	            EmployeePojo u1=new EmployeePojo(id,uname,pwd,"true");
	            s1.addEmployee(u1);
	            System.out.println("successfully added");
	            System.exit(0);
			}
		}

}


	}


