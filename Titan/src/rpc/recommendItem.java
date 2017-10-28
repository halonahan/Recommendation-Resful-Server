package rpc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import algorithm.GeoRecommendation;
import entity.Item;

/**
 * Servlet implementation class recommendItem
 */
@WebServlet("/recommendation")
public class recommendItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public recommendItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("application/json");
		// response.addHeader("Access-Control-Allow-Origin", "*");
		//
		// PrintWriter out = response.getWriter();
		//
		// JSONArray array = new JSONArray();
		// try {
		// array.put(new JSONObject().put("name", "abcd").put("address", "san
		// francisco").put("time", "01/01/2017"));
		// array.put(new JSONObject().put("name", "1234").put("address", "san
		// jose").put("time", "01/02/2017"));
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
		// out.print(array);
		// out.flush();
		// out.close();
		// JSONArray array = new JSONArray();
		// try {
		// array.put(new JSONObject().put("username", "abcd"));
		// array.put(new JSONObject().put("username", "1234"));
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
		// RpcHelper.writeJsonArray(response, array);
		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		GeoRecommendation recommendation = new GeoRecommendation();
		List<Item> items = recommendation.recommendItems(userId, lat, lon);

		JSONArray result = new JSONArray();
		try {
			for (Item item : items) {
				result.put(item.toJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, result);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
