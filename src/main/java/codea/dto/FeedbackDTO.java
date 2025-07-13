package codea.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Integer productId;
    private Integer userId;
    private String comment;
    private Integer star;
}