package pe.gob.qw.supervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.evaluation_bidder).setOnClickListener(this);
        findViewById(R.id.evaluation_provider).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.evaluation_bidder:
                goToActivity(TabFragmentActivity.class);
                break;
            case R.id.evaluation_provider:
                goToActivity(TabFragmentActivity.class);
                break;
        }
    }

    private void goToActivity(Class activity){
        Intent intent= new Intent(this,activity);
        startActivity(intent);
    }
}
