package com.example.dell.beekeeping.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.beekeeping.Adapter.Apiary_Adapter;
import com.example.dell.beekeeping.DataBase.RecordSql;
import com.example.dell.beekeeping.R;
import com.example.dell.beekeeping.fragment.Apiaries;
import com.example.dell.beekeeping.fragment.Visit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.dell.beekeeping.fragment.Apiaries.record_model;

public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FloatingActionButton fab;
    Apiaries apiaries;
    Apiary_Adapter apiary_adapter;
    public static TextView textView;
    public static TextView textView1;
    public  static TextView edit;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static Toolbar toolbar;
    public SearchView searchView;
    ArrayList<RecordSql> list = new ArrayList<>();
    public static MenuItem menuItem;
    DrawerLayout drawerLayout;
    public static NavigationView navigationView;

    TextView layout0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.items);
        textView1 = findViewById(R.id.appName);
        edit = findViewById(R.id.Edit1);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation);
        View view = navigationView.inflateHeaderView(R.layout.navigation_header);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                showSelectedItem(menuItem);
                return false;
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i == 0){

                }else if (i == 1){

                    clearActionMode();
                }
            }

            @Override
            public void onPageSelected(int i) {
//                if (i == 0){
//                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                        @Override
//                        public boolean onQueryTextSubmit (String s) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onQueryTextChange (String s) {
//                            apiary_adapter.getFilter().filter(s);
//                            return false;
//                        }
//
//
//                    });
//            }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        tabLayout.setupWithViewPager(mViewPager);





        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


  @Override
   public boolean onCreateOptionsMenu(Menu menu) {

//
        if (Apiaries.is_in_contextualMode){
            getMenuInflater().inflate(R.menu.menu_record,menu);

        }else {

            getMenuInflater().inflate(R.menu.menu_main, menu);
        }

       return true;
   }

    @Override
   public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       int id = item.getItemId();

      //noinspection SimplifiableIfStatement

         if (id == R.id.share){

        }else if(id == R.id.delete){

            if (Apiary_Adapter.counter == 0){
                Toast.makeText(this, "please select item to delete", Toast.LENGTH_SHORT).show();
            }else {
                showDialogueBox();
            }

//        }else if (id == android.R.id.home ){
//            if (Apiaries.is_in_contextualMode = true){
//                clearActionMode();
//                Apiaries.apiary_adapter.notifyDataSetChanged();
//            }else {
//                super.onBackPressed();
//            }

        }else if (actionBarDrawerToggle.onOptionsItemSelected(item)){

             return true;
         }
        return super.onOptionsItemSelected(item);


    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position== 0){
                return new Apiaries();
            }else if (position== 1){
                return new Visit();
            }else
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0){
                return "Apiaries";
            }else if (position== 1){
                return "Visits Report";
            }else
            return super.getPageTitle(position);
        }
    }


    public static void clearActionMode(){

        Apiaries.is_in_contextualMode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_main);
        textView.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        textView1.setVisibility(View.VISIBLE);
        textView.setText("0 items selected");
        Apiary_Adapter.counter = 0;
        Apiary_Adapter.selectionList.clear();
        Apiaries.apiary_adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (Apiaries.is_in_contextualMode == true){
            clearActionMode();
        }else {
            super.onBackPressed();

        }


    }
    public  void showDialogueBox(){
        apiaries.is_in_contextualMode = true;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.delete_marked_item,null);
        builder.setView(view);
        TextView textView = view.findViewById(R.id.confirmation);
        TextView textView1 = view.findViewById(R.id.question);
        Button btnOk = view.findViewById(R.id.ok);
        Button btnCancel = view.findViewById(R.id.cancel);
        final AlertDialog alertDialog = builder.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Apiaries.is_in_contextualMode = false;
                Apiary_Adapter apiary_adapter = new Apiary_Adapter(getApplicationContext(),list,apiaries);
                apiary_adapter.deleteRecord(apiary_adapter.selectionList);

                clearActionMode();

                Toast.makeText(MainActivity.this, "marked item deleted", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


    public  void showDialogueBoxForDeleteAll(){
        apiaries.is_in_contextualMode = true;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.delete_alert_layout,null);
        builder.setView(view);
        TextView textView = view.findViewById(R.id.confirmation);
        TextView textView1 = view.findViewById(R.id.question);
        Button btnOk = view.findViewById(R.id.ok);
        Button btnCancel = view.findViewById(R.id.cancel);

        final AlertDialog alertDialog = builder.create();
        builder.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record_model.deleteAllRecord();
                Toast.makeText(getApplicationContext(), "All Record deleted ", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });

    }

    public void showSelectedItem(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.Feedback){
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();

        }else if (id ==R.id.AddToApiary){
            Intent intent = new Intent(getApplicationContext(),Record.class);
            startActivityForResult(intent,apiaries.ADD_RECORD_REQUEST);

        }else if(id == R.id. profile){
        startActivity(new Intent(MainActivity.this, Profile.class));

        }else if(id == R.id.deleteAll){
        showDialogueBoxForDeleteAll();

         }else if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,Settings.class));

        }else if (id == R.id.Add_to_Gallery){
            startActivity(new Intent(MainActivity.this,GalleryActivity.class));
        }
    }

}
