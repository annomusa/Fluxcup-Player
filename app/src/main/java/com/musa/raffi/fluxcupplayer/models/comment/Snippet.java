
package com.musa.raffi.fluxcupplayer.models.comment;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Snippet {

    private String videoId;
    private TopLevelComment topLevelComment;
    private Boolean canReply;
    private Integer totalReplyCount;
    private Boolean isPublic;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The topLevelComment
     */
    public TopLevelComment getTopLevelComment() {
        return topLevelComment;
    }

    /**
     * 
     * @param topLevelComment
     *     The topLevelComment
     */
    public void setTopLevelComment(TopLevelComment topLevelComment) {
        this.topLevelComment = topLevelComment;
    }

    /**
     * 
     * @return
     *     The canReply
     */
    public Boolean getCanReply() {
        return canReply;
    }

    /**
     * 
     * @param canReply
     *     The canReply
     */
    public void setCanReply(Boolean canReply) {
        this.canReply = canReply;
    }

    /**
     * 
     * @return
     *     The totalReplyCount
     */
    public Integer getTotalReplyCount() {
        return totalReplyCount;
    }

    /**
     * 
     * @param totalReplyCount
     *     The totalReplyCount
     */
    public void setTotalReplyCount(Integer totalReplyCount) {
        this.totalReplyCount = totalReplyCount;
    }

    /**
     * 
     * @return
     *     The isPublic
     */
    public Boolean getIsPublic() {
        return isPublic;
    }

    /**
     * 
     * @param isPublic
     *     The isPublic
     */
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
