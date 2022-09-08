package io.nzbee.domain.bag;

import java.util.HashMap;
import java.util.Map;
import io.nzbee.domain.bag.item.IBagItem;

public class BagIssues {
	private Map<String, IBagItem> bagErrors = new HashMap<String, IBagItem>();
	
	public void logItemError(String key, IBagItem bagItem) {
		bagErrors.put(key,  bagItem);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : bagErrors.keySet()) {
			sb.append(key).append(bagErrors.get(key)).append("\n");
		}
		return sb.toString();
	}
	
	public boolean hasIssues() {
		return !bagErrors.isEmpty();
	}
}
