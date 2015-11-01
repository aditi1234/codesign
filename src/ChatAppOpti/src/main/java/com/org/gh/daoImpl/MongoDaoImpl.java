package com.org.gh.daoImpl;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.org.gh.Constants.DBConstants;
import com.org.gh.dao.MongoDao;

public class MongoDaoImpl implements MongoDao {

	private DBCollection dBCollection ;
	private static MongoDaoImpl INSTANCE = null;
	
	private MongoDaoImpl() {
		setDBCollection();
	}
	
	public static MongoDaoImpl getInstance() {
		
		if (null == INSTANCE) {
			synchronized (INSTANCE) {
				if (null == INSTANCE) {
					INSTANCE = new MongoDaoImpl();
				}
			}
		}
		return INSTANCE;
	}
	
	
	@Override
	public String getWordCloud(String location) {
		// code to fetch word cloud data
		
		String jsonText;
		DBCursor cursor = dBCollection.find();
		
	    while(cursor.hasNext()) {
	    	jsonText = String.valueOf(cursor.next());
	        System.out.println(jsonText);
	    }
		return null;
	}

	private MongoClient createClient() {
		
		MongoClient mongo = null;
		try {
			mongo = new MongoClient(DBConstants.HOST_NAME, DBConstants.PORT);
			
			if(null != mongo) {
				return mongo;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return mongo;
	}
	
	private DB getDB() {
		
		MongoClient mongo = createClient();

		if(null != mongo) {
			DB db = mongo.getDB(DBConstants.DB_NAME);
			return db;
		}
		else {
			return null;
		}
	}
	
	private DBCollection setDBCollection() {
		DB db = null;
		if (null != (db = getDB())) {
			dBCollection = db.getCollection(DBConstants.COLLECTION_NAME);
			return dBCollection;
		}
		else {
			return null;
		}
	}
	
}
