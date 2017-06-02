package com.android.xuanhong.tryonglasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.android.xuanhong.tryonglasses.models.view.ModelActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GlassesGallery extends Activity {
    private List<Glass> glasses = new ArrayList<>();
    @InjectView(R.id.grid_view)
    GridView gridView;
    @InjectView(R.id.search_edit_text)
    EditText editText;

    Button btnBackToMainFromGlasses;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                        .setDefaultFontPath("fonts/Roboto-Light.ttf")
//                        .setFontAttrId(R.attr.fontPath)
//                        .build()
//        );
        setContentView(R.layout.activity_glasses_gallery);
        ButterKnife.inject(this);

        btnBackToMainFromGlasses = (Button) findViewById(R.id.btnBackToMainFromGlasses);
        btnBackToMainFromGlasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlassesGallery.this.startActivity(new Intent(GlassesGallery.this.getApplicationContext(), MainScreen.class));
            }
        });

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
                Intent intent = new Intent(GlassesGallery.this, ModelActivity.class);
                intent.putExtra("ID_Glasses", chose.getTitle());
                GlassesGallery.this.startActivity(intent);
                //Toast.makeText(GlassesGallery.this, "DA SENT", Toast.LENGTH_SHORT).show();
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
        glasses.add(new Glass("1099", "Buy 1 Free 1%", "100$", R.drawable.g11));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}