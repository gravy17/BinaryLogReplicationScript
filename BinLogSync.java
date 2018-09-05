/**
 * 
 */
package com.replication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;

/**
 * @author Tofi
 *
 */
public class BinLogSync {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final Map<String, Long> tableMap = new HashMap<String, Long>();
        BinaryLogClient client =
          new BinaryLogClient("localhost", 3306, "root", "pass");

        client.registerEventListener(event -> {
            EventData data = event.getData();

            if(data instanceof TableMapEventData) {
                TableMapEventData tableData = (TableMapEventData)data;
                tableMap.put(tableData.getTable(), tableData.getTableId());
            }
            
            System.out.println(data);
        });
        client.connect();
	}

}
