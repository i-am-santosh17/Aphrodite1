package com.example.aphrodite;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Subject.class,Notes.class,WeekNotes.class},version = 5,exportSchema = false)
public  abstract class SubjectRoomDatabase extends RoomDatabase {

    public abstract SubjectDao subjectDao();

    private static SubjectRoomDatabase INSTANCE;

    public static SubjectRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SubjectRoomDatabase.class) {
                if (INSTANCE == null) {
                    // creating databse here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SubjectRoomDatabase.class, "subject_notes_database").fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    //  new PopulateDbAsync(INSTANCE).execute();
                }
            };

   /* private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SubjectDao mDao;
        String[] subjects = {"COA","CN"};
        String[] notes={"Hello","Hii"};

        PopulateDbAsync(SubjectRoomDatabase db) {
            mDao = db.subjectDao();

        }
            @Override
            protected Void doInBackground ( final Void...params){
                // Start the app with a clean database every time.
                // Not needed if you only populate the database
                // when it is first created
                mDao.deleteAll();

                for (int i = 0; i <= subjects.length - 1; i++) {
                    Subject subject = new Subject(subjects[i]);
                    mDao.insert(subject);
                    Notes note=new Notes();
                    note.mNote=notes[i];
                    note.subject=subjects[i];
                    mDao.insertn(note);
                }

                return null;
            }



    }*/
}
