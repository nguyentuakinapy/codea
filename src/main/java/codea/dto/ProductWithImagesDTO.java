package codea.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductWithImagesDTO(
    Integer productId,
    String name,
    BigDecimal realPrice,
    BigDecimal price,
    List<String> images
) {}
