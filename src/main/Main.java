package main;
import java.util.Scanner;


/**
 * Copyright (C), 2001-2012, Aha
 * <br/>Date:2012-11-12 
 * @author  Aha tzl77258511@gmail.com
 * @version  1.0
 */
public class Main {
	
	// sum用来记录皇后放置成功的不同布局数；upperlim用来标记所有列都已经放置好了皇后。
	static long sum = 0, upperlim = 1;
	
	public static void test(long row, long ld, long rd)
	{
	    if (row != upperlim)
	    {
	    	// row，ld，rd进行“或”运算，求得所有可以放置皇后的列,对应位为0，  
	        // 然后再取反后“与”上全1的数，来求得当前所有可以放置皇后的位置，对应列改为1  
	        // 也就是求取当前哪些列可以放置皇后  
	        long pos = upperlim & ~(row | ld | rd);
	        while (pos != 0 )    // 0 -- 皇后没有地方可放，回溯
	        {
	        	// 拷贝pos最右边为1的bit，其余bit置0  
	            // 也就是取得可以放皇后的最右边的列  
	            long p = pos & -pos;                                                
	  
	            // 将pos最右边为1的bit清零  
	            // 也就是为获取下一次的最右可用列使用做准备，  
	            // 程序将来会回溯到这个位置继续试探  
	            pos -= p;                         
	  
	            // row + p，将当前列置1，表示记录这次皇后放置的列。  
	            // (ld + p) << 1，标记当前皇后左边相邻的列不允许下一个皇后放置。  
	            // (ld + p) >> 1，标记当前皇后右边相邻的列不允许下一个皇后放置。  
	            // 此处的移位操作实际上是记录对角线上的限制，只是因为问题都化归  
	            // 到一行网格上来解决，所以表示为列的限制就可以了。显然，随着移位  
	            // 在每次选择列之前进行，原来N×N网格中某个已放置的皇后针对其对角线  
	            // 上产生的限制都被记录下来了  
	            test(row + p, (ld + p) << 1, (rd + p) >> 1);
	        }
	    }
	    else
	    {
	    	// row的所有位都为1，即找到了一个成功的布局，回溯 
	        sum++;
	    }
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("N皇后问题[回溯风格](只能处理1-64皇后,输入-1结束程序),请输入N:");
		int n = -1;
	    long tm;
	    Scanner scanner = new Scanner(System.in);
	    while (true) {
	        System.out.println("请输入N:");
	        sum = 0;
	        upperlim = 1;
	        n = scanner.nextInt();
	        
	        if (n == -1) {
	            break;
	        }
	        
	        
	        tm = System.currentTimeMillis();
	        
	        // 因为长整型数的限制，最大只能64位，  
	        // 如果想处理N大于64的皇后问题，需要  
	        // 用bitset数据结构进行存储
	        if ((n < 1) || (n > 64))
	        {
	        	System.out.println(" ֻ只能计算1-64之间");
	            return ;
	        }
	        System.out.println("正在计算...");
	        
	        // N个皇后只需N位存储，N列中某列有皇后则对应bit置1。
	        upperlim = (upperlim << n) - 1;
	        
	        test(0, 0, 0);
	        System.out.println("共有" + sum + "种排列, 计算时间" + (float)(System.currentTimeMillis() - tm)/1000 + "秒\n");
	    }
	    scanner.close();
	}
	
	

}
