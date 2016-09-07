package mUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResponseUtils {

	public static void resposeToApp(HttpServletResponse response, Object data)
			throws IOException {
		Gson gson=new Gson();
		String resultJson=gson.toJson(data);
		System.out.println(resultJson);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); 
		PrintWriter out = response.getWriter();
		out.print(resultJson);
		out.flush();
	}	
}
