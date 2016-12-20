package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;


public class JokeAsyncTest extends AndroidTestCase {
    private static final String TAG = JokeAsyncTest.class.getSimpleName();
    private String joke;

    public void testBackend() throws ExecutionException, InterruptedException {
        JokeAsyncTask fetchJokeTask = new JokeAsyncTask();
        fetchJokeTask.setListner(new JokeAsyncTask.TaskListner() {
            @Override
            public void getTaskResult(String result) {
                joke = result;
            }
        }).execute(mContext).get();
        Log.d(TAG, "Joke result: " + joke);
        assertNotNull(joke);
        assertTrue(joke, joke.length() > 0);
    }
}
