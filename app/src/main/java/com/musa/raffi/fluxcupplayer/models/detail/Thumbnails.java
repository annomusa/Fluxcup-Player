
package com.musa.raffi.fluxcupplayer.models.detail;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Thumbnails {

    private Default _default;
    private Medium medium;
    private High high;
    private Standard standard;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The _default
     */
    public Default getDefault() {
        return _default;
    }

    /**
     * 
     * @param _default
     *     The default
     */
    public void setDefault(Default _default) {
        this._default = _default;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * 
     * @return
     *     The high
     */
    public High getHigh() {
        return high;
    }

    /**
     * 
     * @param high
     *     The high
     */
    public void setHigh(High high) {
        this.high = high;
    }

    /**
     * 
     * @return
     *     The standard
     */
    public Standard getStandard() {
        return standard;
    }

    /**
     * 
     * @param standard
     *     The standard
     */
    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
