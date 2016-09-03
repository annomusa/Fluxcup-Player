
package com.musa.raffi.fluxcupplayer.models.comment;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Snippet_ {

    private String authorDisplayName;
    private String authorProfileImageUrl;
    private String authorChannelUrl;
    private AuthorChannelId authorChannelId;
    private String videoId;
    private String textDisplay;
    private Boolean canRate;
    private String viewerRating;
    private Integer likeCount;
    private String publishedAt;
    private String updatedAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The authorDisplayName
     */
    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    /**
     * 
     * @param authorDisplayName
     *     The authorDisplayName
     */
    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    /**
     * 
     * @return
     *     The authorProfileImageUrl
     */
    public String getAuthorProfileImageUrl() {
        return authorProfileImageUrl;
    }

    /**
     * 
     * @param authorProfileImageUrl
     *     The authorProfileImageUrl
     */
    public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
        this.authorProfileImageUrl = authorProfileImageUrl;
    }

    /**
     * 
     * @return
     *     The authorChannelUrl
     */
    public String getAuthorChannelUrl() {
        return authorChannelUrl;
    }

    /**
     * 
     * @param authorChannelUrl
     *     The authorChannelUrl
     */
    public void setAuthorChannelUrl(String authorChannelUrl) {
        this.authorChannelUrl = authorChannelUrl;
    }

    /**
     * 
     * @return
     *     The authorChannelId
     */
    public AuthorChannelId getAuthorChannelId() {
        return authorChannelId;
    }

    /**
     * 
     * @param authorChannelId
     *     The authorChannelId
     */
    public void setAuthorChannelId(AuthorChannelId authorChannelId) {
        this.authorChannelId = authorChannelId;
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

    /**
     * 
     * @return
     *     The textDisplay
     */
    public String getTextDisplay() {
        return textDisplay;
    }

    /**
     * 
     * @param textDisplay
     *     The textDisplay
     */
    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    /**
     * 
     * @return
     *     The canRate
     */
    public Boolean getCanRate() {
        return canRate;
    }

    /**
     * 
     * @param canRate
     *     The canRate
     */
    public void setCanRate(Boolean canRate) {
        this.canRate = canRate;
    }

    /**
     * 
     * @return
     *     The viewerRating
     */
    public String getViewerRating() {
        return viewerRating;
    }

    /**
     * 
     * @param viewerRating
     *     The viewerRating
     */
    public void setViewerRating(String viewerRating) {
        this.viewerRating = viewerRating;
    }

    /**
     * 
     * @return
     *     The likeCount
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 
     * @param likeCount
     *     The likeCount
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

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
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
