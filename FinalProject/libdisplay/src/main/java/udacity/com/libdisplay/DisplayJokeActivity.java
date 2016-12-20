package udacity.com.libdisplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by techjini on 20/12/2016.
 */
public class DisplayJokeActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT_JOKE = "intent_extra_joke";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView text = (TextView) findViewById(R.id.joke);
        text.setText(getIntent().getStringExtra(EXTRA_INTENT_JOKE));
    }
}
