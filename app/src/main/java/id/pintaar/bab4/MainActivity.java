package id.pintaar.bab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import id.pintaar.bab4.model.people;
import id.pintaar.bab4.service.api;
import id.pintaar.bab4.service.retrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api service = retrofitService.getRetrofitInstance().create(api.class);
        Call<people> call = service.getPeopleId("2");
        final TextView name = findViewById(R.id.name);
        final TextView height = findViewById(R.id.height);
        final TextView mass = findViewById(R.id.mass);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("loading..");
        progressDialog.show();
        final TextView gender = findViewById(R.id.gender);
        call.enqueue(new Callback<people>() {
            @Override
            public void onResponse(Call<people> call, Response<people> response) {
                if(response.isSuccessful()) {
                    name.setText(response.body().getName());
                    height.setText(response.body().getHeight());
                    mass.setText(response.body().getMass());
                    gender.setText(response.body().getGender());
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<people> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });



    }
}
