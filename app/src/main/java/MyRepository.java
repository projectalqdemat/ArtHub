import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MyRepository {
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private static MyRepository instance;

    private MyRepository(Context context) {
        volleySingleton = VolleySingleton.getInstance(context);
        requestQueue = volleySingleton.getRequestQueue();
    }

    public static synchronized MyRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MyRepository(context);
        }
        return instance;
    }

    public void makeApiRequest(String url, final Response.Listener<JSONObject> listener,
                               final Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                listener, errorListener);
        requestQueue.add(request);
    }
}
