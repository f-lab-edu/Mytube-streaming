package com.flab.Mytube.dto.movie.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class StartingShowResponse {
    private long id;
    private String url;
    private String title;
    private String contents;
    private int userCount;
    private int thumbsUp;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id : "+ this.id);
        sb.append("   title : "+ this.title);
        sb.append("   contents : "+ this.contents);
        sb.append("   userCount : "+ this.userCount);
        String desc = sb.toString();
        return desc;
    }
}


