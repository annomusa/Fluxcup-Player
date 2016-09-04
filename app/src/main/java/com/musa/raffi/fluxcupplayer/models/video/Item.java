
package com.musa.raffi.fluxcupplayer.models.video;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Item {

    private String kind;
    private String etag;
    private Id id;
    private Snippet snippet;
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
     *     The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     * 
     * @param etag
     *     The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Id getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The snippet
     */
    public Snippet getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
