package com.theflexproject.thunder.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        
		/* ********OpenRefactory Warning********
		 Potential data race detected!
		
		The data access in 
		mInstance == null
		may have race with 1 other access.
		
		The mentioned access is performed in a thread spawned by 
		thread.start()
		in file, MovieDetailsFragment.java.
		
		It may have contending concurrent access 
		
		in file, DatabaseClient.java, class DatabaseClient, method getInstance, 
		
		mInstance=new DatabaseClient(mCtx)
		
		*/
		if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}