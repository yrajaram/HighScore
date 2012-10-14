/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 *  Unless agreed to in writing, this software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.herakles.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.herakles.service.api.HiScore;
import com.herakles.service.domain.ScoreRequest;
import com.herakles.service.domain.ScoreResponse;

/**
 * This is the API implementation.
 * 
 * @author yrajaram
 *
 */
@WebService(endpointInterface = "com.herakles.service.api.HiScore")
@Path("/score")
public class HiScoreImpl implements HiScore{
	private HttpHeaders headers;
	private static Hashtable<String, ScoreRequest> Cache = new Hashtable<String, ScoreRequest>();
	
	public HiScoreImpl (@Context HttpHeaders headers){
		super();
		this.headers = headers;
	}
	public HiScoreImpl (){
		super();
	}

	private void printCache() {
		for (int i = 0; i < Cache.size(); i++) {
			System.out.println("Cache entity with key ["+i+"] has the value ["+Cache.get(""+i)+"]");
		}
	}
	private void setDebugInfo(boolean debug, ScoreResponse ret) {
		if (debug){
			// Do whatever you want here; this is just a sample
			System.out.println("Enabling debug instrumentation");
			Map<String, String> params = new HashMap<String, String>();
						
			Map<String, Cookie> reqCookies = headers.getCookies();
			for (String key : reqCookies.keySet()) {
				params.put(key, reqCookies.get(key).toString());
			}
			
			MultivaluedMap<String, String> reqHeaders = headers.getRequestHeaders();
			for (String key : reqHeaders.keySet()) {
				params.put(key, reqHeaders.getFirst(key)); // TODO: get all params not the first one alone

			}
			ret.setDebugInfo(params);
		}
	}
	private void populateResponse(String rank, ScoreResponse ret) {
		ScoreRequest requestedResponse = Cache.get(rank);
		Map<String, ScoreRequest> m = new HashMap<String, ScoreRequest>();

		if (requestedResponse!=null) {
			m.put(rank, requestedResponse);
		}
		ret.setScore(Collections.unmodifiableMap(m));
	}

	public ScoreResponse getAll(boolean debug) {
		printCache();
		ScoreResponse ret = new ScoreResponse();

		ret.setScore(Collections.unmodifiableMap(Cache));
		
		setDebugInfo(debug, ret);
		return ret;
	}	
	public ScoreResponse get(String rank, boolean debug) {
		printCache();
		ScoreResponse ret = new ScoreResponse();
		int r = 1;
		try {
			r = Integer.valueOf(rank);
		}catch (NumberFormatException ne){
			System.out.println("Invalid value for rank ["+rank+"] provided. Using "+r);
		}
		rank = ""+r;
		populateResponse(rank, ret);
		
		setDebugInfo(debug, ret);
		return ret;
	}
	public Response put(String rank, ScoreRequest updateData, boolean debug) {
		printCache();
		ScoreResponse ret = new ScoreResponse();
		if (!Cache.contains(updateData)) {
			Cache.remove(rank);
			Cache.put(rank, updateData);
			sortCache();
			populateResponse(rank, ret);
			setDebugInfo(debug, ret);
			printCache();
			return Response.status(201).entity(ret).build();
		} else {
			return Response.status(400).entity(ret).build();
		}
	}
//{"playerOne":"yash","playerTwo":"you","scoreOne":"1","scoreTwo":"2"}
	public Response post(ScoreRequest data, boolean debug) {
		printCache();
		ScoreResponse ret = new ScoreResponse();
		setDebugInfo(debug, ret);

		if (!Cache.contains(data)) {
			String rank = ""+Cache.size();
			Cache.put(rank, data);
			sortCache();
			populateResponse(rank, ret);
			printCache();
			return Response.status(201).entity(ret).build();
		} else {
			return Response.status(400).entity(ret).build();
		}
	}
	
	private void sortCache() {
		List<ScoreRequest> list = new ArrayList<ScoreRequest>();
		list.addAll(Cache.values());
		//System.out.println("Original:"+list);

		Collections.sort(list); //put in ascending order; if we change the comparator then we can do descending order as natural-order 
		Collections.reverse(list);
		//System.out.println("Sorted:"+list);
		printCache();

		Cache.clear();
		for (int i = 0; i < list.size(); i++) {
			Cache.put(""+i, list.get(i));
		}
		//TODO: Limit to say to 25 or 50 rows so we don't haul all million rows
		//TODO: Store in DB to show historic data
	}

	public Response delete(String rank, boolean debug) {
		printCache();
		Cache.remove(rank);
		printCache();
		return Response.status(204).build();
	}
}
