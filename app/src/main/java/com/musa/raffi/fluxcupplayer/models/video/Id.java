
package com.musa.raffi.fluxcupplayer.models.video;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Id {

    private String kind;
    private String videoId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The videoId
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 
     * @param videoId
     *     The videoId
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
