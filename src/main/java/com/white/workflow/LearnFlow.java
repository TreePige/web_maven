package com.white.workflow;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class LearnFlow {
	static void log(Object obj) {
		System.out.println(obj);
	}

	static void run(ProcessEngine processEngine) throws Exception {
		
		RepositoryService repositoryService = processEngine.getRepositoryService();

		repositoryService.createDeployment().addClasspathResource("qingjia.bpmn20.xml").deploy();

		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<>();
		variables.put("userid", "10010");
		variables.put("day", 2);
		variables.put("comment", "pass");
		
		runtimeService.startProcessInstanceByKey("rongdu.forLeave", variables);

		TaskService taskService = processEngine.getTaskService();

		TaskQuery query = taskService.createTaskQuery();

		List<Task> tasks = query.list();

		for (Task task : tasks) {
			log(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}

		log("--------------------");

		tasks = query.list();

		for (Task task : tasks) {
			log(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}

		log("--------------------");

		tasks = query.list();

		for (Task task : tasks) {
			log(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}
	}

	public static void main(String[] args) {
		try {
			String url = "activiti.cfg.xml";
			
			ProcessEngine processEngine = ProcessEngineConfiguration
					.createProcessEngineConfigurationFromResource(url).buildProcessEngine();
			try {
				run(processEngine);
			} catch (Exception e) {
				e.printStackTrace();
			}

			processEngine.close();
			//test();
		} catch (Exception e) {
			e.printStackTrace();
		}
    

	}

	private static void test() {
		//声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3307/web_maven";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = (Connection) DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = (Statement) con.createStatement();
            //要执行的SQL语句
            /*String sql = "select * from emp";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
            System.out.println("-----------------");  
            System.out.println("姓名" + "\t" + "职称");  
            System.out.println("-----------------");  
             
            String job = null;
            String id = null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("job");
                //获取stuid这列数据
                id = rs.getString("ename");

                //输出结果
                System.out.println(id + "\t" + job);
            }
            rs.close();*/
          
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
	}
	

}