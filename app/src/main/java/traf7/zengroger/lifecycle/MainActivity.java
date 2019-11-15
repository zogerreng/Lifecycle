package traf7.zengroger.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int create2, start2, resume2, pause2, stop2, restart2, destroy2;
    int[] counts;
    SharedPreferences sharedCounts;
    SharedPreferences.Editor preferencesEditor;
    Button resetCurrent, resetShared, showCurrent, showShared;
    TextView numbers, display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counts = new int[]{0, 0, 0, 0, 0, 0, 0};
        counts[0]++;
        create2++;

        sharedCounts = getSharedPreferences("sharedcounts", MODE_PRIVATE);
        preferencesEditor = sharedCounts.edit();
        preferencesEditor.putInt("create2", create2);
        preferencesEditor.apply();

        resetCurrent = findViewById(R.id.resetCurrent);
        resetShared = findViewById(R.id.resetShared);
        showCurrent = findViewById(R.id.showCurrent);
        showShared = findViewById(R.id.showShared);

        numbers = findViewById(R.id.numbers);
        display = findViewById(R.id.display);

        resetCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counts = new int[]{0, 0, 0, 0, 0, 0, 0};
            }
        });

        resetShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesEditor.clear().apply();
            }
        });

        showCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(counts[6]);
                String myString = "Create: " + counts[0] + "\nStart: " + counts[1] +
                        "\nResume: " + counts[2] + "\nPause: " + counts[3] + "\nStop: " + counts[4] +  "\nRestart: " + counts[5] +
                        "\nDestroy: " + counts[6];
                numbers.setText(myString);
                display.setText("Current counts");
            }
        });

         showShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myString = "Create: " + sharedCounts.getInt("create2", 0) + "\nStart: " + sharedCounts.getInt("start2", 0) +
                        "\nResume: " + sharedCounts.getInt("resume2", 0) + "\nPause: " + sharedCounts.getInt("pause2", 0) +
                        "\nStop: " + sharedCounts.getInt("stop2", 0) +  "\nRestart: " + sharedCounts.getInt("restart2", 0) +
                        "\nDestroy: " + sharedCounts.getInt("destroy2", 0);
                numbers.setText(myString);
                display.setText("Shared counts");
            }
        });
    }


    protected void onStart() {
        super.onStart();
        counts[1]++;
        start2++;
        preferencesEditor.putInt("start2", sharedCounts.getInt("start2", 0) + 1);
        preferencesEditor.apply();

    }

    protected void onResume() {
        super.onResume();
        counts[2]++;
        resume2++;
        preferencesEditor.putInt("resume2", sharedCounts.getInt("resume2", 0) + 1);
        preferencesEditor.apply();

    }

    protected void onPause() {
        super.onPause();
        counts[3]++;
        pause2++;
        preferencesEditor.putInt("pause2", sharedCounts.getInt("pause2", 0) + 1);
        preferencesEditor.apply();

    }

    protected void onStop(){
        super.onStop();
        counts[4]++;
        stop2++;
        preferencesEditor.putInt("stop2", sharedCounts.getInt("stop2", 0) + 1);
        preferencesEditor.apply();

    }

    protected void onRestart(){
        super.onRestart();
        counts[5]++;
        restart2++;
        preferencesEditor.putInt("restart2", sharedCounts.getInt("restart2", 0) + 1);
        preferencesEditor.apply();

    }

    protected void onDestroy(){
        super.onDestroy();
        destroy2++;
        counts[6]++;
        preferencesEditor.putInt("destroy2", sharedCounts.getInt("destroy2", 0) + 1).apply();
        System.out.println(sharedCounts.getInt("destroy2", 0));
    }
}
