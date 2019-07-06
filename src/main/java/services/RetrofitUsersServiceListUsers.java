package services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitUsersServiceListUsers {
    @GET("/api/users")
    Call<ResponseUsersList> getUserList(@Query("page") int idPage);

}
