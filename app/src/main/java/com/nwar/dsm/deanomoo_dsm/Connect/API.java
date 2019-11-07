package com.nwar.dsm.deanomoo_dsm.Connect;

import com.nwar.dsm.deanomoo_dsm.DataModule.Token;
import retrofit2.Call;
import retrofit2.http.*;

public interface API {
    @FormUrlEncoded
    @POST("api/login")
    Call<Token> signIn (@Field("userId") String ID, @Field("password") String PW);
}