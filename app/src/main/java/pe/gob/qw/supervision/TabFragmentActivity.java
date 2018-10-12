package pe.gob.qw.supervision;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.gob.qw.supervision.fragments.DescalificationFragment;
import pe.gob.qw.supervision.fragments.FirstEvaluationFragment;
import pe.gob.qw.supervision.fragments.SecondEvaluationFragment;
import pe.gob.qw.supervision.listeners.OnTabNavListener;

public class TabFragmentActivity extends AppCompatActivity implements OnTabNavListener,View.OnClickListener {

    private final int DESCALIFICATION_FRAGMENT=0;
    private final int FIRST_EVALUATION_FRAGMENT=1;
    private final int SECOND_EVALUATION_FRAGMENT=2;

    private TextView tviDescalification,tviFirstEvaluation,tviSecondEvaluation;
    private List<View> indicatorViews;
    private List<TextView> titleViews;
    private int currentIndicator=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_fragment);
        getSupportActionBar().hide();
        ui();

        selectFirst();
    }

    private void selectFirst() {
        Bundle bundle= new Bundle();
        int fragmentId=DESCALIFICATION_FRAGMENT;
        updateUI(fragmentId);
        changeFragment(bundle,fragmentId);
    }

    private void updateUI(int fragmentId) {
        if(currentIndicator>=0){
            indicatorViews.get(currentIndicator).setBackgroundColor(Color.TRANSPARENT);
            titleViews.get(currentIndicator).setInputType(Typeface.NORMAL);
        }
        indicatorViews.get(fragmentId).setBackgroundColor(Color.parseColor("#ffeb3b"));
        titleViews.get(fragmentId).setInputType(Typeface.BOLD);
        currentIndicator= fragmentId;
    }

    private void ui() {
        indicatorViews= new ArrayList<>();
        indicatorViews.add(findViewById(R.id.iviDescalification));
        indicatorViews.add(findViewById(R.id.iviFirstEvaluation));
        indicatorViews.add(findViewById(R.id.iviSecondEvaluation));

        titleViews= new ArrayList<>();
        titleViews.add((TextView) findViewById(R.id.tviDescalification));
        titleViews.add((TextView)findViewById(R.id.tviFirstEvaluation));
        titleViews.add((TextView)findViewById(R.id.tviSecondEvaluation));
        //events

        for (TextView textView:titleViews) {
            textView.setOnClickListener(this) ;
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle= new Bundle();
        int fragmentId=DESCALIFICATION_FRAGMENT;

        switch (view.getId()){
            case R.id.tviDescalification:
                fragmentId=DESCALIFICATION_FRAGMENT;
                break;

            case R.id.tviFirstEvaluation:
                fragmentId=FIRST_EVALUATION_FRAGMENT;
                break;

            case R.id.tviSecondEvaluation:
                fragmentId=SECOND_EVALUATION_FRAGMENT;
                break;
        }

        updateUI(fragmentId);
        changeFragment(bundle,fragmentId);
    }

    private void changeFragment(Bundle bundle, int fragmentId) {

        Fragment fragment= factoryFragment(bundle,fragmentId);
        //fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flayContainer, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private Fragment factoryFragment(Bundle bundle,int fragmentId) {
        switch (fragmentId){
            case DESCALIFICATION_FRAGMENT:
                return DescalificationFragment.newInstance(bundle);
            case FIRST_EVALUATION_FRAGMENT:
                return FirstEvaluationFragment.newInstance(bundle);
            case SECOND_EVALUATION_FRAGMENT:
                return SecondEvaluationFragment.newInstance(bundle);
        }
        return null;
    }
}
