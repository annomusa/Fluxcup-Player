
package com.musa.raffi.fluxcupplayer.models.video;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Snippet {

    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String channelTitle;
    private String liveBroadcastContent;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The publishedAt
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * 
     * @param publishedAt
     *     The publishedAt
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * 
     * @return
     *     The channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 
     * @param channelId
     *     The channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The thumbnails
     */
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    /**
     * 
     * @param thumbnails
     *     The thumbnails
     */
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * 
     * @return
     *     The channelTitle
     */
    public String getChannelTitle() {
        return channelTitle;
    }

    /**
     * 
     * @param channelTitle
     *     The channelTitle
     */
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    /**
     * 
     * @return
     *     The liveBroadcastContent
     */
    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    /**
     * 
     * @param liveBroadcastContent
     *     The liveBroadcastContent
     */
    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
