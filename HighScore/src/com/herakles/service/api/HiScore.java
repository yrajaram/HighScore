/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 *  Unless agreed to in writing, this software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.herakles.service.api;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.herakles.service.domain.ScoreRequest;
import com.herakles.service.domain.ScoreResponse;

/**
 * 
 * @author yrajaram
 */
@WebService
public interface HiScore {
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	ScoreResponse getAll(
			@DefaultValue("false") @QueryParam("debug") boolean debug
			);

	@Path("/{rank}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	ScoreResponse get(
			@PathParam("rank") String rank,
			@DefaultValue("false") @QueryParam("debug") boolean debug
			);
	@Path("/{rank}")
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response put(
			@PathParam("rank") String rank,
			ScoreRequest updateData,
			@DefaultValue("false") @QueryParam("debug") boolean debug
			);
	@Path("/")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response post(
			ScoreRequest data,
			@DefaultValue("false") @QueryParam("debug") boolean debug
			);
	@Path("/{rank}")
	@DELETE
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	Response delete(
			@PathParam("rank") String rank,
			@DefaultValue("false") @QueryParam("debug") boolean debug
			);
}
