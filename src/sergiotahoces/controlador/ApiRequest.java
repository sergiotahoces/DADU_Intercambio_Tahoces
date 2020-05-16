package sergiotahoces.controlador;


import com.squareup.okhttp.*;
import java.io.IOException;

public class ApiRequest {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	OkHttpClient client;

	public ApiRequest() {
		client = new OkHttpClient();
	}

	public String getRequest(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}

	public String postRequest(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}
	public String putRequest(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).put(body).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}

	public String deleteRequest(String url) throws IOException {
		Request request = new Request.Builder().url(url).delete().build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public String deleteUnoRequest(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).delete(body).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}

	public String postRequestWithParams(String url, String json) throws IOException {
		RequestBody formBody = new FormEncodingBuilder().add("json", json).build();
		Request request = new Request.Builder().url(url).post(formBody).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}
	
}
