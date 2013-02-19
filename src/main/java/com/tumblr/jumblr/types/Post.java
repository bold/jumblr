package com.tumblr.jumblr.types;

import com.tumblr.jumblr.JumblrClient;
import java.util.HashMap;
import java.util.Map;

public class Post {

    // @TODO make base params editable
    // @TODO ensure fields

    private Long id;
    private String reblog_key;
    private String blog_name;
    private String post_url;
    private String type;
    private int timestamp;
    private String state;
    private String format;
    private String date;
    private String[] tags;

    protected JumblrClient client;

    public String getFormat() {
        return format;
    }

    public String getState() {
        return state;
    }

    public String getPostUrl() {
        return post_url;
    }

    public String[] getTags() {
        return tags;
    }

    public String getDateGMT() {
        return date;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setClient(JumblrClient client) {
        this.client = client;
    }

    public String getReblogKey() {
        return this.reblog_key;
    }

    /**
     * Delete this post
     */
    public void delete() {
        client.postDelete(blog_name, id);
    }

    /**
     * Reblog this post
     * @param blogName the blog name to reblog onto
     * @param options options to reblog with (or null)
     * @return reblogged post
     */
    public Post reblog(String blogName, Map<String, ?> options) {
        return client.postReblog(blogName, id, reblog_key, options);
    }

    public Post reblog(String blogName) {
        return this.reblog(blogName, null);
    }

    /**
     * Like this post
     */
    public void like() {
        client.like(this.id, this.reblog_key);
    }

    /**
     * Unlike this post
     */
    public void unlike() {
        client.unlike(this.id, this.reblog_key);
    }

    /**
     * Set the blog name for this post
     * @param blogName the blog name to set
     */
    public void setBlogName(String bn) {
        blog_name = bn;
    }

    /**
     * Set the id for this post
     * @param id The id of the post
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Save this post
     */
    public void save() {
        if (id == null) {
            this.id = client.postCreate(blog_name, detail());
        } else {
            client.postEdit(blog_name, id, detail());
        }
    }

    /**
     * Detail for this post
     */
    protected Map<String, String> detail() {
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }

    /**
     * Post toString
     * @return a nice representation of this post
     */
    @Override
    public String toString() {
        return "[" + this.getClass().getName() + " (" + blog_name + ":" + id + ")]";
    }

}