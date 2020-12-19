package com.example.timestamp.ui.myStamp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MyStampDetailMenuRequest extends StringRequest {

    //서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://lyg6452.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public MyStampDetailMenuRequest(String resID, String title, String image, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("resID", resID);
        map.put("title", title);
        map.put("image", image);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
