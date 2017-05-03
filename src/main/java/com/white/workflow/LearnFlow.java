package com.white.workflow;

import java.io.InputStream;
import java.sql.DriverManager;
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

		String url = "config/bpmn/qingjia.bpmn20.xml";
		repositoryService.createDeployment().addClasspathResource(url).deploy();

		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<>();
		variables.put("userid", "10010");
		variables.put("day", 10);
		variables.put("comment", "pass");
		
		runtimeService.startProcessInstanceByKey("rongdu.forLeave", variables);

		TaskService taskService = processEngine.getTaskService();

		TaskQuery query = taskService.createTaskQuery();
		
		logout(variables, taskService, query);
	}

	private static void logout(Map<String, Object> variables, TaskService taskService, TaskQuery query) {
		List<Task> tasks;
		log("--------------------");

		tasks = query.list();
		if (tasks.size() == 0) {
			return;
		}
		for (Task task : tasks) {
			log(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}
		logout(variables, taskService, query);
	}

	public static void main(String[] args) {
		try {
			//URL url1 = ClassLoader.getSystemClassLoader().getResource("/activiti.cfg.xml");
			String url = "/config/simpleActiviti/activiti.cfg.xml";
			
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

	@SuppressWarnings("unused")
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
