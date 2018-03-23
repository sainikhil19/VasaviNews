package com.example.sainikhil.vasavinews.menuactions;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.example.sainikhil.vasavinews.R;

import static android.view.Menu.NONE;


public class MenuCreate {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private String[] tagsArray;
    private TabLayout tabLayout;
    public MenuCreate(NavigationView navigationView1,  String[] tagsArray1, TabLayout tabLayout1){
        navigationView = navigationView1;
        tagsArray=tagsArray1;
        tabLayout = tabLayout1;

    }


    public void addMenu()
    {
        Menu menu = navigationView.getMenu();
        //SubMenu categories=menu.addSubMenu("Categories");

        for(int i=0;i<tagsArray.length;i++)
        {
            final int here=i;
            menu.add(R.id.categories_group,NONE,0,tagsArray[i]).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    tabLayout.getTabAt(here+1).select();

                    return false;
                }
            });

        }

        /*<item android:title="Communicate">
        <menu>
            <item
                android:id="@+id/nav_share"
                android:icon="@drawable/ic_menu_share"
                android:title="Share" />
            <item
                android:id="@+id/nav_send"
                android:icon="@drawable/ic_menu_send"

                android:title="Send" />
            <item
                android:id="@+id/nav_settings"
                android:icon="@drawable/ic_menu_manage"
                android:title="Settings" />
            <item
                android:id="@+id/nav_about"
                android:icon="@drawable/aboutus"
                android:title="About us" />
            <item
                android:id="@+id/nav_logout"
                android:icon="@drawable/logout"
                android:title="Logout" />
        </menu>
    </item>
    */
    }
}
