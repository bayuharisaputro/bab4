package id.pintaar.bab4.service;

import id.pintaar.bab4.model.people;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface api {
    @GET("people/{id}")
    Call<people> getPeopleId(@Path("id") String id);


}

