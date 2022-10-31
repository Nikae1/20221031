package server;

import java.util.ArrayList;

import server.beans.ToDoBean;

public class TaskManager {

	public TaskManager() {
		
	}
	
	
	/* 특정 계정의 특정한 달의 등록되어 있는 할 일의 날짜 리스트 가져오기 */
	/* 1.clientData : serviceCode=9&accessCode=hoonzzang&date=202210 --> Bean Data 
	 * 2. todo --> Dao.getToDoList --> ArrayList<ToDoBean>
	 * 
	 * */
	public String getToDoDateCtl(String clientData) {
		DataAccessObject dao = new DataAccessObject();
		ArrayList<ToDoBean> toDoList;
		
		ToDoBean todo = (ToDoBean)this.setBean(clientData);
		todo.setFileIdx(2);
		return this.convertServerData(dao.getToDoList(todo));
		
		
		
		
//		return this.convertServerData(toDoList);
//		return this.convertServerData
//				(dao.getToDoList((ToDoBean)
//						this.setBean(clientData))); 
	}
	
	private String convertServerData(ArrayList<ToDoBean> list) {
		StringBuffer serverData = new StringBuffer();
		/* 예제 = 1:10:15:16:20:일때 ----> 마지막 콜론은 떼주기  */
		for(ToDoBean todo : list) {
			serverData.append(todo.getStartDate().substring(6, 8));
			serverData.append(":");
			
		}
		/* 마지막 추가된  " : " 없애기 */
		serverData.deleteCharAt(serverData.length()-1);
		return serverData.toString();	
		}
	
	private Object setBean(String clientData) {
		Object object = null;
		String[] splitData = clientData.split("&");
		switch(splitData[0].split("=")[1]) {
		case "9":
			/* serviceCode=9&accessCode=hoonzzang&date=202210  
			 * servicecode를 임의로 9로 설정하여 사용
			 * 
			 * */
			object = new ToDoBean();
			((ToDoBean)object).setAccessCode(splitData[1].split("=")[1]);
			((ToDoBean)object).setStartDate(splitData[2].split("=")[1]);
			break;
		}
				
		return object;
	} 
	
	
	
	
	
	
}
