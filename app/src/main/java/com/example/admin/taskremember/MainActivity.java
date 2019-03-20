package com.example.admin.taskremember;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements IQuantityTasksEndListner {
    ViewPager vpTabs;
    TabLayout tlTabs;

    public int getQuantityEnd() {
        return quantityEnd;
    }

    public int quantityEnd;
    SharedPreferences sharedPreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmet_tasks);
        vpTabs = findViewById(R.id.vp_main_activity);
        tlTabs = findViewById(R.id.tabs);
        sharedPreferences = getSharedPreferences(TasksFragment.APP_PREFERENCES, MODE_PRIVATE);
       quantityEnd = sharedPreferences.getInt(TasksFragment.APP_PREFERENCES_TASKS_END, 0);


        FragmentManager fragmentManager = getSupportFragmentManager();
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(fragmentManager);
        vpTabs.setAdapter(adapter);
        tlTabs.setupWithViewPager(vpTabs);
        applyThem();



    }



    private void applyThem() {
        tlTabs.setBackgroundColor(getResources().getColor(R.color.blueFranchColor));
        tlTabs.setTabTextColors(getResources().getColor(R.color.white_color),getResources().getColor(R.color.white_color));
        tlTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.white_color));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id =  item.getItemId();
        if (id == R.id.send_masseg){
            Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onQuantityTasksChange(int quantity) {
    this.quantityEnd=quantity;
    }


    public static class TabsFragmentAdapter extends FragmentPagerAdapter{



        public TabsFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return TasksFragment.newInstance();
            }
            if (position == 1){

                return ProductivityFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0){
                return "Задачи";
            }
            if (position == 1){
                return "Продуктивность";
            }

            return super.getPageTitle(position);
        }
    }




}
