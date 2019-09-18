package v1.post.model;

import v1.post.entity.PostData;

public class Response {

    private PostData data;
    private String status;

    public Response(PostData data, String status) {
        this.data = data;
        this.status = status;
    }

    public PostData getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
