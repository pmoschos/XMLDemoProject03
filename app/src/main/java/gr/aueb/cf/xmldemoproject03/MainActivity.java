package gr.aueb.cf.xmldemoproject03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gr.aueb.cf.xmldemoproject03.adapters.TravelerAdapter;
import gr.aueb.cf.xmldemoproject03.httpclient.RetrofitClient;
import gr.aueb.cf.xmldemoproject03.interfaces.ApiResponseService;
import gr.aueb.cf.xmldemoproject03.models.TestTraveler;
import gr.aueb.cf.xmldemoproject03.models.Travelerinformation;
import gr.aueb.cf.xmldemoproject03.models.TravelerinformationResponse;
import gr.aueb.cf.xmldemoproject03.models.Travelers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText postNameET;
    private EditText postEmailET;
    private EditText postAddressET;
    private Button postButton;
    private RecyclerView recyclerView;
    private EditText idET;
    private TextView nameTV;
    private TextView emailTV;
    private TextView addressTV;
    private TextView dateTV;
    private Button getButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postNameET = findViewById(R.id.postNameET);
        postEmailET = findViewById(R.id.postEmailET);
        postAddressET = findViewById(R.id.postAddressET);
        postButton = findViewById(R.id.postButton);
        recyclerView = findViewById(R.id.recyclerView);
        idET = findViewById(R.id.idET);
        nameTV = findViewById(R.id.nameTV);
        emailTV = findViewById(R.id.emailTV);
        addressTV = findViewById(R.id.addressTV);
        dateTV = findViewById(R.id.dateTV);
        getButton = findViewById(R.id.getButton);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Create a new post
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = postNameET.getText().toString();
                String email = postEmailET.getText().toString();
                String address = postAddressET.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !address.isEmpty()) {
                    createNewTraveler(name, email, address);
                } else {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Get traveler details
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String travelerId = idET.getText().toString();
                getTravelerById(travelerId);
            }
        });

        // Fetch Data
        ApiResponseService apiResponseService = RetrofitClient.getClient().create(ApiResponseService.class);
        Call<TravelerinformationResponse> call = apiResponseService.getTravelerData(10);

        call.enqueue(new Callback<TravelerinformationResponse>() {
            @Override
            public void onResponse(Call<TravelerinformationResponse> call, Response<TravelerinformationResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "onCreate: success");
                    TravelerinformationResponse travelerinformationResponse = response.body();
                    Travelers travelers = travelerinformationResponse.getTravelers();
                    List<Travelerinformation> travelerinformationList = travelers.getTravelerinformations();

                    recyclerView.setAdapter(new TravelerAdapter(travelerinformationList, getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<TravelerinformationResponse> call, Throwable t) {
                Log.d("MainActivity", "onCreate: error" + t.getMessage());
            }
        });

    }

    private void getTravelerById(String travelerId) {
        ApiResponseService apiResponseService = RetrofitClient.getClient().create(ApiResponseService.class);

        Call<Travelerinformation> getCall = apiResponseService.getTravelerInformation(travelerId);
        getCall.enqueue(new Callback<Travelerinformation>() {
            @Override
            public void onResponse(Call<Travelerinformation> call, Response<Travelerinformation> response) {
                if (response.isSuccessful()) {
                    Travelerinformation fetchedInfo = response.body();
                    idET.setText(fetchedInfo.getId());
                    nameTV.setText(fetchedInfo.getName());
                    emailTV.setText(fetchedInfo.getEmail());
                    addressTV.setText(fetchedInfo.getAddress());
                    dateTV.setText(fetchedInfo.getCreatedat());

                    // My tests
                    Log.d("Name", fetchedInfo.getName());
                    Log.d("Email", fetchedInfo.getEmail());
                }
            }

            @Override
            public void onFailure(Call<Travelerinformation> call, Throwable t) {
                // Handle failure
                // Log the error message
                // Log.e("API Error", t.getMessage());
            }
        });
    }

    private void createNewTraveler(String name, String email, String address) {
        // Initialize Retrofit
        ApiResponseService apiResponseService = RetrofitClient.getClient().create(ApiResponseService.class);

        // Create a new TestTraveler object and set the dynamic values
        TestTraveler newTraveler = new TestTraveler();
        newTraveler.setName(name);
        newTraveler.setEmail(email);
        newTraveler.setAddress(address);

        // Make the POST request
        Call<Travelerinformation> call = apiResponseService.createTraveler(newTraveler);
        call.enqueue(new Callback<Travelerinformation>() {
            @Override
            public void onResponse(Call<Travelerinformation> call, Response<Travelerinformation> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "POST success");

                    // Print the server's response
                    Travelerinformation responseBody = response.body();
                    if (responseBody != null) {
                        Log.d("MainActivity", "Response Body: " + responseBody.toString());

                    } else {
                        Log.d("MainActivity", "Response Body is null");
                    }
                } else {
                    Log.d("MainActivity", "POST failed with status code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Travelerinformation> call, Throwable t) {
                Log.d("MainActivity", "POST error" + t.getMessage());
            }
        });
    }



}