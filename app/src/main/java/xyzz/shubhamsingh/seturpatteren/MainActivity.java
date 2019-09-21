package xyzz.shubhamsingh.seturpatteren;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    String save_pattern_key="pattern_code";
    PatternLockView mPatternLockView;
    String final_pattern="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        final String save_pattern=Paper.book().read(save_pattern_key);
        if(save_pattern !=null && !save_pattern.equals("null")) {
            setContentView(R.layout.pattern_screen);
            mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                /**
                 * Fired when the pattern drawing has just started
                 */
                @Override
                public void onStarted() {

                }

                /**
                 * Fired when the pattern is still being drawn and progressed to
                 * one more {@link PatternLockView.Dot}
                 *
                 * @param progressPattern
                 */
                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                /**
                 * Fired when the user has completed drawing the pattern and has moved their finger away
                 * from the view
                 *
                 * @param pattern
                 */
                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {

                    String final_pattern = PatternLockUtils.patternToString(mPatternLockView, pattern);

                    {
                        if (final_pattern.equals(save_pattern))
                            Toast.makeText(MainActivity.this, "SAHI HAI !!!!!!!!", Toast.LENGTH_SHORT).show();


                        else Toast.makeText(MainActivity.this, "GALAT HAI!!!!!!!!", Toast.LENGTH_SHORT).show();
                    }
                }

                /**
                 * Fired when the patten has been cleared from the view
                 */
                @Override
                public void onCleared() {

                }
            });

        }

        else
        {
            setContentView(R.layout.activity_main);
            mPatternLockView=(PatternLockView)findViewById(R.id.pattern_lock_view);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                /**
                 * Fired when the pattern drawing has just started
                 */
                @Override
                public void onStarted() {

                }

                /**
                 * Fired when the pattern is still being drawn and progressed to
                 * one more {@link PatternLockView.Dot}
                 *
                 * @param progressPattern
                 */
                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                /**
                 * Fired when the user has completed drawing the pattern and has moved their finger away
                 * from the view
                 *
                 * @param pattern
                 */
                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {

                    final_pattern=PatternLockUtils.patternToString(mPatternLockView,pattern);


                }

                /**
                 * Fired when the patten has been cleared from the view
                 */
                @Override
                public void onCleared() {

                }
            });
            Button btnSetup=(Button)findViewById(R.id.btnSetPattern);
            btnSetup.setOnClickListener(new View.OnClickListener() {
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {



                    Paper.book().write(save_pattern_key,final_pattern);
Toast.makeText(MainActivity.this,"THIS IS SAVED!!!! ",Toast.LENGTH_SHORT).show();


                }
            });
        }


        }
    }

