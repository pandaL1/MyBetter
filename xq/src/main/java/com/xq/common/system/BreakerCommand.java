/**
 * 
 */
package com.xq.common.system;

import java.util.List;

import org.apache.http.NameValuePair;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.xq.common.enums.HttpMethod;
import com.xq.common.enums.ResultEnum;
import com.xq.common.tools.NetworkRequestUtils;

import net.sf.json.JSONObject;

/**
 * @PMAPPBoot
 * @author bruce -李小强
 * @date 2018年11月22日 下午3:39:30
 * @vison 1.0
 */
public class BreakerCommand extends HystrixCommand<JSONObject> {
	private  HystrixInfo hystrixInfo;
	/** 
	 * 
	 * Q: 什么时候使用线程池隔离，什么使用信号量隔离？
	   A:  线程池隔离缺点是带来一定的开销，但不会阻塞请求线程，适合于于IO密集型的任务		
		信号量隔离使用用户请求线程，没有格外线程切换开销，使用与执行时间和执行逻辑都比较短的本地计算。比如CPU密集型的任务
		
		参数配置说明：
		1.配置线程值等待队列长度,默认值:-1 
		2.建议值:-1表示不等待直接拒绝,测试表明线程池使用直接决绝策略+ 合适大小的非回缩线程池效率最高.所以不建议修改此值。 
		3.当使用非回缩线程池时，queueSizeRejectionThreshold,keepAliveTimeMinutes 参数无效
		4.coreSize=高峰用户访问量*平均访问时长（ms）*99%+弹性范围（冗余线程）；
		
		详细参见：https://blog.csdn.net/tongtong_use/article/details/78611225
	*/
	public BreakerCommand(HystrixInfo hystrixInfo) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(hystrixInfo.getGroupKey()))  //必须
				 .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
				                .withExecutionTimeoutInMilliseconds(5000).//超时时间
				                withCircuitBreakerSleepWindowInMilliseconds(10000)//默认等待10秒进行重连尝试（半熔断）
				                .withCircuitBreakerErrorThresholdPercentage(70))  //错误率超过百分之70的时候进行熔断（视系统情况而定，即依赖重要性，默认50%，越重要，那么百分比越低，即容错率低）
				 .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(hystrixInfo.getAddressEnum().getType().getName()))  //可选,默认 ,优先使用这个作为线程池分组策略
				 .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)//每个线程池默认10个
						 .withAllowMaximumSizeToDivergeFromCoreSize(true)//允许MaximumSize生效
						 .withMaximumSize(30))//在熔断器未打开情况下，并发数高的情况下，可以向上扩展，非活动期回向系统归还线程数，即弹性核心线程
				 .andCommandKey(HystrixCommandKey.Factory.asKey(hystrixInfo.getAddressEnum().getAddress())));//Hystrix使用单例模式存储HystrixCommand，熔断机制就是根据单实例上的调用情况统计实现的，所以每个HystrixCommand要有自己的名字，用于区分，同时用于依赖调用的隔离
		this.hystrixInfo = hystrixInfo;
	}

	/**
	 * 除了HystrixBadRequestException异常之外，所有从run()方法抛出的异常都算作失败，并触发降级getFallback()和断路器逻辑。
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject run() {
		if(HttpMethod.put.equals(hystrixInfo.getHttpMethod())) {
			return NetworkRequestUtils.doGet(hystrixInfo.getAddressEnum().getAddress(),(List<NameValuePair>)hystrixInfo.getParams());
		}else if(HttpMethod.post.equals(hystrixInfo.getHttpMethod())){
			return NetworkRequestUtils.doPost(hystrixInfo.getAddressEnum().getAddress(),(List<NameValuePair>)hystrixInfo.getParams());
		}else if(HttpMethod.get.equals(hystrixInfo.getHttpMethod())){
			return NetworkRequestUtils.doGet(hystrixInfo.getAddressEnum().getAddress(),(List<NameValuePair>)hystrixInfo.getParams());
		}else if(HttpMethod.delete.equals(hystrixInfo.getHttpMethod())){
			
		}
		return NetworkRequestUtils.doPost(hystrixInfo.getAddressEnum().getAddress(), (List<NameValuePair>)hystrixInfo.getParams());
	}

	
	@Override
	protected JSONObject getFallback() {
		JSONObject obj=new JSONObject();
		obj.put(ResultEnum.RESULT.name(), "serverError");
		obj.put(ResultEnum.CODE.name(), 500);
		return obj;
	}
	
	/**
	 *  A. coreSize

		线程池coreSize
		
		默认值：10
		
		设置标准：qps*99meantime+breathing room
		
		B. maximumSize
		
		此属性设置最大线程池大小。 这是在不开始拒绝HystrixCommands的情况下可以支持的最大并发数。 请注意，此设置仅在您还设置allowMaximumSizeToDivergeFromCoreSize时才会生效。
		
		默认值：10
		
		C. maxQueueSize
		
		请求等待队列
		
		默认值：-1
		
		如果使用正数，队列将从SynchronizeQueue改为LinkedBlockingQueue
		
		D. queueSizeRejectionThreshold
		
		此属性设置队列大小拒绝阈值 - 即使未达到maxQueueSize也将发生拒绝的人为最大队列大小。 此属性存在，因为BlockingQueue的maxQueueSize不能动态更改，我们希望允许您动态更改影响拒绝的队列大小。
		
		默认值：5
		
		注意：如果maxQueueSize == -1，则此属性不适用。
		
		E. keepAliveTimeMinutes
		
		此属性设置保持活动时间，以分钟为单位。
		
		默认值：1
		
		F. allowMaximumSizeToDivergeFromCoreSize
		
		此属性允许maximumSize的配置生效。 那么该值可以等于或高于coreSize。 设置coreSize <maximumSize会创建一个线程池，该线程池可以支持maximumSize并发，但在相对不活动期间将向系统返回线程。 （以keepAliveTimeInMinutes为准）
		
		默认值：false
		
		G. metrics.rollingStats.timeInMilliseconds
		
		此属性设置statistical rolling窗口的持续时间（以毫秒为单位）。 这是为线程池保留多长时间。
		
		默认值：10000
		
		H. metrics.rollingStats.numBuckets
		
		此属性设置滚动统计窗口划分的桶数。
		注意：以下必须为true - “metrics.rollingStats.timeInMilliseconds%metrics.rollingStats.numBuckets == 0” -否则将引发异常。
		
		默认值：10

	 */
	// 调用实例
	public static void main(String[] args) throws Exception {
		
	}
}
