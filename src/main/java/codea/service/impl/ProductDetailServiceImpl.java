package codea.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codea.dao.ColorDAO;
import codea.dao.GalleryDAO;
import codea.dao.ProductDAO;
import codea.dao.ProductDetailDAO;
import codea.dao.ProductDetailSizeDAO;
import codea.dto.ProductDetailCreateRequest;
import codea.dto.ProductDetailUpdateRequest;
import codea.entity.Color;
import codea.entity.Gallery;
import codea.entity.Product;
import codea.entity.ProductDetail;
import codea.entity.ProductDetailSize;
import codea.service.ProductDetailService;
import codea.utils.CloudinaryUtils;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	@Autowired
	ProductDetailDAO detailDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	ColorDAO colorDAO;
	@Autowired
	GalleryDAO galleryDAO;
	@Autowired
	ProductDetailSizeDAO detailSizeDAO;
	@Autowired
	CloudinaryUtils cloudinaryUtils;
	
	@Override
	public ProductDetail create(ProductDetailCreateRequest req) {
        Product product = productDAO.findById(req.getProductId()).get();

        Color color = new Color();
        color.setName(req.getColor().getName());
        color.setHexCode(req.getColor().getHexCode());
        colorDAO.save(color);

        ProductDetail detail = new ProductDetail();
        detail.setProduct(product);
        detail.setColor(color);
        detailDAO.save(detail);

        for (ProductDetailCreateRequest.SizeRequest sizeReq : req.getSizes()) {
            ProductDetailSize size = new ProductDetailSize();
            size.setSize(sizeReq.getSize());
            size.setQuantity(sizeReq.getQuantity());
            size.setPrice(sizeReq.getPrice());
            size.setDiscountPercent(sizeReq.getDiscountPercent());
            size.setRealPrice(sizeReq.getRealPrice());
            size.setProductDetail(detail);
            detailSizeDAO.save(size);
        }

        for (ProductDetailCreateRequest.GalleryRequest galleryReq : req.getGalleries()) {
            Gallery gallery = new Gallery();
            gallery.setImageUrl(galleryReq.getImageUrl());
            gallery.setProductDetail(detail);
            galleryDAO.save(gallery);
        }

        return detail;
    }

	@Override
	public ProductDetail update(ProductDetailUpdateRequest req) {
		ProductDetail detail = detailDAO.findById(req.getProductDetailId()).get();

	    Color color = new Color();
	    color.setName(req.getColor().getName());
	    color.setHexCode(req.getColor().getHexCode());
	    colorDAO.save(color);
	    detail.setColor(color);
	    detailDAO.save(detail);

	    for (ProductDetailUpdateRequest.SizeRequest sizeReq : req.getSizes()) {
	        ProductDetailSize size = detailSizeDAO.findById(sizeReq.getProductDetailSizeId()).get();
	        size.setSize(sizeReq.getSize());
	        size.setQuantity(sizeReq.getQuantity());
	        size.setPrice(sizeReq.getPrice());
	        size.setDiscountPercent(sizeReq.getDiscountPercent());
	        size.setRealPrice(sizeReq.getRealPrice());
	        size.setProductDetail(detail);
	        detailSizeDAO.save(size);
	    }
	    
	    List<Gallery> oldGalleries = galleryDAO.findByProductDetail(detail);
	    List<String> newImageUrls = req.getGalleries().stream()
	        .map(ProductDetailUpdateRequest.GalleryRequest::getImageUrl)
	        .toList();

	    for (Gallery g : oldGalleries) {
	        if (!newImageUrls.contains(g.getImageUrl())) {
	            String publicId = cloudinaryUtils.extractPublicId(g.getImageUrl());
	            try {
	                cloudinaryUtils.deleteImage(publicId);
	            } catch (IOException e) {
	                throw new RuntimeException("Lỗi khi xoá ảnh trên Cloudinary", e);
	            }
	            galleryDAO.delete(g);
	        }
	    }

	    for (String url : newImageUrls) {
	        boolean exists = oldGalleries.stream()
	            .anyMatch(g -> g.getImageUrl().equals(url));
	        if (!exists) {
	            Gallery gallery = new Gallery();
	            gallery.setImageUrl(url);
	            gallery.setProductDetail(detail);
	            galleryDAO.save(gallery);
	        }
	    }

	    return detail;
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		ProductDetail productDetail = detailDAO.findById(id).get();

		    List<Gallery> galleries = productDetail.getGalleries();
		    if (galleries != null) {
		        for (Gallery g : galleries) {
		            String publicId = cloudinaryUtils.extractPublicId(g.getImageUrl());
		            try {
		                cloudinaryUtils.deleteImage(publicId);
		            } catch (IOException e) {
		                throw new RuntimeException("Lỗi khi xoá ảnh trên Cloudinary", e);
		            }
		            galleryDAO.delete(g);
		        }
		        galleries.clear();
		    }

		    List<ProductDetailSize> sizes = productDetail.getSizes();
		    if (sizes != null) {
		        for (ProductDetailSize size : sizes) {
		            size.setProductDetail(null);
		            detailSizeDAO.delete(size);
		        }
		        sizes.clear();
		    }

		    var color = productDetail.getColor();
		    detailDAO.delete(productDetail);
		    colorDAO.deleteById(color.getColorId());
	}
}
