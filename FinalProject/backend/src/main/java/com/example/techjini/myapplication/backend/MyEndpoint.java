package com.example.techjini.myapplication.backend;

import com.example.JokeClass;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * Created by techjini on 20/12/2016.
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.techjini.example.com",
                ownerName = "backend.myapplication.techjini.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayJoke")
    public MyJoke sayJoke(@Named("name") String name) {
        String joke = JokeClass.getOneJoke();
        MyJoke response = new MyJoke();
        response.setData(joke);
        return response;
    }

}
