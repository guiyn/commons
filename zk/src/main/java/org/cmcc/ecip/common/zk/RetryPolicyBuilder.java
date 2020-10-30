package org.cmcc.ecip.common.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.retry.RetryUntilElapsed;

public class RetryPolicyBuilder {

	public enum defRetryPolicy {

		ExponentialBackoff(createExponentialBackoffRetry(1000, 5)), NTimes(
				createRetryNTimes(3, 5000)), OneTime(createRetryOneTime(5000)), Forever(
				createRetryForever(3000)), UntilElapsed(
				createRetryUntilElapsed(2000, 3000));

		RetryPolicy rp;

		defRetryPolicy(RetryPolicy _rp) {
			rp = _rp;
		}

		public RetryPolicy getPolicy() {
			return rp;
		}
	}

	/**
	 * （推荐） 同步创建zk示例，原生api是异步的 这一步是设置重连策略
	 * 
	 * 构造器参数： curator链接zookeeper的策略:ExponentialBackoffRetry
	 * baseSleepTimeMs：初始sleep的时间 maxRetries：最大重试次数 maxSleepMs：最大重试时间
	 */
	public static RetryPolicy createExponentialBackoffRetry(
			int baseSleepTimeMs, int maxRetries) {

		return new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);

	}

	/**
	 * int n, int sleepMsBetweenRetries
	 * 
	 * @param baseSleepTimeMs
	 * @param maxRetries
	 * @return
	 */
	public static RetryPolicy createRetryNTimes(int n, int sleepMsBetweenRetries) {

		return new RetryNTimes(n, sleepMsBetweenRetries);

	}

	/**
	 * 
	 * curator链接zookeeper的策略:RetryOneTime
	 * 
	 * 构造器参数： sleepMsBetweenRetry:每次重试间隔的时间 这个策略只会重试一次
	 */
	public static RetryPolicy createRetryOneTime(int sleepMsBetweenRetry) {

		return new RetryOneTime(sleepMsBetweenRetry);

	}

	/**
	 * 永远重试
	 * 
	 * @param retryIntervalMs
	 * @return
	 */
	public static RetryPolicy createRetryForever(int retryIntervalMs) {
		return new RetryForever(retryIntervalMs);
	}

	/**
	 * curator链接zookeeper的策略:RetryUntilElapsed
	 * 
	 * 构造器参数： maxElapsedTimeMs:最大重试时间 sleepMsBetweenRetries:每次重试间隔
	 * 重试时间超过maxElapsedTimeMs后，就不再重试
	 */
	public static RetryPolicy createRetryUntilElapsed(int maxElapsedTimeMs,
			int sleepMsBetweenRetries) {
		return new RetryUntilElapsed(maxElapsedTimeMs, sleepMsBetweenRetries);
	}

}
