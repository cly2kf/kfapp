package talex.zsw.baselibrary.view.Volley;

import com.google.gson.Gson;

public class GsonParser
{
	GsonParser mGsonParser;
	static Gson gson;

	private GsonParser()
	{

	}

	public static Gson create()
	{
		if (gson == null)
		{
			gson = new Gson();
		}
		return gson;
	}
}
