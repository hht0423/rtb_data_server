/**
 * 
 */
package com.ocean.rtb.stat;

/**   
 *    
 * 项目名称：Rtb_Statistics   
 * 创建人：Alex 569246607@qq.com   
 * 创建时间：2019年4月3日 下午5:37:35      
 * @version    
 *    
 */
public class SyncTest{
	
	public static void main(String[] args){
	
		final SyncBean bean1=new SyncBean("bean1");
		new Thread(){
			public void run(){
				
				bean1.syn1();
			}
		}.start();
		
		new Thread(){
			public void run(){
				bean1.syn1();
				
				SyncBean bean2=new SyncBean("bean2");
				bean2.syn1();
			}
		}.start();
	}
	
	
}


