/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 *  Unless agreed to in writing, this software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.herakles.service.domain;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The response object that will be returned to the caller.
 * 
 * @author yrajaram
 */
@XmlRootElement
public class ScoreResponse{
	
	private Map<String,String> debugInfo;
	private Map<String,ScoreRequest> score;
	
	public void setDebugInfo(Map<String, String> map) {
		this.debugInfo = map;
	}
	public Map<String, String> getDebugInfo() {
		return debugInfo;
	}
	public Map<String, ScoreRequest> getScore() {
		return score;
	}
	public void setScore(Map<String, ScoreRequest> score) {
		this.score = score;
	}
}
