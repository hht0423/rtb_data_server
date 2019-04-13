/**
 * 
 */
package com.ocean.rtb.stat;

/**   
 *    
 * 项目名称：Rtb_Statistics   
 * 创建人：Alex 569246607@qq.com   
 * 创建时间：2019年4月3日 下午5:44:06      
 * @version    
 *    
 */
public class SyncBean{
	private String name;
	public SyncBean(String name){
		this.name=name;
	}
	public synchronized void syn1(){
		System.out.println(name+" syn1 run begin");
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+"  syn1 run end");
	} 
	public synchronized void syn2(){
		System.out.println(name+" syn2 run begin");
	} 
}