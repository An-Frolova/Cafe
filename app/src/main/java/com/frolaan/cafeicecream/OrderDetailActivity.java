package com.frolaan.cafeicecream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_CLIENT_NAME = "client_name";
    private static final String EXTRA_ICE_CREAM_TYPE = "ice_cream_type";
    private static final String EXTRA_TASTES = "tastes";
    private static final String EXTRA_ADDITIVES = "additives";

    private TextView textViewOrderNumber;
    private TextView textViewClientName;
    private TextView textViewIceCreamOrder;
    private TextView textViewAdditivesOrder;
    private TextView textViewTastesOrder;
    private Button buttonConfirmReceipt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        setUpOrderNumber();
        setUpClientName();
        setUpIceCreamType();
        setUpTastes();
        setUpAdditives();
        setUpClickListener();
    }

    private void setUpOrderNumber() {
        Random random = new Random();
        int number = random.nextInt(20);
        String orderNumber = getString(R.string.text_view_order_number, number);
        textViewOrderNumber.setText(orderNumber);
    }

    private void setUpClientName() {
        String clientName = getIntent().getStringExtra(EXTRA_CLIENT_NAME);
        textViewClientName.setText(clientName);
    }

    private void setUpIceCreamType() {
        String iceCreamType = getIntent().getStringExtra(EXTRA_ICE_CREAM_TYPE);
        String iceCreamTypeOrder = getString(R.string.text_view_icecream_order, iceCreamType);
        textViewIceCreamOrder.setText(iceCreamTypeOrder);
    }

    private void setUpTastes() {
        String tastes = getIntent().getStringExtra(EXTRA_TASTES);
        textViewTastesOrder.setText(tastes);
    }

    private void setUpAdditives() {
        String additives = getIntent().getStringExtra(EXTRA_ADDITIVES);
        textViewAdditivesOrder.setText(additives);
    }

    private void setUpClickListener() {
        buttonConfirmReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        OrderDetailActivity.this,
                        R.string.toast_order_confirmation,
                        Toast.LENGTH_SHORT
                ).show();
                Intent intent = MainActivity.newIntent(OrderDetailActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        textViewOrderNumber = findViewById(R.id.textViewOrderNumber);
        textViewClientName = findViewById(R.id.textViewClientName);
        textViewIceCreamOrder = findViewById(R.id.textViewIceCreamOrder);
        textViewTastesOrder = findViewById(R.id.textViewTastesOrder);
        textViewAdditivesOrder = findViewById(R.id.textViewAdditivesOrder);
        buttonConfirmReceipt = findViewById(R.id.buttonConfirmReceipt);
    }

    public static Intent newIntent(
            Context context,
            String clientName,
            String iceCreamType,
            String tastes,
            String additives) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_CLIENT_NAME, clientName);
        intent.putExtra(EXTRA_ICE_CREAM_TYPE, iceCreamType);
        intent.putExtra(EXTRA_TASTES, tastes);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        return intent;
    }
}