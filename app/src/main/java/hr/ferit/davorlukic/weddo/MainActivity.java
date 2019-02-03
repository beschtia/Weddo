package hr.ferit.davorlukic.weddo;


import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements PersonClickInterface, PersonDialog.OnInputListener, GroupFragment.OnFragmentInteractionListener{

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Button mBtnSummary, mBtnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        initViews();
        setUpPager();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tab);
        mBtnSummary = findViewById(R.id.btnSummary);
        mBtnWeb = findViewById(R.id.btnWeb);

        mBtnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SummaryDialog dialog = new SummaryDialog();
                dialog.show(getSupportFragmentManager(),"SummaryDialog");
            }
        });

        mBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://www.dlukic.ml");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    private void setUpPager() {
        PagerAdapter pagerAdapter = new myPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void sendInput(String name, String lastName) {
        Person person=new Person(name,lastName);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(person);
        realm.commitTransaction();
        PersonFragment frag = (PersonFragment) mViewPager.getAdapter().instantiateItem(mViewPager,0);
        frag.onInsert(person);

    }


    @Override
    public void onFragmentInteraction(String fragment, String sender, String input) {
        Realm realm = Realm.getDefaultInstance();
        Group result = realm.where(Group.class).like("id", fragment).findFirst();

        realm.beginTransaction();
        if (sender.equalsIgnoreCase("btnName")) {
            result.setName(input);
        }
        if (sender.equalsIgnoreCase("btnDownPayment")){
            result.setDownPayment(Double.valueOf(input));
        }
        if (sender.equalsIgnoreCase("btnPrice")){
            result.setPrice(Double.valueOf(input));
        }
        if (sender.equalsIgnoreCase("btnPhone")){
            result.setPhone(input);
        }
        if (sender.equalsIgnoreCase("btnNote")){
            result.setNote(input);
        }
        realm.commitTransaction();
    }


    @Override
    public void onPersonClicked(int adapterPosition) {
        PersonFragment frag = (PersonFragment) mViewPager.getAdapter().instantiateItem(mViewPager,0);
        frag.onPersonClicked(adapterPosition);
    }
}
