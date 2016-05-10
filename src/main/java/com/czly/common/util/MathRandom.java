package com.czly.common.util;

/**
 * JAVA 返回随机数，并根据概率、比率
 * 
 * @author xiaohuiyang
 * 
 */
public class MathRandom {
	
	/**
	 * 0出现的概率为%0.5555555556
	 */
	private static double rate0 = 0.5555555556;
	/**
	 * 1出现的概率为%0.3333333333
	 */
	private static double rate1 = 0.3333333333;
	/**
	 * 2出现的概率为%0.1111111111
	 */
	private static double rate2 = 0.1111111111;
	/**
	 * 3出现的概率为%
	 */
	//public static double rate3 = 0.0002777006;
	/**
	 * 4出现的概率为%4
	 */
	//public static double rate4 = 0.04;
	/**
	 * 5出现的概率为%1
	 */ 
	//public static double rate5 = 0.01;

	/**
	 * Math.random()产生一个double型的随机数，判断一下 例如0出现的概率为%50，则介于0到0.50中间的返回0
	 * 
	 * @return int
	 * 
	 */
	private static int PercentageRandom() {
		double randomNumber;
		randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber <= rate0) {
			return 0;
		} else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
			return 1;
		} else if (randomNumber >= rate0 + rate1
				&& randomNumber <= rate0 + rate1 + rate2) {
			return 2;
		}
//		else if (randomNumber >= rate0 + rate1 + rate2
//				&& randomNumber <= rate0 + rate1 + rate2 + rate3) {
//			return 3;
//		}
//		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3
//				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
//			return 4;
//		}else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4
//				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4
//						+ rate5) {
//			return 5;
//		}
		return -1;
	}
	
	/**
	 * 获取奖励话费
	 * @return
	 */
	public static int getprice(){
		int pricecode = PercentageRandom();
		if(pricecode == 0){
			return 2;
		}
		if(pricecode == 1){
			return 5;
		}
		if(pricecode == 2){
			return 10;
		}
		return 2;
	}

	/**
	 * 测试主程序
	 * 
	 * @param agrs
	 */
	public static void main(String[] agrs) {
		int i = 0;
		
		for(int p = 0 ; p < 1 ; p++){
			
			int q = 0;//2
			int w = 0;//5
			int e = 0;//10
			int r = 0;//6plus
			
			int qq = 0;//2
			int ww = 0;//5
			int ee = 0;//10
			int rr = 0;//6plus
		
			for (i = 0; i < 500; i++)// 打印100个测试概率的准确性
			{
				int k = PercentageRandom();
				int l = PercentageRandom();
				if(k == 0){
					q ++;
				}
				if(k == 1){
					w ++;
				}
				if(k == 2){
					e++;
				}
				if(k == 3){
					r++;
				}
				
				if(l == 0){
					qq ++;
				}
				if(l == 1){
					ww ++;
				}
				if(l == 2){
					ee++;
				}
				if(l == 3){
					rr++;
				}
				System.out.println("aaa"+getprice());
				System.out.println("bbb"+getprice());
			}
//			System.out.println("a2======="+q+" b2=========="+qq);
//			System.out.println("a5======="+w+" b5=========="+ww);
//			System.out.println("a10======="+e+" b10=========="+ee);
//			System.out.println("a6======="+r+" b6=========="+rr);
//			System.out.println("a总金额" + ((q*2) + (w*5) + (e*10)));
//			System.out.println("b总金额" + ((qq*2) + (ww*5) + (ee*10)));
		}
		
	}
}