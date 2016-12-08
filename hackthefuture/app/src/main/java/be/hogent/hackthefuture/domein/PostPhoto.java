package be.hogent.hackthefuture.domein;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matthias on 8/12/2016.
 */

public class PostPhoto {


    public String description, base64imagedata, datetime;

    public PostPhoto(String desc, String img){

        description=desc;
        base64imagedata=img;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd-hhmmss");
        datetime = sdf.format(new Date());

        Log.i("PostPhoto", datetime);

    }

}
