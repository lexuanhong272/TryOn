package com.android.xuanhong.tryonglasses.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.android.xuanhong.tryonglasses.Glass;
import com.android.xuanhong.tryonglasses.GlassAdapter;
import com.android.xuanhong.tryonglasses.GlassesGallery;
import com.android.xuanhong.tryonglasses.R;
import com.android.xuanhong.tryonglasses.SearchAdapter;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Navigation extends AppCompatActivity {

    private List<Glass> glasses = new ArrayList<>();
    @InjectView(R.id.grid_view)
    GridView gridView;
    @InjectView(R.id.search_edit_text)
    EditText editText;

    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    FragmentManager fragmentManager;
    NavigationView navigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ButterKnife.inject(this);

        fragmentManager = getSupportFragmentManager();

        setupView();

        fillList(glasses);
        final SearchAdapter adapter = new GlassAdapter(glasses, this).registerFilter(Glass.class, "title")
                .setIgnoreCase(true);
        gridView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override public void afterTextChanged(Editable s) {}
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Glass chose = (Glass) gridView.getItemAtPosition(position);
                Intent intent = new Intent(Navigation.this, ModelActivity.class);
                intent.putExtra("ID_Glasses", chose.getTitle());
                Navigation.this.startActivity(intent);
                //Toast.makeText(Navigation.this, "DA SENT", Toast.LENGTH_SHORT).show();
            }
        });

        //if (savedInstanceState == null) showHome();
    }

    private void setupView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //frameLayout = (FrameLayout) findViewById(R.id.content_frame);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }
    private void fillList(List<Glass> glasses) {
        glasses.add(new Glass("1023", "Sale of 50%", "90$", R.drawable.g1));
        glasses.add(new Glass("1034", "Buy 1 Free 1", "85$", R.drawable.g2));
        glasses.add(new Glass("1044", "Sale of 20%", "102$", R.drawable.g3));
        glasses.add(new Glass("1045", "Sale of 10%", "95$", R.drawable.g4));
        glasses.add(new Glass("106", "Big sale 75%", "90$", R.drawable.g5));
        glasses.add(new Glass("107", "Sale of 20%", "70$", R.drawable.g6));
        glasses.add(new Glass("1022", "Not available", "60$", R.drawable.g7));
        glasses.add(new Glass("1021", "New product", "55$", R.drawable.g8));
        glasses.add(new Glass("108", "Imported from Iceland", "95$", R.drawable.g9));
        glasses.add(new Glass("1099", "New production", "100$", R.drawable.g10));
        glasses.add(new Glass("1092", "Buy 1 Free 1%", "100$", R.drawable.g11));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void showHome() {
        //selectDrawerItem(navigationView.getMenu().getItem(0));
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void selectDrawerItem(MenuItem menuItem) {
        boolean specialToolbarBehaviour = false;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
//            case R.id.drawer_home:
//                fragmentClass = HomeFragment.class;
//                break;
//            case R.id.drawer_favorites:
//                fragmentClass = FavoritesFragment.class;
//                specialToolbarBehaviour = true;
//                break;
//            case R.id.drawer_settings:
//                fragmentClass = SettingsFragment.class;
//                break;

//            default:
//                fragmentClass = HomeFragment.class;
//                break;
        }

        try {
            //Fragment fragment = (Fragment) fragmentClass.newInstance();
            //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setToolbarElevation(specialToolbarBehaviour);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setToolbarElevation(boolean specialToolbarBehaviour) {
        if (specialToolbarBehaviour) {
            toolbar.setElevation(0.0f);
            //frameLayout.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));
        } else {
            //toolbar.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));
            frameLayout.setElevation(0.0f);
        }
    }

    public void showSnackbarMessage(View v) {
        EditText et_snackbar = (EditText) findViewById(R.id.et_snackbar);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        View view = findViewById(R.id.coordinator_layout);
        if (et_snackbar.getText().toString().isEmpty()) {
            //textInputLayout.setError(getString(R.string.alert_text));
        } else {
            textInputLayout.setErrorEnabled(false);
            et_snackbar.onEditorAction(EditorInfo.IME_ACTION_DONE);
            Snackbar.make(view, et_snackbar.getText().toString(), Snackbar.LENGTH_LONG)
                    .setAction(getResources().getString(android.R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Do nothing
                        }
                    })
                    .show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        drawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}