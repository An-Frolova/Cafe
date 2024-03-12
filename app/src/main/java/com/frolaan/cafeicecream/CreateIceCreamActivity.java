package com.frolaan.cafeicecream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateIceCreamActivity extends AppCompatActivity {

    private static final String EXTRA_CLIENT_NAME = "client_name";
    private static final String NO_SCOOP = "—";

    private RadioGroup radioGroup;
    private RadioButton radioButtonOnPlate;
    private RadioButton radioButtonInCone;
    private Spinner spinnerTastesOne;
    private Spinner spinnerTastesTwo;
    private Spinner spinnerTastesThree;
    private TextView textViewScoopThree;
    private CheckBox checkBoxMarshmello;
    private CheckBox checkBoxConfiture;
    private CheckBox checkBoxWhippedCream;
    private CheckBox checkBoxKiwi;
    private CheckBox checkBoxBanana;
    private CheckBox checkBoxStrawberry;
    private Button buttonMakeOrder;

    private String iceCreamType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ice_cream);
        initViews();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonOnPlate.getId()) {
                    onUserChooseOnPlate();
                } else if (checkedId == radioButtonInCone.getId()) {
                    onUserChooseInCone();
                }
            }
        });
        radioButtonInCone.setChecked(true);
        setUpClickListener();
    }

    private void onUserMadeOder() {
        String clientName = getIntent().getStringExtra(EXTRA_CLIENT_NAME);

        ArrayList<String> tastes = new ArrayList<>();
        String firstScoop = spinnerTastesOne.getSelectedItem().toString();
        tastes.add(firstScoop);
        String secondScoop = spinnerTastesTwo.getSelectedItem().toString();
        if (!secondScoop.equals(NO_SCOOP)) {
            tastes.add(secondScoop);
        }
        String thirdScoop = spinnerTastesThree.getSelectedItem().toString();
        if (!thirdScoop.equals(NO_SCOOP)) {
            tastes.add(thirdScoop);
        }

        ArrayList<String> additives = new ArrayList<>();
        if (checkBoxMarshmello.isChecked()) {
            additives.add(checkBoxMarshmello.getText().toString());
        }
        if (checkBoxKiwi.isChecked()) {
            additives.add(checkBoxKiwi.getText().toString());
        }
        if (checkBoxConfiture.isChecked()) {
            additives.add(checkBoxConfiture.getText().toString());
        }
        if (checkBoxBanana.isChecked()) {
            additives.add(checkBoxBanana.getText().toString());
        }
        if (checkBoxWhippedCream.isChecked()) {
            additives.add(checkBoxWhippedCream.getText().toString());
        }
        if (checkBoxStrawberry.isChecked()) {
            additives.add(checkBoxStrawberry.getText().toString());
        }
        if (additives.isEmpty()) {
            additives.add("без добавок");
        }
        if (radioButtonInCone.isChecked() && additives.size() > 2) {
            Toast.makeText(
                    CreateIceCreamActivity.this,
                    R.string.toast_choose_additives,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        Intent intent = OrderDetailActivity.newIntent(
                CreateIceCreamActivity.this,
                clientName,
                iceCreamType,
                tastes.toString(),
                additives.toString()
        );
        startActivity(intent);
        finish();
    }

    private void setUpClickListener() {
        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserMadeOder();
            }
        });
    }

    private void onUserChooseInCone() {
        iceCreamType = getString(R.string.radio_button_cone);
        textViewScoopThree.setVisibility(View.INVISIBLE);
        spinnerTastesThree.setVisibility(View.INVISIBLE);
    }

    private void onUserChooseOnPlate() {
        iceCreamType = getString(R.string.radio_button_plate);
        textViewScoopThree.setVisibility(View.VISIBLE);
        spinnerTastesThree.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonOnPlate = findViewById(R.id.radioButtonOnPlate);
        radioButtonInCone = findViewById(R.id.radioButtonInCone);
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
        checkBoxMarshmello = findViewById(R.id.checkBoxMarshmello);
        checkBoxConfiture = findViewById(R.id.checkBoxConfiture);
        checkBoxWhippedCream = findViewById(R.id.checkBoxWhippedCream);
        checkBoxKiwi = findViewById(R.id.checkBoxKiwi);
        checkBoxBanana = findViewById(R.id.checkBoxBanana);
        checkBoxStrawberry = findViewById(R.id.checkBoxStrawberry);
        textViewScoopThree = findViewById(R.id.textViewScoopThree);
        spinnerTastesOne = findViewById(R.id.spinnerTastesOne);
        spinnerTastesTwo = findViewById(R.id.spinnerTastesTwo);
        spinnerTastesThree = findViewById(R.id.spinnerTastesThree);
    }

    public static Intent newIntent(Context context, String clientName) {
        Intent intent = new Intent(context, CreateIceCreamActivity.class);
        intent.putExtra(EXTRA_CLIENT_NAME, clientName);
        return intent;
    }
}