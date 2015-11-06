package io.github.si1en7ium.threadinghomework;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvPi;
    TextView tvTime;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        tvPi = (TextView) findViewById(R.id.tvPi);
        tvTime = (TextView) findViewById(R.id.tvTime);
        pb = (ProgressBar) findViewById(R.id.pbLoading);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFabClick(View view) {
        pb.setVisibility(ProgressBar.VISIBLE);
        final long startTime = System.currentTimeMillis();
        AsyncTask<Void, Void, Double> calculatePiTask = new AsyncTask<Void, Void, Double>() {
            @Override
            protected Double doInBackground(Void... params) {
                // запускаем долгое вычисление числа Пи методом монте-карло
                return MonteCarloPiCalculator.calculate(100000000l);
            }
            @Override
            protected void onPostExecute(Double pi) {
                tvPi.setText(pi.toString());
                pb.setVisibility(ProgressBar.INVISIBLE);
                Double runningTime = (System.currentTimeMillis() - startTime) / 1000.0;
                tvTime.setText(runningTime.toString());
            }
        };
        calculatePiTask.execute();
    }
}
