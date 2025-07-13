package codea.dto;

import java.util.List;

public record ProductGroupByLabelDTO(
    String label,
    List<ProductWithImagesDTO> products
) {}
