/*
 * Created by Junseok Oh on 2016.
 * Copyright by Good-Reserve Project @kotohana5706
 * All rights reversed.
 */

package kr.edcan.rerant.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import kr.edcan.rerant.R;
import kr.edcan.rerant.databinding.ActivityReserveCompleteBinding;
import kr.edcan.rerant.model.Bucket;
import kr.edcan.rerant.model.Menu;
import kr.edcan.rerant.model.Reservation;
import kr.edcan.rerant.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReserveCompleteActivity extends AppCompatActivity {
    Call<Reservation> reservationCall;
    ActivityReserveCompleteBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reserve_complete);
        initAppbarLayout();
        setDefault();
    }

    private void setDefault() {
        intent = getIntent();
        reservationCall = NetworkHelper.getNetworkInstance().getReservationInfo(intent.getStringExtra("id"));
        reservationCall.enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                switch (response.code()) {
                    case 200:
                        Reservation data = response.body();
                        String menuResult = "";
                        Bucket bucket = data.getReservation_menu();
                        for(Menu m : bucket.getMenus()){
                            menuResult += m.getName() + " ";
                        }
                        binding.reserveCode.setText(data.getReservation_code());
                        binding.reserveDateStatus.setText(data.getReservation_time().toLocaleString());
                        binding.reserveResTitle.setText(data.getRestaurant_name());
                        binding.reserveInfo.setText(menuResult);
                        binding.reserveMoney.setText(data.getReservation_price()+" 원");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {

            }
        });
        binding.reserveConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.reserveCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReserveCompleteActivity.this, "정식 서비스 이용때 지원 예정입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAppbarLayout() {
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setBackgroundColor(Color.WHITE);
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setTitle("예약 완료");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
