package gr.aueb.cf.xmldemoproject03.interfaces;

import gr.aueb.cf.xmldemoproject03.models.TestTraveler;
import gr.aueb.cf.xmldemoproject03.models.Travelerinformation;
import gr.aueb.cf.xmldemoproject03.models.TravelerinformationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiResponseService {

//    @GET("api/Traveler?page=1")
//    Call<TravelerinformationResponse> getTravelerData();

    @GET("api/Traveler")
    Call<TravelerinformationResponse> getTravelerData(@Query("page") int page);


    @POST("/api/Traveler")
    Call<Travelerinformation> createTraveler(@Body TestTraveler travelerinformation);

    @GET("api/Traveler/{id}")
    Call<Travelerinformation> getTravelerInformation(@Path("id") String id);



}
