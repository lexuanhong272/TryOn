package com.android.xuanhong.tryonglasses.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.xuanhong.tryonglasses.Glass;
import com.android.xuanhong.tryonglasses.GlassAdapter;
import com.android.xuanhong.tryonglasses.GlassesAdapter;
import com.android.xuanhong.tryonglasses.GlassesGallery;
import com.android.xuanhong.tryonglasses.MainActivity;
import com.android.xuanhong.tryonglasses.R;
import com.android.xuanhong.tryonglasses.SearchAdapter;
import com.android.xuanhong.tryonglasses.controller.RestManager;
import com.android.xuanhong.tryonglasses.model.Glasses;
import com.android.xuanhong.tryonglasses.model.User;
import com.android.xuanhong.tryonglasses.models.view.ModelActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.android.xuanhong.tryonglasses.Activities.LoginEmail.BYTEARRAYAVATAR;
import static com.android.xuanhong.tryonglasses.Activities.LoginEmail.EMAILUSER;
import static com.android.xuanhong.tryonglasses.Activities.LoginEmail.NAMEUSER;

public class Navigation extends AppCompatActivity {

    private List<Glass> glasses = new ArrayList<>();
    private List<Glasses> mGlasses = new ArrayList<>();

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


    private RestManager mRestManager;
    List<Glasses> userGlasses;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Log out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.this.startActivity(new Intent(Navigation.this.getApplicationContext(), Welcome.class));
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    TextView txt_name;
    TextView txt_email;
    ImageView imv_avatar;

    String name;
    String email;
    byte[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mRestManager = new RestManager();
        Call<List<Glasses>> listCall = mRestManager.getGlassesService().getAllGlasses();

        listCall.enqueue(new Callback<List<Glasses>>() {
            @Override
            public void onResponse(Call<List<Glasses>> call, Response<List<Glasses>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Navigation.this, "Get glasses successfully", Toast.LENGTH_SHORT).show();
                    userGlasses = response.body();
                    for(int i = 0; i < userGlasses.size(); i++) {
                        Glasses glasses = userGlasses.get(i);
                        mGlasses.add(glasses);
                    }
                }
                else {
                    Toast.makeText(Navigation.this, "Error on get glasses", Toast.LENGTH_SHORT).show();
                    int sc = response.code();
                    switch (sc){
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Glasses>> call, Throwable t) {
                Toast.makeText(Navigation.this, "Fail to get glasses", Toast.LENGTH_SHORT).show();
            }
        });

        Intent myIntent = getIntent();
        Bundle myBundle =  myIntent.getBundleExtra("bundle");
        if(myBundle != null) {
            name = myBundle.getString("NAMEUSER");
            email = myBundle.getString("EMAILUSER");
            arr = myBundle.getByteArray("AVATAR");
        }


        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        View header = navigationView.getHeaderView(0);

        txt_name = (TextView) header.findViewById(R.id.txt_username);
        txt_email = (TextView) header.findViewById(R.id.txt_useremail);
        imv_avatar = (ImageView) header.findViewById(R.id.imgv_avatar);

        txt_name.setText(name);
        txt_email.setText(email);
        //Bitmap bmp = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        //imv_avatar.setImageBitmap(bmp);

        ButterKnife.inject(this);

        fragmentManager = getSupportFragmentManager();

        setupView();


        //////////////////-------------------
        fillList(glasses);



//        final SearchAdapter adapter = new GlassAdapter(glasses, this).registerFilter(Glass.class, "title")
//                .setIgnoreCase(true);

        final SearchAdapter mAdapter = new GlassesAdapter(mGlasses, this).registerFilter(Glasses.class, "Id").setIgnoreCase(true);

        gridView.setAdapter(mAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.filter(s.toString());
            }

            @Override public void afterTextChanged(Editable s) {}
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Glasses chose = (Glasses) gridView.getItemAtPosition(position);
                Intent intent = new Intent(Navigation.this, ModelActivity.class);
                intent.putExtra("ID_Glasses", chose.getId());
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
            case R.id.drawer_home:
                //Toast.makeText(Navigation.this, "Click on Home", Toast.LENGTH_SHORT).show();
                Navigation.this.startActivity(new Intent(Navigation.this.getApplicationContext(), Navigation.class));
                Navigation.this.finish();
                break;
//            case R.id.drawer_favorites:
//                fragmentClass = FavoritesFragment.class;
//                specialToolbarBehaviour = true;
//                break;
            case R.id.drawer_settings:
                //Toast.makeText(Navigation.this, "Click on Log out", Toast.LENGTH_SHORT).show();
                Navigation.this.startActivity(new Intent(Navigation.this.getApplicationContext(), Welcome.class));
                Navigation.this.finish();
                break;

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