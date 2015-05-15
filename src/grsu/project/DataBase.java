package grsu.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;

public class DataBase {
	private Jedis jedis;
	private Gson gson;
	private String key;

	public DataBase(String key) {
		this.key = key;
		jedis = new Jedis("localhost", 6379, 60000000);
		jedis.connect();
		gson = new Gson();
	}

	public void save(LogRecordList logRecords) {
		jedis.zadd(key, logRecords.getScore(), gson.toJson(logRecords));
	}

	public List<LogRecordList> getRange(Date startDate, Date endDate) {
		Long minScore = Long.MIN_VALUE;
		Long maxScore = Long.MAX_VALUE;
		if (startDate != null) {
			minScore = startDate.getTime();
		}
		if (startDate != null) {
			maxScore = endDate.getTime();
		}
		return getRange(minScore, maxScore);
	}

	private List<LogRecordList> getRange(Long minScore, Long maxScore) {
		LogRecordList logRecords = new LogRecordList();
		List<LogRecordList> query = new ArrayList<LogRecordList>();
		Set<String> lines = jedis.zrange(key, minScore, maxScore);
		for (String line : lines) {
			logRecords = gson.fromJson(line, LogRecordList.class);
			query.add(logRecords);
		}
		return query;

	}
}
