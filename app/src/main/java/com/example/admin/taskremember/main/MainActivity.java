package com.example.admin.taskremember.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.taskremember.ProductivityFragment.ProductivityFragment;
import com.example.admin.taskremember.R;
import com.example.admin.taskremember.newtask.NewTaskActivity;


public class MainActivity extends AppCompatActivity {
    ViewPager vpTabs;
    TabLayout tlTabs;
    public final static int ACTIVITY_CODE = 101;
    private FloatingActionButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmet_tasks);
        vpTabs = findViewById(R.id.vp_main_activity);
        tlTabs = findViewById(R.id.tabs);


        FragmentManager fragmentManager = getSupportFragmentManager();
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(fragmentManager);
        vpTabs.setAdapter(adapter);
        tlTabs.setupWithViewPager(vpTabs);

        imageButton = findViewById(R.id.imageButtonAddNewTascActivity);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivityForResult(intent, ACTIVITY_CODE);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.send_masseg) {
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


    public static class TabsFragmentAdapter extends FragmentPagerAdapter {


        public TabsFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return TasksFragment.newInstance();
            }
            if (position == 1) {

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
            if (position == 0) {
                return "Задачи";
            }
            if (position == 1) {
                return "Продуктивность";
            }

            return super.getPageTitle(position);
        }
    }


}
