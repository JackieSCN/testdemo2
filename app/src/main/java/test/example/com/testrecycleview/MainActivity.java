package test.example.com.testrecycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jackie.sun on 2015/11/30.
 */
public class MainActivity extends Activity {
//    static ItemConfig[] list= new ItemConfig[]{
//        new ItemConfig(R.id.demo1,DemoActivity.class),
//        new ItemConfig(R.id.demo2,DemoDragActivity.class)
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initBody();
    }

    private void initBody() {
        TextView demo1 = (TextView) findViewById(R.id.demo1);
        demo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d1 = new Intent(MainActivity.this,DemoActivity.class);
                startActivity(d1);
            }
        });
        TextView demo2 = (TextView) findViewById(R.id.demo2);
        demo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d1 = new Intent(MainActivity.this,DemoDragActivity.class);
                startActivity(d1);
            }
        });
        TextView demo3 = (TextView) findViewById(R.id.demo3);
        demo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d1 = new Intent(MainActivity.this,DemoRefreshActivity.class);
                startActivity(d1);
            }
        });
    }
}
